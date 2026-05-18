package com.thewandererraven.ravenbrewslib.registry;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.brew.effect.BrewEffectBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class RavenBrewsLibRegistryKeys {
    public static final ResourceKey<Registry<BrewEffectBehaviour>> BREW_EFFECT_BEHAVIOUR = createRegistryKey("brew_effects");

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String name) {
        return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
