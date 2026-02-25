package com.guchasen.talismans.items;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    // 创建自定义物品栏
    public static final ItemGroup TALISMANS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Talismans.MOD_ID, "talismans"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.talismans")) // 物品栏名称
                    .icon(() -> new ItemStack(ModItems.THUNDER_TALISMAN)) // 物品栏图标
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.TALISMAN_CRAFTING_TABLE);
                        entries.add(ModItems.TALISMAN_PEN);
                        entries.add(ModItems.TALISMAN_PAPER);
                        entries.add(ModItems.THUNDER_TALISMAN);
                        entries.add(ModItems.LUCK_TALISMAN);
                        entries.add(ModItems.FIRE_RESISTANCE_TALISMAN);
                        entries.add(ModItems.WATER_BREATHING_TALISMAN);
                        entries.add(ModItems.NIGHT_VISION_TALISMAN);
                        entries.add(ModItems.RESISTANCE_TALISMAN);
                        entries.add(ModItems.SPEED_TALISMAN);
                        entries.add(ModItems.JUMP_TALISMAN);
                        entries.add(ModItems.STRENGTH_TALISMAN);
                        entries.add(ModItems.INVISIBILITY_TALISMAN);
                        entries.add(ModItems.REGENERATION_TALISMAN);
                        entries.add(ModItems.INSTANT_HEALTH_TALISMAN);
                        entries.add(ModItems.BETTER_LUCK_TALISMAN);
                        entries.add(ModItems.BETTER_STRENGTH_TALISMAN);
                        entries.add(ModItems.WONDERFUL_STRENGTH_TALISMAN);
                    })
                    .build());

    // 注册物品栏的方法
    public static void registerItemGroups() {
        Talismans.LOGGER.info("Registering Item Groups for " + Talismans.MOD_ID);
    }
}
