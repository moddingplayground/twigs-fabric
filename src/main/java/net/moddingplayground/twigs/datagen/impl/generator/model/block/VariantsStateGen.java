package net.moddingplayground.twigs.datagen.impl.generator.model.block;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateModelInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public class VariantsStateGen implements StateGen {
    private final Map<String, StateModelInfo[]> variants = new HashMap<>();

    private VariantsStateGen() {}

    @Override
    public JsonElement makeJson(Identifier id, Block block) {
        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();
        for (Map.Entry<String, StateModelInfo[]> variant : this.variants.entrySet()) {
            JsonElement variantJson = StateModelInfo.makeJson(variant.getValue());
            variants.add(variant.getKey(), variantJson);
        }
        root.add("variants", variants);
        return root;
    }

    @Override
    public void getModels(BiConsumer<Identifier, ModelGen> consumer) {
        for (StateModelInfo[] infos : variants.values()) {
            for (StateModelInfo info : infos) {
                info.getModels(consumer);
            }
        }
    }

    public VariantsStateGen variant(String variant, StateModelInfo... models) {
        variants.put(variant, models.clone());
        return this;
    }

    public static VariantsStateGen variants(String variant, StateModelInfo... models) {
        return new VariantsStateGen().variant(variant, models);
    }

    public static VariantsStateGen variants(StateModelInfo... models) {
        return new VariantsStateGen().variant("", models);
    }

    public static VariantsStateGen variants() {
        return new VariantsStateGen();
    }
}
