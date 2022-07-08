package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.ItemTags;
import net.moddingplayground.frame.api.toymaker.v0.TagHelpers;
import net.moddingplayground.twigs.api.item.TwigsItemGroups;
import net.moddingplayground.twigs.api.tag.TwigsBlockTags;
import net.moddingplayground.twigs.api.tag.TwigsItemTags;

import static net.moddingplayground.twigs.api.item.TwigsItems.*;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    private final BlockTagProvider blockTagProvider;

    public ItemTagProvider(FabricDataGenerator gen, BlockTagProvider blockTagProvider) {
        super(gen, blockTagProvider);
        this.blockTagProvider = blockTagProvider;
    }

    @Override
    protected void generateTags() {
        TagHelpers.copyAll(this.blockTagProvider, this);

        this.copy(TwigsBlockTags.TABLES, TwigsItemTags.TABLES);
        this.copy(TwigsBlockTags.PAPER_LANTERNS, TwigsItemTags.PAPER_LANTERNS);

        this.getOrCreateTagBuilder(ItemTags.PIGLIN_REPELLENTS).add(SOUL_LAMP);
        this.getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(SCHIST, RHYOLITE);
        this.getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(SCHIST, RHYOLITE);

        this.getOrCreateTagBuilder(ItemTags.BOATS).add(STRIPPED_BAMBOO_BOAT);
        this.getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(STRIPPED_BAMBOO_CHEST_BOAT);

        /* Item Group */

        // woods and plants
        this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("natural")).add(
            TWIG,
            PEBBLE,
            SEA_SHELL,
            AZALEA_FLOWERS,
            BAMBOO_LEAVES,
            BAMBOO_THATCH,
            BAMBOO_THATCH_SLAB,
            BAMBOO_THATCH_STAIRS,
            BUNDLED_BAMBOO,
            STRIPPED_BUNDLED_BAMBOO,
            ROCKY_DIRT,
            STRIPPED_BAMBOO,
            STRIPPED_BAMBOO_PLANKS,
            STRIPPED_BAMBOO_SLAB,
            STRIPPED_BAMBOO_FENCE,
            STRIPPED_BAMBOO_FENCE_GATE,
            STRIPPED_BAMBOO_STAIRS,
            STRIPPED_BAMBOO_BUTTON,
            STRIPPED_BAMBOO_PRESSURE_PLATE,
            STRIPPED_BAMBOO_DOOR,
            STRIPPED_BAMBOO_TRAPDOOR,
            STRIPPED_BAMBOO_SIGN,
            STRIPPED_BAMBOO_MAT,
            STRIPPED_BAMBOO_BOAT,
            STRIPPED_BAMBOO_CHEST_BOAT
        );

        // stones
        this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("stones")).add(
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
        this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("decoration")).add(
            TWIG,
            PEBBLE,
            SEA_SHELL,
            AZALEA_FLOWERS,
            BAMBOO_LEAVES,
            LAMP,
            SOUL_LAMP,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP
        ).addTag(TwigsItemTags.PAPER_LANTERNS).addTag(TwigsItemTags.TABLES);

        // miscellaneous
        this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("miscellaneous")).add(
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
}
