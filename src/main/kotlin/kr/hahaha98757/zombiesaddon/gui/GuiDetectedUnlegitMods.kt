package kr.hahaha98757.zombiesaddon.gui

import kr.hahaha98757.zombiesaddon.utils.getTranslatedString
import kr.hahaha98757.zombiesaddon.utils.logger
import kr.hahaha98757.zombiesaddon.utils.runBatchFileAndQuit
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
        super.initGui()
        buttonList.clear()
        buttonList.add(GuiButton(0, width / 2 - 100, height / 2, getTranslatedString("zombiesaddon.gui.detectedUnlegitMods.button.remove")))
        buttonList.add(GuiButton(1, width / 2 - 100, height / 2 + 30, getTranslatedString("menu.quit")))
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            0 -> {
                if ("win" !in System.getProperty("os.name").lowercase()) {
                    logger.warn("자동 언레짓 모드 제거는 Windows에서만 지원됩니다.")
                    return
                }
                val strBuilder = StringBuilder()
                for (mod in Loader.instance().modList)
                    if (mod.modId in unlegitMods) strBuilder.append("del \"${mod.source.absolutePath}\"\n")
                logger.info("배치 파일 실행 및 게임 종료를 시작합니다.")
                runBatchFileAndQuit(File(mc.mcDataDir, "mods/deleter_zombiesaddon.bat"), """
                    @echo off
                    chcp 65001
                    echo This is a mod deleter. It should continue after Minecraft quits.
                    echo 이것은 모드 제거 프로그램 입니다. 마인크래프트가 종료된 후 계속 진행되어야 합니다.
                    timeout /t 2 /nobreak
                    pause
                    echo Deleting unlegit mods...
                    echo 언레짓 모드를 제거하는 중...
                    $strBuilder
                    exit
                """.trimIndent())
            }
            1 -> mc.shutdown()
        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (keyCode != Keyboard.KEY_ESCAPE) super.keyTyped(typedChar, keyCode)
    }
}