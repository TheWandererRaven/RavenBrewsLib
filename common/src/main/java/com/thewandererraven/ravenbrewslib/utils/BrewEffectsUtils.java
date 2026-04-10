package com.thewandererraven.ravenbrewslib.utils;


import com.thewandererraven.ravenbrewslib.brew.effect.BrewEffect;
import com.thewandererraven.ravenbrewslib.brew.effect.BrewEffectsRegistry;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.Level;

public class BrewEffectsUtils {
    public static BrewEffect findEffectInRegistry(ResourceLocation effectId) {
        RegistryObject<BrewEffect> foundEffect = BrewEffectsRegistry.BREW_EFFECT_CORES.getEntries().stream()
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
}