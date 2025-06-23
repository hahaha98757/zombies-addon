package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.KeyBindings
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.DelayedTask
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.sendChat

class AutoRejoin: ToggleModule("Auto Rejoin", ZombiesAddon.instance.config.autoRejoinDefault) {
    companion object {
        val instance = AutoRejoin()
    }

    override fun getKeyBinding() = KeyBindings.toggleAutoRejoin
    override fun addToggleText(enabled: Boolean) =
        addTranslationChat("zombiesaddon.features.general.toggled", "§eAuto Rejoin", if (enabled) "§aon" else "§coff")

    override fun onRoundStart(event: RoundStartEvent) {
        addTranslationChat("zombiesaddon.features.autoRejoin.rejoining")
        sendChat("/l")
        DelayedTask(100) {
            sendChat("/rejoin")
        }
    }
}