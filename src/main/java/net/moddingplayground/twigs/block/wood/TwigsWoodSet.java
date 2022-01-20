package net.moddingplayground.twigs.block.wood;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TableBlock;

import java.util.function.Function;
import java.util.function.Predicate;

import static net.moddingplayground.twigs.block.wood.WoodBlock.*;

public class TwigsWoodSet extends WoodSet {
    private final Function<WoodBlock, Block> tryVanilla = Util.memoize(wood -> {
        Identifier id = new Identifier(wood.format.formatted(this.getId(), wood.getId()));
        return Registry.BLOCK.get(id);
    });

    private final Block table;

    public TwigsWoodSet(String modId, String id, ItemGroup itemGroup, Predicate<WoodBlock> condition, WoodBlock.MaterialSet materialSet, WoodBlock.ColorSet colorSet, WoodBlock.SoundSet soundSet, SaplingGenerator saplingGenerator, PressurePlateBlock.ActivationRule activationRule, boolean flammable, boolean boat) {
        super(modId, id, itemGroup, condition, materialSet, colorSet, soundSet, saplingGenerator, activationRule, flammable, boat);
        this.table = table(this);
    }

    public TwigsWoodSet(String modId, String id, ItemGroup itemGroup, boolean flammable) {
        super(modId, id, itemGroup, flammable);
        this.table = table(this);
    }

    @Override
    public Block get(WoodBlock block) {
        if (this.isVanilla()) return this.tryVanilla.apply(block);
        return super.get(block);
    }

    @Override
    public boolean contains(WoodBlock... blocks) {
        if (this.isVanilla()) for (WoodBlock block : blocks) if (this.get(block) == null) return false;
        return super.contains(blocks);
    }

    public Block getTable() {
        return this.table;
    }

    public static Block table(WoodSet set) {
        return new TableBlock(FabricBlockSettings.copyOf(set.get(PLANKS)).breakInstantly());
    }

    @Override
    public TwigsWoodSet register() {
        if (!this.isVanilla()) super.register();

        register("%s_table".formatted(this.id), this.table);
        if (this.isFlammable()) {
            FlammableBlockRegistry.getDefaultInstance().add(this.table, 5, 20);
            FuelRegistry.INSTANCE.add(this.table, 300);
        }

        return this;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerClient() {
        super.registerClient();

        BlockRenderLayerMap layers = BlockRenderLayerMap.INSTANCE;
        layers.putBlock(this.getTable(), RenderLayer.getCutout());
    }

    private static Block register(String id, Block block, Function<Block, Item> item) {
        Identifier identifier = new Identifier(Twigs.MOD_ID, id);
        if (item != null) Registry.register(Registry.ITEM, identifier, item.apply(block));
        return Registry.register(Registry.BLOCK, identifier, block);
    }

    private static Block register(String id, Block block) {
        return register(id, block, b -> new BlockItem(b, new FabricItemSettings().group(Twigs.ITEM_GROUP)));
    }
}
