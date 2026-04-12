package com.thewandererraven.ravenbrewslib.brew.effect;

import net.minecraft.world.entity.LivingEntity;

public class BrewEffectInstance {
    public BrewEffectBehaviour effectCore;
    public int effectTicks;
    public int effectTicksDuration;
    public double mainValue;
    public double secondaryValue;

    public BrewEffectInstance(BrewEffectBehaviour effectCore, int effectTicksDuration, double mainValue, double secondaryValue) {
        this.effectCore = effectCore;
        this.effectTicks = 0;
        this.effectTicksDuration = effectTicksDuration;
        this.mainValue = mainValue;
        this.secondaryValue = secondaryValue;
    }

    public boolean applyPrimaryEffect(LivingEntity player)
    {
        if(this.effectCore.primaryEffect != null)
        {
            this.effectCore.primaryEffect.accept(new BrewEffectContext(player, this.mainValue, this.secondaryValue));
            return true;
        }
        return false;
    }

    public boolean applyAdditionalEffect(LivingEntity player)
    {
        if(this.effectCore.additionalEffect != null)
        {
            this.effectCore.additionalEffect.accept(new BrewEffectContext(player, this.mainValue, this.secondaryValue));
            return true;
        }
        return false;
    }
}
