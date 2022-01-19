package net.moddingplayground.twigs.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.moddingplayground.twigs.block.TwigsSignType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    @Shadow @Final public static Identifier SIGNS_ATLAS_TEXTURE;

    @Inject(method = "createSignTextureId", at = @At("HEAD"), cancellable = true)
    private static void onCreateSignTextureId(SignType type, CallbackInfoReturnable<SpriteIdentifier> cir) {
        if (type instanceof TwigsSignType t) {
            Identifier id = t.getId();
            cir.setReturnValue(new SpriteIdentifier(SIGNS_ATLAS_TEXTURE, new Identifier(id.getNamespace(), "entity/signs/%s".formatted(id.getPath()))));
        }
    }
}
