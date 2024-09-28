//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoSplits {
    public static String timer = "0:00.0";

    private boolean AAr10;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (Utils.isNotZombies()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

        long millis = RoundTimer.instance.getMillis();
        long minutesPart = millis / 60000;
        long secondsPart = (millis % 60000) / 1000;
        long tenthSecondsPart = (millis % 1000) / 100;
        String time = String.format("%d:%02d.%d", minutesPart, secondsPart, tenthSecondsPart);
        timer = time;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        if (ZombiesAddonConfig.isToggleAutoSplits())
            fr.drawStringWithShadow(time, HUDUtils.getAutoSplitsStrX(time), HUDUtils.getAutoSplitsStrY(), 0xffffff);
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;

        String sound = event.getSoundName();

        if (sound.equals("mob.wither.spawn") || sound.equals("mob.enderdragon.end")) AAr10 = false;

        if (sound.equals("mob.wither.spawn") || sound.equals("mob.guardian.curse") && !AAr10) {
            if (sound.equals("mob.guardian.curse")) AAr10 = true;

            runTimer();
        } else if (sound.equals("mob.enderdragon.end")) stopTimer();
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("The Helicopter is on its way! Hold out for 120 more seconds!"))
            runTimer();
    }

    public static void runTimer() {
        RoundTimer.instance.run();
    }

    public static void stopTimer() {
        RoundTimer.instance.stop();
    }
}