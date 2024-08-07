//Code in Cornering by syeyoung

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class BlockAlarmListener {

	@SubscribeEvent
	public void blockAlarm(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) return;

		if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

		if (!EventListener.blockAlarm) return;

		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

		byte[] rdq = Utils.getRevDeadQuit();

		if ((rdq[0] + rdq[1] + rdq[2] == 3) && (rdq[0] >= 1)) {
			GL11.glPushMatrix();
			GL11.glScalef(7.0F, 7.0F, 7.0F);
			fr.drawStringWithShadow("BLOCK", (Utils.getX() + fr.getStringWidth("BLOCK")) / 14.0F, (Utils.getY() / 2.0F - 8.0F) / 7.0F, 0xaa0000);
			GL11.glPopMatrix();
		}
	}
}