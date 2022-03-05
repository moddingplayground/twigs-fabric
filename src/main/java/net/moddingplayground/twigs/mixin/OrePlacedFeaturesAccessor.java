package net.moddingplayground.twigs.mixin;

import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(OrePlacedFeatures.class)
public interface OrePlacedFeaturesAccessor {
    @Invoker static List<PlacementModifier> invokeModifiersWithCount(int count, PlacementModifier modifier) { throw new AssertionError(); }
    @Invoker static List<PlacementModifier> invokeModifiersWithRarity(int chance, PlacementModifier modifier) { throw new AssertionError(); }
}
