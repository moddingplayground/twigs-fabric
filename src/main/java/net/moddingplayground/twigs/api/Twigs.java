package net.moddingplayground.twigs.api;

import net.minecraft.item.ItemGroup;
import net.moddingplayground.twigs.impl.TwigsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entrypoint helper for Twigs.
 */
public interface Twigs {
    String MOD_ID   = "twigs";
    String MOD_NAME = "Twigs";
    Logger LOGGER   = LoggerFactory.getLogger(MOD_ID);

    static ItemGroup getItemGroup() {
        return TwigsImpl.getInstance().getItemGroup();
    }
}
