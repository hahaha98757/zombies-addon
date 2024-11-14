package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class TextMacro {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;

        if (!Hotkeys.textMacro.isPressed()) return;

        Minecraft.getMinecraft().thePlayer.sendChatMessage(ZombiesAddonConfig.getTextMacro());
    }
}