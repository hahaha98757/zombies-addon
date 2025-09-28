package kr.hahaha98757.zombiesaddon.modules

import com.seosean.showspawntime.ShowSpawnTime
import kr.hahaha98757.zombiesaddon.NAME
import kr.hahaha98757.zombiesaddon.VERSION
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object General: AlwaysEnableModule("General") {

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

        str = "Player Visibility: ${if (PlayerVisibility.isEnable()) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.pvText) fr.drawStringWithShadow(str, HudUtils.getToggleTextStrX(str), HudUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Block Alarm: ${if (BlockAlarm.isEnable()) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.blockAlarmText) fr.drawStringWithShadow(str, HudUtils.getToggleTextStrX(str), HudUtils.getToggleTextStrY(i++), 0xffff55)

        str = "Auto Rejoin: ${if (AutoRejoin.isEnable()) "§aon" else "§coff"}"
        if (ZombiesAddon.instance.config.autoRejoinText) fr.drawStringWithShadow(str, HudUtils.getToggleTextStrX(str), HudUtils.getToggleTextStrY(i), 0xffff55)
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

class ThePlayerJoinHandler {
    @SubscribeEvent
    fun onPlayerJoin(event: EntityJoinWorldEvent) {
        if (event.entity != mc.thePlayer) return
        MinecraftForge.EVENT_BUS.unregister(this)

        val za = ZombiesAddon.instance

        if (!za.config.blockUnlegitMods) addTranslationChatLine("zombiesaddon.messages.offBlockUnlegitMods")
        if (za.hasSST && !za.config.blockUnlegitSst) addTranslationChatLine("zombiesaddon.messages.offBlockUnlegitSST")

        if (za.hasSST) {
            val sstVer = runCatching { ShowSpawnTime.VERSION }.getOrNull() ?: "Unknown"
            if (sstVer != "2.1.1") addTranslationChatLine("zombiesaddon.messages.sstDiffVer", sstVer)
        }
        if (za.hasZombiesUtils) {
            val zuVer = runCatching { Loader.instance().indexedModList["zombiesutils"]!!.metadata.version }.getOrNull() ?: "Unknown"
            if (zuVer != "1.3.7") addTranslationChatLine("zombiesaddon.messages.zuDiffVer", zuVer)
        }
    }
}