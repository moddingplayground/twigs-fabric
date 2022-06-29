package net.moddingplayground.twigs.api.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsEntityType {
    EntityType<PebbleEntity> PEBBLE = register("pebble",
        FabricEntityTypeBuilder.<PebbleEntity>create()
                               .entityFactory(PebbleEntity::new)
                               .spawnGroup(SpawnGroup.MISC)
                               .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                               .trackRangeChunks(4)
                               .trackedUpdateRate(10)
    );

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Twigs.MOD_ID, id), builder.build());
    }
}
