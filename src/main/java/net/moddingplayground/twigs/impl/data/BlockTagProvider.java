package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.PaperLanternBlock;
import net.moddingplayground.twigs.api.block.TableBlock;
import net.moddingplayground.twigs.api.tag.TwigsBlockTags;

import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataGenerator gen) {
        super(gen);
    }

    @Override
    protected void generateTags() {
        Registry.BLOCK.forEach(block -> {
            Identifier id = Registry.BLOCK.getId(block);
            if (id.getNamespace().equals(Twigs.MOD_ID)) {
                if (block instanceof WallBlock) this.getOrCreateTagBuilder(BlockTags.WALLS).add(block);
                if (block instanceof SlabBlock) this.getOrCreateTagBuilder(BlockTags.SLABS).add(block);
                if (block instanceof StairsBlock) this.getOrCreateTagBuilder(BlockTags.STAIRS).add(block);
                if (block instanceof TableBlock) this.getOrCreateTagBuilder(TwigsBlockTags.TABLES).add(block);
                if (block instanceof PaperLanternBlock) this.getOrCreateTagBuilder(TwigsBlockTags.PAPER_LANTERNS).add(block);
            }
        });

        // rocky dirt
        this.getOrCreateTagBuilder(BlockTags.DIRT).add(ROCKY_DIRT);
        this.getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON).add(ROCKY_DIRT);
        this.getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ROCKY_DIRT);
        this.getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON).add(ROCKY_DIRT);

        // piglin repellents
        this.getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS).add(SOUL_LAMP);

        // base stone overworld
        this.getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(
            SCHIST,
            RHYOLITE
        );

        // azalea
        this.getOrCreateTagBuilder(BlockTags.REPLACEABLE_PLANTS).add(
            AZALEA_FLOWERS,
            PETRIFIED_LICHEN
        );

        this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(POTTED_AZALEA_FLOWERS);

        // wood
        this.getOrCreateTagBuilder(BlockTags.PLANKS).add(STRIPPED_BAMBOO_PLANKS);
        this.getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(STRIPPED_BAMBOO_SIGN);
        this.getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(STRIPPED_BAMBOO_WALL_SIGN);
        this.getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(STRIPPED_BAMBOO_FENCE_GATE);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(STRIPPED_BAMBOO_BUTTON);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(STRIPPED_BAMBOO_DOOR);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(STRIPPED_BAMBOO_FENCE);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(STRIPPED_BAMBOO_PRESSURE_PLATE);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(STRIPPED_BAMBOO_SLAB);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(STRIPPED_BAMBOO_STAIRS);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(STRIPPED_BAMBOO_TRAPDOOR);

        // amethyst
        this.getOrCreateTagBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS).add(
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

        // mineable axe
        this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
            STRIPPED_BAMBOO,
            STRIPPED_BUNDLED_BAMBOO,
            STRIPPED_BAMBOO_MAT,
            BUNDLED_BAMBOO,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP,
            AZALEA_FLOWERS
        );

        // mineable hoe
        this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP,
            BAMBOO_THATCH,
            BAMBOO_THATCH_STAIRS,
            BAMBOO_THATCH_SLAB
        );

        // mineable pickaxe
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
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
            WAXED_OXIDIZED_COPPER_PILLAR,
            ENDER_MESH,
            PETRIFIED_LICHEN
        );
    }
}
