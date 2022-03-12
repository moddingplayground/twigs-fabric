package net.moddingplayground.twigs.api.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

public interface TwigsItemGroups {
    ItemGroup ALL = FabricItemGroupBuilder.create(new Identifier(Twigs.MOD_ID, "item_group"))
                                          .icon(() -> new ItemStack(TwigsBlocks.TWIG))
                                          .build();
}
