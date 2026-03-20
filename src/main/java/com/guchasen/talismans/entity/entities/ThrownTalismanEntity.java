package com.guchasen.talismans.entity.entities;

import com.guchasen.talismans.entity.ModEntities;
import com.guchasen.talismans.items.ModItems;
import com.guchasen.talismans.uitl.TUtils;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static com.guchasen.talismans.uitl.TUtils.*;


public class ThrownTalismanEntity extends ThrownItemEntity implements FlyingItemEntity {
    // Behavior types
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_BETTER = 1;
    public static final int TYPE_WONDERFUL = 2;
    public static final int TYPE_EXPLOSIVE = 3;
    public static final int TYPE_GREATER_EXPLOSIVE = 4;
    public static final int TYPE_FIRE = 5;

    private int talismanType = TYPE_NORMAL;
    private int lifeTicksAfterHit = 0;
    private boolean hasHit = false;
    private Vec3d hitPos;

    public ThrownTalismanEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownTalismanEntity(World world, LivingEntity owner) {
        super(ModEntities.THROWN_TALISMAN_ENTITY_RENDERER, owner, world);
    }

    public ThrownTalismanEntity(World world, LivingEntity owner, int type) {
        super(ModEntities.THROWN_TALISMAN_ENTITY_RENDERER, owner, world);
        this.talismanType = type;
    }

    public ThrownTalismanEntity(World world, double x, double y, double z) {
        super(ModEntities.THROWN_TALISMAN_ENTITY_RENDERER, x, y, z, world);
    }

    public void setTalismanType(int type) {
        this.talismanType = type;
    }

    @Override
    protected Item getDefaultItem() {
        // Return corresponding item based on type for rendering if needed, 
        // or just default to ThunderTalisman if we don't care about the flying texture being exact
        // The render usually takes the item stack set on the entity.
        return ModItems.THUNDER_TALISMAN;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("TalismanType", this.talismanType);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.talismanType = nbt.getInt("TalismanType");
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        triggerEffect(entityHitResult.getPos());
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        triggerEffect(blockHitResult.getPos());
    }

    public void triggerEffect(Vec3d pos) {
        if (!this.getWorld().isClient) {
            World world = this.getWorld();
            this.hitPos = pos;
            
            switch (talismanType) {
                case TYPE_NORMAL:
                    spawnLightning(world, pos);
                    this.discard();
                    break;
                case TYPE_BETTER:
                    spawnLightning(world, pos);
                    this.hasHit = true;
                    this.setNoGravity(true);
                    this.setVelocity(0,0,0);
                    // Don't discard, wait for tick 10
                    break;
                case TYPE_WONDERFUL:
                    // Delay 0.3s (6 ticks)
                    spawnRandomLightning(this.getWorld(), hitPos, 5.0);
                    this.hasHit = true;
                    this.setNoGravity(true);
                    this.setVelocity(0,0,0);
                    // Don't discard, wait for tick 6
                    break;
                case TYPE_EXPLOSIVE:
                    world.createExplosion(this, pos.x, pos.y, pos.z, 1.5f, true, World.ExplosionSourceType.NONE);
                    this.discard();
                    break;
                case TYPE_GREATER_EXPLOSIVE:
                    world.createExplosion(this, pos.x, pos.y, pos.z, 4.0f, false, World.ExplosionSourceType.TNT);
                    this.discard();
                    break;
                case TYPE_FIRE:
                    createFireCircle(world, pos, 4);
                    this.discard();
                    break;
            }
        }
    }



    @Override
    public void tick() {

        super.tick();
        if (hasHit && !this.getWorld().isClient) {
            lifeTicksAfterHit++;
            if (talismanType == TYPE_BETTER) {
                if (lifeTicksAfterHit == 5) {
                     spawnLightning(this.getWorld(), hitPos);
                     spawnLightning(this.getWorld(), hitPos);
                }
                else if(lifeTicksAfterHit == 7){
                    spawnLightning(this.getWorld(), hitPos);
                    this.discard();
                }
            }
            else if (talismanType == TYPE_WONDERFUL) {
                if (lifeTicksAfterHit == 4) {
                    spawnRandomLightning(this.getWorld(), hitPos, 7.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 5.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 3.0);
                    //this.discard();
                }
                else if(lifeTicksAfterHit == 10){
                    spawnRandomLightning(this.getWorld(), hitPos, 5.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 8.0);
                }
                else if(lifeTicksAfterHit == 20){
                    spawnRandomLightning(this.getWorld(), hitPos, 6.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 3.0);
                    //this.discard();
                }
                else if(lifeTicksAfterHit == 30){
                    spawnRandomLightning(this.getWorld(), hitPos, 5.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 7.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 9.0);
                    //this.discard();
                }
                else if(lifeTicksAfterHit == 40){
                    spawnRandomLightning(this.getWorld(), hitPos, 5.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 7.0);
                    spawnRandomLightning(this.getWorld(), hitPos, 10.0);
                    this.discard();
                }
            } else {
                this.discard();
            }
        }
    }
    
    private void scheduleTask(Runnable task, int delay) {
    }
}
