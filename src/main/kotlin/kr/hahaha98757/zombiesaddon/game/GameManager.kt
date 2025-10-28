package kr.hahaha98757.zombiesaddon.game

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.events.GameEndEvent
import kr.hahaha98757.zombiesaddon.events.GameRemoveEvent
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.modules.AutoSplits
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.common.MinecraftForge

class GameManager {
    private val games = mutableMapOf<ServerNumber, Game>()

    val game get() = games[getServerNumber()]

    /**
     * @throws IllegalStateException 서버 번호 또는 맵을 알 수 없을 때
     */
    fun splitOrNew(round: Int, byCommand: Boolean = false) {
        val serverNumber = getServerNumber() ?: throw IllegalStateException("알 수 없는 서버 번호(Unknown Server Number)")
        if (serverNumber in games.keys) {
            if (round == 0) newGame(serverNumber, doNotCorrectTimer = byCommand)
            else games[serverNumber]?.pass(round, byCommand)
        } else newGame(serverNumber, round, byCommand)
    }

    private fun newGame(serverNumber: ServerNumber, round: Int = 0, doNotCorrectTimer: Boolean = false) {
        if (!mc.isCallingFromMinecraftThread) return
        val game = Game(getMap()?.getNormalGameMode() ?: throw IllegalStateException("알 수 없는 맵(Unknown Map)"), serverNumber, if (round == 0) 1 else round, doNotCorrectTimer)
        games[serverNumber] = game
        AutoSplits.startOrSplit()
        DelayedTask(20) { game.changeDifficulty(getDifficulty()!!) }
        MinecraftForge.EVENT_BUS.post(RoundStartEvent(game))
    }

    fun endGame(serverNumber: ServerNumber, isWin: Boolean) {
        val game = games[serverNumber] ?: return
        if (game.gameEnd) return // 중복 호출 방지
        game.gameEnd = true
        game.timer.stop = true
        game.isWin = isWin
        if (isWin) {
            when (game.gameMode.map) {
                ZombiesMap.DEAD_END, ZombiesMap.BAD_BLOOD, ZombiesMap.PRISON -> game.pass(30)
                ZombiesMap.ALIEN_ARCADIUM -> game.pass(105)
            }
        }
        if (isWin) AutoSplits.startOrSplit()
        else AutoSplits.pause()
        if (mc.isCallingFromMinecraftThread) MinecraftForge.EVENT_BUS.post(GameEndEvent(game, isWin))
        else mc.addScheduledTask { MinecraftForge.EVENT_BUS.post(GameEndEvent(game, isWin)) }
    }

    fun removeGame() {
        if (!ZombiesAddon.instance.debug && !isNotPlayZombies()) return
        val queuedEndedGames = games.values.filter { it.gameEnd }
        for (game in queuedEndedGames) {
            games.remove(game.serverNumber)
            MinecraftForge.EVENT_BUS.post(GameRemoveEvent(game))
        }
    }
}