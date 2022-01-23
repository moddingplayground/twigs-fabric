package net.moddingplayground.twigs.datagen;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.toymaker.api.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.tag.TwigsBiomeTags;

import static net.minecraft.world.biome.BiomeKeys.*;

public class BiomeTagGenerator extends AbstractTagGenerator<Biome> {
    public BiomeTagGenerator() {
        super(Twigs.MOD_ID, BuiltinRegistries.BIOME, "biome");
    }

    @Override
    public void generate() {
        this.add(TwigsBiomeTags.SPAWNS_TWIG,
            FOREST,
            FLOWER_FOREST,
            DARK_FOREST,
            BIRCH_FOREST,
            OLD_GROWTH_BIRCH_FOREST,
            WINDSWEPT_FOREST,
            TAIGA
        );

        this.add(TwigsBiomeTags.SPAWNS_PEBBLE,
            SAVANNA,
            PLAINS,
            MEADOW,
            STONY_SHORE,
            TAIGA
        );
    }
}
