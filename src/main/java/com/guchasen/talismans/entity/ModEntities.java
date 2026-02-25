package com.guchasen.talismans.entity;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.entity.entities.ThunderTalismanEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<ThunderTalismanEntity> THUNDER_TALISMAN_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Talismans.MOD_ID, "thunder_talisman"),
            FabricEntityTypeBuilder.<ThunderTalismanEntity>create(SpawnGroup.MISC, ThunderTalismanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4)
                    .trackedUpdateRate(10)
                    .build()
    );

    public static void registerModEntities() {
        Talismans.LOGGER.info("Registering Mod Entities for " + Talismans.MOD_ID);
    }
}