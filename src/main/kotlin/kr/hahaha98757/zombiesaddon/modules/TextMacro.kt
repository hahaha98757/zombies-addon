package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.sendChat
import net.minecraftforge.fml.common.gameevent.InputEvent

class TextMacro: AlwaysEnableModule("Text Macro") {
    companion object {
        val instance = TextMacro()
    }

    override fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (ZombiesAddon.instance.keyBindings.textMacro.isPressed) sendChat(ZombiesAddon.instance.config.textMacro)
    }
}