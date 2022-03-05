//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.moddingplayground.twigs.init;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class TwigsConfiguredFeatures {

    public static final RegistryEntry<? extends ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TWIG = ConfiguredFeatures.register("patch_twig", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(TwigsBlocks.TWIG.getDefaultState().getBlock()), 16));
    public static final RegistryEntry<? extends ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_PEBBLE = ConfiguredFeatures.register("patch_pebble", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(TwigsBlocks.PEBBLE.getDefaultState().getBlock()), 8));


    public TwigsConfiguredFeatures() {
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
}
