package net.moddingplayground.twigs.datagen.impl.generator.tag;

import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.datagen.impl.generator.AbstractGenerator;

@SuppressWarnings("unused")
public abstract class AbstractTagGenerator<T> extends AbstractGenerator<Identifier, TagFactory<T>> {
    protected final Registry<T> registry;

    public AbstractTagGenerator(String modId, Registry<T> registry) {
        super(modId);
        this.registry = registry;
    }

    @SafeVarargs
    public final TagFactory<T> add(Tag<T> tag, T... objects) {
        return this.getOrCreateFactory(tag).add(objects);
    }

    @SafeVarargs
    public final TagFactory<T> add(Tag<T> tag, Tag<T>... tags) {
        return this.getOrCreateFactory(tag).add(tags);
    }

    public TagFactory<T> getOrCreateFactory(Tag<T> tag) {
        if (tag instanceof Tag.Identified identified) {
            return this.getOrCreateFactory(identified.getId());
        } else {
            throw new RuntimeException("Cannot identify tag " + tag);
        }
    }

    public TagFactory<T> getOrCreateFactory(Identifier id) {
        return this.map.computeIfAbsent(this.getId(id), i -> new TagFactory<>(this.registry::getId));
    }

    public Identifier getId(Identifier id) {
        Identifier registryId = this.registry.getKey().getValue();
        return new Identifier(id.getNamespace(), String.format("%ss/%s", registryId.getPath(), id.getPath()));
    }
}
