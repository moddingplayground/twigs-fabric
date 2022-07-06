package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.toymaker.v0.recipe.RecipeExporter;
import net.moddingplayground.frame.api.toymaker.v0.recipe.RecipeJsonBuilders;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.data.TwigsBlockFamilies;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static net.minecraft.data.family.BlockFamily.Variant.*;
import static net.minecraft.item.Items.*;
import static net.moddingplayground.frame.api.toymaker.v0.recipe.RecipeJsonBuilders.*;
import static net.moddingplayground.twigs.api.item.TwigsItems.*;

public class RecipeProvider extends FabricRecipeProvider {
    private RecipeExporter exporter;

    public RecipeProvider(FabricDataGenerator exporter) {
        super(exporter);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporterFunction) {
        RecipeExporter exporter = this.exporter = RecipeExporter.of(exporterFunction, this);

        // cobblestone from pebbles
        exporter.export(generic2x2(PEBBLE, COBBLESTONE, 1), convertBetween(COBBLESTONE, PEBBLE));
        // rocky dirt from dirt/pebbles
        exporter.export(chequer2x2(DIRT, PEBBLE, ROCKY_DIRT, 2));

        // sticks from twig
        exporter.export(shapeless(TWIG, STICK, 2).group("sticks"), convertBetween(STICK, TWIG));
        // bone meal from sea shell
        exporter.export(shapeless(SEA_SHELL, BONE_MEAL, 2), convertBetween(BONE_MEAL, SEA_SHELL));

        // azalea
        exporter.export(azaleaConversion(AZALEA, FLOWERING_AZALEA), convertBetween(FLOWERING_AZALEA, AZALEA_FLOWERS));
        exporter.export(azaleaConversion(AZALEA_LEAVES, FLOWERING_AZALEA_LEAVES), convertBetween(FLOWERING_AZALEA_LEAVES, AZALEA_FLOWERS));

        // mossy bricks
        exporter.export(shapeless(VINE, BRICKS, MOSSY_BRICKS, 2));
        exporter.export(shapeless(VINE, COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICKS, 2));

        // blackstone bricks
        exporter.export(shapeless(POLISHED_BLACKSTONE_BRICKS, TWISTING_VINES, TWISTING_POLISHED_BLACKSTONE_BRICKS, 1));
        exporter.export(shapeless(POLISHED_BLACKSTONE_BRICKS, WEEPING_VINES, WEEPING_POLISHED_BLACKSTONE_BRICKS, 1));

        // stones
        exporter.export(chequer2x2(RED_SAND, QUARTZ, RHYOLITE, 2));
        exporter.export(chequer2x2(CLAY_BALL, QUARTZ, SCHIST, 2));
        exporter.export(chequer2x2(IRON_NUGGET, QUARTZ, BLOODSTONE, 2));

        // paper lantern
        exporter.export(ringSurrounding(PAPER, TORCH, PAPER_LANTERN, 2).group("paper_lantern"));
        exporter.export(paperLantern(ALLIUM, ALLIUM_PAPER_LANTERN));
        exporter.export(paperLantern(BLUE_ORCHID, BLUE_ORCHID_PAPER_LANTERN));
        exporter.export(paperLantern(CRIMSON_ROOTS, CRIMSON_ROOTS_PAPER_LANTERN));
        exporter.export(paperLantern(DANDELION, DANDELION_PAPER_LANTERN));

        // lamps
        exporter.export(lamp(TORCH, LAMP));
        exporter.export(lamp(SOUL_TORCH, SOUL_LAMP));

        // shroomlamps
        exporter.export(sandwich(CRIMSON_PLANKS, SHROOMLIGHT, CRIMSON_SHROOMLAMP, 3).group("shroomlamp"));
        exporter.export(sandwich(WARPED_PLANKS, SHROOMLIGHT, WARPED_SHROOMLAMP, 3).group("shroomlamp"));

        // bamboo
        exporter.export(generic2x2(BAMBOO_LEAVES, BAMBOO_THATCH, 2));
        exporter.export(generic3x3(BAMBOO, BUNDLED_BAMBOO, 3));
        exporter.export(shapeless(BUNDLED_BAMBOO, BAMBOO, 3));
        exporter.export(stonecutting(BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO));

        exporter.export(stonecutting(BAMBOO, STRIPPED_BAMBOO));

        exporter.export(stonecutting(STRIPPED_BUNDLED_BAMBOO, STRIPPED_BAMBOO, 4), convertBetween(STRIPPED_BAMBOO, STRIPPED_BUNDLED_BAMBOO));
        exporter.export(stonecutting(BUNDLED_BAMBOO, STRIPPED_BAMBOO, 4), convertBetween(STRIPPED_BAMBOO, BUNDLED_BAMBOO));

        exporter.export(planks(STRIPPED_BAMBOO, STRIPPED_BAMBOO_PLANKS));
        exporter.export(generic3x1(STRIPPED_BAMBOO, STRIPPED_BAMBOO_MAT, 2));

        // families
        TwigsBlockFamilies.FAMILIES.forEach(this::generateFamilyRecipes);
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
        private final RecipeExporter exporter = RecipeProvider.this.exporter;

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
            this.exporter.export(processWooden(woodenButton(this.base, block)));
        }

