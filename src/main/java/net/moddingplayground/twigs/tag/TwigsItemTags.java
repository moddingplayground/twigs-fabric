package net.moddingplayground.twigs.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.Twigs;

public class TwigsItemTags {
    public static final Tag.Identified<Item> TABLES = register("tables");
    public static final Tag.Identified<Item> PAPER_LANTERNS = register("paper_lanterns");

    private static Tag.Identified<Item> register(String id) {
        return TagFactory.ITEM.create(new Identifier(Twigs.MOD_ID, id));
    }
}
