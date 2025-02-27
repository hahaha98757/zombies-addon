package kr.hahaha98757.zombiesaddon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seosean.showspawntime.ShowSpawnTime;
import kr.hahaha98757.zombiesaddon.commands.*;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.wavedelays.CustomPlaySound;
import kr.hahaha98757.zombiesaddon.features.Features;
import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.*;
import java.nio.file.Files;

@Mod(modid = ZombiesAddon.MODID, name = ZombiesAddon.NAME, version = ZombiesAddon.VERSION, guiFactory = "kr.hahaha98757.zombiesaddon.config.ZombiesAddonGuiFactory")
public class ZombiesAddon {
	public static final String MODID = "zombiesaddon";
	public static final String NAME = "Zombies Addon";
	public static final String VERSION = "4.3.0";
	private static File directory;

	public static boolean hasOldSST;
	public static boolean haveUnlegitMods;
	public static boolean hasSST;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		directory = new File(event.getModConfigurationDirectory(), MODID);

		if (!directory.exists()) //noinspection ResultOfMethodCallIgnored
            directory.mkdir();

		writeCustomPlaySoundGuide();
		writeCustomPlaySoundGuideKo_KR();
		writeCustomPlaySoundJson();

		ZombiesAddonConfig.config = new Configuration(new File(directory, MODID + ".cfg"));
		ZombiesAddonConfig.loadConfig();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new UpdateChecker());
		KeyBindings.registerAll();
		Commands.registerAll();
		Features.registerAll();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("ShowSpawnTime")) hasOldSST = true;
        //noinspection SpellCheckingInspection
        if (ZombiesAddonConfig.isDetectUnlegitMods() && (Loader.isModLoaded("zombiesatellite") || Loader.isModLoaded("zombiesexplorer") || Loader.isModLoaded("TeammatesOutline") || Loader.isModLoaded("zombieshelper")))
            haveUnlegitMods = true;
		HUDUtils.set();
		Utils.set();
		if (Loader.isModLoaded("showspawntime")) {
			hasSST = ZombiesAddonConfig.isBlockUnlegitSST();
			try {
				ShowSpawnTime.getMainConfiguration().ConfigLoad();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(NAME + " v" + VERSION + " is loaded.");
	}

	private void writeCustomPlaySoundGuide() {
		File file = new File("config/zombiesaddon/CustomPlaySoundGuide.txt");

		//noinspection ResultOfMethodCallIgnored
		file.getParentFile().mkdirs();

		try (InputStream in = getClass().getClassLoader().getResourceAsStream("assets/" + MODID + "/data/text/CustomPlaySoundGuide.txt");
			 OutputStream out = Files.newOutputStream(file.toPath())) {

			if (in == null) return;

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeCustomPlaySoundGuideKo_KR() {
		File file = new File("config/zombiesaddon/CustomPlaySoundGuide(ko_KR).txt");

		//noinspection ResultOfMethodCallIgnored
		file.getParentFile().mkdirs();

		try (InputStream in = getClass().getClassLoader().getResourceAsStream("assets/" + MODID + "/data/text/CustomPlaySoundGuide(ko_KR).txt");
			 OutputStream out = Files.newOutputStream(file.toPath())) {

			if (in == null) return;

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeCustomPlaySoundJson() {
		File jsonFile = new File(directory, "CustomPlaySound.json");

		if (jsonFile.exists()) return;

		CustomPlaySound[] customPlaySounds = new CustomPlaySound[] {
				new CustomPlaySound("note.pling", 2.0F, 0, (byte) 1),
				new CustomPlaySound("note.pling", 1.5F, -60, (byte) 2),
				new CustomPlaySound("note.pling", 1.5F, -40, (byte) 2),
				new CustomPlaySound("note.pling", 1.5F, -20, (byte) 2),
				new CustomPlaySound("random.orb", 0.5F, 0, (byte) 2),
		};

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonContent = gson.toJson(customPlaySounds);

		try (FileWriter writer = new FileWriter(jsonFile)) {
			writer.write(jsonContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}