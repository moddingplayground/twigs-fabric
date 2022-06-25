package net.moddingplayground.twigs.api.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.*;

public interface TwigsConfiguredFeatures {
    RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TWIG = register("patch_twig", Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.of(States.TWIG), 3));
    RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_PEBBLE = register("patch_pebble", Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.of(States.PEBBLE), 2));
    RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_SEA_SHELL = register("patch_sea_shell", Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.of(States.SEA_SHELL), 2));

    RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_RHYOLITE = register("ore_rhyolite", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, States.RHYOLITE, 45));
    RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SCHIST = register("ore_schist", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, States.SCHIST, 64));
    RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_BLOODSTONE = register("ore_bloodstone", Feature.ORE, new OreFeatureConfig(BASE_STONE_NETHER, States.BLOODSTONE, 64));

    private static RandomPatchFeatureConfig randomPatch(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    private static <C extends FeatureConfig, F extends Feature<C>> RegistryEntry<ConfiguredFeature<C, ?>> register(String id, F feature, C config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Twigs.MOD_ID, id).toString(), new ConfiguredFeature<>(feature, config));
    }

    class States {
        public static final BlockState TWIG = TwigsBlocks.TWIG.getDefaultState();
        public static final BlockState PEBBLE = TwigsBlocks.PEBBLE.getDefaultState();
        public static final BlockState SEA_SHELL = TwigsBlocks.SEA_SHELL.getDefaultState();
        public static final BlockState RHYOLITE = TwigsBlocks.RHYOLITE.getDefaultState();
        public static final BlockState SCHIST = TwigsBlocks.SCHIST.getDefaultState();
        public static final BlockState BLOODSTONE = TwigsBlocks.BLOODSTONE.getDefaultState();
    }
}
