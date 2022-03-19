package net.moddingplayground.twigs.impl.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.toymaker.v0.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.api.block.PaperLanternBlock;
import net.moddingplayground.twigs.api.block.TableBlock;
import net.moddingplayground.twigs.api.item.TwigsItemGroups;
import net.moddingplayground.twigs.api.tag.TwigsItemTags;
import net.moddingplayground.twigs.impl.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.impl.block.wood.WoodBlock;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;
import static net.moddingplayground.twigs.impl.data.TwigsToymakerImpl.*;

public class ItemTagGenerator extends AbstractTagGenerator<Item> {
    public ItemTagGenerator(String modId) {
        super(modId, Registry.ITEM);
    }

    @Override
    public void generate() {
        this.add(ItemTags.WALLS,
            MOSSY_BRICK_WALL,
            COBBLESTONE_BRICK_WALL,
            MOSSY_COBBLESTONE_BRICK_WALL,
            SMOOTH_BASALT_BRICK_WALL,
            POLISHED_AMETHYST_BRICK_WALL,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
            WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
            TUFF_WALL,
            POLISHED_TUFF_BRICK_WALL,
            CALCITE_WALL,
            POLISHED_CALCITE_BRICK_WALL,
            RHYOLITE_WALL,
            POLISHED_RHYOLITE_BRICK_WALL,
            SCHIST_WALL,
            POLISHED_SCHIST_BRICK_WALL,
            BLOODSTONE_WALL,
            POLISHED_BLOODSTONE_BRICK_WALL
        );

        this.add(ItemTags.STAIRS,
            MOSSY_BRICK_STAIRS,
            COBBLESTONE_BRICK_STAIRS,
            MOSSY_COBBLESTONE_BRICK_STAIRS,
            SMOOTH_BASALT_BRICK_STAIRS,
            POLISHED_AMETHYST_BRICK_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            TUFF_STAIRS,
            POLISHED_TUFF_BRICK_STAIRS,
            CALCITE_STAIRS,
            POLISHED_CALCITE_BRICK_STAIRS,
            RHYOLITE_STAIRS,
            POLISHED_RHYOLITE_BRICK_STAIRS,
            SCHIST_STAIRS,
            POLISHED_SCHIST_BRICK_STAIRS,
            BLOODSTONE_STAIRS,
            POLISHED_BLOODSTONE_BRICK_STAIRS,
            BAMBOO_THATCH_STAIRS,
            POLISHED_AMETHYST_STAIRS,
            POLISHED_TUFF_STAIRS,
            POLISHED_CALCITE_STAIRS,
            POLISHED_RHYOLITE_STAIRS,
            POLISHED_SCHIST_STAIRS,
            POLISHED_BLOODSTONE_STAIRS
        );

        this.add(ItemTags.SLABS,
            MOSSY_BRICK_SLAB,
            COBBLESTONE_BRICK_SLAB,
            MOSSY_COBBLESTONE_BRICK_SLAB,
            SMOOTH_BASALT_BRICK_SLAB,
            POLISHED_AMETHYST_BRICK_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
            WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
            TUFF_SLAB,
            POLISHED_TUFF_BRICK_SLAB,
            CALCITE_SLAB,
            POLISHED_CALCITE_BRICK_SLAB,
            RHYOLITE_SLAB,
            POLISHED_RHYOLITE_BRICK_SLAB,
            SCHIST_SLAB,
            POLISHED_SCHIST_BRICK_SLAB,
            BLOODSTONE_SLAB,
            POLISHED_BLOODSTONE_BRICK_SLAB,
            BAMBOO_THATCH_SLAB,
            POLISHED_AMETHYST_SLAB,
            POLISHED_TUFF_SLAB,
            POLISHED_CALCITE_SLAB,
            POLISHED_RHYOLITE_SLAB,
            POLISHED_SCHIST_SLAB,
            POLISHED_BLOODSTONE_SLAB
        );

        this.add(ItemTags.DIRT, ROCKY_DIRT);
        this.add(ItemTags.PIGLIN_REPELLENTS, SOUL_LAMP);
        this.add(ItemTags.STONE_CRAFTING_MATERIALS, SCHIST, RHYOLITE);
        this.add(ItemTags.STONE_TOOL_MATERIALS, SCHIST, RHYOLITE);

        this.add(TwigsItemTags.PAPER_LANTERNS,
            PAPER_LANTERN,
            ALLIUM_PAPER_LANTERN,
            BLUE_ORCHID_PAPER_LANTERN,
            CRIMSON_ROOTS_PAPER_LANTERN,
            DANDELION_PAPER_LANTERN
        );

        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

        /* Item Group */

        // woods and plants
        this.add(TwigsItemGroups.createTabTag("woods_and_plants"),
            TWIG, PEBBLE, AZALEA_FLOWERS, BAMBOO_LEAVES,
            BAMBOO_THATCH, BAMBOO_THATCH_SLAB, BAMBOO_THATCH_STAIRS,
            BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO,
            STRIPPED_BAMBOO, STRIPPED_BAMBOO_MAT,
            ROCKY_DIRT, CRIMSON_SHROOMLAMP, WARPED_SHROOMLAMP
        );

        WOOD_SETS.forEach(set -> set.forEach((element, block) -> this.add(TwigsItemGroups.createTabTag("woods_and_plants"), block)));

        // stones
        this.add(TwigsItemGroups.createTabTag("stones"),
            CALCITE_STAIRS,
            CALCITE_SLAB,
            CALCITE_WALL,
            POLISHED_CALCITE,
            POLISHED_CALCITE_STAIRS,
            POLISHED_CALCITE_SLAB,
            POLISHED_CALCITE_BRICKS,
            POLISHED_CALCITE_BRICK_STAIRS,
            POLISHED_CALCITE_BRICK_SLAB,
            POLISHED_CALCITE_BRICK_WALL,
            CRACKED_POLISHED_CALCITE_BRICKS,
            SCHIST,
            SCHIST_STAIRS,
            SCHIST_SLAB,
            SCHIST_WALL,
            POLISHED_SCHIST,
            POLISHED_SCHIST_STAIRS,
            POLISHED_SCHIST_SLAB,
            POLISHED_SCHIST_BRICKS,
            POLISHED_SCHIST_BRICK_STAIRS,
            POLISHED_SCHIST_BRICK_SLAB,
            POLISHED_SCHIST_BRICK_WALL,
            CRACKED_POLISHED_SCHIST_BRICKS,
            COBBLESTONE_BRICKS,
            COBBLESTONE_BRICK_STAIRS,
            COBBLESTONE_BRICK_SLAB,
            COBBLESTONE_BRICK_WALL,
            CRACKED_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICK_STAIRS,
            MOSSY_COBBLESTONE_BRICK_SLAB,
            MOSSY_COBBLESTONE_BRICK_WALL,
            TUFF_STAIRS,
            TUFF_SLAB,
            TUFF_WALL,
            POLISHED_TUFF,
            POLISHED_TUFF_STAIRS,
            POLISHED_TUFF_SLAB,
            POLISHED_TUFF_BRICKS,
            POLISHED_TUFF_BRICK_STAIRS,
            POLISHED_TUFF_BRICK_SLAB,
            POLISHED_TUFF_BRICK_WALL,
            CRACKED_POLISHED_TUFF_BRICKS,
            POLISHED_BASALT_BRICKS,
            SMOOTH_BASALT_BRICKS,
            SMOOTH_BASALT_BRICK_STAIRS,
            SMOOTH_BASALT_BRICK_SLAB,
            SMOOTH_BASALT_BRICK_WALL,
            BLOODSTONE,
            BLOODSTONE_STAIRS,
            BLOODSTONE_SLAB,
            BLOODSTONE_WALL,
            POLISHED_BLOODSTONE,
            POLISHED_BLOODSTONE_STAIRS,
            POLISHED_BLOODSTONE_SLAB,
            POLISHED_BLOODSTONE_BRICKS,
            POLISHED_BLOODSTONE_BRICK_STAIRS,
            POLISHED_BLOODSTONE_BRICK_SLAB,
            POLISHED_BLOODSTONE_BRICK_WALL,
            CRACKED_POLISHED_BLOODSTONE_BRICKS,
            TWISTING_POLISHED_BLACKSTONE_BRICKS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
            WEEPING_POLISHED_BLACKSTONE_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
            WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
            RHYOLITE,
            RHYOLITE_STAIRS,
            RHYOLITE_SLAB,
            RHYOLITE_WALL,
            POLISHED_RHYOLITE,
            POLISHED_RHYOLITE_STAIRS,
            POLISHED_RHYOLITE_SLAB,
            POLISHED_RHYOLITE_BRICKS,
            POLISHED_RHYOLITE_BRICK_STAIRS,
            POLISHED_RHYOLITE_BRICK_SLAB,
            POLISHED_RHYOLITE_BRICK_WALL,
            CRACKED_POLISHED_RHYOLITE_BRICKS
        );

        // decoration
        this.add(TwigsItemGroups.createTabTag("decoration"),
            LAMP, SOUL_LAMP,
            CRIMSON_SHROOMLAMP, WARPED_SHROOMLAMP
        );

        for (Block block : Registry.BLOCK) {
            if (block instanceof PaperLanternBlock || block instanceof TableBlock) {
                this.add(TwigsItemGroups.createTabTag("decoration"), block);
            }
        }

        // miscellaneous
        this.add(TwigsItemGroups.createTabTag("miscellaneous"),
            CHISELED_BRICKS,
            CRACKED_BRICKS,
            MOSSY_BRICKS,
            MOSSY_BRICK_STAIRS,
            MOSSY_BRICK_SLAB,
            MOSSY_BRICK_WALL,
            COPPER_PILLAR,
            EXPOSED_COPPER_PILLAR,
            WEATHERED_COPPER_PILLAR,
            OXIDIZED_COPPER_PILLAR,
            WAXED_COPPER_PILLAR,
            WAXED_EXPOSED_COPPER_PILLAR,
            WAXED_WEATHERED_COPPER_PILLAR,
            WAXED_OXIDIZED_COPPER_PILLAR,
            POLISHED_AMETHYST,
            POLISHED_AMETHYST_STAIRS,
            POLISHED_AMETHYST_SLAB,
            CHISELED_POLISHED_AMETHYST,
            POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST_BRICK_STAIRS,
            POLISHED_AMETHYST_BRICK_SLAB,
            POLISHED_AMETHYST_BRICK_WALL,
            CRACKED_POLISHED_AMETHYST_BRICKS
        );
    }

