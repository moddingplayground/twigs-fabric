package net.moddingplayground.twigs.api.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;

public interface TwigsRegistry {
    DefaultedRegistry<WoodSet> WOOD = defaulted("wood", WoodSet.class, "stripped_bamboo");

    private static <T> DefaultedRegistry<T> defaulted(String id, Class<T> type, String def) {
        return FabricRegistryBuilder.createDefaulted(type, new Identifier(Twigs.MOD_ID, id), new Identifier(Twigs.MOD_ID, def)).buildAndRegister();
    }
}
