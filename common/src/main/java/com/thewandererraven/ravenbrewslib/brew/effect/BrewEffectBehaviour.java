package com.thewandererraven.ravenbrewslib.brew.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.function.Consumer;

public class BrewEffectBehaviour {
    public final Consumer<BrewEffectContext> primaryEffect;
    //TODO: move this so it's ony on the attribute mod class? Currently I have this in case I need it for another effect type, now it's just useful for att mod so I wan remove the modifier when the effect ends
    public final Consumer<BrewEffectContext> additionalEffect;

    public BrewEffectBehaviour(Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        this.primaryEffect = primaryEffect;
        this.additionalEffect = additionalEffect;
    }

    public static BrewEffectBehaviour instant(Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return new BrewEffectBehaviour(primaryEffect, additionalEffect);
    }

    public static BrewEffectBehaviour instant(Consumer<BrewEffectContext> primaryEffect) {
        return new BrewEffectBehaviour(primaryEffect, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(ResourceLocation attributeId) {
        return attributeModifier(attributeId, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, context -> {}, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(ResourceLocation attributeId, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(attributeId, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, primaryEffect, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation) {
        return attributeModifier(attributeId, attributeOperation, context -> {}, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(attributeId, attributeOperation, primaryEffect, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(ResourceLocation attributeId, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(attributeId, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, primaryEffect, additionalEffect);
    }

    public static BrewEffectBehaviour attributeModifier(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return new AttributeModifierBrewEffectBehaviour(attributeId, attributeOperation, primaryEffect, additionalEffect);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId));
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), primaryEffect);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, AttributeModifier.Operation attributeOperation) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), attributeOperation);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), attributeOperation, primaryEffect);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), primaryEffect, additionalEffect);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), attributeOperation, primaryEffect, additionalEffect);
    }

    public static final BrewEffectBehaviour EMPTY = instant(
            (context) -> {}
    );
}
