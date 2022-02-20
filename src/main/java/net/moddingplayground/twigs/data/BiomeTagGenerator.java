package net.moddingplayground.twigs.data;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.frame.api.toymaker.v0.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.tag.TwigsBiomeTags;

import static net.minecraft.world.biome.Biome.*;
import static net.minecraft.world.biome.BiomeKeys.*;
import static net.moddingplayground.twigs.tag.TwigsBiomeTags.*;

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
            TAIGA,
            OLD_GROWTH_PINE_TAIGA,
            OLD_GROWTH_SPRUCE_TAIGA,
            SWAMP
        );

        this.add(TwigsBiomeTags.SPAWNS_PEBBLE,
            PLAINS,
            SUNFLOWER_PLAINS,
            MEADOW,
            RIVER,
            MUSHROOM_FIELDS,
            STONY_SHORE,
            TAIGA,
            OLD_GROWTH_PINE_TAIGA,
            OLD_GROWTH_SPRUCE_TAIGA,
            WINDSWEPT_FOREST,
            WINDSWEPT_HILLS,
            WINDSWEPT_GRAVELLY_HILLS,
            WINDSWEPT_SAVANNA,
            SAVANNA,
            SAVANNA_PLATEAU,
            SWAMP
        );

        for (Biome biome : BuiltinRegistries.BIOME) {
            Category category = biome.getCategory();
            if (category != Category.NETHER && category != Category.THEEND && category != Category.NONE) this.add(SPAWNS_RHYOLITE, biome);
            if (category == Category.NETHER) this.add(SPAWNS_BLOODSTONE, biome);
            if (category == Category.MOUNTAIN) this.add(SPAWNS_SCHIST, biome);
        }
    }

    public RegistryKey<Biome> getKey(Biome biome) {
        return RegistryKey.of(Registry.BIOME_KEY, BuiltinRegistries.BIOME.getId(biome));
    }
}
