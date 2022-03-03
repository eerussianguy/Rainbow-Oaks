package com.eerussianguy.rainbowoaks;

import java.util.function.Supplier;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.eerussianguy.rainbowoaks.RainbowOaks.MOD_ID;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("unused")
public class RORegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> RAINBOW_LOG = register("rainbow_log", () -> new RORPBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAINBOW_LEAVES = register("rainbow_leaves", () -> new ROLBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(RORegistry::ocelotOrParrot).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAINBOW_SAPLING = register("rainbow_sapling", () -> new SaplingBlock(new RainbowTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), CreativeModeTab.TAB_DECORATIONS);

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> entityType)
    {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, CreativeModeTab tab)
    {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return block;
    }
}
