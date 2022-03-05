package net.moddingplayground.twigs.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;

@SuppressWarnings("unused")
public class TwigsItems {
    public static final Item BAMBOO_LEAVES = register("bamboo_leaves", new BlockItem(TwigsBlocks.BAMBOO_LEAVES, new FabricItemSettings().group(Twigs.ITEM_GROUP)));

    static {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(BAMBOO_LEAVES, 0.5F);

        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (id.equals(Blocks.BAMBOO.getLootTableId())) {
                supplier.copyFrom(manager.getTable(new Identifier(Twigs.MOD_ID, "additions/blocks/bamboo")));
            }
        });
    }

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Twigs.MOD_ID, id), item);
    }
}
