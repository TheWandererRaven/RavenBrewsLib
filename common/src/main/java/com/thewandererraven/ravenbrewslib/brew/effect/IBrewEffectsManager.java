package com.thewandererraven.ravenbrewslib.brew.effect;


import com.thewandererraven.ravenbrewslib.brew.data.BrewEffectDefinition;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IBrewEffectsManager {
    public boolean isEmpty();
    boolean add(BrewEffectDefinition brewData);
    void tick();
    List<BrewEffectBehaviour> getEffectsStack();
    public void setEffectIcons(List<ResourceLocation> iconLocations);
    public List<ResourceLocation> getEffectIcons();
    public void clearEffects();
    public void clearAll();
    public void sendEffectIconsToClient();
    public void sendDurationsToClient();
    public void sendCaffeineToClient();
    public void sendAllInfoToClient();
    public CompoundTag serializeNBT();
    public void deserializeNBT(CompoundTag tag);
}