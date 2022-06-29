package net.moddingplayground.twigs.api.data;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

import java.util.HashMap;


public interface TwigsBlockFamilies {
    HashMap<Block, BlockFamily> FAMILIES = Maps.newHashMap();

    BlockFamily STRIPPED_BAMBOO = register(TwigsBlocks.STRIPPED_BAMBOO_PLANKS)
        .group("wooden")
        .unlockCriterionName("has_planks")
        .button(TwigsBlocks.STRIPPED_BAMBOO_BUTTON)
        .fence(TwigsBlocks.STRIPPED_BAMBOO_FENCE)
        .fenceGate(TwigsBlocks.STRIPPED_BAMBOO_FENCE_GATE)
        .pressurePlate(TwigsBlocks.STRIPPED_BAMBOO_PRESSURE_PLATE)
        .sign(TwigsBlocks.STRIPPED_BAMBOO_SIGN, TwigsBlocks.STRIPPED_BAMBOO_WALL_SIGN)
        .slab(TwigsBlocks.STRIPPED_BAMBOO_SLAB)
        .stairs(TwigsBlocks.STRIPPED_BAMBOO_STAIRS)
        .door(TwigsBlocks.STRIPPED_BAMBOO_DOOR)
        .trapdoor(TwigsBlocks.STRIPPED_BAMBOO_TRAPDOOR)
        .build();

    BlockFamily BAMBOO_THATCH = register(TwigsBlocks.BAMBOO_THATCH)
        .group("bamboo_thatch")
        .stairs(TwigsBlocks.BAMBOO_THATCH_STAIRS)
        .slab(TwigsBlocks.BAMBOO_THATCH_SLAB)
        .build();

    BlockFamily BRICKS = register(Blocks.BRICKS)
        .group("brick")
        .chiseled(TwigsBlocks.CHISELED_BRICKS)
        .cracked(TwigsBlocks.CRACKED_BRICKS)
        .slab(Blocks.BRICK_SLAB)
        .build();

    BlockFamily MOSSY_BRICKS = register(TwigsBlocks.MOSSY_BRICKS)
        .group("mossy_brick")
        .wall(TwigsBlocks.MOSSY_BRICK_WALL)
        .stairs(TwigsBlocks.MOSSY_BRICK_STAIRS)
        .slab(TwigsBlocks.MOSSY_BRICK_SLAB)
        .build();

    BlockFamily SMOOTH_BASALT = register(Blocks.SMOOTH_BASALT)
        .group("smooth_basalt")
        .polished(TwigsBlocks.SMOOTH_BASALT_BRICKS)
        .build();

    BlockFamily POLISHED_BASALT = register(Blocks.POLISHED_BASALT)
        .group("polished_basalt")
        .polished(TwigsBlocks.POLISHED_BASALT_BRICKS)
        .build();

    BlockFamily POLISHED_BASALT_BRICKS = register(TwigsBlocks.POLISHED_BASALT_BRICKS)
        .group("polished_basalt_brick")
        .build();

    BlockFamily SMOOTH_BASALT_BRICKS = register(TwigsBlocks.SMOOTH_BASALT_BRICKS)
        .group("smooth_basalt_brick")
        .wall(TwigsBlocks.SMOOTH_BASALT_BRICK_WALL)
        .stairs(TwigsBlocks.SMOOTH_BASALT_BRICK_STAIRS)
        .slab(TwigsBlocks.SMOOTH_BASALT_BRICK_SLAB)
        .build();

    BlockFamily COBBLESTONE = register(Blocks.COBBLESTONE)
        .group("cobblestone")
        .polished(TwigsBlocks.COBBLESTONE_BRICKS)
        .build();

    BlockFamily COBBLESTONE_BRICKS = register(TwigsBlocks.COBBLESTONE_BRICKS)
        .group("cobblestone_brick")
        .cracked(TwigsBlocks.CRACKED_COBBLESTONE_BRICKS)
        .wall(TwigsBlocks.COBBLESTONE_BRICK_WALL)
        .stairs(TwigsBlocks.COBBLESTONE_BRICK_STAIRS)
        .slab(TwigsBlocks.COBBLESTONE_BRICK_SLAB)
        .build();

