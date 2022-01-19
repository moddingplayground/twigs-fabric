package net.moddingplayground.twigs.datagen.impl.generator.recipe;

import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CraftingRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.datagen.impl.generator.AbstractGenerator;
import net.moddingplayground.twigs.datagen.impl.mixin.SingleItemRecipeJsonFactoryAccessor;

@SuppressWarnings({ "unused", "UnusedReturnValue" })
public abstract class AbstractRecipeGenerator extends AbstractGenerator<Identifier, CraftingRecipeJsonFactory> {
    public AbstractRecipeGenerator(String modId) {
        super(modId);
    }

    public AbstractRecipeGenerator add(String id, CraftingRecipeJsonFactory factory) {
        this.add(getId(id), factory);
        return this;
    }

    public SingleItemRecipeJsonFactory copyFactory(SingleItemRecipeJsonFactory factory, ItemConvertible newInput) {
        SingleItemRecipeJsonFactoryAccessor acco = (SingleItemRecipeJsonFactoryAccessor) factory;
        SingleItemRecipeJsonFactory nu = SingleItemRecipeJsonFactory.createStonecutting(Ingredient.ofItems(newInput), factory.getOutputItem(), acco.getCount())
                                                                    .group(acco.getGroup());
        ((SingleItemRecipeJsonFactoryAccessor) nu).setBuilder(acco.getBuilder());
        return nu;
    }

    protected static EnterBlockCriterion.Conditions inFluid(Block block) {
        return new EnterBlockCriterion.Conditions(EntityPredicate.Extended.EMPTY, block, StatePredicate.ANY);
    }

    public InventoryChangedCriterion.Conditions hasItem(ItemConvertible itemConvertible) {
        return this.hasItems(ItemPredicate.Builder.create().items(itemConvertible).build());
    }

    public InventoryChangedCriterion.Conditions hasItems(Tag<Item> tag) {
        return this.hasItems(ItemPredicate.Builder.create().tag(tag).build());
    }

    public InventoryChangedCriterion.Conditions hasItems(ItemPredicate... predicates) {
        return new InventoryChangedCriterion.Conditions(EntityPredicate.Extended.EMPTY, NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, predicates);
    }

    public ShapedRecipeJsonFactory generic3x3(ItemConvertible from, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', from)
                                      .pattern("###")
                                      .pattern("###")
                                      .pattern("###")
                                      .criterion("has_ingredient", hasItem(from));
    }

    public ShapedRecipeJsonFactory generic2x2(ItemConvertible from, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', from)
                                      .pattern("##")
                                      .pattern("##")
                                      .criterion("has_ingredient", hasItem(from));
    }

    public ShapedRecipeJsonFactory generic2x1(ItemConvertible from, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', from)
                                      .pattern("##")
                                      .criterion("has_ingredient", hasItem(from));
    }

    public ShapedRecipeJsonFactory generic2x3(ItemConvertible from, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', from)
                                      .pattern("##")
                                      .pattern("##")
                                      .pattern("##")
                                      .criterion("has_ingredient", hasItem(from));
    }

    public ShapedRecipeJsonFactory generic3x1(ItemConvertible from, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', from)
                                      .pattern("###")
                                      .criterion("has_ingredient", hasItem(from));
    }

    public ShapedRecipeJsonFactory sandwich(ItemConvertible outside, ItemConvertible inside, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', outside)
                                      .input('X', inside)
                                      .pattern("###")
                                      .pattern("XXX")
                                      .pattern("###")
                                      .criterion("has_outside", hasItem(outside))
                                      .criterion("has_inside", hasItem(inside));
    }

    public ShapedRecipeJsonFactory chequer2x2(ItemConvertible one, ItemConvertible two, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', one)
                                      .input('X', two)
                                      .pattern("#X")
                                      .pattern("X#")
                                      .criterion("has_one", hasItem(one))
                                      .criterion("has_two", hasItem(two));
    }

    public ShapedRecipeJsonFactory ring(ItemConvertible from, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', from)
                                      .pattern("###")
                                      .pattern("# #")
                                      .pattern("###")
                                      .criterion("has_ingredient", hasItem(from));
    }

    public ShapedRecipeJsonFactory ringSurrounding(ItemConvertible outside, ItemConvertible inside, ItemConvertible to, int count) {
        return ShapedRecipeJsonFactory.create(to, count)
                                      .input('#', outside)
                                      .input('X', inside)
                                      .pattern("###")
                                      .pattern("#X#")
                                      .pattern("###")
                                      .criterion("has_outside", hasItem(outside))
                                      .criterion("has_inside", hasItem(inside));
    }

