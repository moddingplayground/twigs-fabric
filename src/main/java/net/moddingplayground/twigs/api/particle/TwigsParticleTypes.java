package net.moddingplayground.twigs.api.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsParticleTypes {
    DefaultParticleType ITEM_PEBBLE = register("item_pebble", false);

    private static DefaultParticleType register(String id, boolean alwaysShow) {
        return Registry.register(Registry.PARTICLE_TYPE, new Identifier(Twigs.MOD_ID, id), FabricParticleTypes.simple(alwaysShow));
    }
}
