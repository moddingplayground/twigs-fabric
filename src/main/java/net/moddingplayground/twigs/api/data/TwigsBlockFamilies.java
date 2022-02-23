package net.moddingplayground.twigs.api.data;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

import java.util.HashMap;

import static net.minecraft.block.Blocks.*;
import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;

public interface TwigsBlockFamilies {
    HashMap<Block, BlockFamily> FAMILIES = Maps.newHashMap();

    BlockFamily BAMBOO_THATCH = register(TwigsBlocks.BAMBOO_THATCH)
        .group("bamboo_thatch")
        .stairs(BAMBOO_THATCH_STAIRS)
        .slab(BAMBOO_THATCH_SLAB)
        .build();

    BlockFamily BRICK = register(BRICKS)
        .group("brick")
        .chiseled(CHISELED_BRICKS)
        .cracked(CRACKED_BRICKS)
        .slab(BRICK_SLAB)
        .build();

    BlockFamily MOSSY_BRICK = register(MOSSY_BRICKS)
        .group("mossy_brick")
        .wall(MOSSY_BRICK_WALL)
        .stairs(MOSSY_BRICK_STAIRS)
        .slab(MOSSY_BRICK_SLAB)
        .build();

    BlockFamily SMOOTH_BASALT = register(Blocks.SMOOTH_BASALT)
        .group("smooth_basalt")
        .polished(SMOOTH_BASALT_BRICKS)
        .build();

    BlockFamily POLISHED_BASALT = register(Blocks.POLISHED_BASALT)
        .group("polished_basalt")
        .polished(POLISHED_BASALT_BRICKS)
        .build();

    BlockFamily SMOOTH_BASALT_BRICK = register(SMOOTH_BASALT_BRICKS)
        .group("smooth_basalt_brick")
        .wall(SMOOTH_BASALT_BRICK_WALL)
        .stairs(SMOOTH_BASALT_BRICK_STAIRS)
        .slab(SMOOTH_BASALT_BRICK_SLAB)
        .build();

    BlockFamily COBBLESTONE = register(Blocks.COBBLESTONE)
        .group("cobblestone")
        .polished(COBBLESTONE_BRICKS)
        .build();

    BlockFamily COBBLESTONE_BRICK = register(COBBLESTONE_BRICKS)
        .group("cobblestone_brick")
        .cracked(CRACKED_COBBLESTONE_BRICKS)
        .wall(COBBLESTONE_BRICK_WALL)
        .stairs(COBBLESTONE_BRICK_STAIRS)
        .slab(COBBLESTONE_BRICK_SLAB)
        .build();

    BlockFamily MOSSY_COBBLESTONE_BRICK = register(MOSSY_COBBLESTONE_BRICKS)
        .group("mossy_cobblestone_brick")
        .wall(MOSSY_COBBLESTONE_BRICK_WALL)
        .stairs(MOSSY_COBBLESTONE_BRICK_STAIRS)
        .slab(MOSSY_COBBLESTONE_BRICK_SLAB)
        .build();

    BlockFamily AMETHYST = register(AMETHYST_BLOCK)
        .group("amethyst")
        .polished(TwigsBlocks.POLISHED_AMETHYST)
        .build();

    BlockFamily POLISHED_AMETHYST = register(TwigsBlocks.POLISHED_AMETHYST)
        .group("polished_amethyst")
        .polished(POLISHED_AMETHYST_BRICKS)
        .chiseled(CHISELED_POLISHED_AMETHYST)
        .stairs(POLISHED_AMETHYST_STAIRS)
        .slab(POLISHED_AMETHYST_SLAB)
        .build();

    BlockFamily POLISHED_AMETHYST_BRICK = register(POLISHED_AMETHYST_BRICKS)
        .group("polished_amethyst_brick")
        .cracked(CRACKED_POLISHED_AMETHYST_BRICKS)
        .stairs(POLISHED_AMETHYST_BRICK_STAIRS)
        .slab(POLISHED_AMETHYST_BRICK_SLAB)
        .build();

