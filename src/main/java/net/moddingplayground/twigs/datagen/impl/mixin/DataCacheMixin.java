package net.moddingplayground.twigs.datagen.impl.mixin;

import com.google.common.collect.Sets;
import net.minecraft.data.DataCache;
import net.moddingplayground.twigs.datagen.impl.DataCacheAccess;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@SuppressWarnings({ "UnusedMixin", "MismatchedQueryAndUpdateOfCollection", "unused" })
@Mixin(DataCache.class)
public abstract class DataCacheMixin implements DataCacheAccess {
    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private Path root;
    @Shadow @Final private Map<Path, String> oldSha1;
    @Shadow @Final private Map<Path, String> newSha1;
    @Shadow @Final private Set<Path> ignores;

    private final Set<Path> _copyOutputs = Sets.newHashSet();
    private final Set<Path> _unchangedPaths = Sets.newHashSet();

    @Shadow
    protected abstract Stream<Path> files();

    @Inject(method = "write", at = @At("RETURN"))
    private void onWrite(CallbackInfo ci) {
        for (Path out : _copyOutputs) {
            copyAll(out);
        }
    }

    @Inject(method = "updateSha1", at = @At("HEAD"))
    private void onUpdateSha1(Path path, String sha1, CallbackInfo ci) {
        if (Objects.equals(oldSha1.get(path), sha1)) {
            _unchangedPaths.add(path);
        }
    }

    private void copyAll(Path out) {
        files().forEach(path -> {
            Path rel = root.relativize(path);
            Path copyTo = out.resolve(rel);
            if (newSha1.containsKey(path)) {
                try {
                    Files.createDirectories(copyTo.getParent());
                    Files.copy(path, copyTo, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException err) {
                    LOGGER.warn("Unable to copy: {} to {} ({})", path, copyTo, err.toString());
                }
            }
        });
    }

    @Override
    public void addCopyPath(Path out) {
        _copyOutputs.add(out);
    }

    @Override
    public void keepFiles(Path... keep) {
        ignores.addAll(Arrays.asList(keep));
    }
}
