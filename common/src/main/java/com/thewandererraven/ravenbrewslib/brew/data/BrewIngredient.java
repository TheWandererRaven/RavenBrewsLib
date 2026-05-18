package com.thewandererraven.ravenbrewslib.brew.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

public record BrewIngredient(
        Item item,
        int caffeineDelta,
        List<BrewEffectDefinition> effects,
        List<ResourceLocation> negatedEffects
) {
    public static final Codec<BrewIngredient> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(BrewIngredient::item),
                    Codec.INT.fieldOf("caffeine_delta").forGetter(BrewIngredient::caffeineDelta),
                    BrewEffectDefinition.CODEC.listOf().fieldOf("effects").forGetter(BrewIngredient::effects),
                    ResourceLocation.CODEC.listOf().fieldOf("negated_effects").orElse(List.of()).forGetter(BrewIngredient::negatedEffects)
            ).apply(instance, BrewIngredient::new)
    );
}
