package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.LootCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@SuppressWarnings("unused")
@Mixin(BlockLootTableGenerator.class)
public interface BlockLootTableGeneratorAccessor {
    @Accessor("EXPLOSION_IMMUNE")
    static Set<Item> getExplosionImmune() {
        throw new AssertionError();
    }

    @Accessor("SAPLING_DROP_CHANCE")
    static float[] getSaplingDropChance() {
        throw new AssertionError();
    }

    @Accessor("JUNGLE_SAPLING_DROP_CHANCE")
    static float[] getJungleSaplingDropChance() {
        throw new AssertionError();
    }

    @Accessor("WITHOUT_SILK_TOUCH_NOR_SHEARS")
    static LootCondition.Builder getWithoutSilkTouchNorShears() {
        throw new AssertionError();
    }

    @Accessor("WITH_SILK_TOUCH_OR_SHEARS")
    static LootCondition.Builder getWithSilkTouchOrShears() {
        throw new AssertionError();
    }

    @Accessor("WITH_SHEARS")
    static LootCondition.Builder getWithShears() {
        throw new AssertionError();
    }

    @Accessor("WITH_SILK_TOUCH")
    static LootCondition.Builder getWithSilkTouch() {
        throw new AssertionError();
    }

    @Accessor("WITHOUT_SILK_TOUCH")
    static LootCondition.Builder getWithoutSilkTouch() {
        throw new AssertionError();
    }
}
