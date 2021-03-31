package com.eerussianguy.rainbowoaks;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

public class CommonConfig
{
    public final IntValue rarity;
    public final IntValue extraAttempts;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> biomes;

    CommonConfig(Builder innerBuilder)
    {
        Function<String, Builder> builder = name -> innerBuilder.translation(MOD_ID + ".config.server." + name);

        innerBuilder.push("general");

        rarity = builder.apply("rarity").comment("Rarity of a rainbow tree patch in 1/N chunks").worldRestart().defineInRange("rarity", 1, 1, Integer.MAX_VALUE);
        extraAttempts = builder.apply("extraAttempts").comment("Extra trees that spawn in a patch").worldRestart().defineInRange("extraAttempts", 2, 0, Integer.MAX_VALUE);
        biomes = builder.apply("biomes").comment("Resource Locations of biomes they spawn in. Separate with commas.").worldRestart().defineList("biomes", () -> Arrays.asList("minecraft:flower_forest"), o -> o instanceof String);

        innerBuilder.pop();
    }

}