    public ShapelessRecipeJsonFactory dualShapeless(ItemConvertible one, ItemConvertible two, ItemConvertible to, int count) {
        return ShapelessRecipeJsonFactory.create(to, count)
                                         .input(one)
                                         .input(two)
                                         .criterion("has_one", hasItem(one))
                                         .criterion("has_two", hasItem(two));
    }

    public ShapelessRecipeJsonFactory shapeless(ItemConvertible from, ItemConvertible to, int count) {
        return ShapelessRecipeJsonFactory.create(to, count)
                                         .input(from)
                                         .criterion("has_ingredient", hasItem(from));
    }

    public ShapelessRecipeJsonFactory shapeless(ItemConvertible[] from, ItemConvertible to, int count) {
        ShapelessRecipeJsonFactory factory = ShapelessRecipeJsonFactory.create(to, count)
                                                                       .input(Ingredient.ofItems(from));
        for (ItemConvertible itemC : from) {
            Item item = itemC.asItem();
            String itemId = Registry.ITEM.getId(item)
                                         .getPath();
            factory.criterion("has_" + itemId, hasItem(itemC));
        }

        return factory;
    }

    public SingleItemRecipeJsonFactory stonecutting(ItemConvertible from, ItemConvertible to, int count) {
        return SingleItemRecipeJsonFactory.createStonecutting(Ingredient.ofItems(from), to, count)
                                          .criterion("has_item", hasItem(from));
    }

    public SingleItemRecipeJsonFactory stonecutting(ItemConvertible from, ItemConvertible to) {
        return stonecutting(from, to, 1);
    }

    public ShapelessRecipeJsonFactory planks(ItemConvertible from, ItemConvertible to) {
        return ShapelessRecipeJsonFactory.create(to, 4)
                                         .input(from)
                                         .group("planks")
                                         .criterion("has_log", hasItem(from));
    }

    public ShapelessRecipeJsonFactory planks(Tag<Item> from, ItemConvertible to) {
        return ShapelessRecipeJsonFactory.create(to, 4)
                                         .input(from)
                                         .group("planks")
                                         .criterion("has_log", hasItems(from));
    }

    public ShapelessRecipeJsonFactory planksLogs(Tag<Item> from, ItemConvertible to) {
        return ShapelessRecipeJsonFactory.create(to, 4)
                                         .input(from)
                                         .group("planks")
                                         .criterion("has_logs", hasItems(from));
    }

