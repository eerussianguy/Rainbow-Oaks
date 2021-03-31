package com.eerussianguy.rainbowoaks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

@Mod.EventBusSubscriber(value=Dist.CLIENT, modid=MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler
{
    @SubscribeEvent
    public static void onBlockColors(ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register(new BlockColorRainbowLeaves(), RORegistry.RAINBOW_LEAVES.get());
    }

    @SubscribeEvent
    public static void onItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register(new ItemColorRainbowLeaves(), RORegistry.RAINBOW_LEAVES.get().asItem());
    }

    public static class ItemColorRainbowLeaves implements IItemColor
    {
        @Override
        public int getColor(@Nonnull ItemStack stack, int tintIndex)
        {
            return 0;
        }
    }

    public static class BlockColorRainbowLeaves implements IBlockColor
    {
        @Override
        public int getColor(@Nonnull BlockState state, @Nullable IBlockDisplayReader worldIn, @Nullable BlockPos pos, int tintindex)
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
            else return FoliageColors.getDefaultColor();
        }

    }
}
