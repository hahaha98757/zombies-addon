package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.commands.CommandZaDebug
import net.minecraft.scoreboard.ScorePlayerTeam

object Scoreboard {
    private val emojiPattern = Regex("[\uD83D\uDD2B\uD83C\uDF6B\uD83D\uDCA3\uD83D\uDC7D\uD83D\uDD2E\uD83D\uDC0D\uD83D\uDC7E\uD83C\uDF20\uD83C\uDF6D\u26BD\uD83C\uDFC0\uD83D\uDC79\uD83C\uDF81\uD83C\uDF89\uD83C\uDF82]+")

    var title: String? = null
    val lines = mutableListOf<String>()

    fun refresh() {
        if (mc.theWorld == null || mc.thePlayer == null) return
        val scoreboard = mc.theWorld.scoreboard
        val objective = scoreboard.getObjectiveInDisplaySlot(1) ?: return

        title = getText(objective.displayName)
        val scores = scoreboard.getSortedScores(objective)?.filter { it.playerName != null && !it.playerName.startsWith("#") }?.takeLast(15)?.asReversed() ?: return

        lines.clear()
        for (score in scores) {
            val team = scoreboard.getPlayersTeam(score.playerName)
            lines += getText(ScorePlayerTeam.formatPlayerName(team, score.playerName).trim().replace(emojiPattern, "")).trim()
        }
    }

    val isNotZombies get() = if (ZombiesAddon.instance.debug) CommandZaDebug.debugIsNotZombies else title != "ZOMBIES"

    val round: Int get() {
        if (ZombiesAddon.instance.debug) return 1
        if (isNotZombies) return 0
        val line = lines.getOrNull(2) ?: return 0
        val round = line.replace(Regex("[^0-9]"), "").toIntOrNull() ?: return 0
        return round
    }
}