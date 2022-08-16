package net.moddingplayground.twigs.impl;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.moddingplayground.frame.api.util.InitializationLogger;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.api.block.TwigsSignTypes;
import net.moddingplayground.twigs.api.config.TwigsWorldGenReplacementsConfig;
import net.moddingplayground.twigs.api.entity.TwigsEntityType;
import net.moddingplayground.twigs.api.item.TwigsItemGroups;
import net.moddingplayground.twigs.api.item.TwigsItems;
import net.moddingplayground.twigs.api.particle.TwigsParticleTypes;
import net.moddingplayground.twigs.api.sound.TwigsSoundEvents;
import net.moddingplayground.twigs.api.world.gen.feature.TwigsConfiguredFeatures;
import net.moddingplayground.twigs.api.world.gen.feature.TwigsPlacedFeatures;
import net.moddingplayground.twigs.impl.command.CalculateSculkPassengerBiasCommand;

public final class TwigsImpl implements Twigs, ModInitializer {
    private final InitializationLogger initializer;

    public TwigsImpl() {
        this.initializer = new InitializationLogger(LOGGER, MOD_NAME);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onInitialize() {
        this.initializer.start();

        Reflection.initialize(
            TwigsSoundEvents.class,
            TwigsParticleTypes.class,

            TwigsSignTypes.class,
            TwigsBlocks.class,

            TwigsItemGroups.class,
            TwigsItems.class,
            TwigsEntityType.class,

            TwigsConfiguredFeatures.class,
            TwigsPlacedFeatures.class,

            TwigsWorldGenReplacementsConfig.class
        );

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> CalculateSculkPassengerBiasCommand.register(dispatcher));

        this.initializer.finish();
    }
}
