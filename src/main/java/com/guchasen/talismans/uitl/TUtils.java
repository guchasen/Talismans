package com.guchasen.talismans.uitl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class TUtils {
    private TUtils(){};

    //summon lightning
    public static void spawnLightning(World world, Vec3d pos) {
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
        if (lightningEntity != null) {
            lightningEntity.refreshPositionAfterTeleport(pos.x, pos.y, pos.z);
            world.spawnEntity(lightningEntity);
        }
    }

    //summon lightning in a circle randomly
    public static void spawnRandomLightning(World world, Vec3d center, double radius) {
        double x = center.x + (world.random.nextDouble() - 0.5) * 2 * radius;
        double z = center.z + (world.random.nextDouble() - 0.5) * 2 * radius;
        spawnLightning(world, new Vec3d(x, center.y, z));
    }

    //create explosion
    public static void createExplosion(Entity entity,World world, Vec3d pos, float  power, boolean fire, World.ExplosionSourceType sourceType){
        world.createExplosion(entity, pos.x, pos.y, pos.z, 1.5f, true, net.minecraft.world.World.ExplosionSourceType.NONE);
    }

    //create fire circle
    public static void createFireCircle(World world, Vec3d center, int radius) {
        BlockPos centerPos = new BlockPos((int)center.x, (int)center.y, (int)center.z);
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x*x + z*z <= radius*radius) {
                    BlockPos target = centerPos.add(x, 0, z);
                    if (world.getBlockState(target).isAir() && !world.getBlockState(target.down()).isAir()) {
                        world.setBlockState(target, net.minecraft.block.Blocks.FIRE.getDefaultState());
                    }
                }
            }
        }
    }
}
