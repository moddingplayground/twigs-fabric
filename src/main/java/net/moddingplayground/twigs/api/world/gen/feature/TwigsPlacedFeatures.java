package net.moddingplayground.twigs.api.world.gen.feature;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.mixin.OrePlacedFeaturesAccessor;

import java.util.List;

import static net.minecraft.world.gen.YOffset.*;
import static net.minecraft.world.gen.decorator.HeightRangePlacementModifier.*;

public interface TwigsPlacedFeatures extends ModInitializer {
    PlacedFeature PATCH_TWIG = register("patch_twig", TwigsConfiguredFeatures.PATCH_TWIG.withPlacement(modifiers(2)));
    PlacedFeature PATCH_PEBBLE = register("patch_pebble", TwigsConfiguredFeatures.PATCH_PEBBLE.withPlacement(modifiers(2)));

    PlacedFeature ORE_RHYOLITE_LOWER = register("ore_rhyolite_lower", TwigsConfiguredFeatures.ORE_RHYOLITE.withPlacement(countOre(2, uniform(getBottom(), fixed(16)))));

    PlacedFeature ORE_BLOODSTONE_NETHER = register("ore_bloodstone_nether", TwigsConfiguredFeatures.ORE_BLOODSTONE.withPlacement(countOre(2, uniform(fixed(5), getTop()))));

    PlacedFeature ORE_SCHIST_UPPER = register("ore_schist_upper", TwigsConfiguredFeatures.ORE_SCHIST.withPlacement(rarityOre(6, uniform(fixed(64), fixed(128)))));
    PlacedFeature ORE_SCHIST_LOWER = register("ore_schist_lower", TwigsConfiguredFeatures.ORE_SCHIST.withPlacement(countOre(2, uniform(getBottom(), fixed(60)))));

    private static PlacedFeature register(String id, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Twigs.MOD_ID, id), feature);
    }

    private static List<PlacementModifier> modifiers(int chance) {
        return VegetationPlacedFeatures.modifiers(chance);
    }

    static List<PlacementModifier> countOre(int count, HeightRangePlacementModifier modifier) {
        return OrePlacedFeaturesAccessor.invokeModifiersWithCount(count, modifier);
    }

    static List<PlacementModifier> rarityOre(int chance, HeightRangePlacementModifier modifier) {
        return OrePlacedFeaturesAccessor.invokeModifiersWithRarity(chance, modifier);
    }
}
