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

    // SPEED ========== Increase movement speed attribute
    public static final String _speed_id = "effect.speed";
    public static final RegistryObject<BrewEffectBehaviour> SPEED = BREW_EFFECT_BEHAVIOURS.register(
            _speed_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _speed_id))
                    .buildAttributeModifier("movement_speed")
    );

    // SLOWNESS ========== Decrease movement speed attribute
    public static final String _slowness_id = "effect.slowness";
    public static final RegistryObject<BrewEffectBehaviour> SLOWNESS = BREW_EFFECT_BEHAVIOURS.register(
            _slowness_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _slowness_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("movement_speed", -1))
    );

    // QUICK ATTACK ========== Increase attack speed attribute
    public static final String _quick_attack_id = "effect.quick_attack";
    public static final RegistryObject<BrewEffectBehaviour> QUICK_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _quick_attack_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _quick_attack_id))
                    .buildAttributeModifier("attack_speed")
    );

    // SLOW ATTACK ========== Decrease attack_speed attribute
    public static final String _slow_attack_id = "effect.slow_attack";
    public static final RegistryObject<BrewEffectBehaviour> SLOW_ATTACK = BREW_EFFECT_BEHAVIOURS.register(
            _slow_attack_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _slow_attack_id))
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

    // MINER'S ELBOW ========== Increase block break speed attribute
    public static final String _block_breaking_speed_id = "effect.block_breaking_speed";
    public static final RegistryObject<BrewEffectBehaviour> BLOCK_BREAKING_SPEED = BREW_EFFECT_BEHAVIOURS.register(
            _block_breaking_speed_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _block_breaking_speed_id))
                    .buildAttributeModifier("block_break_speed")
    );

    // OVERWORKED ========== Decrease block break speed attribute
    public static final String _block_breaking_slowness_id = "effect.block_breaking_slowness";
    public static final RegistryObject<BrewEffectBehaviour> BLOCK_BREAKING_SLOWNESS = BREW_EFFECT_BEHAVIOURS.register(
            _block_breaking_slowness_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _block_breaking_slowness_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("block_break_speed", -1))
    );

    // STRONG LEGS ========== Decrease fall damage multiplier attribute
    public static final String _decreased_fall_damage_id = "effect.decreased_fall_damage";
    public static final RegistryObject<BrewEffectBehaviour> DECREASED_FALL_DAMAGE = BREW_EFFECT_BEHAVIOURS.register(
            _decreased_fall_damage_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _decreased_fall_damage_id))
                    .buildAttributeModifier("fall_damage_multiplier")
    );

    // WEAK LEGS ========== Increase fall damage multiplier attribute
    public static final String _increased_fall_damage_id = "effect.increased_fall_damage";
    public static final RegistryObject<BrewEffectBehaviour> INCREASED_FALL_DAMAGE = BREW_EFFECT_BEHAVIOURS.register(
            _increased_fall_damage_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _increased_fall_damage_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("fall_damage_multiplier", -1))
    );

    // JUMP BOOST ========== Increase jump strength attr while also creasing the safe fall distance
    public static final String _jump_boost_id = "effect.jump_boost";
    public static final RegistryObject<BrewEffectBehaviour> JUMP_BOOST = BREW_EFFECT_BEHAVIOURS.register(
            _jump_boost_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _jump_boost_id))
                    .buildAttributeModifier(List.of(
                    new AttributeModifierBrewEffectBehaviour.AttributeTemplate("jump_strength"),
                    //new AttributeModifierBrewEffectBehaviour.AttributeTemplate("fall_damage_multiplier", -1),
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

    // EXTRA PULL ========== Increase gravity attr
    public static final String _extra_pull_id = "effect.extra_pull";
    public static final RegistryObject<BrewEffectBehaviour> EXTRA_PULL = BREW_EFFECT_BEHAVIOURS.register(
            _extra_pull_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _extra_pull_id))
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
                    .buildHurtModifier(DamageTypeTags.IS_FIRE, false)
    );

    // FIRE IMMUNITY ========== Decrease luck attribute
    public static final String _fire_immunity_id = "effect.fire_immunity";
    public static final RegistryObject<BrewEffectBehaviour> FIRE_IMMUNITY = BREW_EFFECT_BEHAVIOURS.register(
            _fire_immunity_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _fire_immunity_id))
                    .buildHurtModifier(DamageTypeTags.IS_FIRE, true)
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

    // SWIM SPEED ========== Increase water movement efficiency
    public static final String _swim_speed_id = "effect.swim_speed";
    public static final RegistryObject<BrewEffectBehaviour> SWIM_SPEED = BREW_EFFECT_BEHAVIOURS.register(
            _swim_speed_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _swim_speed_id))
                    .buildAttributeModifier("water_movement_efficiency")
    );

    // SWIM SLOWNESS ========== Decrease water movement efficiency
    public static final String _swim_slowness_id = "effect.swim_slowness";
    public static final RegistryObject<BrewEffectBehaviour> SWIM_SLOWNESS = BREW_EFFECT_BEHAVIOURS.register(
            _swim_slowness_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _swim_slowness_id))
                    .buildAttributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate("water_movement_efficiency", -1))
    );

    // UNDERWATER MINING SPEED ========== Increase submerged mining speed
    public static final String _underwater_mining_speed_id = "effect.underwater_mining_speed";
    public static final RegistryObject<BrewEffectBehaviour> UNDERWATER_MINING_SPEED = BREW_EFFECT_BEHAVIOURS.register(
            _underwater_mining_speed_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _underwater_mining_speed_id))
                    .buildAttributeModifier("submerged_mining_speed")
    );

    // UNDERWATER MINING SLOWNESS ========== Decrease submerged mining speed
    public static final String _underwater_mining_slowness_id = "effect.underwater_mining_slowness";
    public static final RegistryObject<BrewEffectBehaviour> UNDERWATER_MINING_SLOWNESS = BREW_EFFECT_BEHAVIOURS.register(
            _underwater_mining_slowness_id,
            () -> (new BrewEffectBehaviour.Builder(Constants.MOD_ID, _underwater_mining_slowness_id))
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
