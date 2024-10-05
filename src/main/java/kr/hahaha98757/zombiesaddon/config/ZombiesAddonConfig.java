//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.Arrays;
import java.util.List;

public class ZombiesAddonConfig {
	public static Configuration config;

	private static final String CATEGORY_ZOMBIES_ADDON = "Zombies Addon";
	private static final String CATEGORY_WAVE_DELAYS = "Wave Delays";
	private static final String CATEGORY_SLA = "SLA";
	private static final String CATEGORY_PV = "PV";
	private static final String CATEGORY_LAST_WEAPONS = "Last Weapons";
	private static final String CATEGORY_KOREAN_PATCHERS = "Korean Patchers";
	private static final String CATEGORY_OTHERS = "Others";
	private static final String CATEGORY_OTHER_MODS = "Other Mods";
	private static final String CATEGORY_HIDDEN = "Hidden";
	private static final String CATEGORY_HUD = "HUD";

	private static Property enableMod;
	private static Property showMods;
	private static Property modDefaultValue;
	private static Property checkPreRelease;

	private static Property toggleWaveDelays;
	private static Property bossWaveColor;
	private static Property wavePrefix;
	private static Property textStyle;
	private static Property highlightStyle;
	private static Property rlModeOffset;
	private static Property hidePassedWave;
	private static Property playSounds;
	private static Property customPlaySound;

	private static Property autoSLA;
	private static Property showInactiveWindows;

	private static Property pvRange;
	private static Property playerTranslucent;

	private static Property toggleLastWeapons;
	private static Property displayArmors;
	private static Property displayWeaponsLevel;

	private static Property zombiesOverlayPatcher;
	private static Property koreanPatcher;
	private static Property sstPatcher;

	private static Property toggleNotLast;
	private static Property toggleAutoSplits;
	private static Property togglePowerupPatterns;
	private static Property hideAutoRejoin;
	private static Property textMacro;

	private static Property sstSetting;
	private static Property zombiesUtilsSetting;

	private static Property detectUnlegitMods;
	private static Property blockUnlegitSST;

