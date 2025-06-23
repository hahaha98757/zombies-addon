package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.KeyBindings
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.client.event.RenderGameOverlayEvent
import org.lwjgl.opengl.GL11

class BlockAlarm: ToggleModule("Block Alarm", ZombiesAddon.instance.config.blockAlarmDefault) {
    companion object {
        val instance = BlockAlarm()
    }

    override fun getKeyBinding() = KeyBindings.toggleBlockAlarm
    override fun addToggleText(enabled: Boolean) =
        addTranslationChat("zombiesaddon.features.general.toggled", "§eBlock Alarm", if (enabled) "§aon" else "§coff")

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotZombies()) return

        val states = getPlayerState()
        if (states[3] != 0) return
        if (states[0] + states[1] + states[2] != 3) return
        if (states[0] == 0) return

        GL11.glPushMatrix()
        GL11.glScalef(7.0f, 7.0f, 7.0f)
        fr.drawStringWithShadow("BLOCK", (getX() + fr.getStringWidth("BLOCK")) / 14, (getY() / 2 - fr.FONT_HEIGHT) / 7, 0xaa0000)
        GL11.glPopMatrix()
    }
}