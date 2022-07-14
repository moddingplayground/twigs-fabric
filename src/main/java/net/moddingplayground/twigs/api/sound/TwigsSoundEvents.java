package net.moddingplayground.twigs.api.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsSoundEvents {
    SoundEvent BLOCK_LAMP_LIGHT = lamp("light");
    SoundEvent BLOCK_LAMP_EXTINGUISH = lamp("extinguish");
    private static SoundEvent lamp(String id) {
        return block("lamp", id);
    }

    SoundEvent BLOCK_SCULK_PASSENGER_MOVE = block("sculk_passenger", "move");

    SoundEvent BLOCK_AZALEA_FLOWERS_SHEAR = block("azalea_flowers", "shear");
    SoundEvent BLOCK_BAMBOO_STRIP_SHEAR = block("bamboo", "strip_shear");

    SoundEvent ENTITY_PEBBLE_THROW = register("entity.pebble.throw");

    private static SoundEvent block(String block, String id) {
        return register("block.%s.%s".formatted(block, id));
    }

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Twigs.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }
}
