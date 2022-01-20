package net.moddingplayground.twigs.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.item.BoatItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Predicate;

@Mixin(BoatItem.class)
public interface BoatItemAccessor {
    @Accessor static Predicate<Entity> getRIDERS() { throw new AssertionError(); }
}
