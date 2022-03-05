package net.moddingplayground.twigs.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.moddingplayground.twigs.Twigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(method = "addMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
    private static void addMineables(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, Twigs.ORE_RHYOLITE_LOWER);
    }
    @Inject(method = "addNetherMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("HEAD"))
    private static void addNetherMineables(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Twigs.ORE_BLOODSTONE_NETHER);
    }
    @Inject(method = "addEmeraldOre(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("HEAD"))
    private static void addEmeraldOre(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, Twigs.ORE_SCHIST_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, Twigs.ORE_SCHIST_LOWER);
    }
}
