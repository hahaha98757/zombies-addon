//Code in Player Visibility by tahmid-23
//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class PlayerVisibility {
    public static boolean playerVisibility;

    public PlayerVisibility() {
        playerVisibility = ZombiesAddonConfig.getModDefaultValues()[0];
    }

    @SubscribeEvent
    public void onRender(RenderPlayerEvent.Pre event) {
        if (Utils.isModDisable()) return;

        EntityPlayer player = event.entityPlayer;
        if (player != Minecraft.getMinecraft().thePlayer && !player.isPlayerSleeping() && withinDistance(player))
            event.setCanceled(playerVisibility);
    }

    private static boolean withinDistance(EntityPlayer other) {
        return Minecraft.getMinecraft().thePlayer.getDistanceToEntity(other) < ZombiesAddonConfig.getPVRange();
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!Hotkeys.togglePV.isPressed()) return;
        playerVisibility = !playerVisibility;
        Utils.addChat("§eToggled PV to " + (playerVisibility ? "§aon" : "§coff"));
    }

    public static boolean isPlayerInvisible(Entity entity) {
        boolean flag = !entity.isInvisible();
        boolean flag1 = !flag && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer);
        boolean flag2 = entity != Minecraft.getMinecraft().thePlayer && entity instanceof EntityPlayer && Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) < ZombiesAddonConfig.getPVRange() * 2;
        return (flag || flag1) && (flag1 || flag2);
    }

    public static float getAlpha(Entity entity) {
        double pvRange = ZombiesAddonConfig.getPVRange();

        double distance = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);

        if (distance <= pvRange || distance > pvRange * 2) return 0.0F;

        return (float) ((distance - pvRange) / pvRange);
    }
}