package com.thewandererraven.ravenbrewslib.brewing.base;

import com.thewandererraven.ravenbrewslib.brew.data.BrewBase;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.Optional;

public class BrewBaseRegistry {
    private static Map<Item, BrewBase> INGREDIENTS = Map.of();

    private BrewBaseRegistry() {}

    public static void set(Map<Item, BrewBase> map) {
        INGREDIENTS = Map.copyOf(map);
    }

    public static Optional<BrewBase> get(Item item) {
        return Optional.ofNullable(INGREDIENTS.get(item));
    }

    public static Map<Item, BrewBase> getAll() {
        return INGREDIENTS;
    }
}
