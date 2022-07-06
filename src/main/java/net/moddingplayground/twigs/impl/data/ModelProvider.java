package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.moddingplayground.frame.api.toymaker.v0.model.ModelHelpers;
import net.moddingplayground.frame.api.toymaker.v0.model.uploader.BlockStateModelUploader;
import net.moddingplayground.frame.api.toymaker.v0.model.uploader.ItemModelUploader;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.BambooLeavesBlock;
import net.moddingplayground.twigs.api.data.TwigsBlockFamilies;
import net.moddingplayground.twigs.api.item.TwigsItems;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;

public class ModelProvider extends FabricModelProvider {
    private BlockStateModelUploader blockUploader;
    private BlockStateModelGenerator blockGen;

    public ModelProvider(FabricDataGenerator gen) {
        super(gen);
    }

    /* Blocks */

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        BlockStateModelUploader uploader = this.blockUploader = BlockStateModelUploader.of(gen);
        this.blockGen = gen;

        uploader.registerWallPlantThin(AZALEA_FLOWERS);
        this.blockUploader.registerFlowerPot(TintType.NOT_TINTED, POTTED_AZALEA_FLOWERS);

        this.blockUploader.registerRotatingIntProperty(BambooLeavesBlock.LAYERS,
            BAMBOO_LEAVES
        );

        this.blockUploader.register(TexturedModel.CUBE_COLUMN,
            CHISELED_BRICKS,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP
        );

        this.blockUploader.register(TwigsTexturedModels.TEMPLATE_MAT, STRIPPED_BAMBOO_MAT);
        this.blockUploader.registerAxisRotated(TwigsTexturedModels.TEMPLATE_QUAD_POLE, STRIPPED_BAMBOO);
        this.blockUploader.registerGeneratedItemModel(STRIPPED_BAMBOO);

        this.registerTable(
            OAK_TABLE,
            SPRUCE_TABLE,
            BIRCH_TABLE,
            JUNGLE_TABLE,
            ACACIA_TABLE,
            DARK_OAK_TABLE,
            CRIMSON_TABLE,
            WARPED_TABLE,
            STRIPPED_BAMBOO_TABLE
        );

        this.registerLamp(
            LAMP,
            SOUL_LAMP
        );

        this.blockUploader.registerAxisRotatedColumn(
            RHYOLITE,
            BUNDLED_BAMBOO,
            STRIPPED_BUNDLED_BAMBOO,
            POLISHED_BASALT_BRICKS,

            COPPER_PILLAR,
            EXPOSED_COPPER_PILLAR,
            WEATHERED_COPPER_PILLAR,
            OXIDIZED_COPPER_PILLAR
        );

        gen.registerParented(COPPER_PILLAR, WAXED_COPPER_PILLAR);
        gen.registerParented(EXPOSED_COPPER_PILLAR, WAXED_EXPOSED_COPPER_PILLAR);
        gen.registerParented(WEATHERED_COPPER_PILLAR, WAXED_WEATHERED_COPPER_PILLAR);
        gen.registerParented(OXIDIZED_COPPER_PILLAR, WAXED_OXIDIZED_COPPER_PILLAR);

        // varying floor layers
        this.blockUploader.registerVaryingFloorLayers(2,
            TWIG,
            PEBBLE
        );
        this.blockUploader.registerVaryingFloorLayers(6, SEA_SHELL);

        // paper lanterns
        this.registerPaperLanterns(
            PAPER_LANTERN,
            ALLIUM_PAPER_LANTERN,
            BLUE_ORCHID_PAPER_LANTERN,
            CRIMSON_ROOTS_PAPER_LANTERN,
            DANDELION_PAPER_LANTERN
        );

        // simple cube alls
        List.of(
            BAMBOO_THATCH,
            CRACKED_BRICKS,
            MOSSY_BRICKS,
            SMOOTH_BASALT_BRICKS,
            COBBLESTONE_BRICKS,
            CRACKED_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICKS,
            POLISHED_AMETHYST_BRICKS,
            CRACKED_POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST,
            CHISELED_POLISHED_AMETHYST,
            TWISTING_POLISHED_BLACKSTONE_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICKS,
            ROCKY_DIRT,
            POLISHED_TUFF,
            POLISHED_TUFF_BRICKS,
            CRACKED_POLISHED_TUFF_BRICKS,
            POLISHED_CALCITE,
            POLISHED_CALCITE_BRICKS,
            CRACKED_POLISHED_CALCITE_BRICKS,
            POLISHED_RHYOLITE,
            POLISHED_RHYOLITE_BRICKS,
            CRACKED_POLISHED_RHYOLITE_BRICKS,
            SCHIST,
            POLISHED_SCHIST,
            POLISHED_SCHIST_BRICKS,
            CRACKED_POLISHED_SCHIST_BRICKS,
            BLOODSTONE,
            POLISHED_BLOODSTONE,
            POLISHED_BLOODSTONE_BRICKS,
            CRACKED_POLISHED_BLOODSTONE_BRICKS,
            STRIPPED_BAMBOO_PLANKS
        ).forEach(gen::registerSimpleCubeAll);

