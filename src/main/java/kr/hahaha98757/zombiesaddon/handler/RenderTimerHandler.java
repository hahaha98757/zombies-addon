//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.handler;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class RenderTimerHandler {

	private final Minecraft minecraft;

	private final FontRenderer fontRenderer;

	private InternalSplitter internalSplitter;

	private final int color;

	public static String timer;

	public RenderTimerHandler(Minecraft minecraft, FontRenderer fontRenderer, int color) {
		this.minecraft = Objects.requireNonNull(minecraft, "minecraft");
		this.fontRenderer = Objects.requireNonNull(fontRenderer, "fontRenderer");
		this.color = color;
	}

	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (!GameDetect.isZombiesGame()) {
			return;
		}

		if (internalSplitter == null) {
			return;
		}
		if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
			long millis = internalSplitter.getMillis();
			long minutesPart = millis / 60000;
			long secondsPart = (millis % 60000) / 1000;
			long tenthSecondsPart = (millis % 1000) / 100;
			String time = String.format("%d:%02d.%d", minutesPart, secondsPart, tenthSecondsPart);
			int width = fontRenderer.getStringWidth(time);
			ScaledResolution scaledResolution = new ScaledResolution(minecraft);
			int screenWidth = scaledResolution.getScaledWidth();
			int screenHeight = scaledResolution.getScaledHeight();
			timer = time;
			if (ZombiesAddonConfig.toggleAutoSplits) {
				fontRenderer.drawStringWithShadow(time, screenWidth - width, screenHeight - fontRenderer.FONT_HEIGHT,
						color);
			}
		}
	}

	public void setSplitter(InternalSplitter internalSplitter) {
		this.internalSplitter = Objects.requireNonNull(internalSplitter, "internalSplitter");
	}

}