package kr.hahaha98757.zombiesaddon.events;

import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SoundEvent extends Event {
    private final S29PacketSoundEffect soundEffect;

    public SoundEvent(S29PacketSoundEffect soundEffect) {
        this.soundEffect = soundEffect;
    }

    public S29PacketSoundEffect getSoundEffect() {
        return this.soundEffect;
    }
}