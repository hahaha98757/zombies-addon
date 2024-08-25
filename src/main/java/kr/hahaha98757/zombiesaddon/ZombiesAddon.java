package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.commands.*;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.listeners.*;
import kr.hahaha98757.zombiesaddon.listeners.autosplits.AutoSplitsListener;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Mod(modid = ZombiesAddon.MODID, name = ZombiesAddon.NAME, version = ZombiesAddon.VERSION, guiFactory = "kr.hahaha98757.zombiesaddon.config.ZombiesAddonGuiFactory")
public class ZombiesAddon {
	public static final String MODID = "zombiesaddon";
	public static final String NAME = "Zombies Addon";
	public static final String VERSION = "4.1.3";
	public static final String CONFIG_VERSION = "15";

	public static boolean isConfigReset;
	public static boolean oldSST;
	public static boolean detectUnlegit;

	private void writeCfgVersion() {
		try {
			Path filePath = Paths.get("config/zombiesaddonCfgVersion.txt");

			List<String> content = Arrays.asList("#DO NOT EDIT", CONFIG_VERSION);

			Files.write(filePath, content, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readCfgVersion() {
		try {
			Path filePath = Paths.get("config/zombiesaddonCfgVersion.txt");

			List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

			String str = lines.get(1);
			if (!str.equals(CONFIG_VERSION)) {
				resetConfig();
			}
		} catch (IOException e) {
			resetConfig();
		}
	}

	private void resetConfig() {
		String cfgFilePath = "config/zombiesaddon.cfg";

		File cfgFile = new File(cfgFilePath);

		if (cfgFile.exists()) {
			if (cfgFile.delete()) {
				isConfigReset = true;
			}
		}
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		readCfgVersion();
		writeCfgVersion();

		ZombiesAddonConfig.config = new Configuration(event.getSuggestedConfigurationFile());
		ZombiesAddonConfig.loadConfig();

		Keybinds.register();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new CommandInfo());
		ClientCommandHandler.instance.registerCommand(new CommandSLA());
		ClientCommandHandler.instance.registerCommand(new CommandZSV());
		ClientCommandHandler.instance.registerCommand(new CommandZSVLines());
		ClientCommandHandler.instance.registerCommand(new CommandPowerupPatterns());

		MinecraftForge.EVENT_BUS.register(new UpdateChecker());
		MinecraftForge.EVENT_BUS.register(new EventListener());
		MinecraftForge.EVENT_BUS.register(new CorneringListener());
		MinecraftForge.EVENT_BUS.register(new BlockAlarmListener());
		MinecraftForge.EVENT_BUS.register(new NotLastListener());
		MinecraftForge.EVENT_BUS.register(new AutoSplitsListener());
		MinecraftForge.EVENT_BUS.register(new SLAListener());
		MinecraftForge.EVENT_BUS.register(new ZSVListener());
		MinecraftForge.EVENT_BUS.register(new AutoRejoinListener());
		MinecraftForge.EVENT_BUS.register(new WaveDelaysListener());
		MinecraftForge.EVENT_BUS.register(new PowerupPatternsListener());
		MinecraftForge.EVENT_BUS.register(new ZombiesOverlayPatchListener());
		MinecraftForge.EVENT_BUS.register(new KoreanPatchListener());
		MinecraftForge.EVENT_BUS.register(new SSTPatchListener(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new LastWeaponsListener());
		MinecraftForge.EVENT_BUS.register(new TextMacroListener());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("ShowSpawnTime")) {
			oldSST = true;
		}
		if (ZombiesAddonConfig.detectUnlegitMods && (Loader.isModLoaded("zombiesatellite") || Loader.isModLoaded("zombiesexplorer") || Loader.isModLoaded("TeammatesOutline"))) {
			detectUnlegit = true;
		}
	}
}