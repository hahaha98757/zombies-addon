package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.data.CustomPlaySoundLoader
import kr.hahaha98757.zombiesaddon.data.Prefix
import kr.hahaha98757.zombiesaddon.data.Wave
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.enums.Map
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.events.SoundEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import java.util.*


class WaveDelays: Module("Wave Delays") {
    companion object {
        val instance = WaveDelays()
        private const val DESPAWN_TICK: Short = 6000
    }
    var difficulty = Difficulty.NORMAL
    private var round = 0
    private var gameEnd = false
    private var escape = false
    private var rlMode = false
    private var offset = 0

    override fun onDisable() {
        gameEnd = false
        escape = false
    }

    override fun onKeyInput(event: KeyInputEvent) {
        if (!ZombiesAddon.instance.keyBindings.toggleRLMode.isPressed) return
        rlMode = !rlMode
        offset = if (rlMode) ZombiesAddon.instance.config.waveDelaysRLModeOffset else 0
        addTranslationChat("zombiesaddon.features.general.toggled", "§eRL Mode", if (rlMode) "§aon" else "§coff")
    }

    override fun onChat(event: ClientChatReceivedEvent) {
        val message = getText(event.message.unformattedText)
        if (">" in message) return

        if ("The Helicopter is on its way! Hold out for 120 more seconds!" in message) escape = true

        if ("Hard Difficulty" in message || "Hard 난이도" in message) DelayedTask(10) { difficulty = Difficulty.HARD }
        if ("RIP Difficulty" in message || "RIP 난이도" in message) DelayedTask(10) { difficulty = Difficulty.RIP }
    }

