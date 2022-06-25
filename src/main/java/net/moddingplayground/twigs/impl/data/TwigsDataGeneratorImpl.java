package net.moddingplayground.twigs.impl.data;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.PaperLanternBlock;
import net.moddingplayground.twigs.api.block.TableBlock;
import net.moddingplayground.twigs.api.item.TwigsItemGroups;
import net.moddingplayground.twigs.api.item.TwigsItems;
import net.moddingplayground.twigs.api.tag.TwigsBiomeTags;
import net.moddingplayground.twigs.api.tag.TwigsBlockTags;
import net.moddingplayground.twigs.api.tag.TwigsEntityTypeTags;
import net.moddingplayground.twigs.api.tag.TwigsItemTags;
import net.moddingplayground.twigs.impl.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.impl.block.wood.WoodBlock;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.minecraft.item.Items.*;
import static net.minecraft.world.biome.BiomeKeys.*;
import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;

public final class TwigsDataGeneratorImpl implements Twigs, DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        gen.addProvider(RecipeProvider::new);

        BlockTagProvider blockTagProvider = gen.addProvider(BlockTagProvider::new);
        gen.addProvider(g -> new ItemTagProvider(g, blockTagProvider));
        gen.addProvider(EntityTypeTagProvider::new);
        gen.addProvider(BiomeTagProvider::new);
    }

    public static <T> boolean contains(Registry<T> registry, Function<T, RegistryEntry<T>> entry, TagKey<T> tag) {
        return !registry.stream().filter(t -> entry.apply(t).isIn(tag)).toList().isEmpty();
    }

    @SuppressWarnings("deprecation")
    public static boolean containsBlock(TagKey<Block> tag) {
        return contains(Registry.BLOCK, Block::getRegistryEntry, tag);
    }

    @SuppressWarnings("deprecation")
    public static boolean containsItem(TagKey<Item> tag) {
        return contains(Registry.ITEM, Item::getRegistryEntry, tag);
    }

    /* Recipes */

    private static class RecipeProvider extends FabricRecipeProvider {
        public final Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> stonecuttingVariantFactories =
            new ImmutableMap.Builder<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>>()
                .put(BlockFamily.Variant.CHISELED, this::variantStonecutting)
                .put(BlockFamily.Variant.CUT, this::variantStonecutting)
                .put(BlockFamily.Variant.SLAB, (output, input) -> this.stonecutting(input, output, 2))
                .put(BlockFamily.Variant.STAIRS, this::variantStonecutting)
                .put(BlockFamily.Variant.POLISHED, this::variantStonecutting)
                .put(BlockFamily.Variant.WALL, this::variantStonecutting)
                .build();

        public RecipeProvider(FabricDataGenerator gen) {
            super(gen);
        }

        @Override
        protected void generateRecipes(Consumer<RecipeJsonProvider> gen) {
            /*TwigsBlockFamilies.FAMILIES.values().stream()
                                       .filter(BlockFamily::shouldGenerateRecipes)
                                       .forEach(f -> {
                                           this.generateFamily(f, gen, RecipeProviderAccessor.getVARIANT_FACTORIES(), false);
                                           this.generateFamily(f, gen, this.stonecuttingVariantFactories, true);
                                       });*/ // TODO

            generic2x2(PEBBLE, COBBLESTONE, 1).offerTo(gen, convertBetween(COBBLESTONE, PEBBLE));
            offerChequer2x2(gen, ROCKY_DIRT, DIRT, PEBBLE, 2);
            offerShapelessRecipe(gen, STICK, TWIG, "sticks", 2);
            offerShapelessRecipe(gen, BONE_MEAL, SEA_SHELL, null, 2);

            /* Azalea */

            offerAzaleaConversion(gen, FLOWERING_AZALEA, AZALEA);
            offerAzaleaConversion(gen, FLOWERING_AZALEA_LEAVES, AZALEA_LEAVES);

            /* Mossy Bricks */

            // offerSingleOutputShapelessRecipe();

            /*
            // mossy bricks
            add(bp(MOSSY_BRICKS) + p(MOSSY_BRICKS), shapeless(VINE, BRICKS, MOSSY_BRICKS, 2));
            add(bp(MOSSY_COBBLESTONE_BRICKS) + p(MOSSY_COBBLESTONE_BRICKS), shapeless(VINE, COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICKS, 2));

            // blackstone bricks
            add(bp(TWISTING_POLISHED_BLACKSTONE_BRICKS) + p(TWISTING_POLISHED_BLACKSTONE_BRICKS), shapeless(TWISTING_VINES, POLISHED_BLACKSTONE_BRICKS, TWISTING_POLISHED_BLACKSTONE_BRICKS, 1));
            add(bp(WEEPING_POLISHED_BLACKSTONE_BRICKS) + p(WEEPING_POLISHED_BLACKSTONE_BRICKS), shapeless(WEEPING_VINES, POLISHED_BLACKSTONE_BRICKS, WEEPING_POLISHED_BLACKSTONE_BRICKS, 1));

            // stones
            add(b(RHYOLITE) + p(RHYOLITE), chequer2x2(RED_SAND, QUARTZ, RHYOLITE, 2));
            add(b(SCHIST) + p(SCHIST), chequer2x2(QUARTZ, CLAY_BALL, SCHIST, 2));
            add(b(BLOODSTONE) + p(BLOODSTONE), chequer2x2(QUARTZ, IRON_NUGGET, BLOODSTONE, 2));

            // paper lanterns
            add(b(PAPER_LANTERN) +  p(PAPER_LANTERN), ringSurrounding(PAPER, TORCH, PAPER_LANTERN, 2));
            paperLantern(ALLIUM, ALLIUM_PAPER_LANTERN);
            paperLantern(BLUE_ORCHID, BLUE_ORCHID_PAPER_LANTERN);
            paperLantern(CRIMSON_ROOTS, CRIMSON_ROOTS_PAPER_LANTERN);
            paperLantern(DANDELION, DANDELION_PAPER_LANTERN);

            // lamps
            lamp(TORCH, LAMP);
            lamp(SOUL_TORCH, SOUL_LAMP);

            // shroomlamps
            add(b("shroomlamp") + p(CRIMSON_SHROOMLAMP), this.sandwich(CRIMSON_PLANKS, SHROOMLIGHT, CRIMSON_SHROOMLAMP, 3));
            add(b("shroomlamp") + p(WARPED_SHROOMLAMP), this.sandwich(WARPED_PLANKS, SHROOMLIGHT, WARPED_SHROOMLAMP, 3));

            // bamboo
            add(b(BAMBOO_THATCH) + p(BAMBOO_THATCH), generic2x2(BAMBOO_LEAVES, BAMBOO_THATCH, 2));
            add(b(BUNDLED_BAMBOO) + p(BUNDLED_BAMBOO), generic3x3(BAMBOO, BUNDLED_BAMBOO, 3));
            add(b(BUNDLED_BAMBOO) + p(BUNDLED_BAMBOO) + "_undo", shapeless(BUNDLED_BAMBOO, BAMBOO, 3));
            add(b(BUNDLED_BAMBOO) + p(STRIPPED_BAMBOO) + "_from_stonecutting", stonecutting(BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO));

            // woods
            add(woodId(STRIPPED_BAMBOO_SET, "from_stonecutting"), stonecutting(BAMBOO, STRIPPED_BAMBOO));
            add(woodId(STRIPPED_BAMBOO_SET,"planks"), planks(STRIPPED_BAMBOO, STRIPPED_BAMBOO_SET.get(PLANKS)));
            add(woodId(STRIPPED_BAMBOO_SET, "mat"), generic3x1(STRIPPED_BAMBOO, STRIPPED_BAMBOO_MAT, 2));

            this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));*/
        }

        private static void offerAzaleaConversion(Consumer<RecipeJsonProvider> gen, Item output, Item input) {
            ShapelessRecipeJsonBuilder.create(output)
                                      .input(input)
                                      .input(AZALEA_FLOWERS, 3)
                                      .criterion("has_azalea_flowers", conditionsFromItem(AZALEA_FLOWERS))
                                      .offerTo(gen, convertBetween(output, AZALEA_FLOWERS));
        }

        private static void offerChequer2x2(Consumer<RecipeJsonProvider> gen, ItemConvertible output, ItemConvertible one, ItemConvertible two, int count) {
            ShapedRecipeJsonBuilder.create(output, count)
                                   .input('#', one)
                                   .input('X', two)
                                   .pattern("#X")
                                   .pattern("X#")
                                   .criterion("has_one", conditionsFromItem(one))
                                   .criterion("has_two", conditionsFromItem(two))
                                   .offerTo(gen);
        }

        private static ShapedRecipeJsonBuilder generic2x2(ItemConvertible from, ItemConvertible to, int count) {
            return ShapedRecipeJsonBuilder.create(to, count)
                                          .input('#', from)
                                          .pattern("##")
                                          .pattern("##")
                                          .criterion("has_ingredient", conditionsFromItem(from));
        }

        private void generateFamily(BlockFamily family, Consumer<RecipeJsonProvider> gen, Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> factories, boolean stonecutting) {
            family.getVariants().forEach((variant, output) -> {
                Optional.ofNullable(factories.get(variant)).ifPresent(func -> {
                    ItemConvertible input = getVariantRecipeInput(family, variant, stonecutting);
                    Optional<String> group = family.getGroup();

                    String blockId = getItemPath(output);
                    String id = group.map(s -> s + "/").orElse("") + blockId + (stonecutting ? "_from_stonecutting" : "");

                    CraftingRecipeJsonBuilder recipe = func.apply(output, input);
                    group.ifPresent(s -> recipe.group(s + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getName())));
                    recipe.criterion(family.getUnlockCriterionName().orElseGet(() -> hasItem(input)), conditionsFromItem(input));
                    recipe.offerTo(gen, id);

                    if (variant == BlockFamily.Variant.CRACKED) offerCrackingRecipe(gen, output, input);
                });
            });
        }

        /*public void wood(WoodSet set, @Nullable TagKey<Item> logs) {
            if (!set.isVanilla()) {
                if (logs != null && containsItem(logs)) set.requireTo(s -> this.add(woodId(set, "planks"), planks(logs, s.get(PLANKS))), PLANKS);
                set.requireTo(s -> this.add(woodId(s, "wood"), wood(s.get(LOG), s.get(WOOD))), LOG, WOOD);
                set.requireTo(s -> this.add(woodId(s, "stripped_wood"), wood(s.get(STRIPPED_LOG), s.get(STRIPPED_WOOD))), STRIPPED_LOG, STRIPPED_WOOD);
                set.requireTo(s -> this.add(woodId(s, "button"), woodenButton(s.get(PLANKS), s.get(BUTTON))), PLANKS, BUTTON);
                set.requireTo(s -> this.add(woodId(s, "door"), woodenDoor(s.get(PLANKS), s.get(DOOR))), PLANKS, DOOR);
                set.requireTo(s -> this.add(woodId(s, "fence"), woodenFence(s.get(PLANKS), s.get(FENCE))), PLANKS, FENCE);
                set.requireTo(s -> this.add(woodId(s, "fence_gate"), woodenFenceGate(s.get(PLANKS), s.get(FENCE_GATE))), PLANKS, FENCE_GATE);
                set.requireTo(s -> this.add(woodId(s, "pressure_plate"), woodenPressurePlate(s.get(PLANKS), s.get(PRESSURE_PLATE))), PLANKS, PRESSURE_PLATE);
                set.requireTo(s -> this.add(woodId(s, "slab"), woodenSlab(s.get(PLANKS), s.get(SLAB))), PLANKS, SLAB);
                set.requireTo(s -> this.add(woodId(s, "stairs"), woodenStairs(s.get(PLANKS), s.get(STAIRS))), PLANKS, STAIRS);
                set.requireTo(s -> this.add(woodId(s, "trapdoor"), woodenTrapdoor(s.get(PLANKS), s.get(TRAPDOOR))), PLANKS, TRAPDOOR);
                set.requireTo(s -> this.add(woodId(s, "sign"), sign(s.get(PLANKS), s.get(SIGN))), PLANKS, SIGN);
                set.requireTo(s -> s.getBoatItem().ifPresent(item -> this.add(woodId(s, "boat"), boat(s.get(PLANKS), item))), PLANKS);
            }
            if (set instanceof TwigsWoodSet twigs) this.table(set.get(SLAB), set.get(FENCE), twigs.getTable());
        }

        public void woods(WoodSet... sets) {
            for (WoodSet set : sets) this.wood(set, null);
        }

        public String woodId(WoodSet set, String id) {
            String setId = set.getId();
            return b(setId) + setId + "_" + id;
        }*/

        public Block getVariantRecipeInput(BlockFamily family, BlockFamily.Variant variant, boolean stonecutting) {
            if (variant == BlockFamily.Variant.CHISELED && !stonecutting) {
                if (!family.getVariants().containsKey(BlockFamily.Variant.SLAB)) throw new IllegalStateException("Slab is not defined for the family.");
                return family.getVariant(BlockFamily.Variant.SLAB);
            }
            return family.getBaseBlock();
        }

        public CookingRecipeJsonBuilder cracking(ItemConvertible output, ItemConvertible input) {
            return CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), output, 0.1f, 200)
                                           .criterion(net.minecraft.data.server.RecipeProvider.hasItem(input), conditionsFromItem(input));
        }

        private SingleItemRecipeJsonBuilder variantStonecutting(ItemConvertible to, ItemConvertible from) {
            return stonecutting(from, to);
        }

        private SingleItemRecipeJsonBuilder stonecutting(ItemConvertible from, ItemConvertible to, int count) {
            return SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(from), to, count)
                                              .criterion("has_item", conditionsFromItem(from));
        }

        private SingleItemRecipeJsonBuilder stonecutting(ItemConvertible from, ItemConvertible to) {
            return stonecutting(from, to, 1);
        }
    }

    /* Tags */

    private static class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public BlockTagProvider(FabricDataGenerator gen) {
            super(gen);
        }

        @Override
        protected void generateTags() {
            this.getOrCreateTagBuilder(BlockTags.WALLS).add(
                MOSSY_BRICK_WALL,
                COBBLESTONE_BRICK_WALL,
                MOSSY_COBBLESTONE_BRICK_WALL,
                SMOOTH_BASALT_BRICK_WALL,
                POLISHED_AMETHYST_BRICK_WALL,
                TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
                WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
                TUFF_WALL,
                POLISHED_TUFF_BRICK_WALL,
                CALCITE_WALL,
                POLISHED_CALCITE_BRICK_WALL,
                RHYOLITE_WALL,
                POLISHED_RHYOLITE_BRICK_WALL,
                SCHIST_WALL,
                POLISHED_SCHIST_BRICK_WALL,
                BLOODSTONE_WALL,
                POLISHED_BLOODSTONE_BRICK_WALL
            );

            this.getOrCreateTagBuilder(BlockTags.STAIRS).add(
                MOSSY_BRICK_STAIRS,
                COBBLESTONE_BRICK_STAIRS,
                MOSSY_COBBLESTONE_BRICK_STAIRS,
                SMOOTH_BASALT_BRICK_STAIRS,
                POLISHED_AMETHYST_BRICK_STAIRS,
                TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                TUFF_STAIRS,
                POLISHED_TUFF_BRICK_STAIRS,
                CALCITE_STAIRS,
                POLISHED_CALCITE_BRICK_STAIRS,
                RHYOLITE_STAIRS,
                POLISHED_RHYOLITE_BRICK_STAIRS,
                SCHIST_STAIRS,
                POLISHED_SCHIST_BRICK_STAIRS,
                BLOODSTONE_STAIRS,
                POLISHED_BLOODSTONE_BRICK_STAIRS,
                BAMBOO_THATCH_STAIRS,
                POLISHED_AMETHYST_STAIRS,
                POLISHED_TUFF_STAIRS,
                POLISHED_CALCITE_STAIRS,
                POLISHED_RHYOLITE_STAIRS,
                POLISHED_SCHIST_STAIRS,
                POLISHED_BLOODSTONE_STAIRS
            );

            this.getOrCreateTagBuilder(BlockTags.SLABS).add(
                MOSSY_BRICK_SLAB,
                COBBLESTONE_BRICK_SLAB,
                MOSSY_COBBLESTONE_BRICK_SLAB,
                SMOOTH_BASALT_BRICK_SLAB,
                POLISHED_AMETHYST_BRICK_SLAB,
                TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
                WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
                TUFF_SLAB,
                POLISHED_TUFF_BRICK_SLAB,
                CALCITE_SLAB,
                POLISHED_CALCITE_BRICK_SLAB,
                RHYOLITE_SLAB,
                POLISHED_RHYOLITE_BRICK_SLAB,
                SCHIST_SLAB,
                POLISHED_SCHIST_BRICK_SLAB,
                BLOODSTONE_SLAB,
                POLISHED_BLOODSTONE_BRICK_SLAB,
                BAMBOO_THATCH_SLAB,
                POLISHED_AMETHYST_SLAB,
                POLISHED_TUFF_SLAB,
                POLISHED_CALCITE_SLAB,
                POLISHED_RHYOLITE_SLAB,
                POLISHED_SCHIST_SLAB,
                POLISHED_BLOODSTONE_SLAB
            );

            this.getOrCreateTagBuilder(BlockTags.DIRT).add(ROCKY_DIRT);
            this.getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON).add(ROCKY_DIRT);
            this.getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ROCKY_DIRT);
            this.getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON).add(ROCKY_DIRT);

            this.getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS).add(SOUL_LAMP);
            this.getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(SCHIST, RHYOLITE);
            this.getOrCreateTagBuilder(BlockTags.REPLACEABLE_PLANTS).add(AZALEA_FLOWERS);
            this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(POTTED_AZALEA_FLOWERS);

            this.getOrCreateTagBuilder(TwigsBlockTags.PAPER_LANTERNS).add(
                PAPER_LANTERN,
                ALLIUM_PAPER_LANTERN,
                BLUE_ORCHID_PAPER_LANTERN,
                CRIMSON_ROOTS_PAPER_LANTERN,
                DANDELION_PAPER_LANTERN
            );

            this.getOrCreateTagBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS).add(
                POLISHED_AMETHYST,
                POLISHED_AMETHYST_STAIRS,
                POLISHED_AMETHYST_SLAB,
                CHISELED_POLISHED_AMETHYST,
                POLISHED_AMETHYST_BRICKS,
                POLISHED_AMETHYST_BRICK_STAIRS,
                POLISHED_AMETHYST_BRICK_SLAB,
                POLISHED_AMETHYST_BRICK_WALL,
                CRACKED_POLISHED_AMETHYST_BRICKS
            );

            this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

            this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
                STRIPPED_BAMBOO,
                STRIPPED_BUNDLED_BAMBOO,
                STRIPPED_BAMBOO_MAT,
                BUNDLED_BAMBOO,
                CRIMSON_SHROOMLAMP,
                WARPED_SHROOMLAMP,
                AZALEA_FLOWERS
            );

            this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(
                CRIMSON_SHROOMLAMP,
                WARPED_SHROOMLAMP
            );

            this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                LAMP,
                SOUL_LAMP,
                ROCKY_DIRT,
                MOSSY_BRICKS,
                POLISHED_BASALT_BRICKS,
                CRACKED_BRICKS,
                CHISELED_BRICKS,
                MOSSY_BRICK_SLAB,
                MOSSY_BRICK_STAIRS,
                MOSSY_BRICK_WALL,
                SMOOTH_BASALT_BRICKS,
                SMOOTH_BASALT_BRICK_SLAB,
                SMOOTH_BASALT_BRICK_STAIRS,
                SMOOTH_BASALT_BRICK_WALL,
                MOSSY_COBBLESTONE_BRICKS,
                MOSSY_COBBLESTONE_BRICK_SLAB,
                MOSSY_COBBLESTONE_BRICK_STAIRS,
                MOSSY_COBBLESTONE_BRICK_WALL,
                COBBLESTONE_BRICKS,
                COBBLESTONE_BRICK_SLAB,
                COBBLESTONE_BRICK_STAIRS,
                COBBLESTONE_BRICK_WALL,
                CRACKED_COBBLESTONE_BRICKS,
                POLISHED_AMETHYST,
                POLISHED_AMETHYST_SLAB,
                POLISHED_AMETHYST_STAIRS,
                POLISHED_AMETHYST_BRICKS,
                POLISHED_AMETHYST_BRICK_SLAB,
                POLISHED_AMETHYST_BRICK_STAIRS,
                POLISHED_AMETHYST_BRICK_WALL,
                CHISELED_POLISHED_AMETHYST,
                CRACKED_POLISHED_AMETHYST_BRICKS,
                WEEPING_POLISHED_BLACKSTONE_BRICKS,
                WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
                WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
                TWISTING_POLISHED_BLACKSTONE_BRICKS,
                TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
                TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
                SCHIST,
                SCHIST_SLAB,
                SCHIST_STAIRS,
                SCHIST_WALL,
                POLISHED_SCHIST,
                POLISHED_SCHIST_SLAB,
                POLISHED_SCHIST_STAIRS,
                CRACKED_POLISHED_SCHIST_BRICKS,
                POLISHED_SCHIST_BRICKS,
                POLISHED_SCHIST_BRICK_SLAB,
                POLISHED_SCHIST_BRICK_STAIRS,
                POLISHED_SCHIST_BRICK_WALL,
                RHYOLITE,
                RHYOLITE_SLAB,
                RHYOLITE_STAIRS,
                RHYOLITE_WALL,
                POLISHED_RHYOLITE,
                POLISHED_RHYOLITE_SLAB,
                POLISHED_RHYOLITE_STAIRS,
                CRACKED_POLISHED_RHYOLITE_BRICKS,
                POLISHED_RHYOLITE_BRICKS,
                POLISHED_RHYOLITE_BRICK_SLAB,
                POLISHED_RHYOLITE_BRICK_STAIRS,
                POLISHED_RHYOLITE_BRICK_WALL,
                BLOODSTONE,
                BLOODSTONE_SLAB,
                BLOODSTONE_STAIRS,
                BLOODSTONE_WALL,
                POLISHED_BLOODSTONE,
                POLISHED_BLOODSTONE_SLAB,
                POLISHED_BLOODSTONE_STAIRS,
                CRACKED_POLISHED_BLOODSTONE_BRICKS,
                POLISHED_BLOODSTONE_BRICKS,
                POLISHED_BLOODSTONE_BRICK_SLAB,
                POLISHED_BLOODSTONE_BRICK_STAIRS,
                POLISHED_BLOODSTONE_BRICK_WALL,
                TUFF_SLAB,
                TUFF_STAIRS,
                TUFF_WALL,
                POLISHED_TUFF,
                POLISHED_TUFF_SLAB,
                POLISHED_TUFF_STAIRS,
                CRACKED_POLISHED_TUFF_BRICKS,
                POLISHED_TUFF_BRICKS,
                POLISHED_TUFF_BRICK_SLAB,
                POLISHED_TUFF_BRICK_STAIRS,
                POLISHED_TUFF_BRICK_WALL,
                CALCITE_SLAB,
                CALCITE_STAIRS,
                CALCITE_WALL,
                POLISHED_CALCITE,
                POLISHED_CALCITE_SLAB,
                POLISHED_CALCITE_STAIRS,
                CRACKED_POLISHED_CALCITE_BRICKS,
                POLISHED_CALCITE_BRICKS,
                POLISHED_CALCITE_BRICK_SLAB,
                POLISHED_CALCITE_BRICK_STAIRS,
                POLISHED_CALCITE_BRICK_WALL,
                COPPER_PILLAR,
                WAXED_COPPER_PILLAR,
                EXPOSED_COPPER_PILLAR,
                WAXED_EXPOSED_COPPER_PILLAR,
                WEATHERED_COPPER_PILLAR,
                WAXED_WEATHERED_COPPER_PILLAR,
                OXIDIZED_COPPER_PILLAR,
                WAXED_OXIDIZED_COPPER_PILLAR
            );
        }

        public void wood(WoodSet set, @Nullable TagKey<Block> logs) {
            if (!set.isVanilla()) {
                this.wood(set, BlockTags.PLANKS, WoodBlock.PLANKS);
                this.wood(set, BlockTags.SAPLINGS, WoodBlock.SAPLING);
                this.wood(set, BlockTags.FLOWER_POTS, WoodBlock.POTTED_SAPLING);
                this.wood(set, logs, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);

                if (set.isFlammable()) {
                    Optional.ofNullable(logs).ifPresent(tag -> { if (containsBlock(logs)) this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tag); });
                } else {
                    Optional.ofNullable(logs).ifPresent(tag -> { if (containsBlock(logs)) this.getOrCreateTagBuilder(BlockTags.LOGS).addTag(tag); });
                    this.wood(set, BlockTags.NON_FLAMMABLE_WOOD, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);
                }

                this.wood(set, BlockTags.LEAVES, WoodBlock.LEAVES);
                this.wood(set, BlockTags.WOODEN_SLABS, WoodBlock.SLAB);
                this.wood(set, BlockTags.WOODEN_PRESSURE_PLATES, WoodBlock.PRESSURE_PLATE);
                this.wood(set, BlockTags.WOODEN_FENCES, WoodBlock.FENCE);
                this.wood(set, BlockTags.WOODEN_TRAPDOORS, WoodBlock.TRAPDOOR);
                this.wood(set, BlockTags.FENCE_GATES, WoodBlock.FENCE_GATE);
                this.wood(set, BlockTags.WOODEN_STAIRS, WoodBlock.STAIRS);
                this.wood(set, BlockTags.WOODEN_BUTTONS, WoodBlock.BUTTON);
                this.wood(set, BlockTags.WOODEN_DOORS, WoodBlock.DOOR);
                this.wood(set, BlockTags.STANDING_SIGNS, WoodBlock.SIGN);
                this.wood(set, BlockTags.WALL_SIGNS, WoodBlock.WALL_SIGN);
            }
            if (set instanceof TwigsWoodSet twigs) this.getOrCreateTagBuilder(TwigsBlockTags.TABLES).add(twigs.getTable());
        }

        public void woods(WoodSet... sets) {
            for (WoodSet set : sets) this.wood(set, null);
        }

        public void wood(WoodSet set, @Nullable TagKey<Block> tag, WoodBlock... woods) {
            for (WoodBlock wood : woods) { if (set.contains(wood)) this.getOrCreateTagBuilder(tag).add(set.get(wood)); }
        }
    }

    private static class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public ItemTagProvider(FabricDataGenerator gen, BlockTagProvider blockTagProvider) {
            super(gen, blockTagProvider);
        }

        @Override
        protected void generateTags() {
            this.getOrCreateTagBuilder(ItemTags.WALLS).add(
                TwigsItems.MOSSY_BRICK_WALL,
                TwigsItems.COBBLESTONE_BRICK_WALL,
                TwigsItems.MOSSY_COBBLESTONE_BRICK_WALL,
                TwigsItems.SMOOTH_BASALT_BRICK_WALL,
                TwigsItems.POLISHED_AMETHYST_BRICK_WALL,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
                TwigsItems.TUFF_WALL,
                TwigsItems.POLISHED_TUFF_BRICK_WALL,
                TwigsItems.CALCITE_WALL,
                TwigsItems.POLISHED_CALCITE_BRICK_WALL,
                TwigsItems.RHYOLITE_WALL,
                TwigsItems.POLISHED_RHYOLITE_BRICK_WALL,
                TwigsItems.SCHIST_WALL,
                TwigsItems.POLISHED_SCHIST_BRICK_WALL,
                TwigsItems.BLOODSTONE_WALL,
                TwigsItems.POLISHED_BLOODSTONE_BRICK_WALL
            );

            this.getOrCreateTagBuilder(ItemTags.STAIRS).add(
                TwigsItems.MOSSY_BRICK_STAIRS,
                TwigsItems.COBBLESTONE_BRICK_STAIRS,
                TwigsItems.MOSSY_COBBLESTONE_BRICK_STAIRS,
                TwigsItems.SMOOTH_BASALT_BRICK_STAIRS,
                TwigsItems.POLISHED_AMETHYST_BRICK_STAIRS,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                TwigsItems.TUFF_STAIRS,
                TwigsItems.POLISHED_TUFF_BRICK_STAIRS,
                TwigsItems.CALCITE_STAIRS,
                TwigsItems.POLISHED_CALCITE_BRICK_STAIRS,
                TwigsItems.RHYOLITE_STAIRS,
                TwigsItems.POLISHED_RHYOLITE_BRICK_STAIRS,
                TwigsItems.SCHIST_STAIRS,
                TwigsItems.POLISHED_SCHIST_BRICK_STAIRS,
                TwigsItems.BLOODSTONE_STAIRS,
                TwigsItems.POLISHED_BLOODSTONE_BRICK_STAIRS,
                TwigsItems.BAMBOO_THATCH_STAIRS,
                TwigsItems.POLISHED_AMETHYST_STAIRS,
                TwigsItems.POLISHED_TUFF_STAIRS,
                TwigsItems.POLISHED_CALCITE_STAIRS,
                TwigsItems.POLISHED_RHYOLITE_STAIRS,
                TwigsItems.POLISHED_SCHIST_STAIRS,
                TwigsItems.POLISHED_BLOODSTONE_STAIRS
            );

            this.getOrCreateTagBuilder(ItemTags.SLABS).add(
                TwigsItems.MOSSY_BRICK_SLAB,
                TwigsItems.COBBLESTONE_BRICK_SLAB,
                TwigsItems.MOSSY_COBBLESTONE_BRICK_SLAB,
                TwigsItems.SMOOTH_BASALT_BRICK_SLAB,
                TwigsItems.POLISHED_AMETHYST_BRICK_SLAB,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
                TwigsItems.TUFF_SLAB,
                TwigsItems.POLISHED_TUFF_BRICK_SLAB,
                TwigsItems.CALCITE_SLAB,
                TwigsItems.POLISHED_CALCITE_BRICK_SLAB,
                TwigsItems.RHYOLITE_SLAB,
                TwigsItems.POLISHED_RHYOLITE_BRICK_SLAB,
                TwigsItems.SCHIST_SLAB,
                TwigsItems.POLISHED_SCHIST_BRICK_SLAB,
                TwigsItems.BLOODSTONE_SLAB,
                TwigsItems.POLISHED_BLOODSTONE_BRICK_SLAB,
                TwigsItems.BAMBOO_THATCH_SLAB,
                TwigsItems.POLISHED_AMETHYST_SLAB,
                TwigsItems.POLISHED_TUFF_SLAB,
                TwigsItems.POLISHED_CALCITE_SLAB,
                TwigsItems.POLISHED_RHYOLITE_SLAB,
                TwigsItems.POLISHED_SCHIST_SLAB,
                TwigsItems.POLISHED_BLOODSTONE_SLAB
            );

            this.getOrCreateTagBuilder(ItemTags.DIRT).add(TwigsItems.ROCKY_DIRT);
            this.getOrCreateTagBuilder(ItemTags.PIGLIN_REPELLENTS).add(TwigsItems.SOUL_LAMP);
            this.getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(TwigsItems.SCHIST, TwigsItems.RHYOLITE);
            this.getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(TwigsItems.SCHIST, TwigsItems.RHYOLITE);

            this.getOrCreateTagBuilder(TwigsItemTags.PAPER_LANTERNS).add(
                TwigsItems.PAPER_LANTERN,
                TwigsItems.ALLIUM_PAPER_LANTERN,
                TwigsItems.BLUE_ORCHID_PAPER_LANTERN,
                TwigsItems.CRIMSON_ROOTS_PAPER_LANTERN,
                TwigsItems.DANDELION_PAPER_LANTERN
            );

            this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

            /* Item Group */

            // woods and plants
            this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("natural")).add(
                TwigsItems.TWIG,
                TwigsItems.PEBBLE,
                TwigsItems.SEA_SHELL,
                TwigsItems.AZALEA_FLOWERS,
                TwigsItems.BAMBOO_LEAVES,
                TwigsItems.BAMBOO_THATCH,
                TwigsItems.BAMBOO_THATCH_SLAB,
                TwigsItems.BAMBOO_THATCH_STAIRS,
                TwigsItems.BUNDLED_BAMBOO,
                TwigsItems.STRIPPED_BUNDLED_BAMBOO,
                TwigsItems.STRIPPED_BAMBOO,
                TwigsItems.STRIPPED_BAMBOO_MAT,
                TwigsItems.ROCKY_DIRT,
                TwigsItems.CRIMSON_SHROOMLAMP,
                TwigsItems.WARPED_SHROOMLAMP
            );

            WOOD_SETS.forEach(set -> set.forEach((element, block) -> this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("natural")).add(block.asItem())));

            // stones
            this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("stones")).add(
                TwigsItems.CALCITE_STAIRS,
                TwigsItems.CALCITE_SLAB,
                TwigsItems.CALCITE_WALL,
                TwigsItems.POLISHED_CALCITE,
                TwigsItems.POLISHED_CALCITE_STAIRS,
                TwigsItems.POLISHED_CALCITE_SLAB,
                TwigsItems.POLISHED_CALCITE_BRICKS,
                TwigsItems.POLISHED_CALCITE_BRICK_STAIRS,
                TwigsItems.POLISHED_CALCITE_BRICK_SLAB,
                TwigsItems.POLISHED_CALCITE_BRICK_WALL,
                TwigsItems.CRACKED_POLISHED_CALCITE_BRICKS,
                TwigsItems.SCHIST,
                TwigsItems.SCHIST_STAIRS,
                TwigsItems.SCHIST_SLAB,
                TwigsItems.SCHIST_WALL,
                TwigsItems.POLISHED_SCHIST,
                TwigsItems.POLISHED_SCHIST_STAIRS,
                TwigsItems.POLISHED_SCHIST_SLAB,
                TwigsItems.POLISHED_SCHIST_BRICKS,
                TwigsItems.POLISHED_SCHIST_BRICK_STAIRS,
                TwigsItems.POLISHED_SCHIST_BRICK_SLAB,
                TwigsItems.POLISHED_SCHIST_BRICK_WALL,
                TwigsItems.CRACKED_POLISHED_SCHIST_BRICKS,
                TwigsItems.COBBLESTONE_BRICKS,
                TwigsItems.COBBLESTONE_BRICK_STAIRS,
                TwigsItems.COBBLESTONE_BRICK_SLAB,
                TwigsItems.COBBLESTONE_BRICK_WALL,
                TwigsItems.CRACKED_COBBLESTONE_BRICKS,
                TwigsItems.MOSSY_COBBLESTONE_BRICKS,
                TwigsItems.MOSSY_COBBLESTONE_BRICK_STAIRS,
                TwigsItems.MOSSY_COBBLESTONE_BRICK_SLAB,
                TwigsItems.MOSSY_COBBLESTONE_BRICK_WALL,
                TwigsItems.TUFF_STAIRS,
                TwigsItems.TUFF_SLAB,
                TwigsItems.TUFF_WALL,
                TwigsItems.POLISHED_TUFF,
                TwigsItems.POLISHED_TUFF_STAIRS,
                TwigsItems.POLISHED_TUFF_SLAB,
                TwigsItems.POLISHED_TUFF_BRICKS,
                TwigsItems.POLISHED_TUFF_BRICK_STAIRS,
                TwigsItems.POLISHED_TUFF_BRICK_SLAB,
                TwigsItems.POLISHED_TUFF_BRICK_WALL,
                TwigsItems.CRACKED_POLISHED_TUFF_BRICKS,
                TwigsItems.POLISHED_BASALT_BRICKS,
                TwigsItems.SMOOTH_BASALT_BRICKS,
                TwigsItems.SMOOTH_BASALT_BRICK_STAIRS,
                TwigsItems.SMOOTH_BASALT_BRICK_SLAB,
                TwigsItems.SMOOTH_BASALT_BRICK_WALL,
                TwigsItems.BLOODSTONE,
                TwigsItems.BLOODSTONE_STAIRS,
                TwigsItems.BLOODSTONE_SLAB,
                TwigsItems.BLOODSTONE_WALL,
                TwigsItems.POLISHED_BLOODSTONE,
                TwigsItems.POLISHED_BLOODSTONE_STAIRS,
                TwigsItems.POLISHED_BLOODSTONE_SLAB,
                TwigsItems.POLISHED_BLOODSTONE_BRICKS,
                TwigsItems.POLISHED_BLOODSTONE_BRICK_STAIRS,
                TwigsItems.POLISHED_BLOODSTONE_BRICK_SLAB,
                TwigsItems.POLISHED_BLOODSTONE_BRICK_WALL,
                TwigsItems.CRACKED_POLISHED_BLOODSTONE_BRICKS,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICKS,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
                TwigsItems.TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICKS,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
                TwigsItems.WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
                TwigsItems.RHYOLITE,
                TwigsItems.RHYOLITE_STAIRS,
                TwigsItems.RHYOLITE_SLAB,
                TwigsItems.RHYOLITE_WALL,
                TwigsItems.POLISHED_RHYOLITE,
                TwigsItems.POLISHED_RHYOLITE_STAIRS,
                TwigsItems.POLISHED_RHYOLITE_SLAB,
                TwigsItems.POLISHED_RHYOLITE_BRICKS,
                TwigsItems.POLISHED_RHYOLITE_BRICK_STAIRS,
                TwigsItems.POLISHED_RHYOLITE_BRICK_SLAB,
                TwigsItems.POLISHED_RHYOLITE_BRICK_WALL,
                TwigsItems.CRACKED_POLISHED_RHYOLITE_BRICKS
            );

            // decoration
            this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("decoration")).add(
                TwigsItems.LAMP, TwigsItems.SOUL_LAMP,
                TwigsItems.CRIMSON_SHROOMLAMP, TwigsItems.WARPED_SHROOMLAMP
            );

            for (Block block : Registry.BLOCK) {
                if (block instanceof PaperLanternBlock || block instanceof TableBlock) {
                    this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("decoration")).add(block.asItem());
                }
            }

            // miscellaneous
            this.getOrCreateTagBuilder(TwigsItemGroups.createTabTag("miscellaneous")).add(
                TwigsItems.CHISELED_BRICKS,
                TwigsItems.CRACKED_BRICKS,
                TwigsItems.MOSSY_BRICKS,
                TwigsItems.MOSSY_BRICK_STAIRS,
                TwigsItems.MOSSY_BRICK_SLAB,
                TwigsItems.MOSSY_BRICK_WALL,
                TwigsItems.COPPER_PILLAR,
                TwigsItems.EXPOSED_COPPER_PILLAR,
                TwigsItems.WEATHERED_COPPER_PILLAR,
                TwigsItems.OXIDIZED_COPPER_PILLAR,
                TwigsItems.WAXED_COPPER_PILLAR,
                TwigsItems.WAXED_EXPOSED_COPPER_PILLAR,
                TwigsItems.WAXED_WEATHERED_COPPER_PILLAR,
                TwigsItems.WAXED_OXIDIZED_COPPER_PILLAR,
                TwigsItems.POLISHED_AMETHYST,
                TwigsItems.POLISHED_AMETHYST_STAIRS,
                TwigsItems.POLISHED_AMETHYST_SLAB,
                TwigsItems.CHISELED_POLISHED_AMETHYST,
                TwigsItems.POLISHED_AMETHYST_BRICKS,
                TwigsItems.POLISHED_AMETHYST_BRICK_STAIRS,
                TwigsItems.POLISHED_AMETHYST_BRICK_SLAB,
                TwigsItems.POLISHED_AMETHYST_BRICK_WALL,
                TwigsItems.CRACKED_POLISHED_AMETHYST_BRICKS
            );
        }

        public void wood(WoodSet set, @Nullable TagKey<Item> logs) {
            if (!set.isVanilla()) {
                this.wood(set, ItemTags.PLANKS, WoodBlock.PLANKS);
                this.wood(set, ItemTags.SAPLINGS, WoodBlock.SAPLING);
                this.wood(set, logs, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);

                if (set.isFlammable()) {
                    Optional.ofNullable(logs).ifPresent(tag -> { if (containsItem(logs)) this.getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(tag); });
                } else {
                    Optional.ofNullable(logs).ifPresent(tag -> { if (containsItem(logs)) this.getOrCreateTagBuilder(ItemTags.LOGS).addTag(tag); });
                    this.wood(set, ItemTags.NON_FLAMMABLE_WOOD, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);
                }

                this.wood(set, ItemTags.LEAVES, WoodBlock.LEAVES);
                this.wood(set, ItemTags.WOODEN_SLABS, WoodBlock.SLAB);
                this.wood(set, ItemTags.WOODEN_PRESSURE_PLATES, WoodBlock.PRESSURE_PLATE);
                this.wood(set, ItemTags.WOODEN_FENCES, WoodBlock.FENCE);
                this.wood(set, ItemTags.WOODEN_TRAPDOORS, WoodBlock.TRAPDOOR);
                this.wood(set, ItemTags.WOODEN_STAIRS, WoodBlock.STAIRS);
                this.wood(set, ItemTags.WOODEN_BUTTONS, WoodBlock.BUTTON);
                this.wood(set, ItemTags.WOODEN_DOORS, WoodBlock.DOOR);
                this.wood(set, ItemTags.SIGNS, WoodBlock.SIGN);
            }
            if (set instanceof TwigsWoodSet twigs) this.getOrCreateTagBuilder(TwigsItemTags.TABLES).add(twigs.getTable().asItem());
        }

        public void woods(WoodSet... sets) {
            for (WoodSet set : sets) this.wood(set, null);
        }

        public void wood(WoodSet set, @Nullable TagKey<Item> tag, WoodBlock... woods) {
            for (WoodBlock wood : woods) { if (set.contains(wood)) this.getOrCreateTagBuilder(tag).add(set.get(wood).asItem()); }
        }
    }

    private static class EntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
        public EntityTypeTagProvider(FabricDataGenerator gen) {
            super(gen);
        }

        @Override
        protected void generateTags() {
            this.getOrCreateTagBuilder(TwigsEntityTypeTags.BAMBOO_LEAVES_SLOW_IMMUNE).add(
                EntityType.PANDA,
                EntityType.BEE,
                EntityType.PARROT,
                EntityType.OCELOT
            );
        }
    }

    private static class BiomeTagProvider extends FabricTagProvider.DynamicRegistryTagProvider<Biome> {
        public BiomeTagProvider(FabricDataGenerator gen) {
            super(gen, Registry.BIOME_KEY);
        }

        @Override
        protected void generateTags() {
            this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_TWIG)
                .forceAddTag(BiomeTags.IS_FOREST)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .add(SWAMP);

            this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_PEBBLE)
                .forceAddTag(BiomeTags.IS_RIVER)
                .forceAddTag(BiomeTags.IS_SAVANNA)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .forceAddTag(BiomeTags.IS_BEACH)
                .add(
                    PLAINS,
                    SUNFLOWER_PLAINS,
                    MEADOW,
                    MUSHROOM_FIELDS,
                    WINDSWEPT_FOREST,
                    WINDSWEPT_HILLS,
                    WINDSWEPT_GRAVELLY_HILLS,
                    STONY_SHORE,
                    SWAMP
                );

            this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_SEA_SHELL).forceAddTag(BiomeTags.IS_BEACH).add(STONY_SHORE);
            this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_BLOODSTONE).forceAddTag(BiomeTags.IS_NETHER);
            this.getOrCreateTagBuilder(TwigsBiomeTags.SPAWNS_SCHIST).forceAddTag(BiomeTags.IS_MOUNTAIN);
            this.getOrCreateTagBuilder(TwigsBiomeTags.DOES_NOT_SPAWN_RHYOLITE);
        }
    }
}
