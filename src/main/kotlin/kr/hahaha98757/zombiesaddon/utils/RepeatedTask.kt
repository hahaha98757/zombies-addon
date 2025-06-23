package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class RepeatedTask(private var tick: Int = 100, private val block: (Int) -> Boolean) {

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onTick(event: LastClientTickEvent) {
        if (tick <= 0) {
            MinecraftForge.EVENT_BUS.unregister(this)
            return
        }
        if (block(tick)) MinecraftForge.EVENT_BUS.unregister(this)
        tick--
    }
}