    override fun onSound(event: SoundEvent) {
        if (event.sound == "minecraft:mob.enderdragon.end") gameEnd = true
    }

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotZombies()) {
            gameEnd = false
            return
        }

        val map = getMap()

        if (isNotPlayZombies()) {
            if (map == null) difficulty = Difficulty.NORMAL
            gameEnd = false
            escape = false
        }

        if (!gameEnd) round = if (map == Map.PRISON && escape) 31 else getRound()

        val round = RoundData.getRoundData(map ?: return, difficulty, round) ?: return

        val waves = round.waves
        val roundTime = InternalTimer.instance.ticks
        val length = waves.size
        var faded: Boolean
        var color = "§e"

        if (ZombiesAddon.instance.config.waveDelaysHighlightStyle == "Zombies Addon") for (i in waves.indices.reversed()) {
            val waveDelay = waves[i].ticks + offset
            if (roundTime >= waveDelay) {
                if (ZombiesAddon.instance.config.waveDelaysHidePassedWave && roundTime > waveDelay) break
                val str = "➤ ${ZombiesAddon.instance.config.waveDelaysTextStyle}"
                fr.drawStringWithShadow("§5➤ ", HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, i), 0)
                break
            }
        }

        for ((i, wave) in waves.withIndex()) {
            val waveTime = wave.ticks + offset

            val bossColor = arrayOf("", "")
            for (prefix in wave.prefixes) {
                if ((prefix != Prefix.BOSS && prefix != Prefix.GIANT && prefix != Prefix.OLD_ONE) || !ZombiesAddon.instance.config.waveDelaysBossColor) continue
                bossColor[0] = getBossColor1(map, difficulty, this.round, i+1)
                if (map == Map.ALIEN_ARCADIUM) bossColor[1] = getBossColor2(this.round, i+1)
            }

            val waveText = when (ZombiesAddon.instance.config.waveDelaysTextStyle) {
                "W1: 0:10.0" -> "W${i+1}: ${bossColor[0] + getMinutesString(waveTime.toLong())}:${bossColor[1] + getSecondsString(waveTime.toLong())}.${getTenthSecondsString(waveTime.toLong())}"
                "W1 0:10.0" -> "W${i+1} ${bossColor[0] + getMinutesString(waveTime.toLong())}:${bossColor[1] + getSecondsString(waveTime.toLong())}.${getTenthSecondsString(waveTime.toLong())}"
                "W1: 00:10" -> "W${i+1}: ${bossColor[0] + getMinutesString(waveTime.toLong(), true)}:${bossColor[1] + getSecondsString(waveTime.toLong())}"
                "W1 00:10" -> "W${i+1} ${bossColor[0] + getMinutesString(waveTime.toLong(), true)}:${bossColor[1] + getSecondsString(waveTime.toLong())}"
                else -> throw Error("It is impossible to reach this code.")
            }

            if (ZombiesAddon.instance.config.waveDelaysHighlightStyle == "Zombies Addon") {
                if (roundTime >= waveTime + DESPAWN_TICK)
                    fr.drawStringWithShadow("§c$waveText", HUDUtils.getWaveDelaysStrX(waveText), HUDUtils.getWaveDelaysStrY(length, i), 0)
                else if (roundTime >= waveTime) if (roundTime != waveTime && ZombiesAddon.instance.config.waveDelaysHidePassedWave) continue
                    else fr.drawStringWithShadow("§a$waveText", HUDUtils.getWaveDelaysStrX(waveText), HUDUtils.getWaveDelaysStrY(length, i), 0)
                else if (roundTime > waveTime - 60)
                    fr.drawStringWithShadow("§e$waveText", HUDUtils.getWaveDelaysStrX(waveText), HUDUtils.getWaveDelaysStrY(length, i), 0)
                else
                    fr.drawStringWithShadow("§8$waveText", HUDUtils.getWaveDelaysStrX(waveText), HUDUtils.getWaveDelaysStrY(length, i), 0)

                drawPrefixes(waveText, wave, i, length)
            } else if (ZombiesAddon.instance.config.waveDelaysHighlightStyle == "Zombies Utils") {
                faded = if (roundTime > waveTime) if (!ZombiesAddon.instance.config.waveDelaysHidePassedWave) true else continue else false
                fr.drawStringWithShadow(if (faded) "§8$waveText" else color + waveText, HUDUtils.getWaveDelaysStrX(waveText), HUDUtils.getWaveDelaysStrY(length, i), 0)

                drawPrefixes(waveText, wave, i, length, faded)
                if (!faded) color = "§7"
            }
        }
    }

    private fun drawPrefixes(waveText: String, wave: Wave, i: Int, length: Int, faded: Boolean = false) {
        if (!ZombiesAddon.instance.config.waveDelaysPrefix) return
        var width = HUDUtils.getWaveDelaysStrX("➤ $waveText")
        for (prefix in wave.prefixes) {
            if (ZombiesAddon.instance.config.waveDelaysBossColor && (prefix == Prefix.BOSS || prefix == Prefix.GIANT || prefix == Prefix.OLD_ONE)) continue
            val prefixStr = "${prefix.prefix} "
            width -= fr.getStringWidth(prefixStr)
            fr.drawStringWithShadow(prefixStr, width, HUDUtils.getWaveDelaysStrY(length, i), if (faded) prefix.fadedColor else prefix.color)
        }
    }

    override fun onLastTick(event: LastClientTickEvent) {
        if (isNotZombies()) return
        if (gameEnd) return
        if (round == 0) return

        val round = RoundData.getRoundData(getMap() ?: return, difficulty, round) ?: return

        val waves = round.waves
        val roundTime = InternalTimer.instance.ticks
        val lastIndex = waves.lastIndex

        for ((i, wave) in waves.withIndex()) {
            val waveTime = wave.ticks + offset
            var play = false

            if (ZombiesAddon.instance.config.waveDelaysCustomPlaySound) {
                val cpsArr = CustomPlaySoundLoader.cps ?: return
                val pre = roundTime - waveTime

                for (cps in cpsArr) {
                    if (cps.timing != pre) continue
                    play = true
                    when (cps.playType) {
                        1 -> if (lastIndex != i) mc.thePlayer.playSound(cps.name, 1f, cps.pitch)
                        2 -> if (lastIndex == i) mc.thePlayer.playSound(cps.name, 1f, cps.pitch)
                        else -> mc.thePlayer.playSound(cps.name, 1f, cps.pitch)
                    }
                }
            } else {
                val timings = ZombiesAddon.instance.config.waveDelaysPlaySounds
                val pre = roundTime - waveTime

                if (Arrays.stream(timings).anyMatch { it == pre }) {
                    play = true
                    mc.thePlayer.playSound("note.pling", 1f, 2.0f)
                }
            }
            if (play) break
        }
    }


    private fun getMinutesString(ticks: Long, sst: Boolean = false): String {
        val ms = ticks * 50
        return String.format(if (sst) "%02d" else "%d", ms / 60000)
    }

    private fun getSecondsString(ticks: Long): String {
        val ms = ticks * 50
        return String.format("%02d", (ms % 60000) / 1000)
    }

    private fun getTenthSecondsString(ticks: Long): String {
        val ms = ticks * 50
        return String.format("%d", (ms % 1000) / 100)
    }

    override fun isEnable() = ZombiesAddon.instance.config.waveDelaysToggle
}

