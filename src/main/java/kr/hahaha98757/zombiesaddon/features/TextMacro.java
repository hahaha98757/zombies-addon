package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.KeyBindings;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class TextMacro {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;

        if (!KeyBindings.textMacro.isPressed()) return;

        Utils.sendChat(ZombiesAddonConfig.getTextMacro());
    }
}