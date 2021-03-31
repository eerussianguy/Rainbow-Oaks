package com.eerussianguy.rainbowoaks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RainbowTree extends Tree
{
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(@Nullable Random rand, boolean p_225546_2_)
    {
        if (rand == null) return ROConfiguredFeatures.RAINBOW_OAK;
        return rand.nextInt(10) == 0 ? ROConfiguredFeatures.FANCY_RAINBOW_OAK : ROConfiguredFeatures.RAINBOW_OAK;
    }
}
