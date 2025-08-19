package kr.hahaha98757.zombiesaddon.game

import kr.hahaha98757.zombiesaddon.utils.Scoreboard
import kr.hahaha98757.zombiesaddon.utils.getServerNumber
import kr.hahaha98757.zombiesaddon.utils.logger
import net.minecraft.entity.monster.EntityZombie
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class TimerCorrector(private val game: Game) {
    @SubscribeEvent
    fun onWaveSpawn(event: EntityJoinWorldEvent) {
        if (event.entity !is EntityZombie) return
        if (Scoreboard.isNotZombies) return
        if (game.serverNumber == getServerNumber()) {
            val ticks = game.gameMode.rounds[0].waves[0].ticks
            game.timer.correctStartTick(ticks)
            logger.debug("타이머를 $ticks 틱으로 보정했습니다.")
        }
        MinecraftForge.EVENT_BUS.unregister(this)
    }
}