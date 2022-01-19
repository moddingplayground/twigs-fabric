package net.moddingplayground.twigs.datagen.impl.provider;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.ItemModelGenerator;
import net.moddingplayground.twigs.datagen.impl.DataType;
import net.moddingplayground.twigs.datagen.impl.generator.model.item.AbstractItemModelGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ItemModelProvider extends AbstractDataProvider<Supplier<AbstractItemModelGenerator>> {
    public ItemModelProvider(DataGenerator root) {
        super(root);
    }

    @Override
    public String getName() {
        return "Item Models";
    }

    @Override
    public String getFolder() {
        return "models/item";
    }

    @Override
    public DataType getDataType() {
        return DataType.ASSET;
    }

    @Override
    public List<Supplier<AbstractItemModelGenerator>> getGenerators() {
        return List.of(ItemModelGenerator::new);
    }

    @Override
    public Map<Identifier, JsonElement> createFileMap() {
        Map<Identifier, JsonElement> map = Maps.newHashMap();
        for (Supplier<AbstractItemModelGenerator> generator : this.getGenerators()) {
            generator.get().accept((id, modelGen) -> {
                if (map.put(id, modelGen.makeJson(id)) != null) throw new IllegalStateException("Duplicate model " + id);
            });
        }
        return map;
    }
}
