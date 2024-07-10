// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.listeners.sla;

import kr.hahaha98757.zombiesaddon.data.Room;
import kr.hahaha98757.zombiesaddon.data.Window;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

import java.util.Optional;

public class SLAListener {
	public static SLAListener instance = null;
	private final Room[] rooms;

	public SLAListener(int map) {
		switch (map) {
		case 1:
			this.rooms = Room.getDE();
			break;
		case 2:
			this.rooms = Room.getBB();
			break;
		case 3:
			this.rooms = Room.getAA();
			break;
		case 4:
			this.rooms = Room.getPR();
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + map);
		}
	}

	public void refreshActives() {
		final EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		final double[] playerCoords = { (player.posX), player.posY, player.posZ };
		for (Room room : rooms) {
			room.resetActiveWindowCount();
			for (Window window : room.getWindows()) {
				double distanceDoubledThenSquared = 0;
				for (int i = 0; i < 3; i++) {
					distanceDoubledThenSquared += ((playerCoords[i] * 2 - window.getXYZ()[i])
							* (playerCoords[i] * 2 - window.getXYZ()[i]));
				}

				if (GameDetect.getMap() == 4) {
					if (GameDetect.getArea().equals(room.getName().replaceAll("[^A-Za-z]", ""))) {
						window.setActive(true);
						room.increaseActiveWindowCount();
					} else {
						window.setActive(false);
					}
				} else {
					if (distanceDoubledThenSquared < 10000) {
						window.setActive(true);
						room.increaseActiveWindowCount();
					} else {
						window.setActive(false);
					}
				}
			}
		}
	}

	public static Optional<SLAListener> getInstance() {
		return Optional.ofNullable(instance);
	}

	public static void drop() {
		instance = null;
	}

	public Room[] getRooms() {
		return rooms;
	}
}
