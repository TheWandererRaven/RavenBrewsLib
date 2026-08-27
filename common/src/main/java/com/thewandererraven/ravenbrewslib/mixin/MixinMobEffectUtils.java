package com.thewandererraven.ravenbrewslib.mixin;

import com.thewandererraven.ravenbrewslib.brew.effect.BrewEffectsRegistry;
import com.thewandererraven.ravenbrewslib.brew.effect.IBrewEffectManagerHolder;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectUtil.class)
public class MixinMobEffectUtils {

    @Inject(method = "hasWaterBreathing", at = @At("RETURN"), cancellable = true)
    private static void ravenBrewsLib$hasWaterBreathingEffect(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof IBrewEffectManagerHolder holder) {
            if(holder.ravenbrewslib$getBrewEffectManager() != null)
                if(holder.ravenbrewslib$getBrewEffectManager().getCurrentEffect() != null)
                    cir.setReturnValue(cir.getReturnValue() || holder.ravenbrewslib$getBrewEffectManager().getCurrentEffect().effectBehaviour == BrewEffectsRegistry.WATER_BREATHING.get());
        }
    }

}
