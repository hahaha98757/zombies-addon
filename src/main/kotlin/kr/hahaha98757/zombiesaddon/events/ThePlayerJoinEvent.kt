package kr.hahaha98757.zombiesaddon.events

import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ThePlayerJoinEvent: Event()

class ThePlayerJoinEventListener {
    @SubscribeEvent
    fun onPlayerJoin(event: EntityJoinWorldEvent) {
        if (event.entity != mc.thePlayer) return
        MinecraftForge.EVENT_BUS.unregister(this)
        MinecraftForge.EVENT_BUS.post(ThePlayerJoinEvent())
    }
}