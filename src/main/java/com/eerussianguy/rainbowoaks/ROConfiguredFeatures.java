package com.eerussianguy.rainbowoaks;

import java.util.OptionalInt;
import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;

public class ROConfiguredFeatures
{
    public static final ConfiguredFeature<TreeConfiguration, ?> RAINBOW_OAK = register("rainbow_oak",
        Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
            new SimpleStateProvider(RORegistry.RAINBOW_LOG.get().defaultBlockState()),
            new StraightTrunkPlacer(4, 2, 0),
            new SimpleStateProvider(RORegistry.RAINBOW_LEAVES.get().defaultBlockState()),
            new SimpleStateProvider(RORegistry.RAINBOW_SAPLING.get().defaultBlockState()),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
            new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_RAINBOW_OAK = register("fancy_rainbow_oak",
        Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(
            new SimpleStateProvider(RORegistry.RAINBOW_LOG.get().defaultBlockState()),
            new FancyTrunkPlacer(3, 11, 0),
            new SimpleStateProvider(RORegistry.RAINBOW_LEAVES.get().defaultBlockState()),
            new SimpleStateProvider(RORegistry.RAINBOW_SAPLING.get().defaultBlockState()),
            new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> RAINBOW_TREES = register("rainbow_trees",
        Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(
            ImmutableList.of(FANCY_RAINBOW_OAK.weighted(0.25F)), RAINBOW_OAK))
            .decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA
            .configured(new FrequencyWithExtraChanceDecoratorConfiguration(ROConfig.COMMON.extraAttempts.get(), 0.1F, 1)))
            .rarity(ROConfig.COMMON.rarity.get()));


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(@Nonnull String name, ConfiguredFeature<FC, ?> cf)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, name), cf);
    }
}
