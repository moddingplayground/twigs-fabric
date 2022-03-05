package net.moddingplayground.twigs.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class LampBlock extends Block {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public LampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, true));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world,BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
        final boolean wasLit = state.get(LIT);

        world.setBlockState(pos, state.with(LIT, !wasLit));
        playSound(player, !wasLit);

        return ActionResult.SUCCESS;
    }

    private void playSound(PlayerEntity player, boolean isLit) {
        if (!player.world.isClient) {
            player.playSound(
                    isLit ? SoundEvents.ITEM_FLINTANDSTEEL_USE : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,
                    SoundCategory.BLOCKS,
                    0.3f,
                    isLit ? 0.6f : 0.5f
            );
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

}
