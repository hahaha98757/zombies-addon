package kr.hahaha98757.zombiesaddon.events

import net.minecraft.util.IChatComponent
import net.minecraftforge.fml.common.eventhandler.Event

class ClientChatPrintedEvent(val message: IChatComponent): Event() {
    override fun isCancelable() = true
}