package com.guchasen.talismans;


import com.guchasen.talismans.entity.ModEntities;
import com.guchasen.talismans.entity.render.ThunderTalismanEntityRenderer;
import com.guchasen.talismans.screen.ModScreenHandlers;
import com.guchasen.talismans.screen.TalismanCraftingTableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class TalismansClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册生物实体renderer
        EntityRendererRegistry.register(ModEntities.THUNDER_TALISMAN_ENTITY_TYPE, ThunderTalismanEntityRenderer::new);
        // 注册Screen
        HandledScreens.register(ModScreenHandlers.TALISMAN_CRAFTING_SCREEN_HANDLER, TalismanCraftingTableScreen::new);
    }
}
