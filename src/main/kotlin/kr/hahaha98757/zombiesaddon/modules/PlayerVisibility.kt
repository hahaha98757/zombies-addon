package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.SemiPvMode
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.isDisable
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.client.event.RenderPlayerEvent
import net.minecraftforge.fml.common.eventhandler.Event
import kotlin.math.sqrt

object PlayerVisibility: ToggleableModule("Player Visibility", ZombiesAddon.instance.config.pvDefault) {
    override fun getKeyBinding() = ZombiesAddon.instance.keyBindings.togglePv
    override fun addToggleText(enabled: Boolean) =
        addTranslatedChat("zombiesaddon.modules.general.toggled", "Player Visibility", if (enabled) "§aon" else "§coff")

    override fun onEvent(event: Event) {
        if (event !is RenderPlayerEvent.Pre) return
        if (isNotPlayZombies()) return

        val player = event.entityPlayer
        if (player == mc.thePlayer) return
        if (player.isPlayerSleeping) return
        if (PvUtils.withoutRange(player)) return
        event.isCanceled = true
    }
}

object PvUtils {
    fun isSemiPv(other: Entity): Boolean {
        if (isDisable()) return false
        if (!PlayerVisibility.isEnable()) return false
        if (!ZombiesAddon.instance.config.pvToggleSemiPv) return false
        if (isNotPlayZombies()) return false
        if (other !is EntityPlayer) return false
        if (other == mc.thePlayer) return false
        if (other.isPlayerSleeping) return false
        if (!withoutRange(other)) return false
        if (mc.thePlayer.getDistanceToEntity(other) > ZombiesAddon.instance.config.pvSemiPvRange) return false
        if (other.isInvisibleToPlayer(mc.thePlayer)) return false
        return true
    }

    /** isSemiPv가 true인 경우에만 호출해야 함. */
    fun getAlpha(other: Entity): Float {
        if (!isSemiPv(other)) return 1.0f // 보험. 원래는 호출 전에 isSemiPv로 걸러야 함.
        val mode = ZombiesAddon.instance.config.pvSemiPvMode
        if (mode == SemiPvMode.FIXED)
            return ZombiesAddon.instance.config.pvSemiPvMinAlpha.toFloat()

        val distance = mc.thePlayer.getDistanceToEntity(other)
        val pvRange = ZombiesAddon.instance.config.pvRange
        val semiPvRange = ZombiesAddon.instance.config.pvSemiPvRange
        val minAlpha = ZombiesAddon.instance.config.pvSemiPvMinAlpha
        val maxAlpha = ZombiesAddon.instance.config.pvSemiPvMaxAlpha

        val ratio = (pvRange - distance) / (pvRange - semiPvRange)
        val factor = when (mode) {
            SemiPvMode.LINEAR -> ratio
            SemiPvMode.SMOOTH -> ratio * ratio
            SemiPvMode.SHARP -> sqrt(ratio)
            SemiPvMode.FIXED -> return minAlpha.toFloat() // 도달할 수 없는 구간
        }

        return (minAlpha + (maxAlpha - minAlpha) * factor).toFloat()
    }

    fun withoutRange(other: EntityPlayer) = mc.thePlayer.getDistanceToEntity(other) > ZombiesAddon.instance.config.pvRange
}