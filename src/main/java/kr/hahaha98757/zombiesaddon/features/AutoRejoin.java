//Code in Auto Rejoin by tahmid-23

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
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

        Utils.addChat("§eAuto Rejoin: Rejoining...");
        Utils.sendChat("/l");
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

        Utils.sendChat("/rejoin");
        tick = 0;
        rejoin = false;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;
        if (!Hotkeys.toggleAutoRejoin.isPressed()) return;
        autoRejoin = !autoRejoin;
        Utils.addChat("§eToggled Auto Rejoin to " + (autoRejoin ? "§aon" : "§coff"));
    }
}