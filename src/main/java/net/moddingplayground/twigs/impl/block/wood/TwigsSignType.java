package net.moddingplayground.twigs.impl.block.wood;

import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.moddingplayground.frame.api.rendering.v0.SignTextureProvider;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.mixin.SignTypeInvoker;

public class TwigsSignType extends SignType implements SignTextureProvider {
    private final Identifier id;

    public TwigsSignType(Identifier id) {
        super(id.toString());
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    @Override
    public Identifier getTexture(SignType type) {
        Identifier id = ((TwigsSignType) type).getId();
        return new Identifier(id.getNamespace(), "entity/signs/%s".formatted(id.getPath()));
    }

    public static SignType register(String id) {
        return SignTypeInvoker.invokeRegister(new TwigsSignType(new Identifier(Twigs.MOD_ID, id)));
    }
}
