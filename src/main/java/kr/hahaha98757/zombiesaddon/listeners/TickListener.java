//Code in Auto Rejoin by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.utils.Callback;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickListener {
	private final Callback callback;
	int tick = 0;

	public TickListener(Callback callback) {
		this.callback = callback;
	}

	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event) {
		if (this.tick == 100) {
			this.callback.run();
			MinecraftForge.EVENT_BUS.unregister(this);
		} else {
			++this.tick;
		}

	}
}
