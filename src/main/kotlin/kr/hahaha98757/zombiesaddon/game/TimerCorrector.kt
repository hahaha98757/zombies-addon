package kr.hahaha98757.zombiesaddon.game

import kr.hahaha98757.zombiesaddon.utils.Scoreboard
import kr.hahaha98757.zombiesaddon.utils.getServerNumber
import kr.hahaha98757.zombiesaddon.utils.logger
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.entity.monster.EntityZombie
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class TimerCorrector(private val game: Game) {
    @SubscribeEvent
    fun onWaveSpawn(event: EntityJoinWorldEvent) {
        if (event.entity == mc.thePlayer) {
            logger.info("타이머 보정을 취소합니다. (플레이어가 보정 전에 게임을 떠남)")
            MinecraftForge.EVENT_BUS.unregister(this)
            return
        }
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