package kr.hahaha98757.zombiesaddon.config;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.LinkedHashMap;

public class ZombiesAddonConfig {
	public static Configuration config;

	public static LinkedHashMap<String, IConfigElement> zombiesAddon = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> waveDelays = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> sla = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> others = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> patchers = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> hidden = new LinkedHashMap<>();

	public static boolean enableMod = true;
	public static boolean showMods = true;
	public static boolean[] modDefaultValue = { false, false, false };
	public static boolean checkPreRelease = false;

	public static boolean toggleWaveDelays = true;
	public static boolean bossWaveAlarm = true;
	public static String textStyle = "Zombies Addon";
	public static String highlightStyle = "Zombies Addon";
	public static int rlModeOffset = -28;
	public static boolean playSound = true;
	public static boolean playCount = true;
	public static String soundName = "note.pling";
	public static double soundPitch = 2.0;
	public static String lastSoundName = "random.orb";
	public static double lastSoundPitch = 0.5;
	public static String countSoundName = "note.pling";
	public static double countSoundPitch = 1.5;

	public static boolean autoSLA = true;
	public static boolean showInactiveWindows = false;

	public static boolean toggleNotLast = true;
	public static boolean toggleAutoSplits = true;
	public static boolean togglePowerupPatterns = true;
	public static boolean toggleLastWeapons = true;
	public static double corneringRange = 2.5;
	public static boolean hideAutoRejoin = true;
	public static boolean overlayKorean = false;
	public static String textMacro = "T";

	public static boolean koreanPatch = true;
	public static boolean sstPatch = true;

	public static boolean detectUnlegitMods = true;

