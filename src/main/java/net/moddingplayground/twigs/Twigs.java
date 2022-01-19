package net.moddingplayground.twigs;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.block.TwigsBlocks;
import net.moddingplayground.twigs.block.TwigsSignType;
import net.moddingplayground.twigs.data.family.TwigsBlockFamilies;
import net.moddingplayground.twigs.world.gen.feature.TwigsConfiguredFeatures;
import net.moddingplayground.twigs.world.gen.feature.TwigsPlacedFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Twigs implements ModInitializer {
    public static final String MOD_ID   = "twigs";
    public static final String MOD_NAME = "Twigs";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(TwigsBlocks.TWIG));

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onInitialize() {
        LOGGER.info("Initializing {}", MOD_NAME);

        Reflection.initialize(
            TwigsSignType.class,
            TwigsBlocks.class,

            TwigsBlockFamilies.class,

            TwigsConfiguredFeatures.class,
            TwigsPlacedFeatures.class
        );

        LOGGER.info("Initialized {}", MOD_NAME);
    }
}
