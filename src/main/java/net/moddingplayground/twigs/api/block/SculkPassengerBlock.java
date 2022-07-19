package net.moddingplayground.twigs.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.moddingplayground.twigs.api.sound.TwigsSoundEvents;
import net.moddingplayground.twigs.api.tag.TwigsBlockTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
public class SculkPassengerBlock extends OreBlock {
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty ADHERES = TwigsProperties.ADHERES;

    public static final BiFunction<Direction, Long, List<Direction>> BIASED_DIRECTIONS = Util.memoize((direction, seed) -> {
        if (direction == null) {
            // get a list of random directions, based on the world seed
            Random random = Random.create(seed);
            return Util.copyShuffled(Direction.values(), random);
        }

        // create a list of directions, with the given direction
        // and its opposite at the start of the list
        List<Direction> directions = new ArrayList<>(Arrays.stream(Direction.values()).toList());
        directions.add(0, direction.getOpposite());
        directions.add(0, direction);
        return directions;
    });

    public SculkPassengerBlock(Settings settings) {
        super(settings, ConstantIntProvider.create(1));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(ADHERES, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite()).with(ADHERES, true);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        this.scheduleMovement(world, pos, world.random);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.scheduledTick(state, world, pos, random);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.findSculkDirection(state.get(ADHERES) ? state.get(FACING) : null, world, pos).ifPresent(direction -> this.moveSculkPassenger(world, pos, state, direction));
        this.scheduleMovement(world, pos, world.random);
    }

    public void scheduleMovement(World world, BlockPos pos, Random random) {
        world.createAndScheduleBlockTick(pos, this, MathHelper.nextInt(random, 20, 40));
    }

    public void moveSculkPassenger(ServerWorld world, BlockPos rootPos, BlockState rootState, Direction direction) {
        BlockPos pos = rootPos.offset(direction);
        BlockState state = world.getBlockState(pos);

        world.setBlockState(rootPos, state, Block.NOTIFY_ALL);
        world.setBlockState(pos, rootState.with(FACING, direction), Block.NOTIFY_ALL);

        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(rootState));

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        world.spawnParticles(ParticleTypes.SCULK_CHARGE_POP, x + 0.5, y + 1.15, z + 0.5, 2, 0.2, 0.0, 0.2, 0.0);
        world.playSound(null, x, y, z, TwigsSoundEvents.BLOCK_SCULK_PASSENGER_MOVE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    public Optional<Direction> findSculkDirection(@Nullable Direction facing, ServerWorld world, BlockPos pos) {
        List<Direction> list = BIASED_DIRECTIONS.apply(facing, world.getSeed())
                                                .stream()
                                                .filter(direction -> {
                                                    BlockState state = world.getBlockState(pos.offset(direction));
                                                    return this.isStateAvailableToMove(state);
                                                })
                                                .sorted((d1, d2) -> Boolean.compare(this.isDirectionThis(d1, world, pos), this.isDirectionThis(d2, world, pos)))
                                                .toList();

        if (list.isEmpty()) return Optional.empty(); // return immediately if empty
        if (facing != null) return Optional.of(list.get(0)); // return immediately if adheres

        Random random = world.random;
        int bound = random.nextInt(list.size()) + 1;
        return Optional.of(list.get(random.nextInt(bound)));
    }

    public boolean isStateAvailableToMove(BlockState state) {
        return state.isIn(TwigsBlockTags.SCULK_PASSENGER_MOVES_TO);
    }

    public boolean isDirectionThis(Direction direction, ServerWorld world, BlockPos pos) {
        return world.getBlockState(pos.offset(direction)).isOf(this);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ADHERES);
    }
}
