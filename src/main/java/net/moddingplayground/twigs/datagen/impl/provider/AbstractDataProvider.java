package net.moddingplayground.twigs.datagen.impl.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.DataType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public abstract class AbstractDataProvider<T> implements DataProvider {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    protected final DataGenerator root;

    public AbstractDataProvider(DataGenerator root) {
        this.root = root;
    }

    @Override public abstract String getName();
    public abstract String getFolder();
    public abstract DataType getDataType();

    public abstract List<T> getGenerators();
    public abstract Map<Identifier, JsonElement> createFileMap();

    @Override
    public void run(DataCache cache) {
        this.write(cache, this.createFileMap());
    }

    public void write(DataCache cache, Map<Identifier, JsonElement> map, BiFunction<Path, Identifier, Path> pathCreator) {
        Path path = this.root.getOutput();
        map.forEach((id, json) -> {
            Path output = pathCreator.apply(path, id);
            try {
                this.writeToPath(GSON, cache, json, output);
            } catch (IOException e) {
                LOGGER.error("Couldn't save {} {}", this.getFolder(), output, e);
            }
        });
    }

    public void writeToPath(Gson gson, DataCache cache, JsonElement output, Path path) throws IOException {
        String string = gson.toJson(output) + "\n";
        String string2 = SHA1.hashUnencodedChars(string).toString();
        if (!Objects.equals(cache.getOldSha1(path), string2) || !Files.exists(path)) {
            Files.createDirectories(path.getParent());
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)){
                bufferedWriter.write(string);
            }
        }
        cache.updateSha1(path, string2);
    }

    public void write(DataCache cache, Map<Identifier, JsonElement> map) {
        this.write(cache, map, this::getOutput);
    }

    public Path getOutput(Path root, Identifier id, DataType type, String folder) {
        return root.resolve(String.format("%s/%s/%s/%s.json", type.getId(), id.getNamespace(), folder, id.getPath()));
    }

    public Path getOutput(Path root, Identifier id, String folder) {
        return this.getOutput(root, id, this.getDataType(), folder);
    }

    public Path getOutput(Path root, Identifier id, DataType type) {
        return this.getOutput(root, id, type, this.getFolder());
    }

    public Path getOutput(Path root, Identifier id) {
        return this.getOutput(root, id, this.getDataType(), this.getFolder());
    }
}
