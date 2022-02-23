package net.moddingplayground.twigs.impl.entity;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public class TwigsEntityType {
    public static final EntityType<TwigsBoatEntity> BOAT = register(
        "boat",
        FabricEntityTypeBuilder.<TwigsBoatEntity>create()
                               .entityFactory(TwigsBoatEntity::new).spawnGroup(SpawnGroup.MISC)
                               .dimensions(EntityDimensions.fixed(1.375f, 0.5625f)).trackRangeChunks(10)
    );

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, Pair<Integer, Integer> colors, SpawnEggFactory eggFactory) {
        EntityType<T> builtEntityType = entityType.build();
        if (eggFactory != null) {
            Item.Settings settings = new FabricItemSettings().maxCount(64).group(Twigs.getItemGroup());
            Item item = eggFactory.apply((EntityType<? extends MobEntity>) builtEntityType, colors.getLeft(), colors.getRight(), settings);
            Registry.register(Registry.ITEM,  new Identifier(Twigs.MOD_ID, "%s_spawn_egg".formatted(id)), item);
        }
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Twigs.MOD_ID, id), builtEntityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, Pair<Integer, Integer> colors) {
        return register(id, entityType, colors, SpawnEggItem::new);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType) {
        return register(id, entityType, null, null);
    }

    private static Pair<Integer, Integer> colors(int primary, int secondary) {
        return new Pair<>(primary, secondary);
    }

    @FunctionalInterface
    public interface SpawnEggFactory { SpawnEggItem apply(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Item.Settings settings); }
}
