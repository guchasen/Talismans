package com.guchasen.talismans.items;

import com.guchasen.talismans.Talismans;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.guchasen.talismans.Talismans.MOD_ID;

public class ModItems {
    private ModItems() {}

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    private static final Item STRENGTH_TALISMAN = registerItem("strength_talisman", new StrengthTalisman(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static void registerModItems() {
        Talismans.LOGGER.info("Registering ModItems for " + Talismans.MOD_ID);
    }
}
