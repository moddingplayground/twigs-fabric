package net.moddingplayground.twigs.impl.data;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.moddingplayground.frame.api.toymaker.v0.RecipeJsonBuilders;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static net.minecraft.item.Items.*;
import static net.moddingplayground.frame.api.toymaker.v0.RecipeJsonBuilders.*;
import static net.moddingplayground.twigs.api.item.TwigsItems.*;

class RecipeProvider extends FabricRecipeProvider {
    public final Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> stonecuttingVariantFactories =
        new ImmutableMap.Builder<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>>()
            .put(BlockFamily.Variant.CHISELED, RecipeJsonBuilders::stonecutting)
            .put(BlockFamily.Variant.CUT, RecipeJsonBuilders::stonecutting)
            .put(BlockFamily.Variant.SLAB, (output, input) -> stonecutting(input, output, 2))
            .put(BlockFamily.Variant.STAIRS, RecipeJsonBuilders::stonecutting)
            .put(BlockFamily.Variant.POLISHED, RecipeJsonBuilders::stonecutting)
            .put(BlockFamily.Variant.WALL, RecipeJsonBuilders::stonecutting)
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

        // cobblestone from pebbles
        generic2x2(PEBBLE, COBBLESTONE, 1).offerTo(gen, convertBetween(COBBLESTONE, PEBBLE));
        // rocky dirt from dirt/pebbles
        offer(gen, chequer2x2(DIRT, PEBBLE, ROCKY_DIRT, 2));

        // sticks from twig
        offerShapelessRecipe(gen, STICK, TWIG, "sticks", 2);
        // bone meal from sea shell
        offerShapelessRecipe(gen, BONE_MEAL, SEA_SHELL, null, 2);

        // azalea
        offerAzaleaConversion(gen, FLOWERING_AZALEA, AZALEA);
        offerAzaleaConversion(gen, FLOWERING_AZALEA_LEAVES, AZALEA_LEAVES);

        // mossy bricks
        offer(gen, shapeless(VINE, BRICKS, MOSSY_BRICKS, 2));
        offer(gen, shapeless(VINE, COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICKS, 2));

        /*
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
        ShapelessRecipeJsonBuilder.create(output).input(input).input(AZALEA_FLOWERS, 3).criterion("has_azalea_flowers", conditionsFromItem(AZALEA_FLOWERS)).offerTo(gen, convertBetween(output, AZALEA_FLOWERS));
    }
}