    public ShapedRecipeJsonFactory wood(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 3)
                                      .input('#', from)
                                      .pattern("##")
                                      .pattern("##")
                                      .group("bark")
                                      .criterion("has_log", hasItem(from));
    }

    public ShapedRecipeJsonFactory boat(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to)
                                      .input('#', from)
                                      .pattern("# #")
                                      .pattern("###")
                                      .group("boat")
                                      .criterion("in_water", inFluid(Blocks.WATER));
    }

    public ShapelessRecipeJsonFactory woodenButton(ItemConvertible from, ItemConvertible to) {
        return ShapelessRecipeJsonFactory.create(to)
                                         .input(from)
                                         .group("wooden_button")
                                         .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenDoor(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 3)
                                      .input('#', from)
                                      .pattern("##")
                                      .pattern("##")
                                      .pattern("##")
                                      .group("wooden_door")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenFence(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 3)
                                      .input('#', Items.STICK)
                                      .input('W', from)
                                      .pattern("W#W")
                                      .pattern("W#W")
                                      .group("wooden_fence")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenFenceGate(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to)
                                      .input('#', Items.STICK)
                                      .input('W', from)
                                      .pattern("#W#")
                                      .pattern("#W#")
                                      .group("wooden_fence_gate")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenPressurePlate(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to)
                                      .input('#', from)
                                      .pattern("##")
                                      .group("wooden_pressure_plate")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenSlab(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 6)
                                      .input('#', from)
                                      .pattern("###")
                                      .group("wooden_slab")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenStairs(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 4)
                                      .input('#', from)
                                      .pattern("#  ")
                                      .pattern("## ")
                                      .pattern("###")
                                      .group("wooden_stairs")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory woodenTrapdoor(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 2)
                                      .input('#', from)
                                      .pattern("###")
                                      .pattern("###")
                                      .group("wooden_trapdoor")
                                      .criterion("has_planks", hasItem(from));
    }

    public ShapedRecipeJsonFactory sign(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        return ShapedRecipeJsonFactory.create(to, 3)
                                      .group("sign")
                                      .input('#', from)
                                      .input('X', Items.STICK)
                                      .pattern("###")
                                      .pattern("###")
                                      .pattern(" X ")
                                      .criterion("has_" + string, hasItem(from));
    }

    public ShapelessRecipeJsonFactory wool(ItemConvertible from, ItemConvertible to) {
        return ShapelessRecipeJsonFactory.create(to)
                                         .input(from)
                                         .input(Blocks.WHITE_WOOL)
                                         .group("wool")
                                         .criterion("has_white_wool", hasItem(Blocks.WHITE_WOOL));
    }

    public ShapedRecipeJsonFactory carpet(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        return ShapedRecipeJsonFactory.create(to, 3)
                                      .input('#', from)
                                      .pattern("##")
                                      .group("carpet")
                                      .criterion("has_" + string, hasItem(from));
    }

    public ShapedRecipeJsonFactory dyedCarpet(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(to.asItem())
                                     .getPath();
        String string2 = Registry.ITEM.getId(from.asItem())
                                      .getPath();
        return ShapedRecipeJsonFactory.create(to, 8)
                                      .input('#', Blocks.WHITE_CARPET)
                                      .input('$', from)
                                      .pattern("###")
                                      .pattern("#$#")
                                      .pattern("###")
                                      .group("carpet")
                                      .criterion("has_white_carpet", hasItem(Blocks.WHITE_CARPET))
                                      .criterion("has_" + string2, hasItem(from));
    }

    public ShapedRecipeJsonFactory bed(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        return ShapedRecipeJsonFactory.create(to)
                                      .input('#', from)
                                      .input('X', ItemTags.PLANKS)
                                      .pattern("###")
                                      .pattern("XXX")
                                      .group("bed")
                                      .criterion("has_" + string, hasItem(from));
    }

    public ShapelessRecipeJsonFactory dyedBed(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(to.asItem())
                                     .getPath();
        return ShapelessRecipeJsonFactory.create(to)
                                         .input(Items.WHITE_BED)
                                         .input(from)
                                         .group("dyed_bed")
                                         .criterion("has_bed", hasItem(Items.WHITE_BED));
    }

    public ShapedRecipeJsonFactory banner(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        return ShapedRecipeJsonFactory.create(to)
                                      .input('#', from)
                                      .input('|', Items.STICK)
                                      .pattern("###")
                                      .pattern("###")
                                      .pattern(" | ")
                                      .group("banner")
                                      .criterion("has_" + string, hasItem(from));
    }

    public ShapedRecipeJsonFactory stainedGlass(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 8)
                                      .input('#', Blocks.GLASS)
                                      .input('X', from)
                                      .pattern("###")
                                      .pattern("#X#")
                                      .pattern("###")
                                      .group("stained_glass")
                                      .criterion("has_glass", hasItem(Blocks.GLASS));
    }

    public ShapedRecipeJsonFactory stainedGlassPaneGlass(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 16)
                                      .input('#', from)
                                      .pattern("###")
                                      .pattern("###")
                                      .group("stained_glass_pane")
                                      .criterion("has_glass", hasItem(from));
    }

    public ShapedRecipeJsonFactory stainedGlassPaneDye(ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(to.asItem()).getPath();
        String string2 = Registry.ITEM.getId(from.asItem()).getPath();
        return ShapedRecipeJsonFactory.create(to, 8)
                                      .input('#', Blocks.GLASS_PANE)
                                      .input('$', from)
                                      .pattern("###")
                                      .pattern("#$#")
                                      .pattern("###")
                                      .group("stained_glass_pane")
                                      .criterion("has_glass_pane", hasItem(Blocks.GLASS_PANE))
                                      .criterion("has_" + string2, hasItem(from));
    }

    public ShapedRecipeJsonFactory stainedTerracotta(ItemConvertible from, ItemConvertible to) {
        return ShapedRecipeJsonFactory.create(to, 8)
                                      .input('#', Blocks.TERRACOTTA)
                                      .input('X', from)
                                      .pattern("###")
                                      .pattern("#X#")
                                      .pattern("###")
                                      .group("stained_terracotta")
                                      .criterion("has_terracotta", hasItem(Blocks.TERRACOTTA));
    }

    public ShapelessRecipeJsonFactory concretePowder(ItemConvertible from, ItemConvertible to) {
        return ShapelessRecipeJsonFactory.create(to, 8)
                                         .input(from)
                                         .input(Blocks.SAND, 4)
                                         .input(Blocks.GRAVEL, 4)
                                         .group("concrete_powder")
                                         .criterion("has_sand", hasItem(Blocks.SAND))
                                         .criterion("has_gravel", hasItem(Blocks.GRAVEL));
    }
}
