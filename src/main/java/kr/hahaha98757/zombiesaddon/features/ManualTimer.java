package kr.hahaha98757.zombiesaddon.features;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ManualTimer {
    public static final ManualTimer INS = new ManualTimer();
    public static final ManualTimer MAX = new ManualTimer();
    public static final ManualTimer SS = new ManualTimer();
    public static final ManualTimer DG = new ManualTimer();
    public static final ManualTimer CAR = new ManualTimer();
    public static final ManualTimer BG = new ManualTimer();

    public short timer = 1200;

    public void runTimer() {
        timer = 1200;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            timer--;
            if (timer <= 0) MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}