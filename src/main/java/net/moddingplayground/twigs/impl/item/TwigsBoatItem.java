package net.moddingplayground.twigs.impl.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;
import net.moddingplayground.twigs.impl.entity.TwigsBoatEntity;
import net.moddingplayground.twigs.mixin.BoatItemAccessor;

import java.util.List;

public class TwigsBoatItem extends Item {
    private final WoodSet wood;

    public TwigsBoatItem(WoodSet wood, Settings settings) {
        super(settings);
        this.wood = wood;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.ANY);

        if (hit.getType() == HitResult.Type.MISS) return TypedActionResult.pass(stack);

        Vec3d rot = player.getRotationVec(1.0f);
        List<Entity> nearbyRiders = world.getOtherEntities(player, player.getBoundingBox().stretch(rot.multiply(5.0)).expand(1.0), BoatItemAccessor.getRIDERS());
        if (!nearbyRiders.isEmpty()) {
            Vec3d pos = player.getEyePos();
            for (Entity entity : nearbyRiders) {
                Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                if (!box.contains(pos)) continue;
                return TypedActionResult.pass(stack);
            }
        }

        if (hit.getType() == HitResult.Type.BLOCK) {
            TwigsBoatEntity entity = new TwigsBoatEntity(world, hit.getPos().x, hit.getPos().y, hit.getPos().z);
            entity.setWoodType(this.wood);
            entity.setYaw(player.getYaw());
            if (!world.isSpaceEmpty(entity, entity.getBoundingBox())) return TypedActionResult.fail(stack);

            if (!world.isClient) {
                world.spawnEntity(entity);
                world.emitGameEvent(player, GameEvent.ENTITY_PLACE, new BlockPos(hit.getPos()));
                if (!player.getAbilities().creativeMode) stack.decrement(1);
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(stack, world.isClient());
        }

        return TypedActionResult.pass(stack);
    }
}
