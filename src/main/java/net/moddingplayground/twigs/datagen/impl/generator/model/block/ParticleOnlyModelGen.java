package net.moddingplayground.twigs.datagen.impl.generator.model.block;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;

@SuppressWarnings("unused")
public record ParticleOnlyModelGen(Identifier particles) implements ModelGen {
    @Override
    public JsonElement makeJson(Identifier name) {
        JsonObject root = new JsonObject();
        JsonObject textures = new JsonObject();
        textures.addProperty("particle", particles.toString());
        root.add("textures", textures);
        return root;
    }

    public static ParticleOnlyModelGen particles(Identifier particles) {
        return new ParticleOnlyModelGen(particles);
    }

    public static ParticleOnlyModelGen particles(String particles) {
        return new ParticleOnlyModelGen(Identifier.tryParse(particles));
    }
}
