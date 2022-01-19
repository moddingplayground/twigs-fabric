package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonFactory;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SingleItemRecipeJsonFactory.class)
public interface SingleItemRecipeJsonFactoryAccessor {
    @Accessor Ingredient getInput();
    @Accessor String getGroup();
    @Accessor int getCount();

    @Accessor Advancement.Task getBuilder();
    @Mutable @Accessor void setBuilder(Advancement.Task builder);
}
