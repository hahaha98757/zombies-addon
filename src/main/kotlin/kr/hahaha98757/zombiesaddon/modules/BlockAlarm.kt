package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Status
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.client.event.RenderGameOverlayEvent
import org.lwjgl.opengl.GL11

object BlockAlarm: ToggleableModule("Block Alarm", ZombiesAddon.instance.config.blockAlarmDefault) {
    override fun getKeyBinding() = ZombiesAddon.instance.keyBindings.toggleBlockAlarm
    override fun addToggleText(enabled: Boolean) =
        addTranslatedChat("zombiesaddon.modules.general.toggled", "Block Alarm", if (enabled) "§aon" else "§coff")

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotPlayZombies()) return

        val status = getPlayerStatus()
        if (status[0] != Status.SURVIVE) return
        var nonSurviveCount = 0
        var reviveCount = 0
        for (i in status) {
            if (i != Status.SURVIVE) nonSurviveCount++
            if (i == Status.REVIVE) reviveCount++
        }
        if (nonSurviveCount != 3) return
        if (reviveCount == 0) return

        GL11.glPushMatrix()
        GL11.glScalef(7.0f, 7.0f, 7.0f)
        fr.drawStringWithShadow("BLOCK", (getX() + fr.getStringWidth("BLOCK")) / 14, (getY() / 2 - fr.FONT_HEIGHT) / 7, 0xaa0000)
        GL11.glPopMatrix()
    }
}