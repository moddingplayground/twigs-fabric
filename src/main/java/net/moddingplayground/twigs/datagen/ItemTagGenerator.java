package net.moddingplayground.twigs.datagen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.block.wood.WoodBlock;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.datagen.impl.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.tag.TwigsItemTags;
import org.jetbrains.annotations.Nullable;

import static net.moddingplayground.twigs.block.TwigsBlocks.*;

public class ItemTagGenerator extends AbstractTagGenerator<Item> {
    public ItemTagGenerator() {
        super(Twigs.MOD_ID, Registry.ITEM);
    }

    @Override
    public void generate() {
        this.add(TwigsItemTags.PAPER_LANTERNS, PAPER_LANTERN, ALLIUM_PAPER_LANTERN, BLUE_ORCHID_PAPER_LANTERN, CRIMSON_ROOTS_PAPER_LANTERN, DANDELION_PAPER_LANTERN);
        this.add(ItemTags.DIRT, ROCKY_DIRT);
        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));
    }

    public void wood(WoodSet set, @Nullable Tag.Identified<Item> logs) {
        if (!set.isVanilla()) {
            this.wood(set, ItemTags.PLANKS, WoodBlock.PLANKS);
            this.wood(set, ItemTags.SAPLINGS, WoodBlock.SAPLING);
            this.wood(set, logs, WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD);

            if (set.isFlammable()) {
                if (logs != null && !logs.values().isEmpty()) this.add(ItemTags.LOGS_THAT_BURN, logs);
            } else {
                if (logs != null && !logs.values().isEmpty()) this.add(ItemTags.LOGS, logs);
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
        if (set instanceof TwigsWoodSet twigs) this.add(TwigsItemTags.TABLES, twigs.getTable());
    }

    public void woods(WoodSet... sets) {
        for (WoodSet set : sets) this.wood(set, null);
    }

    public void wood(WoodSet set, @Nullable Tag.Identified<Item> tag, WoodBlock... woods) {
        for (WoodBlock wood : woods) { if (set.contains(wood)) this.add(tag, set.get(wood).asItem()); }
    }

    private void add(Tag.Identified<Item> tag, Block... blocks) {
        for (Block block : blocks) this.add(tag, block.asItem());
    }
}
