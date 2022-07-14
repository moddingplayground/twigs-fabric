package net.moddingplayground.twigs.api.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.api.entity.TwigsBoatTypes;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface TwigsItems {
    Item TWIG = register(TwigsBlocks.TWIG, FlintAndSteelBlockItem::new);
    Item PEBBLE = register(TwigsBlocks.PEBBLE, PebbleItem::new);
    Item SEA_SHELL = register(TwigsBlocks.SEA_SHELL);
    Item AZALEA_FLOWERS = register(TwigsBlocks.AZALEA_FLOWERS);

    Item PAPER_LANTERN = register(TwigsBlocks.PAPER_LANTERN);
    Item ALLIUM_PAPER_LANTERN = register(TwigsBlocks.ALLIUM_PAPER_LANTERN);
    Item BLUE_ORCHID_PAPER_LANTERN = register(TwigsBlocks.BLUE_ORCHID_PAPER_LANTERN);
    Item CRIMSON_ROOTS_PAPER_LANTERN = register(TwigsBlocks.CRIMSON_ROOTS_PAPER_LANTERN);
    Item DANDELION_PAPER_LANTERN = register(TwigsBlocks.DANDELION_PAPER_LANTERN);

    Item BAMBOO_LEAVES = register(TwigsBlocks.BAMBOO_LEAVES);

    Item BAMBOO_THATCH = register(TwigsBlocks.BAMBOO_THATCH);
    Item BAMBOO_THATCH_STAIRS = register(TwigsBlocks.BAMBOO_THATCH_STAIRS);
    Item BAMBOO_THATCH_SLAB = register(TwigsBlocks.BAMBOO_THATCH_SLAB);

    Item BUNDLED_BAMBOO = register(TwigsBlocks.BUNDLED_BAMBOO);
    Item STRIPPED_BUNDLED_BAMBOO = register(TwigsBlocks.STRIPPED_BUNDLED_BAMBOO);

    Item STRIPPED_BAMBOO = register(TwigsBlocks.STRIPPED_BAMBOO);
    Item STRIPPED_BAMBOO_PLANKS = register(TwigsBlocks.STRIPPED_BAMBOO_PLANKS);
    Item STRIPPED_BAMBOO_SLAB = register(TwigsBlocks.STRIPPED_BAMBOO_SLAB);
    Item STRIPPED_BAMBOO_FENCE = register(TwigsBlocks.STRIPPED_BAMBOO_FENCE);
    Item STRIPPED_BAMBOO_FENCE_GATE = register(TwigsBlocks.STRIPPED_BAMBOO_FENCE_GATE);
    Item STRIPPED_BAMBOO_STAIRS = register(TwigsBlocks.STRIPPED_BAMBOO_STAIRS);
    Item STRIPPED_BAMBOO_BUTTON = register(TwigsBlocks.STRIPPED_BAMBOO_BUTTON);
    Item STRIPPED_BAMBOO_PRESSURE_PLATE = register(TwigsBlocks.STRIPPED_BAMBOO_PRESSURE_PLATE);
    Item STRIPPED_BAMBOO_DOOR = register(TwigsBlocks.STRIPPED_BAMBOO_DOOR);
    Item STRIPPED_BAMBOO_TRAPDOOR = register(TwigsBlocks.STRIPPED_BAMBOO_TRAPDOOR);
    Item STRIPPED_BAMBOO_SIGN = register(TwigsBlocks.STRIPPED_BAMBOO_SIGN, (block, settings) -> new SignItem(settings.maxCount(16), block, TwigsBlocks.STRIPPED_BAMBOO_WALL_SIGN));
    Item STRIPPED_BAMBOO_MAT = register(TwigsBlocks.STRIPPED_BAMBOO_MAT);
    Item STRIPPED_BAMBOO_BOAT = unstackable("stripped_bamboo_boat", settings -> new BoatItem(false, TwigsBoatTypes.STRIPPED_BAMBOO, settings));
    Item STRIPPED_BAMBOO_CHEST_BOAT = unstackable("stripped_bamboo_chest_boat", settings -> new BoatItem(true, TwigsBoatTypes.STRIPPED_BAMBOO, settings));
    Item STRIPPED_BAMBOO_TABLE = register(TwigsBlocks.STRIPPED_BAMBOO_TABLE);

    Item OAK_TABLE = register(TwigsBlocks.OAK_TABLE);
    Item SPRUCE_TABLE = register(TwigsBlocks.SPRUCE_TABLE);
    Item BIRCH_TABLE = register(TwigsBlocks.BIRCH_TABLE);
    Item JUNGLE_TABLE = register(TwigsBlocks.JUNGLE_TABLE);
    Item ACACIA_TABLE = register(TwigsBlocks.ACACIA_TABLE);
    Item DARK_OAK_TABLE = register(TwigsBlocks.DARK_OAK_TABLE);
    Item MANGROVE_TABLE = register(TwigsBlocks.MANGROVE_TABLE);
    Item CRIMSON_TABLE = register(TwigsBlocks.CRIMSON_TABLE);
    Item WARPED_TABLE = register(TwigsBlocks.WARPED_TABLE);

    Item LAMP = register(TwigsBlocks.LAMP);
    Item SOUL_LAMP = register(TwigsBlocks.SOUL_LAMP);

    Item CRIMSON_SHROOMLAMP = register(TwigsBlocks.CRIMSON_SHROOMLAMP);
    Item WARPED_SHROOMLAMP = register(TwigsBlocks.WARPED_SHROOMLAMP);

    Item ENDER_MESH = register(TwigsBlocks.ENDER_MESH);
    Item SCULK_PASSENGER = register(TwigsBlocks.SCULK_PASSENGER);
    Item PETRIFIED_LICHEN = register(TwigsBlocks.PETRIFIED_LICHEN);

    Item CALCITE_STAIRS = register(TwigsBlocks.CALCITE_STAIRS);
    Item CALCITE_SLAB = register(TwigsBlocks.CALCITE_SLAB);
    Item CALCITE_WALL = register(TwigsBlocks.CALCITE_WALL);
    Item POLISHED_CALCITE = register(TwigsBlocks.POLISHED_CALCITE);
    Item POLISHED_CALCITE_STAIRS = register(TwigsBlocks.POLISHED_CALCITE_STAIRS);
    Item POLISHED_CALCITE_SLAB = register(TwigsBlocks.POLISHED_CALCITE_SLAB);
    Item POLISHED_CALCITE_BRICKS = register(TwigsBlocks.POLISHED_CALCITE_BRICKS);
    Item POLISHED_CALCITE_BRICK_STAIRS = register(TwigsBlocks.POLISHED_CALCITE_BRICK_STAIRS);
    Item POLISHED_CALCITE_BRICK_SLAB = register(TwigsBlocks.POLISHED_CALCITE_BRICK_SLAB);
    Item POLISHED_CALCITE_BRICK_WALL = register(TwigsBlocks.POLISHED_CALCITE_BRICK_WALL);
    Item CRACKED_POLISHED_CALCITE_BRICKS = register(TwigsBlocks.CRACKED_POLISHED_CALCITE_BRICKS);

    Item SCHIST = register(TwigsBlocks.SCHIST);
    Item SCHIST_STAIRS = register(TwigsBlocks.SCHIST_STAIRS);
    Item SCHIST_SLAB = register(TwigsBlocks.SCHIST_SLAB);
    Item SCHIST_WALL = register(TwigsBlocks.SCHIST_WALL);
    Item POLISHED_SCHIST = register(TwigsBlocks.POLISHED_SCHIST);
    Item POLISHED_SCHIST_STAIRS = register(TwigsBlocks.POLISHED_SCHIST_STAIRS);
    Item POLISHED_SCHIST_SLAB = register(TwigsBlocks.POLISHED_SCHIST_SLAB);
    Item POLISHED_SCHIST_BRICKS = register(TwigsBlocks.POLISHED_SCHIST_BRICKS);
    Item POLISHED_SCHIST_BRICK_STAIRS = register(TwigsBlocks.POLISHED_SCHIST_BRICK_STAIRS);
    Item POLISHED_SCHIST_BRICK_SLAB = register(TwigsBlocks.POLISHED_SCHIST_BRICK_SLAB);
    Item POLISHED_SCHIST_BRICK_WALL = register(TwigsBlocks.POLISHED_SCHIST_BRICK_WALL);
    Item CRACKED_POLISHED_SCHIST_BRICKS = register(TwigsBlocks.CRACKED_POLISHED_SCHIST_BRICKS);

    Item ROCKY_DIRT = register(TwigsBlocks.ROCKY_DIRT);

    Item COBBLESTONE_BRICKS = register(TwigsBlocks.COBBLESTONE_BRICKS);
    Item COBBLESTONE_BRICK_STAIRS = register(TwigsBlocks.COBBLESTONE_BRICK_STAIRS);
    Item COBBLESTONE_BRICK_SLAB = register(TwigsBlocks.COBBLESTONE_BRICK_SLAB);
    Item COBBLESTONE_BRICK_WALL = register(TwigsBlocks.COBBLESTONE_BRICK_WALL);
    Item CRACKED_COBBLESTONE_BRICKS = register(TwigsBlocks.CRACKED_COBBLESTONE_BRICKS);
    Item MOSSY_COBBLESTONE_BRICKS = register(TwigsBlocks.MOSSY_COBBLESTONE_BRICKS);
    Item MOSSY_COBBLESTONE_BRICK_STAIRS = register(TwigsBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS);
    Item MOSSY_COBBLESTONE_BRICK_SLAB = register(TwigsBlocks.MOSSY_COBBLESTONE_BRICK_SLAB);
    Item MOSSY_COBBLESTONE_BRICK_WALL = register(TwigsBlocks.MOSSY_COBBLESTONE_BRICK_WALL);

    Item TUFF_STAIRS = register(TwigsBlocks.TUFF_STAIRS);
    Item TUFF_SLAB = register(TwigsBlocks.TUFF_SLAB);
    Item TUFF_WALL = register(TwigsBlocks.TUFF_WALL);
    Item POLISHED_TUFF = register(TwigsBlocks.POLISHED_TUFF);
    Item POLISHED_TUFF_STAIRS = register(TwigsBlocks.POLISHED_TUFF_STAIRS);
    Item POLISHED_TUFF_SLAB = register(TwigsBlocks.POLISHED_TUFF_SLAB);
    Item POLISHED_TUFF_BRICKS = register(TwigsBlocks.POLISHED_TUFF_BRICKS);
    Item POLISHED_TUFF_BRICK_STAIRS = register(TwigsBlocks.POLISHED_TUFF_BRICK_STAIRS);
    Item POLISHED_TUFF_BRICK_SLAB = register(TwigsBlocks.POLISHED_TUFF_BRICK_SLAB);
    Item POLISHED_TUFF_BRICK_WALL = register(TwigsBlocks.POLISHED_TUFF_BRICK_WALL);
    Item CRACKED_POLISHED_TUFF_BRICKS = register(TwigsBlocks.CRACKED_POLISHED_TUFF_BRICKS);

    Item POLISHED_BASALT_BRICKS = register(TwigsBlocks.POLISHED_BASALT_BRICKS);
    Item SMOOTH_BASALT_BRICKS = register(TwigsBlocks.SMOOTH_BASALT_BRICKS);
    Item SMOOTH_BASALT_BRICK_STAIRS = register(TwigsBlocks.SMOOTH_BASALT_BRICK_STAIRS);
    Item SMOOTH_BASALT_BRICK_SLAB = register(TwigsBlocks.SMOOTH_BASALT_BRICK_SLAB);
    Item SMOOTH_BASALT_BRICK_WALL = register(TwigsBlocks.SMOOTH_BASALT_BRICK_WALL);

    Item BLOODSTONE = register(TwigsBlocks.BLOODSTONE);
    Item BLOODSTONE_STAIRS = register(TwigsBlocks.BLOODSTONE_STAIRS);
    Item BLOODSTONE_SLAB = register(TwigsBlocks.BLOODSTONE_SLAB);
    Item BLOODSTONE_WALL = register(TwigsBlocks.BLOODSTONE_WALL);
    Item POLISHED_BLOODSTONE = register(TwigsBlocks.POLISHED_BLOODSTONE);
    Item POLISHED_BLOODSTONE_STAIRS = register(TwigsBlocks.POLISHED_BLOODSTONE_STAIRS);
    Item POLISHED_BLOODSTONE_SLAB = register(TwigsBlocks.POLISHED_BLOODSTONE_SLAB);
    Item POLISHED_BLOODSTONE_BRICKS = register(TwigsBlocks.POLISHED_BLOODSTONE_BRICKS);
    Item POLISHED_BLOODSTONE_BRICK_STAIRS = register(TwigsBlocks.POLISHED_BLOODSTONE_BRICK_STAIRS);
    Item POLISHED_BLOODSTONE_BRICK_SLAB = register(TwigsBlocks.POLISHED_BLOODSTONE_BRICK_SLAB);
    Item POLISHED_BLOODSTONE_BRICK_WALL = register(TwigsBlocks.POLISHED_BLOODSTONE_BRICK_WALL);
    Item CRACKED_POLISHED_BLOODSTONE_BRICKS = register(TwigsBlocks.CRACKED_POLISHED_BLOODSTONE_BRICKS);

    Item TWISTING_POLISHED_BLACKSTONE_BRICKS = register(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICKS);
    Item TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS = register(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS);
    Item TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB = register(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB);
    Item TWISTING_POLISHED_BLACKSTONE_BRICK_WALL = register(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICK_WALL);
    Item WEEPING_POLISHED_BLACKSTONE_BRICKS = register(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICKS);
    Item WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS = register(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS);
    Item WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB = register(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB);
    Item WEEPING_POLISHED_BLACKSTONE_BRICK_WALL = register(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_WALL);

    Item RHYOLITE = register(TwigsBlocks.RHYOLITE);
    Item RHYOLITE_STAIRS = register(TwigsBlocks.RHYOLITE_STAIRS);
    Item RHYOLITE_SLAB = register(TwigsBlocks.RHYOLITE_SLAB);
    Item RHYOLITE_WALL = register(TwigsBlocks.RHYOLITE_WALL);
    Item POLISHED_RHYOLITE = register(TwigsBlocks.POLISHED_RHYOLITE);
    Item POLISHED_RHYOLITE_STAIRS = register(TwigsBlocks.POLISHED_RHYOLITE_STAIRS);
    Item POLISHED_RHYOLITE_SLAB = register(TwigsBlocks.POLISHED_RHYOLITE_SLAB);
    Item POLISHED_RHYOLITE_BRICKS = register(TwigsBlocks.POLISHED_RHYOLITE_BRICKS);
    Item POLISHED_RHYOLITE_BRICK_STAIRS = register(TwigsBlocks.POLISHED_RHYOLITE_BRICK_STAIRS);
    Item POLISHED_RHYOLITE_BRICK_SLAB = register(TwigsBlocks.POLISHED_RHYOLITE_BRICK_SLAB);
    Item POLISHED_RHYOLITE_BRICK_WALL = register(TwigsBlocks.POLISHED_RHYOLITE_BRICK_WALL);
    Item CRACKED_POLISHED_RHYOLITE_BRICKS = register(TwigsBlocks.CRACKED_POLISHED_RHYOLITE_BRICKS);

    Item CHISELED_BRICKS = register(TwigsBlocks.CHISELED_BRICKS);
    Item CRACKED_BRICKS = register(TwigsBlocks.CRACKED_BRICKS);

    Item MOSSY_BRICKS = register(TwigsBlocks.MOSSY_BRICKS);
    Item MOSSY_BRICK_STAIRS = register(TwigsBlocks.MOSSY_BRICK_STAIRS);
    Item MOSSY_BRICK_SLAB = register(TwigsBlocks.MOSSY_BRICK_SLAB);
    Item MOSSY_BRICK_WALL = register(TwigsBlocks.MOSSY_BRICK_WALL);

    Item COPPER_PILLAR = register(TwigsBlocks.COPPER_PILLAR);
    Item EXPOSED_COPPER_PILLAR = register(TwigsBlocks.EXPOSED_COPPER_PILLAR);
    Item WEATHERED_COPPER_PILLAR = register(TwigsBlocks.WEATHERED_COPPER_PILLAR);
    Item OXIDIZED_COPPER_PILLAR = register(TwigsBlocks.OXIDIZED_COPPER_PILLAR);
    Item WAXED_COPPER_PILLAR = register(TwigsBlocks.WAXED_COPPER_PILLAR);
    Item WAXED_EXPOSED_COPPER_PILLAR = register(TwigsBlocks.WAXED_EXPOSED_COPPER_PILLAR);
    Item WAXED_WEATHERED_COPPER_PILLAR = register(TwigsBlocks.WAXED_WEATHERED_COPPER_PILLAR);
    Item WAXED_OXIDIZED_COPPER_PILLAR = register(TwigsBlocks.WAXED_OXIDIZED_COPPER_PILLAR);

    Item POLISHED_AMETHYST = register(TwigsBlocks.POLISHED_AMETHYST);
    Item POLISHED_AMETHYST_STAIRS = register(TwigsBlocks.POLISHED_AMETHYST_STAIRS);
    Item POLISHED_AMETHYST_SLAB = register(TwigsBlocks.POLISHED_AMETHYST_SLAB);
    Item CHISELED_POLISHED_AMETHYST = register(TwigsBlocks.CHISELED_POLISHED_AMETHYST);
    Item POLISHED_AMETHYST_BRICKS = register(TwigsBlocks.POLISHED_AMETHYST_BRICKS);
    Item POLISHED_AMETHYST_BRICK_STAIRS = register(TwigsBlocks.POLISHED_AMETHYST_BRICK_STAIRS);
    Item POLISHED_AMETHYST_BRICK_SLAB = register(TwigsBlocks.POLISHED_AMETHYST_BRICK_SLAB);
    Item POLISHED_AMETHYST_BRICK_WALL = register(TwigsBlocks.POLISHED_AMETHYST_BRICK_WALL);
    Item CRACKED_POLISHED_AMETHYST_BRICKS = register(TwigsBlocks.CRACKED_POLISHED_AMETHYST_BRICKS);

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Twigs.MOD_ID, id), item);
    }

    private static Item register(String id, Function<FabricItemSettings, Item> item) {
        return register(id, item.apply(new FabricItemSettings().group(TwigsItemGroups.ALL)));
    }

    private static Item register(Block block, BiFunction<Block, FabricItemSettings, Item> item) {
        Identifier id = Registry.BLOCK.getId(block);
        return Registry.register(Registry.ITEM, id, item.apply(block, new FabricItemSettings().group(TwigsItemGroups.ALL)));
    }

    private static Item register(Block block) {
        return register(block, BlockItem::new);
    }

    private static Item unstackable(String id, Function<FabricItemSettings, Item> item) {
        return register(id, settings -> item.apply(settings.maxCount(1)));
    }
}
