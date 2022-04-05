package net.moddingplayground.twigs.mixin;

import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(OrePlacedFeatures.class)
public interface OrePlacedFeaturesInvoker {
    @Invoker static List<PlacementModifier> invokeModifiersWithCount(int count, PlacementModifier modifier) { throw new AssertionError(); }
}
