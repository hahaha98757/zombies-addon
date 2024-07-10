package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class PowerupAlarmListener {
	private static final String[] IGNORE_ENTITY_SET = { "Armor Stand", "\u00A7c\u00A7lTarget Practice",
			"\u00A7e\u00A7lHOLD SNEAK TO REVIVE!", "\u00A7e\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0\u25A0"};
	private static final Pattern REVIVE_SECONDS_PATTERN = Pattern.compile("\u00A7c\\d+\\.\\d+s");

	private static final int[] INSTA_PATTERN1 = { 2, 5, 8, 11, 14, 17, 20, 23 };
	private static final int[] INSTA_PATTERN2 = { 3, 6, 9, 12, 15, 18, 21, 24 };
	private static final int[] MAX_PATTERN1 = { 2, 5, 8, 12, 16, 21, 26, 31, 36, 41, 46, 51, 61, 66, 71, 76, 81, 86, 91,
			96 };
	private static final int[] MAX_PATTERN2 = { 3, 6, 9, 13, 17, 22, 27, 32, 37, 42, 47, 52, 62, 67, 72, 77, 82, 87, 92,
			97, 102 };
	private static final int[] SS_PATTERN1 = { 5, 15, 45, 55, 65, 75, 85, 95, 105 };
	private static final int[] SS_PATTERN2 = { 6, 16, 26, 36, 46, 66, 76, 86, 96 };
	private static final int[] SS_PATTERN3 = { 7, 17, 27, 37, 47, 67, 77, 87, 97 };

	public static int instaPattern;
	public static int maxPattern;
	public static int ssPattern;

	public static boolean useInstaPattern = true;
	public static boolean useMaxPattern = true;
	public static boolean useSSPattern = true;

	public static Set<EntityLivingBase> spawnedEntities = new HashSet<>();

	@SubscribeEvent
	public void onPowerupText(RenderGameOverlayEvent.Post event) {// insta:1 max:2, dg:3, carpenter:4, ss:5, bg:6
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
			return;
		}

		if (!ZombiesAddonConfig.togglePowerupAlarm) {
			return;
		}

		if (!GameDetect.isZombiesGame()) {
			return;
		}

		if (WaveDelaysListener.escape) {
			return;
		}

		if (instaPattern != 0 && useInstaPattern) {
			int round = GameDetect.getRound();
			int patternRound = 0;

			if (instaPattern == 2) {
				for (int i : INSTA_PATTERN1) {
					if (round <= i) {
						if (GameDetect.getMap() == 1 && i == 5) {
							patternRound = 8;
							break;
						}
						if (GameDetect.getMap() != 3 && i == 20) {
							patternRound = 23;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A7cInsta Kill: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 60, 0);
				}
			} else if (instaPattern == 3) {
				for (int i : INSTA_PATTERN2) {
					if (round <= i) {
						if (GameDetect.getMap() != 3 && i == 15) {
							patternRound = 18;
							break;
						}
						if (GameDetect.getMap() == 3 && i == 24) {
							patternRound = 0;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A7cInsta Kill: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 60, 0);
				}
			}
		}

		if (maxPattern != 0 && useMaxPattern) {
			int round = GameDetect.getRound();
			int patternRound = 0;

			if (maxPattern == 2) {
				for (int i : MAX_PATTERN1) {
					if (round <= i) {
						if (GameDetect.getMap() == 1 && i == 5) {
							patternRound = 8;
							break;
						}
						if (GameDetect.getMap() != 3 && i >= 31) {
							patternRound = 0;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A79Max Ammo: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 68, 0);
				}
			} else if (maxPattern == 3) {
				for (int i : MAX_PATTERN2) {
					if (round <= i) {
						if (GameDetect.getMap() != 3 && i >= 32) {
							patternRound = 0;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A79Max Ammo: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 68, 0);
				}
			}
		}

		if (ssPattern != 0 && GameDetect.getMap() == 3 && useSSPattern) {
			int round = GameDetect.getRound();
			int patternRound = 0;

			if (ssPattern == 5) {
				for (int i : SS_PATTERN1) {
					if (round <= i) {
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 76, 0);
				}
			} else if (ssPattern == 6) {
				for (int i : SS_PATTERN2) {
					if (round <= i) {
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 92, 0);
				}
			} else if (ssPattern == 7) {
				for (int i : SS_PATTERN3) {
					if (round <= i) {
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 92, 0);
				}
			}
		}
	}
}
