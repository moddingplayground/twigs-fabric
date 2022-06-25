package net.moddingplayground.twigs.impl.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Blocks;
import net.moddingplayground.frame.api.loottables.v0.LootTableAdditions;
import net.moddingplayground.twigs.api.Twigs;
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

        LootTableAdditions.of(Blocks.BAMBOO).defaulted(Twigs.MOD_ID).register();
    }
}
