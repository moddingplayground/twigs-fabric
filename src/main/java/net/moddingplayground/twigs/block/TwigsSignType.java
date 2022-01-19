package net.moddingplayground.twigs.block;

import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.mixin.SignTypeInvoker;

public class TwigsSignType extends SignType {
    private final Identifier id;

    public TwigsSignType(Identifier id) {
        super(id.toString());
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    public static SignType register(String id) {
        return SignTypeInvoker.invokeRegister(new TwigsSignType(new Identifier(Twigs.MOD_ID, id)));
    }
}
