package net.moddingplayground.twigs.datagen.impl.generator.model.block;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateModelInfo;

import java.util.List;
import java.util.function.BiConsumer;


@SuppressWarnings("unused")
public class MultipartStateGen implements StateGen {
    private final List<Part> parts = Lists.newArrayList();

    private MultipartStateGen() {}

    @Override
    public JsonElement makeJson(Identifier id, Block block) {
        JsonObject root = new JsonObject();
        JsonArray multipart = new JsonArray();

        for (Part part : parts) {
            multipart.add(part.getJson());
        }

        root.add("multipart", multipart);
        return root;
    }

    @Override
    public void getModels(BiConsumer<Identifier, ModelGen> consumer) {
        for (Part part : parts) {
            for (StateModelInfo model : part.models) {
                model.getModels(consumer);
            }
        }
    }

    public MultipartStateGen part(StateModelInfo... models) {
        parts.add(new Part(null, models));
        return this;
    }

    public MultipartStateGen part(StateSelector sel, StateModelInfo... models) {
        parts.add(new Part(sel, models));
        return this;
    }

    public static MultipartStateGen multipart() {
        return new MultipartStateGen();
    }

    private record Part(StateSelector selector, StateModelInfo[] models) {
        public JsonObject getJson() {
            JsonObject obj = new JsonObject();
            if (selector != null) {
                obj.add("when", selector.getJson());
            }
            obj.add("apply", StateModelInfo.makeJson(models));
            return obj;
        }
    }
}
