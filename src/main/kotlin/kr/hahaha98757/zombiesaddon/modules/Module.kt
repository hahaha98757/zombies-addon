package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.events.SoundEvent
import kr.hahaha98757.zombiesaddon.events.TitleEvent
import kr.hahaha98757.zombiesaddon.utils.isDisable
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent

abstract class Module(val name: String, enabled: Boolean) {
    var enabled = enabled
        set (value) {
            field = value
            if (!value) onDisable()
        }

    internal open fun occurEvent(event: Event) {
        if (isDisable()) return
        if (!enabled) return
        when (event) {
            is LastClientTickEvent -> onLastTick(event)
            is RenderGameOverlayEvent.Text -> onRender(event)
            is SoundEvent -> onSound(event)
            is TitleEvent -> onTitle(event)
            is RoundStartEvent -> onRoundStart(event)
            is ClientChatReceivedEvent -> onChat(event)
            is KeyInputEvent -> onKeyInput(event)
            else -> onEvent(event)
        }
    }

    protected open fun onDisable() {}
    protected open fun onLastTick(event: LastClientTickEvent) {}
    protected open fun onRender(event: RenderGameOverlayEvent.Text) {}
    protected open fun onSound(event: SoundEvent) {}
    protected open fun onTitle(event: TitleEvent) {}
    protected open fun onRoundStart(event: RoundStartEvent) {}
    protected open fun onChat(event: ClientChatReceivedEvent) {}
    protected open fun onKeyInput(event: KeyInputEvent) {}
    protected open fun onEvent(event: Event) {}
}

abstract class ToggleModule(name: String, enabled: Boolean): Module(name, enabled) {
    abstract fun getKeyBinding(): KeyBinding
    abstract fun addToggleText(enabled: Boolean)

    final override fun occurEvent(event: Event) {
        if (isDisable()) return
        when (event) {
            is KeyInputEvent -> onKeyInput(event)
            else -> {
                if (!enabled) return
                super.occurEvent(event)
            }
        }
    }

    final override fun onKeyInput(event: KeyInputEvent) {
        if (getKeyBinding().isPressed) {
            enabled = !enabled
            addToggleText(enabled)
        }
    }


}

class ModuleListener {
    companion object {
        private val modules = mutableListOf<Module>()

        fun registerModule(module: Module) {
            modules += module
        }
    }

    @SubscribeEvent
    fun onEvent(event: Event) {
        for (module in modules) module.occurEvent(event)
    }
}