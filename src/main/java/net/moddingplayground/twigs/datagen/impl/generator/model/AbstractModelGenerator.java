package net.moddingplayground.twigs.datagen.impl.generator.model;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.datagen.impl.generator.AbstractGenerator;

@SuppressWarnings("ConstantConditions")
public abstract class AbstractModelGenerator<T, U> extends AbstractGenerator<Identifier, U> {
    public AbstractModelGenerator(String modId) {
        super(modId);
    }

    public abstract Registry<T> getRegistry();

    public Identifier name(T object, String nameFormat) {
        Identifier id = this.getRegistry().getId(object);
        return new Identifier(id.getNamespace(), String.format(nameFormat, id.getPath()));
    }

    public Identifier name(T object, String nameFormat, String omitSuffix) {
        Identifier id = this.getRegistry().getId(object);

        String path = id.getPath();
        if (path.endsWith(omitSuffix)) {
            path = path.substring(0, path.length() - omitSuffix.length());
        }

        return new Identifier(id.getNamespace(), String.format(nameFormat, path));
    }

    public Identifier name(T object, String nameFormat, String pattern, String reformat) {
        Identifier id = this.getRegistry().getId(object);

        String path = id.getPath();
        path = path.replaceAll(pattern, reformat);

        return new Identifier(id.getNamespace(), String.format(nameFormat, path));
    }

    public Identifier name(T object) {
        return name(object, "block/%s");
    }

    public Identifier vanilla(T object, String nameFormat) {
        return new Identifier(name(object, nameFormat).getPath());
    }

    public Identifier vanilla(T object, String nameFormat, String omitSuffix) {
        return new Identifier(name(object, nameFormat, omitSuffix).getPath());
    }

    public Identifier vanilla(T object, String nameFormat, String pattern, String reformat) {
        return new Identifier(name(object, nameFormat, pattern, reformat).getPath());
    }

    public Identifier vanilla(T object) {
        return new Identifier(name(object).getPath());
    }
}
