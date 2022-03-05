package net.moddingplayground.twigs.api.tag;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsItemTags {
    TagKey<Item> TABLES = register("tables");
    TagKey<Item> PAPER_LANTERNS = register("paper_lanterns");

    private static TagKey<Item> register(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(Twigs.MOD_ID, id));
    }
}
