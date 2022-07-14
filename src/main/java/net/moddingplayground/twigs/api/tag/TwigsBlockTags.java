package net.moddingplayground.twigs.api.tag;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsBlockTags {
    TagKey<Block> TABLES = register("tables");
    TagKey<Block> PAPER_LANTERNS = register("paper_lanterns");
    TagKey<Block> SCULK_PASSENGER_MOVES_TO = register("sculk_passenger_moves_to");

    private static TagKey<Block> register(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(Twigs.MOD_ID, id));
    }
}
