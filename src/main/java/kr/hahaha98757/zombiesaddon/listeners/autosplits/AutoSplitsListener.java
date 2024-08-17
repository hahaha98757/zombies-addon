//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners.autosplits;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoSplitsListener {

    public static String timer = "0:00.0";

    private boolean AAr10 = false;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (!ZombiesAddonConfig.enableMod) return;

        if (!Utils.isZombies()) return;

        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

        long millis = new TimerListener().getMillis();
        long minutesPart = millis / 60000;
        long secondsPart = (millis % 60000) / 1000;
        long tenthSecondsPart = (millis % 1000) / 100;
        String time = String.format("%d:%02d.%d", minutesPart, secondsPart, tenthSecondsPart);
        timer = time;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        if (ZombiesAddonConfig.toggleAutoSplits)
            fr.drawStringWithShadow(time, Utils.getX(time), Utils.getYFont(), 0xffffff);
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (!ZombiesAddonConfig.enableMod) return;

        String sound = event.getSoundEffect().getSoundName();

        if (sound.equals("mob.wither.spawn") || sound.equals("mob.enderdragon.end")) AAr10 = false;

        if (sound.equals("mob.wither.spawn") || sound.equals("mob.guardian.curse") && !AAr10) {
            if (sound.equals("mob.guardian.curse")) AAr10 = true;

            runTimer();
        } else if (sound.equals("mob.enderdragon.end")) stopTimer();
    }

    public static void runTimer() {
        TimerListener.instance.run();
    }

    public static void stopTimer() {
        TimerListener.instance.stop();
    }
}