package kr.hahaha98757.zombiesaddon.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientCrash {
	public static boolean update = false;
	public static boolean unlegit = false;

	private short delay = 0;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END) return;

		delay++;

		switch (delay) {
			case 100:
				Utils.addChat("§cEnds after 5 seconds...");
				break;
			case 120:
				Utils.addChat("§cEnds after 4 seconds...");
				break;
			case 140:
				Utils.addChat("§c§lEnds after 3 seconds...");
				break;
			case 160:
				Utils.addChat("§c§lEnds after 2 seconds...");
				break;
			case 180:
				Utils.addChat("§c§lEnds after 1 second...");
				break;
			case 200:
				Utils.addChat("§c§lThe game ends...");

				if (update)
					throw new RuntimeException("Update Zombies Addon. URL: https://github.com/hahaha98757/zombies-addon/releases");
				if (unlegit)
                    throw new RuntimeException("Detected Unlegit Mods. Remove ZombiesSatellite, Zombies Explorer, TeammatesOutline, or ZombiesHelper.");

				throw new RuntimeException("Unknown Error.");
			default:
				break;
		}
	}

	public static void setUpdate() {
		update = true;
	}

	public static void setUnlegit() {
		unlegit = true;
	}
}