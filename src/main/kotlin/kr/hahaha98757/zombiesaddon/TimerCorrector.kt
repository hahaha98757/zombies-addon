package kr.hahaha98757.zombiesaddon

import kr.hahaha98757.zombiesaddon.utils.getServerNumber
import kr.hahaha98757.zombiesaddon.utils.isNotZombies
import net.minecraft.entity.monster.EntityZombie
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class TimerCorrector(private val game: Game) {
    @SubscribeEvent
    fun onWaveSpawn(event: EntityJoinWorldEvent) {
        if (event.entity !is EntityZombie) return
        if (isNotZombies()) return
        if (game.serverNumber == getServerNumber()) game.timer.correctStartTick(game.gameMode.rounds[0].waves[0].ticks)
        MinecraftForge.EVENT_BUS.unregister(this)
    }
}