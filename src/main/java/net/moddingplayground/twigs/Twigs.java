package net.moddingplayground.twigs;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.moddingplayground.twigs.block.BambooMatBlock;
import net.moddingplayground.twigs.block.vanilla.PublicDoorBlock;
import net.moddingplayground.twigs.block.vanilla.PublicPressurePlateBlock;
import net.moddingplayground.twigs.block.vanilla.PublicStairsBlock;
import net.moddingplayground.twigs.block.vanilla.PublicTrapdoorBlock;
import net.moddingplayground.twigs.block.vanilla.PublicWoodenButtonBlock;
import net.moddingplayground.twigs.entity.CustomBoatItem;
import net.moddingplayground.twigs.init.TwigsBlocks;
import net.moddingplayground.twigs.init.TwigsItems;
import net.moddingplayground.twigs.mixin.OrePlacedFeaturesInvoker;
import net.moddingplayground.twigs.mixin.SignTypeAccessor;

import java.util.List;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.*;
import static net.moddingplayground.twigs.entity.CustomBoatItem.*;
import static net.moddingplayground.twigs.init.TwigsBlocks.*;
import static net.moddingplayground.twigs.init.TwigsPlacedFeatures.*;

public class Twigs implements ModInitializer {
	public static final String MOD_ID = "twigs";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Twigs.MOD_ID, "item_group"), () -> new ItemStack(TwigsBlocks.TWIG));

	//rhyolite blocks
	public static final Block RHYOLITE = new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE));
	public static final Block RHYOLITE_STAIRS = new PublicStairsBlock(RHYOLITE.getDefaultState(), FabricBlockSettings.copyOf(RHYOLITE));
	public static final Block RHYOLITE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(RHYOLITE));
	public static final Block RHYOLITE_WALL = new WallBlock(FabricBlockSettings.copyOf(RHYOLITE));
	public static final Block POLISHED_RHYOLITE = new Block(FabricBlockSettings.copyOf(RHYOLITE));
	public static final Block POLISHED_RHYOLITE_STAIRS = new PublicStairsBlock(POLISHED_RHYOLITE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_RHYOLITE));
	public static final Block POLISHED_RHYOLITE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE));
	public static final Block POLISHED_RHYOLITE_BRICKS = new Block(FabricBlockSettings.copyOf(RHYOLITE));
	public static final Block POLISHED_RHYOLITE_BRICK_STAIRS = new PublicStairsBlock(POLISHED_RHYOLITE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS));
	public static final Block POLISHED_RHYOLITE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS));
	public static final Block POLISHED_RHYOLITE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS));
	public static final Block CRACKED_POLISHED_RHYOLITE_BRICKS = new Block(FabricBlockSettings.copyOf(POLISHED_RHYOLITE_BRICKS));

	//schist blocks
	public static final Block SCHIST = new Block(FabricBlockSettings.copyOf(Blocks.CALCITE));
	public static final Block SCHIST_STAIRS = new PublicStairsBlock(SCHIST.getDefaultState(), FabricBlockSettings.copyOf(SCHIST));
	public static final Block SCHIST_SLAB = new SlabBlock(FabricBlockSettings.copyOf(SCHIST));
	public static final Block SCHIST_WALL = new WallBlock(FabricBlockSettings.copyOf(SCHIST));
	public static final Block POLISHED_SCHIST = new Block(FabricBlockSettings.copyOf(SCHIST));
	public static final Block POLISHED_SCHIST_STAIRS = new PublicStairsBlock(POLISHED_SCHIST.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_SCHIST));
	public static final Block POLISHED_SCHIST_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST));
	public static final Block POLISHED_SCHIST_BRICKS = new Block(FabricBlockSettings.copyOf(SCHIST));
	public static final Block POLISHED_SCHIST_BRICK_STAIRS = new PublicStairsBlock(POLISHED_SCHIST_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS));
	public static final Block POLISHED_SCHIST_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS));
	public static final Block POLISHED_SCHIST_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS));
	public static final Block CRACKED_POLISHED_SCHIST_BRICKS = new Block(FabricBlockSettings.copyOf(POLISHED_SCHIST_BRICKS));


	//bloodstone blocks
	public static final Block BLOODSTONE = new Block(FabricBlockSettings.copyOf(Blocks.BASALT));
	public static final Block BLOODSTONE_STAIRS = new PublicStairsBlock(BLOODSTONE.getDefaultState(), FabricBlockSettings.copyOf(BLOODSTONE));
	public static final Block BLOODSTONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(BLOODSTONE));
	public static final Block BLOODSTONE_WALL = new WallBlock(FabricBlockSettings.copyOf(BLOODSTONE));
	public static final Block POLISHED_BLOODSTONE = new Block(FabricBlockSettings.copyOf(BLOODSTONE));
	public static final Block POLISHED_BLOODSTONE_STAIRS = new PublicStairsBlock(POLISHED_BLOODSTONE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLOODSTONE));
	public static final Block POLISHED_BLOODSTONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE));
	public static final Block POLISHED_BLOODSTONE_BRICKS = new Block(FabricBlockSettings.copyOf(BLOODSTONE));
	public static final Block POLISHED_BLOODSTONE_BRICK_STAIRS = new PublicStairsBlock(POLISHED_BLOODSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS));
	public static final Block POLISHED_BLOODSTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS));
	public static final Block POLISHED_BLOODSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS));
	public static final Block CRACKED_POLISHED_BLOODSTONE_BRICKS = new Block(FabricBlockSettings.copyOf(POLISHED_BLOODSTONE_BRICKS));

	private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
		return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
	}

	private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModfier) {
		return modifiers(CountPlacementModifier.of(count), heightModfier);
	}

	private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
		return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
	}

	public static final RegistryEntry<? extends ConfiguredFeature<?, ?>> ORE_RHYOLITE = ConfiguredFeatures.register("ore_rhyolite", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, RHYOLITE.getDefaultState(), 45));
	public static final RegistryEntry<? extends ConfiguredFeature<?, ?>> ORE_SCHIST = ConfiguredFeatures.register("ore_schist", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, SCHIST.getDefaultState(), 64));
	public static final RegistryEntry<? extends ConfiguredFeature<?, ?>> ORE_BLOODSTONE = ConfiguredFeatures.register("ore_bloodstone", Feature.ORE, new OreFeatureConfig(BASE_STONE_NETHER, BLOODSTONE.getDefaultState(), 64));


	public static final RegistryEntry<PlacedFeature> ORE_RHYOLITE_LOWER = PlacedFeatures.register("ore_rhyolite_lower", ORE_RHYOLITE, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(16))));
	public static final RegistryEntry<PlacedFeature> ORE_SCHIST_UPPER = PlacedFeatures.register("ore_schist_upper", ORE_SCHIST, modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))));
	public static final RegistryEntry<PlacedFeature> ORE_SCHIST_LOWER = PlacedFeatures.register("ore_schist_lower", ORE_SCHIST, OrePlacedFeaturesInvoker.invokeModifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));
	public static final RegistryEntry<PlacedFeature> ORE_BLOODSTONE_NETHER = PlacedFeatures.register("ore_bloodstone_nether", ORE_BLOODSTONE, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(5), YOffset.fixed(225))));
	//signs
	public static final SignType STRIPPED_BAMBOO_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("stripped_bamboo"));
	public static final Block STRIPPED_BAMBOO_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_WHITE).noCollision().strength(1.0F).sounds(BlockSoundGroup.SCAFFOLDING), STRIPPED_BAMBOO_SIGN_TYPE);
	public static final Block STRIPPED_BAMBOO_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_WHITE).noCollision().strength(1.0F).sounds(BlockSoundGroup.SCAFFOLDING).dropsLike(STRIPPED_BAMBOO_SIGN), STRIPPED_BAMBOO_SIGN_TYPE);

	public static final Block STRIPPED_BAMBOO_MAT = new BambooMatBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET).sounds(BlockSoundGroup.SCAFFOLDING));
	public static final Block STRIPPED_BAMBOO_PLANKS = new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).strength(1.0F, 1.5F).sounds(BlockSoundGroup.SCAFFOLDING));
	public static final Block STRIPPED_BAMBOO_STAIRS = new PublicStairsBlock(STRIPPED_BAMBOO_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_SLAB = new SlabBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_FENCE = new FenceBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_DOOR = new PublicDoorBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_TRAPDOOR = new PublicTrapdoorBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_BUTTON = new PublicWoodenButtonBlock(FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));
	public static final Block STRIPPED_BAMBOO_PRESSURE_PLATE = new PublicPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(STRIPPED_BAMBOO_PLANKS));




	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rhyolite"), RHYOLITE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rhyolite"), new BlockItem(RHYOLITE, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rhyolite_stairs"), RHYOLITE_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rhyolite_stairs"), new BlockItem(RHYOLITE_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rhyolite_slab"), RHYOLITE_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rhyolite_slab"), new BlockItem(RHYOLITE_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rhyolite_wall"), RHYOLITE_WALL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rhyolite_wall"), new BlockItem(RHYOLITE_WALL, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite"), POLISHED_RHYOLITE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite"), new BlockItem(POLISHED_RHYOLITE, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite_stairs"), POLISHED_RHYOLITE_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite_stairs"), new BlockItem(POLISHED_RHYOLITE_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite_slab"), POLISHED_RHYOLITE_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite_slab"), new BlockItem(POLISHED_RHYOLITE_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite_bricks"), POLISHED_RHYOLITE_BRICKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite_bricks"), new BlockItem(POLISHED_RHYOLITE_BRICKS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite_brick_stairs"), POLISHED_RHYOLITE_BRICK_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite_brick_stairs"), new BlockItem(POLISHED_RHYOLITE_BRICK_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite_brick_slab"), POLISHED_RHYOLITE_BRICK_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite_brick_slab"), new BlockItem(POLISHED_RHYOLITE_BRICK_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_rhyolite_brick_wall"), POLISHED_RHYOLITE_BRICK_WALL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_rhyolite_brick_wall"), new BlockItem(POLISHED_RHYOLITE_BRICK_WALL, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cracked_polished_rhyolite_bricks"), CRACKED_POLISHED_RHYOLITE_BRICKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cracked_polished_rhyolite_bricks"), new BlockItem(CRACKED_POLISHED_RHYOLITE_BRICKS, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "schist"), SCHIST);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "schist"), new BlockItem(SCHIST, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "schist_stairs"), SCHIST_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "schist_stairs"), new BlockItem(SCHIST_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "schist_slab"), SCHIST_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "schist_slab"), new BlockItem(SCHIST_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "schist_wall"), SCHIST_WALL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "schist_wall"), new BlockItem(SCHIST_WALL, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist"), POLISHED_SCHIST);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist"), new BlockItem(POLISHED_SCHIST, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist_stairs"), POLISHED_SCHIST_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist_stairs"), new BlockItem(POLISHED_SCHIST_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist_slab"), POLISHED_SCHIST_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist_slab"), new BlockItem(POLISHED_SCHIST_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist_bricks"), POLISHED_SCHIST_BRICKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist_bricks"), new BlockItem(POLISHED_SCHIST_BRICKS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist_brick_stairs"), POLISHED_SCHIST_BRICK_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist_brick_stairs"), new BlockItem(POLISHED_SCHIST_BRICK_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist_brick_slab"), POLISHED_SCHIST_BRICK_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist_brick_slab"), new BlockItem(POLISHED_SCHIST_BRICK_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_schist_brick_wall"), POLISHED_SCHIST_BRICK_WALL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_schist_brick_wall"), new BlockItem(POLISHED_SCHIST_BRICK_WALL, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cracked_polished_schist_bricks"), CRACKED_POLISHED_SCHIST_BRICKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cracked_polished_schist_bricks"), new BlockItem(CRACKED_POLISHED_SCHIST_BRICKS, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "bloodstone"), BLOODSTONE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bloodstone"), new BlockItem(BLOODSTONE, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "bloodstone_stairs"), BLOODSTONE_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bloodstone_stairs"), new BlockItem(BLOODSTONE_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "bloodstone_slab"), BLOODSTONE_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bloodstone_slab"), new BlockItem(BLOODSTONE_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "bloodstone_wall"), BLOODSTONE_WALL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bloodstone_wall"), new BlockItem(BLOODSTONE_WALL, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone"), POLISHED_BLOODSTONE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone"), new BlockItem(POLISHED_BLOODSTONE, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone_stairs"), POLISHED_BLOODSTONE_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone_stairs"), new BlockItem(POLISHED_BLOODSTONE_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone_slab"), POLISHED_BLOODSTONE_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone_slab"), new BlockItem(POLISHED_BLOODSTONE_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone_bricks"), POLISHED_BLOODSTONE_BRICKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone_bricks"), new BlockItem(POLISHED_BLOODSTONE_BRICKS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone_brick_stairs"), POLISHED_BLOODSTONE_BRICK_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone_brick_stairs"), new BlockItem(POLISHED_BLOODSTONE_BRICK_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone_brick_slab"), POLISHED_BLOODSTONE_BRICK_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone_brick_slab"), new BlockItem(POLISHED_BLOODSTONE_BRICK_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "polished_bloodstone_brick_wall"), POLISHED_BLOODSTONE_BRICK_WALL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "polished_bloodstone_brick_wall"), new BlockItem(POLISHED_BLOODSTONE_BRICK_WALL, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cracked_polished_bloodstone_bricks"), CRACKED_POLISHED_BLOODSTONE_BRICKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cracked_polished_bloodstone_bricks"), new BlockItem(CRACKED_POLISHED_BLOODSTONE_BRICKS, new FabricItemSettings().group(ITEM_GROUP)));
		//im sorry I know its scuffed ill make the block registry more organized later

		CustomBoatItem.registerBoats();
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_sign"), STRIPPED_BAMBOO_SIGN);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_wall_sign"), STRIPPED_BAMBOO_WALL_SIGN);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_sign"), (Item)(new SignItem(new Item.Settings().maxCount(16).group(ITEM_GROUP), STRIPPED_BAMBOO_SIGN, STRIPPED_BAMBOO_WALL_SIGN)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_mat"), STRIPPED_BAMBOO_MAT);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_mat"),new BlockItem(STRIPPED_BAMBOO_MAT, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_planks"), STRIPPED_BAMBOO_PLANKS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_planks"),new BlockItem(STRIPPED_BAMBOO_PLANKS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_stairs"), STRIPPED_BAMBOO_STAIRS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_stairs"),new BlockItem(STRIPPED_BAMBOO_STAIRS, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_slab"), STRIPPED_BAMBOO_SLAB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_slab"),new BlockItem(STRIPPED_BAMBOO_SLAB, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_fence"), STRIPPED_BAMBOO_FENCE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_fence"),new BlockItem(STRIPPED_BAMBOO_FENCE, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_fence_gate"), STRIPPED_BAMBOO_FENCE_GATE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_fence_gate"),new BlockItem(STRIPPED_BAMBOO_FENCE_GATE, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_door"), STRIPPED_BAMBOO_DOOR);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_door"),new BlockItem(STRIPPED_BAMBOO_DOOR, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_trapdoor"), STRIPPED_BAMBOO_TRAPDOOR);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_trapdoor"),new BlockItem(STRIPPED_BAMBOO_TRAPDOOR, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_button"), STRIPPED_BAMBOO_BUTTON);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_button"),new BlockItem(STRIPPED_BAMBOO_BUTTON, new FabricItemSettings().group(ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stripped_bamboo_pressure_plate"), STRIPPED_BAMBOO_PRESSURE_PLATE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stripped_bamboo_pressure_plate"),new BlockItem(STRIPPED_BAMBOO_PRESSURE_PLATE, new FabricItemSettings().group(ITEM_GROUP)));

		Reflection.initialize(
				TwigsBlocks.class,
				TwigsItems.class
		);

		FlammableBlockRegistry fbrInstance = FlammableBlockRegistry.getDefaultInstance();
		fbrInstance.add(TwigsBlocks.AZALEA_FLOWERS,30, 60);
		fbrInstance.add(TwigsBlocks.TWIG,30, 60);
		fbrInstance.add(TwigsBlocks.BAMBOO_LEAVES,30, 60);
		fbrInstance.add(TwigsBlocks.BAMBOO_THATCH,30, 60);
		fbrInstance.add(TwigsBlocks.BAMBOO_THATCH_SLAB, 30, 60);
		fbrInstance.add(TwigsBlocks.BAMBOO_THATCH_STAIRS, 30, 60);
		fbrInstance.add(TwigsBlocks.OAK_TABLE, 5, 20);
		fbrInstance.add(TwigsBlocks.SPRUCE_TABLE, 5, 20);
		fbrInstance.add(TwigsBlocks.BIRCH_TABLE, 5, 20);
		fbrInstance.add(TwigsBlocks.ACACIA_TABLE, 5, 20);
		fbrInstance.add(TwigsBlocks.JUNGLE_TABLE, 5, 20);
		fbrInstance.add(TwigsBlocks.DARK_OAK_TABLE, 5, 20);
		fbrInstance.add(TwigsBlocks.STRIPPED_BAMBOO_TABLE, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_PLANKS, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_STAIRS, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_SLAB, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_FENCE, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_FENCE_GATE, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_BUTTON, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_TRAPDOOR, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_DOOR, 5, 20);
		fbrInstance.add(STRIPPED_BAMBOO_PRESSURE_PLATE, 5, 20);

		FuelRegistry.INSTANCE.add(STRIPPED_BAMBOO, 50);
		FuelRegistry.INSTANCE.add(STRIPPED_BAMBOO_BOAT, 1200);
		FuelRegistry.INSTANCE.add(STRIPPED_BAMBOO_SIGN, 200);
		FuelRegistry.INSTANCE.add(TwigsBlocks.BUNDLED_BAMBOO, 450);
		FuelRegistry.INSTANCE.add(TwigsBlocks.STRIPPED_BUNDLED_BAMBOO, 450);
		FuelRegistry.INSTANCE.add(STRIPPED_BAMBOO_PLANKS, 200);

		PATCH_TWIG.getKey().ifPresent(key -> {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.DARK_FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, key);
		});

		PATCH_PEBBLE.getKey().ifPresent(key -> {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA, BiomeKeys.PLAINS, BiomeKeys.MEADOW, BiomeKeys.STONY_SHORE, BiomeKeys.TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, key);
		});
	}
}
