package net.moddingplayground.twigs.datagen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.block.wood.WoodBlock;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.item.AbstractItemModelGenerator;

import java.util.function.Function;

import static net.moddingplayground.twigs.block.TwigsBlocks.*;
import static net.moddingplayground.twigs.datagen.impl.generator.model.InheritingModelGen.*;

public class ItemModelGenerator extends AbstractItemModelGenerator {
    public ItemModelGenerator() {
        super(Twigs.MOD_ID);
    }

    @Override
    public void generate() {
        this.walls(BLOODSTONE_WALL, SCHIST_WALL);
        this.wallColumns(RHYOLITE_WALL);
        this.wallsVanilla(TUFF_WALL, CALCITE_WALL);
        this.wallPlurals(
            MOSSY_BRICK_WALL, COBBLESTONE_BRICK_WALL, MOSSY_COBBLESTONE_BRICK_WALL,
            SMOOTH_BASALT_BRICK_WALL, POLISHED_AMETHYST_BRICK_WALL,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL, WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
            POLISHED_TUFF_BRICK_WALL,
            POLISHED_CALCITE_BRICK_WALL,
            POLISHED_RHYOLITE_BRICK_WALL,
            POLISHED_SCHIST_BRICK_WALL,
            POLISHED_BLOODSTONE_BRICK_WALL
        );

        this.waxed(
            WAXED_COPPER_PILLAR,
            WAXED_EXPOSED_COPPER_PILLAR,
            WAXED_OXIDIZED_COPPER_PILLAR,
            WAXED_WEATHERED_COPPER_PILLAR
        );

        this.generatedItems(
            TWIG,
            PEBBLE,
            AZALEA_FLOWERS,
            BAMBOO_LEAVES,
            PAPER_LANTERN,
            ALLIUM_PAPER_LANTERN,
            BLUE_ORCHID_PAPER_LANTERN,
            CRIMSON_ROOTS_PAPER_LANTERN,
            DANDELION_PAPER_LANTERN,
            STRIPPED_BAMBOO
        );

        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

        for (Block block : Registry.BLOCK) {
            if (block.asItem() == Items.AIR) continue;
            Identifier id = Registry.BLOCK.getId(block);
            if (id.getNamespace().equals(Twigs.MOD_ID)) {
                if (!this.map.containsKey(id)) this.block(block);
            }
        }
    }

    public void woods(WoodSet... sets) {
        for (WoodSet set : sets) wood(set);
    }

    public void wood(WoodSet set) {
        if (!set.isVanilla()) {
        wood(set, WoodBlock.PLANKS, this::inherit);
            wood(set, WoodBlock.SAPLING, this::generatedBlock);
            wood(set, WoodBlock.LOG, this::inherit);
            wood(set, WoodBlock.STRIPPED_LOG, this::inherit);
            wood(set, WoodBlock.WOOD, this::inherit);
            wood(set, WoodBlock.STRIPPED_WOOD, this::inherit);
            wood(set, WoodBlock.LEAVES, this::inherit);
            wood(set, WoodBlock.SLAB, this::inherit);
            wood(set, WoodBlock.STAIRS, this::inherit);
            wood(set, WoodBlock.FENCE, i -> fenceInventory(name(i, "block/%s_planks", "_fence")));
            wood(set, WoodBlock.DOOR, this::generatedItem);
            wood(set, WoodBlock.TRAPDOOR, i -> inherit(name(i, "block/%s_bottom")));
            wood(set, WoodBlock.FENCE_GATE, this::inherit);
            wood(set, WoodBlock.PRESSURE_PLATE, this::inherit);
            wood(set, WoodBlock.BUTTON, i -> buttonInventory(name(i, "block/%s_planks", "_button")));
            wood(set, WoodBlock.SIGN, this::generatedItem);
            set.getBoatItem().ifPresent(this::add);
        }
    }

    public void wood(WoodSet set, WoodBlock wood, Function<Item, ModelGen> factory) {
        if (set.contains(wood)) this.add(set.get(wood).asItem(), factory);
    }
}
