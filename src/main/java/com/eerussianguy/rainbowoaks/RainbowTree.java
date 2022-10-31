package com.eerussianguy.rainbowoaks;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

@ParametersAreNonnullByDefault
public class RainbowTree extends AbstractTreeGrower
{
    public static final ResourceLocation FANCY_RAINBOW_OAK = RainbowOaks.identifier("fancy_rainbow_oak");
    public static final ResourceLocation NORMAL_RAINBOW_OAK = RainbowOaks.identifier("rainbow_oak");

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random, boolean hasFlowers)
    {
        final var registry = level.registryAccess().registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY);
        final var feature = registry.get(random.nextFloat() < 0.1f ? FANCY_RAINBOW_OAK : NORMAL_RAINBOW_OAK);
        return feature == null ? null : Holder.direct(feature);
    }

    //*** unused via forge patch ***//
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers)
    {
        return null;
    }

}
