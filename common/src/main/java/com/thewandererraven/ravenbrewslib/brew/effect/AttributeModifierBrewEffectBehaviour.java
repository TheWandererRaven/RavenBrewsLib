package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.utils.BrewEffectsUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.Consumer;

public class AttributeModifierBrewEffectBehaviour extends BrewEffectBehaviour {
    //final List<ResourceLocation> attributeIds;
    //final AttributeModifier.Operation attributeOperation;

    public AttributeModifierBrewEffectBehaviour(List<AttributeTemplate> attributes, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        super(
                brewEffectContext -> {
                    for (AttributeTemplate attr : attributes) {
                        AttributeModifierBrewEffectBehaviour.addAttributeModifierToPlayer(
                                brewEffectContext.entity(),
                                BrewEffectsUtils.findAttributeByItsId(brewEffectContext.entity().level(), attr.id),
                                attr.create(brewEffectContext.effectMainValue())
                        );
                        primaryEffect.accept(brewEffectContext);
                    }
                },
                brewEffectContext -> {
                    for (AttributeTemplate attr : attributes) {
                        AttributeModifierBrewEffectBehaviour.removeAttributeModifierToPlayer(
                                brewEffectContext.entity(),
                                BrewEffectsUtils.findAttributeByItsId(brewEffectContext.entity().level(), attr.id),
                                attr.create(brewEffectContext.effectSecondaryValue() * -1)
                        );
                        additionalEffect.accept(brewEffectContext);
                    }
                }
                );
    }

    private static void addAttributeModifierToPlayer(LivingEntity entity, Holder<Attribute> attribute, AttributeModifier modifier) {
        if(entity instanceof Player player) {
            AttributeInstance instance = player.getAttributes().getInstance(attribute);
            if (instance != null)
                if(!instance.hasModifier(modifier.id()))
                    instance.addTransientModifier(modifier);
        }
    }

    private static void removeAttributeModifierToPlayer(LivingEntity entity, Holder<Attribute> attribute, AttributeModifier modifier) {
        if(entity instanceof Player player) {
            AttributeInstance instance = player.getAttributes().getInstance(attribute);
            if (instance != null)
                instance.removeModifier(modifier);
        }
    }

    public record AttributeTemplate(ResourceLocation id, double baseAmount, AttributeModifier.Operation operation) {
//        public AttributeModifier create(int level) {
//            return new AttributeModifier(this.id, this.amount * (double)(level + 1), this.operation);
//        }
        public AttributeModifier create(double amountMultiplier) {
            return new AttributeModifier(this.id, baseAmount * amountMultiplier, this.operation);
        }

        public AttributeTemplate(ResourceLocation id, AttributeModifier.Operation operation) {
            this(id, 1, operation);
        }

        public AttributeTemplate(ResourceLocation id, double baseAmount) {
            this(id, baseAmount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }

        public AttributeTemplate(ResourceLocation id) {
            this(id, 1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }

        public AttributeTemplate(String id, double baseAmount, AttributeModifier.Operation operation) {
            this(ResourceLocation.withDefaultNamespace(id), baseAmount, operation);
        }

        public AttributeTemplate(String id, AttributeModifier.Operation operation) {
            this(ResourceLocation.withDefaultNamespace(id), operation);
        }

        public AttributeTemplate(String id, double baseAmount) {
            this(ResourceLocation.withDefaultNamespace(id), baseAmount);
        }

        public AttributeTemplate(String id) {
            this(ResourceLocation.withDefaultNamespace(id));
        }
    }
}