	public static void loadConfig() {
		config.load();

		//Zombies Addon
		enableMod = config.get(CATEGORY_ZOMBIES_ADDON, "Enable Mod", true, "Enable Zombie Addon. If set to false, Zombie Addon is disabled.\nHowever, some features will still work.(Fixes a command bug, Block SST Unlegit, etc.)");

		showMods = config.get(CATEGORY_ZOMBIES_ADDON, "Show Mods", true, "Display text of the mods on the in-game screen.");

		modDefaultValue = config.get(CATEGORY_ZOMBIES_ADDON, "Mod Default Value", new boolean[] { false, false, false }, "Sets the default value of Cornering, Block Alarm, and Auto Rejoin.");

		checkPreRelease = config.get(CATEGORY_ZOMBIES_ADDON, "Check Pre-Release", false, "It checks the latest pre-releases.");

		//Wave Delays
		toggleWaveDelays = config.get(CATEGORY_WAVE_DELAYS, "Toggle Wave Delays", true, "Show wave delays(Same as Spawn Time).");

		bossWaveColor = config.get(CATEGORY_WAVE_DELAYS, "Boss Wave Color", true, "Colors the waves spawned by the boss.");

		wavePrefix = config.get(CATEGORY_WAVE_DELAYS, "Wave Prefix", true, "Adds prefixes to waves spawned by specific mobs.");

		textStyle = config.get(CATEGORY_WAVE_DELAYS, "Text Style", "W1: 0:10.0", "Sets the style of the text.", new String[] { "W1: 0:10.0", "W1 0:10.0", "W1: 00:10", "W1 00:10" });

		highlightStyle = config.get(CATEGORY_WAVE_DELAYS, "Highlight Style", "Zombies Addon", "Sets the style of the highlight.", new String[] { "Zombies Addon", "Zombies Utils" });

		rlModeOffset = config.get(CATEGORY_WAVE_DELAYS, "RL Mode Offset", -28, "Ticks to be added to the wave delays time.", -2000, 2000);

		hidePassedWave = config.get(CATEGORY_WAVE_DELAYS, "Hide Passed Wave", false, "Hides passed waves.");

		playSounds = config.get(CATEGORY_WAVE_DELAYS, "Play Sounds", new int[] {-60, -40, -20}, "Plays sounds.", -200, 200, false, 5);

		customPlaySound = config.get(CATEGORY_WAVE_DELAYS, "Custom Play Sound", false, "Play sounds with more detailed settings.");

		//SLA
		autoSLA = config.get(CATEGORY_SLA, "Auto SLA", true, "Automatically turn on SLA.");

		showInactiveWindows = config.get(CATEGORY_SLA, "Show Inactive Windows", false, "Show inactive windows.");

		//PV
		pvRange = config.get(CATEGORY_OTHERS, "Range", 2.5, "Set range of Cornering.", 0.0, 100.0);

		playerTranslucent = config.get(CATEGORY_PV, "Player Translucent", true, "Makes out-of-range players semi-transparent.");

		//Last Weapons
		toggleLastWeapons = config.get(CATEGORY_OTHERS, "Toggle Last Weapons", true, "Display your weapons at Game Over.");

		displayArmors = config.get(CATEGORY_LAST_WEAPONS, "Display Armors", true, "Display your armors at Game Over.");

		displayWeaponsLevel = config.get(CATEGORY_LAST_WEAPONS, "Display Weapons Level", true, "Display level of weapons and perks.");

		//Korean Patchers
		zombiesOverlayPatcher = config.get(CATEGORY_KOREAN_PATCHERS, "Zombies Overlay Patcher", false, "You can use Zombies Overlay in Korean.");

		koreanPatcher = config.get(CATEGORY_KOREAN_PATCHERS, "Korean Patcher", true, "Better Korean translation.");

		sstPatcher = config.get(CATEGORY_KOREAN_PATCHERS, "SST Patcher", true, "Translate the text in SST to Korean.");

		//Others
		toggleNotLast = config.get(CATEGORY_OTHERS, "Not Last: Toggle Not Last", true, "Shows the player who killed the last a zombie");

		toggleAutoSplits = config.get(CATEGORY_OTHERS, "Auto Splits: Toggle Auto Splits", true, "Run timer at start of round.");

		togglePowerupPatterns = config.get(CATEGORY_OTHERS, "Powerup Patterns: Toggle Powerup Patterns", true, "Show powerup patterns.");

		hideAutoRejoin = config.get(CATEGORY_OTHERS, "Auto Rejoin: Hide Auto Rejoin", true, "Hide the text of Auto Rejoin on the in-game screen.");

		textMacro = config.get(CATEGORY_OTHERS, "Text Macro", "T", "Sets the text that text macro will send on chat.");

		//Other Mods
		sstSetting = config.get(CATEGORY_OTHER_MODS, "Turn off spawn time of SST", true, "Turn off spawn time of SST.");

		zombiesUtilsSetting = config.get(CATEGORY_OTHER_MODS, "Turn off timer of Zombies Utils", true, "Turn off timer of Zombies Utils.");

		//Hidden
		detectUnlegitMods = config.get(CATEGORY_HIDDEN, "Detect Unlegit Mods", true, "If unlegit mod is detected, the game will end. Unlegit mods: ZombiesSatellite, Zombies Explorer, TeammatesOutline, and ZombiesHelper");
		blockUnlegitSST = config.get(CATEGORY_HIDDEN, "Block Unlegit SST", true, "Blocks the unlegit features of SST.");

		//HUD
		HUDUtils.autoSplitsX = config.get(CATEGORY_HUD, "autoSplitsX", -1, "X").getDouble();
		HUDUtils.autoSplitsY = config.get(CATEGORY_HUD, "autoSplitsY", -1, "Y").getDouble();
		HUDUtils.waveDelaysX = config.get(CATEGORY_HUD, "waveDelaysX", -1, "X").getDouble();
		HUDUtils.waveDelaysY = config.get(CATEGORY_HUD, "waveDelaysY", -1, "Y").getDouble();
		HUDUtils.powerupPatternsX = config.get(CATEGORY_HUD, "powerupPatternsX", -1, "X").getDouble();
		HUDUtils.powerupPatternsY = config.get(CATEGORY_HUD, "powerupPatternsY", -1, "Y").getDouble();
	}

