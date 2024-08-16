package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.utils.GameEnd;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientCrash {
	public static boolean update = false;
	public static boolean unlegit = false;

	private short delay = 0;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws GameEnd {
		if (event.phase != TickEvent.Phase.END) return;

		delay++;

		switch (delay) {
			case 100:
				Utils.addChat("\u00A7cEnds after 5 seconds...");
				break;
			case 120:
				Utils.addChat("\u00A7cEnds after 4 seconds...");
				break;
			case 140:
				Utils.addChat("\u00A7c\u00A7lEnds after 3 seconds...");
				break;
			case 160:
				Utils.addChat("\u00A7c\u00A7lEnds after 2 seconds...");
				break;
			case 180:
				Utils.addChat("\u00A7c\u00A7lEnds after 1 second...");
				break;
			case 200:
				Utils.addChat("\u00A7c\u00A7lThe game ends...");

				if (update)
					throw new GameEnd("Update Zombies Addon. URL: https://github.com/hahaha98757/zombies-addon1.12.2/releases");
				if (unlegit)
                    throw new GameEnd("Detectd Unlegit Mods. Remove ZombiesSatellite, Zombies Explorer, or TeammatesOutline.");

				throw new GameEnd("Unknown Error.");
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