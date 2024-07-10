//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.handler;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class ConfigChangedHandler {

	private final ZombiesAddon ZombiesAddon;

	public ConfigChangedHandler(ZombiesAddon ZombiesAddon) {
		this.ZombiesAddon = Objects.requireNonNull(ZombiesAddon, "ZombiesAddon");
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(kr.hahaha98757.zombiesaddon.ZombiesAddon.MODID)) {
			ZombiesAddonConfig.reloadConfig();
		}
	}

}