    public void wood(WoodSet set, @Nullable TagKey<Item> logs) {
        if (!set.isVanilla()) {
            this.wood(set, ItemTags.PLANKS, WoodBlock.PLANKS);
            this.wood(set, ItemTags.SAPLINGS, WoodBlock.SAPLING);
            this.wood(set, logs, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);

            if (set.isFlammable()) {
                Optional.ofNullable(logs).ifPresent(tag -> { if (containsItem(logs)) this.add(ItemTags.LOGS_THAT_BURN, tag); });
            } else {
                Optional.ofNullable(logs).ifPresent(tag -> { if (containsItem(logs)) this.add(ItemTags.LOGS, tag); });
                this.wood(set, ItemTags.NON_FLAMMABLE_WOOD, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);
            }

            this.wood(set, ItemTags.LEAVES, WoodBlock.LEAVES);
            this.wood(set, ItemTags.WOODEN_SLABS, WoodBlock.SLAB);
            this.wood(set, ItemTags.WOODEN_PRESSURE_PLATES, WoodBlock.PRESSURE_PLATE);
            this.wood(set, ItemTags.WOODEN_FENCES, WoodBlock.FENCE);
            this.wood(set, ItemTags.WOODEN_TRAPDOORS, WoodBlock.TRAPDOOR);
            this.wood(set, ItemTags.WOODEN_STAIRS, WoodBlock.STAIRS);
            this.wood(set, ItemTags.WOODEN_BUTTONS, WoodBlock.BUTTON);
            this.wood(set, ItemTags.WOODEN_DOORS, WoodBlock.DOOR);
            this.wood(set, ItemTags.SIGNS, WoodBlock.SIGN);
        }
        if (set instanceof TwigsWoodSet twigs) this.add(TwigsItemTags.TABLES, twigs.getTable());
    }

    public void woods(WoodSet... sets) {
        for (WoodSet set : sets) this.wood(set, null);
    }

    public void wood(WoodSet set, @Nullable TagKey<Item> tag, WoodBlock... woods) {
        for (WoodBlock wood : woods) { if (set.contains(wood)) this.add(tag, set.get(wood).asItem()); }
    }

    private void add(TagKey<Item> tag, Block... blocks) {
        for (Block block : blocks) this.add(tag, block.asItem());
    }
}
