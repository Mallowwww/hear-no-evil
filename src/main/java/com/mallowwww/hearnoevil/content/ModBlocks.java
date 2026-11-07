package com.mallowwww.hearnoevil.content;

import com.mallowwww.hearnoevil.RadioMod;
import com.mallowwww.hearnoevil.content.block.DepletedRedstoneOre;
import com.mallowwww.hearnoevil.content.block.RedstoneSeedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks
{
    public static DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.createBlocks(RadioMod.MOD_ID);

    public static DeferredBlock<DepletedRedstoneOre> DEPLETED_REDSTONE_ORE = (DeferredBlock<DepletedRedstoneOre>) MOD_BLOCKS.register("depleted_redstone_ore", () -> new DepletedRedstoneOre(BlockBehaviour.Properties.of()));
    public static DeferredBlock<RedstoneSeedBlock> REDSTONE_SEED = (DeferredBlock<RedstoneSeedBlock>) MOD_BLOCKS.register("redstone_seed", () -> new RedstoneSeedBlock(BlockBehaviour.Properties.of()));
}
