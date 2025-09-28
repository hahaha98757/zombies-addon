package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.isDisable
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.client.event.RenderPlayerEvent
import net.minecraftforge.fml.common.eventhandler.Event

object PlayerVisibility: ToggleableModule("Player Visibility", ZombiesAddon.instance.config.pvDefault) {
    override fun getKeyBinding() = ZombiesAddon.instance.keyBindings.togglePv
    override fun addToggleText(enabled: Boolean) =
        addTranslationChat("zombiesaddon.modules.general.toggled", "§ePlayer Visibility", if (enabled) "§aon" else "§coff")

    override fun onEvent(event: Event) {
        if (event !is RenderPlayerEvent.Pre) return
        if (isNotPlayZombies()) return

        val player = event.entityPlayer
        if (player == mc.thePlayer) return
        if (player.isPlayerSleeping) return
        if (PVUtils.withoutRange(player)) return
        event.isCanceled = true
    }
}

object PVUtils {
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

    fun getAlpha(other: Entity): Float {
        val distance = mc.thePlayer.getDistanceToEntity(other)
        val pvRange = ZombiesAddon.instance.config.pvRange
        val semiPvRange = ZombiesAddon.instance.config.pvSemiPvRange

        return ((pvRange - distance) / (pvRange - semiPvRange)).toFloat()
        /*
        이게 뭔 뜻이냐.
        pvRange를 a, semiPvRange를 b, distance를 x, 반환값을 y라고 하면.
        y = (a - x) / (a - b)인데, 이걸 변형하면.
        y = 1/(b-a) (x-a)가 된다.
        이는 기울기가 1/(b-a)이고 항상 (0, a)를 지나는 직선이다.
        x가 b와 a의 거리만큼 증가하면 y가 1만큼 증가한다. 즉, pvRange와 semiPvRange사이의 범위에서 투명도가 일정하게 변화한다.
        (0, a)를 지나므로 pvRange에서 투명도가 0이 된다.
        지랄랄레로 지랄랄라
        이래서 수학을 모르면 이해하기 힘들다. 사람살려
         */
    }

    fun withoutRange(other: EntityPlayer) = mc.thePlayer.getDistanceToEntity(other) > ZombiesAddon.instance.config.pvRange
}