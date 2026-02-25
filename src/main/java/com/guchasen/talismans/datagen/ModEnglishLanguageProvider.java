package com.guchasen.talismans.datagen;


import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 物品翻译
        //translationBuilder.add(ModItems.THUNDER_TALISMAN, "Thunder Talisman");
//        translationBuilder.add(ModItems.NIGHT_VISION_TALISMAN, "Night Vision Talisman");
//        translationBuilder.add(ModItems.WATER_BREATHING_TALISMAN, "Water Breathing Talisman" );
//        translationBuilder.add(ModItems.FIRE_RESISTANCE_TALISMAN, "Fire Resistance Talisman");
//        translationBuilder.add(ModItems.SPEED_TALISMAN, "Speed Talisman");
//        translationBuilder.add(ModItems.RESISTANCE_TALISMAN, "Resistance Talisman");
//        translationBuilder.add(ModItems.INVISIBILITY_TALISMAN, "Invisibility Talisman");
//        translationBuilder.add(ModItems.JUMP_TALISMAN, "Jump Talisman");
//        translationBuilder.add(ModItems.STRENGTH_TALISMAN, "Strength Talisman");
//        translationBuilder.add(ModItems.LUCK_TALISMAN, "Luck Talisman");
//        translationBuilder.add(ModItems.THUNDER_TALISMAN, "Thunder Talisman ");
//        translationBuilder.add("item.talismans.thunder_talisman.tooltip", "§7Throw to summon lightning");
//
//        translationBuilder.add(ModItems.BETTER_LUCK_TALISMAN, "Better Luck Talisman");
//        translationBuilder.add(ModItems.BETTER_STRENGTH_TALISMAN, "Better Strength Talisman");
//        translationBuilder.add(ModItems.WONDERFUL_STRENGTH_TALISMAN, "Wonderful Strength Talisman");
//        translationBuilder.add(ModItems.INSTANT_HEALTH_TALISMAN, "Instant Health Talisman");
//        translationBuilder.add(ModItems.REGENERATION_TALISMAN, "Regeneration Talisman");
    }
}
