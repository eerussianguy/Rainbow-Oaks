package com.eerussianguy.rainbowoaks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RainbowTree extends AbstractTreeGrower
{
    @Nullable
    @Override
    protected Holder<ConfiguredFeature<TreeConfiguration, ?>> getConfiguredFeature(@Nullable Random rand, boolean flowers)
    {
        if (rand == null) return ROConfiguredFeatures.RAINBOW_OAK;
        return rand.nextInt(10) == 0 ? ROConfiguredFeatures.FANCY_RAINBOW_OAK : ROConfiguredFeatures.RAINBOW_OAK;
    }
}