    BlockFamily TWISTING_POLISHED_BLACKSTONE_BRICK = register(TWISTING_POLISHED_BLACKSTONE_BRICKS)
        .group("twisting_polished_blackstone_brick")
        .wall(TWISTING_POLISHED_BLACKSTONE_BRICK_WALL)
        .stairs(TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS)
        .slab(TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB)
        .build();

    BlockFamily WEEPING_POLISHED_BLACKSTONE_BRICK = register(WEEPING_POLISHED_BLACKSTONE_BRICKS)
        .group("weeping_polished_blackstone_brick")
        .wall(WEEPING_POLISHED_BLACKSTONE_BRICK_WALL)
        .stairs(WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS)
        .slab(WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB)
        .build();

    BlockFamily TUFF = register(Blocks.TUFF)
        .group("tuff")
        .polished(TwigsBlocks.POLISHED_TUFF)
        .wall(TUFF_WALL)
        .stairs(TUFF_STAIRS)
        .slab(TUFF_SLAB)
        .build();

    BlockFamily POLISHED_TUFF = register(TwigsBlocks.POLISHED_TUFF)
        .group("polished_tuff")
        .polished(POLISHED_TUFF_BRICKS)
        .stairs(POLISHED_TUFF_STAIRS)
        .slab(POLISHED_TUFF_SLAB)
        .build();

    BlockFamily POLISHED_TUFF_BRICK = register(POLISHED_TUFF_BRICKS)
        .group("polished_tuff_brick")
        .cracked(CRACKED_POLISHED_TUFF_BRICKS)
        .wall(POLISHED_TUFF_BRICK_WALL)
        .stairs(POLISHED_TUFF_BRICK_STAIRS)
        .slab(POLISHED_TUFF_BRICK_SLAB)
        .build();

    BlockFamily CALCITE = register(Blocks.CALCITE)
        .group("calcite")
        .polished(TwigsBlocks.POLISHED_CALCITE)
        .wall(CALCITE_WALL)
        .stairs(CALCITE_STAIRS)
        .slab(CALCITE_SLAB)
        .build();

    BlockFamily POLISHED_CALCITE = register(TwigsBlocks.POLISHED_CALCITE)
        .group("polished_calcite")
        .stairs(POLISHED_CALCITE_STAIRS)
        .slab(POLISHED_CALCITE_SLAB)
        .build();

    BlockFamily POLISHED_CALCITE_BRICK = register(POLISHED_CALCITE_BRICKS)
        .group("polished_calcite_brick")
        .cracked(CRACKED_POLISHED_CALCITE_BRICKS)
        .wall(POLISHED_CALCITE_BRICK_WALL)
        .stairs(POLISHED_CALCITE_BRICK_STAIRS)
        .slab(POLISHED_CALCITE_BRICK_SLAB)
        .build();

