package com.guchasen.talismans.items.BuffTalismans;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;

public class InstantHealthTalisman extends BaseBuffTalisman{
    public InstantHealthTalisman(Settings settings) {
        super(settings);
    }

    public InstantHealthTalisman(Settings settings, StatusEffect effect, int effectDuration, int effectAmplifier) {
        super(settings, effect, effectDuration, effectAmplifier);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 10;
    }
}
