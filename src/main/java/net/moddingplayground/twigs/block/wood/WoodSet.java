package net.moddingplayground.twigs.block.wood;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TwigsSignType;
import net.moddingplayground.twigs.item.TwigsBoatItem;
import net.moddingplayground.twigs.registry.TwigsRegistry;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.minecraft.block.PressurePlateBlock.*;
import static net.moddingplayground.twigs.block.wood.WoodBlock.*;

public class WoodSet {
    protected final String modId;
    protected final String id;
    protected final ItemGroup itemGroup;
    protected final boolean flammable;
    protected final Map<WoodBlock, Block> blocks;
    private boolean vanilla;

    protected SignType signType = null;
    protected Item boatItem = null;

    public WoodSet(
        String modId, String id, ItemGroup itemGroup, Predicate<WoodBlock> condition,
        WoodBlock.MaterialSet materialSet, WoodBlock.ColorSet colorSet, WoodBlock.SoundSet soundSet,
        SaplingGenerator saplingGenerator, ActivationRule activationRule, boolean flammable, boolean boat
    ) {
        this.modId = modId;
        this.id = id;
        this.itemGroup = itemGroup;
        this.flammable = flammable;

        this.blocks = new LinkedHashMap<>(); // create to allow janky access from inside WoodBlock
        for (WoodBlock woodBlock : WoodBlock.values()) {
            if (condition.test(woodBlock)) {
                this.blocks.put(woodBlock, woodBlock.blockFactory.create(this, materialSet, colorSet, soundSet, saplingGenerator, activationRule));
            }
        }

        if (boat) this.boatItem = new TwigsBoatItem(this, new FabricItemSettings().maxCount(1).group(Twigs.ITEM_GROUP));
    }

    public WoodSet(String modId, String id, ItemGroup itemGroup, boolean flammable) {
        this(modId, id, itemGroup, w -> false, null, null, null, null, null, flammable, false);
        this.vanilla = true;
    }

    public String getId() {
        return this.id;
    }

    public boolean isFlammable() {
        return this.flammable;
    }

    public boolean isVanilla() {
        return this.vanilla;
    }

    public Optional<Item> getBoatItem() {
        return Optional.ofNullable(this.boatItem);
    }

    public Block get(WoodBlock block) {
        this.containsOrThrow(block);
        return this.blocks.get(block);
    }

    public boolean contains(WoodBlock... blocks) {
        return this.blocks.keySet().containsAll(List.of(blocks));
    }

    public boolean containsOrThrow(WoodBlock... blocks) {
        if (!this.contains(blocks)) throw new IllegalArgumentException("WoodBlocks " + Arrays.toString(blocks) + "are not present in " + this);
        return true;
    }

    public void requireTo(Consumer<WoodSet> action, WoodBlock... required) {
        if (this.contains(required)) action.accept(this);
    }

    public void forEach(BiConsumer<WoodBlock, Block> action) {
        this.blocks.forEach(action);
    }

    public SignType getOrCreateSignType() {
        if (this.signType == null) this.signType = TwigsSignType.register(this.id);
        return this.signType;
    }

    public WoodSet register() {
        this.blocks.forEach((woodBlock, block) -> {
            if (woodBlock.format.isBlank()) return;
            Identifier id = new Identifier(this.modId, woodBlock.format.formatted(this.id, woodBlock.id));
            Registry.register(Registry.BLOCK, id, block);
            if (woodBlock.itemFactory != null) Optional.ofNullable(woodBlock.itemFactory.create(this, block, new FabricItemSettings().group(this.itemGroup)))
                                                       .ifPresent(i -> Registry.register(Registry.ITEM, id, i));
        });

        this.getBoatItem().ifPresent(item -> Registry.register(Registry.ITEM, new Identifier(Twigs.MOD_ID, "%s_boat".formatted(this.id)), item));

        if (this.isFlammable()) {
            FlammableBlockRegistry flamReg = FlammableBlockRegistry.getDefaultInstance();
            FuelRegistry fuelReg = FuelRegistry.INSTANCE;
            requireTo(s -> flamReg.add(s.get(PLANKS), 5, 20), PLANKS);
            requireTo(s -> flamReg.add(s.get(SLAB), 5, 20), SLAB);
            requireTo(s -> flamReg.add(s.get(STAIRS), 5, 20), STAIRS);
            requireTo(s -> flamReg.add(s.get(LOG), 5, 5), LOG);
            requireTo(s -> flamReg.add(s.get(STRIPPED_LOG), 5, 5), STRIPPED_LOG);
            requireTo(s -> flamReg.add(s.get(STRIPPED_WOOD), 5, 5), STRIPPED_WOOD);
            requireTo(s -> flamReg.add(s.get(WOOD), 5, 5), WOOD);
            requireTo(s -> flamReg.add(s.get(LEAVES), 30, 60), LEAVES);

            requireTo(s -> {
                Block block = s.get(FENCE);
                flamReg.add(block, 5, 20);
                fuelReg.add(block, 300);
            }, FENCE);

            requireTo(s -> {
                Block block = s.get(FENCE_GATE);
                flamReg.add(block, 5, 20);
                fuelReg.add(block, 300);
            }, FENCE_GATE);

            requireTo(s -> {
                Block block = s.get(SIGN);
                Item item = block.asItem();
                fuelReg.add(item, 200);
            }, SIGN);
        }

        return Registry.register(TwigsRegistry.WOOD, new Identifier(Twigs.MOD_ID, this.id), this);
    }

    @Environment(EnvType.CLIENT)
    public void registerClient() {
        if (!this.isVanilla()) {
            BlockRenderLayerMap layers = BlockRenderLayerMap.INSTANCE;
            requireTo(s -> layers.putBlock(s.get(DOOR), RenderLayer.getCutout()), DOOR);
            requireTo(s -> layers.putBlock(s.get(TRAPDOOR), RenderLayer.getCutout()), TRAPDOOR);
        }
    }

    @Override
    public String toString() {
        return "WoodSet{" + "modId='" + modId + '\'' + ", id='" + id + '\'' + '}';
    }
}
