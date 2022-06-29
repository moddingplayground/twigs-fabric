package net.moddingplayground.twigs.impl.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.moddingplayground.twigs.api.entity.PebbleEntity;
import net.moddingplayground.twigs.api.entity.TwigsBoatTypes;
import net.moddingplayground.twigs.api.item.TwigsItems;

public final class TwigsItemsImpl implements TwigsItems, ModInitializer {
    @Override
    public void onInitialize() {
        FuelRegistry fuels = FuelRegistry.INSTANCE;
        fuels.add(STRIPPED_BAMBOO, 50);
        fuels.add(BUNDLED_BAMBOO, 450);
        fuels.add(STRIPPED_BUNDLED_BAMBOO, 450);

        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(BAMBOO_LEAVES, 0.5F);
        composting.add(TWIG, 0.3F);

        DispenserBlock.registerBehavior(STRIPPED_BAMBOO_BOAT, new BoatDispenserBehavior(TwigsBoatTypes.STRIPPED_BAMBOO));
        DispenserBlock.registerBehavior(STRIPPED_BAMBOO_CHEST_BOAT, new BoatDispenserBehavior(TwigsBoatTypes.STRIPPED_BAMBOO, true));

        DispenserBlock.registerBehavior(PEBBLE, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return Util.make(new PebbleEntity(world, position.getX(), position.getY(), position.getZ()), entity -> entity.setItem(stack));
            }
        });
    }
}
