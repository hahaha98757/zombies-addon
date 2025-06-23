package kr.hahaha98757.zombiesaddon.gui.hudposition

import kr.hahaha98757.zombiesaddon.NAME
import kr.hahaha98757.zombiesaddon.VERSION
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.common.MinecraftForge
import org.lwjgl.input.Mouse

class ConfigGui: GuiScreen() {
    private val boxes = mutableListOf<HudCoordinate>()

    override fun handleMouseInput() {
        super.handleMouseInput()
        val mouseX = Mouse.getEventX() * width / mc.displayWidth
        val mouseY = height - Mouse.getEventY() * height / mc.displayHeight - 1
        val button = Mouse.getEventButton()
        val mouseButtonDown = Mouse.getEventButtonState()

        for (box in boxes) {
            if (box.isMouseOver(mouseX, mouseY) && button == 0)
                if (mouseButtonDown) box.onMousePressed(mouseX, mouseY)
                else box.onMouseReleased()
            if (box.isDragging) box.onMouseDragged(mouseX, mouseY)
        }
    }

    override fun initGui() {
        MinecraftForge.EVENT_BUS.register(GuiScreenEvent.InitGuiEvent.Pre(this, buttonList))
        super.initGui()
        MinecraftForge.EVENT_BUS.post(GuiScreenEvent.InitGuiEvent.Post(this, buttonList))
        val widthAutoSplits = fr.getStringWidth("0:00.0")
        val widthWaveDelays = fr.getStringWidth("➤ ${ZombiesAddon.instance.config.waveDelaysTextStyle}")
        val widthPowerupPatterns = fr.getStringWidth("Shopping Spree: Round 105")
        val widthModName = fr.getStringWidth("$NAME v$VERSION")
        val widthToggleText = fr.getStringWidth("Player Visibility: off")
        val x = getX().toInt() / 2 - 82
        val y = getY().toInt() - 25
        buttonList.add(GuiButton(1, x, y, 80, 20, getTranslatedString("gui.done")))
        buttonList.add(GuiButton(2, x + 85, y, 80, 20, getTranslatedString("zombiesaddon.gui.reset")))
        boxes.clear()
        val boxAutoSplits = HudCoordinate(2, HUDUtils.autoSplitsX, HUDUtils.autoSplitsY, widthAutoSplits, fr.FONT_HEIGHT, width, height)
        boxes.add(boxAutoSplits)
        val boxWaveDelays = HudCoordinate(0, HUDUtils.waveDelaysX, HUDUtils.waveDelaysY, widthWaveDelays, fr.FONT_HEIGHT * 8, width, height)
        boxes.add(boxWaveDelays)
        val boxPowerupPatterns = HudCoordinate(1, HUDUtils.powerupPatternsX, HUDUtils.powerupPatternsY, widthPowerupPatterns, fr.FONT_HEIGHT * 6, width, height)
        boxes.add(boxPowerupPatterns)
        val boxModName = HudCoordinate(3, HUDUtils.modNameX, HUDUtils.modNameY, widthModName, fr.FONT_HEIGHT, width, height)
        boxes.add(boxModName)
        val boxToggleText = HudCoordinate(4, HUDUtils.toggleTextX, HUDUtils.toggleTextY, widthToggleText, fr.FONT_HEIGHT * 3, width, height)
        boxes.add(boxToggleText)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()

        for (box in boxes) box.draw()

        fr.drawStringWithShadow(NAME, (getX(NAME) + 1) / 2, 10f, 0xffffff)
        val str = getTranslatedString("zombiesaddon.gui.comment")
        fr.drawStringWithShadow(str, (getX(str) + 1) / 2, height / 2f, 0xffffff)
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            1 -> {
                for (box in boxes) when (box.contents) {
                    0 -> {
                        if (!box.isDefault) {
                            HUDUtils.waveDelaysX = box.x
                            ZombiesAddon.instance.config.config.get("HUD", "waveDelaysX", -1).set(box.x)
                        }
                        if (!box.isDefault) {
                            HUDUtils.waveDelaysY = box.y
                            ZombiesAddon.instance.config.config.get("HUD", "waveDelaysY", -1).set(box.y)
                        }
                    }
                    1 -> {
                        if (!box.isDefault) {
                            HUDUtils.powerupPatternsX = box.x
                            ZombiesAddon.instance.config.config.get("HUD", "powerupPatternsX", -1).set(box.x)
                        }
                        if (!box.isDefault) {
                            HUDUtils.powerupPatternsY = box.y
                            ZombiesAddon.instance.config.config.get("HUD", "powerupPatternsY", -1).set(box.y)
                        }
                    }
                    2 -> {
                        if (!box.isDefault) {
                            HUDUtils.autoSplitsX = box.x
                            ZombiesAddon.instance.config.config.get("HUD", "autoSplitsX", -1).set(box.x)
                        }
                        if (!box.isDefault) {
                            HUDUtils.autoSplitsY = box.y
                            ZombiesAddon.instance.config.config.get("HUD", "autoSplitsY", -1).set(box.y)
                        }
                    }
                    3 -> {
                        if (!box.isDefault) {
                            HUDUtils.modNameX = box.x
                            ZombiesAddon.instance.config.config.get("HUD", "modNameX", -1).set(box.x)
                        }
                        if (!box.isDefault) {
                            HUDUtils.modNameY = box.y
                            ZombiesAddon.instance.config.config.get("HUD", "modNameY", -1).set(box.y)
                        }
                    }
                    4 -> {
                        if (!box.isDefault) {
                            HUDUtils.toggleTextX = box.x
                            ZombiesAddon.instance.config.config.get("HUD", "toggleTextX", -1).set(box.x)
                        }
                        if (!box.isDefault) {
                            HUDUtils.toggleTextY = box.y
                            ZombiesAddon.instance.config.config.get("HUD", "toggleTextY", -1).set(box.y)
                        }
                    }
                }
                ZombiesAddon.instance.config.config.save()
                mc.displayGuiScreen(null)
            }
            2 -> {
                for (box in boxes) when (box.contents) {
                    0 -> {
                        ZombiesAddon.instance.config.config.get("HUD", "waveDelaysX", -1).set(-1)
                        ZombiesAddon.instance.config.config.get("HUD", "waveDelaysY", -1).set(-1)
                        HUDUtils.waveDelaysX = -1.0
                        HUDUtils.waveDelaysY = -1.0
                        box.isDefault = true
                        DelayedTask {
                            box.x = HUDUtils.waveDelaysX
                            box.absoluteX = (HUDUtils.waveDelaysX * width.toDouble()).toInt()
                            box.y = HUDUtils.waveDelaysY
                            box.absoluteY = (HUDUtils.waveDelaysY * width.toDouble()).toInt()
                        }
                    }
                    1 -> {
                        ZombiesAddon.instance.config.config.get("HUD", "powerupPatternsX", -1).set(-1)
                        ZombiesAddon.instance.config.config.get("HUD", "powerupPatternsY", -1).set(-1)
                        HUDUtils.powerupPatternsX = -1.0
                        HUDUtils.powerupPatternsY = -1.0
                        box.isDefault = true
                        DelayedTask {
                            box.x = HUDUtils.powerupPatternsX
                            box.absoluteX = (HUDUtils.powerupPatternsX * width.toDouble()).toInt()
                            box.y = HUDUtils.powerupPatternsY
                            box.absoluteY = (HUDUtils.powerupPatternsY * width.toDouble()).toInt()
                        }
                    }
                    2 -> {
                        ZombiesAddon.instance.config.config.get("HUD", "autoSplitsX", -1).set(-1)
                        ZombiesAddon.instance.config.config.get("HUD", "autoSplitsY", -1).set(-1)
                        HUDUtils.autoSplitsX = -1.0
                        HUDUtils.autoSplitsY = -1.0
                        box.isDefault = true
                        DelayedTask {
                            box.x = HUDUtils.autoSplitsX
                            box.absoluteX = (HUDUtils.autoSplitsX * width.toDouble()).toInt()
                            box.y = HUDUtils.autoSplitsY
                            box.absoluteY = (HUDUtils.autoSplitsY * width.toDouble()).toInt()
                        }
                    }
                    3 -> {
                        ZombiesAddon.instance.config.config.get("HUD", "modNameX", -1).set(-1)
                        ZombiesAddon.instance.config.config.get("HUD", "modNameY", -1).set(-1)
                        HUDUtils.modNameX = -1.0
                        HUDUtils.modNameY = -1.0
                        box.isDefault = true
                        DelayedTask {
                            box.x = HUDUtils.modNameX
                            box.absoluteX = (HUDUtils.modNameX * width.toDouble()).toInt()
                            box.y = HUDUtils.modNameY
                            box.absoluteY = (HUDUtils.modNameY * width.toDouble()).toInt()
                        }
                    }
                    4 -> {
                        ZombiesAddon.instance.config.config.get("HUD", "toggleTextX", -1).set(-1)
                        ZombiesAddon.instance.config.config.get("HUD", "toggleTextY", -1).set(-1)
                        HUDUtils.toggleTextX = -1.0
                        HUDUtils.toggleTextY = -1.0
                        box.isDefault = true
                        DelayedTask {
                            box.x = HUDUtils.toggleTextX
                            box.absoluteX = (HUDUtils.toggleTextX * width.toDouble()).toInt()
                            box.y = HUDUtils.toggleTextY
                            box.absoluteY = (HUDUtils.toggleTextY * width.toDouble()).toInt()
                        }
                    }
                }
                ZombiesAddon.instance.config.config.save()
            }
            else -> ZombiesAddon.instance.config.config.save()
        }
    }
}