package net.moddingplayground.twigs.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.twigs.Twigs;

public class TwigsBiomeTags {
    public static final Tag.Identified<Biome> SPAWNS_TWIG = register("spawns_twig");
    public static final Tag.Identified<Biome> SPAWNS_PEBBLE = register("spawns_pebble");

    private static Tag.Identified<Biome> register(String id) {
        return TagFactory.BIOME.create(new Identifier(Twigs.MOD_ID, id));
    }
}
