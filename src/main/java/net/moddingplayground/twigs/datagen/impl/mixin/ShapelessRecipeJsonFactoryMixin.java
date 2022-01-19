package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Item;
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

import java.util.List;
import java.util.function.Consumer;

@Mixin(ShapelessRecipeJsonFactory.class)
public abstract class ShapelessRecipeJsonFactoryMixin {
    @Shadow @Final private Advancement.Task builder;
    @Shadow @Final private Item output;
    @Shadow @Final private int outputCount;
    @Shadow @Nullable private String group;
    @Shadow @Final private List<Ingredient> inputs;

    @Shadow protected abstract void validate(Identifier recipeId);

    @Inject(method = "offerTo", at = @At(value = "HEAD"), cancellable = true)
    private void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId, CallbackInfo ci) {
        if (recipeId.getNamespace().equals(Twigs.MOD_ID)) {
            this.validate(recipeId);
            this.builder.parent(new Identifier(Twigs.MOD_ID, "recipes/root"))
                        .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                        .rewards(AdvancementRewards.Builder.recipe(recipeId))
                        .criteriaMerger(CriterionMerger.OR);

            exporter.accept(new ShapelessRecipeJsonFactory.ShapelessRecipeJsonProvider(
                recipeId, this.output, this.outputCount,
                this.group == null
                    ? ""
                    : this.group,
                this.inputs, this.builder,
                new Identifier(recipeId.getNamespace(), "recipes/" + recipeId.getPath())
            ));

            ci.cancel();
        }
    }
}
