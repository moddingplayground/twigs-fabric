package net.moddingplayground.twigs.api.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsBiomeTags {
    Tag.Identified<Biome> SPAWNS_TWIG = register("spawns_twig");
    Tag.Identified<Biome> SPAWNS_PEBBLE = register("spawns_pebble");
    Tag.Identified<Biome> SPAWNS_RHYOLITE = register("spawns_rholite");
    Tag.Identified<Biome> SPAWNS_BLOODSTONE = register("spawns_bloodstone");
    Tag.Identified<Biome> SPAWNS_SCHIST = register("spawns_schist");

    private static Tag.Identified<Biome> register(String id) {
        return TagFactory.BIOME.create(new Identifier(Twigs.MOD_ID, id));
    }
}
