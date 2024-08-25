//Code in Auto Rejoin by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoRejoinListener {
    private static boolean rejoin = false;
    private static byte tick;

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (!ZombiesAddonConfig.enableMod) return;

        if (!EventListener.autoRejoin) return;

        if (!Utils.isZombies()) return;

        String soundName = event.getSoundEffect().getSoundName();

        if (!soundName.equals("mob.wither.spawn")) return;

        Utils.addChat("§eAuto Rejoin: Rejoining...");
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/l");
        tick = 0;
        rejoin = true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        if (!rejoin) return;

        if (tick != 100) {
            tick++;
            return;
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/rejoin");
        tick = 0;
        rejoin = false;
    }
}