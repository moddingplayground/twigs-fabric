package net.moddingplayground.twigs.impl.data;

import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.toymaker.v0.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.api.tag.TwigsEntityTypeTags;

public class EntityTypeTagGenerator extends AbstractTagGenerator<EntityType<?>> {
    public EntityTypeTagGenerator(String modId) {
        super(modId, Registry.ENTITY_TYPE);
    }

    @Override
    public void generate() {
        this.add(TwigsEntityTypeTags.BAMBOO_LEAVES_SLOW_IMMUNE,
            EntityType.PANDA,
            EntityType.BEE,
            EntityType.PARROT,
            EntityType.OCELOT
        );
    }
}
