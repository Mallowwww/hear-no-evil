package com.mallowwww.hearnoevil.content.block;

import com.mallowwww.hearnoevil.content.ModBlocks;
import com.mallowwww.hearnoevil.content.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RedstoneSeedBlock extends RodBlock
{
    public static final MapCodec<RedstoneSeedBlock> CODEC = RedstoneSeedBlock.simpleCodec(RedstoneSeedBlock::new);
    public static final IntegerProperty GROWTH = IntegerProperty.create("growth", 0, 5);

    public RedstoneSeedBlock(Properties properties)
    {
        super(properties
                .randomTicks()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)
                .replaceable()
                .noCollission()
                .isRedstoneConductor((BlockState s, BlockGetter g, BlockPos p)->false)
                .isViewBlocking((BlockState s, BlockGetter g, BlockPos p)->false));

        this.registerDefaultState(this.defaultBlockState().setValue(GROWTH, 0).setValue(FACING, Direction.UP));
    }


    @Override
    protected @NotNull MapCodec<? extends RodBlock> codec()
    {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        builder.add(GROWTH);
    }

    public BlockState getStateForAge(int i)
    {
        return this.defaultBlockState().setValue(GROWTH, i);
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, @NotNull RandomSource randomSource)
    {
        var dir = blockState.getValue(FACING);
        var supportPos = blockPos.relative(dir.getOpposite());
        var supportBlock = serverLevel.getBlockState(supportPos);

        if(supportBlock.is(Blocks.REDSTONE_ORE))
        {
            grow(blockState, serverLevel, blockPos, dir);
            serverLevel.setBlockAndUpdate(supportPos, ModBlocks.DEPLETED_REDSTONE_ORE.get().defaultBlockState());
        }
        else if(supportBlock.is(ModBlocks.DEPLETED_REDSTONE_ORE) && !isFullyGrown(blockState) && DepletedRedstoneOre.hasRedstone(supportBlock))
        {
            grow(blockState, serverLevel, blockPos, dir);
            if(randomSource.nextInt(2) == 0) DepletedRedstoneOre.deplete(supportPos, supportBlock, serverLevel);
        }
    }

    private void grow(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Direction dir)
    {
        var current = blockState.getValue(GROWTH);
        var i = Math.min(5, current + 1);
        serverLevel.setBlock(blockPos, this.getStateForAge(i).setValue(FACING, dir), 2);
    }

    private boolean isFullyGrown(BlockState blockState)
    {
        return blockState.getValue(GROWTH) >= 5;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState blockState)
    {
        return blockState.getValue(GROWTH) < 5;
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos)
    {
        var direction = blockState.getValue(FACING);
        var supportPos = blockPos.relative(direction.getOpposite());
        var supportBlock = levelReader.getBlockState(supportPos).getBlock();
        return supportBlock == ModBlocks.DEPLETED_REDSTONE_ORE.get() || supportBlock == Blocks.REDSTONE_ORE;
    }

    @Override
    protected BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2)
    {
        if (direction == blockState.getValue(FACING).getOpposite() && !blockState.canSurvive(levelAccessor, blockPos))
        {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getClickedFace());
    }

    @Override
    protected @NotNull BlockState rotate(BlockState blockState, Rotation rotation)
    {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    protected @NotNull BlockState mirror(BlockState blockState, Mirror mirror)
    {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected @NotNull List<ItemStack> getDrops(BlockState blockState, LootParams.@NotNull Builder builder)
    {
        int growth = blockState.getValue(GROWTH);

        List<ItemStack> list = new ArrayList<>(List.of());

        list.add(new ItemStack(ModBlocks.REDSTONE_SEED.get()));

        for (var i = 0; i < growth; i++)
        {
            list.add(new ItemStack(ModItems.REDSTONE_FIBERS.get()));
        }

        return list;
    }
}
