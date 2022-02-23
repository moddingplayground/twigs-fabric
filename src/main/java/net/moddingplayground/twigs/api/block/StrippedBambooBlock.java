package net.moddingplayground.twigs.api.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class StrippedBambooBlock extends QuadPoleBlock {
    public static final BooleanProperty FROM_BAMBOO = TwigsProperties.FROM_BAMBOO;

    public StrippedBambooBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(AXIS, Direction.Axis.Y).with(FROM_BAMBOO, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(FROM_BAMBOO));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Vec3d offset = state.getModelOffset(world, pos);
        return super.getOutlineShape(state, world, pos, ctx).offset(offset.x, offset.y, offset.z);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Vec3d offset = state.getModelOffset(world, pos);
        return super.getCollisionShape(state, world, pos, ctx).offset(offset.x, offset.y, offset.z);
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return OffsetType.XZ;
    }
}
