package com.eerussianguy.rainbowoaks;

import java.util.List;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

public class ROPlacements
{
    public static Holder<PlacedFeature> RAINBOW_OAK_CHECKED;
    public static Holder<PlacedFeature> FANCY_RAINBOW_OAK_CHECKED;
    public static Holder<PlacedFeature> RAINBOW_TREES_CHECKED;

    public static void init()
    {
        RAINBOW_OAK_CHECKED = register("rainbow_oak_checked", ROConfiguredFeatures.RAINBOW_OAK, PlacementUtils.filteredByBlockSurvival(RORegistry.RAINBOW_SAPLING.get()));
        FANCY_RAINBOW_OAK_CHECKED = register("fancy_rainbow_oak_checked", ROConfiguredFeatures.FANCY_RAINBOW_OAK, PlacementUtils.filteredByBlockSurvival(RORegistry.RAINBOW_SAPLING.get()));
    }

    public static void init2()
    {
        RAINBOW_TREES_CHECKED = register("rainbow_trees_checked", ROConfiguredFeatures.RAINBOW_TREES, treePlacement(PlacementUtils.countExtra(ROConfig.COMMON.extraAttempts.get(), 0.1F, 1)));
    }

    private static ImmutableList<PlacementModifier> treePlacement(PlacementModifier modifier)
    {
        return ImmutableList.<PlacementModifier>builder()
            .add(RarityFilter.onAverageOnceEvery(ROConfig.COMMON.rarity.get()))
            .add(modifier)
            .add(InSquarePlacement.spread())
            .add(VegetationPlacements.TREE_THRESHOLD)
            .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
            .add(BiomeFilter.biome())
            .build();
    }

    private static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> holder, List<PlacementModifier> placements)
    {
        return PlacementUtils.register(MOD_ID + ":" + name, Holder.hackyErase(holder), List.copyOf(placements));
    }

    private static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> holder, PlacementModifier... placements)
    {
        return PlacementUtils.register(MOD_ID + ":" + name, Holder.hackyErase(holder), List.of(placements));
    }
}
