package com.thewandererraven.ravenbrewslib.brew.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

public record BrewVariant(
        List<Item> items,
        ResourceLocation variantId
) {
    public static final Codec<BrewVariant> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.byNameCodec().listOf().fieldOf("items").forGetter(BrewVariant::items),
                    ResourceLocation.CODEC.fieldOf("variant_id").forGetter(BrewVariant::variantId)
            ).apply(instance, BrewVariant::new)
    );
}
