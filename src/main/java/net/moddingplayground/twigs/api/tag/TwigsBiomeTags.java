package net.moddingplayground.twigs.api.tag;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsBiomeTags {
    TagKey<Biome> SPAWNS_TWIG = register("spawns_twig");
    TagKey<Biome> SPAWNS_PEBBLE = register("spawns_pebble");
    TagKey<Biome> SPAWNS_SEA_SHELL = register("spawns_sea_shell");
    TagKey<Biome> SPAWNS_RHYOLITE = register("spawns_rholite");
    TagKey<Biome> SPAWNS_BLOODSTONE = register("spawns_bloodstone");
    TagKey<Biome> SPAWNS_SCHIST = register("spawns_schist");

    private static TagKey<Biome> register(String id) {
        return TagKey.of(Registry.BIOME_KEY, new Identifier(Twigs.MOD_ID, id));
    }
}
