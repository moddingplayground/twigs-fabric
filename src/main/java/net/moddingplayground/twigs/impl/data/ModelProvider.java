package net.moddingplayground.twigs.impl.data;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.BambooLeavesBlock;
import net.moddingplayground.twigs.api.data.TwigsBlockFamilies;
import net.moddingplayground.twigs.api.item.TwigsItems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.minecraft.data.client.VariantSettings.*;
import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;

public class ModelProvider extends FabricModelProvider {
    private BlockStateModelGenerator blockGen;
    private ItemModelGenerator itemGen;

    public ModelProvider(FabricDataGenerator gen) {
        super(gen);
    }

    /* Blocks */

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        this.blockGen = gen;

        this.registerWallPlantThin(AZALEA_FLOWERS);
        this.registerFlowerPot(POTTED_AZALEA_FLOWERS);

        this.registerRotatingIntProperty(BambooLeavesBlock.LAYERS,
            BAMBOO_LEAVES
        );

        this.register(TexturedModel.CUBE_COLUMN,
            CHISELED_BRICKS,
            CRIMSON_SHROOMLAMP,
            WARPED_SHROOMLAMP
        );

        this.register(TwigsTexturedModels.TEMPLATE_MAT, STRIPPED_BAMBOO_MAT);
        this.registerAxisRotated(TwigsTexturedModels.TEMPLATE_QUAD_POLE, STRIPPED_BAMBOO);
        this.registerGeneratedItemModel(STRIPPED_BAMBOO);

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