	public static void reloadConfig() {
		config.save();
		loadConfig();
	}

	private static List<IConfigElement> getZombiesAddonElements() {
		return Arrays.asList(
				new CustomConfigElement("Enable Mod", enableMod),
				new CustomConfigElement("Show Mods", showMods),
				new CustomConfigElement("Mod Default Value", modDefaultValue),
				new CustomConfigElement("Check Pre-Release", checkPreRelease)
		);
	}

	private static List<IConfigElement> getWaveDelaysElements() {
		return Arrays.asList(
				new CustomConfigElement("Toggle Wave Delays", toggleWaveDelays),
				new CustomConfigElement("Boss Wave Color", bossWaveColor),
				new CustomConfigElement("Wave Prefix", wavePrefix),
				new CustomConfigElement("Text Style", textStyle),
				new CustomConfigElement("Highlight Style", highlightStyle),
				new CustomConfigElement("RL Mode Offset", rlModeOffset),
				new CustomConfigElement("Hide Passed Wave", hidePassedWave),
				new CustomConfigElement("Play Sounds", playSounds),
				new CustomConfigElement("Custom Play Sound", customPlaySound)
		);
	}

	private static List<IConfigElement> getSLAElements() {
		return Arrays.asList(
				new CustomConfigElement("Auto SLA", autoSLA),
				new CustomConfigElement("Show Inactive Windows", showInactiveWindows)
		);
	}

	private static List<IConfigElement> getPVElements() {
		return Arrays.asList(
				new CustomConfigElement("Range", pvRange),
				new CustomConfigElement("Player Translucent", playerTranslucent)
		);
	}

	private static List<IConfigElement> getLastWeaponsElements() {
		return Arrays.asList(
				new CustomConfigElement("Toggle Last Weapons", toggleLastWeapons),
				new CustomConfigElement("Display Armors", displayArmors),
				new CustomConfigElement("Display Weapons Level", displayWeaponsLevel)
		);
	}

	private static List<IConfigElement> getKoreanPatchersElements() {
		return Arrays.asList(
				new CustomConfigElement("Zombies Overlay Patcher", zombiesOverlayPatcher),
				new CustomConfigElement("Korean Patcher", koreanPatcher),
				new CustomConfigElement("SST Patcher", sstPatcher)
		);
	}

	private static List<IConfigElement> getOthersElements() {
		return Arrays.asList(
				new CustomConfigElement("Not Last: Toggle Not Last", toggleNotLast),
				new CustomConfigElement("Auto Splits: Toggle Auto Splits", toggleAutoSplits),
				new CustomConfigElement("Powerup Patterns: Toggle Powerup Patterns", togglePowerupPatterns),
				new CustomConfigElement("Last Weapons: Toggle Last Weapons", toggleLastWeapons),
				new CustomConfigElement("Auto Rejoin: Hide Auto Rejoin", hideAutoRejoin),
				new CustomConfigElement("Text Macro", textMacro)
		);
	}

	private static List<IConfigElement> getOtherModsElements() {
		return Arrays.asList(
				new CustomConfigElement("Turn off spawn time of SST", sstSetting),
				new CustomConfigElement("Turn off timer of Zombies Utils", zombiesUtilsSetting)
		);
	}

