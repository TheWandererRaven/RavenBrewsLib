package com.thewandererraven.ravenbrewslib.platform;

import com.thewandererraven.ravenbrewslib.platform.services.IRegistryUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public class NeoForgeRegistryUtil implements IRegistryUtil {

    @SuppressWarnings("all")
    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(
            BiFunction<BlockPos, BlockState, T> builder, Block... blocks) {
        return new BlockEntityType<T>(builder::apply, blocks);
    }

    @Override
    public Holder<MobEffect> getMobEffect(ResourceLocation resourceLocation) {
        return BuiltInRegistries.MOB_EFFECT.get(resourceLocation).orElse(null);
    }
}
