package net.moddingplayground.twigs.datagen.impl.generator.model;

import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public interface StateGen {
    JsonElement makeJson(Identifier id, Block block);
    void getModels(BiConsumer<Identifier, ModelGen> consumer);
}
