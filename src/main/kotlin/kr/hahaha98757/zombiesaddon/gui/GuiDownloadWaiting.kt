package kr.hahaha98757.zombiesaddon.gui

import kr.hahaha98757.zombiesaddon.utils.getTranslatedString
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Keyboard

class GuiDownloadWaiting: GuiScreen() {
    companion object {
        var failed = false
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        if (!failed) drawCenteredString(fontRendererObj, getTranslatedString("zombiesaddon.gui.downloadWaiting.title"), width / 2, height / 2 - 40, 0xffffff)
        else drawCenteredString(fontRendererObj, getTranslatedString("zombiesaddon.gui.downloadWaiting.failed"), width / 2, height / 2 - 40, 0xff5555)
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (keyCode != Keyboard.KEY_ESCAPE) super.keyTyped(typedChar, keyCode)
    }
}