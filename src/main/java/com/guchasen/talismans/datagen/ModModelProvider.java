package com.guchasen.talismans.datagen;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//        itemModelGenerator.register(ModItems.LUCK_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.STRENGTH_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.JUMP_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.INVISIBILITY_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.RESISTANCE_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.SPEED_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.FIRE_RESISTANCE_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.WATER_BREATHING_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.NIGHT_VISION_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.THUNDER_TALISMAN,Models.GENERATED);
//
//        itemModelGenerator.register(ModItems.REGENERATION_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.INSTANT_HEALTH_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.BETTER_LUCK_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.BETTER_STRENGTH_TALISMAN,Models.GENERATED);
//        itemModelGenerator.register(ModItems.WONDERFUL_STRENGTH_TALISMAN,Models.GENERATED);


    }
}
