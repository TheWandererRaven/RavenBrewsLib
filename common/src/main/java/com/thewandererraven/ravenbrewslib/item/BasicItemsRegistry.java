package com.thewandererraven.ravenbrewslib.item;

import com.thewandererraven.ravenbrewslib.Constants;
import com.thewandererraven.ravenbrewslib.registry.RegistryObject;
import com.thewandererraven.ravenbrewslib.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class BasicItemsRegistry {
    public static final RegistryProvider<Item> ITEMS = RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item> TEST_ITEM =
            ITEMS.register("test_item", () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_item")
                    ))
            ));

    public static void init() {

    }
}
