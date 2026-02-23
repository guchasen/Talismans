package com.guchasen.talismans.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class StrengthTalisman extends Item {

    // 效果持续时间（以刻为单位，20刻 = 1秒）
    private static final int EFFECT_DURATION = 600;
    // 力量效果等级（0 = 力量 I，1 = 力量 II）
    private static final int EFFECT_AMPLIFIER = 1;

    public StrengthTalisman(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // 只在服务端执行效果逻辑
        if (!world.isClient) {
            // 添加力量 II 效果
            user.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.STRENGTH,
                            EFFECT_DURATION,
                            EFFECT_AMPLIFIER
                    )
            );

            // 播放使用成功的音效
            world.playSound(
                    null,
                    user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_PLAYER_LEVELUP,
                    SoundCategory.PLAYERS,
                    1.0F, 1.0F
            );

            // 消耗一个物品（数量减一）
            itemStack.decrement(1);

            // 增加使用统计（可选）
            //user.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        // 返回成功结果，并播放手臂动画
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        // 设置使用动作（这里使用“吃”的动作，看起来像在激活护符）
        return UseAction.EAT;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        // 设置使用时间（32刻 = 1.6秒）
        return 32;
    }
}