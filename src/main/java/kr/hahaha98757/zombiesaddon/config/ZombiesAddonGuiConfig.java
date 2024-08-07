package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ZombiesAddonGuiConfig extends GuiConfig {

	public ZombiesAddonGuiConfig(GuiScreen parent) {
		super(parent, getElements(), ZombiesAddon.MODID, false, false, "Zombies Addon Configuration");
	}

	private static List<IConfigElement> getElements() {
		List<IConfigElement> list = new ArrayList<>();

		list.add(new DummyConfigElement.DummyCategoryElement("Zombies Addon", "", new ArrayList<>(ZombiesAddonConfig.zombiesAddon.values())));
		list.add(new DummyConfigElement.DummyCategoryElement("Wave Delays", "", new ArrayList<>(ZombiesAddonConfig.waveDelays.values())));
		list.add(new DummyConfigElement.DummyCategoryElement("SLA", "", new ArrayList<>(ZombiesAddonConfig.sla.values())));
		list.add(new DummyConfigElement.DummyCategoryElement("Others", "", new ArrayList<>(ZombiesAddonConfig.others.values())));
		list.add(new DummyConfigElement.DummyCategoryElement("Patchers", "", new ArrayList<>(ZombiesAddonConfig.patchers.values())));
		//list.add(new DummyConfigElement.DummyCategoryElement("Hidden", "", new ArrayList<>(ZombiesAddonConfig.hidden.values())));
		return list;

	}
}