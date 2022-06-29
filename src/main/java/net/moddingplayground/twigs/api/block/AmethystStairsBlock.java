package net.moddingplayground.twigs.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class AmethystStairsBlock extends StairsBlock {
    public AmethystStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        Blocks.AMETHYST_BLOCK.onProjectileHit(world, state, hit, projectile);
    }
}