    BlockFamily COPPER = register(COPPER_BLOCK)
        .group("copper")
        .chiseled(COPPER_PILLAR)
        .slab(CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_COPPER = register(WAXED_COPPER_BLOCK)
        .group("copper")
        .chiseled(WAXED_COPPER_PILLAR)
        .slab(WAXED_CUT_COPPER_SLAB)
        .build();

    BlockFamily EXPOSED_COPPER = register(Blocks.EXPOSED_COPPER)
        .group("copper")
        .chiseled(EXPOSED_COPPER_PILLAR)
        .slab(EXPOSED_CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_EXPOSED_COPPER = register(Blocks.WAXED_EXPOSED_COPPER)
        .group("copper")
        .chiseled(WAXED_EXPOSED_COPPER_PILLAR)
        .slab(WAXED_EXPOSED_CUT_COPPER_SLAB)
        .build();

    BlockFamily WEATHERED_COPPER = register(Blocks.WEATHERED_COPPER)
        .group("copper")
        .chiseled(WEATHERED_COPPER_PILLAR)
        .slab(WEATHERED_CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_WEATHERED_COPPER = register(Blocks.WAXED_WEATHERED_COPPER)
        .group("copper")
        .chiseled(WAXED_WEATHERED_COPPER_PILLAR)
        .slab(WAXED_WEATHERED_CUT_COPPER_SLAB)
        .build();

    BlockFamily OXIDIZED_COPPER = register(Blocks.OXIDIZED_COPPER)
        .group("copper")
        .chiseled(OXIDIZED_COPPER_PILLAR)
        .slab(OXIDIZED_CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_OXIDIZED_COPPER = register(Blocks.WAXED_OXIDIZED_COPPER)
        .group("copper")
        .chiseled(WAXED_OXIDIZED_COPPER_PILLAR)
        .slab(WAXED_OXIDIZED_CUT_COPPER_SLAB)
        .build();

    BlockFamily RHYOLITE = register(TwigsBlocks.RHYOLITE)
        .group("rhyolite")
        .polished(TwigsBlocks.POLISHED_RHYOLITE)
        .wall(RHYOLITE_WALL)
        .stairs(RHYOLITE_STAIRS)
        .slab(RHYOLITE_SLAB)
        .build();

    BlockFamily POLISHED_RHYOLITE = register(TwigsBlocks.POLISHED_RHYOLITE)
        .group("polished_rhyolite")
        .polished(POLISHED_RHYOLITE_BRICKS)
        .stairs(POLISHED_RHYOLITE_STAIRS)
        .slab(POLISHED_RHYOLITE_SLAB)
        .build();

    BlockFamily POLISHED_RHYOLITE_BRICK = register(POLISHED_RHYOLITE_BRICKS)
        .group("polished_rhyolite_brick")
        .cracked(CRACKED_POLISHED_RHYOLITE_BRICKS)
        .wall(POLISHED_RHYOLITE_BRICK_WALL)
        .stairs(POLISHED_RHYOLITE_BRICK_STAIRS)
        .slab(POLISHED_RHYOLITE_BRICK_SLAB)
        .build();

    BlockFamily SCHIST = register(TwigsBlocks.SCHIST)
        .group("schist")
        .polished(TwigsBlocks.POLISHED_SCHIST)
        .wall(SCHIST_WALL)
        .stairs(SCHIST_STAIRS)
        .slab(SCHIST_SLAB)
        .build();

    BlockFamily POLISHED_SCHIST = register(TwigsBlocks.POLISHED_SCHIST)
        .group("polished_schist")
        .polished(POLISHED_SCHIST_BRICKS)
        .stairs(POLISHED_SCHIST_STAIRS)
        .slab(POLISHED_SCHIST_SLAB)
        .build();

    BlockFamily POLISHED_SCHIST_BRICK = register(POLISHED_SCHIST_BRICKS)
        .group("polished_schist_brick")
        .cracked(CRACKED_POLISHED_SCHIST_BRICKS)
        .wall(POLISHED_SCHIST_BRICK_WALL)
        .stairs(POLISHED_SCHIST_BRICK_STAIRS)
        .slab(POLISHED_SCHIST_BRICK_SLAB)
        .build();

    BlockFamily BLOODSTONE = register(TwigsBlocks.BLOODSTONE)
        .group("bloodstone")
        .polished(TwigsBlocks.POLISHED_BLOODSTONE)
        .wall(BLOODSTONE_WALL)
        .stairs(BLOODSTONE_STAIRS)
        .slab(BLOODSTONE_SLAB)
        .build();

    BlockFamily POLISHED_BLOODSTONE = register(TwigsBlocks.POLISHED_BLOODSTONE)
        .group("polished_bloodstone")
        .polished(POLISHED_BLOODSTONE_BRICKS)
        .stairs(POLISHED_BLOODSTONE_STAIRS)
        .slab(POLISHED_BLOODSTONE_SLAB)
        .build();

    BlockFamily POLISHED_BLOODSTONE_BRICK = register(POLISHED_BLOODSTONE_BRICKS)
        .group("polished_bloodstone_brick")
        .cracked(CRACKED_POLISHED_BLOODSTONE_BRICKS)
        .wall(POLISHED_BLOODSTONE_BRICK_WALL)
        .stairs(POLISHED_BLOODSTONE_BRICK_STAIRS)
        .slab(POLISHED_BLOODSTONE_BRICK_SLAB)
        .build();

    private static BlockFamily.Builder register(Block base) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base);
        BlockFamily family = FAMILIES.put(base, builder.build());
        if (family != null) throw new IllegalStateException("Duplicate family definition for " + Registry.BLOCK.getId(base));
        return builder;
    }
}
