package net.moddingplayground.twigs.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.moddingplayground.twigs.api.sound.TwigsSoundEvents;

@SuppressWarnings("deprecation")
public class LampBlock extends Block {
    public static final BooleanProperty LIT = Properties.LIT;

    public LampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, true));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            boolean lit = !state.get(LIT);
            world.setBlockState(pos, state.with(LIT, lit));
            world.playSound(null, pos, lit ? TwigsSoundEvents.BLOCK_LAMP_LIGHT : TwigsSoundEvents.BLOCK_LAMP_EXTINGUISH, SoundCategory.PLAYERS, 0.3f, 2.0f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(LIT));
    }
}
