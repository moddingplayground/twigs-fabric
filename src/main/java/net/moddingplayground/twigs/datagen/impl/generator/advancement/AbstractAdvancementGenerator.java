package net.moddingplayground.twigs.datagen.impl.generator.advancement;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.generator.AbstractGenerator;

@SuppressWarnings("unused")
public abstract class AbstractAdvancementGenerator extends AbstractGenerator<Identifier, Advancement.Task> {
    public AbstractAdvancementGenerator(String modId) {
        super(modId);
    }

    public AbstractAdvancementGenerator add(String id, Advancement.Task factory) {
        this.add(getId(id), factory);
        return this;
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
}
