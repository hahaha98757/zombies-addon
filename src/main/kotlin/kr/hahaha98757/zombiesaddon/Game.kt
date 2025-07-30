package kr.hahaha98757.zombiesaddon

import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.enums.GameMode
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import net.minecraftforge.common.MinecraftForge

class Game(var gameMode: GameMode, val serverNumber: ServerNumber) {
    var round = 1
    val timer = Timer()
    var gameEnd = false
    var isWin = false
        get() = if (gameEnd) field else throw IllegalStateException("Game has not ended yet")
    var escape = false
    init {
        MinecraftForge.EVENT_BUS.register(TimerCorrector(this))
    }

    val roundData get() = gameMode.rounds[round - 1]

    fun changeDifficulty(difficulty: Difficulty) {
        gameMode = gameMode.appliedDifficulty(difficulty)
    }

    fun pass(round: Int) {
        if (round == 0) return
        if (round >= gameMode.rounds.size) return
        timer.run()
        if (gameEnd) return
        this.round = round + 1
        MinecraftForge.EVENT_BUS.post(RoundStartEvent(this))
    }

    fun helicopter() {
        if (gameMode.map != ZombiesMap.PRISON) return
        escape = true
        pass(30)
    }
}