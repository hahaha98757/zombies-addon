package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.data.Room
import kr.hahaha98757.zombiesaddon.enums.Map
import kr.hahaha98757.zombiesaddon.events.TitleEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.client.event.RenderGameOverlayEvent
import kotlin.math.pow

class SLAListener: AlwaysEnableModule("Spawn Limit Action") {
    companion object {
        val instance = SLAListener()
    }

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        SLA.sla?.refresh() ?: return
        val rooms = SLA.sla?.rooms ?: return
        var y = 0
        for (room in rooms) {
            if (!ZombiesAddon.instance.config.slaUnactivatedWindows && room.activeWindows == 0) continue
            fr.drawStringWithShadow(room.toString(), 1f, 1f + fr.FONT_HEIGHT*y++, 0xffffff)
        }
    }

    override fun onTitle(event: TitleEvent) {
        if (!ZombiesAddon.instance.config.slaAutoSLA) return
        if (!(event.title == "Round 1" || event.title == "1라운드")) return
        DelayedTask(10) {
            val map = getMap() ?: return@DelayedTask
            runCatching { SLA.on(map) }
        }
    }
}

class SLA(map: Map) {
    companion object {
        var sla: SLA? = null
            private set

        fun off() {
            sla = null
            addTranslationChat("zombiesaddon.commands.sla.setMap", "§coff")
        }

        fun on(map: Map) {
            sla = SLA(map)
            addTranslationChat("zombiesaddon.commands.sla.setMap", "§a$map")
        }
    }

    val rooms: Array<Room> = when (map) {
        Map.DEAD_END -> JsonLoader.loadJsonFromResource("data/sla/DE.json", Array<Room>::class.java)
        Map.BAD_BLOOD -> JsonLoader.loadJsonFromResource("data/sla/BB.json", Array<Room>::class.java)
        Map.ALIEN_ARCADIUM -> JsonLoader.loadJsonFromResource("data/sla/AA.json", Array<Room>::class.java)
        else -> throw IllegalArgumentException("$map has no SLA")
    }
    private var offset = IntArray(3)
    private var rotations = 0
    private var mirroring = booleanArrayOf(false, false)

    fun resetRotate() {
        rotate(4 - rotations, true)
        addTranslationChat("zombiesaddon.commands.sla.resetRotations")
    }

    fun rotate(rotations: Int, reset: Boolean = false) {
        var newRotations = rotations % 4
        if (newRotations < 0) newRotations += 4
        sla?.run {
            this.rotations = (this.rotations + newRotations) % 4
            for (room in rooms) for (window in room.windows) window.rotate(newRotations)
            if (reset) return
            when (newRotations) {
                0 -> addTranslationChat("zombiesaddon.commands.sla.rotates", "§a0°")
                1 -> addTranslationChat("zombiesaddon.commands.sla.rotates", "§a90°")
                2 -> addTranslationChat("zombiesaddon.commands.sla.rotates", "§a180°")
                3 -> addTranslationChat("zombiesaddon.commands.sla.rotates", "§a270°")
            }
        }
    }

    fun resetMirroring() {
        if (mirroring[0]) mirrorX(true)
        if (mirroring[1]) mirrorZ(true)
        addTranslationChat("zombiesaddon.commands.sla.resetMirroring")
    }

    fun mirrorX(reset: Boolean = false) {
        mirroring[0] = !mirroring[0]
        for (room in rooms) for (window in room.windows) window.mirrorX()
        if (reset) return
        addTranslationChat("zombiesaddon.commands.sla.mirror", "§a0 y z")
    }

    fun mirrorZ(reset: Boolean = false) {
        mirroring[1] = !mirroring[1]
        for (room in rooms) for (window in room.windows) window.mirrorZ()
        if (reset) return
        addTranslationChat("zombiesaddon.commands.sla.mirror", "§a0 x z")
    }

    fun resetOffset() {
        offset = IntArray(3)
        addTranslationChat("zombiesaddon.commands.sla.setOffset", "§coff")
    }

    fun setOffset(x: Int, y: Int, z: Int) {
        offset[0] = x
        offset[1] = y
        offset[2] = z
        addTranslationChat("zombiesaddon.commands.sla.setOffset", "§a$x $y $z")
    }

    fun refresh() {
        val player = mc.thePlayer
        val playerCoords = doubleArrayOf((player.posX - offset[0]), (player.posY - offset[1]), (player.posZ - offset[2]))
        for (room in rooms) {
            room.activeWindows = 0
            for (window in room.windows) {
                /*
                창문의 좌표는 정수로 표기하기 위해 2배하여 저장되어 있음.
                따라서 플레이어의 좌표도 2배하여 비교해야 함.
                distance는 (2x)^2 + (2y)^2 + (2z)^2 = 4(x^2 + y^2 + z^2) 이다.
                SLA 범위는 50m이므로 4*50^2 = 10000이다.
                이 둘을 비교하여 SLA 범위에 있는지 확인한다.
                 */
                var distance = 0.0
                for (i in 0..2) distance += (playerCoords[i]*2 - window.xyz[i]).pow(2)
                if (distance < 10000) {
                    window.active = true
                    room.activeWindows++
                } else window.active = false
            }
        }
    }
}