package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.registry.RavenBrewsLibRegistryKeys;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import com.thewandererraven.ravenbrewslib.registry.RegistryProvider;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class BrewEffectRegistry {
    public static final RegistryProvider<BrewEffect> BREW_EFFECT_CORES = RegistryProvider.get(RavenBrewsLibRegistryKeys.BREW_EFFECT_CORES, Constants.MOD_ID, BrewEffect.class);

    public static final String _heal_id = "effect.heal";
    public static final RegistryObject<BrewEffect> HEAL = BREW_EFFECT_CORES.register(
            _heal_id,
            () -> BrewEffect.instant(context -> context.entity().heal(context.effectMainValueAsInt()))
    );
    public static final String _hurt_id = "effect.hurt";
    public static final RegistryObject<BrewEffect> HURT = BREW_EFFECT_CORES.register(
            _hurt_id,
            () -> BrewEffect.instant(context -> context.entity().hurt(context.entity().damageSources().generic(), context.effectMainValueAsFloat()))
    );
    public static final String _absorption_id = "effect.absorption";
    public static final RegistryObject<BrewEffect> ABSORPTION = BREW_EFFECT_CORES.register(
            _absorption_id,
            () -> BrewEffect.attributeModifier("max_absorption", AttributeModifier.Operation.ADD_VALUE, context -> {
                context.entity().setAbsorptionAmount(context.entity().getAbsorptionAmount() + context.effectMainValueAsFloat());
            })
    );
    public static final String _speed_id = "effect.speed";
    public static final RegistryObject<BrewEffect> SPEED = BREW_EFFECT_CORES.register(
            _speed_id,
            () -> BrewEffect.attributeModifier("movement_speed")
    );
    // YES, it's basically the same, I just want the different id. I might add more functionality later so the difference is actually different
    public static final String _slowness_id = "effect.slowness";
    public static final RegistryObject<BrewEffect> SLOWNESS = BREW_EFFECT_CORES.register(
            _slowness_id,
            () -> BrewEffect.attributeModifier("movement_speed")
    );
    public static final String _attack_speed_id = "effect.attack_speed";
    public static final RegistryObject<BrewEffect> ATTACK_SPEED = BREW_EFFECT_CORES.register(
            _attack_speed_id,
            () -> BrewEffect.attributeModifier("attack_speed")
    );
    public static final String _haste_id = "effect.haste";
    public static final RegistryObject<BrewEffect> HASTE = BREW_EFFECT_CORES.register(
            _haste_id,
            () -> BrewEffect.attributeModifier("block_break_speed")
    );
    public static final String _strong_legs_id = "effect.strong_legs";
    public static final RegistryObject<BrewEffect> STRONG_LEGS = BREW_EFFECT_CORES.register(
            _strong_legs_id,
            () -> BrewEffect.attributeModifier("fall_damage_multiplier")
    );
    public static final String _weak_legs_id = "effect.weak_legs";
    public static final RegistryObject<BrewEffect> WEAK_LEGS = BREW_EFFECT_CORES.register(
            _weak_legs_id,
            () -> BrewEffect.attributeModifier("fall_damage_multiplier")
    );

    public static void init() {

    }
}
