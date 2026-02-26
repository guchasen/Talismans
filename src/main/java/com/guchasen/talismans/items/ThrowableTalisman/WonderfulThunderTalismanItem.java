package com.guchasen.talismans.items.ThrowableTalisman;

import com.guchasen.talismans.entity.entities.ThrownTalismanEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class WonderfulThunderTalismanItem extends BaseThrowableTalisman {
    public WonderfulThunderTalismanItem(Settings settings) {
        super(settings);
    }

    @Override
    protected int getUseDuration() {
        return 24; // 1.2 seconds
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS,
                    0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }

            ThrownTalismanEntity talismanEntity = new ThrownTalismanEntity(world, player, ThrownTalismanEntity.TYPE_WONDERFUL);
            talismanEntity.setItem(stack);
            talismanEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(talismanEntity);
        }
        return stack;
    }
}