    BlockFamily MOSSY_COBBLESTONE_BRICKS = register(TwigsBlocks.MOSSY_COBBLESTONE_BRICKS)
        .group("mossy_cobblestone_brick")
        .wall(TwigsBlocks.MOSSY_COBBLESTONE_BRICK_WALL)
        .stairs(TwigsBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS)
        .slab(TwigsBlocks.MOSSY_COBBLESTONE_BRICK_SLAB)
        .build();

    BlockFamily AMETHYST = register(Blocks.AMETHYST_BLOCK)
        .group("amethyst")
        .polished(TwigsBlocks.POLISHED_AMETHYST)
        .build();

    BlockFamily POLISHED_AMETHYST = register(TwigsBlocks.POLISHED_AMETHYST)
        .group("polished_amethyst")
        .polished(TwigsBlocks.POLISHED_AMETHYST_BRICKS)
        .chiseled(TwigsBlocks.CHISELED_POLISHED_AMETHYST)
        .stairs(TwigsBlocks.POLISHED_AMETHYST_STAIRS)
        .slab(TwigsBlocks.POLISHED_AMETHYST_SLAB)
        .build();

    BlockFamily POLISHED_AMETHYST_BRICKS = register(TwigsBlocks.POLISHED_AMETHYST_BRICKS)
        .group("polished_amethyst_brick")
        .cracked(TwigsBlocks.CRACKED_POLISHED_AMETHYST_BRICKS)
        .stairs(TwigsBlocks.POLISHED_AMETHYST_BRICK_STAIRS)
        .slab(TwigsBlocks.POLISHED_AMETHYST_BRICK_SLAB)
        .wall(TwigsBlocks.POLISHED_AMETHYST_BRICK_WALL)
        .build();

    BlockFamily TWISTING_POLISHED_BLACKSTONE_BRICKS = register(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICKS)
        .group("twisting_polished_blackstone_brick")
        .wall(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICK_WALL)
        .stairs(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS)
        .slab(TwigsBlocks.TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB)
        .build();

    BlockFamily WEEPING_POLISHED_BLACKSTONE_BRICKS = register(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICKS)
        .group("weeping_polished_blackstone_brick")
        .wall(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_WALL)
        .stairs(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS)
        .slab(TwigsBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB)
        .build();

    BlockFamily TUFF = register(Blocks.TUFF)
        .group("tuff")
        .polished(TwigsBlocks.POLISHED_TUFF)
        .wall(TwigsBlocks.TUFF_WALL)
        .stairs(TwigsBlocks.TUFF_STAIRS)
        .slab(TwigsBlocks.TUFF_SLAB)
        .build();

    BlockFamily POLISHED_TUFF = register(TwigsBlocks.POLISHED_TUFF)
        .group("polished_tuff")
        .polished(TwigsBlocks.POLISHED_TUFF_BRICKS)
        .stairs(TwigsBlocks.POLISHED_TUFF_STAIRS)
        .slab(TwigsBlocks.POLISHED_TUFF_SLAB)
        .build();

    BlockFamily POLISHED_TUFF_BRICKS = register(TwigsBlocks.POLISHED_TUFF_BRICKS)
        .group("polished_tuff_brick")
        .cracked(TwigsBlocks.CRACKED_POLISHED_TUFF_BRICKS)
        .wall(TwigsBlocks.POLISHED_TUFF_BRICK_WALL)
        .stairs(TwigsBlocks.POLISHED_TUFF_BRICK_STAIRS)
        .slab(TwigsBlocks.POLISHED_TUFF_BRICK_SLAB)
        .build();

