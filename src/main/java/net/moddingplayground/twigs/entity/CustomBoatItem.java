package net.moddingplayground.twigs.entity;

import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;

public class CustomBoatItem {
    public static final Item STRIPPED_BAMBOO_BOAT = new BoatItem(CustomBoatType.STRIPPED_BAMBOO, new Item.Settings().maxCount(1).group(Twigs.ITEM_GROUP));

    public static void registerBoats() {
        Registry.register(Registry.ITEM, new Identifier(Twigs.MOD_ID, "stripped_bamboo_boat"), STRIPPED_BAMBOO_BOAT);
    }
}
