package com.thewandererraven.ravenbrewslib;

import com.thewandererraven.ravenbrewslib.brewing.BrewBaseReloadListenerFabric;
import com.thewandererraven.ravenbrewslib.brewing.BrewIngredientReloadListenerFabric;
import com.thewandererraven.ravenbrewslib.brewing.BrewVariantReloadListenerFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public class RavenBrewsLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RavenBrewsLibCommon.init();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new BrewIngredientReloadListenerFabric());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new BrewBaseReloadListenerFabric());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new BrewVariantReloadListenerFabric());
    }
}
