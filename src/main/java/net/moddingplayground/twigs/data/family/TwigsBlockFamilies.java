package net.moddingplayground.twigs.data.family;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.block.TwigsBlocks;

import java.util.HashMap;
import java.util.stream.Stream;

import static net.minecraft.block.Blocks.*;
import static net.moddingplayground.twigs.block.TwigsBlocks.*;

public class TwigsBlockFamilies {
    private static final HashMap<Block, BlockFamily> FAMILIES = Maps.newHashMap();

    public static final BlockFamily BAMBOO_THATCH = register(TwigsBlocks.BAMBOO_THATCH)
        .group("bamboo_thatch")
        .stairs(BAMBOO_THATCH_STAIRS)
        .slab(BAMBOO_THATCH_SLAB)
        .build();

    public static final BlockFamily BRICK = register(BRICKS)
        .group("brick")
        .chiseled(CHISELED_BRICKS)
        .cracked(CRACKED_BRICKS)
        .slab(BRICK_SLAB)
        .build();

    public static final BlockFamily MOSSY_BRICK = register(MOSSY_BRICKS)
        .group("mossy_brick")
        .wall(MOSSY_BRICK_WALL)
        .stairs(MOSSY_BRICK_STAIRS)
        .slab(MOSSY_BRICK_SLAB)
        .build();

    public static final BlockFamily SMOOTH_BASALT_BRICK = register(SMOOTH_BASALT_BRICKS)
        .group("smooth_basalt_brick")
        .wall(SMOOTH_BASALT_BRICK_WALL)
        .stairs(SMOOTH_BASALT_BRICK_STAIRS)
        .slab(SMOOTH_BASALT_BRICK_SLAB)
        .build();

    public static final BlockFamily COBBLESTONE = register(Blocks.COBBLESTONE)
        .group("cobblestone")
        .polished(COBBLESTONE_BRICKS)
        .build();

    public static final BlockFamily COBBLESTONE_BRICK = register(COBBLESTONE_BRICKS)
        .group("cobblestone_brick")
        .cracked(CRACKED_COBBLESTONE_BRICKS)
        .wall(COBBLESTONE_BRICK_WALL)
        .stairs(COBBLESTONE_BRICK_STAIRS)
        .slab(COBBLESTONE_BRICK_SLAB)
        .build();

    public static final BlockFamily MOSSY_COBBLESTONE_BRICK = register(MOSSY_COBBLESTONE_BRICKS)
        .group("mossy_cobblestone_brick")
        .wall(MOSSY_COBBLESTONE_BRICK_WALL)
        .stairs(MOSSY_COBBLESTONE_BRICK_STAIRS)
        .slab(MOSSY_COBBLESTONE_BRICK_SLAB)
        .build();

    public static final BlockFamily AMETHYST = register(AMETHYST_BLOCK)
        .group("amethyst")
        .polished(TwigsBlocks.POLISHED_AMETHYST)
        .build();

    public static final BlockFamily POLISHED_AMETHYST = register(TwigsBlocks.POLISHED_AMETHYST)
        .group("polished_amethyst")
        .chiseled(CHISELED_POLISHED_AMETHYST)
        .stairs(POLISHED_AMETHYST_STAIRS)
        .slab(POLISHED_AMETHYST_SLAB)
        .build();

    public static final BlockFamily POLISHED_AMETHYST_BRICK = register(POLISHED_AMETHYST_BRICKS)
        .group("polished_amethyst_brick")
        .cracked(CRACKED_POLISHED_AMETHYST_BRICKS)
        .stairs(POLISHED_AMETHYST_BRICK_STAIRS)
        .slab(POLISHED_AMETHYST_BRICK_SLAB)
        .build();

    public static final BlockFamily TWISTING_POLISHED_BLACKSTONE_BRICK = register(TWISTING_POLISHED_BLACKSTONE_BRICKS)
        .group("twisting_polished_blackstone_brick")
        .wall(TWISTING_POLISHED_BLACKSTONE_BRICK_WALL)
        .stairs(TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS)
        .slab(TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB)
        .build();