    BlockFamily CALCITE = register(Blocks.CALCITE)
        .group("calcite")
        .polished(TwigsBlocks.POLISHED_CALCITE)
        .wall(TwigsBlocks.CALCITE_WALL)
        .stairs(TwigsBlocks.CALCITE_STAIRS)
        .slab(TwigsBlocks.CALCITE_SLAB)
        .build();

    BlockFamily POLISHED_CALCITE = register(TwigsBlocks.POLISHED_CALCITE)
        .group("polished_calcite")
        .polished(TwigsBlocks.POLISHED_CALCITE_BRICKS)
        .stairs(TwigsBlocks.POLISHED_CALCITE_STAIRS)
        .slab(TwigsBlocks.POLISHED_CALCITE_SLAB)
        .build();

    BlockFamily POLISHED_CALCITE_BRICKS = register(TwigsBlocks.POLISHED_CALCITE_BRICKS)
        .group("polished_calcite_brick")
        .cracked(TwigsBlocks.CRACKED_POLISHED_CALCITE_BRICKS)
        .wall(TwigsBlocks.POLISHED_CALCITE_BRICK_WALL)
        .stairs(TwigsBlocks.POLISHED_CALCITE_BRICK_STAIRS)
        .slab(TwigsBlocks.POLISHED_CALCITE_BRICK_SLAB)
        .build();

