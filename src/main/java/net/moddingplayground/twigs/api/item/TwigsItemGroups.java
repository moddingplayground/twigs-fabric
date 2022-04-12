package net.moddingplayground.twigs.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.tabbeditemgroups.v0.Tab;
import net.moddingplayground.frame.api.tabbeditemgroups.v0.TabbedItemGroup;
import net.moddingplayground.frame.api.util.GUIIcon;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

import java.util.function.Supplier;

public interface TwigsItemGroups {
    TabbedItemGroup ALL = TabbedItemGroup.builder()
                                         .tab(tab("natural",       () -> TwigsBlocks.BAMBOO_LEAVES))
                                         .tab(tab("stones",        () -> TwigsBlocks.COBBLESTONE_BRICKS))
                                         .tab(tab("decoration",    () -> TwigsBlocks.PAPER_LANTERN))
                                         .tab(tab("miscellaneous", () -> TwigsBlocks.POLISHED_AMETHYST))
                                         .build(new Identifier(Twigs.MOD_ID, "item_group"), t -> GUIIcon.of(() -> new ItemStack(TwigsBlocks.TWIG)));

    static TagKey<Item> createTabTag(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(Twigs.MOD_ID, "item_group_tab/%s".formatted(id)));
    }

    private static Tab tab(String id, Supplier<ItemConvertible> icon) {
        return Tab.builder().predicate(Tab.Predicate.tag(createTabTag(id))).build(id, GUIIcon.of(() -> new ItemStack(icon.get())));
    }
}
