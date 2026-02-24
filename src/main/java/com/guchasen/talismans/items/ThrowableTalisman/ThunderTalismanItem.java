package com.guchasen.talismans.items.ThrowableTalisman;

import com.guchasen.talismans.entities.ThunderTalismanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThunderTalismanItem extends Item {
    public ThunderTalismanItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS,
                0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            ThunderTalismanEntity talismanEntity = new ThunderTalismanEntity(world, user);
            talismanEntity.setItem(itemStack);
            talismanEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(talismanEntity);
        }

        //user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}