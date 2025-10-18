package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Status
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraft.scoreboard.ScoreObjective
import net.minecraft.scoreboard.Scoreboard

object NotLast: Module("Not Last") {
    private val scoreMap = mutableMapOf<String, Int>()
    private lateinit var scoreboard: Scoreboard
    private lateinit var scoreObjective: ScoreObjective

    override fun onRoundStart(event: RoundStartEvent) {
        if (AutoRejoin.isEnable()) return
        if (event.game.round == 1) return
        if (isNotPlayZombies()) return
        var quitCounter = 0
        for (i in getPlayerStatus()) if (i == Status.QUIT) quitCounter++
        if (quitCounter == 3) return

        scoreboard = mc.theWorld.scoreboard
        scoreObjective = scoreboard.getObjectiveInDisplaySlot(0) ?: return
        for (score in scoreboard.getSortedScores(scoreObjective))
            scoreMap[score.playerName] = score.scorePoints

        RepeatedTask(endTask = { addTranslatedChat("zombiesaddon.modules.notLast.failed") }) {
            val players = mutableListOf<String>()
            for (score in scoreboard.getSortedScores(scoreObjective)) {
                val kills = scoreMap[score.playerName] ?: continue
                if (score.scorePoints != kills) players += score.playerName
            }

            if (players.isNotEmpty()) {
                printLast(players)
                RepeatedTask.stop()
            }
        }
    }

    private fun printLast(players: List<String>) = StringBuilder().run {
        append(players[0].withNameColor())

        for (i in 1..players.lastIndex) if (i != players.lastIndex) append("§e, ${players[i].withNameColor()}")
        else append("§e, ${getTranslatedString("zombiesaddon.modules.notLast.or")} ${players[i].withNameColor()}")

        addTranslatedChat("zombiesaddon.modules.notLast.printLast", toString())
    }

    override fun isEnable() = ZombiesAddon.instance.config.toggleNotLast
}