package com.guchasen.talismans.screen;

import com.guchasen.talismans.block.entity.TalismanCraftingTableBlockEntity;
import com.guchasen.talismans.recipe.TalismanRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.Optional;

public class TalismanCraftingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final World world;
    // We use a separate result inventory for the output slot to avoid it being part of the persistent inventory logic if we want,
    // but here we are using the BlockEntity's inventory for everything including result (slot 6).
    // So we don't need CraftingResultInventory unless we want temporary output.
    // The prompt says "Right side area... for result output".
    // If we use Slot 6 of BE as result, it persists.
    // If we want it to be like a crafting table (transient), we should calculate it on the fly.
    // But usually machines have persistent output slots.
    // "Real-time detection" + "Click to get product".
    // If it's like a Workbench, the output is virtual.
    // If it's like a Furnace, the output is real.
    // Given "Talisman Crafting Table" and "Pen/Paper slots", it feels like a workstation.
    // Let's make the output slot virtual (calculated) like a Crafting Table, 
    // OR real (machine-like).
    // "Displays synthesis preview effect" suggests virtual.
    // But "Result output slot... click to get" is standard.
    // Let's go with Virtual Output for the Result Slot (like Workbench), 
    // because "Real-time detection" implies if I remove an item, the result disappears.
    // So I shouldn't store the result in the BlockEntity's NBT if it's just a recipe result.
    // However, the BlockEntity has a slot for it (Slot 6).
    // I will use Slot 6 as the output slot, but I will set its content based on the recipe match.
    // And when taken, I consume ingredients.

    public TalismanCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(7));
    }

    public TalismanCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.TALISMAN_CRAFTING_SCREEN_HANDLER, syncId);
        checkSize(inventory, 7);
        this.inventory = inventory;
        this.world = playerInventory.player.getWorld();
        inventory.onOpen(playerInventory.player);

        // Left Column: Pen (Slot 0)
        this.addSlot(new Slot(inventory, 0, 18, 20) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return TalismanCraftingTableBlockEntity.isPen(stack);
            }
        });

        // Left Column: Paper (Slot 1)
        this.addSlot(new Slot(inventory, 1, 18, 50) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return TalismanCraftingTableBlockEntity.isPaper(stack);
            }
        });

        // Middle Column: 2x2 Grid (Slots 2-5)
        // 0,0 -> x=60, y=20
        // 0,1 -> x=78, y=20
        // 1,0 -> x=60, y=38
        // 1,1 -> x=78, y=38
        int gridX = 62;
        int gridY = 26;
        
        // Slot 2 (0,0)
        this.addSlot(new Slot(inventory, 2, gridX, gridY));
        // Slot 3 (1,0)
        this.addSlot(new Slot(inventory, 3, gridX + 18, gridY));
        // Slot 4 (0,1)
        this.addSlot(new Slot(inventory, 4, gridX, gridY + 18));
        // Slot 5 (1,1)
        this.addSlot(new Slot(inventory, 5, gridX + 18, gridY + 18));

        // Right Column: Result (Slot 6)
        this.addSlot(new TalismanResultSlot(playerInventory.player, inventory, 6, 130, 36));

        // Player Inventory
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        
        // Initial recipe check
        // updateResult(); // Disable auto-update for button-based crafting
    }

    public void updateResult() {
        if (world.isClient) return;
        
        // Check for recipe
        SimpleInventory inv = new SimpleInventory(inventory.size());
        for(int i=0; i<inventory.size(); i++) inv.setStack(i, inventory.getStack(i));
        
        Optional<TalismanRecipe> match = world.getRecipeManager()
                .getFirstMatch(TalismanRecipe.Type.INSTANCE, inv, world);

        if (match.isPresent()) {
            // Check if output slot is empty or compatible
            ItemStack currentOutput = inventory.getStack(6);
            ItemStack recipeOutput = match.get().getOutput(null).copy();
            
            if (currentOutput.isEmpty()) {
                // Consume ingredients and set output
                consumeIngredients();
                inventory.setStack(6, recipeOutput);
            } else if (currentOutput.isOf(recipeOutput.getItem()) && currentOutput.getCount() + recipeOutput.getCount() <= currentOutput.getMaxCount()) {
                // Consume ingredients and increment output
                consumeIngredients();
                currentOutput.increment(recipeOutput.getCount());
                inventory.setStack(6, currentOutput);
            }
        }
    }

    private void consumeIngredients() {
        // Slot 0: Pen (Damage it)
        ItemStack pen = inventory.getStack(0);
        if (!pen.isEmpty() && pen.isDamageable()) {
            pen.setDamage(pen.getDamage() + 1);
            if (pen.getDamage() >= pen.getMaxDamage()) {
                inventory.setStack(0, ItemStack.EMPTY);
            }
        }

        // Slot 1: Paper (Consume 1)
        inventory.removeStack(1, 1);

        // Slot 2-5: Grid (Consume 1 each)
        for (int i = 2; i <= 5; i++) {
            inventory.removeStack(i, 1);
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        // Only re-check if input slots changed (0-5)
        // updateResult(); // Disable auto-update
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    // Custom Result Slot class
    class TalismanResultSlot extends Slot {
        private final PlayerEntity player;
        private final Inventory inventory;

        public TalismanResultSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
            this.player = player;
            this.inventory = inventory;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
            super.onTakeItem(player, stack);
            // Ingredients are consumed during crafting button press, not when taking item
        }
    }
}
