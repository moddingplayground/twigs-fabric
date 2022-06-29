package net.moddingplayground.twigs.impl.client.entity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.moddingplayground.twigs.api.entity.TwigsEntityType;

@Environment(EnvType.CLIENT)
public final class TwigsEntityTypeClientImpl implements TwigsEntityType, ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(PEBBLE, FlyingItemEntityRenderer::new);
    }
}
