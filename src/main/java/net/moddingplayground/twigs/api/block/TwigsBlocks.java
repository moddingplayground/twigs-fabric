package net.moddingplayground.twigs.api.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static net.minecraft.block.Blocks.*;

public interface TwigsBlocks {
    Block TWIG = registerFloorLayer("twig", MapColor.OAK_TAN, BlockSoundGroup.WOOD);
    Block PEBBLE = registerFloorLayer("pebble", MapColor.STONE_GRAY, BlockSoundGroup.STONE);
    Block SEA_SHELL = registerFloorLayer("sea_shell", MapColor.WHITE_GRAY, BlockSoundGroup.BONE);

    Block AZALEA_FLOWERS = register("azalea_flowers", new GlowLichenBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.AZALEA)));
    Block POTTED_AZALEA_FLOWERS = register("potted_azalea_flowers", new FlowerPotBlock(AZALEA_FLOWERS, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));

    /* Paper Lanterns */

    Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(AIR, FabricBlockSettings.of(Material.WOOL).breakInstantly().luminance(luminanceNotWaterlogged(10)).nonOpaque().sounds(BlockSoundGroup.BAMBOO)));
    Block ALLIUM_PAPER_LANTERN = registerPaperLantern("allium_paper_lantern", ALLIUM);
    Block BLUE_ORCHID_PAPER_LANTERN = registerPaperLantern("blue_orchid_paper_lantern", BLUE_ORCHID);
    Block CRIMSON_ROOTS_PAPER_LANTERN = registerPaperLantern("crimson_roots_paper_lantern", CRIMSON_ROOTS);
    Block DANDELION_PAPER_LANTERN = registerPaperLantern("dandelion_paper_lantern", DANDELION);

    /* Bamboo */

    Block BAMBOO_LEAVES = register("bamboo_leaves", new BambooLeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).nonOpaque().breakInstantly().noCollision().sounds(BlockSoundGroup.AZALEA_LEAVES)));

    Block BAMBOO_THATCH = register("bamboo_thatch", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.2F).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    Block BAMBOO_THATCH_STAIRS = registerStairs("bamboo_thatch_stairs", BAMBOO_THATCH);
    Block BAMBOO_THATCH_SLAB = registerSlab("bamboo_thatch_slab", BAMBOO_THATCH);

    Block BUNDLED_BAMBOO = register("bundled_bamboo", new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BUNDLED_BAMBOO = register("stripped_bundled_bamboo", PillarBlock::new, BUNDLED_BAMBOO, settings -> settings.mapColor(MapColor.OFF_WHITE));

    Block STRIPPED_BAMBOO = register("stripped_bamboo", StrippedBambooBlock::new, BAMBOO);

    Block STRIPPED_BAMBOO_PLANKS = register("stripped_bamboo_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_SLAB = registerSlab("stripped_bamboo_slab", STRIPPED_BAMBOO_PLANKS);
    Block STRIPPED_BAMBOO_FENCE = register("stripped_bamboo_fence", FenceBlock::new, STRIPPED_BAMBOO_PLANKS);
    Block STRIPPED_BAMBOO_FENCE_GATE = register("stripped_bamboo_fence_gate", FenceGateBlock::new, STRIPPED_BAMBOO_PLANKS);
    Block STRIPPED_BAMBOO_STAIRS = registerStairs("stripped_bamboo_stairs", STRIPPED_BAMBOO_PLANKS);
    Block STRIPPED_BAMBOO_BUTTON = register("stripped_bamboo_button", new WoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_PRESSURE_PLATE = register("stripped_bamboo_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).noCollision().strength(0.5F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_DOOR = register("stripped_bamboo_door", new DoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).nonOpaque().strength(3.0F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_TRAPDOOR = register("stripped_bamboo_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(3.0F).nonOpaque().allowsSpawning(TwigsBlocks::never).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_SIGN = register("stripped_bamboo_sign", new SignBlock(FabricBlockSettings.of(Material.WOOD).noCollision().strength(1.0F).sounds(BlockSoundGroup.SCAFFOLDING), TwigsSignTypes.STRIPPED_BAMBOO));
    Block STRIPPED_BAMBOO_WALL_SIGN = register("stripped_bamboo_wall_sign", new WallSignBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_SIGN).dropsLike(STRIPPED_BAMBOO_SIGN), TwigsSignTypes.STRIPPED_BAMBOO));

    Block STRIPPED_BAMBOO_MAT = register("stripped_bamboo_mat", new BambooMatBlock(FabricBlockSettings.of(Material.WOOD, MapColor.SPRUCE_BROWN).strength(0.1F).sounds(BlockSoundGroup.SCAFFOLDING)));
    Block STRIPPED_BAMBOO_TABLE = registerTable("stripped_bamboo_table", STRIPPED_BAMBOO_PLANKS);

    /* Tables */

    Block OAK_TABLE = registerTable("oak_table", OAK_PLANKS);
    Block SPRUCE_TABLE = registerTable("spruce_table", SPRUCE_PLANKS);
    Block BIRCH_TABLE = registerTable("birch_table", BIRCH_PLANKS);
    Block JUNGLE_TABLE = registerTable("jungle_table", JUNGLE_PLANKS);
    Block ACACIA_TABLE = registerTable("acacia_table", ACACIA_PLANKS);
    Block DARK_OAK_TABLE = registerTable("dark_oak_table", DARK_OAK_PLANKS);
    Block CRIMSON_TABLE = registerTable("crimson_table", CRIMSON_PLANKS);
    Block WARPED_TABLE = registerTable("warped_table", WARPED_PLANKS);

    /* Lamps */

    Block LAMP = register("lamp", new LampBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(4.5F).luminance(TwigsBlocks::luminanceWhenLit).sounds(BlockSoundGroup.LANTERN)));
    Block SOUL_LAMP = register("soul_lamp", LampBlock::new, LAMP);

    Block CRIMSON_SHROOMLAMP = registerShroomlamp("crimson_shroomlamp");
    Block WARPED_SHROOMLAMP = registerShroomlamp("warped_shroomlamp");

    /* Calcite */

    Block CALCITE_STAIRS = registerStairs("calcite_stairs", CALCITE);
    Block CALCITE_SLAB = registerSlab("calcite_slab", CALCITE);
    Block CALCITE_WALL = registerWall("calcite_wall", CALCITE);
    Block POLISHED_CALCITE = registerCopy("polished_calcite", CALCITE);
    Block POLISHED_CALCITE_STAIRS = registerStairs("polished_calcite_stairs", POLISHED_CALCITE);
    Block POLISHED_CALCITE_SLAB = registerSlab("polished_calcite_slab", POLISHED_CALCITE);
    Block POLISHED_CALCITE_BRICKS = registerCopy("polished_calcite_bricks", CALCITE);
    Block POLISHED_CALCITE_BRICK_STAIRS = registerStairs("polished_calcite_brick_stairs", POLISHED_CALCITE_BRICKS);
    Block POLISHED_CALCITE_BRICK_SLAB = registerSlab("polished_calcite_brick_slab", POLISHED_CALCITE_BRICKS);
    Block POLISHED_CALCITE_BRICK_WALL = registerWall("polished_calcite_brick_wall", POLISHED_CALCITE_BRICKS);
    Block CRACKED_POLISHED_CALCITE_BRICKS = registerCopy("cracked_polished_calcite_bricks", POLISHED_CALCITE_BRICKS);

    /* Schist */

    Block SCHIST = registerCopy("schist", CALCITE);
    Block SCHIST_STAIRS = registerStairs("schist_stairs", SCHIST);
    Block SCHIST_SLAB = registerSlab("schist_slab", SCHIST);
    Block SCHIST_WALL = registerWall("schist_wall", SCHIST);
    Block POLISHED_SCHIST = registerCopy("polished_schist", SCHIST);
    Block POLISHED_SCHIST_STAIRS = registerStairs("polished_schist_stairs", POLISHED_SCHIST);
    Block POLISHED_SCHIST_SLAB = registerSlab("polished_schist_slab", POLISHED_SCHIST);
    Block POLISHED_SCHIST_BRICKS = registerCopy("polished_schist_bricks", SCHIST);
    Block POLISHED_SCHIST_BRICK_STAIRS = registerStairs("polished_schist_brick_stairs", POLISHED_SCHIST_BRICKS);
    Block POLISHED_SCHIST_BRICK_SLAB = registerSlab("polished_schist_brick_slab", POLISHED_SCHIST_BRICKS);
    Block POLISHED_SCHIST_BRICK_WALL = registerWall("polished_schist_brick_wall", POLISHED_SCHIST_BRICKS);
    Block CRACKED_POLISHED_SCHIST_BRICKS = registerCopy("cracked_polished_schist_bricks", POLISHED_SCHIST_BRICKS);

    /* Cobblestone Bricks */

    Block ROCKY_DIRT = register("rocky_dirt", new Block(FabricBlockSettings.copyOf(DIRT).strength(2.5F).sounds(BlockSoundGroup.TUFF).requiresTool()));

    Block COBBLESTONE_BRICKS = registerCopy("cobblestone_bricks", COBBLESTONE);
    Block COBBLESTONE_BRICK_STAIRS = registerStairs("cobblestone_brick_stairs", COBBLESTONE_BRICKS);
    Block COBBLESTONE_BRICK_SLAB = registerSlab("cobblestone_brick_slab", COBBLESTONE_BRICKS);
    Block COBBLESTONE_BRICK_WALL = registerWall("cobblestone_brick_wall", COBBLESTONE_BRICKS);
    Block CRACKED_COBBLESTONE_BRICKS = registerCopy("cracked_cobblestone_bricks", COBBLESTONE);
    Block MOSSY_COBBLESTONE_BRICKS = registerCopy("mossy_cobblestone_bricks", COBBLESTONE);
    Block MOSSY_COBBLESTONE_BRICK_STAIRS = registerStairs("mossy_cobblestone_brick_stairs", COBBLESTONE_BRICKS);
    Block MOSSY_COBBLESTONE_BRICK_SLAB = registerSlab("mossy_cobblestone_brick_slab", COBBLESTONE_BRICKS);
    Block MOSSY_COBBLESTONE_BRICK_WALL = registerWall("mossy_cobblestone_brick_wall", COBBLESTONE_BRICKS);

    /* Tuff */

    Block TUFF_STAIRS = registerStairs("tuff_stairs", TUFF);
    Block TUFF_SLAB = registerSlab("tuff_slab", TUFF);
    Block TUFF_WALL = registerWall("tuff_wall", TUFF);
    Block POLISHED_TUFF = registerCopy("polished_tuff", TUFF);
    Block POLISHED_TUFF_STAIRS = registerStairs("polished_tuff_stairs", POLISHED_TUFF);
    Block POLISHED_TUFF_SLAB = registerSlab("polished_tuff_slab", POLISHED_TUFF);
    Block POLISHED_TUFF_BRICKS = registerCopy("polished_tuff_bricks", TUFF);
    Block POLISHED_TUFF_BRICK_STAIRS = registerStairs("polished_tuff_brick_stairs", POLISHED_TUFF_BRICKS);
    Block POLISHED_TUFF_BRICK_SLAB = registerSlab("polished_tuff_brick_slab", POLISHED_TUFF_BRICKS);
    Block POLISHED_TUFF_BRICK_WALL = registerWall("polished_tuff_brick_wall", POLISHED_TUFF_BRICKS);
    Block CRACKED_POLISHED_TUFF_BRICKS = registerCopy("cracked_polished_tuff_bricks", POLISHED_TUFF_BRICKS);

    /* Basalt */

    Block POLISHED_BASALT_BRICKS = registerPillar("polished_basalt_bricks", POLISHED_BASALT);
    Block SMOOTH_BASALT_BRICKS = registerCopy("smooth_basalt_bricks", SMOOTH_BASALT);
    Block SMOOTH_BASALT_BRICK_STAIRS = registerStairs("smooth_basalt_brick_stairs", SMOOTH_BASALT_BRICKS);
    Block SMOOTH_BASALT_BRICK_SLAB = registerSlab("smooth_basalt_brick_slab", SMOOTH_BASALT_BRICKS);
    Block SMOOTH_BASALT_BRICK_WALL = registerWall("smooth_basalt_brick_wall", SMOOTH_BASALT_BRICKS);

    /* Bloodstone */

    Block BLOODSTONE = registerCopy("bloodstone", BASALT);
    Block BLOODSTONE_STAIRS = registerStairs("bloodstone_stairs", BLOODSTONE);
    Block BLOODSTONE_SLAB = registerSlab("bloodstone_slab", BLOODSTONE);
    Block BLOODSTONE_WALL = registerWall("bloodstone_wall", BLOODSTONE);
    Block POLISHED_BLOODSTONE = registerCopy("polished_bloodstone", BLOODSTONE);
    Block POLISHED_BLOODSTONE_STAIRS = registerStairs("polished_bloodstone_stairs", POLISHED_BLOODSTONE);
    Block POLISHED_BLOODSTONE_SLAB = registerSlab("polished_bloodstone_slab", POLISHED_BLOODSTONE);
    Block POLISHED_BLOODSTONE_BRICKS = registerCopy("polished_bloodstone_bricks", BLOODSTONE);
    Block POLISHED_BLOODSTONE_BRICK_STAIRS = registerStairs("polished_bloodstone_brick_stairs", POLISHED_BLOODSTONE_BRICKS);
    Block POLISHED_BLOODSTONE_BRICK_SLAB = registerSlab("polished_bloodstone_brick_slab", POLISHED_BLOODSTONE_BRICKS);
    Block POLISHED_BLOODSTONE_BRICK_WALL = registerWall("polished_bloodstone_brick_wall", POLISHED_BLOODSTONE_BRICKS);
    Block CRACKED_POLISHED_BLOODSTONE_BRICKS = registerCopy("cracked_polished_bloodstone_bricks", POLISHED_BLOODSTONE_BRICKS);

    /* Twisting/Weeping Blackstone Bricks */

    Block TWISTING_POLISHED_BLACKSTONE_BRICKS = registerCopy("twisting_polished_blackstone_bricks", POLISHED_BLACKSTONE_BRICKS);
    Block TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS = registerStairs("twisting_polished_blackstone_brick_stairs", TWISTING_POLISHED_BLACKSTONE_BRICKS);
    Block TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB = registerSlab("twisting_polished_blackstone_brick_slab", TWISTING_POLISHED_BLACKSTONE_BRICKS);
    Block TWISTING_POLISHED_BLACKSTONE_BRICK_WALL = registerWall("twisting_polished_blackstone_brick_wall", TWISTING_POLISHED_BLACKSTONE_BRICKS);
    Block WEEPING_POLISHED_BLACKSTONE_BRICKS = registerCopy("weeping_polished_blackstone_bricks", POLISHED_BLACKSTONE_BRICKS);
    Block WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS = registerStairs("weeping_polished_blackstone_brick_stairs", WEEPING_POLISHED_BLACKSTONE_BRICKS);
    Block WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB = registerSlab("weeping_polished_blackstone_brick_slab", WEEPING_POLISHED_BLACKSTONE_BRICKS);
    Block WEEPING_POLISHED_BLACKSTONE_BRICK_WALL = registerWall("weeping_polished_blackstone_brick_wall", WEEPING_POLISHED_BLACKSTONE_BRICKS);

    /* Rhyolite */

    Block RHYOLITE = registerPillar("rhyolite", DEEPSLATE);
    Block RHYOLITE_STAIRS = registerStairs("rhyolite_stairs", RHYOLITE);
    Block RHYOLITE_SLAB = registerSlab("rhyolite_slab", RHYOLITE);
    Block RHYOLITE_WALL = registerWall("rhyolite_wall", RHYOLITE);
    Block POLISHED_RHYOLITE = registerCopy("polished_rhyolite", RHYOLITE);
    Block POLISHED_RHYOLITE_STAIRS = registerStairs("polished_rhyolite_stairs", POLISHED_RHYOLITE);
    Block POLISHED_RHYOLITE_SLAB = registerSlab("polished_rhyolite_slab", POLISHED_RHYOLITE);
    Block POLISHED_RHYOLITE_BRICKS = registerCopy("polished_rhyolite_bricks", RHYOLITE);
    Block POLISHED_RHYOLITE_BRICK_STAIRS = registerStairs("polished_rhyolite_brick_stairs", POLISHED_RHYOLITE_BRICKS);
    Block POLISHED_RHYOLITE_BRICK_SLAB = registerSlab("polished_rhyolite_brick_slab", POLISHED_RHYOLITE_BRICKS);
    Block POLISHED_RHYOLITE_BRICK_WALL = registerWall("polished_rhyolite_brick_wall", POLISHED_RHYOLITE_BRICKS);
    Block CRACKED_POLISHED_RHYOLITE_BRICKS = registerCopy("cracked_polished_rhyolite_bricks", POLISHED_RHYOLITE_BRICKS);

    /* Bricks */

    Block CHISELED_BRICKS = registerCopy("chiseled_bricks", BRICKS);
    Block CRACKED_BRICKS = registerCopy("cracked_bricks", BRICKS);
    Block MOSSY_BRICKS = registerCopy("mossy_bricks", BRICKS);
    Block MOSSY_BRICK_STAIRS = registerStairs("mossy_brick_stairs", MOSSY_BRICKS);
    Block MOSSY_BRICK_SLAB = registerSlab("mossy_brick_slab", MOSSY_BRICKS);
    Block MOSSY_BRICK_WALL = registerWall("mossy_brick_wall", MOSSY_BRICKS);

    /* Copper */

    Block COPPER_PILLAR = registerPillarOxidizable("copper_pillar", CUT_COPPER, Oxidizable.OxidationLevel.UNAFFECTED);
    Block EXPOSED_COPPER_PILLAR = registerPillarOxidizable("exposed_copper_pillar", EXPOSED_CUT_COPPER, Oxidizable.OxidationLevel.EXPOSED);
    Block WEATHERED_COPPER_PILLAR = registerPillarOxidizable("weathered_copper_pillar", WEATHERED_CUT_COPPER, Oxidizable.OxidationLevel.WEATHERED);
    Block OXIDIZED_COPPER_PILLAR = registerPillarOxidizable("oxidized_copper_pillar", OXIDIZED_CUT_COPPER, Oxidizable.OxidationLevel.OXIDIZED);

    Block WAXED_COPPER_PILLAR = registerPillar("waxed_copper_pillar", WAXED_CUT_COPPER);
    Block WAXED_EXPOSED_COPPER_PILLAR = registerPillar("waxed_exposed_copper_pillar", WAXED_EXPOSED_CUT_COPPER);
    Block WAXED_WEATHERED_COPPER_PILLAR = registerPillar("waxed_weathered_copper_pillar", WAXED_WEATHERED_CUT_COPPER);
    Block WAXED_OXIDIZED_COPPER_PILLAR = registerPillar("waxed_oxidized_copper_pillar", WAXED_OXIDIZED_CUT_COPPER);

    /* Amethyst */

    Block POLISHED_AMETHYST = register("polished_amethyst", AmethystBlock::new, AMETHYST_BLOCK);
    Block POLISHED_AMETHYST_STAIRS = registerAmethystStairs("polished_amethyst_stairs", POLISHED_AMETHYST);
    Block POLISHED_AMETHYST_SLAB = register("polished_amethyst_slab", AmethystSlabBlock::new, POLISHED_AMETHYST);

    Block POLISHED_AMETHYST_BRICKS = register("polished_amethyst_bricks", AmethystBlock::new, POLISHED_AMETHYST);
    Block POLISHED_AMETHYST_BRICK_STAIRS = registerAmethystStairs("polished_amethyst_brick_stairs", POLISHED_AMETHYST_BRICKS);
    Block POLISHED_AMETHYST_BRICK_SLAB = register("polished_amethyst_brick_slab", AmethystSlabBlock::new, POLISHED_AMETHYST_BRICKS);
    Block POLISHED_AMETHYST_BRICK_WALL = register("polished_amethyst_brick_wall", AmethystWallBlock::new, POLISHED_AMETHYST_BRICKS);

    Block CHISELED_POLISHED_AMETHYST = register("chiseled_polished_amethyst", AmethystBlock::new, POLISHED_AMETHYST);
    Block CRACKED_POLISHED_AMETHYST_BRICKS = register("cracked_polished_amethyst_bricks", AmethystBlock::new, POLISHED_AMETHYST);

    static ToIntFunction<BlockState> luminanceWhenLit(int lit) {
        return state -> luminanceWhenLit(state, lit);
    }

    static int luminanceWhenLit(BlockState state, int lit) {
        return state.get(Properties.LIT) ? lit : 0;
    }

    static int luminanceWhenLit(BlockState state) {
        return luminanceWhenLit(state, 15);
    }

    static int luminanceMax(BlockState state) {
        return 15;
    }

    static ToIntFunction<BlockState> luminanceNotWaterlogged(int light) {
        return state -> state.get(Properties.WATERLOGGED) ? 0 : light;
    }

    static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Twigs.MOD_ID, id), block);
    }

    private static Block register(String id, Function<FabricBlockSettings, Block> block, Block copy) {
        return register(id, block.apply(FabricBlockSettings.copyOf(copy)));
    }

    private static Block register(String id, Function<FabricBlockSettings, Block> block, Block copy, Consumer<FabricBlockSettings> modifier) {
        FabricBlockSettings settings = FabricBlockSettings.copyOf(copy);
        modifier.accept(settings);
        return register(id, block.apply(settings));
    }

    private static Block registerCopy(String id, Block copy) {
        return register(id, Block::new, copy);
    }

    private static Block registerPaperLantern(String id, Block base) {
        return register(id, new PaperLanternBlock(base, FabricBlockSettings.copyOf(PAPER_LANTERN)));
    }

    private static Block registerTable(String id, Block base) {
        return register(id, TableBlock::new, base, FabricBlockSettings::breakInstantly);
    }

    private static Block registerShroomlamp(String id) {
        return register(id, new Block(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.5F).luminance(TwigsBlocks::luminanceMax).sounds(BlockSoundGroup.SHROOMLIGHT)));
    }

    private static Block registerPillar(String id, Block base) {
        return register(id, PillarBlock::new, base);
    }

    private static Block registerPillarOxidizable(String id, Block base, Oxidizable.OxidationLevel oxidationLevel) {
        return register(id, new PillarOxidizableBlock(oxidationLevel, FabricBlockSettings.copyOf(base)));
    }

    private static Block registerStairs(String id, BiFunction<BlockState, FabricBlockSettings, Block> block, Block base) {
        return register(id, block.apply(base.getDefaultState(), FabricBlockSettings.copyOf(base)));
    }

    private static Block registerStairs(String id, Block base) {
        return registerStairs(id, StairsBlock::new, base);
    }

    private static Block registerAmethystStairs(String id, Block base) {
        return registerStairs(id, AmethystStairsBlock::new, base);
    }

    private static Block registerSlab(String id, Block base) {
        return register(id, SlabBlock::new, base);
    }

    private static Block registerWall(String id, Block base) {
        return register(id, WallBlock::new, base);
    }

    private static Block registerFloorLayer(String id, MapColor mapColor, BlockSoundGroup soundGroup) {
        return register(id, new FloorLayerBlock(FabricBlockSettings.of(TwigsMaterial.FLOOR_LAYER, mapColor).breakInstantly().noCollision().sounds(soundGroup)));
    }
}
