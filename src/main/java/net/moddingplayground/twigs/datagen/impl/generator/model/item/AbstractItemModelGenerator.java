package net.moddingplayground.twigs.datagen.impl.generator.model.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.datagen.impl.generator.model.AbstractModelGenerator;
import net.moddingplayground.twigs.datagen.impl.generator.model.InheritingModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;

import java.util.function.Function;

public abstract class AbstractItemModelGenerator extends AbstractModelGenerator<Item, ModelGen> {
    public AbstractItemModelGenerator(String modId) {
        super(modId);
    }

    @Override
    public Registry<Item> getRegistry() {
        return Registry.ITEM;
    }

    public void add(ItemConvertible provider, Function<Item, ModelGen> factory) {
        Item item = provider.asItem();
        ModelGen gen = factory.apply(item);
        Identifier id = Registry.ITEM.getId(item);
        this.add(id, gen);
    }

    public void add(ItemConvertible provider, Function<Item, Identifier> id, Function<Item, ModelGen> factory) {
        Item item = provider.asItem();
        this.add(id.apply(item), factory.apply(item));
    }

    public void block(Block... blocks) {
        for (Block block : blocks) {
            this.add(Registry.BLOCK.getId(block), InheritingModelGen.inherit(name(block.asItem())));
        }
    }

    public void waxed(Block... blocks) {
        for (Block block : blocks) {
            this.add(Registry.BLOCK.getId(block), InheritingModelGen.inherit(name(block.asItem(), "block/%s", "(^waxed_)", "")));
        }
    }

    public void generated(Item item) {
        this.add(item, this::generatedItem);
    }

    public void generated(Block block) {
        this.add(block, this::generatedBlock);
    }

    public <T> T using(Identifier name, Function<Identifier, T> func) {
        return func.apply(name);
    }
    public ModelGen inherit(Identifier name) {
        return InheritingModelGen.inherit(name);
    }

    public ModelGen inherit(Item item) {
        return inherit(name(item));
    }

    public ModelGen generatedItem(Item item) {
        return InheritingModelGen.generated(name(item.asItem(), "item/%s"));
    }
    public ModelGen generatedBlock(Item item) {
        return InheritingModelGen.generated(name(item, "block/%s"));
    }

    public void generatedItems(ItemConvertible... items) {
        for (ItemConvertible item : items) {
            Item i = item.asItem();
            this.add(i, this::generatedItem);
        }
    }

    public ModelGen wall(Item item) {
        return InheritingModelGen.wallInventory(name(item, "block/%s", "_wall"));
    }
    public ModelGen wallPlural(Item item) {
        return InheritingModelGen.wallInventory(name(item, "block/%ss", "_wall"));
    }
    public ModelGen wallColumn(Item item) {
        Identifier top = name(item, "block/%s_top", "_wall");
        return InheritingModelGen.wallSidedInventory(top, top, name(item, "block/%s", "_wall"));
    }
    public ModelGen wallVanilla(Item item) {
        return InheritingModelGen.wallInventory(vanilla(item, "block/%s", "_wall"));
    }

    public void walls(Block... blocks) {
        for (Block block : blocks) { this.add(block, this::wall); }
    }
    public void wallColumns(Block... blocks) {
        for (Block block : blocks) { this.add(block, this::wallColumn); }
    }
    public void wallsVanilla(Block... blocks) {
        for (Block block : blocks) { this.add(block, this::wallVanilla); }
    }
    public void wallPlurals(Block... blocks) {
        for (Block block : blocks) { this.add(block, this::wallPlural); }
    }

    public ModelGen dyeable(Item item) {
        return InheritingModelGen.generated(
            name(item, "item/%s"),
            name(item, "item/%s_overlay")
        );
    }

    public ModelGen spawnEgg() {
        return InheritingModelGen.inherit("item/template_spawn_egg");
    }
}
