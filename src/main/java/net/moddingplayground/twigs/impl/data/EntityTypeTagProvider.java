package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.moddingplayground.twigs.api.tag.TwigsEntityTypeTags;

import static net.minecraft.entity.EntityType.*;

class EntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public EntityTypeTagProvider(FabricDataGenerator gen) {
        super(gen);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(TwigsEntityTypeTags.BAMBOO_LEAVES_SLOW_IMMUNE).add(
            PANDA,
            BEE,
            PARROT,
            OCELOT
        );
    }
}
