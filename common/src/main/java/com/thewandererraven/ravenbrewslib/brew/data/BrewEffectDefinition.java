package com.thewandererraven.ravenbrewslib.brew.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.thewandererraven.ravenbrewslib.Constants;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record BrewEffectDefinition(
        ResourceLocation id,
        int priority,
        int duration,
        int intervalDuration,
        double mainValue,
        double secondaryValue
) {

    public static final Codec<BrewEffectDefinition> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceLocation.CODEC.fieldOf("id").forGetter(BrewEffectDefinition::id),
                    Codec.INT.optionalFieldOf("priority", 10).forGetter(BrewEffectDefinition::priority),
                    Codec.INT.optionalFieldOf("duration", 0).forGetter(BrewEffectDefinition::duration),
                    Codec.INT.optionalFieldOf("interval_duration", 0).forGetter(BrewEffectDefinition::intervalDuration),
                    Codec.DOUBLE.fieldOf("main_value").forGetter(BrewEffectDefinition::mainValue),
                    Codec.DOUBLE.optionalFieldOf("secondary_value", 0.0).forGetter(BrewEffectDefinition::secondaryValue)
            ).apply(instance, BrewEffectDefinition::new)
    );

    public ResourceLocation generateIconLocation() {
        // TODO: Rework this later to have more constant paths
        return ResourceLocation.fromNamespaceAndPath(this.id().getNamespace(), "textures/gui/effect/icons/" + this.id.getPath() + ".png");
    }

    public static List<BrewEffectDefinition.Builder> getListOfDefaultEffects() {
        return List.of(
                new BrewEffectDefinition.Builder(
                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "effect.haste"),
                        10,
                        15 * 20,
                        0,
                        5.0,
                        0.0
                ),
                new BrewEffectDefinition.Builder(
                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "effect.slowness"),
                        15,
                        7 * 20,
                        0,
                        0.2,
                        0.0
                )
        );
    }

    public static class Builder {
        public ResourceLocation id;
        int priority;
        int duration;
        int intervalDuration;
        double mainValue;
        double secondaryValue;

        public Builder(ResourceLocation id, int priority, int duration, int intervalDuration, double mainValue, double secondaryValue) {
            this.id = id;
            this.priority = priority;
            this.duration = duration;
            this.intervalDuration = intervalDuration;
            this.mainValue = mainValue;
            this.secondaryValue = secondaryValue;
        }

        public Builder(ResourceLocation id) {
            this(id, 10, 0, 0, 0.0, 0.0);
        }

        public Builder addDuration(int addedDuration) {
            this.duration += addedDuration;
            if(this.duration < 0)
                this.duration = 0;
            return this;
        }

        public Builder scaleDuration(double multiplier) {
            this.duration = (int) Math.ceil(this.duration * multiplier);
            if(this.duration < 0)
                this.duration = 0;
            return this;
        }

        public Builder setMainValue(double mainValue) {
            this.mainValue = mainValue;
            return this;
        }

        public Builder addMainValue(double addedMainValue) {
            this.mainValue += addedMainValue;
            return this;
        }

        public Builder scaleMainValue(double multiplier) {
            this.mainValue = this.mainValue * multiplier;
            return this;
        }

        public Builder setSecondaryValue(double secondaryValue) {
            this.secondaryValue = secondaryValue;
            return this;
        }

        public Builder addSecondaryValue(double addedSecondaryValue) {
            this.secondaryValue += addedSecondaryValue;
            return this;
        }

        public Builder scaleSecondaryValue(double multiplier) {
            this.secondaryValue = this.secondaryValue * multiplier;
            return this;
        }

        public BrewEffectDefinition build() {
            return new BrewEffectDefinition(this.id, this.priority, this.duration, this.intervalDuration, this.mainValue, this.secondaryValue);
        }
    }
}
