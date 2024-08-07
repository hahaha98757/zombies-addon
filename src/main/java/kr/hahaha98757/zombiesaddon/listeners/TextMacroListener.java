package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class TextMacroListener {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!ZombiesAddonConfig.enableMod) return;

        if (!Keybinds.textMacro.isPressed()) return;

        Minecraft.getMinecraft().thePlayer.sendChatMessage(ZombiesAddonConfig.textMacro);
    }
}