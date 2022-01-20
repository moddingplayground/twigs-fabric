package net.moddingplayground.twigs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.registry.TwigsRegistry;

public class TwigsBoatEntity extends BoatEntity {
    public static final TrackedData<String> WOOD_TYPE = DataTracker.registerData(TwigsBoatEntity.class, TrackedDataHandlerRegistry.STRING);
    public static final String DEFAULT_WOOD_TYPE = TwigsRegistry.WOOD.getDefaultId().toString();

    public TwigsBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public TwigsBoatEntity(World world, double x, double y, double z) {
        this(TwigsEntityType.BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WOOD_TYPE, DEFAULT_WOOD_TYPE);
    }

    public String getWoodType() {
        return this.dataTracker.get(WOOD_TYPE);
    }

    public Identifier getWoodTypeId() {
        return Identifier.tryParse(this.getWoodType());
    }

    public void setWoodType(String type) {
        this.dataTracker.set(WOOD_TYPE, type);
    }

    public void setWoodType(WoodSet wood) {
        this.setWoodType(TwigsRegistry.WOOD.getId(wood).toString());
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString("Type", this.getWoodType());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Type", 8)) this.setWoodType(nbt.getString("Type"));
        if (!TwigsRegistry.WOOD.containsId(this.getWoodTypeId())) this.setWoodType(DEFAULT_WOOD_TYPE);
    }

    @Override
    public Item asItem() {
        WoodSet wood = TwigsRegistry.WOOD.get(this.getWoodTypeId());
        return wood.getBoatItem().orElseGet(() -> {
            this.setWoodType(DEFAULT_WOOD_TYPE);
            return this.asItem();
        });
    }

    @Override
    public void setBoatType(Type type) {
        throw new AssertionError("Tried to set vanilla boat type on Twigs boat");
    }

    @Override
    public Type getBoatType() {
        throw new AssertionError("Tried to get vanilla boat type on Twigs boat");
    }
}
