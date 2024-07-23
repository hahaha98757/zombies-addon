package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.commands.*;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.ConfigChangedHandler;
import kr.hahaha98757.zombiesaddon.handler.PacketClientConnectHandler;
import kr.hahaha98757.zombiesaddon.handler.RenderGameOverlayHandler;
import kr.hahaha98757.zombiesaddon.handler.RenderTimerHandler;
import kr.hahaha98757.zombiesaddon.listeners.*;
import kr.hahaha98757.zombiesaddon.listeners.sla.AutoSLAListener;
import kr.hahaha98757.zombiesaddon.packet.AutoSplitPacketInterceptor;
import kr.hahaha98757.zombiesaddon.packet.PacketInterceptor;
import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Mod(modid = ZombiesAddon.MODID, name = ZombiesAddon.NAME, version = ZombiesAddon.VERSION, guiFactory = "kr.hahaha98757.zombiesaddon.config.ZombiesAddonGuiFactory")
public class ZombiesAddon {
	public static final String MODID = "zombiesaddon";
	public static final String NAME = "Zombies Addon";
	public static final String VERSION = "4.1.0-pre2";
	public static final String CONFIG_VERSION = "15";

	@Instance(MODID)
	public static ZombiesAddon instance;

	public static Logger logger;

	private AutoSplitPacketInterceptor packetInterceptor;
	private InternalSplitter internalSplitter;
	private RenderTimerHandler renderTimeHandler;
	private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public static boolean isConfigReset;
	public static boolean oldSST;
	public static boolean detectUnlegit;

	public static ZombiesAddon getInstance() {
		return instance;
	}

	public Logger getLogger() {
		return logger;
	}

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

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		readCfgVersion();
		writeCfgVersion();
		logger = event.getModLog();

		ZombiesAddonConfig.config = new Configuration(event.getSuggestedConfigurationFile());
		ZombiesAddonConfig.loadConfig();

		Keybinds.register();

		LiveSplitSplitter splitter = new InternalSplitter(executor);
		packetInterceptor = new AutoSplitPacketInterceptor(Minecraft.getMinecraft(), logger, splitter);
		if (splitter instanceof InternalSplitter) {
			internalSplitter = (InternalSplitter) splitter;
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new CommandInfo());
		ClientCommandHandler.instance.registerCommand(new CommandCornering());
		ClientCommandHandler.instance.registerCommand(new CommandZSV());
		ClientCommandHandler.instance.registerCommand(new CommandZSVLines());
		ClientCommandHandler.instance.registerCommand(new CommandSLA());
		ClientCommandHandler.instance.registerCommand(new CommandPowerupAlarm());

		MinecraftForge.EVENT_BUS.register(new UpdateChecker());
		MinecraftForge.EVENT_BUS.register(new ConfigChangedHandler(this));
		MinecraftForge.EVENT_BUS.register(new EventListener());
		MinecraftForge.EVENT_BUS.register(new CorneringListener());
		MinecraftForge.EVENT_BUS.register(new BlockAlarmListener());
		MinecraftForge.EVENT_BUS.register(new WaveDelaysListener());
		MinecraftForge.EVENT_BUS.register(new PowerupAlarmListener());
		MinecraftForge.EVENT_BUS.register(new ZSVListener());
		MinecraftForge.EVENT_BUS.register(new AutoSLAListener());
		MinecraftForge.EVENT_BUS.register(new KoreanPatchListener());
		MinecraftForge.EVENT_BUS.register(new SSTPatchListener());
		MinecraftForge.EVENT_BUS.register(new LastWeaponsListener());

		RoundListener roundListener = new RoundListener();
		MinecraftForge.EVENT_BUS.register(roundListener);
		MinecraftForge.EVENT_BUS.register(new TitleListener());

		MinecraftForge.EVENT_BUS.register(new RenderGameOverlayHandler());

		Iterable<PacketInterceptor> interceptors = Collections.singleton(packetInterceptor);
		MinecraftForge.EVENT_BUS.register(new PacketClientConnectHandler(interceptors));
		MinecraftForge.EVENT_BUS.register(new ConfigChangedHandler(this));

		Minecraft minecraft = Minecraft.getMinecraft();
		int color = 0xFFFFFF;
		renderTimeHandler = new RenderTimerHandler(minecraft, minecraft.fontRendererObj, color);
		if (internalSplitter != null) {
			renderTimeHandler.setSplitter(internalSplitter);
		}
		MinecraftForge.EVENT_BUS.register(renderTimeHandler);

		instance = this;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("ShowSpawnTime")) {
			oldSST = true;
		}
		if (Loader.isModLoaded("zombiesatellite") || Loader.isModLoaded("zombiesexplorer") || Loader.isModLoaded("TeammatesOutline")) {
			detectUnlegit = true;
		}
	}
}