	public static void loadConfig() {
		config.load();

		//Zombies Addon
		Property propertyEnableMod = config.get(Configuration.CATEGORY_GENERAL, "Enable Mod", true, "Uses Zombie Addon. If set to false, Zombie Addon is disabled.");
		enableMod = propertyEnableMod.getBoolean();
		zombiesAddon.put(propertyEnableMod.getName(), new ConfigElement(propertyEnableMod));

		Property propertyShowMods = config.get(Configuration.CATEGORY_GENERAL, "Show Mods", true, "Display text of the mods on the in-game screen.");
		showMods = propertyShowMods.getBoolean();
		zombiesAddon.put(propertyShowMods.getName(), new ConfigElement(propertyShowMods));

		Property propertyModDefaultValue = config.get(Configuration.CATEGORY_GENERAL, "Mod Default Value", new boolean[] { false, false, false }, "Sets the default value of Cornering, Block Alarm, and Auto Rejoin.");
		modDefaultValue = propertyModDefaultValue.getBooleanList();
		zombiesAddon.put(propertyModDefaultValue.getName(), new ConfigElement(propertyModDefaultValue));

		Property propertyCheckPreRelease = config.get(Configuration.CATEGORY_GENERAL, "Check Pre-Release", false, "It checks the latest pre-releases.");
		checkPreRelease = propertyCheckPreRelease.getBoolean();
		zombiesAddon.put(propertyCheckPreRelease.getName(), new ConfigElement(propertyCheckPreRelease));

		//Wave Delays
		Property propertyToggleWaveDelays = config.get(Configuration.CATEGORY_GENERAL, "Toggle Wave Delays", true, "Show wave delays(Same as Spawn Time).");
		toggleWaveDelays = propertyToggleWaveDelays.getBoolean();
		waveDelays.put(propertyToggleWaveDelays.getName(), new ConfigElement(propertyToggleWaveDelays));

		Property propertyBossWaveAlarm = config.get(Configuration.CATEGORY_GENERAL, "Boss Wave Alarm", true, "You can see the wave which the boss will spawn.");
		bossWaveAlarm = propertyBossWaveAlarm.getBoolean();
		waveDelays.put(propertyBossWaveAlarm.getName(), new ConfigElement(propertyBossWaveAlarm));

		Property propertyTextStyle = config.get(Configuration.CATEGORY_GENERAL, "Text Style", "Zombies Addon", "Sets the style of the text.", new String[] { "Zombies Addon", "SST" });
		textStyle = propertyTextStyle.getString();
		waveDelays.put(propertyTextStyle.getName(), new ConfigElement(propertyTextStyle));

		Property propertyHighlightStyle = config.get(Configuration.CATEGORY_GENERAL, "Highlight Style", "Zombies Addon", "Sets the style of the highlight.", new String[] { "Zombies Addon", "SST" });
		highlightStyle = propertyHighlightStyle.getString();
		waveDelays.put(propertyHighlightStyle.getName(), new ConfigElement(propertyHighlightStyle));

		Property propertyRLmodeOffset = config.get(Configuration.CATEGORY_GENERAL, "RL Mode Offset", -28, "Ticks to be added to the wave delays time.", -2000, 2000);
		rlModeOffset = propertyRLmodeOffset.getInt();
		waveDelays.put(propertyRLmodeOffset.getName(), new ConfigElement(propertyRLmodeOffset));

		Property propertyPlaySound = config.get(Configuration.CATEGORY_GENERAL, "Play Sound", true, "Play sound when wave start.");
		playSound = propertyPlaySound.getBoolean();
		waveDelays.put(propertyPlaySound.getName(), new ConfigElement(propertyPlaySound));

		Property propertyPlayCount = config.get(Configuration.CATEGORY_GENERAL, "Play Count", true, "Play countdown before last wave start.");
		playCount = propertyPlayCount.getBoolean();
		waveDelays.put(propertyPlayCount.getName(), new ConfigElement(propertyPlayCount));

		Property propertySoundName = config.get(Configuration.CATEGORY_GENERAL, "Sound Name", "note.pling", "Sets the sound name of the non-last wave.");
		soundName = propertySoundName.getString();
		waveDelays.put(propertySoundName.getName(), new ConfigElement(propertySoundName));

		Property propertySoundPitch = config.get(Configuration.CATEGORY_GENERAL, "Sound Pitch", 2.0, "Sets the sound pitch of the non-last wave.", 0.0, 2.0);
		soundPitch = propertySoundPitch.getDouble();
		waveDelays.put(propertySoundPitch.getName(), new ConfigElement(propertySoundPitch));

		Property propertyLastSoundName = config.get(Configuration.CATEGORY_GENERAL, "Last Sound Name", "random.orb", "Sets the sound name of the last wave.");
		lastSoundName = propertyLastSoundName.getString();
		waveDelays.put(propertyLastSoundName.getName(), new ConfigElement(propertyLastSoundName));

		Property propertyLastSoundPitch = config.get(Configuration.CATEGORY_GENERAL, "Last Sound Pitch", 0.5, "Sets the sound pitch of the last wave.", 0.0, 2.0);
		lastSoundPitch = propertyLastSoundPitch.getDouble();
		waveDelays.put(propertyLastSoundPitch.getName(), new ConfigElement(propertyLastSoundPitch));

		Property propertyCountSoundName = config.get(Configuration.CATEGORY_GENERAL, "Count Sound Name", "note.pling", "Sets the countdown name.");
		countSoundName = propertyCountSoundName.getString();
		waveDelays.put(propertyCountSoundName.getName(), new ConfigElement(propertyCountSoundName));

		Property propertyCountSoundPitch = config.get(Configuration.CATEGORY_GENERAL, "Count Sound Pitch", 1.5, "Sets the countdown pitch.", 0.0, 2.0);
		countSoundPitch = propertyCountSoundPitch.getDouble();
		waveDelays.put(propertyCountSoundPitch.getName(), new ConfigElement(propertyCountSoundPitch));

		//SLA
		Property propertyAutoSLA = config.get(Configuration.CATEGORY_GENERAL, "Auto SLA", true, "Automatically turn on SLA.");
		autoSLA = propertyAutoSLA.getBoolean();
		sla.put(propertyAutoSLA.getName(), new ConfigElement(propertyAutoSLA));

		Property propertyShowInactiveWindows = config.get(Configuration.CATEGORY_GENERAL, "Show Inactive Windows", false, "Show inactive windows.");
		showInactiveWindows = propertyShowInactiveWindows.getBoolean();
		sla.put(propertyShowInactiveWindows.getName(), new ConfigElement(propertyShowInactiveWindows));

		//Others
		Property propertyToggleNotLast = config.get(Configuration.CATEGORY_GENERAL, "Not Last: Toggle Not Last", true, "Shows the player who killed the last a zombie");
		toggleNotLast = propertyToggleNotLast.getBoolean();
		others.put(propertyToggleNotLast.getName(), new ConfigElement(propertyToggleNotLast));

		Property propertyToggleAutoSplits = config.get(Configuration.CATEGORY_GENERAL, "Auto Splits: Toggle Auto Splits", true, "Run timer at start of round.");
		toggleAutoSplits = propertyToggleAutoSplits.getBoolean();
		others.put(propertyToggleAutoSplits.getName(), new ConfigElement(propertyToggleAutoSplits));

		Property propertyTogglePowerupAlarm = config.get(Configuration.CATEGORY_GENERAL, "Powerup Patterns: Toggle Powerup Patterns", true, "Show powerup patterns.");
		togglePowerupPatterns = propertyTogglePowerupAlarm.getBoolean();
		others.put(propertyTogglePowerupAlarm.getName(), new ConfigElement(propertyTogglePowerupAlarm));

		Property propertyLastWeapons = config.get(Configuration.CATEGORY_GENERAL, "Last Weapons: Toggle Last Weapons", true, "Show your weapons at Game Over.");
		toggleLastWeapons = propertyLastWeapons.getBoolean();
		others.put(propertyLastWeapons.getName(), new ConfigElement(propertyLastWeapons));

		Property propertyCorneringRange = config.get(Configuration.CATEGORY_GENERAL, "Cornering: Range", 2.5, "Set range of Cornering.", 0.0, 100.0);
		corneringRange = propertyCorneringRange.getDouble();
		others.put(propertyCorneringRange.getName(), new ConfigElement(propertyCorneringRange));

		Property propertyHideAutoRejoin = config.get(Configuration.CATEGORY_GENERAL, "Auto Rejoin: Hide Auto Rejoin", true, "Hide the text of Auto Rejoin on the in-game screen.");
		hideAutoRejoin = propertyHideAutoRejoin.getBoolean();
		others.put(propertyHideAutoRejoin.getName(), new ConfigElement(propertyHideAutoRejoin));

		Property propertyOverlayKorean = config.get(Configuration.CATEGORY_GENERAL, "Use Zombeis Overlay in Korean", false, "You can use Zombies Overlay in Korean.");
		overlayKorean = propertyOverlayKorean.getBoolean();
		others.put(propertyOverlayKorean.getName(), new ConfigElement(propertyOverlayKorean));

		Property propertyTextMacro = config.get(Configuration.CATEGORY_GENERAL, "Text Macro", "T", "Sets the text that text macro will send on chat.");
		textMacro = propertyTextMacro.getString();
		others.put(propertyTextMacro.getName(), new ConfigElement(propertyTextMacro));

		//Patchers
		Property propertyKoreanPatch = config.get(Configuration.CATEGORY_GENERAL, "Korean Patch", true, "Better Korean translation.");
		koreanPatch = propertyKoreanPatch.getBoolean();
		patchers.put(propertyKoreanPatch.getName(), new ConfigElement(propertyKoreanPatch));

		Property propertySSTPatch = config.get(Configuration.CATEGORY_GENERAL, "SST Patch", true, "Translate the text in SST to Korean.");
		sstPatch = propertySSTPatch.getBoolean();
		patchers.put(propertySSTPatch.getName(), new ConfigElement(propertySSTPatch));

		//Hidden
		Property propertyDetectUnlegitMods = config.get(Configuration.CATEGORY_GENERAL, "Detect Unlegit Mods", true, "If unlegit mod is detected, the game will end. Unlegit mods: ZombiesSatellite, Zombies Explorer, TeammatesOutline");
		detectUnlegitMods = propertyDetectUnlegitMods.getBoolean();
		hidden.put(propertyDetectUnlegitMods.getName(), new ConfigElement(propertyDetectUnlegitMods));
	}

	public static void reloadConfig() {
		config.save();
		loadConfig();
	}
}