        this.registerAxisRotatedColumn(
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
        this.registerVaryingFloorLayers(2,
            TWIG,
            PEBBLE
        );
        this.registerVaryingFloorLayers(6, SEA_SHELL);

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

            this.registerIfPresent(family, BlockFamily.Variant.STAIRS, stairs -> this.registerStairs(TwigsTextureMap::sideTopBottomNoSuffix, stairs, base));
            this.registerIfPresent(family, BlockFamily.Variant.SLAB, slab -> this.registerSlab(TwigsTextureMap::sideTopBottomNoSuffix, slab, base));
            this.registerIfPresent(family, BlockFamily.Variant.WALL, wall -> this.registerWall(TextureMap::wallSideEnd, wall, base));
            this.registerIfPresent(family, BlockFamily.Variant.FENCE, fence -> this.registerFence(TextureMap::texture, fence, base));
            this.registerIfPresent(family, BlockFamily.Variant.FENCE_GATE, fenceGate -> this.registerFenceGate(TextureMap::texture, fenceGate, base));
            this.registerIfPresent(family, BlockFamily.Variant.BUTTON, button -> this.registerButton(TextureMap::texture, button, base));
            this.registerIfPresent(family, BlockFamily.Variant.PRESSURE_PLATE, pressurePlate -> this.registerPressurePlate(TextureMap::texture, pressurePlate, base));
            this.registerIfPresent(family, BlockFamily.Variant.DOOR, gen::registerDoor);
            this.registerIfPresent(family, BlockFamily.Variant.TRAPDOOR, gen::registerOrientableTrapdoor);
            this.registerIfPresent(family, BlockFamily.Variant.SIGN, sign -> this.registerSign(TextureMap::particle, sign, family.getVariant(BlockFamily.Variant.WALL_SIGN), base));
        });

        Util.make(RHYOLITE, base -> {
            this.registerStairs(TwigsTextureMap::stairsColumn, RHYOLITE_STAIRS, base);
            this.registerSlab(TwigsTextureMap::column, RHYOLITE_SLAB, base);
            this.registerWallColumn(TwigsTextureMap::column, RHYOLITE_WALL, base);
        });
    }

    public void registerTable(Block... blocks) {
        for (Block block : blocks) {
            this.accept(
                VariantsBlockStateSupplier.create(block,
                    BlockStateVariant.create()
                                     .put(VariantSettings.MODEL, this.upload(TwigsTexturedModels.TEMPLATE_TABLE, block))
                ).coordinate(BlockStateModelGenerator.createEastDefaultHorizontalRotationStates())
            );
        }
    }

    public void registerLamp(Block... blocks) {
        for (Block block : blocks) {
            Identifier off = this.upload(TexturedModel.CUBE_BOTTOM_TOP.andThen(TwigsTextureMap.sideOff(block)), block, "_off");
            Identifier regular = this.upload(TexturedModel.CUBE_BOTTOM_TOP, block);
            this.accept(
                VariantsBlockStateSupplier.create(block)
                                          .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, regular, off))
            );
        }
    }

    public void registerIfPresent(BlockFamily family, BlockFamily.Variant variant, Consumer<Block> action) {
        Optional.ofNullable(family.getVariant(variant)).ifPresent(block -> {
            Identifier id = Registry.BLOCK.getId(block);
            if (id.getNamespace().equals(Twigs.MOD_ID)) action.accept(block);
        });
    }

    public void registerAxisRotated(TexturedModel.Factory model, Block... blocks) {
        for (Block block : blocks) this.blockGen.registerAxisRotated(block, model);
    }

    public void registerAxisRotatedColumn(Block... blocks) {
        for (Block block : blocks) {
            Identifier model = this.upload(TexturedModel.CUBE_COLUMN, block);
            this.accept(BlockStateModelGenerator.createAxisRotatedBlockState(block, model));
        }
    }

    public void registerRotatingIntProperty(IntProperty property, Block... blocks) {
        for (Block block : blocks) {
            this.accept(
                VariantsBlockStateSupplier.create(block)
                                          .coordinate(
                                              BlockStateVariantMap.create(property)
                                                                  .registerVariants(layer -> {
                                                                      layer -= 1;
                                                                      String suffix = layer == 0 ? "" : "" + layer;
                                                                      return this.rotateAll(ModelIds.getBlockSubModelId(block, suffix));
                                                                  })
                                          )
            );
            this.registerGeneratedItemModel(block);
        }
    }

    public void registerVaryingFloorLayers(int variants, Block... blocks) {
        for (Block block : blocks) {
            this.accept(this.createVariants(block, Util.make(new ArrayList<>(), list -> {
                for (int i = 0; i <= variants; i++) {
                    String suffix = i == 0 ? "" : "" + i;
                    TextureMap textureMap = TextureMap.texture(TextureMap.getSubId(block, suffix));
                    Identifier model = this.upload(TwigsModels.TEMPLATE_FLOOR_LAYER, block, suffix, textureMap);
                    this.rotateAll(list, model);
                }
            })));
            this.registerGeneratedItemModel(block);
        }
    }

    public void registerPaperLanterns(Block... blocks) {
        for (Block block : blocks) {
            Identifier standing = this.upload(TwigsTexturedModels.TEMPLATE_PAPER_LANTERN, block);
            Identifier hanging = this.upload(TwigsTexturedModels.TEMPLATE_PAPER_LANTERN_HANGING, block);

            this.accept(
                VariantsBlockStateSupplier.create(block)
                                          .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.HANGING, hanging, standing))
            );
            this.registerGeneratedItemModel(block);
        }
    }

    public void registerFlowerPot(Block plant, Block flowerPot, TintType tintType) {
        TextureMap textureMap = TextureMap.plant(plant);
        Identifier model = this.upload(tintType.getFlowerPotCrossModel(), flowerPot, textureMap);
        this.accept(BlockStateModelGenerator.createSingletonBlockState(flowerPot, model));
    }

    public void registerFlowerPot(Block plant, Block flowerPot) {
        this.registerFlowerPot(plant, flowerPot, TintType.NOT_TINTED);
    }

    public void registerFlowerPot(TintType tintType, Block... blocks) {
        for (Block block : blocks) this.registerFlowerPot(block, block, tintType);
    }

    public void registerFlowerPot(Block... blocks) {
        this.registerFlowerPot(TintType.NOT_TINTED, blocks);
    }

    public void registerWallPlantThin(Block... blocks) {
        for (Block block : blocks) {
            Identifier model = this.upload(TwigsTexturedModels.TEMPLATE_WALL_PLANT_THIN, block);
            MultipartBlockStateSupplier multipart = MultipartBlockStateSupplier.create(block);
            BlockState state = block.getDefaultState();
            When.PropertyCondition when = Util.make(When.create(),
                condition -> CONNECTION_VARIANT_FUNCTIONS.stream()
                                                         .map(Pair::getFirst)
                                                         .forEach(property -> { if (state.contains(property)) condition.set(property, false);})
            );
            for (Pair<BooleanProperty, Function<Identifier, BlockStateVariant>> pair : CONNECTION_VARIANT_FUNCTIONS) {
                BooleanProperty property = pair.getFirst();
                Function<Identifier, BlockStateVariant> function = pair.getSecond();
                if (!state.contains(property)) continue;
                multipart.with(When.create().set(property, true), function.apply(model));
                multipart.with(when, function.apply(model));
            }

            this.accept(multipart);
            this.registerGeneratedItemModel(block);
        }
    }

    public void registerStairs(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier inner = this.upload(Models.INNER_STAIRS, block, textureMap);
        Identifier regular = this.upload(Models.STAIRS, block, textureMap);
        Identifier outer = this.upload(Models.OUTER_STAIRS, block, textureMap);

        this.accept(BlockStateModelGenerator.createStairsBlockState(block, inner, regular, outer));
        this.blockGen.registerParentedItemModel(block, regular);
    }

    public void registerSlab(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier bottom = this.upload(Models.SLAB, block, textureMap);
        Identifier top = this.upload(Models.SLAB_TOP, block, textureMap);
        this.accept(BlockStateModelGenerator.createSlabBlockState(block, bottom, top, ModelIds.getBlockModelId(base)));
        this.blockGen.registerParentedItemModel(block, bottom);
    }

    public void registerWall(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier post = this.upload(Models.TEMPLATE_WALL_POST, block, textureMap);
        Identifier side = this.upload(Models.TEMPLATE_WALL_SIDE, block, textureMap);
        Identifier sideTall = this.upload(Models.TEMPLATE_WALL_SIDE_TALL, block, textureMap);
        this.accept(BlockStateModelGenerator.createWallBlockState(block, post, side, sideTall));
        Identifier inventory = this.upload(Models.WALL_INVENTORY, block, textureMap);
        this.blockGen.registerParentedItemModel(block, inventory);
    }

    public void registerWallColumn(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier post = this.upload(TwigsModels.TEMPLATE_WALL_COLUMN_POST, block, textureMap);
        Identifier side = this.upload(TwigsModels.TEMPLATE_WALL_COLUMN_SIDE, block, textureMap);
        Identifier sideTall = this.upload(TwigsModels.TEMPLATE_WALL_COLUMN_SIDE_TALL, block, textureMap);
        this.accept(BlockStateModelGenerator.createWallBlockState(block, post, side, sideTall));
        Identifier inventory = this.upload(TwigsModels.TEMPLATE_WALL_COLUMN_INVENTORY, block, textureMap);
        this.blockGen.registerParentedItemModel(block, inventory);
    }

    public void registerFence(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier post = this.upload(Models.FENCE_POST, block, textureMap);
        Identifier side = this.upload(Models.FENCE_SIDE, block, textureMap);
        this.accept(BlockStateModelGenerator.createFenceBlockState(block, post, side));
        Identifier inventory = this.upload(Models.FENCE_INVENTORY, block, textureMap);
        this.blockGen.registerParentedItemModel(block, inventory);
    }

    public void registerFenceGate(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier open = this.upload(Models.TEMPLATE_FENCE_GATE_OPEN, block, textureMap);
        Identifier closed = this.upload(Models.TEMPLATE_FENCE_GATE, block, textureMap);
        Identifier openWall = this.upload(Models.TEMPLATE_FENCE_GATE_WALL_OPEN, block, textureMap);
        Identifier closedWall = this.upload(Models.TEMPLATE_FENCE_GATE_WALL, block, textureMap);
        this.accept(BlockStateModelGenerator.createFenceGateBlockState(block, open, closed, openWall, closedWall));
    }

    public void registerButton(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier regular = this.upload(Models.BUTTON, block, textureMap);
        Identifier pressed = this.upload(Models.BUTTON_PRESSED, block, textureMap);
        this.accept(BlockStateModelGenerator.createButtonBlockState(block, regular, pressed));
        Identifier inventory = this.upload(Models.BUTTON_INVENTORY, block, textureMap);
        this.blockGen.registerParentedItemModel(block, inventory);
    }

    public void registerPressurePlate(Function<Block, TextureMap> textureMapFunction, Block block, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier up = this.upload(Models.PRESSURE_PLATE_UP, block, textureMap);
        Identifier down = this.upload(Models.PRESSURE_PLATE_DOWN, block, textureMap);
        this.accept(BlockStateModelGenerator.createPressurePlateBlockState(block, up, down));
    }

    public void registerSign(Function<Block, TextureMap> textureMapFunction, Block block, Block wall, Block base) {
        TextureMap textureMap = textureMapFunction.apply(base);
        Identifier model = this.upload(Models.PARTICLE, block, textureMap);
        this.blockGen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, model));
        this.blockGen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wall, model));
        this.registerGeneratedItemModel(block);
        this.blockGen.excludeFromSimpleItemModelGeneration(wall);
    }

    // factories

    public BlockStateVariant createVariant(Identifier model) {
        return BlockStateVariant.create().put(MODEL, model);
    }

    public VariantsBlockStateSupplier createVariants(Block block, List<BlockStateVariant> variants) {
        return VariantsBlockStateSupplier.create(block, variants.toArray(BlockStateVariant[]::new));
    }

    public void registerGeneratedItemModel(Block... blocks) {
        for (Block block : blocks) this.blockGen.registerItemModel(block.asItem());
    }

    public void accept(BlockStateSupplier state) {
        this.blockGen.blockStateCollector.accept(state);
    }

    public Identifier upload(Model model, Block block, TextureMap textureMap) {
        return model.upload(block, textureMap, this.blockGen.modelCollector);
    }

    public Identifier upload(Model model, Block block, String suffix, TextureMap textureMap) {
        return model.upload(block, suffix, textureMap, this.blockGen.modelCollector);
    }

    public Identifier upload(TexturedModel.Factory model, Block block) {
        return model.upload(block, this.blockGen.modelCollector);
    }

    public Identifier upload(TexturedModel.Factory model, Block block, String suffix) {
        return model.upload(block, suffix, this.blockGen.modelCollector);
    }

    public void rotateAll(List<BlockStateVariant> list, Identifier model) {
        Collections.addAll(list,
            this.createVariant(model),
            this.createVariant(model).put(Y, Rotation.R90),
            this.createVariant(model).put(Y, Rotation.R180),
            this.createVariant(model).put(Y, Rotation.R270)
        );
    }

    public List<BlockStateVariant> rotateAll(Identifier model) {
        List<BlockStateVariant> list = new ArrayList<>();
        this.rotateAll(list, model);
        return list;
    }

    /* Items */

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        this.itemGen = gen;

        this.register(Models.GENERATED,
            TwigsItems.STRIPPED_BAMBOO_BOAT,
            TwigsItems.STRIPPED_BAMBOO_CHEST_BOAT
        );
    }

    public void register(Model model, Item... items) {
        for (Item item : items) this.itemGen.register(item, model);
    }

    public void register(TexturedModel.Factory model, Block... blocks) {
        for (Block block : blocks) this.blockGen.registerSingleton(block, model);
    }

    public void register(Model model, TextureMap textureMap, Block... blocks) {
        for (Block block : blocks) this.blockGen.registerSingleton(block, textureMap, model);
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

        Model TEMPLATE_FLOOR_LAYER = block("template_floor_layer", TextureKey.TEXTURE);

        Model TEMPLATE_WALL_COLUMN_INVENTORY = block("template_wall_column_inventory", "_inventory", SIDE_TOP_BOTTOM);
        Model TEMPLATE_WALL_COLUMN_POST = block("template_wall_column_post", "_post", SIDE_TOP_BOTTOM);
        Model TEMPLATE_WALL_COLUMN_SIDE = block("template_wall_column_side", "_side", SIDE_TOP_BOTTOM);
        Model TEMPLATE_WALL_COLUMN_SIDE_TALL = block("template_wall_column_side_tall", "_side_tall", SIDE_TOP_BOTTOM);

        /* Textured */

        Model TEMPLATE_WALL_PLANT_THIN = block("template_wall_plant_thin", TextureKey.TEXTURE);
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
        TexturedModel.Factory TEMPLATE_WALL_PLANT_THIN = TexturedModel.makeFactory(TextureMap::texture, TwigsModels.TEMPLATE_WALL_PLANT_THIN);
        TexturedModel.Factory TEMPLATE_QUAD_POLE = TexturedModel.makeFactory(TwigsTextureMap::textureStalk, TwigsModels.TEMPLATE_QUAD_POLE);
        TexturedModel.Factory TEMPLATE_MAT = TexturedModel.makeFactory(TextureMap::texture, TwigsModels.TEMPLATE_MAT);
        TexturedModel.Factory TEMPLATE_TABLE = TexturedModel.makeFactory(TextureMap::sideTopBottom, TwigsModels.TEMPLATE_TABLE);
        TexturedModel.Factory TEMPLATE_PAPER_LANTERN = TexturedModel.makeFactory(TwigsTextureMap::paperLantern, TwigsModels.TEMPLATE_PAPER_LANTERN);
        TexturedModel.Factory TEMPLATE_PAPER_LANTERN_HANGING = TexturedModel.makeFactory(TwigsTextureMap::paperLantern, TwigsModels.TEMPLATE_PAPER_LANTERN_HANGING);
    }
}
