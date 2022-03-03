package com.eerussianguy.rainbowoaks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

public class ClientEventHandler
{
    public static void init()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ClientEventHandler::clientSetup);
        bus.addListener(ClientEventHandler::onBlockColors);
        bus.addListener(ClientEventHandler::onItemColors);
    }

    private static void clientSetup(final FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(RORegistry.RAINBOW_SAPLING.get(), RenderType.cutout());
    }

    private static void onBlockColors(ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register(new BlockColorRainbowLeaves(), RORegistry.RAINBOW_LEAVES.get());
    }

    private static void onItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register(new ItemColorRainbowLeaves(), RORegistry.RAINBOW_LEAVES.get().asItem());
    }

    public static class ItemColorRainbowLeaves implements ItemColor
    {
        @Override
        public int getColor(@Nonnull ItemStack stack, int tintIndex)
        {
            int red = 0;
            int green = 0;
            int blue = 0;

            for (int i = -1; i <= 1; ++i)
            {
                for (int j = -1; j <= 1; ++j)
                {
                    int averageColor = FoliageColor.getDefaultColor();
                    red += (averageColor & 16711680) >> 16;
                    green += (averageColor & 65280) >> 8;
                    blue += averageColor & 255;
                }
            }

            red = stack.getCount() * 64;
            if ((red & 256) != 0)
            {
                red = 255 - (red & 255);
            }
            red &= 255;

            blue = stack.getCount() * 32;
            if ((blue & 256) != 0)
            {
                blue = 255 - (blue & 255);
            }
            blue ^= 255;

            green = stack.getCount();
            if ((green & 256) != 0)
            {
                green = 255 - (green & 255);
            }
            green &= 255;

            return red << 16 | blue << 8 | green;
        }
    }

    public static class BlockColorRainbowLeaves implements BlockColor
    {
        @Override
        public int getColor(@Nonnull BlockState state, @Nullable BlockAndTintGetter worldIn, @Nullable BlockPos pos, int tintindex)
        {
            if (worldIn != null && pos != null)
            {
                int red = 0;
                int green = 0;
                int blue = 0;

                for (int i = -1; i <= 1; ++i)
                {
                    for (int j = -1; j <= 1; ++j)
                    {
                        int averageColor = BiomeColors.getAverageFoliageColor(worldIn, pos);
                        red += (averageColor & 16711680) >> 16;
                        green += (averageColor & 65280) >> 8;
                        blue += averageColor & 255;
                    }
                }

                red = pos.getX() * 32 + pos.getY() * 16;
                if ((red & 256) != 0)
                {
                    red = 255 - (red & 255);
                }
                red &= 255;

                blue = pos.getY() * 32 + pos.getZ() * 16;
                if ((blue & 256) != 0)
                {
                    blue = 255 - (blue & 255);
                }
                blue ^= 255;

                green = pos.getX() * 16 + pos.getZ() * 32;
                if ((green & 256) != 0)
                {
                    green = 255 - (green & 255);
                }
                green &= 255;

                return red << 16 | blue << 8 | green;
            }
            else return FoliageColor.getDefaultColor();
        }

    }
}
