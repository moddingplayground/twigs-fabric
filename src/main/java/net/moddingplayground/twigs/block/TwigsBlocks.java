package net.moddingplayground.twigs.block;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.vanilla.PublicStairsBlock;
import net.moddingplayground.twigs.block.wood.WoodBlock;
import net.moddingplayground.twigs.block.wood.WoodSet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public class TwigsBlocks {
    public static final Block TWIG = register("twig", new FloorLayerBlock(FabricBlockSettings.of(Material.WOOD).breakInstantly().sounds(BlockSoundGroup.WOOD).noCollision()));
    public static final Block ROCKY_DIRT = register("rocky_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT).strength(2.5F).sounds(BlockSoundGroup.TUFF).requiresTool()));
    public static final Block PEBBLE = register("pebble", new FloorLayerBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()));

    // paper lanterns

    public static final Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(
        Blocks.AIR,
        FabricBlockSettings.of(Material.WOOL)
                           .breakInstantly().sounds(BlockSoundGroup.BAMBOO)
                           .blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting)
                           .luminance(10).nonOpaque()
    ));
    public static final Block ALLIUM_PAPER_LANTERN = register("allium_paper_lantern", new PaperLanternBlock(Blocks.ALLIUM, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    public static final Block BLUE_ORCHID_PAPER_LANTERN = register("blue_orchid_paper_lantern", new PaperLanternBlock(Blocks.BLUE_ORCHID, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    public static final Block CRIMSON_ROOTS_PAPER_LANTERN = register("crimson_roots_paper_lantern", new PaperLanternBlock(Blocks.CRIMSON_ROOTS, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    public static final Block DANDELION_PAPER_LANTERN = register("dandelion_paper_lantern", new PaperLanternBlock(Blocks.DANDELION, FabricBlockSettings.copyOf(PAPER_LANTERN)));

    // azalea flowers

    public static final Block AZALEA_FLOWERS = register("azalea_flowers", new GlowLichenBlock(
        FabricBlockSettings.of(Material.PLANT)
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.AZALEA)
    ));

    public static final Block POTTED_AZALEA_FLOWERS = register("potted_azalea_flowers", new FlowerPotBlock(AZALEA_FLOWERS, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()), null);

    // stripped bamboo

    public static final WoodSet STRIPPED_BAMBOO_SET = new WoodSet(
        Twigs.MOD_ID, "stripped_bamboo", Twigs.ITEM_GROUP, WoodBlock::isArtificial,
        new WoodBlock.MaterialSet(Material.WOOD, Material.PLANT, Material.LEAVES, Material.DECORATION),
        new WoodBlock.ColorSet(MapColor.PALE_YELLOW, MapColor.OFF_WHITE),
        new WoodBlock.SoundSet(BlockSoundGroup.BAMBOO, BlockSoundGroup.BAMBOO_SAPLING, BlockSoundGroup.GRASS),
        null, PressurePlateBlock.ActivationRule.EVERYTHING, true
    ).register();

    public static final Block STRIPPED_BAMBOO = register("stripped_bamboo", new StrippedBambooBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO)));
    public static final Block STRIPPED_BUNDLED_BAMBOO = register("stripped_bundled_bamboo", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.SCAFFOLDING)));
    public static final Block BUNDLED_BAMBOO = register("bundled_bamboo", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.SCAFFOLDING)));
    public static final Block BAMBOO_THATCH = register("bamboo_thatch", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    public static final Block BAMBOO_THATCH_STAIRS = register("bamboo_thatch_stairs", new PublicStairsBlock(BAMBOO_THATCH.getDefaultState(), FabricBlockSettings.copyOf(BAMBOO_THATCH)));
    public static final Block BAMBOO_THATCH_SLAB = register("bamboo_thatch_slab", new SlabBlock(FabricBlockSettings.copyOf(BAMBOO_THATCH)));

    public static final Block BAMBOO_LEAVES = register("bamboo_leaves", new BambooLeavesBlock(
        FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES)
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.AZALEA_LEAVES)
    ));

    public static final Block POTTED_BAMBOO_LEAVES = register("potted_bamboo_leaves", new FlowerPotBlock(BAMBOO_LEAVES, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()), null);

    public static final Block STRIPPED_BAMBOO_MAT = register("stripped_bamboo_mat", new BambooMatBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET).sounds(BlockSoundGroup.SCAFFOLDING)));

    public static final Block OAK_TABLE = register("oak_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block SPRUCE_TABLE = register("spruce_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS).breakInstantly()));
    public static final Block BIRCH_TABLE = register("birch_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.BIRCH_PLANKS).breakInstantly()));
    public static final Block JUNGLE_TABLE = register("jungle_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).breakInstantly()));
    public static final Block ACACIA_TABLE = register("acacia_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_PLANKS).breakInstantly()));
    public static final Block DARK_OAK_TABLE = register("dark_oak_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_PLANKS).breakInstantly()));
    public static final Block CRIMSON_TABLE = register("crimson_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_PLANKS).breakInstantly()));
    public static final Block WARPED_TABLE = register("warped_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.WARPED_PLANKS).breakInstantly()));
    public static final Block STRIPPED_BAMBOO_TABLE = register("stripped_bamboo_table", new TableBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_SET.get(WoodBlock.PLANKS)).breakInstantly()));

    // bricks

    public static final Block CHISELED_BRICKS = register("chiseled_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block CRACKED_BRICKS = register("cracked_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block MOSSY_BRICKS = register("mossy_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", new PublicStairsBlock(MOSSY_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_BRICKS)));
    public static final Block MOSSY_BRICK_SLAB = register("mossy_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)));
    public static final Block MOSSY_BRICK_WALL = register("mossy_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)));

    // basalt

    public static final Block POLISHED_BASALT_BRICKS = register("polished_basalt_bricks", new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
    public static final Block SMOOTH_BASALT_BRICKS = register("smooth_basalt_bricks", new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static final Block SMOOTH_BASALT_BRICK_STAIRS = register("smooth_basalt_brick_stairs", new PublicStairsBlock(SMOOTH_BASALT_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));
    public static final Block SMOOTH_BASALT_BRICK_SLAB = register("smooth_basalt_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));
    public static final Block SMOOTH_BASALT_BRICK_WALL = register("smooth_basalt_brick_wall", new WallBlock(FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));

    // lamps

    public static final Block LAMP = register("lamp", new LampBlock(
        FabricBlockSettings.of(Material.METAL)
                           .requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)
                           .luminance(TwigsBlocks::litLevel)
    ));

    public static final Block SOUL_LAMP = register("soul_lamp", new LampBlock(FabricBlockSettings.copyOf(LAMP)));

    public static final Block CRIMSON_SHROOMLAMP = register("crimson_shroomlamp", new Block(
        FabricBlockSettings.of(Material.NETHER_WOOD)
                           .strength(3.5F).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque()
                           .luminance(TwigsBlocks::lightMax)
                           .blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting)
    ));

    public static final Block WARPED_SHROOMLAMP = register("warped_shroomlamp", new Block(
        FabricBlockSettings.of(Material.NETHER_WOOD)
                           .strength(3.5F).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque()
                           .luminance(TwigsBlocks::lightMax)
                           .blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting)
    ));

    // cobblestone bricks

    public static final Block COBBLESTONE_BRICKS = register("cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));

    // amethyst

    public static final Block POLISHED_AMETHYST = register("polished_amethyst", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block POLISHED_AMETHYST_STAIRS = register("polished_amethyst_stairs", new PublicStairsBlock(POLISHED_AMETHYST.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_AMETHYST)));
    public static final Block POLISHED_AMETHYST_SLAB = register("polished_amethyst_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST)));
    public static final Block CHISELED_POLISHED_AMETHYST = register("chiseled_polished_amethyst", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block POLISHED_AMETHYST_BRICKS = register("polished_amethyst_bricks", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block POLISHED_AMETHYST_BRICK_STAIRS = register("polished_amethyst_brick_stairs", new PublicStairsBlock(POLISHED_AMETHYST_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    public static final Block POLISHED_AMETHYST_BRICK_SLAB = register("polished_amethyst_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    public static final Block POLISHED_AMETHYST_BRICK_WALL = register("polished_amethyst_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    public static final Block CRACKED_POLISHED_AMETHYST_BRICKS = register("cracked_polished_amethyst_bricks", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));

    // twisting/weeping blackstone bricks

    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICKS = register("twisting_polished_blackstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS = register("twisting_polished_blackstone_brick_stairs", new PublicStairsBlock(TWISTING_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB = register("twisting_polished_blackstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICK_WALL = register("twisting_polished_blackstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICKS = register("weeping_polished_blackstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS = register("weeping_polished_blackstone_brick_stairs", new PublicStairsBlock(WEEPING_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB = register("weeping_polished_blackstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_WALL = register("weeping_polished_blackstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));

    // tuff

    public static final Block TUFF_STAIRS = register("tuff_stairs", new PublicStairsBlock(Blocks.TUFF.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BASALT)));
    public static final Block TUFF_SLAB = register("tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block TUFF_WALL = register("tuff_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block POLISHED_TUFF = register("polished_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block POLISHED_TUFF_STAIRS = register("polished_tuff_stairs", new PublicStairsBlock(POLISHED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_TUFF)));
    public static final Block POLISHED_TUFF_SLAB = register("polished_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_TUFF)));
    public static final Block POLISHED_TUFF_BRICKS = register("polished_tuff_bricks", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    public static final Block POLISHED_TUFF_BRICK_STAIRS = register("polished_tuff_brick_stairs", new PublicStairsBlock(POLISHED_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));
    public static final Block POLISHED_TUFF_BRICK_SLAB = register("polished_tuff_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));
    public static final Block POLISHED_TUFF_BRICK_WALL = register("polished_tuff_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));
    public static final Block CRACKED_POLISHED_TUFF_BRICKS = register("cracked_polished_tuff_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));

    // calcite

    public static final Block CALCITE_STAIRS = register("calcite_stairs", new PublicStairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block CALCITE_SLAB = register("calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block CALCITE_WALL = register("calcite_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block POLISHED_CALCITE = register("polished_calcite", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block POLISHED_CALCITE_STAIRS = register("polished_calcite_stairs", new PublicStairsBlock(POLISHED_CALCITE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_CALCITE)));
    public static final Block POLISHED_CALCITE_SLAB = register("polished_calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE)));
    public static final Block POLISHED_CALCITE_BRICKS = register("polished_calcite_bricks", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block POLISHED_CALCITE_BRICK_STAIRS = register("polished_calcite_brick_stairs", new PublicStairsBlock(POLISHED_CALCITE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));
    public static final Block POLISHED_CALCITE_BRICK_SLAB = register("polished_calcite_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));
    public static final Block POLISHED_CALCITE_BRICK_WALL = register("polished_calcite_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));
    public static final Block CRACKED_POLISHED_CALCITE_BRICKS = register("cracked_polished_calcite_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));

    // copper

    public static final Block COPPER_PILLAR = register("copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, FabricBlockSettings.copyOf(Blocks.CUT_COPPER)));
    public static final Block EXPOSED_COPPER_PILLAR = register("exposed_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, FabricBlockSettings.copyOf(Blocks.EXPOSED_CUT_COPPER)));
    public static final Block WEATHERED_COPPER_PILLAR = register("weathered_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, FabricBlockSettings.copyOf(Blocks.WEATHERED_CUT_COPPER)));
    public static final Block OXIDIZED_COPPER_PILLAR = register("oxidized_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, FabricBlockSettings.copyOf(Blocks.OXIDIZED_CUT_COPPER)));
    public static final Block WAXED_COPPER_PILLAR = register("waxed_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_CUT_COPPER)));
    public static final Block WAXED_EXPOSED_COPPER_PILLAR = register("waxed_exposed_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_EXPOSED_CUT_COPPER)));
    public static final Block WAXED_WEATHERED_COPPER_PILLAR = register("waxed_weathered_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_WEATHERED_CUT_COPPER)));
    public static final Block WAXED_OXIDIZED_COPPER_PILLAR = register("waxed_oxidized_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_OXIDIZED_CUT_COPPER)));

    // rhyolite

    public static final Block RHYOLITE = register("rhyolite", new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    public static final Block RHYOLITE_STAIRS = register("rhyolite_stairs", new PublicStairsBlock(RHYOLITE.getDefaultState(), FabricBlockSettings.copyOf(RHYOLITE)));
    public static final Block RHYOLITE_SLAB = register("rhyolite_slab", new SlabBlock(FabricBlockSettings.copyOf(RHYOLITE)));
    public static final Block RHYOLITE_WALL = register("rhyolite_wall", new WallBlock(FabricBlockSettings.copyOf(RHYOLITE)));
    public static final Block POLISHED_RHYOLITE = register("polished_rhyolite", new Block(FabricBlockSettings.copyOf(RHYOLITE)));
    public static final Block POLISHED_RHYOLITE_STAIRS = register("polished_rhyolite_stairs", new PublicStairsBlock(POLISHED_RHYOLITE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_RHYOLITE)));
    public static final Block POLISHED_RHYOLITE_SLAB = register("polished_rhyolite_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE)));
    public static final Block POLISHED_RHYOLITE_BRICKS = register("polished_rhyolite_bricks", new Block(FabricBlockSettings.copyOf(RHYOLITE)));
    public static final Block POLISHED_RHYOLITE_BRICK_STAIRS = register("polished_rhyolite_brick_stairs", new PublicStairsBlock(POLISHED_RHYOLITE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));
    public static final Block POLISHED_RHYOLITE_BRICK_SLAB = register("polished_rhyolite_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));
    public static final Block POLISHED_RHYOLITE_BRICK_WALL = register("polished_rhyolite_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));
    public static final Block CRACKED_POLISHED_RHYOLITE_BRICKS = register("cracked_polished_rhyolite_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));

    // schist

    public static final Block SCHIST = register("schist", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block SCHIST_STAIRS = register("schist_stairs", new PublicStairsBlock(SCHIST.getDefaultState(), FabricBlockSettings.copyOf(SCHIST)));
    public static final Block SCHIST_SLAB = register("schist_slab", new SlabBlock(FabricBlockSettings.copyOf(SCHIST)));
    public static final Block SCHIST_WALL = register("schist_wall", new WallBlock(FabricBlockSettings.copyOf(SCHIST)));
    public static final Block POLISHED_SCHIST = register("polished_schist", new Block(FabricBlockSettings.copyOf(SCHIST)));
    public static final Block POLISHED_SCHIST_STAIRS = register("polished_schist_stairs", new PublicStairsBlock(POLISHED_SCHIST.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_SCHIST)));
    public static final Block POLISHED_SCHIST_SLAB = register("polished_schist_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST)));
    public static final Block POLISHED_SCHIST_BRICKS = register("polished_schist_bricks", new Block(FabricBlockSettings.copyOf(SCHIST)));
    public static final Block POLISHED_SCHIST_BRICK_STAIRS = register("polished_schist_brick_stairs", new PublicStairsBlock(POLISHED_SCHIST_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));
    public static final Block POLISHED_SCHIST_BRICK_SLAB = register("polished_schist_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));
    public static final Block POLISHED_SCHIST_BRICK_WALL = register("polished_schist_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));
    public static final Block CRACKED_POLISHED_SCHIST_BRICKS = register("cracked_polished_schist_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));

    // bloodstone

    public static final Block BLOODSTONE = register("bloodstone", new Block(FabricBlockSettings.copyOf(Blocks.BASALT)));
    public static final Block BLOODSTONE_STAIRS = register("bloodstone_stairs", new PublicStairsBlock(BLOODSTONE.getDefaultState(), FabricBlockSettings.copyOf(BLOODSTONE)));
    public static final Block BLOODSTONE_SLAB = register("bloodstone_slab", new SlabBlock(FabricBlockSettings.copyOf(BLOODSTONE)));
    public static final Block BLOODSTONE_WALL = register("bloodstone_wall", new WallBlock(FabricBlockSettings.copyOf(BLOODSTONE)));
    public static final Block POLISHED_BLOODSTONE = register("polished_bloodstone", new Block(FabricBlockSettings.copyOf(BLOODSTONE)));
    public static final Block POLISHED_BLOODSTONE_STAIRS = register("polished_bloodstone_stairs", new PublicStairsBlock(POLISHED_BLOODSTONE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLOODSTONE)));
    public static final Block POLISHED_BLOODSTONE_SLAB = register("polished_bloodstone_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE)));
    public static final Block POLISHED_BLOODSTONE_BRICKS = register("polished_bloodstone_bricks", new Block(FabricBlockSettings.copyOf(BLOODSTONE)));
    public static final Block POLISHED_BLOODSTONE_BRICK_STAIRS = register("polished_bloodstone_brick_stairs", new PublicStairsBlock(POLISHED_BLOODSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));
    public static final Block POLISHED_BLOODSTONE_BRICK_SLAB = register("polished_bloodstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));
    public static final Block POLISHED_BLOODSTONE_BRICK_WALL = register("polished_bloodstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));
    public static final Block CRACKED_POLISHED_BLOODSTONE_BRICKS = register("cracked_polished_bloodstone_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));

    static {
        UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
            ItemStack stack = player.getStackInHand(hand);
            BlockPos pos = hit.getBlockPos();
            BlockState state = world.getBlockState(pos);

            Optional<BlockState> nu = Optional.empty();

            if (state.isOf(Blocks.FLOWERING_AZALEA) && stack.isIn(FabricToolTags.SHEARS)) {
                world.playSound(player, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0f, 1.0f);
                Block.dropStack(world, pos.up(), new ItemStack(AZALEA_FLOWERS, world.random.nextInt(2) + 1));
                nu = Optional.of(Blocks.AZALEA.getDefaultState());
            } if (state.isOf(Blocks.BAMBOO) && stack.isIn(FabricToolTags.AXES)) {
                if (!world.getBlockState(pos.up()).isOf(Blocks.BAMBOO)) {
                    world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    nu = Optional.of(STRIPPED_BAMBOO.getDefaultState().with(StrippedBambooBlock.FROM_BAMBOO, true));
                }
            }

            if (nu.isPresent()) {
                if (player instanceof ServerPlayerEntity serverPlayer) Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
                world.setBlockState(pos, nu.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                return ActionResult.success(world.isClient);
            }

            return ActionResult.PASS;
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            lootAddition(Blocks.BAMBOO, id, supplier, manager);
        });

        FlammableBlockRegistry flamReg = FlammableBlockRegistry.getDefaultInstance();
        flamReg.add(AZALEA_FLOWERS,30, 60);
        flamReg.add(TWIG,30, 60);
        flamReg.add(BAMBOO_LEAVES,30, 60);
        flamReg.add(BAMBOO_THATCH,30, 60);
        flamReg.add(BAMBOO_THATCH_SLAB, 30, 60);
        flamReg.add(BAMBOO_THATCH_STAIRS, 30, 60);
        flamReg.add(OAK_TABLE, 5, 20);
        flamReg.add(SPRUCE_TABLE, 5, 20);
        flamReg.add(BIRCH_TABLE, 5, 20);
        flamReg.add(ACACIA_TABLE, 5, 20);
        flamReg.add(JUNGLE_TABLE, 5, 20);
        flamReg.add(DARK_OAK_TABLE, 5, 20);
        flamReg.add(STRIPPED_BAMBOO_TABLE, 5, 20);
        flamReg.add(STRIPPED_BAMBOO, 5, 20);

        FuelRegistry fuelReg = FuelRegistry.INSTANCE;
        fuelReg.add(STRIPPED_BAMBOO, 50);
        fuelReg.add(BUNDLED_BAMBOO, 450);
        fuelReg.add(STRIPPED_BUNDLED_BAMBOO, 450);

        CompostingChanceRegistry compReg = CompostingChanceRegistry.INSTANCE;
        compReg.add(BAMBOO_LEAVES, 0.5F);

        TillableBlockRegistry.register(ROCKY_DIRT, ctx -> true, Blocks.COARSE_DIRT.getDefaultState(), PEBBLE);
        StrippableBlockRegistry.register(BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO);

        LinkedHashMap<Block, Block> copperPillars = Maps.newLinkedHashMap();
        copperPillars.put(COPPER_PILLAR, WAXED_COPPER_PILLAR);
        copperPillars.put(EXPOSED_COPPER_PILLAR, WAXED_EXPOSED_COPPER_PILLAR);
        copperPillars.put(WEATHERED_COPPER_PILLAR, WAXED_WEATHERED_COPPER_PILLAR);
        copperPillars.put(OXIDIZED_COPPER_PILLAR, WAXED_OXIDIZED_COPPER_PILLAR);

        copperPillars.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);

        List<Block> unwaxedCopperPillars = List.copyOf(copperPillars.keySet());
        for (int i = 0, l = copperPillars.size() - 1; i < l; i++) OxidizableBlocksRegistry.registerOxidizableBlockPair(unwaxedCopperPillars.get(i), unwaxedCopperPillars.get(i + 1));
    }

    private static void lootAddition(Block block, Identifier id, FabricLootSupplierBuilder supplier, LootManager manager) {
        if (id.equals(block.getLootTableId())) supplier.copyFrom(manager.getTable(getAdditiveLootTable(block)));
    }

    public static Identifier getAdditiveLootTable(Block block) {
        Identifier id = block.getLootTableId();
        return new Identifier(Twigs.MOD_ID, "additions/%s/%s".formatted(id.getNamespace(), id.getPath()));
    }

    private static ToIntFunction<BlockState> litLevel(int lit) {
        return (state) -> state.get(Properties.LIT) ? lit : 0;
    }

    private static int litLevel(BlockState state) {
        return litLevel(15).applyAsInt(state);
    }

    private static int lightMax(BlockState state) {
        return 15;
    }

    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    public static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static Block register(String id, Block block, Function<Block, Item> item) {
        Identifier identifier = new Identifier(Twigs.MOD_ID, id);
        if (item != null) Registry.register(Registry.ITEM, identifier, item.apply(block));
        return Registry.register(Registry.BLOCK, identifier, block);
    }

    private static Block register(String id, Block block) {
        return register(id, block, b -> new BlockItem(b, new FabricItemSettings().group(Twigs.ITEM_GROUP)));
    }
}
