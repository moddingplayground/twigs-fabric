package net.moddingplayground.twigs.block.wood;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.block.TwigsSignType;

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
    private final boolean flammable;
    protected final Map<WoodBlock, Block> blocks;

    protected SignType signType = null;

    public WoodSet(
        String modId, String id, ItemGroup itemGroup, Predicate<WoodBlock> condition,
        WoodBlock.MaterialSet materialSet, WoodBlock.ColorSet colorSet, WoodBlock.SoundSet soundSet,
        SaplingGenerator saplingGenerator, ActivationRule activationRule, boolean flammable
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
    }

    public String getId() {
        return id;
    }

    public boolean isFlammable() {
        return this.flammable;
    }

    public Block get(WoodBlock block) {
        return this.blocks.get(block);
    }

    public boolean contains(WoodBlock... wood) {
        return this.blocks.keySet().containsAll(List.of(wood));
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

        return this;
    }
}
