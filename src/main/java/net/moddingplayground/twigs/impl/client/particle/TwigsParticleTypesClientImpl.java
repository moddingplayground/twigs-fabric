package net.moddingplayground.twigs.impl.client.particle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.moddingplayground.twigs.api.particle.TwigsParticleTypes;

@Environment(EnvType.CLIENT)
public final class TwigsParticleTypesClientImpl implements TwigsParticleTypes, ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry particleFactories = ParticleFactoryRegistry.getInstance();
        particleFactories.register(ITEM_PEBBLE, new TwigsCrackParticle.PebbleFactory());
    }
}
