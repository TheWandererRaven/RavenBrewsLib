package com.thewandererraven.ravenbrewslib.brew.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.List;
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

    public static BrewEffectBehaviour attributeModifier(List<AttributeModifierBrewEffectBehaviour.AttributeTemplate> attributes) {
        return attributeModifier(attributes, context -> {}, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(List<AttributeModifierBrewEffectBehaviour.AttributeTemplate> attributes, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(attributes, primaryEffect, context -> {});
    }

    public static BrewEffectBehaviour attributeModifier(List<AttributeModifierBrewEffectBehaviour.AttributeTemplate> attributes, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return new AttributeModifierBrewEffectBehaviour(attributes, primaryEffect, additionalEffect);
    }

    public static BrewEffectBehaviour attributeModifier(AttributeModifierBrewEffectBehaviour.AttributeTemplate attribute) {
        return attributeModifier(List.of(attribute));
    }

    public static BrewEffectBehaviour attributeModifier(AttributeModifierBrewEffectBehaviour.AttributeTemplate attribute, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(List.of(attribute), primaryEffect);
    }

    public static BrewEffectBehaviour attributeModifier(AttributeModifierBrewEffectBehaviour.AttributeTemplate attribute, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(List.of(attribute), primaryEffect, additionalEffect);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId) {
        return attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(attributeId));
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, Consumer<BrewEffectContext> primaryEffect) {
        return attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(attributeId), primaryEffect);
    }

    public static BrewEffectBehaviour attributeModifier(String attributeId, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        return attributeModifier(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(attributeId), primaryEffect, additionalEffect);
    }

    public static final BrewEffectBehaviour EMPTY = instant(
            (context) -> {}
    );
}
