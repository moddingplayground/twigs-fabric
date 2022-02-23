package net.moddingplayground.twigs.api.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsItemTags {
    Tag.Identified<Item> TABLES = register("tables");
    Tag.Identified<Item> PAPER_LANTERNS = register("paper_lanterns");

    private static Tag.Identified<Item> register(String id) {
        return TagFactory.ITEM.create(new Identifier(Twigs.MOD_ID, id));
    }
}
