package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class DelayedTask(private var tick: Int = 2, private val block: () -> Unit) {
    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @Suppress("unused")
    @SubscribeEvent
    fun onTick(event: LastClientTickEvent) {
        if (tick <= 0) {
            MinecraftForge.EVENT_BUS.unregister(this)
            block()
        }
        tick--
    }
}