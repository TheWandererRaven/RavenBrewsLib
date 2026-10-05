package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.utils.BrewEffectsUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.Consumer;

public class HurtModifierBrewEffectBehaviour extends BrewEffectBehaviour {
    public TagKey<DamageType> damageTag = null;
    public boolean isVulnerability = false;
    public boolean isInvulnerability = false;

    public HurtModifierBrewEffectBehaviour(ResourceLocation id, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect, TickMode tickMode, TagKey<DamageType> damageTypeTagKey, boolean isInvulnerability, boolean isVulnerability) {
        super(id, primaryEffect, additionalEffect, tickMode);
        this.damageTag = damageTypeTagKey;
        this.isInvulnerability = isInvulnerability;
        this.isVulnerability = isVulnerability;
    }
}
