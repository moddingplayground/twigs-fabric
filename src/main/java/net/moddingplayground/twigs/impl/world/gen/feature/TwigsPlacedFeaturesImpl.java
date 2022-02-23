package net.moddingplayground.twigs.impl.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.moddingplayground.twigs.api.tag.TwigsBiomeTags;
import net.moddingplayground.twigs.api.world.gen.feature.TwigsPlacedFeatures;

import java.util.function.Consumer;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.*;
import static net.minecraft.world.gen.GenerationStep.Feature.*;

public class TwigsPlacedFeaturesImpl implements TwigsPlacedFeatures {
    @Override
    public void onInitialize() {
        tryPlace(PATCH_TWIG, TwigsBiomeTags.SPAWNS_TWIG, VEGETAL_DECORATION);
        tryPlace(PATCH_PEBBLE, TwigsBiomeTags.SPAWNS_PEBBLE, VEGETAL_DECORATION);
        tryPlace(ORE_RHYOLITE_LOWER, TwigsBiomeTags.SPAWNS_RHYOLITE, UNDERGROUND_ORES);
        tryPlace(ORE_BLOODSTONE_NETHER, TwigsBiomeTags.SPAWNS_BLOODSTONE, UNDERGROUND_DECORATION);
        tryPlace(ORE_SCHIST_UPPER, TwigsBiomeTags.SPAWNS_SCHIST, UNDERGROUND_ORES);
        tryPlace(ORE_SCHIST_LOWER, TwigsBiomeTags.SPAWNS_SCHIST, UNDERGROUND_ORES);
    }

    private static void tryPlace(PlacedFeature feature, Consumer<RegistryKey<PlacedFeature>> action) {
        BuiltinRegistries.PLACED_FEATURE.getKey(feature).ifPresent(action);
    }

    private static void tryPlace(PlacedFeature feature, Tag<Biome> tag, GenerationStep.Feature step) {
        tryPlace(feature, key -> addFeature(BiomeSelectors.tag(tag), step, key));
    }
}
