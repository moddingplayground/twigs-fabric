package net.moddingplayground.twigs.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.mixin.OrePlacedFeaturesAccessor;
import net.moddingplayground.twigs.tag.TwigsBiomeTags;

import java.util.List;
import java.util.function.Consumer;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.*;
import static net.minecraft.world.gen.GenerationStep.Feature.*;
import static net.minecraft.world.gen.YOffset.*;
import static net.minecraft.world.gen.decorator.HeightRangePlacementModifier.*;

public class TwigsPlacedFeatures {
    public static final PlacedFeature PATCH_TWIG = register("patch_twig", TwigsConfiguredFeatures.PATCH_TWIG.withPlacement(modifiers(2)));
    public static final PlacedFeature PATCH_PEBBLE = register("patch_pebble", TwigsConfiguredFeatures.PATCH_PEBBLE.withPlacement(modifiers(2)));

    public static final PlacedFeature ORE_RHYOLITE_LOWER = register("ore_rhyolite_lower", TwigsConfiguredFeatures.ORE_RHYOLITE.withPlacement(countOre(2, uniform(getBottom(), fixed(16)))));

    public static final PlacedFeature ORE_BLOODSTONE_NETHER = register("ore_bloodstone_nether", TwigsConfiguredFeatures.ORE_BLOODSTONE.withPlacement(countOre(2, uniform(fixed(5), getTop()))));

    public static final PlacedFeature ORE_SCHIST_UPPER = register("ore_schist_upper", TwigsConfiguredFeatures.ORE_SCHIST.withPlacement(rarityOre(6, uniform(fixed(64), fixed(128)))));
    public static final PlacedFeature ORE_SCHIST_LOWER = register("ore_schist_lower", TwigsConfiguredFeatures.ORE_SCHIST.withPlacement(countOre(2, uniform(getBottom(), fixed(60)))));

    static {
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

    private static PlacedFeature register(String id, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Twigs.MOD_ID, id), feature);
    }

    public static List<PlacementModifier> modifiers(int chance) {
        return VegetationPlacedFeatures.modifiers(chance);
    }

    public static List<PlacementModifier> countOre(int count, HeightRangePlacementModifier modifier) {
        return OrePlacedFeaturesAccessor.invokeModifiersWithCount(count, modifier);
    }

    public static List<PlacementModifier> rarityOre(int chance, HeightRangePlacementModifier modifier) {
        return OrePlacedFeaturesAccessor.invokeModifiersWithRarity(chance, modifier);
    }
}
