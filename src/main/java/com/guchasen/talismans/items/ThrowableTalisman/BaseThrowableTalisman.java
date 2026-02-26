package com.guchasen.talismans.items.ThrowableTalisman;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BaseThrowableTalisman extends Item {
    public BaseThrowableTalisman(Settings settings) {
        super(settings);
    }
    
    // Default use duration, can be overridden
    protected int getUseDuration() {
        return 30; // Default 1.5s (30 ticks), user requested specific times for each item
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return getUseDuration();
    }
}
