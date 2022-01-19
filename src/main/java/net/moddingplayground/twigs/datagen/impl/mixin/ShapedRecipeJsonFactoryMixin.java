package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
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
import java.util.Map;
import java.util.function.Consumer;

@Mixin(ShapedRecipeJsonFactory.class)
public abstract class ShapedRecipeJsonFactoryMixin {
    @Shadow @Final private Advancement.Task builder;
    @Shadow @Final private Item output;
    @Shadow @Final private int outputCount;
    @Shadow @Nullable private String group;
    @Shadow @Final private List<String> pattern;
    @Shadow @Final private Map<Character, Ingredient> inputs;

    @Shadow protected abstract void validate(Identifier recipeId);

    @Inject(method = "offerTo", at = @At("HEAD"), cancellable = true)
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id, CallbackInfo ci) {
        if (id.getNamespace().equals(Twigs.MOD_ID)) {
            this.validate(id);
            this.builder.parent(new Identifier(Twigs.MOD_ID, "recipes/root"))
                        .criterion("has_the_recipe", RecipeUnlockedCriterion.create(id))
                        .rewards(AdvancementRewards.Builder.recipe(id))
                        .criteriaMerger(CriterionMerger.OR);

            exporter.accept(new ShapedRecipeJsonFactory.ShapedRecipeJsonProvider(
                id, this.output, this.outputCount,
                this.group == null
                    ? ""
                    : this.group,
                this.pattern, this.inputs, this.builder,
                new Identifier(id.getNamespace(), "recipes/" + id.getPath())
            ));

            ci.cancel();
        }
    }
}
