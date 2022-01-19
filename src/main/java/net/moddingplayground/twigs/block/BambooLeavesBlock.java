package net.moddingplayground.twigs.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.moddingplayground.twigs.tag.TwigsEntityTypeTags;

@SuppressWarnings("deprecation")
public class BambooLeavesBlock extends PlantBlock implements Waterloggable {
    public static final IntProperty LAYERS = TwigsProperties.LAYERS_1_4;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final VoxelShape SHAPE_ONE = Block.createCuboidShape(0, 0, 0, 16, 1, 16);
    public static final VoxelShape SHAPE_TWO = Block.createCuboidShape(0, 0, 0, 16, 2, 16);
    public static final VoxelShape SHAPE_THREE = Block.createCuboidShape(0, 0, 0, 16, 3, 16);
    public static final VoxelShape SHAPE_FOUR = Block.createCuboidShape(0, 0, 0, 16, 5, 16);

    public BambooLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LAYERS, 1).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(LAYERS, WATERLOGGED));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return switch (state.get(LAYERS)) {
            default -> SHAPE_ONE;
            case 2 -> SHAPE_TWO;
            case 3 -> SHAPE_THREE;
            case 4 -> SHAPE_FOUR;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState state = ctx.getWorld().getBlockState(pos);
        if (state.isOf(this)) {
            return state.cycle(LAYERS);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(pos);
            return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (state.get(WATERLOGGED)) world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    protected boolean canPlantOnTop(BlockState state, BlockView world, BlockPos pos) {
        return !state.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || state.isSideSolidFullSquare(world, pos, Direction.UP);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos posDown = pos.down();
        return this.canPlantOnTop(world.getBlockState(posDown), world, posDown);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        return (!ctx.shouldCancelInteraction() && ctx.getStack().isOf(this.asItem()) && state.get(LAYERS) < 4) || super.canReplace(state, ctx);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && state.get(LAYERS) > 2) {
            EntityType<?> type = entity.getType();
            if (!TwigsEntityTypeTags.BAMBOO_LEAVES_SLOW_IMMUNE.contains(type)) {
                entity.slowMovement(state, new Vec3d(0.75D, 1.0D, 0.75D));
            }
        }
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) && stateFrom.get(LAYERS) >= state.get(LAYERS);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
