package net.moddingplayground.twigs.mixin.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.moddingplayground.twigs.api.block.StrippedBambooBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin extends State<Block, BlockState> {
    @Shadow public abstract Block getBlock();

    private AbstractBlockStateMixin(Block owner, ImmutableMap<Property<?>, Comparable<?>> entries, MapCodec<BlockState> codec) {
        super(owner, entries, codec);
    }

    @Inject(method = "getModelOffset", at = @At("HEAD"), cancellable = true)
    private void onGetModelOffset(BlockView world, BlockPos pos, CallbackInfoReturnable<Vec3d> cir) {
        if (this.getBlock() instanceof StrippedBambooBlock && !this.get(StrippedBambooBlock.FROM_BAMBOO)) cir.setReturnValue(Vec3d.ZERO);
    }
}
