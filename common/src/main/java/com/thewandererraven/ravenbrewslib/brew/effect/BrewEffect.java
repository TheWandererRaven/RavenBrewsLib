package com.thewandererraven.ravenbrewslib.brew.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.function.Consumer;

public class BrewEffect {
    public final Consumer<BrewEffectContext> primaryEffect;
    //TODO: move this so it's ony on the attribute mod class? Currently I have this in case I need it for another effect type, now it's just useful for att mod so I wan remove the modifier when the effect ends
    public final Consumer<BrewEffectContext> additionalEffect;

    public BrewEffect(Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        this.primaryEffect = primaryEffect;
        this.additionalEffect = additionalEffect;
    }

//    public ResourceLocation generateIconLocation() {
//        // TODO: Rework this later to have more constant paths
//        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/effect/icons/" + this.attributeId.getPath() + ".png");
//    }

    public static BrewEffect instant(Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return new BrewEffect(primaryEffect, additionalEffect);
    }

    public static BrewEffect instant(Consumer<BrewEffectContext> primaryEffect) {
        return new BrewEffect(primaryEffect, context -> {});
    }

    public static BrewEffect attributeModifier(ResourceLocation attributeId) {
        return attributeModifier(attributeId, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, context -> {}, context -> {});
    }

    public static BrewEffect attributeModifier(ResourceLocation attributeId, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(attributeId, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, primaryEffect, context -> {});
    }

    public static BrewEffect attributeModifier(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation) {
        return attributeModifier(attributeId, attributeOperation, context -> {}, context -> {});
    }

    public static BrewEffect attributeModifier(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(attributeId, attributeOperation, primaryEffect, context -> {});
    }

    public static BrewEffect attributeModifier(ResourceLocation attributeId, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(attributeId, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, primaryEffect, additionalEffect);
    }

    public static BrewEffect attributeModifier(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return new AttributeModifierEffect(attributeId, attributeOperation, primaryEffect, additionalEffect);
    }

    public static BrewEffect attributeModifier(String attributeId) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId));
    }

    public static BrewEffect attributeModifier(String attributeId, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), primaryEffect);
    }

    public static BrewEffect attributeModifier(String attributeId, AttributeModifier.Operation attributeOperation) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), attributeOperation);
    }

    public static BrewEffect attributeModifier(String attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), attributeOperation, primaryEffect);
    }

    public static BrewEffect attributeModifier(String attributeId, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), primaryEffect, additionalEffect);
    }

    public static BrewEffect attributeModifier(String attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(ResourceLocation.withDefaultNamespace(attributeId), attributeOperation, primaryEffect, additionalEffect);
    }

    public static final BrewEffect EMPTY = instant(
            (context) -> {}
    );
}
