package com.eerussianguy.rainbowoaks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

@Mod(MOD_ID)
public class RainbowOaks
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "rainbowoaks";

    public RainbowOaks()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);

        RORegistry.BLOCKS.register(modBus);
        RORegistry.ITEMS.register(modBus);

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            ClientEventHandler.init();
        }
        ForgeEventHandler.init();
        ROConfig.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ROConfiguredFeatures.init();
            ROPlacements.init();
            ROConfiguredFeatures.init2();
            ROPlacements.init2();
        });
    }
}
