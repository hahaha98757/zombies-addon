package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.utils.Scoreboard
import kr.hahaha98757.zombiesaddon.utils.fr
import kr.hahaha98757.zombiesaddon.utils.withoutColor
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.scoreboard.Score
import net.minecraft.scoreboard.ScoreObjective
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

    fun customRenderScoreboard(objective: ScoreObjective, scaledRes: ScaledResolution) {
        val scoreboard = objective.scoreboard
        val scores = scoreboard.getSortedScores(objective).asSequence()
            .filter { it.playerName != null && !it.playerName.startsWith("#") }
            .let {
                val list = it.toList()
                if (list.size > 15) list.takeLast(15) else list
            }.takeIf { it.isNotEmpty() } ?: return


        var maxWidth = fr.getStringWidth(objective.displayName)

        scores.forEach {
            val team = scoreboard.getPlayersTeam(it.playerName)
            val line = "${getName(team, it)}: §c${it.scorePoints}"
            maxWidth = maxWidth.coerceAtLeast(fr.getStringWidth(line))
        }

        val totalHeight = scores.size * fr.FONT_HEIGHT
        val startY = scaledRes.scaledHeight / 2 + totalHeight / 3
        val padding = 3
        val leftX = scaledRes.scaledWidth - maxWidth - padding
        val rightX = scaledRes.scaledWidth - padding + 2

        var index = 0

        for (score in scores) {
            index++

            val team = scoreboard.getPlayersTeam(score.playerName)
            val nameText = getName(team, score)
            val valueText = "§c${score.scorePoints}"
            val y = startY - index * fr.FONT_HEIGHT

            Gui.drawRect(leftX - 2, y, rightX, y + fr.FONT_HEIGHT, 0x50000000)
            fr.drawString(nameText, leftX, y, 0x20FFFFFF)
            fr.drawString(valueText, rightX - fr.getStringWidth(valueText), y, 0x20FFFFFF)

            if (index != scores.size) continue

            val title = objective.displayName

            Gui.drawRect(leftX - 2, y - fr.FONT_HEIGHT - 1, rightX, y - 1, 0x60000000)
            Gui.drawRect(leftX -2, y - 1, rightX, y, 0x50000000)
            fr.drawString(title, leftX + maxWidth / 2 - fr.getStringWidth(title) / 2, y - fr.FONT_HEIGHT, 0x20FFFFFF)
        }
    }

    fun getName(team: ScorePlayerTeam?, score: Score): String {
        val text = ScorePlayerTeam.formatPlayerName(team, score.playerName)
        if (!isEnable()) return text
        if (!isWork) return text
        if (score.scorePoints != 12) return text

        val base = if (":" in text) text.substringBefore(":") + ": " else text.substringBefore("：") + "："

        val newText = "$base§a$leftOnCurrentWave§f + §c$leftAfterWave"

        return newText
    }

    override fun isEnable() = ZombiesAddon.instance.config.toggleBetterZombiesLeft
}