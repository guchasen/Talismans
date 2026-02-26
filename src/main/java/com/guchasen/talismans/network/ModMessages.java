package com.guchasen.talismans.network;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.network.packet.CraftTalismanC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier CRAFT_TALISMAN_ID = new Identifier(Talismans.MOD_ID, "craft_talisman");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(CRAFT_TALISMAN_ID, CraftTalismanC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        // Registration will happen in the client class or a dedicated proxy
    }
}
