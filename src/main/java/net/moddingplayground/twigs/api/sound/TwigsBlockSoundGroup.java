package net.moddingplayground.twigs.api.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class TwigsBlockSoundGroup extends BlockSoundGroup {
    public static final BlockSoundGroup TWIG = copy(WOOD);
    public static final BlockSoundGroup PEBBLE = copy(STONE);
    public static final BlockSoundGroup SEA_SHELL = copy(BONE);
    public static final BlockSoundGroup PAPER_LANTERN = copy(BAMBOO);
    public static final BlockSoundGroup BAMBOO_LEAVES = copy(AZALEA_LEAVES);
    public static final BlockSoundGroup STRIPPED_BAMBOO = copy(SCAFFOLDING);
    public static final BlockSoundGroup LAMP = copy(LANTERN);
    public static final BlockSoundGroup ROCKY_DIRT = copy(TUFF);
    public static final BlockSoundGroup PETRIFIED_LICHEN = copy(DEEPSLATE);
    public static final BlockSoundGroup SCULK_PASSENGER = copy(SCULK);
    public static final BlockSoundGroup ENDER_MESH = builder(SCULK_VEIN).stepSound(SoundEvents.BLOCK_STONE_STEP)
                                                                        .fallSound(SoundEvents.BLOCK_STONE_FALL)
                                                                        .build();

    public TwigsBlockSoundGroup(float volume, float pitch, SoundEvent breakSound, SoundEvent stepSound, SoundEvent placeSound, SoundEvent hitSound, SoundEvent fallSound) {
        super(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
    }

    public static TwigsBlockSoundGroup.Builder builder(BlockSoundGroup parent) {
        return new TwigsBlockSoundGroup.Builder(parent);
    }

    public static TwigsBlockSoundGroup copy(BlockSoundGroup group) {
        return builder(group).build();
    }

    public static class Builder {
        private float volume, pitch;
        private SoundEvent breakSound, stepSound, placeSound, hitSound, fallSound;

        protected Builder(BlockSoundGroup parent) {
            this.volume = parent.getVolume();
            this.pitch = parent.getPitch();
            this.breakSound = parent.getBreakSound();
            this.stepSound = parent.getStepSound();
            this.placeSound = parent.getPlaceSound();
            this.hitSound = parent.getHitSound();
            this.fallSound = parent.getFallSound();
        }

        public Builder volume(float volume) {
            this.volume = volume;
            return this;
        }

        public Builder pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public Builder breakSound(SoundEvent breakSound) {
            this.breakSound = breakSound;
            return this;
        }

        public Builder stepSound(SoundEvent stepSound) {
            this.stepSound = stepSound;
            return this;
        }

        public Builder placeSound(SoundEvent placeSound) {
            this.placeSound = placeSound;
            return this;
        }

        public Builder hitSound(SoundEvent hitSound) {
            this.hitSound = hitSound;
            return this;
        }

        public Builder fallSound(SoundEvent fallSound) {
            this.fallSound = fallSound;
            return this;
        }

        public TwigsBlockSoundGroup build() {
            return new TwigsBlockSoundGroup(this.volume, this.pitch, this.breakSound, this.stepSound, this.placeSound, this.hitSound, this.fallSound);
        }
    }
}
