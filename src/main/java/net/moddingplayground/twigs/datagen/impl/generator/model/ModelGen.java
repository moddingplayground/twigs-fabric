package net.moddingplayground.twigs.datagen.impl.generator.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
@FunctionalInterface
public interface ModelGen {
    ModelGen EMPTY = name -> new JsonObject();
    JsonElement makeJson(Identifier name);
}
