package net.moddingplayground.twigs.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.SimpleRegistry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.wood.WoodSet;

public class TwigsRegistry {
    public static final DefaultedRegistry<WoodSet> WOOD = defaulted("wood", WoodSet.class, id("stripped_bamboo"));

    private static <T> SimpleRegistry<T> simple(String id, Class<T> type) {
        return FabricRegistryBuilder.createSimple(type, id(id)).buildAndRegister();
    }

    private static <T> DefaultedRegistry<T> defaulted(String id, Class<T> type, Identifier def) {
        return FabricRegistryBuilder.createDefaulted(type, id(id), def).buildAndRegister();
    }

    private static Identifier id(String path) {
        return new Identifier(Twigs.MOD_ID, path);
    }
}
