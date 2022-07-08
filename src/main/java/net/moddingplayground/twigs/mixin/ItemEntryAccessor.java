package net.moddingplayground.twigs.mixin;

import net.minecraft.item.Item;
import net.minecraft.loot.entry.ItemEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemEntry.class)
public interface ItemEntryAccessor {
    @Accessor Item getItem();
    @Mutable @Accessor void setItem(Item item);
}
