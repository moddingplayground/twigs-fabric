package net.moddingplayground.twigs.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class AmethystSlabBlock extends SlabBlock {
    public AmethystSlabBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        Blocks.AMETHYST_BLOCK.onProjectileHit(world, state, hit, projectile);
    }
}
