package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.toymaker.v0.RecipeJsonBuilders;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.data.TwigsBlockFamilies;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static net.minecraft.data.family.BlockFamily.Variant.*;
import static net.minecraft.item.Items.*;
import static net.moddingplayground.frame.api.toymaker.v0.RecipeJsonBuilders.*;
import static net.moddingplayground.twigs.api.item.TwigsItems.*;

public class RecipeProvider extends FabricRecipeProvider {
    private Consumer<RecipeJsonProvider> exporter;

    public RecipeProvider(FabricDataGenerator exporter) {
        super(exporter);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> gen) {
        this.exporter = gen;

        // cobblestone from pebbles
        offer(generic2x2(PEBBLE, COBBLESTONE, 1), convertBetween(COBBLESTONE, PEBBLE));
        // rocky dirt from dirt/pebbles
        offer(chequer2x2(DIRT, PEBBLE, ROCKY_DIRT, 2));

        // sticks from twig
        offer(shapeless(TWIG, STICK, 2).group("sticks"), convertBetween(STICK, TWIG));
        // bone meal from sea shell
        offer(shapeless(SEA_SHELL, BONE_MEAL, 2), convertBetween(BONE_MEAL, SEA_SHELL));

        // azalea
        offer(azaleaConversion(AZALEA, FLOWERING_AZALEA), convertBetween(FLOWERING_AZALEA, AZALEA_FLOWERS));
        offer(azaleaConversion(AZALEA_LEAVES, FLOWERING_AZALEA_LEAVES), convertBetween(FLOWERING_AZALEA_LEAVES, AZALEA_FLOWERS));

        // mossy bricks
        offer(shapeless(VINE, BRICKS, MOSSY_BRICKS, 2));
        offer(shapeless(VINE, COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICKS, 2));

        // blackstone bricks
        offer(shapeless(POLISHED_BLACKSTONE_BRICKS, TWISTING_VINES, TWISTING_POLISHED_BLACKSTONE_BRICKS, 1));
        offer(shapeless(POLISHED_BLACKSTONE_BRICKS, WEEPING_VINES, WEEPING_POLISHED_BLACKSTONE_BRICKS, 1));

        // stones
        offer(chequer2x2(RED_SAND, QUARTZ, RHYOLITE, 2));
        offer(chequer2x2(CLAY_BALL, QUARTZ, SCHIST, 2));
        offer(chequer2x2(IRON_NUGGET, QUARTZ, BLOODSTONE, 2));

        // paper lantern
        offer(ringSurrounding(PAPER, TORCH, PAPER_LANTERN, 2).group("paper_lantern"));
        offer(paperLantern(ALLIUM, ALLIUM_PAPER_LANTERN));
        offer(paperLantern(BLUE_ORCHID, BLUE_ORCHID_PAPER_LANTERN));
        offer(paperLantern(CRIMSON_ROOTS, CRIMSON_ROOTS_PAPER_LANTERN));
        offer(paperLantern(DANDELION, DANDELION_PAPER_LANTERN));

        // lamps
        offer(lamp(TORCH, LAMP));
        offer(lamp(SOUL_TORCH, SOUL_LAMP));

        // shroomlamps
        offer(sandwich(CRIMSON_PLANKS, SHROOMLIGHT, CRIMSON_SHROOMLAMP, 3).group("shroomlamp"));
        offer(sandwich(WARPED_PLANKS, SHROOMLIGHT, WARPED_SHROOMLAMP, 3).group("shroomlamp"));

        // bamboo
        offer(generic2x2(BAMBOO_LEAVES, BAMBOO_THATCH, 2));
        offer(generic3x3(BAMBOO, BUNDLED_BAMBOO, 3));
        offer(shapeless(BUNDLED_BAMBOO, BAMBOO, 3));
        offer(stonecutting(BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO));

        offer(stonecutting(BAMBOO, STRIPPED_BAMBOO));

        offer(stonecutting(STRIPPED_BUNDLED_BAMBOO, STRIPPED_BAMBOO, 4), convertBetween(STRIPPED_BAMBOO, STRIPPED_BUNDLED_BAMBOO));
        offer(stonecutting(BUNDLED_BAMBOO, STRIPPED_BAMBOO, 4), convertBetween(STRIPPED_BAMBOO, BUNDLED_BAMBOO));

        offer(planks(STRIPPED_BAMBOO, STRIPPED_BAMBOO_PLANKS));
        offer(generic3x1(STRIPPED_BAMBOO, STRIPPED_BAMBOO_MAT, 2));

        // families
        TwigsBlockFamilies.FAMILIES.forEach(this::generateFamilyRecipes);
    }

    protected void offer(CraftingRecipeJsonBuilder recipe, String id) {
        recipe.offerTo(this.exporter, new Identifier(Twigs.MOD_ID, recipe instanceof SingleItemRecipeJsonBuilder ? id + "_from_stonecutting" : id));
    }

    protected void offer(CraftingRecipeJsonBuilder recipe) {
        this.offer(recipe, getItemPath(recipe.getOutputItem()));
    }

    public static ShapedRecipeJsonBuilder condensing(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonBuilder.create(to, 4)
                                      .input('S', from)
                                      .pattern("SS")
                                      .pattern("SS")
                                      .criterion(hasItem(from), conditionsFromItem(from));
    }

    public static ShapedRecipeJsonBuilder chiselCrafting(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonBuilder.create(to)
                                      .input('#', from)
                                      .pattern("#")
                                      .pattern("#")
                                      .criterion(hasItem(from), conditionsFromItem(from));
    }

