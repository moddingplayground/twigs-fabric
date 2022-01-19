package net.moddingplayground.twigs.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.moddingplayground.twigs.block.TwigsBlocks;
import net.moddingplayground.twigs.block.wood.WoodBlock;
import net.moddingplayground.twigs.block.wood.WoodSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void onSupports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        BlockEntityType<?> type = BlockEntityType.class.cast(this);
        if (type == BlockEntityType.SIGN) {
            WoodSet set = TwigsBlocks.STRIPPED_BAMBOO_SET;
            if (state.isOf(set.get(WoodBlock.SIGN)) || state.isOf(set.get(WoodBlock.WALL_SIGN))) cir.setReturnValue(true);
        }
    }
}
