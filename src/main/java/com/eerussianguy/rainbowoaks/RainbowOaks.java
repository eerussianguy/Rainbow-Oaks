package com.eerussianguy.rainbowoaks;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

@Mod(MOD_ID)
public class RainbowOaks
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String MOD_ID = "rainbowoaks";

    public static ResourceLocation identifier(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }

    public RainbowOaks()
    {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        RORegistry.BLOCKS.register(modBus);
        RORegistry.ITEMS.register(modBus);

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            ClientEventHandler.init();
        }
    }
}
