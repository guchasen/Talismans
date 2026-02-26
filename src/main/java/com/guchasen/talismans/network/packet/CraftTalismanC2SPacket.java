package com.guchasen.talismans.network.packet;

import com.guchasen.talismans.screen.TalismanCraftingTableScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class CraftTalismanC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            ScreenHandler screenHandler = player.currentScreenHandler;
            if (screenHandler instanceof TalismanCraftingTableScreenHandler) {
                ((TalismanCraftingTableScreenHandler) screenHandler).updateResult();
            }
        });
    }
}
