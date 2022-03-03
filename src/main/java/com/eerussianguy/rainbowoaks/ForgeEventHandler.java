package com.eerussianguy.rainbowoaks;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ForgeEventHandler
{
    public static void init()
    {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ForgeEventHandler::onBiomeLoad);
    }

    private static void onBiomeLoad(final BiomeLoadingEvent event)
    {
        List<ResourceLocation> biomes = ROConfig.COMMON.biomes.get().stream().map(ResourceLocation::new).toList();
        if (event.getName() != null)
        {
            for (ResourceLocation res : biomes)
            {
                if (event.getName().compareNamespaced(res) == 0)
                {
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(ROPlacements.RAINBOW_TREES_CHECKED);
                }
            }
        }
    }
}
