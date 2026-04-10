package com.thewandererraven.ravenbrewslib.brew.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.function.Consumer;

public class AttributeModifierEffect extends BrewEffect {
    final ResourceLocation attributeId;
    final AttributeModifier.Operation attributeOperation;

    public AttributeModifierEffect(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        super(primaryEffect, additionalEffect);
        this.attributeId = attributeId;
        this.attributeOperation = attributeOperation;
    }
}
