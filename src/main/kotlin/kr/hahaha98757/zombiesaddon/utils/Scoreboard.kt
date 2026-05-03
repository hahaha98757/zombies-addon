package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.commands.CommandZaDebug

object Scoreboard {
    private var title: String? = null
    val lines = mutableListOf<String>()

    fun refresh() {
        if (mc.theWorld == null || mc.thePlayer == null) return
        if (!ZombiesAddon.instance.debug && mc.isSingleplayer) return
        val scoreboard = mc.theWorld.scoreboard
        val objective = scoreboard.getObjectiveInDisplaySlot(1) ?: return

        title = objective.displayName.withoutColor()
        val scores = scoreboard.getSortedScores(objective)?.filter { it.playerName != null && !it.playerName.startsWith("#") }?.takeLast(15)?.asReversed() ?: return

        lines.clear()
        for (score in scores) {
            val team = scoreboard.getPlayersTeam(score.playerName)

            // Better Zombies Left에서 사용되는 Mixin 때문에, ScorePlayerTeam.formatPlayerName을 사용할 수 없음
            val raw = buildString {
                if (team != null) append(team.colorPrefix)
                append(score.playerName)
                if (team != null) append(team.colorSuffix)
            }

            lines += raw.trim().removeEmoji().withoutColor().trim()
        }
    }

    val isNotZombies get() = if (ZombiesAddon.instance.debug) CommandZaDebug.isNotZombies else title != "ZOMBIES"

    val round: Int get() {
        if (isNotZombies) return 0
        val line = lines.getOrNull(2) ?: return 0
        val round = line.replace(Regex("[^0-9]"), "").toIntOrNull() ?: return 0
        return round
    }
}