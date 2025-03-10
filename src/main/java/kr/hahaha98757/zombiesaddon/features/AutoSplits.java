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
    private static boolean AAr10;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (Utils.isNotZombies()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (!ZombiesAddonConfig.isToggleAutoSplits()) return;

        long millis = RoundTimer.getMillis();
        long minutesPart = millis / 60000;
        long secondsPart = (millis % 60000) / 1000;
        long tenthSecondsPart = (millis % 1000) / 100;
        String time = String.format("%d:%02d.%d", minutesPart, secondsPart, tenthSecondsPart);
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawStringWithShadow(time, HUDUtils.getAutoSplitsStrX(time), HUDUtils.getAutoSplitsStrY(), 0xffffff);
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;

        String soundName = event.getSoundName();

        if (soundName.equals("mob.wither.spawn") || soundName.equals("mob.enderdragon.end")) AAr10 = false;

        if (soundName.equals("mob.wither.spawn") || soundName.equals("mob.guardian.curse") && !AAr10) {
            if (soundName.equals("mob.guardian.curse")) AAr10 = true;
            RoundTimer.run();
        } else if (soundName.equals("mob.enderdragon.end")) RoundTimer.run();
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (Utils.isModDisable()) return;
        if (event.message.getUnformattedText().contains("The Helicopter is on its way! Hold out for 120 more seconds!"))
            RoundTimer.run();
    }
}