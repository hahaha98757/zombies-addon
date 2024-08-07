package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

public class PowerupPatternsListener {
	private static final byte[] INSTA_PATTERN1 = { 2, 5, 8, 11, 14, 17, 20, 23 };
	private static final byte[] INSTA_PATTERN2 = { 3, 6, 9, 12, 15, 18, 21, 24 };
	private static final byte[] MAX_PATTERN1 = { 2, 5, 8, 12, 16, 21, 26, 31, 36, 41, 46, 51, 61, 66, 71, 76, 81, 86, 91, 96 };
	private static final byte[] MAX_PATTERN2 = { 3, 6, 9, 13, 17, 22, 27, 32, 37, 42, 47, 52, 62, 67, 72, 77, 82, 87, 92, 97, 102 };
	private static final byte[] SS_PATTERN1 = { 5, 15, 45, 55, 65, 75, 85, 95, 105 };
	private static final byte[] SS_PATTERN2 = { 6, 16, 26, 36, 46, 66, 76, 86, 96 };
	private static final byte[] SS_PATTERN3 = { 7, 17, 27, 37, 47, 67, 77, 87, 97 };

	public static byte instaPattern;
	public static byte maxPattern;
	public static byte ssPattern;

	public static boolean useInstaPattern = true;
	public static boolean useMaxPattern = true;
	public static boolean useSSPattern = true;

	public static boolean stop;

	public static Set<EntityLivingBase> spawnedEntities = new HashSet<>();

	@SubscribeEvent
	public void onPowerupText(RenderGameOverlayEvent.Post event) {// insta:1 max:2, dg:3, carpenter:4, ss:5, bg:6
		if (!ZombiesAddonConfig.enableMod) return;

		if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

		if (!ZombiesAddonConfig.togglePowerupPatterns) return;

		if (!Utils.isZombies()) return;

		if (WaveDelaysListener.escape) return;

		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		byte round = Utils.getRound();

		if (instaPattern != 0 && useInstaPattern) {
			byte patternRound = 0;

			if (instaPattern == 2) {
				for (int i : INSTA_PATTERN1)
					if (round <= i) {
						if (Utils.getMap() == 1 && i == 5) {
							patternRound = 8;
							break;
						}
						if (Utils.getMap() != 3 && i == 20) {
							patternRound = 23;
							break;
						}
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A7cInsta Kill: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 60, 0);
				}
			} else if (instaPattern == 3) {
				for (int i : INSTA_PATTERN2)
					if (round <= i) {
						if (Utils.getMap() != 3 && i == 15) {
							patternRound = 18;
							break;
						}
						if (Utils.getMap() == 3 && i == 24) {
							patternRound = 0;
							break;
						}
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A7cInsta Kill: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 60, 0);
				}
			}
		}

		if (maxPattern != 0 && useMaxPattern) {
			byte patternRound = 0;

			if (maxPattern == 2) {
				for (int i : MAX_PATTERN1)
					if (round <= i) {
						if (Utils.getMap() == 1 && i == 5) {
							patternRound = 8;
							break;
						}
						if (Utils.getMap() != 3 && i >= 31) {
							patternRound = 0;
							break;
						}
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A79Max Ammo: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 68, 0);
				}
			} else if (maxPattern == 3) {
				for (int i : MAX_PATTERN2)
					if (round <= i) {
						if (Utils.getMap() != 3 && i >= 32) {
							patternRound = 0;
							break;
						}
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A79Max Ammo: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 68, 0);
				}
			}
		}

		if (ssPattern != 0 && Utils.getMap() == 3 && useSSPattern) {
			byte patternRound = 0;

			if (ssPattern == 5) {
				for (int i : SS_PATTERN1)
					if (round <= i) {
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 76, 0);
				}
			} else if (ssPattern == 6) {
				for (int i : SS_PATTERN2)
					if (round <= i) {
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 92, 0);
				}
			} else if (ssPattern == 7) {
				for (int i : SS_PATTERN3)
					if (round <= i) {
						patternRound = (byte) i;
						break;
					}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					fr.drawStringWithShadow(str, Utils.getX(str), Utils.getYFont() - 92, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
		if (!ZombiesAddonConfig.enableMod) return;

		if (!Utils.isZombies() || !ZombiesAddonConfig.togglePowerupPatterns) return;

		if (stop) return;

		EntityLivingBase entity = event.entityLiving;

		if (!(entity instanceof EntityArmorStand)) return;

		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName()).replaceAll("[^A-Z\uAC00-\uD7A3]", "");

		if (spawnedEntities.contains(entity)) return;

		if (name.equals("INSTAKILL") || name.equals("\uC989\uC2DC\ucc98\uCE58")) {
			spawnedEntities.add(entity);

			for (int i : INSTA_PATTERN1)
				if (i == Utils.getRound()) {
					instaPattern = 2;
					break;
				}
			for (int i : INSTA_PATTERN2)
				if (i == Utils.getRound()) {
					instaPattern = 3;
					break;
				}
		}

		if (name.equals("MAXAMMO") || name.equals("\uD0C4\uC57D\uCDA9\uC804")) {
			spawnedEntities.add(entity);

			for (int i : MAX_PATTERN1)
				if (i == Utils.getRound()) {
					maxPattern = 2;
					break;
				}
			for (int i : MAX_PATTERN2)
				if (i == Utils.getRound()) {
					maxPattern = 3;
					break;
				}
		}

		if (name.equals("SHOPPINGSPREE") || name.equals("\uC9C0\uB984\uC2E0\uAC15\uB9BC")) {
			spawnedEntities.add(entity);

			for (int i : SS_PATTERN1)
				if (i == Utils.getRound()) {
					ssPattern = 5;
					break;
				}
			for (int i : SS_PATTERN2)
				if (i == Utils.getRound()) {
					ssPattern = 6;
					break;
				}
			for (int i : SS_PATTERN3)
				if (i == Utils.getRound()) {
					ssPattern = 7;
					break;
				}
		}
	}
}