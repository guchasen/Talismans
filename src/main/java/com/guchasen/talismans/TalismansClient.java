package com.guchasen.talismans;


import com.guchasen.talismans.entity.ModEntities;
import com.guchasen.talismans.entity.render.ThunderTalismanEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TalismansClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.THUNDER_TALISMAN_ENTITY_TYPE, ThunderTalismanEntityRenderer::new);
    }
}
