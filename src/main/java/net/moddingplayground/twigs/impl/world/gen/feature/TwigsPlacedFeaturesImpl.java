package net.moddingplayground.twigs.impl.world.gen.feature;

import net.fabricmc.api.ModInitializer;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.moddingplayground.twigs.api.tag.TwigsBiomeTags;
import net.moddingplayground.twigs.api.world.gen.feature.TwigsPlacedFeatures;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.*;
import static net.fabricmc.fabric.api.biome.v1.BiomeSelectors.*;
import static net.minecraft.world.gen.GenerationStep.Feature.*;

public final class TwigsPlacedFeaturesImpl implements TwigsPlacedFeatures, ModInitializer {
    @Override
    public void onInitialize() {
        tryPlace(PATCH_TWIG, TwigsBiomeTags.SPAWNS_TWIG, VEGETAL_DECORATION);
        tryPlace(PATCH_PEBBLE, TwigsBiomeTags.SPAWNS_PEBBLE, VEGETAL_DECORATION);
        tryPlace(PATCH_SEA_SHELL, TwigsBiomeTags.SPAWNS_SEA_SHELL, VEGETAL_DECORATION);
        tryPlace(ORE_BLOODSTONE_NETHER, TwigsBiomeTags.SPAWNS_BLOODSTONE, UNDERGROUND_DECORATION);
        tryPlace(ORE_SCHIST_UPPER, TwigsBiomeTags.SPAWNS_SCHIST, UNDERGROUND_ORES);
        tryPlace(ORE_SCHIST_LOWER, TwigsBiomeTags.SPAWNS_SCHIST, UNDERGROUND_ORES);

        ORE_RHYOLITE.getKey().ifPresent(key -> addFeature(foundInOverworld().and(tag(TwigsBiomeTags.DOES_NOT_SPAWN_RHYOLITE).negate()), UNDERGROUND_ORES, key));
    }

    private static void tryPlace(RegistryEntry<PlacedFeature> feature, TagKey<Biome> tag, GenerationStep.Feature step) {
        feature.getKey().ifPresent(key -> addFeature(tag(tag), step, key));
    }
}
