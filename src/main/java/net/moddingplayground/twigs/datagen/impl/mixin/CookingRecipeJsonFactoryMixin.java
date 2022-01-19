package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.Twigs;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(CookingRecipeJsonFactory.class)
public abstract class CookingRecipeJsonFactoryMixin {
    @Shadow @Final private Advancement.Task builder;
    @Shadow @Nullable private String group;
    @Shadow @Final private Ingredient input;
    @Shadow @Final private Item output;
    @Shadow @Final private float experience;
    @Shadow @Final private int cookingTime;
    @Shadow @Final private CookingRecipeSerializer<?> serializer;

    @Shadow protected abstract void validate(Identifier recipeId);

    @Inject(method = "offerTo", at = @At("HEAD"), cancellable = true)
    private void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id, CallbackInfo ci) {
        if (id.getNamespace().equals(Twigs.MOD_ID)) {
            this.validate(id);
            this.builder.parent(new Identifier(Twigs.MOD_ID, "recipes/root"))
                        .criterion("has_the_recipe", RecipeUnlockedCriterion.create(id))
                        .rewards(AdvancementRewards.Builder.recipe(id))
                        .criteriaMerger(CriterionMerger.OR);

            exporter.accept(new CookingRecipeJsonFactory.CookingRecipeJsonProvider(
                id,
                this.group == null
                    ? ""
                    : this.group,
                this.input, this.output, this.experience, this.cookingTime, this.builder,
                new Identifier(id.getNamespace(), "recipes/" + id.getPath()), this.serializer
            ));

            ci.cancel();
        }
    }
}
