package kr.hahaha98757.zombiesaddon.config

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.NAME
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory
import net.minecraftforge.fml.client.config.GuiConfig

@Suppress("unused")
class ZAGuiFactory: IModGuiFactory {
    override fun initialize(minecraft: Minecraft?) = Unit
    override fun mainConfigGuiClass(): Class<out GuiScreen> = ZAGuiConfig::class.java
    override fun runtimeGuiCategories(): MutableSet<IModGuiFactory.RuntimeOptionCategoryElement>? = null
    override fun getHandlerFor(runtimeOptionCategoryElement: IModGuiFactory.RuntimeOptionCategoryElement?): IModGuiFactory.RuntimeOptionGuiHandler? = null
}
class ZAGuiConfig(parentScreen: GuiScreen): GuiConfig(parentScreen, ZombiesAddon.instance.config.getElements(), MODID, false, false, "$NAME Configuration")