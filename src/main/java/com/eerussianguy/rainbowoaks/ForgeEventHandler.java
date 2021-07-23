package com.eerussianguy.rainbowoaks;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE, modid=MOD_ID)
public class ForgeEventHandler
{
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event)
    {
        List<ResourceLocation> biomes = ROConfig.COMMON.biomes.get().stream().map(ResourceLocation::new).collect(Collectors.toList());
        if (event.getName() != null)
        {
            for (ResourceLocation res : biomes)
            {
                if (event.getName().compareNamespaced(res) == 0)
                {
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> ROConfiguredFeatures.RAINBOW_TREES);
                }
            }
        }
    }
}
