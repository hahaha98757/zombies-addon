package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.NAME
import kr.hahaha98757.zombiesaddon.VERSION
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.HUDUtils
import kr.hahaha98757.zombiesaddon.utils.fr
import net.minecraftforge.client.event.RenderGameOverlayEvent

class Modules: Module("Modules", true) {
    companion object {
        val instance = Modules()
    }

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        var i = 1

        var str = "$NAME v$VERSION"
        fr.drawStringWithShadow(str, HUDUtils.getModNameStrX(str), HUDUtils.getModNameStrY(), 0xffff55)

        str = "Player Visibility: ${if (PlayerVisibility.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.pvText) fr.drawStringWithShadow(str, HUDUtils.getToggleTextStrX(str), HUDUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Block Alarm: ${if (BlockAlarm.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.blockAlarmText) fr.drawStringWithShadow(str, HUDUtils.getToggleTextStrX(str), HUDUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Auto Rejoin: ${if (AutoRejoin.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.autoRejoinText) fr.drawStringWithShadow(str, HUDUtils.getToggleTextStrX(str), HUDUtils.getToggleTextStrY(i), 0xffff55)
    }
}