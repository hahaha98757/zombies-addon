package kr.hahaha98757.zombiesaddon.hudposition;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DelayedTask {
    private int counter;
    private final Runnable run;

    public DelayedTask(Runnable run) {
        this(run, 2);
    }

    public DelayedTask(Runnable run, int ticks) {
        this.counter = ticks;
        this.run = run;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        if (counter <= 0) {
            MinecraftForge.EVENT_BUS.unregister(this);
            run.run();
        }

        --counter;
    }
}