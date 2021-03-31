package com.eerussianguy.rainbowoaks;

import java.util.function.Supplier;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

@SuppressWarnings("unused")
public class RORegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> RAINBOW_LOG = register("rainbow_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RAINBOW_LEAVES = register("rainbow_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(RORegistry::ocelotOrParrot).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false)));

    private static Boolean ocelotOrParrot(BlockState state, IBlockReader world, BlockPos pos, EntityType<?> entityType)
    {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier)
    {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
        return block;
    }
}
