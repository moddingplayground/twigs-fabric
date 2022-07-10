package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.math.Direction;
import net.moddingplayground.twigs.api.block.TwigsProperties;

import java.util.function.Function;

import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS));

    protected BlockLootTableProvider(FabricDataGenerator gen) {
        super(gen);
    }

    @Override
    protected void generateBlockLootTables() {
        this.addDrops(
            TWIG,
            ROCKY_DIRT,
            PEBBLE,
            SEA_SHELL,
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
            STRIPPED_BAMBOO_MAT,
            CHISELED_BRICKS,
            CRACKED_BRICKS,
            MOSSY_BRICKS,
            MOSSY_BRICK_STAIRS,
            MOSSY_BRICK_WALL,
            POLISHED_BASALT_BRICKS,
            SMOOTH_BASALT_BRICKS,
            SMOOTH_BASALT_BRICK_STAIRS,
            SMOOTH_BASALT_BRICK_WALL,
            LAMP,
            SOUL_LAMP,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP,
            COBBLESTONE_BRICKS,
            COBBLESTONE_BRICK_STAIRS,
            COBBLESTONE_BRICK_WALL,
            CRACKED_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICK_STAIRS,
            MOSSY_COBBLESTONE_BRICK_WALL,
            POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST_BRICK_STAIRS,
            POLISHED_AMETHYST_BRICK_WALL,
            CRACKED_POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST,
            CHISELED_POLISHED_AMETHYST,
            POLISHED_AMETHYST_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICKS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
            WEEPING_POLISHED_BLACKSTONE_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
            TUFF_STAIRS,
            TUFF_WALL,
            POLISHED_TUFF,
            POLISHED_TUFF_STAIRS,
            POLISHED_TUFF_BRICKS,
            POLISHED_TUFF_BRICK_STAIRS,
            POLISHED_TUFF_BRICK_WALL,
            CRACKED_POLISHED_TUFF_BRICKS,
            CALCITE_STAIRS,
            CALCITE_WALL,
            POLISHED_CALCITE,
            POLISHED_CALCITE_STAIRS,
            POLISHED_CALCITE_BRICKS,
            POLISHED_CALCITE_BRICK_STAIRS,
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
            RHYOLITE_WALL,
            POLISHED_RHYOLITE,
            POLISHED_RHYOLITE_STAIRS,
            POLISHED_RHYOLITE_BRICKS,
            POLISHED_RHYOLITE_BRICK_STAIRS,
            POLISHED_RHYOLITE_BRICK_WALL,
            CRACKED_POLISHED_RHYOLITE_BRICKS,
            SCHIST,
            SCHIST_STAIRS,
            SCHIST_WALL,
            POLISHED_SCHIST,
            POLISHED_SCHIST_STAIRS,
            POLISHED_SCHIST_BRICKS,
            POLISHED_SCHIST_BRICK_STAIRS,
            POLISHED_SCHIST_BRICK_WALL,
            CRACKED_POLISHED_SCHIST_BRICKS,
            BLOODSTONE,
            BLOODSTONE_STAIRS,
            BLOODSTONE_WALL,
            POLISHED_BLOODSTONE,
            POLISHED_BLOODSTONE_STAIRS,
            POLISHED_BLOODSTONE_BRICKS,
            POLISHED_BLOODSTONE_BRICK_STAIRS,
            POLISHED_BLOODSTONE_BRICK_WALL,
            CRACKED_POLISHED_BLOODSTONE_BRICKS,
            STRIPPED_BAMBOO_PLANKS,
            STRIPPED_BAMBOO_SIGN,
            STRIPPED_BAMBOO_BUTTON,
            STRIPPED_BAMBOO_FENCE,
            STRIPPED_BAMBOO_FENCE_GATE,
            STRIPPED_BAMBOO_PRESSURE_PLATE,
            STRIPPED_BAMBOO_STAIRS,
            STRIPPED_BAMBOO_TRAPDOOR,
            OAK_TABLE,
            SPRUCE_TABLE,
            BIRCH_TABLE,
            JUNGLE_TABLE,
            ACACIA_TABLE,
            DARK_OAK_TABLE,
            MANGROVE_TABLE,
            CRIMSON_TABLE,
            WARPED_TABLE,
            STRIPPED_BAMBOO_TABLE,
            ENDER_MESH
        );

        this.addDrops(BlockLootTableGenerator::slabDrops,
            BAMBOO_THATCH_SLAB,
            MOSSY_BRICK_SLAB,
            SMOOTH_BASALT_BRICK_SLAB,
            COBBLESTONE_BRICK_SLAB,
            MOSSY_COBBLESTONE_BRICK_SLAB,
            POLISHED_AMETHYST_BRICK_SLAB,
            POLISHED_AMETHYST_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
            WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
            TUFF_SLAB,
            POLISHED_TUFF_SLAB,
            POLISHED_TUFF_BRICK_SLAB,
            CALCITE_SLAB,
            POLISHED_CALCITE_SLAB,
            POLISHED_CALCITE_BRICK_SLAB,
            RHYOLITE_SLAB,
            POLISHED_RHYOLITE_SLAB,
            POLISHED_RHYOLITE_BRICK_SLAB,
            SCHIST_SLAB,
            POLISHED_SCHIST_SLAB,
            POLISHED_SCHIST_BRICK_SLAB,
            BLOODSTONE_SLAB,
            POLISHED_BLOODSTONE_SLAB,
            POLISHED_BLOODSTONE_BRICK_SLAB,
            STRIPPED_BAMBOO_SLAB
        );

        this.addDrop(STRIPPED_BAMBOO_DOOR, BlockLootTableGenerator::addDoorDrop);

        this.addDrop(AZALEA_FLOWERS, block -> multifaceGrowthDrops(block, WITH_SHEARS));
        this.addDrops(BlockLootTableProvider::multifaceGrowthDrops, PETRIFIED_LICHEN);
        this.addDrop(BAMBOO_LEAVES, this::dropsLayer1_4);

        this.addPottedPlantDrop(POTTED_AZALEA_FLOWERS);
    }

    public void addDrops(Block... blocks) {
        for (Block block : blocks) this.addDrop(block);
    }

    public void addDrops(Function<Block, LootTable.Builder> function, Block... blocks) {
        for (Block block : blocks) this.addDrop(block, function);
    }

    public LootTable.Builder dropsLayer1_4(Block block) {
        return LootTable.builder().pool(LootPool.builder().with(
            AlternativeEntry.builder(
                ItemEntry.builder(block)
                         .apply(addPerLayer1_4(block, 1.0F, 2))
                         .apply(addPerLayer1_4(block, 2.0F, 3))
                         .apply(addPerLayer1_4(block, 3.0F, 4))
            )
        ));
    }

    public LootFunction.Builder addPerLayer1_4(Block block, float count, int layer) {
        return SetCountLootFunction.builder(ConstantLootNumberProvider.create(count), true).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(TwigsProperties.LAYERS_1_4, layer)));
    }

    public static LootTable.Builder multifaceGrowthDrops(Block block) {
        return LootTable.builder()
                        .pool(
                            LootPool.builder()
                                    .with(
                                        BlockLootTableGenerator.applyExplosionDecay(block,
                                            ItemEntry.builder(block)
                                                     .apply(Direction.values(), direction ->
                                                         SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f), true)
                                                                             .conditionally(BlockStatePropertyLootCondition.builder(block).properties(
                                                                                 StatePredicate.Builder.create()
                                                                                                       .exactMatch(MultifaceGrowthBlock.getProperty(direction), true)
                                                                             ))
                                                     )
                                                     .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(-1.0f), true)))
                                    )
                        );
    }
}
