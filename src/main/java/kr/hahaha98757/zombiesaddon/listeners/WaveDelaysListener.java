package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.RenderTimerHandler;
import kr.hahaha98757.zombiesaddon.packet.AutoSplitPacketInterceptor;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class WaveDelaysListener {
	private static String[] waveColors = { "8", "8", "8", "8", "8", "8", "8", "8" };
	private static final String WAVE = "\u00A75\u27A4 ";
	private static boolean[] waves = { false, false, false, false, false, false, false, false };
	private static boolean[] playSounds = { false, false, false, false, false, false, false, false };
	private static boolean[] playCounts = { false, false, false };

	public static boolean hard;
	public static boolean rip;

	private static final String[] bossWaves = { "", "", "", "", "", "", "", "" };

	private static final int DESPAWN_TIME = 300;

	private static final int HIGHLIGHT_DELAY_ZOMBIESADDON = 3;

	private static final int HIGHLIGHT_DELAY_SST = 2;

	public static int rlmode = -28;

	private static final String pattern1IntDot1Int = "\\d{1}\\.\\d";

	public static boolean stop = false;
	public static int round = 0;
	public static boolean escape = false;

	public String[][] getWaveDelay(boolean RIP) {
		String[][] DEDelays = new String[29][5];
		DEDelays = new String[][] { { "10.0", "20.0" }, { "10.0", "20.0" }, { "10.0", "20.0", "35.0" },
				{ "10.0", "20.0", "35.0" }, { "10.0", "22.0", "37.0" }, { "10.0", "22.0", "44.0" },
				{ "10.0", "25.0", "47.0" }, { "10.0", "25.0", "50.0" }, { "10.0", "22.0", "38.0" },
				{ "10.0", "24.0", "45.0" }, { "10.0", "25.0", "48.0" }, { "10.0", "25.0", "50.0" },
				{ "10.0", "25.0", "50.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "25.0", "46.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "49.0" }, { "10.0", "23.0", "44.0" },
				{ "10.0", "23.0", "45.0" }, { "10.0", "23.0", "42.0" }, { "10.0", "23.0", "43.0" },
				{ "10.0", "23.0", "43.0" }, { "10.0", "23.0", "36.0" }, { "10.0", "24.0", "44.0" },
				{ "10.0", "24.0", "42.0" }, { "10.0", "24.0", "42.0" }, { "10.0", "24.0", "45.0" } };
		String[][] RIPDelays = new String[29][5];
		RIPDelays = new String[][] { { "10.0", "20.0" }, { "10.0", "20.0" }, { "10.0", "20.0", "35.0" },
				{ "10.0", "20.0", "35.0" }, { "10.0", "22.0", "37.0" }, { "10.0", "22.0", "44.0" },
				{ "10.0", "25.0", "47.0" }, { "10.0", "25.0", "50.0" }, { "10.0", "22.0", "38.0" },
				{ "10.0", "24.0", "45.0", "48.0" }, { "10.0", "25.0", "48.0" }, { "10.0", "25.0", "50.0" },
				{ "10.0", "25.0", "50.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "25.0", "46.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "49.0", "52.0" }, { "10.0", "23.0", "44.0" },
				{ "10.0", "23.0", "45.0" }, { "10.0", "23.0", "42.0" }, { "10.0", "23.0", "43.0" },
				{ "10.0", "23.0", "43.0" }, { "10.0", "23.0", "36.0" }, { "10.0", "24.0", "44.0" },
				{ "10.0", "24.0", "42.0" }, { "10.0", "24.0", "42.0" }, { "10.0", "24.0", "45.0" } };
		String[][] BBDelays = new String[29][5];
		BBDelays = new String[][] { { "10.0", "22.0" }, { "10.0", "22.0" }, { "10.0", "22.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "24.0", "38.0" }, { "10.0", "24.0", "38.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "24.0", "38.0" }, { "10.0", "22.0", "34.0" } };
		String[][] AADelays = new String[104][5];
		AADelays = new String[][] { { "10.0", "13.0", "16.0", "19.0" }, { "10.0", "14.0", "18.0", "22.0" },
				{ "10.0", "13.0", "16.0", "19.0" }, { "10.0", "14.0", "17.0", "21.0", "25.0", "28.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" },
				{ "10.0", "15.0", "19.0", "23.0", "27.0", "31.0" }, { "10.0", "15.0", "20.0", "25.0", "30.0", "35.0" },
				{ "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" }, { "10.0", "16.0", "22.0", "27.0", "33.0", "38.0" },
				{ "10.0", "16.0", "21.0", "27.0", "32.0", "38.0" }, { "10.0", "16.0", "22.0", "28.0", "34.0", "40.0" },
				{ "10.0", "16.0", "22.0", "28.0", "34.0", "40.0" }, { "10.0", "16.0", "21.0", "26.0", "31.0", "36.0" },
				{ "10.0", "17.0", "24.0", "31.0", "38.0", "46.0" }, { "10.0", "16.0", "22.0", "27.0", "33.0", "38.0" },
				{ "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "15.0", "21.0", "26.0", "31.0", "36.0" },
				{ "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "34.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" },
				{ "10.0" }, { "10.0", "23.0", "36.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "20.0", "30.0" },
				{ "10.0", "24.0", "38.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "21.0", "32.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "20.0", "31.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0", "37.0", "45.0" }, { "10.0", "21.0", "32.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "13.0", "22.0", "25.0", "34.0", "37.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0", "35.0" }, { "10.0", "21.0", "32.0", "35.0" },
				{ "10.0", "20.0", "30.0" }, { "10.0", "20.0", "30.0", "33.0" }, { "10.0", "21.0", "32.0" },
				{ "10.0", "22.0", "34.0", "37.0" }, { "10.0", "20.0", "30.0", "33.0" },
				{ "10.0", "22.0", "34.0", "37.0" }, { "10.0", "22.0", "34.0", "37.0" },
				{ "10.0", "20.0", "32.0", "35.0", "39.0" }, { "10.0", "16.0", "22.0", "28.0", "34.0", "40.0" },
				{ "10.0", "14.0", "18.0" }, { "10.0", "14.0", "18.0" }, { "10.0", "22.0", "34.0", "37.0", "38.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "20.0", "30.0", "33.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "5.0" }, { "5.0" }, { "5.0" }, { "5.0" }, { "5.0" } };
		String[][] PrisonDelays = new String[29][4];
		PrisonDelays = new String[][] { { "10.0", "20.0" }, { "10.0", "20.0", "30.0" },
				{ "10.0", "17.0", "24.0", "31.0" }, { "10.0", "17.0", "24.0", "31.0" }, { "10.0", "20.0", "30.0" },
				{ "10.0", "20.0", "30.0" }, { "10.0", "20.0", "30.0" }, { "10.0", "25.0", "40.0" },
				{ "10.0", "25.0", "35.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "25.0", "40.0" },
				{ "10.0", "25.0", "37.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "25.0", "37.0" },
				{ "10.0", "25.0", "40.0" }, { "10.0", "22.0", "37.0" }, { "10.0", "22.0", "42.0" },
				{ "10.0", "25.0", "45.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "25.0", "40.0" },
				{ "10.0", "20.0", "35.0", "55.0", "75.0" }, { "10.0", "25.0", "40.0" }, { "10.0", "30.0", "50.0" },
				{ "10.0", "30.0", "50.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "30.0", "50.0" },
				{ "10.0", "25.0", "45.0" }, { "10.0", "30.0", "50.0" }, { "10.0", "30.0", "55.0" }, { "10.0" },
				{ "0.0", "15.0", "30.0", "45.0", "60.0", "75.0", "90.0", "105.0" } };

		if (GameDetect.getMap() == 1 && !RIP) {
			return DEDelays;
		} else if (GameDetect.getMap() == 1 && RIP) {
			return RIPDelays;
		} else if (GameDetect.getMap() == 2) {
			return BBDelays;
		} else if (GameDetect.getMap() == 3) {
			return AADelays;
		} else if (GameDetect.getMap() == 4) {
			return PrisonDelays;
		}
		return null;
	}

	public int[][] getBossWave() {// Bombie or Warden: 1, Inferno, Angry Prisoner, or Corrupted Pigman: 2, The Broodmother, Wither, or Herobrine: 3, Lily and
									// Ellie: 4 , King Slime or Mega Blob: 5, Giant: 6, The Old One or Mega Magma:
									// 7, Giant and The Old One: 8,
									// World Ender: 9
		int[][] DEDelays = new int[29][5];
		DEDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 3 } };
		int[][] DEHardDelays = new int[29][5];
		DEHardDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 1, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 3, 3 } };
		int[][] DERIPDelays = new int[29][5];
		DERIPDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 1, 2 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2, 2 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 1, 1, 3 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 3, 3, 3 } };
		int[][] BBDelays = new int[29][5];
		BBDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 4 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 3, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 3, 0 } };
		int[][] BBRIPDelays = new int[29][5];
		BBRIPDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 4 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 5, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 3, 0, 3 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 3, 3, 0 } };
		int[][] AADelays = new int[104][5];
		AADelays = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 6, 0, 6, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 6, 0, 6 }, { 0, 0, 0, 0, 0, 0 }, { 0, 6, 0, 6, 0, 6 }, { 5 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 6, 6, 6 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 7 }, { 0, 6, 6 }, { 0, 6, 6 }, { 0, 6, 6 }, { 0, 6, 6 }, { 0, 6, 6, 0, 7 }, { 0, 6, 6 },
				{ 6, 6, 6 }, { 0, 6, 0, 6, 0, 6 }, { 6, 6, 6 }, { 0, 6, 7, 7 }, { 0, 0, 0, 7 }, { 0, 0, 6 },
				{ 0, 0, 0, 7 }, { 0, 0, 0 }, { 0, 6, 0, 6 }, { 0, 6, 0, 6 }, { 0, 6, 0, 6 }, { 0, 6, 0, 6 },
				{ 0, 8, 0, 6, 7 }, { 6, 6, 6, 6, 8, 7 }, { 5, 0, 0 }, { 7, 0, 0 }, { 0, 8, 0, 6, 7 },
				{ 7, 7, 7, 7, 7, 7 }, { 0, 0, 7, 7 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 6, 6, 6 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 7, 7, 8, 8, 8 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 7, 7, 7 }, { 0, 0, 0, 6, 6, 6 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 },
				{ 0, 7, 7, 8, 8, 8 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 7, 7, 7 }, { 0, 0, 0, 6, 6, 6 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 7, 7, 8, 8, 8 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 7, 7, 7 }, { 0, 0, 0, 6, 6, 6 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 },
				{ 0, 7, 7, 8, 8, 8 }, { 9 }, { 7 }, { 7 }, { 7 }, { 7 } };
		int[][] PRDelays = new int[29][5];
		PRDelays = new int[][] { { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 1 }, { 0, 0, 0, 0, 1, 0, 0, 0 } };

		if (GameDetect.getMap() == 1) {
			if (hard) {
				return DEHardDelays;
			} else if (rip) {
				return DERIPDelays;
			} else {
				return DEDelays;
			}
		} else if (GameDetect.getMap() == 2) {
			if (rip) {
				return BBRIPDelays;
			} else {
				return BBDelays;
			}
		} else if (GameDetect.getMap() == 3) {
			return AADelays;
		} else if (GameDetect.getMap() == 4) {
			return PRDelays;
		}
		return null;
	}

	private double[] getDoubleWaveDelays(String[] stringWaveDelays) {
		try {
			double[] doubleArray = Arrays.stream(stringWaveDelays).mapToDouble(Double::parseDouble).toArray();
			return doubleArray;
		} catch (Exception e) {
			return null;
		}
	}

	private String[][] getStringWaveDelaysWithRLmode(String[][] stringWaveDelays) {
		boolean plus;

		int rl = rlmode;

		try {
			if (rl >= 0) {
				plus = true;
			} else {
				plus = false;
				rl = Math.abs(rl);
			}

			int ds = rl / 2;

			String[][] newWaveDelays = stringWaveDelays;

			for (int i = 0; true; i++) {

				try {
					if (stringWaveDelays[i] == null) {
						break;
					}
				} catch (Exception e) {
					break;
				}

				for (int j = 0; true; j++) {

					try {
						if (stringWaveDelays[i][j] == null) {
							break;
						}
					} catch (Exception e) {
						break;
					}

					String str = String
							.valueOf(Integer.valueOf(stringWaveDelays[i][j].replaceAll("\\.", "")) + (plus ? ds : -ds));

					if (str.startsWith("-")) {
						str = "0";
					}

					if (str.length() > 1) {// �Ҽ��� �߰�
						str = str.substring(0, str.length() - 1) + "." + str.charAt(str.length() - 1);
					} else if (str.length() == 1) {
						str = "0." + str;
					} else {
						str = "0.0";
					}

					newWaveDelays[i][j] = str;

				}
			}
			return newWaveDelays;
		} catch (Exception e) {
			return stringWaveDelays;
		}
	}

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (event.type != ElementType.EXPERIENCE) {
			return;
		}

		if (!ZombiesAddonConfig.toggleWaveDelays) {
			return;
		}

		if (!GameDetect.isZombiesGame()) {
			stop = false;
			return;
		}

		if (!stop) {
			if (GameDetect.getMap() == 0 || GameDetect.getRound() == 0) {
				return;
			}

			if (GameDetect.getMap() == 4 && escape) {
				round = 30;
			} else {
				round = GameDetect.getRound() - 1;
			}
		}

        hard = (GameDetect.getMap() == 1 && (round == 14 || round == 24 || round == 29)
                && GameDetect.getDifficult(GameDetect.getMap(), round) == 2) || (hard);
        rip = (GameDetect.getMap() == 1
                && (round == 4 || round == 9 || round == 14 || round == 19 || round == 24 || round == 29)
                && GameDetect.getDifficult(GameDetect.getMap(), round) == 3) || rip;
        rip = (GameDetect.getMap() == 2 && (round == 14 || round == 19 || round == 24 || round == 29)
                && GameDetect.getDifficult(GameDetect.getMap(), round) == 3) || rip;
		if (!(round == 4 || round == 9 || round == 14 || round == 19 || round == 24 || round == 29)) {
			hard = false;
			rip = false;
		}

		String[][] strArray = getWaveDelay(rip);
		String[][] orgStrArray = getWaveDelay(rip);

		int[][] bossWave = getBossWave();

		if (!ZombiesAddonConfig.bossWaveAlarm) {
			bossWave = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };
		}

		if (strArray == null) {
			return;
		}

		try {
			if (EventListener.rlmode) {
				strArray = getStringWaveDelaysWithRLmode(strArray);
			}

			String min;
			String sec;
			String str;
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight - 10;

			for (int i = 0; i <= 7; i++) {
				try {
					if (strArray[round][i] == null) {
						break;
					}
				} catch (Exception e) {
					break;
				}

				switch (bossWave[round][i]) {
				case 0:
					bossWaves[i] = "";
					break;
				case 1:
					bossWaves[i] = "\u00A76";
					break;
				case 2:
					bossWaves[i] = "\u00A7c";
					break;
				case 3:
					bossWaves[i] = "\u00A75";
					break;
				case 4:
					bossWaves[i] = "\u00A7c";
					break;
				case 5:
					bossWaves[i] = "\u00A7a";
					break;
				case 6:
					bossWaves[i] = "\u00A73";
					break;
				case 7:
					bossWaves[i] = "\u00A74";
					break;
				case 8:
					bossWaves[i] = "\u00A73";
					break;
				case 9:
					bossWaves[i] = "\u00A70";
					break;
				}

				min = String.valueOf(Integer.valueOf(strArray[round][i].split("\\.")[0]) / 60);
				sec = Integer.valueOf(strArray[round][i].split("\\.")[0]) % 60 + "."
						+ strArray[round][i].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					fr.drawStringWithShadow((waves[i] ? WAVE : ""),
							((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
									- fr.getStringWidth(WAVE + "W1 0:10.0")),
							y - 8 * (strArray[round].length - 1) + 8 * i, 0);
					str = "\u00A7" + waveColors[i] + "W" + (i + 1) + " " + bossWaves[i] + min + ":"
							+ (bossWave[round][i] == 4 ? "\u00A7b" : (bossWave[round][i] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					fr.drawStringWithShadow((waves[i] ? WAVE : ""),
							((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
									- fr.getStringWidth(WAVE + "W1 00:10")),
							y - 8 * (strArray[round].length - 1) + 8 * i, 0);
					str = "\u00A7" + waveColors[i] + "W" + (i + 1) + " " + bossWaves[i] + "0" + min + ":"
							+ (bossWave[round][i] == 4 ? "\u00A7b" : (bossWave[round][i] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 8 * (strArray[round].length - 1) + 8 * i, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (RenderTimerHandler.timer == null) {
			return;
		}

		try {

			double[] waveDelays = getDoubleWaveDelays(strArray[round]);
			double[] orgWaveDelays = getDoubleWaveDelays(orgStrArray[round]);

			String s = RenderTimerHandler.timer;

			double time = Double.valueOf(s.split(":")[0]) * 60 + Double.valueOf(s.split(":")[1]);

			if (time < 1) {
				waveColors = new String[] { "8", "8", "8", "8", "8", "8", "8", "8" };
				waves = new boolean[] { false, false, false, false, false, false, false, false };
				playSounds = new boolean[] { false, false, false, false, false, false, false, false };
				playCounts = new boolean[] { false, false, false };
			}

			if (ZombiesAddonConfig.highlightStyle.equals("Zombies Addon")) {

				for (int i = 0; i <= 7; i++) {
					if (waveDelays.length == i) {
						break;
					}

					if (time - orgWaveDelays[i] >= DESPAWN_TIME) {
						waveColors[i] = "c";
                        waves[i] = waveDelays.length == i + 1;
					} else if (waveDelays[i] - time <= 0) {
						for (int j = 1; j <= 8; j++) {
							try {
								if (waves[i + j]) {
									waves[i] = false;
									break;
								}
							} catch (Exception e) {
								waves[i] = true;
								if (waveDelays.length == i + 1) {
									playLastSound(i);
								}
								playSound(i);
							}
						}
						waveColors[i] = "a";
					} else if (waveDelays[i] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						if (GameDetect.getMap() != 3 && waveDelays.length == i + 1) {
							playCountSound((int) (waveDelays[i] - time), waveDelays[i] - time);
						}
						playSounds[i] = true;
						waveColors[i] = "e";
					} else {
						if (waveDelays.length == i + 1) {
							playCounts = new boolean[] { true, true, true };
						}
						waveColors[i] = "8";
						waves[i] = false;
					}
				}

			} else if (GameDetect.getMap() != 3) {// SST

				for (int i = 0; i <= 7; i++) {
					if (waveDelays.length == i) {
						break;
					}

					if (time - orgWaveDelays[i] >= DESPAWN_TIME) {
						waveColors[i] = "c";
                        waves[i] = waveDelays.length == i + 1;
					} else if (time - waveDelays[i] < HIGHLIGHT_DELAY_SST) {
						for (int j = 1; j <= 7; j++) {
							try {
								if (waves[i - j]) {
									playCounts = new boolean[] { true, true, true };
									waveColors[i] = "7";
									waves[i] = false;
									break;
								}
							} catch (Exception e) {
								if (waveDelays[i] - time == 0.0) {
									if (waveDelays.length == i + 1) {
										playLastSound(i);
									}
									playSound(i);
								} else {
									playSounds[i] = true;
								}

								if (waveDelays[i] - time > 0 && waveDelays[i] - time <= 3) {
									if (waveDelays.length == i + 1) {
										playCountSound((int) (waveDelays[i] - time), waveDelays[i] - time);
									}
								}
								waveColors[i] = "e";
								waves[i] = true;
							}
						}
					} else {
						if (waveDelays.length == i + 1) {
							waveColors[i] = "e";
							waves[i] = true;
						} else {
							waveColors[i] = "8";
							waves[i] = false;
						}
					}
				}

			} else {// SST AA

				for (int i = 0; i <= 7; i++) {
					if (waveDelays.length == i) {
						break;
					}

					if (time - orgWaveDelays[i] >= DESPAWN_TIME) {
						waveColors[i] = "c";
                        waves[i] = waveDelays.length == i + 1;
					} else if (time - waveDelays[i] <= 0) {
						for (int j = 1; j <= 7; j++) {
							try {
								if (waves[i - j]) {
									waveColors[i] = "7";
									waves[i] = false;
									break;
								}
							} catch (Exception e) {
								if (waveDelays[i] - time == 0.0) {
									if (waveDelays.length == i + 1) {
										playLastSound(i);
									}
									playSound(i);
								} else {
									playSounds[i] = true;
								}
								waveColors[i] = "e";
								waves[i] = true;
							}
						}
					} else {
						if (waveDelays.length == i + 1) {
							waveColors[i] = "e";
							waves[i] = true;
						} else {
							waveColors[i] = "8";
							waves[i] = false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void playSound(int i) {
		if (!ZombiesAddonConfig.playSound) {
			return;
		}
		if (!playSounds[i]) {
			return;
		}

		playSounds[i] = false;

		Minecraft.getMinecraft().thePlayer.playSound(ZombiesAddonConfig.soundName, 1.0F,
				(float) ZombiesAddonConfig.soundPitch);
	}

	private static void playLastSound(int i) {
		if (!ZombiesAddonConfig.playSound) {
			return;
		}
		if (!playSounds[i]) {
			return;
		}

		playSounds[i] = false;

		Minecraft.getMinecraft().thePlayer.playSound(ZombiesAddonConfig.lastSoundName, 1.0F,
				(float) ZombiesAddonConfig.lastSoundPitch);
	}

	private static void playCountSound(int i, double d) {
		if (!ZombiesAddonConfig.playCount) {
			return;
		}
		if (!(d == 3.0 || d == 2.0 || d == 1.0)) {
			return;
		}

		if (!playCounts[i - 1]) {
			return;
		}

		playCounts[i - 1] = false;

		Minecraft.getMinecraft().thePlayer.playSound(ZombiesAddonConfig.countSoundName, 1.0F,
				(float) ZombiesAddonConfig.countSoundPitch);

	}

	@SubscribeEvent
	public void onChatReceived(ClientChatReceivedEvent event) {
		if (!event.message.toString().contains("The Helicopter is on its way! Hold out for 120 more seconds!")) {
			return;
		}
		escape = true;
		// Minecraft.getMinecraft().thePlayer.playSound("mob.wither.spawn", 1.0F, 1.0F);
		AutoSplitPacketInterceptor.runTimer();
	}
}
