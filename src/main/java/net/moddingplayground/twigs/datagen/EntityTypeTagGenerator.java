package net.moddingplayground.twigs.datagen;

import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.datagen.impl.generator.tag.AbstractTagGenerator;
import net.moddingplayground.twigs.tag.TwigsEntityTypeTags;

public class EntityTypeTagGenerator extends AbstractTagGenerator<EntityType<?>> {
    public EntityTypeTagGenerator() {
        super(Twigs.MOD_ID, Registry.ENTITY_TYPE);
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
