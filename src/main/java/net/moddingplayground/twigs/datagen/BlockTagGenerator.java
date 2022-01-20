package net.moddingplayground.twigs.datagen;

import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.block.wood.WoodBlock;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.datagen.impl.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.tag.TwigsBlockTags;
import org.jetbrains.annotations.Nullable;

import static net.moddingplayground.twigs.block.TwigsBlocks.*;

public class BlockTagGenerator extends AbstractTagGenerator<Block> {
    public BlockTagGenerator() {
        super(Twigs.MOD_ID, Registry.BLOCK);
    }

    @Override
    public void generate() {
        this.add(BlockTags.WALLS,
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

        this.add(BlockTags.STAIRS,
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

        this.add(BlockTags.SLABS,
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

        this.add(TwigsBlockTags.PAPER_LANTERNS, PAPER_LANTERN, ALLIUM_PAPER_LANTERN, BLUE_ORCHID_PAPER_LANTERN, CRIMSON_ROOTS_PAPER_LANTERN, DANDELION_PAPER_LANTERN);
        this.add(BlockTags.DIRT, ROCKY_DIRT);
        this.add(BlockTags.PIGLIN_REPELLENTS, SOUL_LAMP);

        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

        this.add(BlockTags.REPLACEABLE_PLANTS, AZALEA_FLOWERS);

        this.add(BlockTags.AXE_MINEABLE,
            STRIPPED_BAMBOO,
            STRIPPED_BUNDLED_BAMBOO,
            STRIPPED_BAMBOO_MAT,
            BUNDLED_BAMBOO,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP,
            AZALEA_FLOWERS
        );

        this.add(BlockTags.HOE_MINEABLE,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP
        );

        this.add(BlockTags.PICKAXE_MINEABLE,
            LAMP,
            SOUL_LAMP,
            ROCKY_DIRT,
            MOSSY_BRICKS,
            POLISHED_BASALT_BRICKS,
            CRACKED_BRICKS,
            CHISELED_BRICKS,
            MOSSY_BRICK_SLAB,
            MOSSY_BRICK_STAIRS,
            MOSSY_BRICK_WALL,
            SMOOTH_BASALT_BRICKS,
            SMOOTH_BASALT_BRICK_SLAB,
            SMOOTH_BASALT_BRICK_STAIRS,
            SMOOTH_BASALT_BRICK_WALL,
            MOSSY_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICK_SLAB,
            MOSSY_COBBLESTONE_BRICK_STAIRS,
            MOSSY_COBBLESTONE_BRICK_WALL,
            COBBLESTONE_BRICKS,
            COBBLESTONE_BRICK_SLAB,
            COBBLESTONE_BRICK_STAIRS,
            COBBLESTONE_BRICK_WALL,
            CRACKED_COBBLESTONE_BRICKS,
            POLISHED_AMETHYST,
            POLISHED_AMETHYST_SLAB,
            POLISHED_AMETHYST_STAIRS,
            POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST_BRICK_SLAB,
            POLISHED_AMETHYST_BRICK_STAIRS,
            POLISHED_AMETHYST_BRICK_WALL,
            CHISELED_POLISHED_AMETHYST,
            CRACKED_POLISHED_AMETHYST_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
            WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
            TWISTING_POLISHED_BLACKSTONE_BRICKS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
            SCHIST,
            SCHIST_SLAB,
            SCHIST_STAIRS,
            SCHIST_WALL,
            POLISHED_SCHIST,
            POLISHED_SCHIST_SLAB,
            POLISHED_SCHIST_STAIRS,
            CRACKED_POLISHED_SCHIST_BRICKS,
            POLISHED_SCHIST_BRICKS,
            POLISHED_SCHIST_BRICK_SLAB,
            POLISHED_SCHIST_BRICK_STAIRS,
            POLISHED_SCHIST_BRICK_WALL,
            RHYOLITE,
            RHYOLITE_SLAB,
            RHYOLITE_STAIRS,
            RHYOLITE_WALL,
            POLISHED_RHYOLITE,
            POLISHED_RHYOLITE_SLAB,
            POLISHED_RHYOLITE_STAIRS,
            CRACKED_POLISHED_RHYOLITE_BRICKS,
            POLISHED_RHYOLITE_BRICKS,
            POLISHED_RHYOLITE_BRICK_SLAB,
            POLISHED_RHYOLITE_BRICK_STAIRS,
            POLISHED_RHYOLITE_BRICK_WALL,
            BLOODSTONE,
            BLOODSTONE_SLAB,
            BLOODSTONE_STAIRS,
            BLOODSTONE_WALL,
            POLISHED_BLOODSTONE,
            POLISHED_BLOODSTONE_SLAB,
            POLISHED_BLOODSTONE_STAIRS,
            CRACKED_POLISHED_BLOODSTONE_BRICKS,
            POLISHED_BLOODSTONE_BRICKS,
            POLISHED_BLOODSTONE_BRICK_SLAB,
            POLISHED_BLOODSTONE_BRICK_STAIRS,
            POLISHED_BLOODSTONE_BRICK_WALL,
            TUFF_SLAB,
            TUFF_STAIRS,
            TUFF_WALL,
            POLISHED_TUFF,
            POLISHED_TUFF_SLAB,
            POLISHED_TUFF_STAIRS,
            CRACKED_POLISHED_TUFF_BRICKS,
            POLISHED_TUFF_BRICKS,
            POLISHED_TUFF_BRICK_SLAB,
            POLISHED_TUFF_BRICK_STAIRS,
            POLISHED_TUFF_BRICK_WALL,
            CALCITE_SLAB,
            CALCITE_STAIRS,
            CALCITE_WALL,
            POLISHED_CALCITE,
            POLISHED_CALCITE_SLAB,
            POLISHED_CALCITE_STAIRS,
            CRACKED_POLISHED_CALCITE_BRICKS,
            POLISHED_CALCITE_BRICKS,
            POLISHED_CALCITE_BRICK_SLAB,
            POLISHED_CALCITE_BRICK_STAIRS,
            POLISHED_CALCITE_BRICK_WALL,
            COPPER_PILLAR,
            WAXED_COPPER_PILLAR,
            EXPOSED_COPPER_PILLAR,
            WAXED_EXPOSED_COPPER_PILLAR,
            WEATHERED_COPPER_PILLAR,
            WAXED_WEATHERED_COPPER_PILLAR,
            OXIDIZED_COPPER_PILLAR,
            WAXED_OXIDIZED_COPPER_PILLAR
        );
    }

    public void wood(WoodSet set, @Nullable Tag.Identified<Block> logs) {
        if (!set.isVanilla()) {
            this.wood(set, BlockTags.PLANKS, WoodBlock.PLANKS);
            this.wood(set, BlockTags.SAPLINGS, WoodBlock.SAPLING);
            this.wood(set, BlockTags.FLOWER_POTS, WoodBlock.POTTED_SAPLING);
            this.wood(set, logs, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);

            if (set.isFlammable()) {
                if (logs != null && !logs.values().isEmpty()) this.add(BlockTags.LOGS_THAT_BURN, logs);
            } else {
                if (logs != null && !logs.values().isEmpty()) this.add(BlockTags.LOGS, logs);
                this.wood(set, BlockTags.NON_FLAMMABLE_WOOD, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);
            }

            this.wood(set, BlockTags.LEAVES, WoodBlock.LEAVES);
            this.wood(set, BlockTags.WOODEN_SLABS, WoodBlock.SLAB);
            this.wood(set, BlockTags.WOODEN_PRESSURE_PLATES, WoodBlock.PRESSURE_PLATE);
            this.wood(set, BlockTags.WOODEN_FENCES, WoodBlock.FENCE);
            this.wood(set, BlockTags.WOODEN_TRAPDOORS, WoodBlock.TRAPDOOR);
            this.wood(set, BlockTags.FENCE_GATES, WoodBlock.FENCE_GATE);
            this.wood(set, BlockTags.WOODEN_STAIRS, WoodBlock.STAIRS);
            this.wood(set, BlockTags.WOODEN_BUTTONS, WoodBlock.BUTTON);
            this.wood(set, BlockTags.WOODEN_DOORS, WoodBlock.DOOR);
            this.wood(set, BlockTags.STANDING_SIGNS, WoodBlock.SIGN);
            this.wood(set, BlockTags.WALL_SIGNS, WoodBlock.WALL_SIGN);
            }
        if (set instanceof TwigsWoodSet twigs) this.add(TwigsBlockTags.TABLES, twigs.getTable());
    }

    public void woods(WoodSet... sets) {
        for (WoodSet set : sets) this.wood(set, null);
    }

    public void wood(WoodSet set, @Nullable Tag.Identified<Block> tag, WoodBlock... woods) {
        for (WoodBlock wood : woods) { if (set.contains(wood)) this.add(tag, set.get(wood)); }
    }
}
