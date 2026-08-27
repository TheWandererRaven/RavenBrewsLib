package com.thewandererraven.ravenbrewslib.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.thewandererraven.ravenbrewslib.brew.effect.IBrewEffectManagerHolder;
import com.thewandererraven.ravenbrewslib.brew.effect.IBrewEffectsManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity implements IBrewEffectManagerHolder {
    @Unique
    IBrewEffectsManager ravenBrewsLib$brewEffectsManager = null;

    @Override
    public IBrewEffectsManager ravenbrewslib$getBrewEffectManager() {
        return ravenBrewsLib$brewEffectsManager;
    }

    @Override
    public void ravenbrewslib$setBrewEffectManager(IBrewEffectsManager manager) {
        ravenBrewsLib$brewEffectsManager = manager;
    }

    @ModifyExpressionValue(method = "actuallyHurt", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
    ))
    private float ravenCoffee$applyBrewDamageReduction(float amount, ServerLevel level, DamageSource damageSource) {
        IBrewEffectsManager effManager = ravenbrewslib$getBrewEffectManager();
        if (effManager == null)
            return amount;

        float reduction = effManager.getDamageReductionForCurrentEffect(damageSource);

        return Math.max(amount - reduction, 0.0F);
    }

    @ModifyReturnValue(method = "getDamageAfterMagicAbsorb", at = @At("RETURN"))
    private float ravenCoffee$applyBrewMagicReduction(float damage, DamageSource damageSource, float originalDamage) {
        if (damageSource.is(DamageTypeTags.BYPASSES_EFFECTS) || damageSource.is(DamageTypeTags.BYPASSES_RESISTANCE))
            return damage;

        IBrewEffectsManager effManager = ravenbrewslib$getBrewEffectManager();
        if (effManager == null) {
            return damage;
        }
        float flatReduction = effManager.getDamageReductionForCurrentEffect(damageSource);

        //TODO: award damage resistance stat to player
        return Math.max(0.0F, damage - flatReduction);
    }

    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    private void ravenCoffee$hurtServerAtStart(ServerLevel p_376221_, DamageSource p_376460_, float p_376610_, CallbackInfoReturnable<Boolean> cir) {
        IBrewEffectsManager effManager = ravenbrewslib$getBrewEffectManager();
        if(effManager != null) {
            if(effManager.isCurrentEffectToBeInvulnerableFor(p_376460_)) {
                cir.setReturnValue(false);
            }
        }
    }
}
