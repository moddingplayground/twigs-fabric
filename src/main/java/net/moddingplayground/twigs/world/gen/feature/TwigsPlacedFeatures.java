package net.moddingplayground.twigs.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.tag.TwigsBiomeTags;

import java.util.function.BiConsumer;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.*;
import static net.minecraft.world.gen.GenerationStep.Feature.*;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.*;

public class TwigsPlacedFeatures {
    public static final PlacedFeature PATCH_TWIG = register("patch_twig", TwigsConfiguredFeatures.PATCH_TWIG.withPlacement(modifiers(2)));
    public static final PlacedFeature PATCH_PEBBLE = register("patch_pebble", TwigsConfiguredFeatures.PATCH_PEBBLE.withPlacement(modifiers(2)));

    static {
        tryPlace(PATCH_TWIG, (feature, key) -> addFeature(BiomeSelectors.tag(TwigsBiomeTags.SPAWNS_TWIG), VEGETAL_DECORATION, key));
        tryPlace(PATCH_PEBBLE, (feature, key) -> addFeature(BiomeSelectors.tag(TwigsBiomeTags.SPAWNS_PEBBLE), VEGETAL_DECORATION, key));
    }

    private static void tryPlace(PlacedFeature feature, BiConsumer<PlacedFeature, RegistryKey<PlacedFeature>> action) {
        BuiltinRegistries.PLACED_FEATURE.getKey(feature).ifPresent(key -> action.accept(feature, key));
    }

    private static PlacedFeature register(String id, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Twigs.MOD_ID, id), feature);
    }
}
