package com.thewandererraven.ravenbrewslib.brew.effect;


import com.thewandererraven.ravenbrewslib.brew.data.BrewEffectDefinition;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public interface IBrewEffectsManager {
    List<BrewEffectDefinition> getInactiveEffects();
    void addInactiveEffects(List<BrewEffectDefinition> effDefs);
    void removeInactiveEffects(List<ResourceLocation> effIds);

    List<BrewEffectInstance> getActiveEffects();
    void addActiveEffects(List<BrewEffectInstance> effInstances);
    void addActiveEffectsFromDefinitions(List<BrewEffectDefinition> effDefs);
    void removeActiveEffects(List<ResourceLocation> effIds);

    void tick();

    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag tag);

    default void addActiveEffects(BrewEffectInstance effInstance) {
        this.addActiveEffects(List.of(effInstance));
    }

    default boolean hasInactiveEffects() {
        return !getInactiveEffects().isEmpty();
    }

    default boolean hasActiveEffects() {
        return !getActiveEffects().isEmpty();
    }

    default boolean isEmpty() {
        return !hasInactiveEffects() && !hasActiveEffects();
    }

    default BrewEffectDefinition getInactiveEffect(int index) {
        if(hasInactiveEffects())
            return getInactiveEffects().get(index);
        return null;
    }

    default BrewEffectInstance getActiveEffect(int index) {
        if(hasActiveEffects())
            return getActiveEffects().get(index);
        return null;
    }

    default boolean hasActiveEffect(ResourceLocation id) {
        return getActiveEffects().stream().anyMatch(eff -> eff.effectBehaviour.id.equals(id));
    }

    default boolean hasInactiveEffect(ResourceLocation id) {
        return getInactiveEffects().stream().anyMatch(eff -> eff.id().equals(id));
    }

    default void clearInactiveEffects() {
        getInactiveEffects().clear();
    }

    default void clearActiveEffects() {
        getActiveEffects().clear();
    }

    default void clearAllData() {
        this.clearInactiveEffects();
        this.clearActiveEffects();
    }

    default boolean isActiveEffectToBeInvulnerableFor(DamageSource damageSource) {
        if(!getActiveEffects().isEmpty())
            for(BrewEffectInstance effInstance: getActiveEffects())
                if(effInstance.effectBehaviour instanceof HurtModifierBrewEffectBehaviour hurtModEffBehaviour)
                    return damageSource.is(hurtModEffBehaviour.damageTag) && hurtModEffBehaviour.isInvulnerability;
        return false;
    }

    default float getDamageReductionFromActiveEffects(DamageSource damageSource) {
        if(!getActiveEffects().isEmpty())
            for(BrewEffectInstance effInstance: getActiveEffects())
                if(effInstance.effectBehaviour instanceof HurtModifierBrewEffectBehaviour hurtModEffBehaviour)
                    if(damageSource.is(hurtModEffBehaviour.damageTag))
                        return (float) effInstance.mainValue;
        return 0.0f;
    }
}