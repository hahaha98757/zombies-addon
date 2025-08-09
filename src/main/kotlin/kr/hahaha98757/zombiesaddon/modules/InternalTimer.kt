package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.HudUtils
import kr.hahaha98757.zombiesaddon.utils.fr
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import net.minecraftforge.client.event.RenderGameOverlayEvent

class InternalTimer: Module("Internal Timer") {
    companion object {
        val instance = InternalTimer()
    }

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotPlayZombies()) return

        val game = ZombiesAddon.instance.gameManager.game ?: return

        val ticks = game.timer.roundTick
        val minutesPart = ticks / 1200
        val secondsPart = (ticks % 1200) / 20
        val tenthSecondsPart = ((ticks % 1200) % 20) / 2
        val timer = String.format("%d:%02d.%d", minutesPart, secondsPart, tenthSecondsPart)
        fr.drawStringWithShadow(timer, HudUtils.getAutoSplitsStrX(timer), HudUtils.getAutoSplitsStrY(), 0xffffff)
    }

    override fun isEnable() = ZombiesAddon.instance.config.toggleInternalTimer
}