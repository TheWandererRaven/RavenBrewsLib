package com.thewandererraven.ravenbrewslib.brewing;

import com.thewandererraven.ravenbrewslib.brewing.base.BrewBaseReloadListener;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;

public class BrewBaseReloadListenerFabric extends BrewBaseReloadListener implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return getReloadListenerId();
    }
}
