package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonFactory;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.function.BiFunction;

@Mixin(RecipesProvider.class)
public interface RecipesProviderAccessor {
    @Accessor
    static Map<BlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonFactory>> getVARIANT_FACTORIES() {
        throw new AssertionError();
    }
}
