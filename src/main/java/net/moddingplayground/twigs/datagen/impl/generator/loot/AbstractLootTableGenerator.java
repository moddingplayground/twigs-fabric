package net.moddingplayground.twigs.datagen.impl.generator.loot;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.datagen.impl.ObjectLootTableAccess;
import net.moddingplayground.twigs.datagen.impl.generator.AbstractGenerator;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public abstract class AbstractLootTableGenerator<T> extends AbstractGenerator<Identifier, LootTable.Builder> {
    public AbstractLootTableGenerator(String modId) {
        super(modId);
    }

    public abstract Registry<T> getRegistry();

    @SuppressWarnings("ConstantConditions")
    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        this.generate();

        Set<Identifier> set = Sets.newHashSet();
        Registry<T> registry = this.getRegistry();
        Iterable<T> objects = registry
            .stream()
            .filter(obj -> registry.getId(obj).getNamespace().equals(this.modId))
            ::iterator;

        for (T obj : objects) {
            Identifier id = ((ObjectLootTableAccess) obj).access_getLootTableId();
            this.testObject(id, obj);
            if (id != LootTables.EMPTY && set.add(id)) {
                LootTable.Builder builder = this.map.remove(id);
                if (builder == null) {
                    throw new IllegalStateException(
                        String.format(
                            "Missing loottable '%s' for '%s'", id,
                            registry.getId(obj)
                        )
                    );
                }

                biConsumer.accept(id, builder);
            }
        }

        this.map.forEach(biConsumer);
    }

    public void testObject(Identifier id, T obj) {}

    public void add(T obj, LootTable.Builder lootTable) {
        this.add(((ObjectLootTableAccess) obj).access_getLootTableId(), lootTable);
    }

    public void add(T obj, Function<T, LootTable.Builder> function) {
        this.add(obj, function.apply(obj));
    }

    public LootTable.Builder dropsNothing() {
        return LootTable.builder();
    }

    public LootTable.Builder dropsConditionally(ItemConvertible drop, LootCondition.Builder condition, LootPoolEntry.Builder<?> orElse) {
        return LootTable.builder().pool(
            pool().with(ItemEntry.builder(drop).conditionally(condition).alternatively(orElse))
        );
    }

    public static ConditionalLootFunction.Builder<?> setCount(LootNumberProvider range) {
        return SetCountLootFunction.builder(range);
    }

    public static LootPool.Builder pool(LootNumberProvider rolls) {
        return LootPool.builder().rolls(rolls);
    }

    public static LootPool.Builder pool() {
        return pool(count(1));
    }

    public static LootNumberProvider count(int count) {
        return ConstantLootNumberProvider.create(count);
    }

    public static LootNumberProvider countRandom(float min, float max) {
        return UniformLootNumberProvider.create(min, max);
    }

    public static LootNumberProvider countBiased(int n, float p) {
        return BinomialLootNumberProvider.create(n, p);
    }

    public static <T extends Comparable<T>> StatePredicate.Builder stateProp(Property<T> prop, T val) {
        return StatePredicate.Builder.create().exactMatch(prop, prop.name(val));
    }

    public static <T extends Comparable<T>> BlockStatePropertyLootCondition.Builder stateCond(Block block, Property<T> prop, T val) {
        return BlockStatePropertyLootCondition.builder(block).properties(stateProp(prop, val));
    }

    public static BoundedIntUnaryOperator atLeast(int min) {
        return BoundedIntUnaryOperator.createMin(min);
    }

    public static BoundedIntUnaryOperator atMost(int max) {
        return BoundedIntUnaryOperator.createMax(max);
    }

    public static BoundedIntUnaryOperator minMax(int min, int max) {
        return BoundedIntUnaryOperator.create(min, max);
    }

    public static RandomChanceLootCondition.Builder chance(float chance) {
        return RandomChanceLootCondition.builder(chance);
    }
}
