package com.guchasen.talismans.block.entity;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<TalismanCraftingTableBlockEntity> TALISMAN_CRAFTING_TABLE_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        TALISMAN_CRAFTING_TABLE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Talismans.MOD_ID, "talisman_crafting_table"),
                FabricBlockEntityTypeBuilder.create(TalismanCraftingTableBlockEntity::new,
                        ModBlocks.TALISMAN_CRAFTING_TABLE).build());
    }
}
