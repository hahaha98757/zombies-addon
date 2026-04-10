package kr.hahaha98757.zombiesaddon.gui

import kr.hahaha98757.zombiesaddon.utils.FileRemoverLauncher
import kr.hahaha98757.zombiesaddon.utils.getTranslatedString
import kr.hahaha98757.zombiesaddon.utils.unlegitMods
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.common.Loader
import org.lwjgl.input.Keyboard
import java.io.File

object GuiDetectedUnlegitMods: GuiScreen() {

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        drawCenteredString(fontRendererObj, getTranslatedString("zombiesaddon.gui.detectedUnlegitMods.title"), width / 2, height / 2 - 40, 0xffffff)
        val strBuilder = StringBuilder(getTranslatedString("zombiesaddon.gui.detectedUnlegitMods.list"))
        for (mod in Loader.instance().modList) if (mod.modId in unlegitMods) strBuilder.append(" ${mod.source.name}")
        drawCenteredString(fontRendererObj, strBuilder.toString(), width / 2, height / 2 - 31, 0xffffff)
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun initGui() {
        buttonList.clear()
        buttonList.add(GuiButton(0, width / 2 - 100, height / 2, getTranslatedString("zombiesaddon.gui.detectedUnlegitMods.button.remove")))
        buttonList.add(GuiButton(1, width / 2 - 100, height / 2 + 30, getTranslatedString("menu.quit")))
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            0 -> {
                val targets = mutableListOf<File>()
                for (mod in Loader.instance().modList)
                    if (mod.modId in unlegitMods) targets += mod.source
                FileRemoverLauncher.runAndQuit(targets.toTypedArray())
            }
            1 -> mc.shutdown()
        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (keyCode != Keyboard.KEY_ESCAPE) super.keyTyped(typedChar, keyCode)
    }
}