    public static final BlockFamily WEEPING_POLISHED_BLACKSTONE_BRICK = register(WEEPING_POLISHED_BLACKSTONE_BRICKS)
        .group("weeping_polished_blackstone_brick")
        .wall(WEEPING_POLISHED_BLACKSTONE_BRICK_WALL)
        .stairs(WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS)
        .slab(WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB)
        .build();

    public static final BlockFamily TUFF = register(Blocks.TUFF)
        .group("tuff")
        .polished(TwigsBlocks.POLISHED_TUFF)
        .wall(TUFF_WALL)
        .stairs(TUFF_STAIRS)
        .slab(TUFF_SLAB)
        .build();

    public static final BlockFamily POLISHED_TUFF = register(TwigsBlocks.POLISHED_TUFF)
        .group("polished_tuff")
        .stairs(POLISHED_TUFF_STAIRS)
        .slab(POLISHED_TUFF_SLAB)
        .build();

    public static final BlockFamily POLISHED_TUFF_BRICK = register(POLISHED_TUFF_BRICKS)
        .group("polished_tuff_brick")
        .cracked(CRACKED_POLISHED_TUFF_BRICKS)
        .wall(POLISHED_TUFF_BRICK_WALL)
        .stairs(POLISHED_TUFF_BRICK_STAIRS)
        .slab(POLISHED_TUFF_BRICK_SLAB)
        .build();

    public static final BlockFamily CALCITE = register(Blocks.CALCITE)
        .group("calcite")
        .polished(TwigsBlocks.POLISHED_CALCITE)
        .wall(CALCITE_WALL)
        .stairs(CALCITE_STAIRS)
        .slab(CALCITE_SLAB)
        .build();

    public static final BlockFamily POLISHED_CALCITE = register(TwigsBlocks.POLISHED_CALCITE)
        .group("polished_calcite")
        .stairs(POLISHED_CALCITE_STAIRS)
        .slab(POLISHED_CALCITE_SLAB)
        .build();

    public static final BlockFamily POLISHED_CALCITE_BRICK = register(POLISHED_CALCITE_BRICKS)
        .group("polished_calcite_brick")
        .cracked(CRACKED_POLISHED_CALCITE_BRICKS)
        .wall(POLISHED_CALCITE_BRICK_WALL)
        .stairs(POLISHED_CALCITE_BRICK_STAIRS)
        .slab(POLISHED_CALCITE_BRICK_SLAB)
        .build();

    public static final BlockFamily COPPER = register(COPPER_BLOCK)
        .group("copper")
        .chiseled(COPPER_PILLAR)
        .slab(CUT_COPPER_SLAB)
        .build();
    public static final BlockFamily WAXED_COPPER = register(WAXED_COPPER_BLOCK)
        .group("copper")
        .chiseled(WAXED_COPPER_PILLAR)
        .slab(WAXED_CUT_COPPER_SLAB)
        .build();

    public static final BlockFamily EXPOSED_COPPER = register(Blocks.EXPOSED_COPPER)
        .group("copper")
        .chiseled(EXPOSED_COPPER_PILLAR)
        .slab(EXPOSED_CUT_COPPER_SLAB)
        .build();
    public static final BlockFamily WAXED_EXPOSED_COPPER = register(Blocks.WAXED_EXPOSED_COPPER)
        .group("copper")
        .chiseled(WAXED_EXPOSED_COPPER_PILLAR)
        .slab(WAXED_EXPOSED_CUT_COPPER_SLAB)
        .build();

    public static final BlockFamily WEATHERED_COPPER = register(Blocks.WEATHERED_COPPER)
        .group("copper")
        .chiseled(WEATHERED_COPPER_PILLAR)
        .slab(WEATHERED_CUT_COPPER_SLAB)
        .build();
    public static final BlockFamily WAXED_WEATHERED_COPPER = register(Blocks.WAXED_WEATHERED_COPPER)
        .group("copper")
        .chiseled(WAXED_WEATHERED_COPPER_PILLAR)
        .slab(WAXED_WEATHERED_CUT_COPPER_SLAB)
        .build();

