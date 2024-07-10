package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.utils.UpdateRequired;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientCrash {

	public static boolean update = false;

	private int delay = 0;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws UpdateRequired {
		if (event.phase == TickEvent.Phase.START) {
			delay++;

			switch (delay) {
			case 100:
				addChat("\u00A7cEnds after 5 seconds...");
				break;
			case 120:
				addChat("\u00A7cEnds after 4 seconds...");
				break;
			case 140:
				addChat("\u00A7c\u00A7lEnds after 3 seconds...");
				break;
			case 160:
				addChat("\u00A7c\u00A7lEnds after 2 seconds...");
				break;
			case 180:
				addChat("\u00A7c\u00A7lEnds after 1 second...");
				break;
			default:
				break;
			}

			if (delay == 200) {
				addChat("\u00A7c\u00A7lThe game ends...");
				throw new UpdateRequired(
						"Update Zombies Addon. URL: https://github.com/hahaha98757/ZombiesAddon/releases");
			}
		}
	}

	private void addChat(String str) {
		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(str));
	}

	public static void update() {
		update = true;
	}
}
