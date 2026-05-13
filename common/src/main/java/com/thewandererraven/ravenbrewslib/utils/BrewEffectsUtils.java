package com.thewandererraven.ravenbrewslib.utils;


import com.thewandererraven.ravenbrewslib.brew.data.BrewBase;
import com.thewandererraven.ravenbrewslib.brew.data.BrewIngredient;
import com.thewandererraven.ravenbrewslib.brew.effect.BrewEffectBehaviour;
import com.thewandererraven.ravenbrewslib.brew.effect.BrewEffectsRegistry;
import com.thewandererraven.ravenbrewslib.brewing.base.BrewBaseRegistry;
import com.thewandererraven.ravenbrewslib.brewing.ingredient.BrewIngredientRegistry;
import com.thewandererraven.ravenbrewslib.brewing.variant.BrewVariantRegistry;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class BrewEffectsUtils {
    public static BrewEffectBehaviour findEffectInRegistry(ResourceLocation effectId) {
        RegistryObject<BrewEffectBehaviour> foundEffect = BrewEffectsRegistry.BREW_EFFECT_CORES.getEntries().stream()
                .filter(param -> param.getId().equals(effectId)).findFirst()
                .orElse(null);
        if(foundEffect != null)
            return foundEffect.get();
        return null;
    }

    public static Holder<Attribute> findAttributeByItsId(Level level, ResourceLocation attributeId) {
        Registry<Attribute> reg = level.registryAccess().lookup(Registries.ATTRIBUTE).orElse(null);
        if(reg == null)
            return null;
        return reg.get(attributeId).orElse(null);
    }

    public static Optional<BrewIngredient> findIngredientData(Item item) {
        return BrewIngredientRegistry.get(item);
    }

    public static Optional<BrewBase> findBaseData(Item item) {
        return BrewBaseRegistry.get(item);
    }

    public static Optional<ResourceLocation> findBrewVariant(List<Item> items) {
        return BrewVariantRegistry.get(items);
    }
}