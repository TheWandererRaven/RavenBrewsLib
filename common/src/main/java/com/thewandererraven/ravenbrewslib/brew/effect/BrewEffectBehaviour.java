package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.brew.data.BrewEffectDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import java.util.List;
import java.util.function.Consumer;

public class BrewEffectBehaviour {
    public final ResourceLocation id;
    public final Consumer<BrewEffectContext> primaryEffect;
    //TODO: move this so it's ony on the attribute mod class? Currently I have this in case I need it for another effect type, now it's just useful for att mod so I wan remove the modifier when the effect ends
    public final Consumer<BrewEffectContext> additionalEffect;
    public final TickMode tickMode;

    public BrewEffectBehaviour(ResourceLocation id, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect, TickMode tickMode) {
        this.id = id;
        this.primaryEffect = primaryEffect;
        this.additionalEffect = additionalEffect;
        this.tickMode = tickMode;
    }

    public static class Builder {
        public ResourceLocation id;
        public Consumer<BrewEffectContext> primaryEffect;
        public Consumer<BrewEffectContext> additionalEffect;
        public TickMode tickMode;

        public Builder(ResourceLocation id) {
            this.id = id;
            this.primaryEffect = context -> {};
            this.additionalEffect = context -> {};
            this.tickMode = TickMode.IGNORE;
        }

        public Builder(String namespace, String path) {
            this(ResourceLocation.fromNamespaceAndPath(namespace, path));
        }

        public Builder withPrimaryEffect(Consumer<BrewEffectContext> effect) {
            this.primaryEffect = effect;
            return this;
        }

        public Builder withAdditionalEffect(Consumer<BrewEffectContext> effect) {
            this.additionalEffect = effect;
            return this;
        }

        public Builder withTickMode(TickMode tickMode) {
            this.tickMode = tickMode;
            return this;
        }

        public BrewEffectBehaviour build() {
            return new BrewEffectBehaviour(this.id, this.primaryEffect, this.additionalEffect, this.tickMode);
        }

        public BrewEffectBehaviour buildHurtModifier(TagKey<DamageType> damageTypeTagKey, boolean isInvulnerability) {
            return new HurtModifierBrewEffectBehaviour(id, primaryEffect, additionalEffect, tickMode, damageTypeTagKey, isInvulnerability);
        }

        public BrewEffectBehaviour buildHurtModifier(TagKey<DamageType> damageTypeTagKey) {
            return buildHurtModifier(damageTypeTagKey, false);
        }

        public BrewEffectBehaviour buildAttributeModifier(List<AttributeModifierBrewEffectBehaviour.AttributeTemplate> attributes) {
            return new AttributeModifierBrewEffectBehaviour(id, attributes, primaryEffect, additionalEffect);
        }

        public BrewEffectBehaviour buildAttributeModifier(AttributeModifierBrewEffectBehaviour.AttributeTemplate attribute) {
            return buildAttributeModifier(List.of(attribute));
        }

        public BrewEffectBehaviour buildAttributeModifier(String attributeName) {
            return buildAttributeModifier(List.of(new AttributeModifierBrewEffectBehaviour.AttributeTemplate(attributeName)));
        }
    }

    public enum TickMode {
        SINGLE,
        EVERY_TICK,
        START_AND_END,
        INTERVAL,
        IGNORE
    }
}
