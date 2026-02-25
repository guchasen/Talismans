package com.guchasen.talismans.entity.entities;

import com.guchasen.talismans.entity.ModEntities;
import com.guchasen.talismans.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;


public class ThunderTalismanEntity extends ThrownItemEntity implements FlyingItemEntity {
    public ThunderTalismanEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThunderTalismanEntity(World world, LivingEntity owner) {
        super(ModEntities.THUNDER_TALISMAN_ENTITY_TYPE, owner, world);
    }

    public ThunderTalismanEntity(World world, double x, double y, double z) {
        super(ModEntities.THUNDER_TALISMAN_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THUNDER_TALISMAN;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        doSomething();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        doSomething();
    }

    public void doSomething() {
        if (!this.getWorld().isClient) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
            if (lightningEntity != null) {
                lightningEntity.refreshPositionAfterTeleport(this.getX(), this.getY(), this.getZ());
                this.getWorld().spawnEntity(lightningEntity);
            }
            this.discard();
        }
    }
}