package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.brew.data.BrewEffectDefinition;
import com.thewandererraven.ravenbrewslib.utils.BrewEffectsUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BrewEffectInstance {
    public BrewEffectBehaviour effectBehaviour;
    public int remainingTicks;
    public int duration;
    public double mainValue;
    public double secondaryValue;

    public BrewEffectInstance(BrewEffectBehaviour effectBehaviour, int duration, double mainValue, double secondaryValue) {
        this.effectBehaviour = effectBehaviour;
        this.duration = duration;
        this.resetEffectTicks();
        this.mainValue = mainValue;
        this.secondaryValue = secondaryValue;
    }

    public BrewEffectInstance(Level level, BrewEffectDefinition effectDef)
    {
        this(BrewEffectBehaviour.EMPTY, effectDef.duration(), effectDef.mainValue(), effectDef.secondaryValue());
        BrewEffectBehaviour behaviour = BrewEffectsUtils.findEffectBehaviour(level, effectDef.id());
        if(behaviour != null) {
            this.effectBehaviour = behaviour;
        } else
            Constants.LOG.error("Unable to find brew effect for: {}", effectDef.id().toString());
    }

    public boolean applyPrimaryEffect(LivingEntity player)
    {
        if(this.effectBehaviour.primaryEffect != null)
        {
            this.effectBehaviour.primaryEffect.accept(new BrewEffectContext(player, this.mainValue, this.secondaryValue));
            return true;
        }
        return false;
    }

    public boolean applyAdditionalEffect(LivingEntity player)
    {
        if(this.effectBehaviour.additionalEffect != null)
        {
            this.effectBehaviour.additionalEffect.accept(new BrewEffectContext(player, this.mainValue, this.secondaryValue));
            return true;
        }
        return false;
    }

    public void resetEffectTicks()
    {
        this.remainingTicks = this.duration;
    }

    public boolean isEffectStarting() {
        return this.remainingTicks == this.duration;
    }

    public boolean isEffectEnding() {
        return this.remainingTicks <= 0;
    }
}
