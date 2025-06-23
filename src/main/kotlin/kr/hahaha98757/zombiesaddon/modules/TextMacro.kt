package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.KeyBindings
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.sendChat
import net.minecraftforge.fml.common.gameevent.InputEvent

class TextMacro: Module("Text Macro", true) {
    companion object {
        val instance = TextMacro()
    }

    override fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (KeyBindings.textMacro.isPressed) sendChat(ZombiesAddon.instance.config.textMacro)
    }
}