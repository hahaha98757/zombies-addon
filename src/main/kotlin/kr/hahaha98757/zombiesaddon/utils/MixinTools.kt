package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.ServerTickEvent
import kr.hahaha98757.zombiesaddon.modules.PvUtils
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.Entity
import net.minecraft.network.play.server.S03PacketTimeUpdate
import net.minecraft.network.play.server.S29PacketSoundEffect
import net.minecraft.network.play.server.S45PacketTitle
import net.minecraftforge.common.MinecraftForge
import org.lwjgl.opengl.GL11

fun renderPre(entity: Entity) {
    if (!PvUtils.isSemiPv(entity)) return
    GlStateManager.color(1f, 1f, 1f, PvUtils.getAlpha(entity))
    GlStateManager.depthMask(false)
    GlStateManager.enableBlend()
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569f) // 0.003921569f = 1/255
}

fun renderPost(entity: Entity) {
    if (!PvUtils.isSemiPv(entity)) return
    GlStateManager.disableBlend()
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f)
    GlStateManager.depthMask(true)
}

private var aaR10 = false
fun onSound(packet: S29PacketSoundEffect) {
    if (!mc.isCallingFromMinecraftThread) return
    if (Scoreboard.isNotZombies) return
    val sound = packet.soundName
    ZombiesAddon.instance.gameManager.runCatching {
        if (sound == "mob.wither.spawn") {
            aaR10 = false
            splitOrNew(Scoreboard.round)
        } else if (sound == "mob.guardian.curse" && !aaR10) {
            aaR10 = true
            splitOrNew(Scoreboard.round)
        }
    }.onFailure {
        logger.error("게임 또는 라운드 시작에 실패했습니다.", it)
        addTranslatedChat("zombiesaddon.game.failed.splitOrNew", it.message ?: "알 수 없음(Unknown)")
    }
}

fun onTitle(packet: S45PacketTitle) {
    if (!mc.isCallingFromMinecraftThread) return
    if (packet.type != S45PacketTitle.Type.TITLE) return
    if (Scoreboard.isNotZombies) return
    val title = packet.message.unformattedText.trim().withoutColor()
    val serverNumber = getServerNumber() ?: return
    val isWin = when (title) {
        in win -> true
        in lose -> false
        else -> return
    }
    ZombiesAddon.instance.gameManager.endGame(serverNumber, isWin)
}

fun onXpPacket() {
    if (!mc.isCallingFromMinecraftThread) return
    MinecraftForge.EVENT_BUS.post(ServerTickEvent())
}

fun onTimeUpdate(packet: S03PacketTimeUpdate) {
    if (!mc.isCallingFromMinecraftThread) return
    ZombiesAddon.instance.gameManager.game?.timer?.onPacket(packet)
}