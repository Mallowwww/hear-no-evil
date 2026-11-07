package com.mallowwww.hearnoevil.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.ToIntFunction;

public class DepletedRedstoneOre extends Block
{
    public static final IntegerProperty REMAINING = IntegerProperty.create("remaining", 0, 5);
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = blockState -> blockState.getValue(REMAINING);

    public DepletedRedstoneOre(Properties properties)
    {
        super(properties
                .sound(SoundType.STONE)
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .lightLevel(DepletedRedstoneOre.LIGHT_EMISSION)
                .strength(1,1));
        this.registerDefaultState(this.stateDefinition.any().setValue(REMAINING, 5));
    }

    public static void deplete(BlockPos blockPos, BlockState currentState, ServerLevel level)
    {
        int currentRemaining = currentState.getValue(REMAINING);
        var newRemaining =  Math.max(0, currentRemaining - 1);

        level.setBlockAndUpdate(blockPos, currentState.setValue(REMAINING, newRemaining));

        RandomSource randomSource = level.random;

        for (Direction direction : Direction.values())
        {
            for (int i = 0; i < currentRemaining * 2; i++)
            {
                //BlockPos facePos = blockPos.relative(direction);
                //if (level.getBlockState(facePos).isSolidRender()) continue;

                Direction.Axis axis = direction.getAxis();
                double rx = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getStepX() : (double)randomSource.nextFloat();
                double ry = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction.getStepY() : (double)randomSource.nextFloat();
                double rz = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getStepZ() : (double)randomSource.nextFloat();

                double x = (double)blockPos.getX() + rx;
                double y = (double)blockPos.getY() + ry;
                double z = (double)blockPos.getZ() + rz;
                //may want to replace this with a single packet later
                level.sendParticles(DustParticleOptions.REDSTONE, x, y, z, 1, 0.0, 0.0, 0.0, 1);
            }
        }
    }

    public static boolean hasRedstone(BlockState currentState)
    {
        return currentState.getValue(REMAINING) > 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(REMAINING);
    }
}
