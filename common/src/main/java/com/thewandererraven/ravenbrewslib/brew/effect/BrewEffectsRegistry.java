package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.registry.RavenBrewsLibRegistryKeys;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import com.thewandererraven.ravenbrewslib.registry.RegistryProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.alchemy.Potions;

import java.util.List;

public class BrewEffectsRegistry {
    public static final RegistryProvider<BrewEffectBehaviour> BREW_EFFECT_BEHAVIOURS = RegistryProvider.get(RavenBrewsLibRegistryKeys.BREW_EFFECT_BEHAVIOUR, Constants.MOD_ID, BrewEffectBehaviour.class);

    // HEAL ========== Simple, instant heal
    public static final String _heal_id = "effect.heal";
    public static final RegistryObject<BrewEffectBehaviour> HEAL = BREW_EFFECT_BEHAVIOURS.register(
            _heal_id,
            () -> BrewEffectBehaviour.instant(context -> context.entity().heal(context.effectMainValueAsInt()))
    );

    // HURT ========== Simple, instant hurt
    public static final String _hurt_id = "effect.hurt";
    public static final RegistryObject<BrewEffectBehaviour> HURT = BREW_EFFECT_BEHAVIOURS.register(
            _hurt_id,
            () -> BrewEffectBehaviour.instant(context -> context.entity().hurt(context.entity().damageSources().generic(), context.effectMainValueAsFloat()))
    );

