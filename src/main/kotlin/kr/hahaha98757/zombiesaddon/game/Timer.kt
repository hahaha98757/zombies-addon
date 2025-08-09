package kr.hahaha98757.zombiesaddon.game

import kr.hahaha98757.zombiesaddon.utils.mc

class Timer {
    private var startTick = currentTick
    private var roundStartTick = 0
    private var worldTime = 0L
    var stop = false

    val gameTick get() = (currentTick - startTick).toInt()

    val roundTick get() = gameTick - roundStartTick

    fun split() {
        roundStartTick = gameTick
    }

    fun correctStartTick(tick: Int) {
        startTick = currentTick - tick
    }

    private val currentTick: Long get() {
        if (mc.theWorld == null) return 0
        if (!stop) worldTime = mc.theWorld.totalWorldTime
        return worldTime
    }
}