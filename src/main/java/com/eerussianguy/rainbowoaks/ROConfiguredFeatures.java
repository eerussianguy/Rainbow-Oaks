package com.eerussianguy.rainbowoaks;

import java.util.OptionalInt;
import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

public class ROConfiguredFeatures
{
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> RAINBOW_OAK = register("rainbow_oak",
        Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RORegistry.RAINBOW_LOG.get().defaultBlockState()),
            new SimpleBlockStateProvider(RORegistry.RAINBOW_LEAVES.get().defaultBlockState()),
            new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
            new StraightTrunkPlacer(4, 2, 0),
            new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_RAINBOW_OAK = register("fancy_rainbow_oak",
        Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RORegistry.RAINBOW_LOG.get().defaultBlockState()),
            new SimpleBlockStateProvider(RORegistry.RAINBOW_LEAVES.get().defaultBlockState()),
            new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
            new FancyTrunkPlacer(3, 11, 0),
            new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
            .ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<?, ?> RAINBOW_TREES = register("rainbow_trees",
        Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(
            ImmutableList.of(FANCY_RAINBOW_OAK.weighted(0.25F)), RAINBOW_OAK))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA
            .configured(new AtSurfaceWithExtraConfig(ROConfig.COMMON.extraAttempts.get(), 0.1F, 1))).chance(ROConfig.COMMON.rarity.get()));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(@Nonnull String name, ConfiguredFeature<FC, ?> cf)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, name), cf);
    }
}