    // ABSORPTION ========== Add absorption attribute and add absorption points
    public static final String _absorption_id = "effect.absorption";
    public static final RegistryObject<BrewEffectBehaviour> ABSORPTION = BREW_EFFECT_BEHAVIOURS.register(
            _absorption_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("max_absorption", AttributeModifier.Operation.ADD_VALUE), context -> {
                context.entity().setAbsorptionAmount(context.entity().getAbsorptionAmount() + context.effectMainValueAsFloat());
            })
    );

    // HEALTH BOOST ========== Increase health temporarily
    public static final String _health_boost_id = "effect.health_boost";
    public static final RegistryObject<BrewEffectBehaviour> HEALTH_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _health_boost_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("max_health", AttributeModifier.Operation.ADD_VALUE), context -> {
                context.entity().heal(context.effectMainValueAsFloat());
            })
    );

    // SPEED ========== Increase movement speed attribute
    public static final String _speed_id = "effect.speed";
    public static final RegistryObject<BrewEffectBehaviour> SPEED = BREW_EFFECT_BEHAVIOURS.register(
            _speed_id,
            () -> BrewEffectBehaviour.attributeModifier("movement_speed")
    );

    // SLOWNESS ========== Decrease movement speed attribute
    // YES, it's basically the same as the speed effect, I just want the different id. I might add more functionality later so the difference is actually different
    public static final String _slowness_id = "effect.slowness";
    public static final RegistryObject<BrewEffectBehaviour> SLOWNESS = BREW_EFFECT_BEHAVIOURS.register(
            _slowness_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("movement_speed", -1))
    );

    // QUICK ATTACK ========== Increase attack speed attribute
    public static final String _quick_attack_id = "effect.quick_attack";
    public static final RegistryObject<BrewEffectBehaviour> QUICK_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _quick_attack_id,
            () -> BrewEffectBehaviour.attributeModifier("attack_speed")
    );

    // SLOW ATTACK ========== Decrease attack_speed attribute
    public static final String _slow_attack_id = "effect.slow_attack";
    public static final RegistryObject<BrewEffectBehaviour> SLOW_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _slow_attack_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("attack_speed", -1))
    );

    // STRONG ATTACK ========== Increase attack_damage attribute
    public static final String _strong_attack_id = "effect.strong_attack";
    public static final RegistryObject<BrewEffectBehaviour> STRONG_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _strong_attack_id,
            () -> BrewEffectBehaviour.attributeModifier("attack_damage")
    );

    // WEAK ATTACK ========== Decrease attack_damage attribute
    public static final String _weak_attack_id = "effect.weak_attack";
    public static final RegistryObject<BrewEffectBehaviour> WEAK_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _weak_attack_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("attack_damage", -1))
    );

    // MINER'S ELBOW ========== Increase block break speed attribute
    public static final String _block_breaking_speed_id = "effect.block_breaking_speed";
    public static final RegistryObject<BrewEffectBehaviour> BLOCK_BREAKING_SPEED = BREW_EFFECT_BEHAVIOURS.register(
            _block_breaking_speed_id,
            () -> BrewEffectBehaviour.attributeModifier("block_break_speed")
    );

    // OVERWORKED ========== Decrease block break speed attribute
    public static final String _block_breaking_slowness_id = "effect.block_breaking_slowness";
    public static final RegistryObject<BrewEffectBehaviour> BLOCK_BREAKING_SLOWNESS = BREW_EFFECT_BEHAVIOURS.register(
            _block_breaking_slowness_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("block_break_speed", -1))
    );

    // STRONG LEGS ========== Decrease fall damage multiplier attribute
    public static final String _decreased_fall_damage_id = "effect.decreased_fall_damage";
    public static final RegistryObject<BrewEffectBehaviour> DECREASED_FALL_DAMAGE = BREW_EFFECT_BEHAVIOURS.register(
            _decreased_fall_damage_id,
            () -> BrewEffectBehaviour.attributeModifier("fall_damage_multiplier")
    );

    // WEAK LEGS ========== Increase fall damage multiplier attribute
    public static final String _increased_fall_damage_id = "effect.increased_fall_damage";
    public static final RegistryObject<BrewEffectBehaviour> INCREASED_FALL_DAMAGE = BREW_EFFECT_BEHAVIOURS.register(
            _increased_fall_damage_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("fall_damage_multiplier", -1))
    );

    // JUMP BOOST ========== Increase jump strength attr while also creasing the safe fall distance
    public static final String _jump_boost_id = "effect.jump_boost";
    public static final RegistryObject<BrewEffectBehaviour> JUMP_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _jump_boost_id,
            () -> BrewEffectBehaviour.attributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("jump_strength"),
                    //new AttributeModifierBrewEffectBehaviour.AttributeTemplate("fall_damage_multiplier", -1),
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("safe_fall_distance")
            ))
    );

    // LEVITATION ========== Decrease gravity attr
    public static final String _levitation_id = "effect.levitation";
    public static final RegistryObject<BrewEffectBehaviour> LEVITATION = BREW_EFFECT_BEHAVIOURS.register(
            _levitation_id,
            () -> BrewEffectBehaviour.attributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("gravity", -0.01, AttributeModifier.Operation.ADD_VALUE),
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("safe_fall_distance", 1.0, AttributeModifier.Operation.ADD_VALUE)
            ))
    );

    // EXTRA PULL ========== Increase gravity attr
    public static final String _extra_pull_id = "effect.extra_pull";
    public static final RegistryObject<BrewEffectBehaviour> EXTRA_PULL = BREW_EFFECT_BEHAVIOURS.register(
            _extra_pull_id,
            () -> BrewEffectBehaviour.attributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("gravity", 0.01, AttributeModifier.Operation.ADD_VALUE),
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("safe_fall_distance", -0.2, AttributeModifier.Operation.ADD_VALUE)
            ))
    );

    // INVISIBILITY ========== You disappear lol
    public static final String _invisibility_id = "effect.invisibility";
    public static final RegistryObject<BrewEffectBehaviour> INVISIBILITY = BREW_EFFECT_BEHAVIOURS.register(
            _invisibility_id,
            () -> new BrewEffectBehaviour(
                    context -> {
                        context.entity().setInvisible(true);
                    },
                    context -> {
                        context.entity().setInvisible(false);
                    },
                    BrewEffectBehaviour.TickMode.START_AND_END
            )
    );

    // FIRE RESISTANCE ========== Decrease fire damage
    public static final String _fire_resistance_id = "effect.fire_resistance";
    public static final RegistryObject<BrewEffectBehaviour> FIRE_RESISTANCE = BREW_EFFECT_BEHAVIOURS.register(
            _fire_resistance_id,
            () -> BrewEffectBehaviour.hurtModifier(DamageTypeTags.IS_FIRE, false)
    );

    // FIRE IMMUNITY ========== Decrease luck attribute
    public static final String _fire_immunity_id = "effect.fire_immunity";
    public static final RegistryObject<BrewEffectBehaviour> FIRE_IMMUNITY = BREW_EFFECT_BEHAVIOURS.register(
            _fire_immunity_id,
            () -> BrewEffectBehaviour.hurtModifier(DamageTypeTags.IS_FIRE, true)
    );

    // LUCKY ========== Increase luck attribute
    public static final String _lucky_id = "effect.lucky";
    public static final RegistryObject<BrewEffectBehaviour> LUCKY = BREW_EFFECT_BEHAVIOURS.register(
            _lucky_id,
            () -> BrewEffectBehaviour.attributeModifier("luck")
    );

    // UNLUCKY ========== Decrease luck attribute
    public static final String _unlucky_id = "effect.unlucky";
    public static final RegistryObject<BrewEffectBehaviour> UNLUCKY = BREW_EFFECT_BEHAVIOURS.register(
            _unlucky_id,
            () -> BrewEffectBehaviour.attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("luck", -1))
    );

    public static void init() {

    }
}
