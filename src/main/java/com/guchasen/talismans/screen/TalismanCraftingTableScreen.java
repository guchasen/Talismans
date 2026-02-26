package com.guchasen.talismans.screen;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.network.ModMessages;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TalismanCraftingTableScreen extends HandledScreen<TalismanCraftingTableScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Talismans.MOD_ID, "textures/gui/talisman_crafting_table.png");

    public TalismanCraftingTableScreen(TalismanCraftingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        
        // Add "Craft" button
        // Grid ends at x=78+18=96, Result is at x=130
        // Center of gap is (96+130)/2 = 113
        // Button width ~30-40
        int buttonX = x + 61;
        int buttonY = y + 64; // Centered vertically with result slot (y=36)
        
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("button.talismans.craft"), (button) -> {
            ClientPlayNetworking.send(ModMessages.CRAFT_TALISMAN_ID, PacketByteBufs.create());
        })
        .dimensions(buttonX, buttonY, 35, 15)
        .build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Draw Slot Icons if empty
        // Pen Slot (Slot 0) - x=18, y=20 (relative to GUI)
        if (handler.getSlot(0).getStack().isEmpty()) {
            // Assuming we have an icon texture or just draw a colored box/item sprite
            // For simplicity, let's draw a semi-transparent item or specific icon if we had one.
            // context.drawTexture(...)
        }
        
        // Paper Slot (Slot 1) - x=18, y=50
        if (handler.getSlot(1).getStack().isEmpty()) {
            // Draw Paper icon
        }
        
        // We can also draw the "Three Columns" visual separation lines here if the texture doesn't have them
        // context.fill(...)
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
