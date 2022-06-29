package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;

import java.util.function.Consumer;

import static net.minecraft.item.Items.*;
import static net.moddingplayground.frame.api.toymaker.v0.RecipeJsonBuilders.*;
import static net.moddingplayground.twigs.api.item.TwigsItems.*;

class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataGenerator gen) {
        super(gen);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> gen) {
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

        // blackstone bricks
        offer(gen, shapeless(POLISHED_BLACKSTONE_BRICKS, TWISTING_VINES, TWISTING_POLISHED_BLACKSTONE_BRICKS, 1));
        offer(gen, shapeless(POLISHED_BLACKSTONE_BRICKS, WEEPING_VINES, WEEPING_POLISHED_BLACKSTONE_BRICKS, 1));

        // stones
        offer(gen, chequer2x2(RED_SAND, QUARTZ, RHYOLITE, 2));
        offer(gen, chequer2x2(CLAY_BALL, QUARTZ, SCHIST, 2));
        offer(gen, chequer2x2(IRON_NUGGET, QUARTZ, BLOODSTONE, 2));

        // paper lantern
        offer(gen, ringSurrounding(PAPER, TORCH, PAPER_LANTERN, 2).group("paper_lantern"));
        offer(gen, paperLantern(ALLIUM, ALLIUM_PAPER_LANTERN));
        offer(gen, paperLantern(BLUE_ORCHID, BLUE_ORCHID_PAPER_LANTERN));
        offer(gen, paperLantern(CRIMSON_ROOTS, CRIMSON_ROOTS_PAPER_LANTERN));
        offer(gen, paperLantern(DANDELION, DANDELION_PAPER_LANTERN));

        // lamps
        offer(gen, lamp(TORCH, LAMP));
        offer(gen, lamp(SOUL_TORCH, SOUL_LAMP));

        // shroomlamps
        offer(gen, sandwich(CRIMSON_PLANKS, SHROOMLIGHT, CRIMSON_SHROOMLAMP, 3).group("shroomlamp"));
        offer(gen, sandwich(WARPED_PLANKS, SHROOMLIGHT, WARPED_SHROOMLAMP, 3).group("shroomlamp"));

        // bamboo
        offer(gen, generic2x2(BAMBOO_LEAVES, BAMBOO_THATCH, 2));
        offer(gen, generic3x3(BAMBOO, BUNDLED_BAMBOO, 3));
        offer(gen, shapeless(BUNDLED_BAMBOO, BAMBOO, 3));
        offer(gen, stonecutting(BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO));

        // woods
        offer(gen, stonecutting(BAMBOO, STRIPPED_BAMBOO));
        offer(gen, planks(STRIPPED_BAMBOO, STRIPPED_BAMBOO_PLANKS));
        offer(gen, generic3x1(STRIPPED_BAMBOO, STRIPPED_BAMBOO_MAT, 2));

        // TODO wood set
    }

    public static void offerAzaleaConversion(Consumer<RecipeJsonProvider> gen, Item output, Item input) {
        ShapelessRecipeJsonBuilder.create(output).input(input).input(AZALEA_FLOWERS, 3).criterion("has_azalea_flowers", conditionsFromItem(AZALEA_FLOWERS)).offerTo(gen, convertBetween(output, AZALEA_FLOWERS));
    }

    public static ShapelessRecipeJsonBuilder paperLantern(Item content, Item lantern) {
        return ShapelessRecipeJsonBuilder.create(lantern)
                                         .group("paper_lantern")
                                         .input(content)
                                         .input(PAPER_LANTERN)
                                         .criterion("has_content", conditionsFromItem(content))
                                         .criterion("has_lantern", conditionsFromItem(PAPER_LANTERN));
    }

    public static ShapedRecipeJsonBuilder lamp(Item torch, Item lamp) {
        return ShapedRecipeJsonBuilder.create(lamp)
                                      .group("lamp")
                                      .pattern("###")
                                      .pattern("NTN")
                                      .pattern("NCN")
                                      .input('#', IRON_INGOT)
                                      .input('N', IRON_NUGGET)
                                      .input('T', torch)
                                      .input('C', ItemTags.COALS)
                                      .criterion("has_iron", conditionsFromItem(IRON_INGOT))
                                      .criterion("has_iron_nugget", conditionsFromItem(IRON_NUGGET))
                                      .criterion("has_torch", conditionsFromItem(torch))
                                      .criterion("has_coal", conditionsFromTag(ItemTags.COALS));
    }
}
