package com.guchasen.talismans.screen;

import com.guchasen.talismans.Talismans;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<TalismanCraftingTableScreenHandler> TALISMAN_CRAFTING_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        TALISMAN_CRAFTING_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                new Identifier(Talismans.MOD_ID, "talisman_crafting"),
                new ExtendedScreenHandlerType<>(TalismanCraftingTableScreenHandler::new));
    }
}
