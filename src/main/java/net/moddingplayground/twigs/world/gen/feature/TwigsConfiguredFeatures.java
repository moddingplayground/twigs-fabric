package net.moddingplayground.twigs.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TwigsBlocks;

public class TwigsConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> PATCH_TWIG = register("patch_twig", Feature.RANDOM_PATCH.configure(randomPatch(BlockStateProvider.of(TwigsBlocks.TWIG.getDefaultState().getBlock()), 3)));
    public static final ConfiguredFeature<?, ?> PATCH_PEBBLE = register("patch_pebble", Feature.RANDOM_PATCH.configure(randomPatch(BlockStateProvider.of(TwigsBlocks.PEBBLE.getDefaultState().getBlock()), 2)));

    private static RandomPatchFeatureConfig randomPatch(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }

    private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Twigs.MOD_ID, id), feature);
    }
}