    BlockFamily COPPER = register(Blocks.COPPER_BLOCK)
        .group("copper")
        .chiseled(TwigsBlocks.COPPER_PILLAR)
        .slab(Blocks.CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_COPPER = register(Blocks.WAXED_COPPER_BLOCK)
        .group("copper")
        .chiseled(TwigsBlocks.WAXED_COPPER_PILLAR)
        .slab(Blocks.WAXED_CUT_COPPER_SLAB)
        .build();

    BlockFamily EXPOSED_COPPER = register(Blocks.EXPOSED_COPPER)
        .group("copper")
        .chiseled(TwigsBlocks.EXPOSED_COPPER_PILLAR)
        .slab(Blocks.EXPOSED_CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_EXPOSED_COPPER = register(Blocks.WAXED_EXPOSED_COPPER)
        .group("copper")
        .chiseled(TwigsBlocks.WAXED_EXPOSED_COPPER_PILLAR)
        .slab(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)
        .build();

    BlockFamily WEATHERED_COPPER = register(Blocks.WEATHERED_COPPER)
        .group("copper")
        .chiseled(TwigsBlocks.WEATHERED_COPPER_PILLAR)
        .slab(Blocks.WEATHERED_CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_WEATHERED_COPPER = register(Blocks.WAXED_WEATHERED_COPPER)
        .group("copper")
        .chiseled(TwigsBlocks.WAXED_WEATHERED_COPPER_PILLAR)
        .slab(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)
        .build();

    BlockFamily OXIDIZED_COPPER = register(Blocks.OXIDIZED_COPPER)
        .group("copper")
        .chiseled(TwigsBlocks.OXIDIZED_COPPER_PILLAR)
        .slab(Blocks.OXIDIZED_CUT_COPPER_SLAB)
        .build();
    BlockFamily WAXED_OXIDIZED_COPPER = register(Blocks.WAXED_OXIDIZED_COPPER)
        .group("copper")
        .chiseled(TwigsBlocks.WAXED_OXIDIZED_COPPER_PILLAR)
        .slab(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)
        .build();

    BlockFamily RHYOLITE = register(TwigsBlocks.RHYOLITE)
        .group("rhyolite")
        .polished(TwigsBlocks.POLISHED_RHYOLITE)
        .wall(TwigsBlocks.RHYOLITE_WALL)
        .stairs(TwigsBlocks.RHYOLITE_STAIRS)
        .slab(TwigsBlocks.RHYOLITE_SLAB)
        .noGenerateModels()
        .build();

    BlockFamily POLISHED_RHYOLITE = register(TwigsBlocks.POLISHED_RHYOLITE)
        .group("polished_rhyolite")
        .polished(TwigsBlocks.POLISHED_RHYOLITE_BRICKS)
        .stairs(TwigsBlocks.POLISHED_RHYOLITE_STAIRS)
        .slab(TwigsBlocks.POLISHED_RHYOLITE_SLAB)
        .build();

    BlockFamily POLISHED_RHYOLITE_BRICKS = register(TwigsBlocks.POLISHED_RHYOLITE_BRICKS)
        .group("polished_rhyolite_brick")
        .cracked(TwigsBlocks.CRACKED_POLISHED_RHYOLITE_BRICKS)
        .wall(TwigsBlocks.POLISHED_RHYOLITE_BRICK_WALL)
        .stairs(TwigsBlocks.POLISHED_RHYOLITE_BRICK_STAIRS)
        .slab(TwigsBlocks.POLISHED_RHYOLITE_BRICK_SLAB)
        .build();

    BlockFamily SCHIST = register(TwigsBlocks.SCHIST)
        .group("schist")
        .polished(TwigsBlocks.POLISHED_SCHIST)
        .wall(TwigsBlocks.SCHIST_WALL)
        .stairs(TwigsBlocks.SCHIST_STAIRS)
        .slab(TwigsBlocks.SCHIST_SLAB)
        .build();

    BlockFamily POLISHED_SCHIST = register(TwigsBlocks.POLISHED_SCHIST)
        .group("polished_schist")
        .polished(TwigsBlocks.POLISHED_SCHIST_BRICKS)
        .stairs(TwigsBlocks.POLISHED_SCHIST_STAIRS)
        .slab(TwigsBlocks.POLISHED_SCHIST_SLAB)
        .build();

    BlockFamily POLISHED_SCHIST_BRICKS = register(TwigsBlocks.POLISHED_SCHIST_BRICKS)
        .group("polished_schist_brick")
        .cracked(TwigsBlocks.CRACKED_POLISHED_SCHIST_BRICKS)
        .wall(TwigsBlocks.POLISHED_SCHIST_BRICK_WALL)
        .stairs(TwigsBlocks.POLISHED_SCHIST_BRICK_STAIRS)
        .slab(TwigsBlocks.POLISHED_SCHIST_BRICK_SLAB)
        .build();

    BlockFamily BLOODSTONE = register(TwigsBlocks.BLOODSTONE)
        .group("bloodstone")
        .polished(TwigsBlocks.POLISHED_BLOODSTONE)
        .wall(TwigsBlocks.BLOODSTONE_WALL)
        .stairs(TwigsBlocks.BLOODSTONE_STAIRS)
        .slab(TwigsBlocks.BLOODSTONE_SLAB)
        .build();

    BlockFamily POLISHED_BLOODSTONE = register(TwigsBlocks.POLISHED_BLOODSTONE)
        .group("polished_bloodstone")
        .polished(TwigsBlocks.POLISHED_BLOODSTONE_BRICKS)
        .stairs(TwigsBlocks.POLISHED_BLOODSTONE_STAIRS)
        .slab(TwigsBlocks.POLISHED_BLOODSTONE_SLAB)
        .build();

    BlockFamily POLISHED_BLOODSTONE_BRICKS = register(TwigsBlocks.POLISHED_BLOODSTONE_BRICKS)
        .group("polished_bloodstone_brick")
        .cracked(TwigsBlocks.CRACKED_POLISHED_BLOODSTONE_BRICKS)
        .wall(TwigsBlocks.POLISHED_BLOODSTONE_BRICK_WALL)
        .stairs(TwigsBlocks.POLISHED_BLOODSTONE_BRICK_STAIRS)
        .slab(TwigsBlocks.POLISHED_BLOODSTONE_BRICK_SLAB)
        .build();

    private static BlockFamily.Builder register(Block base) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base);
        BlockFamily family = FAMILIES.put(base, builder.build());
        if (family != null) throw new IllegalStateException("Duplicate family definition for " + Registry.BLOCK.getId(base));
        return builder;
    }
}
