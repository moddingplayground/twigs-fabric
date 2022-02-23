package net.moddingplayground.twigs.api.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

@SuppressWarnings("deprecation")
public class QuadPoleBlock extends PillarBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final float MIN_BOUND = 5.0f;
    public static final float MAX_BOUND = 11.0f;
    public static final VoxelShape Y_SHAPE = createCuboidShape(MIN_BOUND, 0.0, MIN_BOUND, MAX_BOUND, 16.0, MAX_BOUND);
    public static final VoxelShape Z_SHAPE = createCuboidShape(MIN_BOUND, MIN_BOUND, 0.0, MAX_BOUND, MAX_BOUND, 16.0);
    public static final VoxelShape X_SHAPE = createCuboidShape(0.0, MIN_BOUND, MIN_BOUND, 16.0, MAX_BOUND, MAX_BOUND);

    public static final float MIN_BOUND_COLL = 6.5f;
    public static final float MAX_BOUND_COLL = 9.5f;
    public static final VoxelShape Y_SHAPE_COLL = createCuboidShape(MIN_BOUND_COLL, 0.0, MIN_BOUND_COLL, MAX_BOUND_COLL, 16.0, MAX_BOUND_COLL);
    public static final VoxelShape Z_SHAPE_COLL = createCuboidShape(MIN_BOUND_COLL, MIN_BOUND_COLL, 0.0, MAX_BOUND_COLL, MAX_BOUND_COLL, 16.0);
    public static final VoxelShape X_SHAPE_COLL = createCuboidShape(0.0, MIN_BOUND_COLL, MIN_BOUND_COLL, 16.0, MAX_BOUND_COLL, MAX_BOUND_COLL);

    public QuadPoleBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false).with(AXIS, Direction.Axis.Y));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return switch (state.get(AXIS)) {
            case X -> X_SHAPE;
            case Y -> Y_SHAPE;
            case Z -> Z_SHAPE;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return switch (state.get(AXIS)) {
            case X -> X_SHAPE_COLL;
            case Y -> Y_SHAPE_COLL;
            case Z -> Z_SHAPE_COLL;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState state = super.getPlacementState(ctx);
        return (state == null ? this.getDefaultState() : state).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(WATERLOGGED));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
}
