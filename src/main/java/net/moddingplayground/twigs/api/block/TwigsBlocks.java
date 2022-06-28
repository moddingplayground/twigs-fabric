package net.moddingplayground.twigs.api.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.moddingplayground.twigs.api.Twigs;

import java.util.function.ToIntFunction;

public interface TwigsBlocks {
    Block TWIG = register("twig", new FloorLayerBlock(
        FabricBlockSettings.of(TwigsMaterial.FLOOR_LAYER.apply(MapColor.OAK_TAN))
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.WOOD)
    ));

    Block PEBBLE = register("pebble", new FloorLayerBlock(
        FabricBlockSettings.of(TwigsMaterial.FLOOR_LAYER.apply(MapColor.STONE_GRAY))
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.STONE)
    ));

    Block SEA_SHELL = register("sea_shell", new FloorLayerBlock(
        FabricBlockSettings.of(TwigsMaterial.FLOOR_LAYER.apply(MapColor.ORANGE))
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.BONE)
    ));

    Block AZALEA_FLOWERS = register("azalea_flowers", new GlowLichenBlock(
        FabricBlockSettings.of(Material.PLANT)
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.AZALEA)
    ));

    Block POTTED_AZALEA_FLOWERS = register("potted_azalea_flowers", new FlowerPotBlock(AZALEA_FLOWERS, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));

    Block BAMBOO_LEAVES = register("bamboo_leaves", new BambooLeavesBlock(
        FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES)
                           .breakInstantly().noCollision()
                           .sounds(BlockSoundGroup.AZALEA_LEAVES)
    ));

    /* Paper Lanterns */

    Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(
        Blocks.AIR,
        FabricBlockSettings.of(Material.WOOL)
                           .breakInstantly().sounds(BlockSoundGroup.BAMBOO)
                           .blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting)
                           .luminance(10).nonOpaque()
    ));
    Block ALLIUM_PAPER_LANTERN = register("allium_paper_lantern", new PaperLanternBlock(Blocks.ALLIUM, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    Block BLUE_ORCHID_PAPER_LANTERN = register("blue_orchid_paper_lantern", new PaperLanternBlock(Blocks.BLUE_ORCHID, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    Block CRIMSON_ROOTS_PAPER_LANTERN = register("crimson_roots_paper_lantern", new PaperLanternBlock(Blocks.CRIMSON_ROOTS, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    Block DANDELION_PAPER_LANTERN = register("dandelion_paper_lantern", new PaperLanternBlock(Blocks.DANDELION, FabricBlockSettings.copyOf(PAPER_LANTERN)));

    /* Bamboo */

    Block BAMBOO_THATCH = register("bamboo_thatch", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    Block BAMBOO_THATCH_STAIRS = register("bamboo_thatch_stairs", new StairsBlock(BAMBOO_THATCH.getDefaultState(), FabricBlockSettings.copyOf(BAMBOO_THATCH)));
    Block BAMBOO_THATCH_SLAB = register("bamboo_thatch_slab", new SlabBlock(FabricBlockSettings.copyOf(BAMBOO_THATCH)));

    Block BUNDLED_BAMBOO = register("bundled_bamboo", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BUNDLED_BAMBOO = register("stripped_bundled_bamboo", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.SCAFFOLDING)));

    Block STRIPPED_BAMBOO = register("stripped_bamboo", new StrippedBambooBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO)));
    Block STRIPPED_BAMBOO_PLANKS = register("stripped_bamboo_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_SLAB = register("stripped_bamboo_slab", new SlabBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS)));
    Block STRIPPED_BAMBOO_FENCE = register("stripped_bamboo_fence", new FenceBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS)));
    Block STRIPPED_BAMBOO_FENCE_GATE = register("stripped_bamboo_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS)));
    Block STRIPPED_BAMBOO_STAIRS = register("stripped_bamboo_stairs", new StairsBlock(STRIPPED_BAMBOO_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS)));
    Block STRIPPED_BAMBOO_BUTTON = register("stripped_bamboo_button", new WoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_PRESSURE_PLATE = register("stripped_bamboo_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).noCollision().strength(0.5F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_DOOR = register("stripped_bamboo_door", new DoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).nonOpaque().strength(3.0F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_TRAPDOOR = register("stripped_bamboo_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(3.0F).nonOpaque().allowsSpawning(TwigsBlocks::never).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_SIGN = register("stripped_bamboo_sign", new SignBlock(FabricBlockSettings.of(Material.WOOD).noCollision().strength(1.0F).sounds(BlockSoundGroup.SCAFFOLDING), TwigsSignTypes.STRIPPED_BAMBOO));
    Block STRIPPED_BAMBOO_WALL_SIGN = register("stripped_bamboo_wall_sign", new WallSignBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_SIGN), TwigsSignTypes.STRIPPED_BAMBOO));

    Block STRIPPED_BAMBOO_MAT = register("stripped_bamboo_mat", new BambooMatBlock(FabricBlockSettings.of(Material.CARPET, MapColor.SPRUCE_BROWN).strength(0.1F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_TABLE = register("stripped_bamboo_table", new TableBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS).breakInstantly()));

    /* Tables */

    Block OAK_TABLE = register("oak_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).breakInstantly()));
    Block SPRUCE_TABLE = register("spruce_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS).breakInstantly()));
    Block BIRCH_TABLE = register("birch_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.BIRCH_PLANKS).breakInstantly()));
    Block JUNGLE_TABLE = register("jungle_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.JUNGLE_PLANKS).breakInstantly()));
    Block ACACIA_TABLE = register("acacia_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_PLANKS).breakInstantly()));
    Block DARK_OAK_TABLE = register("dark_oak_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_PLANKS).breakInstantly()));
    Block CRIMSON_TABLE = register("crimson_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_PLANKS).breakInstantly()));
    Block WARPED_TABLE = register("warped_table", new TableBlock(FabricBlockSettings.copyOf(Blocks.WARPED_PLANKS).breakInstantly()));

    /* Lamps */

    Block LAMP = register("lamp", new LampBlock(
        FabricBlockSettings.of(Material.METAL)
                           .requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN)
                           .luminance(TwigsBlocks::litLevel)
    ));

    Block SOUL_LAMP = register("soul_lamp", new LampBlock(FabricBlockSettings.copyOf(LAMP)));

    Block CRIMSON_SHROOMLAMP = register("crimson_shroomlamp", new Block(
        FabricBlockSettings.of(Material.NETHER_WOOD)
                           .strength(3.5F).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque()
                           .luminance(TwigsBlocks::lightMax)
                           .blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting)
    ));

    Block WARPED_SHROOMLAMP = register("warped_shroomlamp", new Block(
        FabricBlockSettings.of(Material.NETHER_WOOD)
                           .strength(3.5F).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque()
                           .luminance(TwigsBlocks::lightMax)
                           .blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting)
    ));

    /* Calcite */

    Block CALCITE_STAIRS = register("calcite_stairs", new StairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CALCITE)));
    Block CALCITE_SLAB = register("calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    Block CALCITE_WALL = register("calcite_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    Block POLISHED_CALCITE = register("polished_calcite", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    Block POLISHED_CALCITE_STAIRS = register("polished_calcite_stairs", new StairsBlock(POLISHED_CALCITE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_CALCITE)));
    Block POLISHED_CALCITE_SLAB = register("polished_calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE)));
    Block POLISHED_CALCITE_BRICKS = register("polished_calcite_bricks", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    Block POLISHED_CALCITE_BRICK_STAIRS = register("polished_calcite_brick_stairs", new StairsBlock(POLISHED_CALCITE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));
    Block POLISHED_CALCITE_BRICK_SLAB = register("polished_calcite_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));
    Block POLISHED_CALCITE_BRICK_WALL = register("polished_calcite_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));
    Block CRACKED_POLISHED_CALCITE_BRICKS = register("cracked_polished_calcite_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_CALCITE_BRICKS)));

    /* Schist */

    Block SCHIST = register("schist", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    Block SCHIST_STAIRS = register("schist_stairs", new StairsBlock(SCHIST.getDefaultState(), FabricBlockSettings.copyOf(SCHIST)));
    Block SCHIST_SLAB = register("schist_slab", new SlabBlock(FabricBlockSettings.copyOf(SCHIST)));
    Block SCHIST_WALL = register("schist_wall", new WallBlock(FabricBlockSettings.copyOf(SCHIST)));
    Block POLISHED_SCHIST = register("polished_schist", new Block(FabricBlockSettings.copyOf(SCHIST)));
    Block POLISHED_SCHIST_STAIRS = register("polished_schist_stairs", new StairsBlock(POLISHED_SCHIST.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_SCHIST)));
    Block POLISHED_SCHIST_SLAB = register("polished_schist_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST)));
    Block POLISHED_SCHIST_BRICKS = register("polished_schist_bricks", new Block(FabricBlockSettings.copyOf(SCHIST)));
    Block POLISHED_SCHIST_BRICK_STAIRS = register("polished_schist_brick_stairs", new StairsBlock(POLISHED_SCHIST_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));
    Block POLISHED_SCHIST_BRICK_SLAB = register("polished_schist_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));
    Block POLISHED_SCHIST_BRICK_WALL = register("polished_schist_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));
    Block CRACKED_POLISHED_SCHIST_BRICKS = register("cracked_polished_schist_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS)));

    /* Cobblestone Bricks */

    Block ROCKY_DIRT = register("rocky_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT).strength(2.5F).sounds(BlockSoundGroup.TUFF).requiresTool()));

    Block COBBLESTONE_BRICKS = register("cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    Block COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs", new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    Block COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    Block COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    Block CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    Block MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    Block MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs", new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    Block MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    Block MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));

    /* Tuff */

    Block TUFF_STAIRS = register("tuff_stairs", new StairsBlock(Blocks.TUFF.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BASALT)));
    Block TUFF_SLAB = register("tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    Block TUFF_WALL = register("tuff_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.TUFF)));
    Block POLISHED_TUFF = register("polished_tuff", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    Block POLISHED_TUFF_STAIRS = register("polished_tuff_stairs", new StairsBlock(POLISHED_TUFF.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_TUFF)));
    Block POLISHED_TUFF_SLAB = register("polished_tuff_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_TUFF)));
    Block POLISHED_TUFF_BRICKS = register("polished_tuff_bricks", new Block(FabricBlockSettings.copyOf(Blocks.TUFF)));
    Block POLISHED_TUFF_BRICK_STAIRS = register("polished_tuff_brick_stairs", new StairsBlock(POLISHED_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));
    Block POLISHED_TUFF_BRICK_SLAB = register("polished_tuff_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));
    Block POLISHED_TUFF_BRICK_WALL = register("polished_tuff_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));
    Block CRACKED_POLISHED_TUFF_BRICKS = register("cracked_polished_tuff_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_TUFF_BRICKS)));

    /* Basalt */

    Block POLISHED_BASALT_BRICKS = register("polished_basalt_bricks", new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
    Block SMOOTH_BASALT_BRICKS = register("smooth_basalt_bricks", new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    Block SMOOTH_BASALT_BRICK_STAIRS = register("smooth_basalt_brick_stairs", new StairsBlock(SMOOTH_BASALT_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));
    Block SMOOTH_BASALT_BRICK_SLAB = register("smooth_basalt_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));
    Block SMOOTH_BASALT_BRICK_WALL = register("smooth_basalt_brick_wall", new WallBlock(FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));

    /* Bloodstone */

    Block BLOODSTONE = register("bloodstone", new Block(FabricBlockSettings.copyOf(Blocks.BASALT)));
    Block BLOODSTONE_STAIRS = register("bloodstone_stairs", new StairsBlock(BLOODSTONE.getDefaultState(), FabricBlockSettings.copyOf(BLOODSTONE)));
    Block BLOODSTONE_SLAB = register("bloodstone_slab", new SlabBlock(FabricBlockSettings.copyOf(BLOODSTONE)));
    Block BLOODSTONE_WALL = register("bloodstone_wall", new WallBlock(FabricBlockSettings.copyOf(BLOODSTONE)));
    Block POLISHED_BLOODSTONE = register("polished_bloodstone", new Block(FabricBlockSettings.copyOf(BLOODSTONE)));
    Block POLISHED_BLOODSTONE_STAIRS = register("polished_bloodstone_stairs", new StairsBlock(POLISHED_BLOODSTONE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLOODSTONE)));
    Block POLISHED_BLOODSTONE_SLAB = register("polished_bloodstone_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE)));
    Block POLISHED_BLOODSTONE_BRICKS = register("polished_bloodstone_bricks", new Block(FabricBlockSettings.copyOf(BLOODSTONE)));
    Block POLISHED_BLOODSTONE_BRICK_STAIRS = register("polished_bloodstone_brick_stairs", new StairsBlock(POLISHED_BLOODSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));
    Block POLISHED_BLOODSTONE_BRICK_SLAB = register("polished_bloodstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));
    Block POLISHED_BLOODSTONE_BRICK_WALL = register("polished_bloodstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));
    Block CRACKED_POLISHED_BLOODSTONE_BRICKS = register("cracked_polished_bloodstone_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS)));

    /* Twisting/Weeping Blackstone Bricks */

    Block TWISTING_POLISHED_BLACKSTONE_BRICKS = register("twisting_polished_blackstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    Block TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS = register("twisting_polished_blackstone_brick_stairs", new StairsBlock(TWISTING_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    Block TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB = register("twisting_polished_blackstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    Block TWISTING_POLISHED_BLACKSTONE_BRICK_WALL = register("twisting_polished_blackstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    Block WEEPING_POLISHED_BLACKSTONE_BRICKS = register("weeping_polished_blackstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    Block WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS = register("weeping_polished_blackstone_brick_stairs", new StairsBlock(WEEPING_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));
    Block WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB = register("weeping_polished_blackstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));
    Block WEEPING_POLISHED_BLACKSTONE_BRICK_WALL = register("weeping_polished_blackstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));

    /* Rhyolite */

    Block RHYOLITE = register("rhyolite", new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    Block RHYOLITE_STAIRS = register("rhyolite_stairs", new StairsBlock(RHYOLITE.getDefaultState(), FabricBlockSettings.copyOf(RHYOLITE)));
    Block RHYOLITE_SLAB = register("rhyolite_slab", new SlabBlock(FabricBlockSettings.copyOf(RHYOLITE)));
    Block RHYOLITE_WALL = register("rhyolite_wall", new WallBlock(FabricBlockSettings.copyOf(RHYOLITE)));
    Block POLISHED_RHYOLITE = register("polished_rhyolite", new Block(FabricBlockSettings.copyOf(RHYOLITE)));
    Block POLISHED_RHYOLITE_STAIRS = register("polished_rhyolite_stairs", new StairsBlock(POLISHED_RHYOLITE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_RHYOLITE)));
    Block POLISHED_RHYOLITE_SLAB = register("polished_rhyolite_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE)));
    Block POLISHED_RHYOLITE_BRICKS = register("polished_rhyolite_bricks", new Block(FabricBlockSettings.copyOf(RHYOLITE)));
    Block POLISHED_RHYOLITE_BRICK_STAIRS = register("polished_rhyolite_brick_stairs", new StairsBlock(POLISHED_RHYOLITE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));
    Block POLISHED_RHYOLITE_BRICK_SLAB = register("polished_rhyolite_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));
    Block POLISHED_RHYOLITE_BRICK_WALL = register("polished_rhyolite_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));
    Block CRACKED_POLISHED_RHYOLITE_BRICKS = register("cracked_polished_rhyolite_bricks", new Block(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS)));

    /* Bricks */

    Block CHISELED_BRICKS = register("chiseled_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    Block CRACKED_BRICKS = register("cracked_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    Block MOSSY_BRICKS = register("mossy_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    Block MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", new StairsBlock(MOSSY_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_BRICKS)));
    Block MOSSY_BRICK_SLAB = register("mossy_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)));
    Block MOSSY_BRICK_WALL = register("mossy_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)));

    /* Copper */

    Block COPPER_PILLAR = register("copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, FabricBlockSettings.copyOf(Blocks.CUT_COPPER)));
    Block EXPOSED_COPPER_PILLAR = register("exposed_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, FabricBlockSettings.copyOf(Blocks.EXPOSED_CUT_COPPER)));
    Block WEATHERED_COPPER_PILLAR = register("weathered_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, FabricBlockSettings.copyOf(Blocks.WEATHERED_CUT_COPPER)));
    Block OXIDIZED_COPPER_PILLAR = register("oxidized_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, FabricBlockSettings.copyOf(Blocks.OXIDIZED_CUT_COPPER)));
    Block WAXED_COPPER_PILLAR = register("waxed_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_CUT_COPPER)));
    Block WAXED_EXPOSED_COPPER_PILLAR = register("waxed_exposed_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_EXPOSED_CUT_COPPER)));
    Block WAXED_WEATHERED_COPPER_PILLAR = register("waxed_weathered_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_WEATHERED_CUT_COPPER)));
    Block WAXED_OXIDIZED_COPPER_PILLAR = register("waxed_oxidized_copper_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WAXED_OXIDIZED_CUT_COPPER)));

    /* Amethyst */

    Block POLISHED_AMETHYST = register("polished_amethyst", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    Block POLISHED_AMETHYST_STAIRS = register("polished_amethyst_stairs", new StairsBlock(POLISHED_AMETHYST.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_AMETHYST)));
    Block POLISHED_AMETHYST_SLAB = register("polished_amethyst_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST)));
    Block CHISELED_POLISHED_AMETHYST = register("chiseled_polished_amethyst", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    Block POLISHED_AMETHYST_BRICKS = register("polished_amethyst_bricks", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    Block POLISHED_AMETHYST_BRICK_STAIRS = register("polished_amethyst_brick_stairs", new StairsBlock(POLISHED_AMETHYST_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    Block POLISHED_AMETHYST_BRICK_SLAB = register("polished_amethyst_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    Block POLISHED_AMETHYST_BRICK_WALL = register("polished_amethyst_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    Block CRACKED_POLISHED_AMETHYST_BRICKS = register("cracked_polished_amethyst_bricks", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));

    static ToIntFunction<BlockState> litLevel(int lit) {
        return (state) -> state.get(Properties.LIT) ? lit : 0;
    }

    static int litLevel(BlockState state) {
        return litLevel(15).applyAsInt(state);
    }

    static int lightMax(BlockState state) {
        return 15;
    }

    static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Twigs.MOD_ID, id), block);
    }
}
