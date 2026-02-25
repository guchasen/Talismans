package com.guchasen.talismans.items.ThrowableTalisman;

import com.guchasen.talismans.entity.entities.ThunderTalismanEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThunderTalismanItem extends BaseThrowableTalisman {

    public ThunderTalismanItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            // 播放投掷音效
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS,
                    0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            // 消耗物品
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }

            ThunderTalismanEntity talismanEntity = new ThunderTalismanEntity(world, player);
            talismanEntity.setItem(stack);
            talismanEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(talismanEntity);


        }
        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.talismans.thunder_talisman.tooltip"));
    }
}