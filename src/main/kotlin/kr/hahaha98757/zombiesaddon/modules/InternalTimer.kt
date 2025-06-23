package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.SoundEvent
import kr.hahaha98757.zombiesaddon.utils.HUDUtils
import kr.hahaha98757.zombiesaddon.utils.fr
import kr.hahaha98757.zombiesaddon.utils.isNotZombies
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

class InternalTimer: Module("Internal Timer", true) {
    companion object {
        val instance = InternalTimer()
    }
    val ticks get() = (millis / 50).toInt()

    private var millis = 0L
    private val lock = ReentrantLock()
    private var future: Future<*>? = null
    private var stopTimer = false

    private var aaR10 = false

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotZombies()) return
        if (!ZombiesAddon.instance.config.toggleInternalTimer) return

        val millis = millis
        val minutesPart = millis / 60000
        val secondsPart = (millis % 60000) / 1000
        val tenthSecondsPart = (millis % 1000) / 100
        val timer = String.format("%d:%02d.%d", minutesPart, secondsPart, tenthSecondsPart)
        fr.drawStringWithShadow(timer, HUDUtils.getAutoSplitsStrX(timer), HUDUtils.getAutoSplitsStrY(), 0xffffff)
    }

    override fun onSound(event: SoundEvent) {
        val sound = event.sound
        if (sound == "minecraft:mob.wither.spawn") {
            aaR10 = false
            run()
        } else if (sound == "minecraft:mob.enderdragon.end") {
            aaR10 = false
            stop()
        } else if (sound == "minecraft:mob.guardian.curse" && !aaR10) {
            aaR10 = true
            run()
        }
    }

    override fun onChat(event: ClientChatReceivedEvent) {
        if (isNotZombies()) return
        if ("The Helicopter is on its way! Hold out for 120 more seconds!" !in event.message.unformattedText) return
        run()
    }

    fun run() {
        lock.lock()
        try {
            if (future == null) future = executor.scheduleAtFixedRate({
                if (stopTimer) return@scheduleAtFixedRate
                lock.lock()
                try {
                    millis += 10
                } finally {
                    lock.unlock()
                }
            }, 0, 10, TimeUnit.MILLISECONDS)
            else {
                stopTimer = false
                millis = 0
            }
        } finally {
            lock.unlock()
        }
    }

    fun stop() {
        if (stopTimer) return
        lock.lock()
        try {
            stopTimer = true
        } finally {
            lock.unlock()
        }
    }

    fun reset() {
        lock.lock()
        try {
            millis = 0
            stopTimer = true
            future?.cancel(false)
            future = null
        } finally {
            lock.unlock()
        }
    }
}