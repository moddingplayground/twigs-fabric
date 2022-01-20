package net.moddingplayground.twigs.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Environment(EnvType.CLIENT)
@Mixin(EntityModelLayers.class)
public interface EntityModelLayersAccessor {
    @Accessor static String getMAIN() { throw new AssertionError(); }
    @Invoker static EntityModelLayer invokeCreate(String id, String layer) { throw new AssertionError(); }
}
