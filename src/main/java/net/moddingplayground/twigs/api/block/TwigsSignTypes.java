package net.moddingplayground.twigs.api.block;

import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.moddingplayground.frame.api.woods.v0.FrameSignType;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsSignTypes {
    SignType STRIPPED_BAMBOO = register("stripped_bamboo");

    private static SignType register(String id) {
        return FrameSignType.register(new Identifier(Twigs.MOD_ID, id));
    }
}