        public void chiseled(Block block) {
            this.exporter.export(chiselCrafting(this.family.getVariant(SLAB), block));
        }

        public void cracked(Block block) {
            this.exporter.export(cracking(this.base, block));
        }

        public void cut(Block block) {
            this.exporter.export(cutCrafting(this.base, block));
        }

        public void door(Block block) {
            this.exporter.export(processWooden(woodenDoor(this.base, block)));
        }

        public void fence(Block block) {
            this.exporter.export(processWooden(woodenFence(this.base, block)));
        }

        public void fenceGate(Block block) {
            this.exporter.export(processWooden(woodenFenceGate(this.base, block)));
        }

        public void sign(Block block) {
            this.exporter.export(RecipeJsonBuilders.sign(this.base, block));
        }

        public void slab(Block block) {
            this.exporter.export(processWooden(woodenSlab(this.base, block)));
            if (stone) this.exporter.export(stonecutting(this.base, block, 2));
        }

        public void stairs(Block block) {
            this.exporter.export(processWooden(woodenStairs(this.base, block)));
            if (stone) this.exporter.export(stonecutting(this.base, block));
        }

        public void pressurePlate(Block block) {
            this.exporter.export(processWooden(woodenPressurePlate(this.base, block)));
        }

        public void polished(Block block) {
            this.exporter.export(condensing(this.base, block));

            if (stone) {
                this.exporter.export(stonecutting(this.base, block));
                this.loopPolished(block);
            }
        }

        public void loopPolished(Block block) {
            Optional.ofNullable(TwigsBlockFamilies.FAMILIES.get(block)).ifPresent(family -> {
                offerMaybe(family, SLAB, slab -> this.exporter.export(stonecutting(this.base, slab, 2), convertBetween(slab, this.base)));
                offerMaybe(family, STAIRS, stairs -> this.exporter.export(stonecutting(this.base, stairs), convertBetween(stairs, this.base)));
                offerMaybe(family, WALL, wall -> this.exporter.export(stonecutting(this.base, wall), convertBetween(wall, this.base)));
                offerMaybe(family, POLISHED, this::loopPolished);
            });
        }

        public void trapdoor(Block block) {
            this.exporter.export(processWooden(woodenTrapdoor(this.base, block)));
        }

        private CraftingRecipeJsonBuilder processWooden(CraftingRecipeJsonBuilder recipe) {
            if (this.stone) recipe.group(null);
            return recipe;
        }

        public void wall(Block block) {
            this.exporter.export(wallCrafting(this.base, block));
            if (stone) this.exporter.export(stonecutting(this.base, block));
        }
    }
}
