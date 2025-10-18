package kr.hahaha98757.zombiesaddon.game

import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.enums.GameMode
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.modules.AutoSplits
import kr.hahaha98757.zombiesaddon.modules.recorder.Recorder
import kr.hahaha98757.zombiesaddon.modules.recorder.files.GameFile
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.logger
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraftforge.common.MinecraftForge

class Game(var gameMode: GameMode, val serverNumber: ServerNumber, var round: Int, doNotCorrectTimer: Boolean = false) {

    val timer = Timer()
    var gameEnd = false
    var isWin = false
        get() = if (gameEnd) field else throw IllegalStateException("게임이 아직 종료되지 않았습니다.")
    var escape = false
    val isFullRecorded = (round == 1)
    val gameFile = GameFile(serverNumber, gameMode.map)
    private val recorder = Recorder(this)

    init {
        if (!doNotCorrectTimer) MinecraftForge.EVENT_BUS.register(TimerCorrector(this))
    }

    val roundData get() = gameMode.rounds[round - 1]

    fun changeDifficulty(difficulty: Difficulty) {
        gameMode = gameMode.appliedDifficulty(difficulty)
    }

    fun pass(round: Int, byCommand: Boolean = false) {
        if (round == 0) return
        if (!byCommand && this.round == round + 1) return // 중복 호출 방지
        if (round > gameMode.rounds.size) return
        recorder.runCatching { record() }.onFailure {
            logger.error("게임 기록을 실패했습니다.", it)
            addTranslatedChat("zombiesaddon.modules.recorder.failed")
        }
        if (gameEnd) return
        timer.split()
        this.round = round + 1
        AutoSplits.startOrSplit()
        if (mc.isCallingFromMinecraftThread) MinecraftForge.EVENT_BUS.post(RoundStartEvent(this))
        else mc.addScheduledTask { MinecraftForge.EVENT_BUS.post(RoundStartEvent(this)) }
    }

    fun helicopter() {
        if (gameMode.map != ZombiesMap.PRISON) return
        escape = true
        pass(30)
    }
}