        // families
        TwigsBlockFamilies.FAMILIES.forEach((base, family) -> {
            if (!family.shouldGenerateModels()) return;

            this.registerIfPresent(family, BlockFamily.Variant.STAIRS, stairs -> this.blockUploader.registerStairs(TwigsTextureMap::sideTopBottomNoSuffix, stairs, base));
            this.registerIfPresent(family, BlockFamily.Variant.SLAB, slab -> this.blockUploader.registerSlab(TwigsTextureMap::sideTopBottomNoSuffix, slab, base));
            this.registerIfPresent(family, BlockFamily.Variant.WALL, wall -> this.blockUploader.registerWall(TextureMap::wallSideEnd, wall, base));
            this.registerIfPresent(family, BlockFamily.Variant.FENCE, fence -> this.blockUploader.registerFence(TextureMap::texture, fence, base));
            this.registerIfPresent(family, BlockFamily.Variant.FENCE_GATE, fenceGate -> this.blockUploader.registerFenceGate(TextureMap::texture, fenceGate, base));
            this.registerIfPresent(family, BlockFamily.Variant.BUTTON, button -> this.blockUploader.registerButton(TextureMap::texture, button, base));
            this.registerIfPresent(family, BlockFamily.Variant.PRESSURE_PLATE, pressurePlate -> this.blockUploader.registerPressurePlate(TextureMap::texture, pressurePlate, base));
            this.registerIfPresent(family, BlockFamily.Variant.DOOR, gen::registerDoor);
            this.registerIfPresent(family, BlockFamily.Variant.TRAPDOOR, gen::registerOrientableTrapdoor);
            this.registerIfPresent(family, BlockFamily.Variant.SIGN, sign -> this.blockUploader.registerSign(TextureMap::particle, sign, family.getVariant(BlockFamily.Variant.WALL_SIGN), base));
        });

