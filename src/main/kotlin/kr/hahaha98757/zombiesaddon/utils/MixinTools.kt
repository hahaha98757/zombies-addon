package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.Entity
import net.minecraft.network.play.server.S29PacketSoundEffect
import net.minecraft.network.play.server.S45PacketTitle
import org.lwjgl.opengl.GL11

internal fun renderPre(entity: Entity) {
    if (!PlayerVisibility.isSemiPv(entity)) return
    GlStateManager.color(1f, 1f, 1f, PlayerVisibility.getAlpha(entity))
    GlStateManager.depthMask(false)
    GlStateManager.enableBlend()
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569f) // 0.003921569f = 1/255
}

internal fun renderPost(entity: Entity) {
    if (!PlayerVisibility.isSemiPv(entity)) return
    GlStateManager.disableBlend()
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f)
    GlStateManager.depthMask(true)
}

private var aaR10 = false
internal fun onSound(packet: S29PacketSoundEffect) {
    if (isNotZombies()) return
    val sound = packet.soundName
    ZombiesAddon.instance.gameManager.runCatching {
        if (sound == "mob.wither.spawn") {
            aaR10 = false
            startOrNew(Scoreboard.round)
        } else if (sound == "mob.guardian.curse" && !aaR10) {
            aaR10 = true
            startOrNew(Scoreboard.round)
        }
    }.onFailure { it.printStackTrace() }
}

internal fun onTitle(packet: S45PacketTitle) {
    if (packet.type != S45PacketTitle.Type.TITLE) return
    if (isNotZombies()) return
    val title = packet.message.unformattedText.trim()
    val serverNumber = getServerNumber() ?: return
    if (title == "You Win!" || title == "승리했습니다.") ZombiesAddon.instance.gameManager.endGame(serverNumber, true)
    else if (title == "Game Over!" || title == "게임 끝!") ZombiesAddon.instance.gameManager.endGame(serverNumber, false)
}