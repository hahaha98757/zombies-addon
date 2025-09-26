package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.isDisable
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import kr.hahaha98757.zombiesaddon.utils.sendChat
import net.minecraftforge.fml.common.gameevent.InputEvent

object TextMacro: AlwaysEnableModule("Text Macro") {
    override fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (!ZombiesAddon.instance.keyBindings.textMacro.isPressed) return
        if (isDisable()) return
        if (isNotPlayZombies()) return
        sendChat(ZombiesAddon.instance.config.textMacro)
    }
}