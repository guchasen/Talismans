package com.guchasen.talismans.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TalismanPenItem extends Item {
    public TalismanPenItem(Settings settings) {
        super(settings.maxDamage(256)); // 符笔有256点耐久度
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true; // 符笔有附魔光泽
    }
}