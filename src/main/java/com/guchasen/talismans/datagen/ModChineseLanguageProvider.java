package com.guchasen.talismans.datagen;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModChineseLanguageProvider extends FabricLanguageProvider {
    public ModChineseLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 物品翻译
        //translationBuilder.add(ModItems.THUNDER_TALISMAN, "雷电护符");
//        translationBuilder.add(ModItems.LUCK_TALISMAN, "奇运符");
//        translationBuilder.add(ModItems.STRENGTH_TALISMAN, "增力符");
//        translationBuilder.add(ModItems.JUMP_TALISMAN, "强跃符");
//        translationBuilder.add(ModItems.INVISIBILITY_TALISMAN, "隐形符");
//        translationBuilder.add(ModItems.RESISTANCE_TALISMAN, "抗性符");
//        translationBuilder.add(ModItems.SPEED_TALISMAN, "增速符");
//        translationBuilder.add(ModItems.FIRE_RESISTANCE_TALISMAN, "抗火符");
//        translationBuilder.add(ModItems.WATER_BREATHING_TALISMAN, "水神符");
//        translationBuilder.add(ModItems.NIGHT_VISION_TALISMAN, "夜明符");
//        translationBuilder.add(ModItems.THUNDER_TALISMAN, "落雷符");
//        translationBuilder.add("item.talismans.thunder_talisman.tooltip", "§7长按投掷以召唤闪电");
//
//        translationBuilder.add(ModItems.BETTER_LUCK_TALISMAN, "神运符");
//        translationBuilder.add(ModItems.BETTER_STRENGTH_TALISMAN, "奇力符");
//        translationBuilder.add(ModItems.WONDERFUL_STRENGTH_TALISMAN, "神力符");
//        translationBuilder.add(ModItems.REGENERATION_TALISMAN, "回春符");
//        translationBuilder.add(ModItems.INSTANT_HEALTH_TALISMAN, "续气符");
    }
}