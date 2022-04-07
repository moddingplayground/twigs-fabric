package net.moddingplayground.twigs.api.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.mixin.OrePlacedFeaturesAccessor;

import java.util.List;

import static net.minecraft.world.gen.YOffset.*;
import static net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier.*;

public interface TwigsPlacedFeatures {
    RegistryEntry<PlacedFeature> PATCH_TWIG = register("patch_twig", TwigsConfiguredFeatures.PATCH_TWIG, modifiers(2));
    RegistryEntry<PlacedFeature> PATCH_PEBBLE = register("patch_pebble", TwigsConfiguredFeatures.PATCH_PEBBLE, modifiers(2));
    RegistryEntry<PlacedFeature> PATCH_SEA_SHELL = register("patch_sea_shell", TwigsConfiguredFeatures.PATCH_SEA_SHELL, modifiers(2));

    RegistryEntry<PlacedFeature> ORE_RHYOLITE_LOWER = register("ore_rhyolite_lower", TwigsConfiguredFeatures.ORE_RHYOLITE, countOre(2, uniform(getBottom(), fixed(16))));

    RegistryEntry<PlacedFeature> ORE_BLOODSTONE_NETHER = register("ore_bloodstone_nether", TwigsConfiguredFeatures.ORE_BLOODSTONE, countOre(2, uniform(fixed(5), getTop())));

    RegistryEntry<PlacedFeature> ORE_SCHIST_UPPER = register("ore_schist_upper", TwigsConfiguredFeatures.ORE_SCHIST, rarityOre(6, uniform(fixed(64), fixed(128))));
    RegistryEntry<PlacedFeature> ORE_SCHIST_LOWER = register("ore_schist_lower", TwigsConfiguredFeatures.ORE_SCHIST, countOre(2, uniform(getBottom(), fixed(60))));

    private static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        return PlacedFeatures.register(new Identifier(Twigs.MOD_ID, id).toString(), feature, modifiers);
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
