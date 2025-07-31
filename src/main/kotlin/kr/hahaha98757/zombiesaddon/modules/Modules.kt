package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.NAME
import kr.hahaha98757.zombiesaddon.VERSION
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.ThePlayerJoinEvent
import kr.hahaha98757.zombiesaddon.utils.HUDUtils
import kr.hahaha98757.zombiesaddon.utils.addTranslationChatLine
import kr.hahaha98757.zombiesaddon.utils.fr
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.Event

class Modules: AlwaysEnableModule("Modules") {
    companion object {
        val instance = Modules()
    }

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        var i = 1

        val red = !ZombiesAddon.instance.config.blockUnlegitMods || !ZombiesAddon.instance.config.blockUnlegitSST
        var str = "$NAME v$VERSION"
        fr.drawStringWithShadow(str, HUDUtils.getModNameStrX(str), HUDUtils.getModNameStrY(),  if (red) 0xff5555 else 0xffff55)

        str = "Player Visibility: ${if (PlayerVisibility.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.pvText) fr.drawStringWithShadow(str, HUDUtils.getToggleTextStrX(str), HUDUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Block Alarm: ${if (BlockAlarm.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.blockAlarmText) fr.drawStringWithShadow(str, HUDUtils.getToggleTextStrX(str), HUDUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Auto Rejoin: ${if (AutoRejoin.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.autoRejoinText) fr.drawStringWithShadow(str, HUDUtils.getToggleTextStrX(str), HUDUtils.getToggleTextStrY(i), 0xffff55)
    }

    override fun onEvent(event: Event) {
        if (event !is ThePlayerJoinEvent) return

        if (!ZombiesAddon.instance.config.blockUnlegitMods) addTranslationChatLine("zombiesaddon.messages.offBlockUnlegitMods")
        if (!ZombiesAddon.instance.config.blockUnlegitSST) addTranslationChatLine("zombiesaddon.messages.offBlockUnlegitSST")
        else addTranslationChatLine("zombiesaddon.messages.blockUnlegitSST")
    }
}