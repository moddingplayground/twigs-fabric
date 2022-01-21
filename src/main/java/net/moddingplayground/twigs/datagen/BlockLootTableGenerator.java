package net.moddingplayground.twigs.datagen;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BambooLeaves;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.moddingplayground.toymaker.api.generator.loot.AbstractBlockLootTableGenerator;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TwigsProperties;
import net.moddingplayground.twigs.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.block.wood.WoodBlock;
import net.moddingplayground.twigs.block.wood.WoodSet;

import java.util.function.Consumer;

import static net.minecraft.data.server.BlockLootTableGenerator.*;
import static net.moddingplayground.twigs.block.TwigsBlocks.*;

public class BlockLootTableGenerator extends AbstractBlockLootTableGenerator {
    private static final float[] SAPLING_DROP_CHANCES = {1 / 20f, 1 / 16f, 1 / 12f, 1 / 10f};

    public BlockLootTableGenerator() {
        super(Twigs.MOD_ID);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public void generate() {
        this.adds(
            TWIG,
            ROCKY_DIRT,
            PEBBLE,
            PAPER_LANTERN,
            ALLIUM_PAPER_LANTERN,
            BLUE_ORCHID_PAPER_LANTERN,
            CRIMSON_ROOTS_PAPER_LANTERN,
            DANDELION_PAPER_LANTERN,
            STRIPPED_BAMBOO,
            STRIPPED_BUNDLED_BAMBOO,
            BUNDLED_BAMBOO,
            BAMBOO_THATCH,
            BAMBOO_THATCH_STAIRS,
            BAMBOO_THATCH_SLAB,
            STRIPPED_BAMBOO_MAT,
            CHISELED_BRICKS,
            CRACKED_BRICKS,
            MOSSY_BRICKS,
            MOSSY_BRICK_STAIRS,
            MOSSY_BRICK_SLAB,
            MOSSY_BRICK_WALL,
            POLISHED_BASALT_BRICKS,
            SMOOTH_BASALT_BRICKS,
            SMOOTH_BASALT_BRICK_STAIRS,
            SMOOTH_BASALT_BRICK_SLAB,
            SMOOTH_BASALT_BRICK_WALL,
            LAMP,
            SOUL_LAMP,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP,
            COBBLESTONE_BRICKS,
            COBBLESTONE_BRICK_STAIRS,
            COBBLESTONE_BRICK_SLAB,
            COBBLESTONE_BRICK_WALL,
            CRACKED_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICK_STAIRS,
            MOSSY_COBBLESTONE_BRICK_SLAB,
            MOSSY_COBBLESTONE_BRICK_WALL,
            POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST_BRICK_STAIRS,
            POLISHED_AMETHYST_BRICK_SLAB,
            POLISHED_AMETHYST_BRICK_WALL,
            CRACKED_POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST,
            CHISELED_POLISHED_AMETHYST,
            POLISHED_AMETHYST_STAIRS,
            POLISHED_AMETHYST_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICKS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
            WEEPING_POLISHED_BLACKSTONE_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
            WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
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
            COPPER_PILLAR,
            EXPOSED_COPPER_PILLAR,
            WEATHERED_COPPER_PILLAR,
            OXIDIZED_COPPER_PILLAR,
            WAXED_COPPER_PILLAR,
            WAXED_EXPOSED_COPPER_PILLAR,
            WAXED_WEATHERED_COPPER_PILLAR,
            WAXED_OXIDIZED_COPPER_PILLAR,
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
            CRACKED_POLISHED_RHYOLITE_BRICKS,
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
            CRACKED_POLISHED_BLOODSTONE_BRICKS
        );

        this.add(AZALEA_FLOWERS, b -> glowLichenDrops(b));
        this.add(BAMBOO_LEAVES, this::dropsLayer14);

        this.addPottedPlantDrop(POTTED_AZALEA_FLOWERS);
        this.addPottedPlantDrop(POTTED_BAMBOO_LEAVES);

        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

        this.add(getAdditiveLootTable(Blocks.BAMBOO), new LootTable.Builder().pool(pool().with(
            ItemEntry.builder(BAMBOO_LEAVES)
                     .conditionally(InvertedLootCondition.builder(stateCond(Blocks.BAMBOO, BambooBlock.LEAVES, BambooLeaves.NONE)))
        )));
    }

    public void adds(Block... blocks) {
        for (Block block : blocks) this.add(block);
    }

    public LootTable.Builder dropsLayer14(Block block) {
        return LootTable.builder().pool(
            pool().with(
                AlternativeEntry.builder(
                    ItemEntry.builder(block)
                             .apply(conditionalSetCountLayer14(block, 1.0f, 2))
                             .apply(conditionalSetCountLayer14(block, 2.0f, 3))
                             .apply(conditionalSetCountLayer14(block, 3.0f, 4))
                )
            )
        );
    }

    public LootFunction.Builder conditionalSetCountLayer14(Block block, float count, int layer) {
        return SetCountLootFunction.builder(ConstantLootNumberProvider.create(count), true)
                                   .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                                                 .properties(StatePredicate.Builder.create()
                                                                                                                   .exactMatch(TwigsProperties.LAYERS_1_4, layer)));
    }

    public void woods(WoodSet... sets) {
        for (WoodSet set : sets) this.wood(set);
    }

    public void wood(WoodSet set) {
        if (!set.isVanilla()) {
            this.wood(set, WoodBlock.PLANKS);
            this.wood(set, WoodBlock.SAPLING);
            this.wood(set, WoodBlock.POTTED_SAPLING, this::addPottedPlantDrop);
            this.wood(set, WoodBlock.LOG);
            this.wood(set, WoodBlock.STRIPPED_LOG);
            this.wood(set, WoodBlock.WOOD);
            this.wood(set, WoodBlock.STRIPPED_WOOD);
            this.wood(set, WoodBlock.LEAVES, b -> this.add(b, dropLeaves(b, set.get(WoodBlock.SAPLING), SAPLING_DROP_CHANCES)));
            this.wood(set, WoodBlock.SLAB, this::addSlabDrop);
            this.wood(set, WoodBlock.STAIRS);
            this.wood(set, WoodBlock.FENCE);
            this.wood(set, WoodBlock.DOOR, b -> this.add(b, this.dropsDoor(b)));
            this.wood(set, WoodBlock.TRAPDOOR);
            this.wood(set, WoodBlock.FENCE_GATE);
            this.wood(set, WoodBlock.PRESSURE_PLATE);
            this.wood(set, WoodBlock.BUTTON);
            this.wood(set, WoodBlock.SIGN);
            this.wood(set, WoodBlock.WALL_SIGN);
        }
        if (set instanceof TwigsWoodSet twigs) this.add(twigs.getTable());
    }

    public void wood(WoodSet set, WoodBlock wood, Consumer<Block> factory) {
        if (set.contains(wood)) factory.accept(set.get(wood));
    }

    public void wood(WoodSet set, WoodBlock wood) {
        wood(set, wood, this::add);
    }
}