private fun getBossColor1(map: Map, difficulty: Difficulty, round: Int, wave: Int): String {
    return when (map) {
        Map.DEAD_END -> {
            when (round) {
                5 -> {
                    if (difficulty == Difficulty.RIP && wave == 3) "§6"
                    else ""
                }
                10 -> {
                    if (wave == 3) "§6"
                    else if (difficulty == Difficulty.RIP && wave == 4) "§6"
                    else ""
                }
                15 -> {
                    if (difficulty != Difficulty.NORMAL && wave == 2) "§6"
                    else if (difficulty == Difficulty.RIP && wave == 3) "§c"
                    else ""
                }
                20 -> {
                    if (wave == 3) "§c"
                    else if (difficulty == Difficulty.RIP && wave == 4) "§c"
                    else ""
                }
                25 -> {
                    if (difficulty == Difficulty.HARD && wave == 3) "§6"
                    else if (difficulty == Difficulty.RIP && (wave == 1 || wave == 2)) "§6"
                    else if (difficulty == Difficulty.RIP && wave == 3) "§5"
                    else ""
                }
                30 -> {
                    if (wave == 3) "§5"
                    else if (difficulty != Difficulty.NORMAL && wave == 2) "§5"
                    else if (difficulty == Difficulty.RIP) "§5"
                    else ""
                }
                else -> ""
            }
        }
        Map.BAD_BLOOD -> {
            when (round) {
                10 -> {
                    if (wave == 3) "§a"
                    else ""
                }
                15 -> {
                    if (difficulty == Difficulty.RIP) "§a"
                    else ""
                }
                20 -> {
                    if (difficulty != Difficulty.RIP && wave == 2) "§5"
                    else if (difficulty == Difficulty.RIP && (wave == 1 || wave == 3)) "§5"
                    else ""
                }
                25 -> {
                    if (difficulty == Difficulty.RIP && wave == 3) "§a"
                    else ""
                }
                30 -> {
                    if (difficulty != Difficulty.RIP && wave == 2) "§5"
                    else if (difficulty == Difficulty.RIP && (wave == 1 || wave == 2)) "§5"
                    else ""
                }
                else -> ""
            }
        }
        Map.ALIEN_ARCADIUM -> {
            when (round) {
                15 -> {
                    if (wave == 6) "§3"
                    else ""
                }
                20 -> {
                    if (wave == 3 || wave == 5) "§3"
                    else ""
                }
                22 -> {
                    if (wave == 4 || wave == 6) "§3"
                    else ""
                }
                24, 43 -> {
                    if (wave == 2 || wave == 4 || wave == 6) "§3"
                    else ""
                }
                25 -> "§a"
                30, 42, 44 -> "§3"
                35, 59, in 102..105 -> "§4"
                in 36..39, 41 -> {
                    if (wave == 2 || wave == 3) "§3"
                    else ""
                }
                40 -> {
                    when (wave) {
                        2, 3 -> "§3"
                        5 -> "§4"
                        else -> ""
                    }
                }
                45 -> {
                    when (wave) {
                        2 -> "§3"
                        3, 4 -> "§4"
                        else -> ""
                    }
                }
                46, 48 -> {
                    if (wave == 4) "§4"
                    else ""
                }
                47 -> {
                    if (wave == 3) "§3"
                    else ""
                }
                in 50..53 -> {
                    if (wave == 2 || wave == 4) "§3"
                    else ""
                }
                54, 58 -> {
                    when (wave) {
                        2, 4 -> "§3"
                        5 -> "§4"
                        else -> ""
                    }
                }
                55 -> {
                    when (wave) {
                        in 1..5 -> "§3"
                        6 -> "§4"
                        else -> ""
                    }
                }
                56 -> {
                    if (wave == 1) "§a"
                    else ""
                }
                57 -> {
                    if (wave == 1) "§4"
                    else ""
                }
                60 -> {
                    if (wave == 3 || wave == 4) "§4"
                    else ""
                }
                64, 68, 69, 78, 79, 88, 89, 98, 99 -> {
                    if (wave == 5 || wave == 6) "§4"
                    else ""
                }
                74, 84, 94 -> {
                    if (wave in 4..6) "§4"
                    else ""
                }
                65, 75, 85, 95 -> {
                    if (wave in 4..6) "§3"
                    else ""
                }
                67, 77, 87, 97 -> {
                    if (wave == 6) "§4"
                    else ""
                }
                70, 80, 90, 100 -> {
                    when (wave) {
                        in 4..6 -> "§3"
                        2, 3 -> "§4"
                        else -> ""
                    }
                }
                101 -> "§0"
                else -> ""
            }
        }
        Map.PRISON -> {
            when (round) {
                10, 20 -> {
                    if (wave == 3) "§c"
                    else ""
                }
                30 -> "§6"
                31 -> {
                    if (wave == 5) "§6"
                    else ""
                }
                else -> ""
            }
        }
    }
}

private fun getBossColor2(round: Int, wave: Int) = when (round) {
    54, 58 -> {
        if (wave == 2) "§4"
        else ""
    }
    55 -> {
        if (wave == 5) "§4"
        else ""
    }
    70, 80, 90, 100 -> {
        if (wave in 4..6) "§4"
        else ""
    }
    else -> ""
}