package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraft.scoreboard.ScoreObjective
import net.minecraft.scoreboard.Scoreboard

class NotLast: Module("Not Last", ZombiesAddon.instance.config.toggleNotLast) {
    companion object {
        val instance = NotLast()
    }

    private val scoreMap = mutableMapOf<String, Int>()
    private lateinit var scoreboard: Scoreboard
    private lateinit var scoreObjective: ScoreObjective

    override fun onRoundStart(event: RoundStartEvent) {
        if (isNotZombies()) return
        if (getRound() == 1) return
        if (getPlayerState()[2] == 3) return
        scoreboard = mc.theWorld.scoreboard
        scoreObjective = scoreboard.getObjectiveInDisplaySlot(0) ?: return
        for (score in scoreboard.getSortedScores(scoreObjective))
            scoreMap[score.playerName] = score.scorePoints

        RepeatedTask(101) {
            val players = mutableListOf<String>()
            for (score in scoreboard.getSortedScores(scoreObjective)) {
                val kills = scoreMap[score.playerName] ?: continue
                if (score.scorePoints != kills) players.add(score.playerName)
            }

            if (players.isNotEmpty()) {
                printLast(players)
                return@RepeatedTask true
            }

            if (it == 1) addTranslationChat("zombiesaddon.features.notLast.failed")
            false
        }
    }

    private fun printLast(players: List<String>) {
        StringBuilder("§e").run {
            append(players[0])

            for (i in 1..players.lastIndex) {
                if (i == players.lastIndex) append(", ${getTranslatedString("zombiesaddon.features.notLast.or")}§e ${players[i]}")
                else append(", ${players[i]}")
            }

            addTranslationChat("zombiesaddon.features.notLast.printLast", toString())
        }
    }
}