        Util.make(RHYOLITE, base -> {
            this.blockUploader.registerStairs(TwigsTextureMap::stairsColumn, RHYOLITE_STAIRS, base);
            this.blockUploader.registerSlab(TwigsTextureMap::column, RHYOLITE_SLAB, base);
            this.registerWallColumn(TwigsTextureMap::column, RHYOLITE_WALL, base);
        });
    }

    public void registerIfPresent(BlockFamily family, BlockFamily.Variant variant, Consumer<Block> action) {
        this.blockUploader.registerIfPresent(family, variant, ModelHelpers.namespacePredicate(Twigs.MOD_ID), action);
    }

    public void registerTable(Block... blocks) {
        for (Block block : blocks) {
            this.blockUploader.accept(
                VariantsBlockStateSupplier.create(block,
                    BlockStateVariant.create()
                                     .put(VariantSettings.MODEL, this.blockUploader.upload(TwigsTexturedModels.TEMPLATE_TABLE, block))
                ).coordinate(BlockStateModelGenerator.createEastDefaultHorizontalRotationStates())
            );
        }
    }

    public void registerLamp(Block... blocks) {
        for (Block block : blocks) {
            Identifier off = this.blockUploader.upload(TexturedModel.CUBE_BOTTOM_TOP.andThen(TwigsTextureMap.sideOff(block)), block, "_off");
            Identifier regular = this.blockUploader.upload(TexturedModel.CUBE_BOTTOM_TOP, block);
            this.blockUploader.accept(
                VariantsBlockStateSupplier.create(block)
                                          .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, regular, off))
            );
        }
    }

    public void registerPaperLanterns(Block... blocks) {
        for (Block block : blocks) {
            Identifier standing = this.blockUploader.upload(TwigsTexturedModels.TEMPLATE_PAPER_LANTERN, block);
            Identifier hanging = this.blockUploader.upload(TwigsTexturedModels.TEMPLATE_PAPER_LANTERN_HANGING, block);

            this.blockUploader.accept(
                VariantsBlockStateSupplier.create(block)
                                          .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.HANGING, hanging, standing))
            );
            this.blockUploader.registerGeneratedItemModel(block);
        }
    }

    public void registerWallColumn(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier post = this.blockUploader.upload(TwigsModels.TEMPLATE_WALL_COLUMN_POST, block, textureMap);
        Identifier side = this.blockUploader.upload(TwigsModels.TEMPLATE_WALL_COLUMN_SIDE, block, textureMap);
        Identifier sideTall = this.blockUploader.upload(TwigsModels.TEMPLATE_WALL_COLUMN_SIDE_TALL, block, textureMap);
        this.blockUploader.accept(BlockStateModelGenerator.createWallBlockState(block, post, side, sideTall));
        Identifier inventory = this.blockUploader.upload(TwigsModels.TEMPLATE_WALL_COLUMN_INVENTORY, block, textureMap);
        this.blockGen.registerParentedItemModel(block, inventory);
    }

    /* Items */

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        ItemModelUploader uploader = ItemModelUploader.of(gen);
        uploader.register(Models.GENERATED,
            TwigsItems.STRIPPED_BAMBOO_BOAT,
            TwigsItems.STRIPPED_BAMBOO_CHEST_BOAT
        );
    }

    /* Texture Maps / Models */

    public interface TwigsTextureMap {
        static TextureMap paperLantern(Block block) {
            return new TextureMap().put(TextureKey.TOP, TextureMap.getSubId(PAPER_LANTERN, "_top"))
                                   .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side"))
                                   .put(TextureKey.BOTTOM, TextureMap.getSubId(PAPER_LANTERN, "_bottom"));
        }

        static TextureMap sideTopBottomNoSuffix(Block block) {
            Identifier id = TextureMap.getId(block);
            return new TextureMap().put(TextureKey.TOP, id)
                                   .put(TextureKey.SIDE, id)
                                   .put(TextureKey.BOTTOM, id);
        }

        static TextureMap stairsColumn(Block block) {
            Identifier end = TextureMap.getSubId(block, "_top");
            Identifier side = TextureMap.getSubId(block, "_side");
            return new TextureMap().put(TextureKey.TOP, end)
                                   .put(TextureKey.SIDE, side)
                                   .put(TextureKey.BOTTOM, end);
        }

        static TextureMap column(Block block) {
            Identifier end = TextureMap.getSubId(block, "_top");
            Identifier side = TextureMap.getSubId(block, "_side");
            return new TextureMap().put(TextureKey.TOP, end)
                                   .put(TextureKey.SIDE, side)
                                   .put(TextureKey.BOTTOM, end);
        }

        static Consumer<TextureMap> sideOff(Block block) {
            return textureMap -> textureMap.put(TextureKey.SIDE, TextureMap.getSubId(block, "_side_off"));
        }

        static TextureMap textureStalk(Block block) {
            Identifier identifier = TextureMap.getSubId(block, "_stalk");
            return TextureMap.texture(identifier);
        }
    }

    public interface TwigsModels {
        TextureKey[] SIDE_TOP_BOTTOM = new TextureKey[]{ TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM };

        Model TEMPLATE_WALL_COLUMN_INVENTORY = block("template_wall_column_inventory", "_inventory", SIDE_TOP_BOTTOM);
        Model TEMPLATE_WALL_COLUMN_POST = block("template_wall_column_post", "_post", SIDE_TOP_BOTTOM);
        Model TEMPLATE_WALL_COLUMN_SIDE = block("template_wall_column_side", "_side", SIDE_TOP_BOTTOM);
        Model TEMPLATE_WALL_COLUMN_SIDE_TALL = block("template_wall_column_side_tall", "_side_tall", SIDE_TOP_BOTTOM);

        /* Textured */

        Model TEMPLATE_QUAD_POLE = block("template_quad_pole", TextureKey.TEXTURE);
        Model TEMPLATE_MAT = block("template_mat", TextureKey.TEXTURE);
        Model TEMPLATE_TABLE = block("template_table", SIDE_TOP_BOTTOM);
        Model TEMPLATE_PAPER_LANTERN = block("template_paper_lantern", SIDE_TOP_BOTTOM);
        Model TEMPLATE_PAPER_LANTERN_HANGING = block("template_paper_lantern_hanging", "_hanging", SIDE_TOP_BOTTOM);

        static Model make(TextureKey... keys) {
            return new Model(Optional.empty(), Optional.empty(), keys);
        }

        static Model block(String parent, TextureKey... keys) {
            return new Model(Optional.of(new Identifier(Twigs.MOD_ID, "block/" + parent)), Optional.empty(), keys);
        }

        static Model item(String parent, TextureKey... keys) {
            return new Model(Optional.of(new Identifier(Twigs.MOD_ID, "item/" + parent)), Optional.empty(), keys);
        }

        static Model block(String parent, String variant, TextureKey... keys) {
            return new Model(Optional.of(new Identifier(Twigs.MOD_ID, "block/" + parent)), Optional.of(variant), keys);
        }
    }

    public interface TwigsTexturedModels {
        TexturedModel.Factory TEMPLATE_QUAD_POLE = TexturedModel.makeFactory(TwigsTextureMap::textureStalk, TwigsModels.TEMPLATE_QUAD_POLE);
        TexturedModel.Factory TEMPLATE_MAT = TexturedModel.makeFactory(TextureMap::texture, TwigsModels.TEMPLATE_MAT);
        TexturedModel.Factory TEMPLATE_TABLE = TexturedModel.makeFactory(TextureMap::sideTopBottom, TwigsModels.TEMPLATE_TABLE);
        TexturedModel.Factory TEMPLATE_PAPER_LANTERN = TexturedModel.makeFactory(TwigsTextureMap::paperLantern, TwigsModels.TEMPLATE_PAPER_LANTERN);
        TexturedModel.Factory TEMPLATE_PAPER_LANTERN_HANGING = TexturedModel.makeFactory(TwigsTextureMap::paperLantern, TwigsModels.TEMPLATE_PAPER_LANTERN_HANGING);
    }
}
