package net.moddingplayground.twigs.datagen.impl.generator.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public final class StateModelInfo {
    private final Identifier model;
    private ModelGen gen;
    private int x;
    private int y;
    private boolean uvLock;
    private int weight = 1;

    private StateModelInfo(Identifier model) {
        this.model = model;
    }

    private StateModelInfo modelGen(ModelGen modelgen) {
        this.gen = modelgen;
        return this;
    }

    public StateModelInfo rotate(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public StateModelInfo uvlock(boolean uvlock) {
        this.uvLock = uvlock;
        return this;
    }

    public StateModelInfo weight(int weight) {
        this.weight = weight;
        return this;
    }

    public void getModels(BiConsumer<Identifier, ModelGen> consumer) {
        if (gen != null) {
            consumer.accept(model, gen);
        }
    }

    public JsonObject makeJson(boolean withWeight) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model.toString());
        if (x != 0) {
            obj.addProperty("x", x);
        }
        if (y != 0) {
            obj.addProperty("y", y);
        }
        if (uvLock) {
            obj.addProperty("uvlock", true);
        }
        if (withWeight && weight != 1) {
            obj.addProperty("weight", weight);
        }
        return obj;
    }

    public static JsonElement makeJson(StateModelInfo... variants) {
        if (variants.length == 0) return new JsonArray();
        if (variants.length == 1) return variants[0].makeJson(false);
        JsonArray arr = new JsonArray();
        for (StateModelInfo variant : variants) {
            arr.add(variant.makeJson(true));
        }
        return arr;
    }

    public static StateModelInfo create(Identifier model) {
        return new StateModelInfo(model);
    }

    public static StateModelInfo create(Identifier model, ModelGen gen) {
        return new StateModelInfo(model).modelGen(gen);
    }

    public StateModelInfo copy() {
        return new StateModelInfo(this.model).modelGen(this.gen);
    }
}
