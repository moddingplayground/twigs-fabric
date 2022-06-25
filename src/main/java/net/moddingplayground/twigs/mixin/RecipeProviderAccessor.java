package net.moddingplayground.twigs.mixin;

import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.function.BiFunction;

@Mixin(RecipeProvider.class)
public interface RecipeProviderAccessor {
    @Accessor static Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> getVARIANT_FACTORIES() { throw new AssertionError(); }
}
