// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.handler;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.Room;
import kr.hahaha98757.zombiesaddon.listeners.sla.SLAListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class RenderGameOverlayHandler {
	private final FontRenderer fontRenderer;

	public RenderGameOverlayHandler() {
		this.fontRenderer = Objects.requireNonNull(Minecraft.getMinecraft().fontRendererObj,
				"FontRenderer must not be null!");
	}

	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
		if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
			return;
		}
		SLAListener.getInstance().ifPresent(sla -> {
			sla.refreshActives();
			renderSla(sla.getRooms());
		});

	}

	private void renderSla(Room[] rooms) {
		int y = 0;
		for (Room room : rooms) {
			if (!ZombiesAddonConfig.showInactiveWindows && room.getActiveWindowCount() == 0) {
				continue;
			}
			fontRenderer.drawStringWithShadow(room.getSlaString(), 1, 1 + y * fontRenderer.FONT_HEIGHT, 0xFFFFFF);
			y++;
		}
	}
}
