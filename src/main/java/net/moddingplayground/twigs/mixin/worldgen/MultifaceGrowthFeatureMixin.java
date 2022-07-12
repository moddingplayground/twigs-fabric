package net.moddingplayground.twigs.mixin.worldgen;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.feature.MultifaceGrowthFeature;
import net.minecraft.world.gen.feature.MultifaceGrowthFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.api.config.TwigsWorldGenReplacementsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Supplier;

// implemented through TwigsMixinPlugin
@SuppressWarnings({ "deprecation", "UnusedMixin" })
@Mixin(MultifaceGrowthFeature.class)
public class MultifaceGrowthFeatureMixin {
    private static @Unique final Supplier<MultifaceGrowthFeatureConfig> TWIGS_PETRIFIED_LICHEN_REPLACEMENT_CONFIG = Suppliers.memoize(() -> new MultifaceGrowthFeatureConfig(
        (MultifaceGrowthBlock) TwigsBlocks.PETRIFIED_LICHEN, 20, true, true, true, 0.5F,
        RegistryEntryList.of(Block::getRegistryEntry,
            Blocks.STONE,
            Blocks.ANDESITE,
            Blocks.DIORITE,
            Blocks.GRANITE,
            Blocks.DRIPSTONE_BLOCK,
            Blocks.CALCITE,
            Blocks.TUFF,
            Blocks.DEEPSLATE
        )
    ));

    @SuppressWarnings("unchecked")
    @ModifyVariable(method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z", at = @At(value = "HEAD", ordinal = 0), argsOnly = true)
    private FeatureContext<MultifaceGrowthFeatureConfig> onGenerate(FeatureContext<MultifaceGrowthFeatureConfig> context) {
        if (!TwigsWorldGenReplacementsConfig.INSTANCE.petrifiedLichenAtDeepslate.getValue()) return context;

        BlockPos origin = context.getOrigin();

        // replace glow lichen below y0
        if (origin.getY() < 0) {
            MultifaceGrowthFeatureConfig config = context.getConfig();
            if (config.lichen == Blocks.GLOW_LICHEN) {
                FeatureContextAccessor<MultifaceGrowthFeatureConfig> accessor = (FeatureContextAccessor<MultifaceGrowthFeatureConfig>) context;
                accessor.setConfig(TWIGS_PETRIFIED_LICHEN_REPLACEMENT_CONFIG.get());
            }
        }

        return context;
    }
}
