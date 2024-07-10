//Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.commands.CommandZSV;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZSVListener {
	public static final List<String> START_LINES = new ArrayList();
	public static final List<String> ACTUAL_LINES = new ArrayList();
	public static int currentLine = 0;
	public static int linesOfView = 1;
	public static int width = 0;
	public static int height = 0;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Keybinds.scrollDown.isPressed()) {
			if (currentLine + 1 < START_LINES.size()) {
			}

			++currentLine;
			recalcActualLines();
		} else if (Keybinds.scrollUp.isPressed()) {
			if (currentLine > 0) {
				--currentLine;
			}

			recalcActualLines();
		}
	}

	public static void recalcActualLines() {
		height = 0;
		ZSVListener.width = 20;
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		ACTUAL_LINES.clear();

		for (int j = currentLine; j < currentLine + linesOfView; ++j) {
			if (j < START_LINES.size()) {
				String line = START_LINES.get(j);
				if (line.isEmpty()) {
					ACTUAL_LINES.add("$CONTROL EMPTY$");
					height += 10;
				} else {
					line = WordUtils.wrap(line, 55);
					String[] var3 = line.split("\n");
					int var4 = var3.length;

					for (int var5 = 0; var5 < var4; ++var5) {
						String str = var3[var5];
						str = str.replace("\r", "");
						ACTUAL_LINES.add(str);
						int width = fr.getStringWidth(str);
						if (width > ZSVListener.width) {
							ZSVListener.width = width;
						}

						height += 10;
					}

					ACTUAL_LINES.add("$HEY CONTROL$");
					height += 5;
				}
			}
		}
	}

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (!CommandZSV.ZSV) {
			return;
		}
		if (event.type == ElementType.EXPERIENCE) {
			drawRect(3, 3, 5 + width, 5 + height, 1431655765);
			int y = -5;
			Iterator var3 = ACTUAL_LINES.iterator();

			while (var3.hasNext()) {
				String str = (String) var3.next();
				if (str.equalsIgnoreCase("$HEY CONTROL$")) {
					y += 5;
				} else if (str.equalsIgnoreCase("$CONTROL EMPTY$")) {
					y += 10;
				} else {
					y += 10;
					this.drawString(str, y, 16777215);
				}
			}
		}
	}

	public void drawString(String str, int y, int color) {
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		fr.drawStringWithShadow(str, 5.0F, (float) y, color);
	}

	public static void drawRect(int left, int top, int right, int bottom, int color) {
		int temp;
		if (left < right) {
			temp = left;
			left = right;
			right = temp;
		}

		if (top < bottom) {
			temp = top;
			top = bottom;
			bottom = temp;
		}

		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;
		float alpha = (float) (color >> 24 & 255) / 255.0F;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer buffer = tessellator.getWorldRenderer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.color(red, green, blue, alpha);
		buffer.begin(7, DefaultVertexFormats.POSITION);
		buffer.pos(left, bottom, 0.0).endVertex();
		buffer.pos(right, bottom, 0.0).endVertex();
		buffer.pos(right, top, 0.0).endVertex();
		buffer.pos(left, top, 0.0).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
}
