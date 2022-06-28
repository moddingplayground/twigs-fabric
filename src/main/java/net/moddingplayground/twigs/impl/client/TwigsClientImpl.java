package net.moddingplayground.twigs.impl.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.moddingplayground.frame.api.util.InitializationLogger;
import net.moddingplayground.twigs.api.Twigs;

@Environment(EnvType.CLIENT)
public final class TwigsClientImpl implements Twigs, ClientModInitializer {
    private final InitializationLogger initializer;

    public TwigsClientImpl() {
        this.initializer = new InitializationLogger(LOGGER, MOD_NAME, EnvType.CLIENT);
    }

    @Override
    public void onInitializeClient() {
        this.initializer.start();

        //

        this.initializer.finish();
    }
}
