package com.guchasen.talismans.items.BuffTalismans;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseBuffTalisman extends Item {
    public BaseBuffTalisman(Settings settings) {
        super(settings);
    }
    public BaseBuffTalisman(Settings settings, StatusEffect effect, int effectDuration, int effectAmplifier) {
        super(settings);
        this.effectDuration = effectDuration;
        this.effectAmplifier = effectAmplifier;
        this.effect = effect;
    }
    protected StatusEffect effect;
    protected int effectDuration;
    protected int effectAmplifier;
    protected static final int USE_TIME = 30;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            user.addStatusEffect(
                    new StatusEffectInstance(this.effect, this.effectDuration, this.effectAmplifier)
            );
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
            stack.decrement(1);
        }
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {return UseAction.BOW;}

    @Override
    public int getMaxUseTime(ItemStack stack) {return USE_TIME;}

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.talismans.buff_talisman.tooltip"));
        tooltip.add(Text.translatable("item.talismans.buff_talisman.tooltip.effect",
                Text.translatable(this.effect.getTranslationKey())
                )
        );
        tooltip.add(Text.translatable("item.talismans.buff_talisman.tooltip.amplifier",
                Text.literal(String.valueOf(this.effectAmplifier + 1))
                )
        );
        tooltip.add(Text.translatable("item.talismans.buff_talisman.tooltip.duration",
                Text.literal(String.valueOf(this.effectDuration / 20))
                )
        );
    }

}