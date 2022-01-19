package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
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

@Mixin(SingleItemRecipeJsonFactory.class)
public abstract class SingleItemRecipeJsonFactoryMixin {
    @Shadow @Nullable private String group;
    @Shadow @Final private Ingredient input;
    @Shadow @Final private Item output;
    @Shadow @Final private int count;
    @Shadow @Final private Advancement.Task builder;
    @Shadow @Final private RecipeSerializer<?> serializer;

    @Shadow protected abstract void validate(Identifier recipeId);

    @Inject(method = "offerTo", at = @At(value = "HEAD"), cancellable = true)
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id, CallbackInfo ci) {
        if (id.getNamespace().equals(Twigs.MOD_ID)) {
            this.validate(id);
            this.builder.parent(new Identifier(Twigs.MOD_ID, "recipes/root"))
                        .criterion("has_the_recipe", RecipeUnlockedCriterion.create(id))
                        .rewards(AdvancementRewards.Builder.recipe(id))
                        .criteriaMerger(CriterionMerger.OR);

            exporter.accept(new SingleItemRecipeJsonFactory.SingleItemRecipeJsonProvider(
                id, this.serializer,
                this.group == null
                    ? ""
                    : this.group,
                this.input, this.output, this.count, this.builder,
                new Identifier(id.getNamespace(), "recipes/" + id.getPath())
            ));

            ci.cancel();
        }
    }
}
