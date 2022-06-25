package net.moddingplayground.twigs.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

@SuppressWarnings("deprecation")
public class PillarOxidizableBlock extends PillarBlock implements Oxidizable {
    private final OxidationLevel level;

    public PillarOxidizableBlock(OxidationLevel level, Settings settings) {
        super(settings);
        this.level = level;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return this.level;
    }
}
