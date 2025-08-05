package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11

internal fun renderPre(entityIn: Entity) {
    if (!PlayerVisibility.isSemiPv(entityIn)) return
    GlStateManager.color(1f, 1f, 1f, PlayerVisibility.getAlpha(entityIn))
    GlStateManager.depthMask(false)
    GlStateManager.enableBlend()
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569f) // 0.003921569f = 1/255
}

internal fun renderPost(entityIn: Entity) {
    if (!PlayerVisibility.isSemiPv(entityIn)) return
    GlStateManager.disableBlend()
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f)
    GlStateManager.depthMask(true)
}