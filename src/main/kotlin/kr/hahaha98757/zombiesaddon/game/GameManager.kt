package kr.hahaha98757.zombiesaddon.game

import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.events.GameEndEvent
import kr.hahaha98757.zombiesaddon.events.GameRemoveEvent
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.getMap
import kr.hahaha98757.zombiesaddon.utils.getServerNumber
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import net.minecraftforge.common.MinecraftForge

class GameManager {
    private val games = mutableMapOf<ServerNumber, Game>()
    private var queuedDifficulty: Difficulty? = null
    private var queuedServerNumber: ServerNumber? = null

    val game get() = games[getServerNumber()]

    fun splitOrNew(round: Int) {
        val serverNumber = getServerNumber() ?: throw IllegalStateException("Unknown server number")
        if (serverNumber in games.keys) {
            if (round == 0) newGame(serverNumber)
            else games[serverNumber]?.pass(round)
        } else newGame(serverNumber)
    }

    private fun newGame(serverNumber: ServerNumber) {
        val game = Game(getMap()?.getNormalGameMode() ?: throw IllegalStateException("Unknown map"), serverNumber)
        if (serverNumber == queuedServerNumber) queuedDifficulty?.let { game.changeDifficulty(it) }
        queuedDifficulty = null
        games[serverNumber] = game
        MinecraftForge.EVENT_BUS.post(RoundStartEvent(game))
    }

    fun endGame(serverNumber: ServerNumber, isWin: Boolean) {
        val game = games[serverNumber] ?: return
        if (isWin) {
            when (game.gameMode.map) {
                ZombiesMap.DEAD_END, ZombiesMap.BAD_BLOOD, ZombiesMap.PRISON -> game.pass(30)
                ZombiesMap.ALIEN_ARCADIUM -> game.pass(105)
            }
        }
        game.gameEnd = true
        game.isWin = isWin
        MinecraftForge.EVENT_BUS.post(GameEndEvent(game, isWin))
    }

    fun setDifficulty(difficulty: Difficulty) {
        queuedServerNumber = getServerNumber()
        games[queuedServerNumber]?.changeDifficulty(difficulty) ?: run { queuedDifficulty = difficulty }
    }

    fun removeGame() {
        if (isNotPlayZombies()) return
        val queuedEndedGames = games.values.filter { it.gameEnd }
        for (game in queuedEndedGames) {
            games.remove(game.serverNumber)
            MinecraftForge.EVENT_BUS.post(GameRemoveEvent(game))
        }
    }
}