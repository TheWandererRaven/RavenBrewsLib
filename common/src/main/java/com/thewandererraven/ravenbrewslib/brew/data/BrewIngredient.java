package com.thewandererraven.ravenbrewslib.brew.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.List;

public record BrewIngredient(
        Item item,
        int caffeineDelta,
        List<BrewEffectData> effects
) {
    public static final Codec<BrewIngredient> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(BrewIngredient::item),
                    Codec.INT.fieldOf("caffeine_delta").forGetter(BrewIngredient::caffeineDelta),
                    BrewEffectData.CODEC.listOf().fieldOf("effects").forGetter(BrewIngredient::effects)
            ).apply(instance, BrewIngredient::new)
    );
}
