package net.moddingplayground.twigs.impl.data;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.toymaker.v0.generator.recipe.AbstractRecipeGenerator;
import net.moddingplayground.frame.mixin.toymaker.RecipeProviderAccessor;
import net.moddingplayground.twigs.api.data.TwigsBlockFamilies;
import net.moddingplayground.twigs.impl.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import static net.minecraft.item.Items.*;
import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;
import static net.moddingplayground.twigs.impl.block.wood.WoodBlock.*;

public class RecipeGenerator extends AbstractRecipeGenerator {
    public final Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> stonecuttingVariantFactories =
        new ImmutableMap.Builder<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>>()
                    .put(BlockFamily.Variant.CHISELED, this::variantStonecutting)
                    .put(BlockFamily.Variant.CUT, this::variantStonecutting)
                    .put(BlockFamily.Variant.SLAB, (output, input) -> this.stonecutting(input, output, 2))
                    .put(BlockFamily.Variant.STAIRS, this::variantStonecutting)
                    .put(BlockFamily.Variant.POLISHED, this::variantStonecutting)
                    .put(BlockFamily.Variant.WALL, this::variantStonecutting)
                    .build();

    public RecipeGenerator(String modId) {
        super(modId);
    }

    @Override
    public void generate() {
        TwigsBlockFamilies.FAMILIES.values().stream()
                     .filter(BlockFamily::shouldGenerateRecipes)
                     .forEach(f -> {
                         this.family(f, RecipeProviderAccessor.getVARIANT_FACTORIES(), false);
                         this.family(f, this.stonecuttingVariantFactories, true);
                     });

        // miscellaneous
        add(b("misc") + p(ROCKY_DIRT), chequer2x2(DIRT, PEBBLE, ROCKY_DIRT, 2));
        add(b("misc") + p(TWIG), shapeless(TWIG, STICK, 2).group("sticks"));
        add(b("misc") + p(COBBLESTONE) + "_from_" + p(PEBBLE), generic2x2(PEBBLE, COBBLESTONE, 1));
        add(b("misc") + p(BONE_MEAL) + "_from_" + p(SEA_SHELL), shapeless(SEA_SHELL, BONE_MEAL, 2));

        // azalea
        add(b(FLOWERING_AZALEA) + p(FLOWERING_AZALEA), shapeless(AZALEA_FLOWERS, AZALEA_FLOWERS, AZALEA_FLOWERS, AZALEA, FLOWERING_AZALEA, 1));
        add(b(FLOWERING_AZALEA_LEAVES) +  p(FLOWERING_AZALEA_LEAVES), shapeless(AZALEA_FLOWERS, AZALEA_FLOWERS, AZALEA_FLOWERS, AZALEA_LEAVES, FLOWERING_AZALEA_LEAVES, 1));

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

        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));
    }

    public void family(BlockFamily family, Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> factories, boolean stonecutting) {
        family.getVariants().forEach((variant, output) -> {
            BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder> func = factories.get(variant);
            Block input = this.getVariantRecipeInput(family, variant, stonecutting);

            Optional<String> group = family.getGroup();
            String blockId = p(output);
            String id = group.map(s -> s + "/").orElse("") + blockId + (stonecutting ? "_from_stonecutting" : "");

            if (func != null) {
                CraftingRecipeJsonBuilder factory = func.apply(output, input);
                group.ifPresent(g -> factory.group(g + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getName())));
                factory.criterion(family.getUnlockCriterionName().orElseGet(() -> RecipeProvider.hasItem(input)), RecipeProvider.conditionsFromItem(input));

                this.add(id, factory);
            }

            if (!stonecutting && variant == BlockFamily.Variant.CRACKED) this.add(id, cracking(output, input));
        });
    }

    public CookingRecipeJsonBuilder cracking(ItemConvertible output, ItemConvertible input) {
        return CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), output, 0.1f, 200)
                                       .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input));
    }

    private void lamp(Item torch, Block lamp) {
        this.add(
            b("lamp") + p(lamp),
            ShapedRecipeJsonBuilder.create(lamp)
                                   .group("lamp")
                                   .pattern("###")
                                   .pattern("NTN")
                                   .pattern("NCN")
                                   .input('#', IRON_INGOT)
                                   .input('N', IRON_NUGGET)
                                   .input('T', torch)
                                   .input('C', ItemTags.COALS)
                                   .criterion("has_iron", hasItem(IRON_INGOT))
                                   .criterion("has_iron_nugget", hasItem(IRON_NUGGET))
                                   .criterion("has_torch", hasItem(torch))
                                   .criterion("has_coal", hasItems(ItemTags.COALS))
        );
    }

    private void table(ItemConvertible slab, ItemConvertible fence, ItemConvertible table) {
        this.add(
            b("table") + p(table),
            ShapedRecipeJsonBuilder.create(table)
                                   .group("table")
                                   .pattern("###")
                                   .pattern("X X")
                                   .pattern("X X")
                                   .input('#', slab)
                                   .input('X', fence)
                                   .criterion("has_slab", hasItem(slab))
                                   .criterion("has_fence", hasItem(fence))
        );
    }

    public void paperLantern(ItemConvertible content, ItemConvertible lantern) {
        this.add(
            b("paper_lantern") + p(lantern),
            ShapelessRecipeJsonBuilder.create(lantern)
                                      .group("paper_lantern")
                                      .input(content)
                                      .input(PAPER_LANTERN)
                                      .criterion("has_content", hasItem(content))
                                      .criterion("has_lantern", hasItem(PAPER_LANTERN))
        );
    }

    public void wood(WoodSet set, @Nullable TagKey<Item> logs) {
        if (!set.isVanilla()) {
            if (logs != null && TwigsToymakerImpl.containsItem(logs)) set.requireTo(s -> this.add(woodId(set, "planks"), planks(logs, s.get(PLANKS))), PLANKS);
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
    }

    public String b(String folder) {
        return "%s/".formatted(folder);
    }

    public String b(ItemConvertible item) {
        return b(p(item));
    }

    public String bp(ItemConvertible item) {
        return b(pp(item));
    }

    public SingleItemRecipeJsonBuilder variantStonecutting(ItemConvertible to, ItemConvertible from) {
        return stonecutting(from, to);
    }

    public Block getVariantRecipeInput(BlockFamily family, BlockFamily.Variant variant, boolean stonecutting) {
        if (variant == BlockFamily.Variant.CHISELED && !stonecutting) {
            if (!family.getVariants().containsKey(BlockFamily.Variant.SLAB)) throw new IllegalStateException("Slab is not defined for the family.");
            return family.getVariant(BlockFamily.Variant.SLAB);
        }
        return family.getBaseBlock();
    }

    public String p(ItemConvertible item) {
        return Registry.ITEM.getId(item.asItem()).getPath();
    }

    public String pp(ItemConvertible item) {
        String path = p(item);
        return path.substring(0, path.length() - 1);
    }
}
