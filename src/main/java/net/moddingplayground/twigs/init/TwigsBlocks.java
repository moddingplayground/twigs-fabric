package net.moddingplayground.twigs.init;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.block.Material;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.BambooLeavesBlock;
import net.moddingplayground.twigs.block.LampBlock;
import net.moddingplayground.twigs.block.PaperLanternBlock;
import net.moddingplayground.twigs.block.PebbleBlock;
import net.moddingplayground.twigs.block.PillarOxidizableBlock;
import net.moddingplayground.twigs.block.TableBlock;
import net.moddingplayground.twigs.block.TwigBlock;
import net.moddingplayground.twigs.block.vanilla.PublicStairsBlock;

import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public final class TwigsBlocks {

    //lamps
    public static final Block LAMP = register("lamp", new LampBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(4.5F).sounds(BlockSoundGroup.LANTERN).luminance(createLightLevelFromLitBlockState(18))));
    public static final Block SOUL_LAMP = register("soul_lamp", new LampBlock(FabricBlockSettings.copyOf(TwigsBlocks.LAMP).luminance(createLightLevelFromLitBlockState(17))));
    public static final Block CRIMSON_SHROOMLAMP = register("crimson_shroomlamp", new Block(AbstractBlock.Settings.of(Material.NETHER_WOOD).strength(3.5F).sounds(BlockSoundGroup.SHROOMLIGHT).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 15).nonOpaque()));
    public static final Block WARPED_SHROOMLAMP = register("warped_shroomlamp", new Block(AbstractBlock.Settings.of(Material.NETHER_WOOD).strength(3.5F).sounds(BlockSoundGroup.SHROOMLIGHT).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 15).nonOpaque()));

    //azalea blocks
    public static final Block AZALEA_FLOWERS = register("azalea_flowers", new GlowLichenBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.AZALEA).noCollision()));
    public static final Block POTTED_AZALEA_FLOWERS = register("potted_azalea_flowers", new FlowerPotBlock(AZALEA_FLOWERS, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()), false);

    //bamboo blocks
    public static final Block BAMBOO_LEAVES = register("bamboo_leaves", new BambooLeavesBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES).breakInstantly().noCollision()), false);
    public static final Block STRIPPED_BAMBOO = register("stripped_bamboo", new ChainBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO)));
    public static final Block STRIPPED_BUNDLED_BAMBOO = register("stripped_bundled_bamboo", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.SCAFFOLDING)));
    public static final Block BUNDLED_BAMBOO = register("bundled_bamboo", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.SCAFFOLDING)));
    public static final Block BAMBOO_THATCH = register("bamboo_thatch", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    public static final Block BAMBOO_THATCH_STAIRS = register("bamboo_thatch_stairs", new PublicStairsBlock(BAMBOO_THATCH.getDefaultState(), FabricBlockSettings.copyOf(BAMBOO_THATCH)));
    public static final Block BAMBOO_THATCH_SLAB = register("bamboo_thatch_slab", new SlabBlock(FabricBlockSettings.copyOf(BAMBOO_THATCH)));

    //tables
    public static final Block OAK_TABLE = register("oak_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block SPRUCE_TABLE = register("spruce_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block BIRCH_TABLE = register("birch_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block JUNGLE_TABLE = register("jungle_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block ACACIA_TABLE = register("acacia_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block DARK_OAK_TABLE = register("dark_oak_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block CRIMSON_TABLE = register("crimson_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block WARPED_TABLE = register("warped_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).breakInstantly()));
    public static final Block STRIPPED_BAMBOO_TABLE = register("stripped_bamboo_table", new TableBlock(AbstractBlock.Settings.copy(Twigs.STRIPPED_BAMBOO_PLANKS).breakInstantly()));

    //brick blocks
    public static final Block CHISELED_BRICKS = register("chiseled_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block CRACKED_BRICKS = register("cracked_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block MOSSY_BRICKS = register("mossy_bricks", new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", new PublicStairsBlock(MOSSY_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_BRICKS)));
    public static final Block MOSSY_BRICK_SLAB = register("mossy_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)));
    public static final Block MOSSY_BRICK_WALL = register("mossy_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MOSSY_BRICKS)));

    //basalt blocks
    public static final Block POLISHED_BASALT_BRICKS = register("polished_basalt_bricks", new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
    public static final Block SMOOTH_BASALT_BRICKS = register("smooth_basalt_bricks", new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static final Block SMOOTH_BASALT_BRICK_STAIRS = register("smooth_basalt_brick_stairs", new PublicStairsBlock(SMOOTH_BASALT_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));
    public static final Block SMOOTH_BASALT_BRICK_SLAB = register("smooth_basalt_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));
    public static final Block SMOOTH_BASALT_BRICK_WALL = register("smooth_basalt_brick_wall", new WallBlock(FabricBlockSettings.copyOf(SMOOTH_BASALT_BRICKS)));

    //cobblestone bricks
    public static final Block COBBLESTONE_BRICKS = register("cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs", new PublicStairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(COBBLESTONE_BRICKS)));

    //amethyst blocks
    public static final Block POLISHED_AMETHYST_BRICKS = register("polished_amethyst_bricks", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block POLISHED_AMETHYST_BRICK_STAIRS = register("polished_amethyst_brick_stairs", new PublicStairsBlock(POLISHED_AMETHYST_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    public static final Block POLISHED_AMETHYST_BRICK_SLAB = register("polished_amethyst_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    public static final Block POLISHED_AMETHYST_BRICK_WALL = register("polished_amethyst_brick_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST_BRICKS)));
    public static final Block CRACKED_POLISHED_AMETHYST_BRICKS = register("cracked_polished_amethyst_bricks", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block POLISHED_AMETHYST = register("polished_amethyst", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block CHISELED_POLISHED_AMETHYST = register("chiseled_polished_amethyst", new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block POLISHED_AMETHYST_STAIRS = register("polished_amethyst_stairs", new PublicStairsBlock(POLISHED_AMETHYST.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_AMETHYST)));
    public static final Block POLISHED_AMETHYST_SLAB = register("polished_amethyst_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_AMETHYST)));

    //twisting and weeping blackstone blocks
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICKS = register("twisting_polished_blackstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS = register("twisting_polished_blackstone_brick_stairs", new PublicStairsBlock(TWISTING_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB = register("twisting_polished_blackstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block TWISTING_POLISHED_BLACKSTONE_BRICK_WALL = register("twisting_polished_blackstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(TWISTING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICKS = register("weeping_polished_blackstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS = register("weeping_polished_blackstone_brick_stairs", new PublicStairsBlock(WEEPING_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB = register("weeping_polished_blackstone_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_WALL = register("weeping_polished_blackstone_brick_wall", new WallBlock(FabricBlockSettings.copyOf(WEEPING_POLISHED_BLACKSTONE_BRICKS)));

    //paper lanterns
    public static final Block PAPER_LANTERN = register("paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(AbstractBlock.Settings.of(Material.WOOL).strength(0.5f, 0.0f).sounds(BlockSoundGroup.WOOL).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 10).nonOpaque())));
    public static final Block ALLIUM_PAPER_LANTERN = register("allium_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(AbstractBlock.Settings.of(Material.WOOL).strength(0.5f, 0.0f).sounds(BlockSoundGroup.WOOL).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 10).nonOpaque())));
    public static final Block BLUE_ORCHID_PAPER_LANTERN = register("blue_orchid_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(AbstractBlock.Settings.of(Material.WOOL).strength(0.5f, 0.0f).sounds(BlockSoundGroup.WOOL).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 10).nonOpaque())));
    public static final Block CRIMSON_ROOTS_PAPER_LANTERN = register("crimson_roots_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(AbstractBlock.Settings.of(Material.WOOL).strength(0.5f, 0.0f).sounds(BlockSoundGroup.WOOL).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 10).nonOpaque())));
    public static final Block DANDELION_PAPER_LANTERN = register("dandelion_paper_lantern", new PaperLanternBlock(FabricBlockSettings.copyOf(AbstractBlock.Settings.of(Material.WOOL).strength(0.5f, 0.0f).sounds(BlockSoundGroup.WOOL).blockVision(AbstractBlock.AbstractBlockState::hasEmissiveLighting).luminance(value -> 10).nonOpaque())));

    //miscellaneous blocks
    public static final Block ROCKY_DIRT = register("rocky_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT).strength(2.5F).sounds(BlockSoundGroup.TUFF).requiresTool()));
    public static final Block TWIG = register("twig", new TwigBlock(FabricBlockSettings.of(Material.WOOD).breakInstantly().sounds(BlockSoundGroup.WOOD).noCollision()));
    public static final Block PEBBLE = register("pebble", new PebbleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()));

    //tuff blocks
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

    //calcite blocks
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

    //copper blocks
    public static final Block COPPER_PILLAR = register("copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, FabricBlockSettings.copy(Blocks.CUT_COPPER)));
    public static final Block EXPOSED_COPPER_PILLAR = register("exposed_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, FabricBlockSettings.copy(Blocks.EXPOSED_CUT_COPPER)));
    public static final Block WEATHERED_COPPER_PILLAR = register("weathered_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, FabricBlockSettings.copy(Blocks.WEATHERED_CUT_COPPER)));
    public static final Block OXIDIZED_COPPER_PILLAR = register("oxidized_copper_pillar", new PillarOxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, FabricBlockSettings.copy(Blocks.OXIDIZED_CUT_COPPER)));
    public static final Block WAXED_COPPER_PILLAR = register("waxed_copper_pillar", new PillarBlock(FabricBlockSettings.copy(Blocks.WAXED_CUT_COPPER)));
    public static final Block WAXED_EXPOSED_COPPER_PILLAR = register("waxed_exposed_copper_pillar", new PillarBlock(FabricBlockSettings.copy(Blocks.WAXED_EXPOSED_CUT_COPPER)));
    public static final Block WAXED_WEATHERED_COPPER_PILLAR = register("waxed_weathered_copper_pillar", new PillarBlock(FabricBlockSettings.copy(Blocks.WAXED_WEATHERED_CUT_COPPER)));
    public static final Block WAXED_OXIDIZED_COPPER_PILLAR = register("waxed_oxidized_copper_pillar", new PillarBlock(FabricBlockSettings.copy(Blocks.WAXED_OXIDIZED_CUT_COPPER)));



    static {
        UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
            BlockPos pos = hit.getBlockPos();
            BlockState state = world.getBlockState(pos);
            if (state.isOf(Blocks.FLOWERING_AZALEA)) {
                ItemStack stack = player.getStackInHand(hand);
                if (stack.getItem() instanceof ShearsItem) {
                    world.setBlockState(pos, Blocks.AZALEA.getDefaultState());
                    world.playSoundFromEntity(null, player, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    Block.dropStack(world, pos.up(), new ItemStack(AZALEA_FLOWERS, world.random.nextInt(2) + 1));
                    stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    return ActionResult.success(world.isClient);
                }
            }

            return ActionResult.PASS;
        });

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_PILLAR, EXPOSED_COPPER_PILLAR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_PILLAR, WEATHERED_COPPER_PILLAR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_PILLAR, OXIDIZED_COPPER_PILLAR);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_PILLAR, WAXED_COPPER_PILLAR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_PILLAR, WAXED_EXPOSED_COPPER_PILLAR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_PILLAR, WAXED_WEATHERED_COPPER_PILLAR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_PILLAR, WAXED_OXIDIZED_COPPER_PILLAR);
    }

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> (Boolean)state.get(Properties.LIT) ? litLevel : 0;
    }

    private static Block register(String id, Block block, boolean registerItem) {
        Block registered = Registry.register(Registry.BLOCK, new Identifier(Twigs.MOD_ID, id), block);
        if (registerItem) {
            Registry.register(Registry.ITEM, new Identifier(Twigs.MOD_ID, id), new BlockItem(registered, new FabricItemSettings().group(Twigs.ITEM_GROUP)));
        }
        return registered;
    }
    private static Block register(String id, Block block) {
        return register(id, block, true);
    }

}
