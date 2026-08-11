package com.thewandererraven.ravenbrewslib.brew.effect;


import com.thewandererraven.ravenbrewslib.brew.data.BrewEffectDefinition;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public interface IBrewEffectsManager {
    List<BrewEffectDefinition> getEffectsStack();
    BrewEffectInstance getCurrentEffect();
    int getTotalRemainingTicks();

    public boolean isEmpty();
    void add(List<BrewEffectDefinition> brewData);
    void tick();
    public void clearEffects();
    public void clearAll();
    public void sendEffectIconsToClient();
    public void sendDurationsToClient();
    public void sendCaffeineToClient();
    public void sendAllInfoToClient();
    public CompoundTag serializeNBT();
    public void deserializeNBT(CompoundTag tag);

    default boolean isCurrentEffectToBeInvulnerableFor(DamageSource damageSource) {
        if(getCurrentEffect() != null)
            if(getCurrentEffect().effectBehaviour instanceof HurtModifierBrewEffectBehaviour hurtModEffBehaviour) {
                boolean idsamsource = damageSource.is(hurtModEffBehaviour.damageTag);
                boolean isInvulnreable = hurtModEffBehaviour.isInvulnerability;
                return damageSource.is(hurtModEffBehaviour.damageTag) && hurtModEffBehaviour.isInvulnerability;
            }
        return false;
    }

    default float getDamageReductionForCurrentEffect(DamageSource damageSource) {
        if(getCurrentEffect() != null)
            if(getCurrentEffect().effectBehaviour instanceof HurtModifierBrewEffectBehaviour hurtModEffBehaviour) {
                if(damageSource.is(hurtModEffBehaviour.damageTag)) {
                    return (float) getCurrentEffect().mainValue;
                }
            }
        return 0.0f;
    }
}