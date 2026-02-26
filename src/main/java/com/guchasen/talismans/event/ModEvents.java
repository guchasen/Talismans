package com.guchasen.talismans.event;

import com.guchasen.talismans.Talismans;
import com.guchasen.talismans.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;

public class ModEvents {

    private static final double DROP_CHANCE = 0.05; // 5% 概率

    public static void registerEvents() {
        Talismans.LOGGER.info("Registering Mod Events for " + Talismans.MOD_ID);

        // 注册实体被击杀事件
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
            // 检查被击杀的实体是否是流浪商人
            if (killedEntity instanceof WanderingTraderEntity) {
                handleWanderingTraderDeath(world, (WanderingTraderEntity) killedEntity,ModItems.INVISIBILITY_TALISMAN);
            }
        });
    }

    private static void handleWanderingTraderDeath(ServerWorld world, WanderingTraderEntity trader, Item item) {
        // 生成随机数判断是否掉落
        if (world.random.nextDouble() < DROP_CHANCE) {
            // 创建物品
            ItemStack itemStack = new ItemStack(item, 1);

            // 在商人死亡位置生成物品实体
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    trader.getX(),
                    trader.getY(),
                    trader.getZ(),
                    itemStack
            );

            // 设置一些随机运动，让掉落物更自然
            itemEntity.setVelocity(
                    world.random.nextGaussian() * 0.1,
                    world.random.nextGaussian() * 0.1 + 0.2,
                    world.random.nextGaussian() * 0.1
            );

            // 设置拾取延迟（20 ticks = 1秒）
            itemEntity.setPickupDelay(20);

            // 生成到世界中
            world.spawnEntity(itemEntity);

        }
    }
}