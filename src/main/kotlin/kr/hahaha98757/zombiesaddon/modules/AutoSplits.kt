package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.SoundEvent
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.isNotZombies
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraftforge.client.event.ClientChatReceivedEvent
import java.io.OutputStreamWriter
import java.net.Socket


class AutoSplits: Module("Auto Splits", ZombiesAddon.instance.config.autoSplitsToggle) {
    companion object {
        val instance = AutoSplits()
    }
    private var aaR10 = false

    override fun onSound(event: SoundEvent) {
        if (isNotZombies()) return

        val sound = event.sound
        if (sound == "minecraft:mob.wither.spawn" || sound == "minecraft:mob.enderdragon.end") {
            aaR10 = false
            sendCommand("startorsplit")
        } else if (sound == "minecraft:mob.guardian.curse" && !aaR10) {
            aaR10 = true
            sendCommand("startorsplit")
        }
    }

    override fun onChat(event: ClientChatReceivedEvent) {
        if (isNotZombies()) return
        if ("The Helicopter is on its way! Hold out for 120 more seconds!" !in event.message.unformattedText) return
        sendCommand("startorsplit")
    }

    fun sendCommand(signal: String) = Thread {
        try {
            Socket(ZombiesAddon.instance.config.autoSplitsHost, ZombiesAddon.instance.config.autoSplitsPort).use { socket ->
                OutputStreamWriter(socket.getOutputStream()).use {
                    it.write("$signal\r\n")
                    it.flush()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mc.addScheduledTask { addTranslationChat("zombiesaddon.features.autoSplits.failed", "§a$signal") }
        }
    }.start()
}