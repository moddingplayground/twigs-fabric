package net.moddingplayground.twigs.mixin.worldgen;

import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FeatureContext.class)
public interface FeatureContextAccessor<FC extends FeatureConfig> {
    @Mutable @Accessor void setConfig(FC config);
}