	public static List<IConfigElement> getRootElements() {
		return Arrays.asList(
				new DummyConfigElement.DummyCategoryElement(CATEGORY_ZOMBIES_ADDON, "", getZombiesAddonElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_WAVE_DELAYS, "", getWaveDelaysElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_SLA, "", getSLAElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_PV, "", getPVElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_LAST_WEAPONS, "", getLastWeaponsElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_KOREAN_PATCHERS, "", getKoreanPatchersElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_OTHERS, "", getOthersElements()),
				new DummyConfigElement.DummyCategoryElement(CATEGORY_OTHER_MODS, "", getOtherModsElements())
		);
	}

	public static boolean isEnableMod() {
		return enableMod.getBoolean();
	}

	public static boolean isShowMods() {
		return showMods.getBoolean();
	}

	public static boolean[] getModDefaultValues() {
		return modDefaultValue.getBooleanList();
	}

	public static boolean isCheckPreRelease() {
		return checkPreRelease.getBoolean();
	}

	public static boolean isNotToggleWaveDelays() {
		return !toggleWaveDelays.getBoolean();
	}

	public static boolean isBossWaveColor() {
		return bossWaveColor.getBoolean();
	}

	public static boolean isNotWavePrefix() {
		return !wavePrefix.getBoolean();
	}

	public static String getTextStyle() {
		String str = textStyle.getString();
		if (!str.equals("W1 0:10.0") && !str.equals("W1: 00:10") && !str.equals("W1 00:10")) return "W1: 0:10.0";
		return textStyle.getString();
	}

	public static String getHighlightStyle() {
		String str = highlightStyle.getString();
		if (!str.equals("Zombies Utils")) return "Zombies Addon";
		return highlightStyle.getString();
	}

	public static int getRLModeOffset() {
		return rlModeOffset.getInt();
	}

	public static boolean isHidePassedWave() {
		return hidePassedWave.getBoolean();
	}

	public static int[] getPlaySounds() {
		return playSounds.getIntList();
	}

	public static boolean isCustomPlaySound() {
		return customPlaySound.getBoolean();
	}

	public static boolean isAutoSLA() {
		return autoSLA.getBoolean();
	}

	public static boolean isShowInactiveWindows() {
		return showInactiveWindows.getBoolean();
	}

	public static double getPVRange() {
		return pvRange.getDouble();
	}

	public static boolean isPlayerTranslucent() {
		return playerTranslucent.getBoolean();
	}

	public static boolean isToggleLastWeapons() {
		return toggleLastWeapons.getBoolean();
	}

	public static boolean isDisplayArmors() {
		return displayArmors.getBoolean();
	}

	public static boolean isDisplayWeaponsLevel() {
		return displayWeaponsLevel.getBoolean();
	}

	public static boolean isZombiesOverlayPatcher() {
		return zombiesOverlayPatcher.getBoolean();
	}

	public static boolean isKoreanPatcher() {
		return koreanPatcher.getBoolean();
	}

	public static boolean isSSTPatch() {
		return sstPatcher.getBoolean();
	}

	public static boolean isToggleNotLast() {
		return toggleNotLast.getBoolean();
	}

	public static boolean isToggleAutoSplits() {
		return toggleAutoSplits.getBoolean();
	}

	public static boolean isNotTogglePowerupPatterns() {
		return !togglePowerupPatterns.getBoolean();
	}

	public static boolean isHideAutoRejoin() {
		return hideAutoRejoin.getBoolean();
	}

	public static String getTextMacro() {
		return textMacro.getString();
	}
	
	public static boolean isSSTSetting() {
		return sstSetting.getBoolean();
	}

	public static boolean isZombiesUtilsSetting() {
		return zombiesUtilsSetting.getBoolean();
	}

	public static boolean isDetectUnlegitMods() {
		return detectUnlegitMods.getBoolean();
	}

	public static boolean isBlockUnlegitSST() {
		if (blockUnlegitSST == null) return false;
		return blockUnlegitSST.getBoolean();
	}
}