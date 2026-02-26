package com.guchasen.talismans.items;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.items.BuffTalismans.BaseBuffTalisman;
import com.guchasen.talismans.items.BuffTalismans.InstantHealthTalisman;
import com.guchasen.talismans.items.ThrowableTalisman.*;
import com.guchasen.talismans.items.BuffTalismans.BaseBuffTalisman;
import net.minecraft.entity.effect.StatusEffects;
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

    //common item
    public static final Item THUNDER_CORE = registerItem("thunder_core", new Item(new Item.Settings().rarity(Rarity.UNCOMMON).fireproof()));

    // 制符工具
    public static final Item TALISMAN_PEN = registerItem("talisman_pen", new TalismanPenItem(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item TALISMAN_PAPER = registerItem("talisman_paper", new TalismanPaperItem(new Item.Settings().maxCount(64)));

    // 符箓物品
    public static final Item STRENGTH_TALISMAN= registerItem("strength_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.STRENGTH,600,1));
    public static final Item JUMP_TALISMAN = registerItem("jump_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.JUMP_BOOST,200,0));
    public static final Item INVISIBILITY_TALISMAN = registerItem("invisibility_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.INVISIBILITY,1200,0));
    public static final Item LUCK_TALISMAN = registerItem("luck_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.LUCK,1800,0));
    public static final Item RESISTANCE_TALISMAN = registerItem("resistance_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.RESISTANCE,600,0));
    public static final Item SPEED_TALISMAN    = registerItem("speed_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.SPEED,300,0));
    public static final Item FIRE_RESISTANCE_TALISMAN = registerItem("fire_resistance_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON),StatusEffects.FIRE_RESISTANCE,1200,0));
    public static final Item WATER_BREATHING_TALISMAN = registerItem("water_breathing_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.WATER_BREATHING,600,0));
    public static final Item NIGHT_VISION_TALISMAN  = registerItem("night_vision_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.NIGHT_VISION,3600,0));
    public static final Item REGENERATION_TALISMAN = registerItem("regeneration_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.REGENERATION,600,0));
    public static final Item INSTANT_HEALTH_TALISMAN = registerItem("instant_health_talisman", new InstantHealthTalisman(new Item.Settings().rarity(Rarity.UNCOMMON), StatusEffects.INSTANT_HEALTH,1,1));
    public static final Item BETTER_LUCK_TALISMAN = registerItem("better_luck_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.RARE), StatusEffects.LUCK,1800,2));
    public static final Item BETTER_STRENGTH_TALISMAN = registerItem("better_strength_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.RARE), StatusEffects.STRENGTH,1200,4));
    public static final Item WONDERFUL_STRENGTH_TALISMAN = registerItem("wonderful_strength_talisman", new BaseBuffTalisman(new Item.Settings().rarity(Rarity.EPIC), StatusEffects.STRENGTH,1200,6));
    public static final Item THUNDER_TALISMAN = registerItem("thunder_talisman", new ThunderTalismanItem(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(16)));
    
    // New Talismans
    public static final Item BETTER_THUNDER_TALISMAN = registerItem("better_thunder_talisman", new BetterThunderTalismanItem(new Item.Settings().rarity(Rarity.RARE).maxCount(16)));
    public static final Item WONDERFUL_THUNDER_TALISMAN = registerItem("wonderful_thunder_talisman", new WonderfulThunderTalismanItem(new Item.Settings().rarity(Rarity.EPIC).maxCount(16)));
    public static final Item EXPLOSIVE_TALISMAN = registerItem("explosive_talisman", new ExplosiveTalismanItem(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(16)));
    public static final Item GREATER_EXPLOSIVE_TALISMAN = registerItem("greater_explosive_talisman", new GreaterExplosiveTalismanItem(new Item.Settings().rarity(Rarity.RARE).maxCount(16)));
    public static final Item FIRE_TALISMAN = registerItem("fire_talisman", new FireTalismanItem(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(16)));

    public static void registerModItems() {
        Talismans.LOGGER.info("Registering ModItems for " + Talismans.MOD_ID);
    }
}
