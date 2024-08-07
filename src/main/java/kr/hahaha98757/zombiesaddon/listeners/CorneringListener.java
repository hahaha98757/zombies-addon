//Code in Player Visibility by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CorneringListener {

	@SubscribeEvent
	public void onRender(RenderPlayerEvent.Pre event) {
		if (!ZombiesAddonConfig.enableMod) return;

		EntityPlayer player = event.entityPlayer;
		if (player != Minecraft.getMinecraft().thePlayer && !player.isPlayerSleeping() && withinDistance(player))
            event.setCanceled(EventListener.cornering);
	}

	private static boolean withinDistance(EntityPlayer other) {
		return getDistanceSquared(other) < Math.pow(ZombiesAddonConfig.corneringRange, 2);
	}

	private static double getDistanceSquared(EntityPlayer other) {
		EntityPlayerSP playerSP = Minecraft.getMinecraft().thePlayer;
		return Math.pow(playerSP.posX - other.posX, 2) + Math.pow(playerSP.posZ - other.posZ, 2);
	}
}
