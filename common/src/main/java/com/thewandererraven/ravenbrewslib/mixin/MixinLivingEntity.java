package com.thewandererraven.ravenbrewslib.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.thewandererraven.ravenbrewslib.brew.effect.IBrewEffectManagerHolder;
import com.thewandererraven.ravenbrewslib.brew.effect.IBrewEffectsManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

//    @ModifyExpressionValue(method = "actuallyHurt", at = @At(value = "INVOKE",
//            target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
//    ))
//    private float ravenCoffee$applyBrewDamageReduction(float amount, ServerLevel level, DamageSource damageSource) {
//        IBrewEffectsManager effManager = ravenbrewslib$getBrewEffectManager();
//        if (effManager == null)
//            return amount;
//
//        float reduction = effManager.getDamageReductionFromActiveEffects(damageSource);
//
//        return Math.max(amount - reduction, 0.0F);
//    }

    @ModifyReturnValue(method = "getDamageAfterMagicAbsorb", at = @At("RETURN"))
    private float ravenbrewslib$applyBrewMagicReduction(float damage, DamageSource damageSource, float originalDamage) {
        if (damageSource.is(DamageTypeTags.BYPASSES_EFFECTS))
            return damage;
        IBrewEffectsManager effManager = ravenbrewslib$getBrewEffectManager();
        if (effManager == null)
            return damage;
        float flatReduction = 0.0f;
        if (!damageSource.is(DamageTypeTags.BYPASSES_RESISTANCE))
            flatReduction = effManager.getDamageModifierFromActiveEffects(damageSource, false);
        float flatIncrease = effManager.getDamageModifierFromActiveEffects(damageSource, true);
        //TODO: award damage resistance stat to player
        return damage + flatIncrease - flatReduction;
    }

    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    private void ravenbrewslib$hurtServerAtStart(ServerLevel p_376221_, DamageSource p_376460_, float p_376610_, CallbackInfoReturnable<Boolean> cir) {
        IBrewEffectsManager effManager = ravenbrewslib$getBrewEffectManager();
        if(effManager != null) {
            if(effManager.isActiveEffectToBeInvulnerableFor(p_376460_)) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "triggerOnDeathMobEffects", at = @At("TAIL"))
    private void ravenbrewslib$triggerOnDeathMobEffectsClearBrewEffects(ServerLevel level, Entity.RemovalReason removalReason, CallbackInfo ci) {
        ravenbrewslib$getBrewEffectManager().clearAllData();
    }

    @Inject(method = "removeAllEffects", at = @At("HEAD"))
    private void ravenCoffee$removeAllEffects(CallbackInfoReturnable<Boolean> ret) {
        if (!((LivingEntity)(Object)this).level().isClientSide && !this.ravenbrewslib$getBrewEffectManager().isEmpty()) {
            this.ravenbrewslib$getBrewEffectManager().clearAllData();
            }
    }
}
