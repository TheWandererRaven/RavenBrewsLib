package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.registry.RavenBrewsLibRegistryKeys;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import com.thewandererraven.ravenbrewslib.registry.RegistryProvider;
import com.thewandererraven.ravenbrewslib.utils.BrewEffectsUtils;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.Potions;

import java.util.List;

public class BrewEffectsRegistry {
    public static final RegistryProvider<BrewEffectBehaviour> BREW_EFFECT_BEHAVIOURS = RegistryProvider.get(RavenBrewsLibRegistryKeys.BREW_EFFECT_BEHAVIOUR, Constants.MOD_ID, BrewEffectBehaviour.class);

    // EMPTY
    public static final String _empty_id = "effect.empty";
    public static final RegistryObject<BrewEffectBehaviour> EMPTY = BREW_EFFECT_BEHAVIOURS.register(
            _empty_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _empty_id)).build()
    );
    // HEAL ========== Simple, instant heal
    public static final String _heal_id = "effect.heal";
    public static final RegistryObject<BrewEffectBehaviour> HEAL = BREW_EFFECT_BEHAVIOURS.register(
            _heal_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _heal_id))
                    .withPrimaryEffect(context -> context.entity().heal(context.effectMainValueAsInt()))
                    .withTickMode(BrewEffectBehaviour.TickMode.SINGLE)
                    .build()
    );

    // HURT ========== Simple, instant hurt
    public static final String _hurt_id = "effect.hurt";
    public static final RegistryObject<BrewEffectBehaviour> HURT = BREW_EFFECT_BEHAVIOURS.register(
            _hurt_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _hurt_id))
                    .withPrimaryEffect(context -> context.entity().hurt(context.entity().damageSources().magic(), context.effectMainValueAsFloat()))
                    .withTickMode(BrewEffectBehaviour.TickMode.SINGLE)
                    .build()
    );

    // ABSORPTION ========== Add absorption attribute and add absorption points
    public static final String _absorption_id = "effect.absorption";
    public static final RegistryObject<BrewEffectBehaviour> ABSORPTION = BREW_EFFECT_BEHAVIOURS.register(
            _absorption_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _absorption_id))
                    .withPrimaryEffect(context -> context.entity().setAbsorptionAmount(context.entity().getAbsorptionAmount() + context.effectMainValueAsFloat()))
                    .buildAttributeModifier("max_absorption")
    );

    // HEALTH BOOST ========== Increase health temporarily
    public static final String _health_boost_id = "effect.health_boost";
    public static final RegistryObject<BrewEffectBehaviour> HEALTH_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _health_boost_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _health_boost_id))
                    .withPrimaryEffect(context -> context.entity().heal(context.effectMainValueAsFloat()))
                    .buildAttributeModifier("max_health")
    );

    // MOVEMENT BOOST ========== Increase movement speed attribute
    public static final String _movement_boost_id = "effect.movement_boost";
    public static final RegistryObject<BrewEffectBehaviour> MOVEMENT_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _movement_boost_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _movement_boost_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(
                            "movement_speed",
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ))
    );

    // MOVEMENT SLOW ========== Decrease movement speed attribute
    public static final String _movement_slow_id = "effect.movement_slow";
    public static final RegistryObject<BrewEffectBehaviour> MOVEMENT_SLOW = BREW_EFFECT_BEHAVIOURS.register(
            _movement_slow_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _movement_slow_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(
                            "movement_speed",
                            -1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ))
    );

    // ATTACK RUSH ========== Increase attack speed attribute
    public static final String _attack_rush_id = "effect.attack_rush";
    public static final RegistryObject<BrewEffectBehaviour> ATTACK_RUSH = BREW_EFFECT_BEHAVIOURS.register(
            _attack_rush_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _attack_rush_id))
                    .buildAttributeModifier("attack_speed")
    );

    // ATTACK DELAY |  ========== Decrease attack_speed attribute
    public static final String _attack_delay_id = "effect.attack_delay";
    public static final RegistryObject<BrewEffectBehaviour> ATTACK_DELAY = BREW_EFFECT_BEHAVIOURS.register(
            _attack_delay_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _attack_delay_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("attack_speed", -1))
    );

    // STRONG ATTACK ========== Increase attack_damage attribute
    public static final String _strong_attack_id = "effect.strong_attack";
    public static final RegistryObject<BrewEffectBehaviour> STRONG_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _strong_attack_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _strong_attack_id))
                    .buildAttributeModifier("attack_damage")
    );

    // WEAK ATTACK ========== Decrease attack_damage attribute
    public static final String _weak_attack_id = "effect.weak_attack";
    public static final RegistryObject<BrewEffectBehaviour> WEAK_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _weak_attack_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _weak_attack_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("attack_damage", -1))
    );

    // LETHARGY ========== weak attack, less mining efficiency, etc | Reduced health?
    // FRAIL ========== some overall weakness / reduced health?

    // MINING HASTE ========== Increase block break speed attribute
    public static final String _mining_haste_id = "effect.mining_haste";
    public static final RegistryObject<BrewEffectBehaviour> MINING_HASTE = BREW_EFFECT_BEHAVIOURS.register(
            _mining_haste_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _mining_haste_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(
                            "mining_efficiency",
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ))
    );

    // MINING FATIGUE ========== Decrease block break speed attribute
    public static final String _mining_fatigue_id = "effect.mining_fatigue";
    public static final RegistryObject<BrewEffectBehaviour> MINING_FATIGUE = BREW_EFFECT_BEHAVIOURS.register(
            _mining_fatigue_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _mining_fatigue_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(
                            "mining_efficiency",
                            -1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ))
    );

    // SOFT LANDING ========== Decrease fall damage multiplier attribute
    public static final String _soft_landing_id = "effect.soft_landing";
    public static final RegistryObject<BrewEffectBehaviour> SOFT_LANDING = BREW_EFFECT_BEHAVIOURS.register(
            _soft_landing_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _soft_landing_id))
                    .buildAttributeModifier("fall_damage_multiplier")
    );

    // ROUGH LANDING ========== Increase fall damage multiplier attribute
    public static final String _rough_landing_id = "effect.rough_landing";
    public static final RegistryObject<BrewEffectBehaviour> ROUGH_LANDING = BREW_EFFECT_BEHAVIOURS.register(
            _rough_landing_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _rough_landing_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("fall_damage_multiplier", -1))
    );

    // JUMP BOOST ========== Increase jump strength attr while also creasing the safe fall distance
    public static final String _jump_boost_id = "effect.jump_boost";
    public static final RegistryObject<BrewEffectBehaviour> JUMP_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _jump_boost_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _jump_boost_id))
                    .buildAttributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("jump_strength"),
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("safe_fall_distance")
            ))
    );

    // LEVITATION ========== Decrease gravity attr
    public static final String _levitation_id = "effect.levitation";
    public static final RegistryObject<BrewEffectBehaviour> LEVITATION = BREW_EFFECT_BEHAVIOURS.register(
            _levitation_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _levitation_id))
                    .buildAttributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("gravity", -0.01, AttributeModifier.Operation.ADD_VALUE),
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("safe_fall_distance", 1.0, AttributeModifier.Operation.ADD_VALUE)
            ))
    );

    // GROUNDING ========== Increase gravity attr
    public static final String _grounding_id = "effect.grounding";
    public static final RegistryObject<BrewEffectBehaviour> GROUNDING = BREW_EFFECT_BEHAVIOURS.register(
            _grounding_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _grounding_id))
                    .buildAttributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("gravity", 0.01, AttributeModifier.Operation.ADD_VALUE),
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("safe_fall_distance", -0.2, AttributeModifier.Operation.ADD_VALUE)
            ))
    );

    // INVISIBILITY ========== You disappear lol
    public static final String _invisibility_id = "effect.invisibility";
    public static final RegistryObject<BrewEffectBehaviour> INVISIBILITY = BREW_EFFECT_BEHAVIOURS.register(
            _invisibility_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _invisibility_id)
                    .withPrimaryEffect(context ->
                        context.entity().setInvisible(true)
                    ).withAdditionalEffect(context ->
                        context.entity().setInvisible(false)
                    ).withTickMode(BrewEffectBehaviour.TickMode.START_AND_END)
                    .build()
            )
    );

    // FIRE RESISTANCE ========== Decrease fire damage
    public static final String _fire_resistance_id = "effect.fire_resistance";
    public static final RegistryObject<BrewEffectBehaviour> FIRE_RESISTANCE = BREW_EFFECT_BEHAVIOURS.register(
            _fire_resistance_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _fire_resistance_id))
                    .buildResistanceHurtModifier(DamageTypeTags.IS_FIRE)
    );

    // FIRE IMMUNITY ========== Decrease luck attribute
    public static final String _fire_immunity_id = "effect.fire_immunity";
    public static final RegistryObject<BrewEffectBehaviour> FIRE_IMMUNITY = BREW_EFFECT_BEHAVIOURS.register(
            _fire_immunity_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _fire_immunity_id))
                    .buildInvulnerabilityHurtModifier(DamageTypeTags.IS_FIRE)
    );

    // FIRE WEAKNESS ========== Decrease fire damage
    public static final String _fire_weakness_id = "effect.fire_weakness";
    public static final RegistryObject<BrewEffectBehaviour> FIRE_WEAKNESS = BREW_EFFECT_BEHAVIOURS.register(
            _fire_weakness_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _fire_weakness_id))
                    .buildVulnerabilityHurtModifier(DamageTypeTags.IS_FIRE)
    );

    // LUCKY ========== Increase luck attribute
    public static final String _lucky_id = "effect.lucky";
    public static final RegistryObject<BrewEffectBehaviour> LUCKY = BREW_EFFECT_BEHAVIOURS.register(
            _lucky_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _lucky_id))
                    .buildAttributeModifier("luck")
    );

    // UNLUCKY ========== Decrease luck attribute
    public static final String _unlucky_id = "effect.unlucky";
    public static final RegistryObject<BrewEffectBehaviour> UNLUCKY = BREW_EFFECT_BEHAVIOURS.register(
            _unlucky_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _unlucky_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("luck", -1))
    );

    // WATER_BREATHING ========== Allows the player to breath underwater
    public static final String _water_breathing_id = "effect.water_breathing";
    public static final RegistryObject<BrewEffectBehaviour> WATER_BREATHING = BREW_EFFECT_BEHAVIOURS.register(
            _water_breathing_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _water_breathing_id))
                    .build()
    );

    // SWIM BOOST ========== Increase water movement efficiency
    public static final String _swim_boost_id = "effect.swim_boost";
    public static final RegistryObject<BrewEffectBehaviour> SWIM_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _swim_boost_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _swim_boost_id))
                    .buildAttributeModifier("water_movement_efficiency")
    );

    // SWIM SLOW ========== Decrease water movement efficiency
    public static final String _swim_slow_id = "effect.swim_slow";
    public static final RegistryObject<BrewEffectBehaviour> SWIM_SLOW = BREW_EFFECT_BEHAVIOURS.register(
            _swim_slow_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _swim_slow_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("water_movement_efficiency", -1))
    );

    // DREDGING HASTE | SCUBA MINING ========== Increase submerged mining speed
    public static final String _dredging_haste_id = "effect.dredging_haste";
    public static final RegistryObject<BrewEffectBehaviour> DREDGING_HASTE = BREW_EFFECT_BEHAVIOURS.register(
            _dredging_haste_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _dredging_haste_id))
                    .buildAttributeModifier("submerged_mining_speed")
    );

    // SOGGY MINING | DREDGING FATIGUE | UNDERTOW MINING | PRESSURE MINING | UNDERWATER FATIGUE | ANCHORED MINING | ADRIFT MINING | MINING DRAG | SEASICK MINING | PRESSURIZED MINING | ----> I'm not entirely happy with dredging as a name
    // DREDGING FATIGUE ========== Decrease submerged mining speed
    public static final String _dredging_fatigue_id = "effect.dredging_fatigue";
    public static final RegistryObject<BrewEffectBehaviour> DREDGING_FATIGUE = BREW_EFFECT_BEHAVIOURS.register(
            _dredging_fatigue_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _dredging_fatigue_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("submerged_mining_speed", -1))
    );

    // REGENERATION ========== Heals the player some amount after some interval of ticks
    public static final String _regeneration_id = "effect.regeneration";
    public static final RegistryObject<BrewEffectBehaviour> REGENERATION = BREW_EFFECT_BEHAVIOURS.register(
            _regeneration_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _regeneration_id))
                    .withPrimaryEffect(context ->
                        context.entity().heal((float) context.effectMainValue())
                    )
                    .withTickMode(BrewEffectBehaviour.TickMode.INTERVAL)
                    .build()
    );

    // POISON ========== Damages the player some amount after some interval of ticks, stopping at half a heart
    public static final String _poison_id = "effect.poison";
    public static final RegistryObject<BrewEffectBehaviour> POISON = BREW_EFFECT_BEHAVIOURS.register(
            _poison_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _poison_id)
                    .withPrimaryEffect(context -> {
                        if(context.entity().getHealth() > 1.0)
                            context.entity().hurt(context.entity().damageSources().magic(), (float) context.effectMainValue());
                    })
                    .withTickMode(BrewEffectBehaviour.TickMode.INTERVAL)
                    .build()
            )
    );

    // FATAL POISON ========== Damages the player some amount after some interval of ticks
    public static final String _fatal_poison_id = "effect.fatal_poison";
    public static final RegistryObject<BrewEffectBehaviour> FATAL_POISON = BREW_EFFECT_BEHAVIOURS.register(
            _fatal_poison_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _fatal_poison_id)
                    .withPrimaryEffect(context ->
                        context.entity().hurt(context.entity().damageSources().magic(), (float) context.effectMainValue())
                    )
                    .withTickMode(BrewEffectBehaviour.TickMode.INTERVAL)
                    .build()
            )
    );

    public static void init() {

    }
}
