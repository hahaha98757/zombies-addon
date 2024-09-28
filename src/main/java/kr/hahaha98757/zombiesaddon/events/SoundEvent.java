package kr.hahaha98757.zombiesaddon.events;

import net.minecraft.client.audio.ISound;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SoundEvent extends Event {
    private final ISound sound;

    public SoundEvent(ISound sound) {
        this.sound = sound;
    }

    public String getSoundName() {
        try {
            return this.sound.getSoundLocation().toString().split(":")[1];
        } catch (Exception e) {
            return "";
        }
    }
}