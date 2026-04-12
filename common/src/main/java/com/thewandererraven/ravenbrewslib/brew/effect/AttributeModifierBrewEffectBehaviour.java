package com.thewandererraven.ravenbrewslib.brew.effect;

import com.thewandererraven.ravenbrewslib.utils.BrewEffectsUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;

public class AttributeModifierBrewEffectBehaviour extends BrewEffectBehaviour {
    final ResourceLocation attributeId;
    final AttributeModifier.Operation attributeOperation;

    public AttributeModifierBrewEffectBehaviour(ResourceLocation attributeId, AttributeModifier.Operation attributeOperation, Consumer<BrewEffectContext> primaryEffect, Consumer<BrewEffectContext> additionalEffect) {
        super(
                brewEffectContext -> {
                    AttributeModifierBrewEffectBehaviour.addAttributeModifierToPlayer(brewEffectContext.entity(), BrewEffectsUtils.findAttributeByItsId(brewEffectContext.entity().level(), attributeId),
                            new AttributeModifierBrewEffectBehaviour.AttributeTemplate(
                                    attributeId,
                                    brewEffectContext.effectMainValue(),
                                    attributeOperation
                            ).create(1));
                    primaryEffect.accept(brewEffectContext);
                    },
                brewEffectContext -> {
                    AttributeModifierBrewEffectBehaviour.removeAttributeModifierToPlayer(brewEffectContext.entity(), BrewEffectsUtils.findAttributeByItsId(brewEffectContext.entity().level(), attributeId),
                            new AttributeModifierBrewEffectBehaviour.AttributeTemplate(
                                    attributeId,
                                    brewEffectContext.effectSecondaryValue() * -1,
                                    attributeOperation
                            ).create(1));
                    additionalEffect.accept(brewEffectContext);
                }
                );
        this.attributeId = attributeId;
        this.attributeOperation = attributeOperation;
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

    public record AttributeTemplate(ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        public AttributeModifier create(int level) {
            return new AttributeModifier(this.id, this.amount * (double)(level + 1), this.operation);
        }
    }
}
