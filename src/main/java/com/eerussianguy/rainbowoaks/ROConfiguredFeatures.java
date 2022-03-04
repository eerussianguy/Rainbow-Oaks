package com.eerussianguy.rainbowoaks;

import java.util.List;
import java.util.OptionalInt;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraftforge.common.util.Lazy;

public class ROConfiguredFeatures
{
    public static final Lazy<Holder<ConfiguredFeature<TreeConfiguration, ?>>> RAINBOW_OAK = Lazy.of(() -> register("rainbow_oak", Feature.TREE, createOak().build()));
    public static final Lazy<Holder<ConfiguredFeature<TreeConfiguration, ?>>> FANCY_RAINBOW_OAK = Lazy.of(() -> register("fancy_rainbow_oak", Feature.TREE, createFancyOak().build()));
    public static final Lazy<Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>>> RAINBOW_TREES = Lazy.of(() -> register("rainbow_trees", Feature.RANDOM_SELECTOR,
        new RandomFeatureConfiguration(List.of(
            new WeightedPlacedFeature(ROPlacements.RAINBOW_OAK_CHECKED.get(), 0.2F),
            new WeightedPlacedFeature(ROPlacements.FANCY_RAINBOW_OAK_CHECKED.get(), 0.1F)
        ),
            TreePlacements.OAK_BEES_002
        )
    ));

    public static void init()
    {
        RAINBOW_OAK.get();
        FANCY_RAINBOW_OAK.get();
    }

    public static void init2()
    {
        RAINBOW_TREES.get();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyOak()
    {
        return (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(RORegistry.RAINBOW_LOG.get()),
            new FancyTrunkPlacer(3, 11, 0),
            BlockStateProvider.simple(RORegistry.RAINBOW_LEAVES.get()),
            new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(0, 0, 0,OptionalInt.of(4))))
            .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createOak()
    {
        return createStraightBlobTree(RORegistry.RAINBOW_LOG.get(), RORegistry.RAINBOW_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves, int baseHeight, int heightRandA, int heightRandB, int offset)
    {
        return new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(log),
            new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
            BlockStateProvider.simple(leaves),
            new BlobFoliagePlacer(ConstantInt.of(offset),ConstantInt.of(0), 3),
            new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String name, F feature, FC config)
    {
        return FeatureUtils.register(MOD_ID + ":" + name, feature, config);
    }
}
