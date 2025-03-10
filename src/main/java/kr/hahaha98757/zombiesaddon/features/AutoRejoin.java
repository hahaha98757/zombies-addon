//Code in Auto Rejoin by tahmid-23

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.KeyBindings;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoRejoin {
    public static boolean autoRejoin;

    private static boolean rejoin = false;
    private static byte tick;

    private static boolean AAr10;

    public AutoRejoin() {
        autoRejoin = ZombiesAddonConfig.getModDefaultValues()[2];
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;
        if (Utils.isNotZombies()) return;
        if (!autoRejoin) return;

        String soundName = event.getSoundName();

        if (soundName.equals("mob.wither.spawn")) AAr10 = false;

        if (!(soundName.equals("mob.wither.spawn") || soundName.equals("mob.guardian.curse") && !AAr10)) return;

        if (soundName.equals("mob.guardian.curse")) AAr10 = true;

        Utils.addTranslationChat("zombiesaddon.features.autoRejoin.rejoining", new Object());
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

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;
        if (!KeyBindings.toggleAutoRejoin.isPressed()) return;
        autoRejoin = !autoRejoin;
        Utils.addTranslationChat("zombiesaddon.features.general.toggled", "§eAuto Rejoin", (autoRejoin ? "§aon" : "§coff"));
    }
}