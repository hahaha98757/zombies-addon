package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.NAME
import kr.hahaha98757.zombiesaddon.VERSION
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.events.SoundEvent
import kr.hahaha98757.zombiesaddon.events.TitleEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class Modules: AlwaysEnableModule("Modules") {
    companion object {
        val instance = Modules()
    }
    private var aaR10 = false

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        var i = 1

        val color = when {
            !ZombiesAddon.instance.config.blockUnlegitSst -> 0xff5555
            !ZombiesAddon.instance.config.blockUnlegitMods -> 0xff5555
            ZombiesAddon.instance.debug -> 0x55ff55
            else -> 0xffff55
        }
        var str = "$NAME v$VERSION"
        fr.drawStringWithShadow(str, HudUtils.getModNameStrX(str), HudUtils.getModNameStrY(),  color)

        str = "Player Visibility: ${if (PlayerVisibility.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.pvText) fr.drawStringWithShadow(str, HudUtils.getToggleTextStrX(str), HudUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Block Alarm: ${if (BlockAlarm.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.blockAlarmText) fr.drawStringWithShadow(str, HudUtils.getToggleTextStrX(str), HudUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Auto Rejoin: ${if (AutoRejoin.instance.enabled) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.autoRejoinText) fr.drawStringWithShadow(str, HudUtils.getToggleTextStrX(str), HudUtils.getToggleTextStrY(i), 0xffff55)
    }

    override fun onSound(event: SoundEvent) {
        val sound = event.sound
        ZombiesAddon.instance.gameManager.runCatching {
            if (sound == "minecraft:mob.wither.spawn") {
                aaR10 = false
                startOrNew(Scoreboard.round)
            } else if (sound == "minecraft:mob.guardian.curse" && !aaR10) {
                aaR10 = true
                startOrNew(Scoreboard.round)
            }
        }.onFailure { it.printStackTrace() }
    }

    override fun onTitle(event: TitleEvent) {
        val title = event.title
        val serverNumber = getServerNumber() ?: return
        if (title == "You Win!" || title == "승리했습니다.") ZombiesAddon.instance.gameManager.endGame(serverNumber, true)
        else if (title == "Game Over!" || title == "게임 끝!") ZombiesAddon.instance.gameManager.endGame(serverNumber, false)
    }

    override fun onChat(event: ClientChatReceivedEvent) {
        val message = event.message.unformattedText
        if (">" in message) return
        val gameManager = ZombiesAddon.instance.gameManager

        if ("Hard Difficulty" in message || "Hard 난이도" in message) gameManager.setDifficulty(Difficulty.HARD)
        else if ("RIP Difficulty" in message || "RIP 난이도" in message) gameManager.setDifficulty(Difficulty.RIP)
        else if ("The Helicopter is on its way! Hold out for 120 more seconds!" in message) gameManager.game?.helicopter()
    }

    override fun onLastTick(event: LastClientTickEvent) {
        Scoreboard.refresh()
        if (!ZombiesAddon.instance.debug) ZombiesAddon.instance.gameManager.removeGame()
    }
}

class ThePlayerJoinListener() {
    @SubscribeEvent
    fun onPlayerJoin(event: EntityJoinWorldEvent) {
        if (event.entity != mc.thePlayer) return
        MinecraftForge.EVENT_BUS.unregister(this)

        if (!ZombiesAddon.instance.config.blockUnlegitMods) addTranslationChatLine("zombiesaddon.messages.offBlockUnlegitMods")
        if (!ZombiesAddon.instance.config.blockUnlegitSst) addTranslationChatLine("zombiesaddon.messages.offBlockUnlegitSST")
        else addTranslationChatLine("zombiesaddon.messages.blockUnlegitSST")
    }
}