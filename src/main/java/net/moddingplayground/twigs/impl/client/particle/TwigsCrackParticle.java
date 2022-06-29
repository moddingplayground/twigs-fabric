package net.moddingplayground.twigs.impl.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.CrackParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.moddingplayground.twigs.api.item.TwigsItems;

@Environment(EnvType.CLIENT)
public class TwigsCrackParticle extends CrackParticle {
    public TwigsCrackParticle(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @Environment(value= EnvType.CLIENT)
    public static class PebbleFactory implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new TwigsCrackParticle(world, x, y, z, new ItemStack(TwigsItems.PEBBLE));
        }
    }
}