    public static ShapedRecipeJsonBuilder cutCrafting(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonBuilder.create(to, 4)
                                      .input('#', from)
                                      .pattern("##")
                                      .pattern("##")
                                      .criterion(hasItem(from), conditionsFromItem(from));
    }

    public static ShapedRecipeJsonBuilder wallCrafting(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonBuilder.create(to, 6)
                                      .input('#', from)
                                      .pattern("###")
                                      .pattern("###")
                                      .criterion(hasItem(from), conditionsFromItem(from));
    }

    public static CookingRecipeJsonBuilder cracking(ItemConvertible from, ItemConvertible to) {
        return CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(from), to, 0.1F, 200)
                                       .criterion(hasItem(from), conditionsFromItem(from));
    }

    public static ShapelessRecipeJsonBuilder azaleaConversion(Item from, Item to) {
        return ShapelessRecipeJsonBuilder.create(to)
                                         .input(from)
                                         .input(AZALEA_FLOWERS, 3)
                                         .criterion("has_azalea_flowers", conditionsFromItem(AZALEA_FLOWERS));
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

    public FamilyRecipes createFamilyRecipes(Block base, BlockFamily family) {
        Set<BlockFamily> not = Set.of(TwigsBlockFamilies.STRIPPED_BAMBOO, TwigsBlockFamilies.BAMBOO_THATCH);
        return new FamilyRecipes(base, family, !not.contains(family));
    }

    public void generateFamilyRecipes(Block base, BlockFamily family) {
        this.createFamilyRecipes(base, family).generate();
    }

    public class FamilyRecipes {
        private final Block base;
        private final BlockFamily family;
        private final boolean stone;

        public FamilyRecipes(Block base, BlockFamily family, boolean stone) {
            this.base = base;
            this.family = family;
            this.stone = stone;
        }

        public void generate() {
            offerMaybe(BUTTON, this::button);
            offerMaybe(CHISELED, this::chiseled);
            offerMaybe(CRACKED, this::cracked);
            offerMaybe(CUT, this::cut);
            offerMaybe(DOOR, this::door);
            offerMaybe(FENCE, this::fence);
            offerMaybe(FENCE_GATE, this::fenceGate);
            offerMaybe(SIGN, this::sign);
            offerMaybe(SLAB, this::slab);
            offerMaybe(STAIRS, this::stairs);
            offerMaybe(PRESSURE_PLATE, this::pressurePlate);
            offerMaybe(POLISHED, this::polished);
            offerMaybe(TRAPDOOR, this::trapdoor);
            offerMaybe(WALL, this::wall);
        }

        public static void offerMaybe(BlockFamily family, BlockFamily.Variant variant, Consumer<Block> action) {
            Optional.ofNullable(family.getVariant(variant)).ifPresent(block -> {
                Identifier id = Registry.BLOCK.getId(block);
                if (id.getNamespace().equals(Twigs.MOD_ID)) action.accept(block);
            });
        }

        public void offerMaybe(BlockFamily.Variant variant, Consumer<Block> action) {
            offerMaybe(this.family, variant, action);
        }

        public void button(Block block) {
            offer(processWooden(woodenButton(this.base, block)));
        }

        public void chiseled(Block block) {
            offer(chiselCrafting(this.family.getVariant(SLAB), block));
        }

        public void cracked(Block block) {
            offer(cracking(this.base, block));
        }

        public void cut(Block block) {
            offer(cutCrafting(this.base, block));
        }

        public void door(Block block) {
            offer(processWooden(woodenDoor(this.base, block)));
        }

        public void fence(Block block) {
            offer(processWooden(woodenFence(this.base, block)));
        }

        public void fenceGate(Block block) {
            offer(processWooden(woodenFenceGate(this.base, block)));
        }

        public void sign(Block block) {
            offer(RecipeJsonBuilders.sign(this.base, block));
        }

        public void slab(Block block) {
            offer(processWooden(woodenSlab(this.base, block)));
            if (stone) offer(stonecutting(this.base, block, 2));
        }

        public void stairs(Block block) {
            offer(processWooden(woodenStairs(this.base, block)));
            if (stone) offer(stonecutting(this.base, block));
        }

        public void pressurePlate(Block block) {
            offer(processWooden(woodenPressurePlate(this.base, block)));
        }

        public void polished(Block block) {
            offer(condensing(this.base, block));

            if (stone) {
                offer(stonecutting(this.base, block));
                this.loopPolished(block);
            }
        }

        public void loopPolished(Block block) {
            Optional.ofNullable(TwigsBlockFamilies.FAMILIES.get(block)).ifPresent(family -> {
                offerMaybe(family, SLAB, slab -> offer(stonecutting(this.base, slab, 2), convertBetween(slab, this.base)));
                offerMaybe(family, STAIRS, stairs -> offer(stonecutting(this.base, stairs), convertBetween(stairs, this.base)));
                offerMaybe(family, WALL, wall -> offer(stonecutting(this.base, wall), convertBetween(wall, this.base)));
                offerMaybe(family, POLISHED, this::loopPolished);
            });
        }

        public void trapdoor(Block block) {
            offer(processWooden(woodenTrapdoor(this.base, block)));
        }

        private CraftingRecipeJsonBuilder processWooden(CraftingRecipeJsonBuilder recipe) {
            if (this.stone) recipe.group(null);
            return recipe;
        }

        public void wall(Block block) {
            offer(wallCrafting(this.base, block));
            if (stone) offer(stonecutting(this.base, block));
        }
    }
}
