//Code in NOTLAST by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class TitleListener {
	private String title = "";

	@SubscribeEvent
	public void onRenderGameOverlayEvent(RenderGameOverlayEvent event) throws IllegalAccessException {
		if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;


		String temp = (String) ReflectionHelper.findField(GuiIngame.class, new String[] { "displayedTitle", "field_175201_x" }).get(Minecraft.getMinecraft().ingameGUI);

		if (title.equals(temp)) return;

		title = temp;
		if (!temp.isEmpty()) MinecraftForge.EVENT_BUS.post(new TitleEvent(temp));
	}
}