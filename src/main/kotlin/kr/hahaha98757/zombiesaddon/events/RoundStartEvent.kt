package kr.hahaha98757.zombiesaddon.events

import kr.hahaha98757.zombiesaddon.utils.isDisable
import kr.hahaha98757.zombiesaddon.utils.isNotZombies
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class RoundStartEvent: Event()

class RoundStartEventListener {
    private var aaR10 = false

    @SubscribeEvent
    fun onSound(event: SoundEvent) {
        if (isDisable()) return
        if (isNotZombies()) return
        val sound = event.sound
        if (sound == "minecraft:mob.wither.spawn") {
            aaR10 = false
            MinecraftForge.EVENT_BUS.post(RoundStartEvent())
        } else if (sound == "minecraft:mob.guardian.curse" && !aaR10) {
            aaR10 = true
            MinecraftForge.EVENT_BUS.post(RoundStartEvent())
        }
    }
}