    public static final BlockFamily OXIDIZED_COPPER = register(Blocks.OXIDIZED_COPPER)
        .group("copper")
        .chiseled(OXIDIZED_COPPER_PILLAR)
        .slab(OXIDIZED_CUT_COPPER_SLAB)
        .build();
    public static final BlockFamily WAXED_OXIDIZED_COPPER = register(Blocks.WAXED_OXIDIZED_COPPER)
        .group("copper")
        .chiseled(WAXED_OXIDIZED_COPPER_PILLAR)
        .slab(WAXED_OXIDIZED_CUT_COPPER_SLAB)
        .build();

    public static final BlockFamily RHYOLITE = register(TwigsBlocks.RHYOLITE)
        .group("rhyolite")
        .polished(TwigsBlocks.POLISHED_RHYOLITE)
        .wall(RHYOLITE_WALL)
        .stairs(RHYOLITE_STAIRS)
        .slab(RHYOLITE_SLAB)
        .build();

    public static final BlockFamily POLISHED_RHYOLITE = register(TwigsBlocks.POLISHED_RHYOLITE)
        .group("polished_rhyolite")
        .stairs(POLISHED_RHYOLITE_STAIRS)
        .slab(POLISHED_RHYOLITE_SLAB)
        .build();

    public static final BlockFamily POLISHED_RHYOLITE_BRICK = register(POLISHED_RHYOLITE_BRICKS)
        .group("polished_rhyolite_brick")
        .cracked(CRACKED_POLISHED_RHYOLITE_BRICKS)
        .wall(POLISHED_RHYOLITE_BRICK_WALL)
        .stairs(POLISHED_RHYOLITE_BRICK_STAIRS)
        .slab(POLISHED_RHYOLITE_BRICK_SLAB)
        .build();

    public static final BlockFamily SCHIST = register(TwigsBlocks.SCHIST)
        .group("schist")
        .polished(TwigsBlocks.POLISHED_SCHIST)
        .wall(SCHIST_WALL)
        .stairs(SCHIST_STAIRS)
        .slab(SCHIST_SLAB)
        .build();

    public static final BlockFamily POLISHED_SCHIST = register(TwigsBlocks.POLISHED_SCHIST)
        .group("polished_schist")
        .stairs(POLISHED_SCHIST_STAIRS)
        .slab(POLISHED_SCHIST_SLAB)
        .build();

    public static final BlockFamily POLISHED_SCHIST_BRICK = register(POLISHED_SCHIST_BRICKS)
        .group("polished_schist_brick")
        .cracked(CRACKED_POLISHED_SCHIST_BRICKS)
        .wall(POLISHED_SCHIST_BRICK_WALL)
        .stairs(POLISHED_SCHIST_BRICK_STAIRS)
        .slab(POLISHED_SCHIST_BRICK_SLAB)
        .build();

    public static final BlockFamily BLOODSTONE = register(TwigsBlocks.BLOODSTONE)
        .group("bloodstone")
        .polished(TwigsBlocks.POLISHED_BLOODSTONE)
        .wall(BLOODSTONE_WALL)
        .stairs(BLOODSTONE_STAIRS)
        .slab(BLOODSTONE_SLAB)
        .build();

    public static final BlockFamily POLISHED_BLOODSTONE = register(TwigsBlocks.POLISHED_BLOODSTONE)
        .group("polished_bloodstone")
        .stairs(POLISHED_BLOODSTONE_STAIRS)
        .slab(POLISHED_BLOODSTONE_SLAB)
        .build();

    public static final BlockFamily POLISHED_BLOODSTONE_BRICK = register(POLISHED_BLOODSTONE_BRICKS)
        .group("polished_bloodstone_brick")
        .cracked(CRACKED_POLISHED_BLOODSTONE_BRICKS)
        .wall(POLISHED_BLOODSTONE_BRICK_WALL)
        .stairs(POLISHED_BLOODSTONE_BRICK_STAIRS)
        .slab(POLISHED_BLOODSTONE_BRICK_SLAB)
        .build();

    public static Stream<BlockFamily> getFamilies() {
        return FAMILIES.values().stream();
    }

    public static BlockFamily.Builder register(Block base) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base);
        BlockFamily family = FAMILIES.put(base, builder.build());
        if (family != null) throw new IllegalStateException("Duplicate family definition for " + Registry.BLOCK.getId(base));
        return builder;
    }
}
