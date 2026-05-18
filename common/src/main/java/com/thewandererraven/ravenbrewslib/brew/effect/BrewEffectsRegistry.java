package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.registry.RavenBrewsLibRegistryKeys;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import com.thewandererraven.ravenbrewslib.registry.RegistryProvider;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class BrewEffectsRegistry {
    public static final RegistryProvider<BrewEffectBehaviour> BREW_EFFECT_BEHAVIOUR = RegistryProvider.get(RavenBrewsLibRegistryKeys.BREW_EFFECT_BEHAVIOUR, Constants.MOD_ID, BrewEffectBehaviour.class);

    public static final String _heal_id = "effect.heal";
    public static final RegistryObject<BrewEffectBehaviour> HEAL = BREW_EFFECT_BEHAVIOUR.register(
            _heal_id,
            () -> BrewEffectBehaviour.instant(context -> context.entity().heal(context.effectMainValueAsInt()))
    );
    public static final String _hurt_id = "effect.hurt";
    public static final RegistryObject<BrewEffectBehaviour> HURT = BREW_EFFECT_BEHAVIOUR.register(
            _hurt_id,
            () -> BrewEffectBehaviour.instant(context -> context.entity().hurt(context.entity().damageSources().generic(), context.effectMainValueAsFloat()))
    );
    public static final String _absorption_id = "effect.absorption";
    public static final RegistryObject<BrewEffectBehaviour> ABSORPTION = BREW_EFFECT_BEHAVIOUR.register(
            _absorption_id,
            () -> BrewEffectBehaviour.attributeModifier("max_absorption", AttributeModifier.Operation.ADD_VALUE, context -> {
                context.entity().setAbsorptionAmount(context.entity().getAbsorptionAmount() + context.effectMainValueAsFloat());
            })
    );
    public static final String _speed_id = "effect.speed";
    public static final RegistryObject<BrewEffectBehaviour> SPEED = BREW_EFFECT_BEHAVIOUR.register(
            _speed_id,
            () -> BrewEffectBehaviour.attributeModifier("movement_speed")
    );
    // YES, it's basically the same, I just want the different id. I might add more functionality later so the difference is actually different
    public static final String _slowness_id = "effect.slowness";
    public static final RegistryObject<BrewEffectBehaviour> SLOWNESS = BREW_EFFECT_BEHAVIOUR.register(
            _slowness_id,
            () -> BrewEffectBehaviour.attributeModifier("movement_speed")
    );
    public static final String _attack_speed_id = "effect.attack_speed";
    public static final RegistryObject<BrewEffectBehaviour> ATTACK_SPEED = BREW_EFFECT_BEHAVIOUR.register(
            _attack_speed_id,
            () -> BrewEffectBehaviour.attributeModifier("attack_speed")
    );
    public static final String _haste_id = "effect.haste";
    public static final RegistryObject<BrewEffectBehaviour> HASTE = BREW_EFFECT_BEHAVIOUR.register(
            _haste_id,
            () -> BrewEffectBehaviour.attributeModifier("block_break_speed")
    );
    public static final String _strong_legs_id = "effect.strong_legs";
    public static final RegistryObject<BrewEffectBehaviour> STRONG_LEGS = BREW_EFFECT_BEHAVIOUR.register(
            _strong_legs_id,
            () -> BrewEffectBehaviour.attributeModifier("fall_damage_multiplier")
    );
    public static final String _weak_legs_id = "effect.weak_legs";
    public static final RegistryObject<BrewEffectBehaviour> WEAK_LEGS = BREW_EFFECT_BEHAVIOUR.register(
            _weak_legs_id,
            () -> BrewEffectBehaviour.attributeModifier("fall_damage_multiplier")
    );

    public static void init() {

    }
}
