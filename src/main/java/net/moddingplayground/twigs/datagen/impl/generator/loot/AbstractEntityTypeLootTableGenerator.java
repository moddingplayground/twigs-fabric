package net.moddingplayground.twigs.datagen.impl.generator.loot;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

@SuppressWarnings("unused")
public abstract class AbstractEntityTypeLootTableGenerator extends AbstractLootTableGenerator<EntityType<?>> {
    public AbstractEntityTypeLootTableGenerator(String modId) {
        super(modId);
    }

    @Override
    public Registry<EntityType<?>> getRegistry() {
        return Registry.ENTITY_TYPE;
    }

    @Override
    public void testObject(Identifier id, EntityType<?> obj) {
        if (!Objects.equals(obj.getBaseClass().getName(), LivingEntity.class.getName())) {
            throw new IllegalStateException(String.format("Weird loottable '%s' for '%s', not a LivingEntity so should not have loot", id, this.getRegistry().getId(obj)));
        }
    }
}
