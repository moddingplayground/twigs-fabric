package net.moddingplayground.twigs.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import java.util.function.Function;

@SuppressWarnings("deprecation")
public class SculkPassengerBlock extends OreBlock {
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty ADHERES = TwigsProperties.ADHERES;

    public static final Function<Direction, List<Direction>> BIASED_DIRECTIONS = Util.memoize(direction -> {
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
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        this.scheduleMovement(world, pos, world.random);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.scheduledTick(state, world, pos, random);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(ADHERES)) {
            this.findSculkDirection(state.get(FACING), world, pos).ifPresent(direction -> this.moveSculkPassenger(world, pos, state, direction));
        } else this.moveSculkPassenger(world, pos, state, getRandomDirection(random));
        this.scheduleMovement(world, pos, world.random);
    }

    public void scheduleMovement(World world, BlockPos pos, Random random) {
        world.createAndScheduleBlockTick(pos, this, MathHelper.nextInt(random, 20, 40));
    }

    public void moveSculkPassenger(ServerWorld world, BlockPos pos, BlockState state, Direction direction) {
        BlockPos movePos = pos.offset(direction);
        BlockState moveState = world.getBlockState(movePos);
        if (this.isStateAvailableToMove(moveState)) {
            world.setBlockState(pos, moveState, Block.NOTIFY_ALL);
            world.setBlockState(movePos, state.with(FACING, direction), Block.NOTIFY_ALL);

            world.emitGameEvent(GameEvent.BLOCK_CHANGE, movePos, GameEvent.Emitter.of(state));

            int x = movePos.getX();
            int y = movePos.getY();
            int z = movePos.getZ();
            world.spawnParticles(ParticleTypes.SCULK_CHARGE_POP, x + 0.5, y + 1.15, z + 0.5, 2, 0.2, 0.0, 0.2, 0.0);
            world.playSound(null, x, y, z, TwigsSoundEvents.BLOCK_SCULK_PASSENGER_MOVE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }

    public static Direction getRandomDirection(Random random) {
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }

    public boolean isStateAvailableToMove(BlockState state) {
        return state.isIn(TwigsBlockTags.SCULK_PASSENGER_MOVES_TO);
    }

    public Optional<Direction> findSculkDirection(Direction facing, ServerWorld world, BlockPos pos) {
        for (Direction direction : BIASED_DIRECTIONS.apply(facing)) {
            BlockState state = world.getBlockState(pos.offset(direction));
            if (this.isStateAvailableToMove(state)) return Optional.of(direction);
        }

        return Optional.empty();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(FACING, ADHERES));
    }
}
