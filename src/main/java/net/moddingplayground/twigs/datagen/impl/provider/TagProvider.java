package net.moddingplayground.twigs.datagen.impl.provider;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.BiomeTagGenerator;
import net.moddingplayground.twigs.datagen.BlockTagGenerator;
import net.moddingplayground.twigs.datagen.EntityTypeTagGenerator;
import net.moddingplayground.twigs.datagen.ItemTagGenerator;
import net.moddingplayground.twigs.datagen.impl.DataType;
import net.moddingplayground.twigs.datagen.impl.generator.tag.AbstractTagGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TagProvider extends AbstractDataProvider<Supplier<AbstractTagGenerator<?>>> {
    public TagProvider(DataGenerator root) {
        super(root);
    }

    @Override
    public String getName() {
        return "Tag";
    }

    @Override
    public String getFolder() {
        return "tags";
    }

    @Override
    public DataType getDataType() {
        return DataType.DATA;
    }

    @Override
    public List<Supplier<AbstractTagGenerator<?>>> getGenerators() {
        return List.of(BlockTagGenerator::new, ItemTagGenerator::new, EntityTypeTagGenerator::new, BiomeTagGenerator::new);
    }

    @Override
    public Map<Identifier, JsonElement> createFileMap() {
        Map<Identifier, JsonElement> map = Maps.newHashMap();
        for (Supplier<AbstractTagGenerator<?>> generator : this.getGenerators()) {
            generator.get().accept((id, factory) -> {
                if (map.put(id, factory.createJson()) != null) {
                    throw new IllegalStateException("Duplicate tag " + id);
                }
            });
        }
        return map;
    }
}
