package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ZombiesAddonGuiConfig extends GuiConfig {

	public ZombiesAddonGuiConfig(GuiScreen parentScreen) {
		super(parentScreen, ZombiesAddonConfig.getRootElements(), ZombiesAddon.MODID, false, false, ZombiesAddon.NAME + "Configuration");
	}
}