package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.fr
import net.minecraft.client.gui.Gui
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import org.apache.commons.lang3.text.WordUtils


class ZombiesStratViewer: Module("Zombies Strat Viewer") {
    companion object {
        val instance = ZombiesStratViewer()
    }

    var enabled = false
    val stratLines = mutableListOf<String>()
    private val actualLines = mutableListOf<String>()
    var currentLine = 0
    var linesOfView = 1
    private var width = 0
    private var height = 20

    fun refreshActualLines() {
        height = 0
        width = 20
        actualLines.clear()
        for (i in currentLine..<currentLine + linesOfView) {
            if (i >= stratLines.size) continue
            var line = stratLines[i]
            if (line.isEmpty()) {
                actualLines += "\$CONTROL EMPTY$"
                height += 10
            } else {
                line = WordUtils.wrap(line, 55)
                val strArr = line.split("\n")
                for (s in strArr) {
                    val str = s.replace("\r", "")
                    actualLines += str
                    val width = fr.getStringWidth(str)
                    if (width > this.width) this.width = width
                    height += 10
                }
                actualLines += "\$HEY CONTROL$"
                height += 5
            }
        }
    }

    override fun onKeyInput(event: KeyInputEvent) {
        if (ZombiesAddon.instance.keyBindings.zsvScrollDown.isPressed && currentLine + 1 < stratLines.size) currentLine++
        else if (ZombiesAddon.instance.keyBindings.zsvScrollUp.isPressed && currentLine > 0) currentLine--
        else return
        refreshActualLines()
    }

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        Gui.drawRect(3, 3, 5 + width, 5 + height, 0x55555555)
        var y = -5

        for (str in actualLines)
            when (str) {
                "\$HEY CONTROL$" -> y += 5
                "\$CONTROL EMPTY$" -> y += 10
                else -> {
                    y += 10
                    fr.drawStringWithShadow(str, 5f, y.toFloat(), 0xffffff)
                }
            }
    }

    override fun isEnable() = enabled
}