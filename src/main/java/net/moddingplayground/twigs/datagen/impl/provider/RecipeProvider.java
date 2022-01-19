package net.moddingplayground.twigs.datagen.impl.provider;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.RecipeGenerator;
import net.moddingplayground.twigs.datagen.impl.DataType;
import net.moddingplayground.twigs.datagen.impl.generator.recipe.AbstractRecipeGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RecipeProvider extends AbstractDataProvider<Supplier<AbstractRecipeGenerator>> {
    public RecipeProvider(DataGenerator root) {
        super(root);
    }

    @Override
    public String getName() {
        return "Recipe";
    }

    @Override
    public String getFolder() {
        return "recipes";
    }

    @Override
    public DataType getDataType() {
        return DataType.DATA;
    }

    @Override
    public List<Supplier<AbstractRecipeGenerator>> getGenerators() {
        return List.of(RecipeGenerator::new);
    }

    @Override
    public void run(DataCache cache) {
        super.run(cache);
        this.write(cache, this.createFileMapAdvancements(), (path, id) -> this.getOutput(path, id, "advancements/recipes"));
    }

    @Override
    public Map<Identifier, JsonElement> createFileMap() {
        Map<Identifier, JsonElement> map = Maps.newHashMap();
        for (Supplier<AbstractRecipeGenerator> generator : this.getGenerators()) {
            generator.get().accept(
                (id, factory) -> factory.offerTo(provider -> {
                    if (map.put(id, provider.toJson()) != null) {
                        throw new IllegalStateException("Duplicate recipe " + id);
                    }
                }, id)
            );
        }
        return map;
    }

    public Map<Identifier, JsonElement> createFileMapAdvancements() {
        Map<Identifier, JsonElement> map = Maps.newHashMap();
        for (Supplier<AbstractRecipeGenerator> generator : this.getGenerators()) {
            AbstractRecipeGenerator gen = generator.get();
            Identifier rootId = gen.getId("root");
            gen.accept(
                (id, factory) -> factory.offerTo(provider -> {
                    JsonObject json = provider.toAdvancementJson();
                    if (json != null) {
                        if (map.put(id, json) != null) {
                            throw new IllegalStateException("Duplicate recipe advancement " + id);
                        } else {
                            if (!map.containsKey(rootId)) {
                                map.put(rootId, Advancement.Task.create()
                                                                .criterion("impossible", new ImpossibleCriterion.Conditions())
                                                                .toJson()
                                );
                            }
                        }
                    }
                }, id)
            );
        }
        return map;
    }
}
