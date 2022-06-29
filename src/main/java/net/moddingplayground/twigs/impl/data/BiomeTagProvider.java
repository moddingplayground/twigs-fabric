package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.twigs.api.tag.TwigsBiomeTags;

import static net.minecraft.world.biome.BiomeKeys.*;

public class BiomeTagProvider extends FabricTagProvider.DynamicRegistryTagProvider<Biome> {
    public BiomeTagProvider(FabricDataGenerator gen) {
        super(gen, Registry.BIOME_KEY);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_TWIG)
            .forceAddTag(BiomeTags.IS_FOREST)
            .forceAddTag(BiomeTags.IS_TAIGA)
            .add(SWAMP);

        this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_PEBBLE)
            .forceAddTag(BiomeTags.IS_RIVER)
            .forceAddTag(BiomeTags.IS_SAVANNA)
            .forceAddTag(BiomeTags.IS_TAIGA)
            .forceAddTag(BiomeTags.IS_BEACH).add(
                PLAINS,
                SUNFLOWER_PLAINS,
                MEADOW,
                MUSHROOM_FIELDS,
                WINDSWEPT_FOREST,
                WINDSWEPT_HILLS,
                WINDSWEPT_GRAVELLY_HILLS,
                STONY_SHORE,
                SWAMP
            );

        this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_SEA_SHELL)
            .forceAddTag(BiomeTags.IS_BEACH)
            .add(STONY_SHORE);

        this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_BLOODSTONE)
            .forceAddTag(BiomeTags.IS_NETHER);

        this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_SCHIST)
            .forceAddTag(BiomeTags.IS_MOUNTAIN);

        this.getOrCreateTagBuilder(TwigsBiomeTags.DOES_NOT_SPAWN_RHYOLITE);
    }
}
