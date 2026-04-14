package com.thewandererraven.ravenbrewslib.brewing;

import com.thewandererraven.ravenbrewslib.brewing.ingredient.BrewIngredientReloadListener;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;

public class BrewIngredientReloadListenerFabric extends BrewIngredientReloadListener implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return getReloadListenerId();
    }
}
