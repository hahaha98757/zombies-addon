package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.utils.Scoreboard
import kr.hahaha98757.zombiesaddon.utils.withoutColor
import net.minecraft.scoreboard.Score
import net.minecraft.scoreboard.ScorePlayerTeam

object BetterZombiesLeft: Module("Better Zombies Left") {

    var isWork = false
    var leftOnCurrentWave = 0
    var leftAfterWave = 0

    override fun onLastTick(event: LastClientTickEvent) {
        isWork = false
        val game = ZombiesAddon.instance.gameManager.game ?: return
        val left = (Scoreboard.lines.getOrNull(3) ?: return).withoutColor().replace(Regex("[^0-9]"), "").toIntOrNull() ?: return

        val waves = game.roundData.waves
        val currentWaveIndex = waves.indexOfLast { game.timer.roundTick >= it.ticks }
        if (currentWaveIndex < 0) {
            leftOnCurrentWave = 0
            leftAfterWave = left
            isWork = true
            return
        }
        if (waves.any { it.mobCount <= 0 }) return
        val expectedLeft = waves
            .drop(currentWaveIndex + 1)
            .sumOf { it.mobCount }

        val currentWaveMobCount = left - expectedLeft

        if (currentWaveMobCount < 0) return
        leftOnCurrentWave = currentWaveMobCount
        leftAfterWave = expectedLeft
        isWork = true
    }

    fun getName(team: ScorePlayerTeam?, score: Score): String {
        val text = ScorePlayerTeam.formatPlayerName(team, score.playerName)
        if (!isEnable()) return text
        if (!isWork) return text
        if (score.scorePoints != 12) return text

        val base = if (":" in text) text.substringBefore(":") + ": " else text.substringBefore("：") + "："

        return "$base§a$leftOnCurrentWave§f + §c$leftAfterWave"
    }

    override fun isEnable() = ZombiesAddon.instance.config.toggleBetterZombiesLeft
}