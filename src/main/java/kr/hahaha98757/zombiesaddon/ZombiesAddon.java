package kr.hahaha98757.zombiesaddon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seosean.showspawntime.ShowSpawnTime;
import kr.hahaha98757.zombiesaddon.commands.*;
import kr.hahaha98757.zombiesaddon.config.Hotkeys;
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

import java.io.File;
import java.io.FileWriter;
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
	public static final String VERSION = "4.2.1";
	private static File directory;

	public static boolean hasOldSST;
	public static boolean haveUnlegitMods;
	public static boolean hasSST;

	private void writeCustomPlaySoundGuide() {
		File file = new File("config/zombiesaddon/CustomPlaySoundGuide.txt");

        if (file.exists()) //noinspection ResultOfMethodCallIgnored
            file.delete();

		try {
			Path filepath = Paths.get("config/zombiesaddon/CustomPlaySoundGuide.txt");

			List<String> content = getPlayCustomSoundGuideContent();

			Files.write(filepath, content, StandardCharsets.UTF_8);
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

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		directory = new File(event.getModConfigurationDirectory(), MODID);

		if (!directory.exists()) //noinspection ResultOfMethodCallIgnored
            directory.mkdir();

		writeCustomPlaySoundGuide();
		writeCustomPlaySoundJson();

		ZombiesAddonConfig.config = new Configuration(new File(directory, MODID + ".cfg"));
		ZombiesAddonConfig.loadConfig();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new UpdateChecker());
		Hotkeys.registerAll();
		Commands.registerAll();
		Features.registerAll();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("ShowSpawnTime")) hasOldSST = true;
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
	}

	private static List<String> getPlayCustomSoundGuideContent() {
		return Arrays.asList(
				"How to use Custom Play Sound.",
				"",
				"Find and Open \".minecraft\\config\\zombiesaddon\\CustomPlaySound.json\" file.",
				"",
				"Write it:", "[{sound1}, {sound2}, {sound3} ... ]",
				"",
				"The contents of each bracket are as follows:",
				"{\"name\": sound_name, \"pitch\": sound_pitch, \"timing\": sound_timing, \"playWave\": sound_playWave}",
				"",
				"sound_name: A name of the sound. It must be wrapped in \"\". (e.g. \"note.pling\")",
				"sound_pitch: A pitch of the sound. The range is 0.0 to 2.0.",
				"",
				"sound_timing: It is the timing when the sound will be played. (e.g. If sound_timing is -20, the sound will play 1 second before the wave starts.)",
				"",
				"sound_playWave: Sets which wave the sound will be played on. (0: Any waves. 1: Any waves without last wave. 2: Only last wave.)",
				"",
				"",
				"e.g. Playing the sound of SST mod:",
				"[",
				"  {",
				"    \"name\": \"note.pling\",",
				"    \"pitch\": 2.0,",
				"    \"timing\": 0,",
				"    \"playWave\": 1",
				"  },",
				"  {",
				"    \"name\": \"note.pling\",",
				"    \"pitch\": 1.5,",
				"    \"timing\": -60,",
				"    \"playWave\": 2",
				"  },",
				"  {",
				"    \"name\": \"note.pling\",",
				"    \"pitch\": 1.5,",
				"    \"timing\": -40,",
				"    \"playWave\": 2",
				"  },",
				"  {",
				"    \"name\": \"note.pling\",",
				"    \"pitch\": 1.5,",
				"    \"timing\": -20,",
				"    \"playWave\": 2",
				"  },",
				"  {",
				"    \"name\": \"random.orb\",",
				"    \"pitch\": 0.5,",
				"    \"timing\": 0,",
				"    \"playWave\": 2",
				"  },",
				"]"
		);
	}
}