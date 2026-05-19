package com.thewandererraven.ravenbrewslib.brew.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public record BrewBase(
        Item item,
        int caffeineBase,
        double caffeineMultiplier,
        double durationMultiplier,
        double effectValuesMultiplier
) {
    public static final Codec<BrewBase> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(BrewBase::item),
                    Codec.INT.optionalFieldOf("caffeine_base", 5).forGetter(BrewBase::caffeineBase),
                    Codec.DOUBLE.optionalFieldOf("caffeine_multiplier", 1.0).forGetter(BrewBase::caffeineMultiplier),
                    Codec.DOUBLE.optionalFieldOf("duration_multiplier", 1.0).forGetter(BrewBase::durationMultiplier),
                    Codec.DOUBLE.optionalFieldOf("effect_values_multiplier", 1.0).forGetter(BrewBase::effectValuesMultiplier)
            ).apply(instance, BrewBase::new)
        );
}
