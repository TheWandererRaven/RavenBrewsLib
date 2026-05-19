package com.thewandererraven.ravenbrewslib.brewing.ingredient;

import com.thewandererraven.ravenbrewslib.brew.data.BrewIngredient;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.Optional;

public class BrewIngredientRegistry {
    private static Map<Item, BrewIngredient> INGREDIENTS = Map.of();

    private BrewIngredientRegistry() {}

    public static void set(Map<Item, BrewIngredient> map) {
        INGREDIENTS = Map.copyOf(map);
    }

    public static Optional<BrewIngredient> get(Item item) {
        return Optional.ofNullable(INGREDIENTS.get(item));
    }

    public static Map<Item, BrewIngredient> getAll() {
        return INGREDIENTS;
    }
}
