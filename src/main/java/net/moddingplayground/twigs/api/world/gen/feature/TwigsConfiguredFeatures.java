package net.moddingplayground.twigs.api.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.*;

public interface TwigsConfiguredFeatures {
    ConfiguredFeature<?, ?> PATCH_TWIG = register("patch_twig", Feature.RANDOM_PATCH.configure(randomPatch(BlockStateProvider.of(States.TWIG), 3)));
    ConfiguredFeature<?, ?> PATCH_PEBBLE = register("patch_pebble", Feature.RANDOM_PATCH.configure(randomPatch(BlockStateProvider.of(States.PEBBLE), 2)));

    ConfiguredFeature<?, ?> ORE_RHYOLITE = register("ore_rhyolite", Feature.ORE.configure(new OreFeatureConfig(BASE_STONE_OVERWORLD, States.RHYOLITE, 45)));
    ConfiguredFeature<?, ?> ORE_SCHIST = register("ore_schist", Feature.ORE.configure(new OreFeatureConfig(BASE_STONE_OVERWORLD, States.SCHIST, 64)));
    ConfiguredFeature<?, ?> ORE_BLOODSTONE = register("ore_bloodstone", Feature.ORE.configure(new OreFeatureConfig(BASE_STONE_NETHER, States.BLOODSTONE, 64)));

    private static RandomPatchFeatureConfig randomPatch(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }

    private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Twigs.MOD_ID, id), feature);
    }

    class States {
        public static final BlockState TWIG = TwigsBlocks.TWIG.getDefaultState();
        public static final BlockState PEBBLE = TwigsBlocks.PEBBLE.getDefaultState();
        public static final BlockState RHYOLITE = TwigsBlocks.RHYOLITE.getDefaultState();
        public static final BlockState SCHIST = TwigsBlocks.SCHIST.getDefaultState();
        public static final BlockState BLOODSTONE = TwigsBlocks.BLOODSTONE.getDefaultState();
    }
}
