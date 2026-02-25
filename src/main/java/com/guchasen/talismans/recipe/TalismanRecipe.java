package com.guchasen.talismans.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.guchasen.talismans.Talismans;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class TalismanRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public TalismanRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) return false;

        // Slot 0: Pen (Not strictly part of recipe match here, but we can check if it exists if needed. 
        // Usually recipe checks ingredients. Let's assume Pen is handled by the TileEntity logic or we include it as ingredient 0)
        // Let's define the recipeItems order:
        // 0: Pen
        // 1: Paper
        // 2,3,4,5: Grid (2x2)
        
        if (recipeItems.size() != 6) return false;

        // Check Pen (Slot 0)
        if (!recipeItems.get(0).test(inventory.getStack(0))) return false;
        
        // Check Paper (Slot 1)
        if (!recipeItems.get(1).test(inventory.getStack(1))) return false;

        // Check Grid (Slots 2-5)
        // 2 3
        // 4 5
        // We need to match the grid. This is a simple shapeless-like check or fixed slot check?
        // "2x2 crafting grid" implies shaped or shapeless.
        // For simplicity and standard modded machines, let's do fixed slot matching for now 
        // (Slot 2 matches ingredient 2, Slot 3 matches ingredient 3, etc.)
        // Or we can do full Shaped logic.
        // Given the requirement "Real-time detection of recipe matching", let's assume exact slot mapping for simplicity in this turn.
        
        for (int i = 0; i < 4; i++) {
             if (!recipeItems.get(i + 2).test(inventory.getStack(i + 2))) {
                 return false;
             }
        }

        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    
    public DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    public static class Type implements RecipeType<TalismanRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "talisman_crafting";
    }

    public static class Serializer implements RecipeSerializer<TalismanRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "talisman_crafting";

        @Override
        public TalismanRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(6, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size() && i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new TalismanRecipe(id, output, inputs);
        }

        @Override
        public TalismanRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new TalismanRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, TalismanRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.getOutput(null));
        }
    }
}
