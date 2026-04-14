package com.thewandererraven.ravenbrewslib.brewing;

import com.thewandererraven.ravenbrewslib.brewing.variant.BrewVariantReloadListener;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;

public class BrewVariantReloadListenerFabric extends BrewVariantReloadListener implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return getReloadListenerId();
    }
}
