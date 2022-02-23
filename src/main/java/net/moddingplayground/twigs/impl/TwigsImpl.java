package net.moddingplayground.twigs.impl;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.moddingplayground.frame.api.util.InitializationLogger;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.api.registry.TwigsRegistry;
import net.moddingplayground.twigs.api.sound.TwigsSoundEvents;
import net.moddingplayground.twigs.api.world.gen.feature.TwigsConfiguredFeatures;
import net.moddingplayground.twigs.api.world.gen.feature.TwigsPlacedFeatures;
import net.moddingplayground.twigs.impl.entity.TwigsEntityType;

public class TwigsImpl implements Twigs, ModInitializer {
    private static TwigsImpl instance = null;

    protected final InitializationLogger initializer;
    protected ItemGroup itemGroup = null;

    public TwigsImpl() {
        this.initializer = new InitializationLogger(LOGGER, MOD_NAME);
        instance = this;
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onInitialize() {
        this.initializer.start();

        this.itemGroup = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(TwigsBlocks.TWIG));

        Reflection.initialize(
            TwigsRegistry.class, TwigsSoundEvents.class,
            TwigsBlocks.class, TwigsEntityType.class,
            TwigsConfiguredFeatures.class, TwigsPlacedFeatures.class
        );

        this.initializer.finish();
    }

    public ItemGroup getItemGroup() {
        return this.itemGroup;
    }

    public static TwigsImpl getInstance() {
        return instance;
    }
}
