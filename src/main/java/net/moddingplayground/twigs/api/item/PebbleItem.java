package net.moddingplayground.twigs.api.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.moddingplayground.twigs.api.entity.PebbleEntity;
import net.moddingplayground.twigs.api.sound.TwigsSoundEvents;

public class PebbleItem extends BlockItem {
    public PebbleItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), TwigsSoundEvents.ENTITY_PEBBLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            PebbleEntity entity = new PebbleEntity(world, player);
            entity.setItem(stack);
            entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(entity);
        }
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.getAbilities().creativeMode) stack.decrement(1);
        return TypedActionResult.success(stack, world.isClient);
    }
}
