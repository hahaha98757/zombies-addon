package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.enums.Map;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.HashSet;
import java.util.Set;

public class PowerupPatterns {
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

    private static byte showingInstaPattern;
    private static byte showingMaxPattern;
    private static byte showingSSPattern;

    public static boolean useInstaPattern = true;
    public static boolean useMaxPattern = true;
    public static boolean useSSPattern = true;

    private static boolean showInsta;
    private static boolean showMax;
    private static boolean showSS;

    public static boolean instaTimer;
    public static boolean maxTimer;
    public static boolean ssTimer;
    public static boolean dgTimer;
    public static boolean carpenterTimer;
    public static boolean bgTimer;

    public static boolean gameEnd;

    public static final Set<EntityLivingBase> spawnedEntities = new HashSet<>();

    @SubscribeEvent
    public void drawTimerText(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (ZombiesAddonConfig.isNotTogglePowerupPatterns()) return;
        if (Utils.isNotZombies()) return;

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        if (instaTimer) {
            short timer = ManualTimer.getTimer((byte) 1);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = Utils.getTranslatedString("zombiesaddon.game.insta", true) + ": " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(1), 0xffffff);
            if (timer == 1) instaTimer = false;
        }
        if (maxTimer) {
            short timer = ManualTimer.getTimer((byte) 2);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = Utils.getTranslatedString("zombiesaddon.game.max", true) + ": " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(2), 0xffffff);
            if (timer == 1) maxTimer = false;
        }
        if (ssTimer) {
            short timer = ManualTimer.getTimer((byte) 3);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = Utils.getTranslatedString("zombiesaddon.game.ss", true) + ": " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(3), 0xffffff);
            if (timer == 1) ssTimer = false;
        }
        if (dgTimer) {
            short timer = ManualTimer.getTimer((byte) 4);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = Utils.getTranslatedString("zombiesaddon.game.dg", true) + ": " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(4), 0xffffff);
            if (timer == 1) dgTimer = false;
        }
        if (carpenterTimer) {
            short timer = ManualTimer.getTimer((byte) 5);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = Utils.getTranslatedString("zombiesaddon.game.carpenter", true) + ": " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(5), 0xffffff);
            if (timer == 1) carpenterTimer = false;
        }
        if (bgTimer) {
            short timer = ManualTimer.getTimer((byte) 6);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = Utils.getTranslatedString("zombiesaddon.game.bg", true) + ": " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(6), 0xffffff);
            if (timer == 1) bgTimer = false;
        }

    }

    @SubscribeEvent
    public void drawPatternsText(RenderGameOverlayEvent.Post event) {// insta:1 max:2, ss:3, dg:4, carpenter:5, bg:6
        if (Utils.isModDisable()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (ZombiesAddonConfig.isNotTogglePowerupPatterns()) return;
        if (Utils.isNotZombies()) return;

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        byte round = Utils.getRound();

        if (showingInstaPattern != 0 && useInstaPattern) {
            byte patternRound = 0;

            if (showingInstaPattern == 2) {
                for (int i : INSTA_PATTERN1)
                    if (round <= i) {
                        if (Utils.getMap() == Map.DEAD_END && i == 5) {
                            patternRound = 8;
                            break;
                        }
                        if (Utils.getMap() != Map.ALIEN_ARCADIUM && i == 20) {
                            patternRound = 23;
                            break;
                        }
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showInsta) printInsta(patternRound);
            } else if (showingInstaPattern == 3) {
                for (int i : INSTA_PATTERN2)
                    if (round <= i) {
                        if (Utils.getMap() != Map.ALIEN_ARCADIUM && i == 15) {
                            patternRound = 18;
                            break;
                        }
                        if (Utils.getMap() == Map.ALIEN_ARCADIUM && i == 24) {
                            patternRound = 0;
                            break;
                        }
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showInsta) printInsta(patternRound);
            }
        }

        if (showingMaxPattern != 0 && useMaxPattern) {
            byte patternRound = 0;

            if (showingMaxPattern == 2) {
                for (int i : MAX_PATTERN1)
                    if (round <= i) {
                        if (Utils.getMap() == Map.DEAD_END && i == 5) {
                            patternRound = 8;
                            break;
                        }
                        if (Utils.getMap() != Map.ALIEN_ARCADIUM && i >= 31) {
                            patternRound = 0;
                            break;
                        }
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showMax) printMax(patternRound);
            } else if (showingMaxPattern == 3) {
                for (int i : MAX_PATTERN2)
                    if (round <= i) {
                        if (Utils.getMap() != Map.ALIEN_ARCADIUM && i >= 32) {
                            patternRound = 0;
                            break;
                        }
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showMax) printMax(patternRound);
            }
        }

        if (showingSSPattern != 0 && Utils.getMap() == Map.ALIEN_ARCADIUM && useSSPattern) {
            byte patternRound = 0;

            if (showingSSPattern == 5) {
                for (int i : SS_PATTERN1)
                    if (round <= i) {
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showSS) printSS(patternRound);
            } else if (showingSSPattern == 6) {
                for (int i : SS_PATTERN2)
                    if (round <= i) {
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showSS) printSS(patternRound);
            } else if (showingSSPattern == 7) {
                for (int i : SS_PATTERN3)
                    if (round <= i) {
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showSS) printSS(patternRound);
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (Utils.isModDisable()) return;
        if (Utils.isNotZombies() || ZombiesAddonConfig.isNotTogglePowerupPatterns()) return;
        if (gameEnd) return;

        EntityLivingBase entity = event.entityLiving;

        if (!(entity instanceof EntityArmorStand)) return;

        String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName());

        if (spawnedEntities.contains(entity)) return;

        if (name.equals("INSTA KILL") || name.equals("즉시 처치")) {
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

        if (name.equals("MAX AMMO") || name.equals("탄약 충전")) {
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

        if (name.equals("SHOPPING SPREE") || name.equals("지름신 강림")) {
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

    @SubscribeEvent
    public void onTitle(TitleEvent event) {
        if (Utils.isModDisable()) return;
        String title = event.getTitle();

        if (Utils.isRoundText(title) && title.replaceAll("[^0-9]", "").equals("1")) {
            spawnedEntities.clear();
            instaPattern = 0;
            maxPattern = 0;
            ssPattern = 0;
        }

        if (Utils.isRoundText(title)) {
            showInsta = instaPattern != 0;
            showMax = maxPattern != 0;
            showSS = ssPattern != 0;

            showingInstaPattern = instaPattern;
            showingMaxPattern = maxPattern;
            showingSSPattern = ssPattern;
        }
    }

    private static void printInsta(byte pattern) {
        if (instaTimer) return;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        String str = Utils.getTranslatedString("zombiesaddon.game.insta", true) + ": " + Utils.getTranslatedString("zombiesaddon.game.round", true, pattern);
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(1), 0xffffff);
    }

    private static void printMax(byte pattern) {
        if (maxTimer) return;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        String str = Utils.getTranslatedString("zombiesaddon.game.max", true) + ": " + Utils.getTranslatedString("zombiesaddon.game.round", true, pattern);
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(2), 0xffffff);
    }

    private static void printSS(byte pattern) {
        if (ssTimer) return;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        String str = Utils.getTranslatedString("zombiesaddon.game.ss", true) + ": " + Utils.getTranslatedString("zombiesaddon.game.round", true, pattern);
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(3), 0xffffff);
    }


    private static boolean AAr10;

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;
        String soundName = event.getSoundName();

        if (soundName.equals("mob.wither.spawn") || soundName.equals("mob.enderdragon.end")) AAr10 = false;

        if (soundName.equals("mob.wither.spawn") || soundName.equals("mob.guardian.curse") && !AAr10) {
            if (soundName.equals("mob.guardian.curse")) AAr10 = true;
            gameEnd = false;
        } else if (soundName.equals("mob.enderdragon.end")) {
            instaTimer = false;
            maxTimer = false;
            ssTimer = false;
            dgTimer = false;
            carpenterTimer = false;
            bgTimer = false;
            gameEnd = true;
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;
        if (Hotkeys.instaTimer.isPressed()) {
            instaTimer = true;
            ManualTimer.resetTimer((byte) 1);
            Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§cInsta Kill");
        }
        if (Hotkeys.maxTimer.isPressed()) {
            maxTimer = true;
            ManualTimer.resetTimer((byte) 2);
            Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§9Max Ammo");
        }
        if (Hotkeys.ssTimer.isPressed()) {
            ssTimer = true;
            ManualTimer.resetTimer((byte) 3);
            Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§5Shopping Spree");
        }
        if (Hotkeys.dgTimer.isPressed()) {
            dgTimer = true;
            ManualTimer.resetTimer((byte) 4);
            Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§6Double Gold");
        }
        if (Hotkeys.carpenterTimer.isPressed()) {
            carpenterTimer = true;
            ManualTimer.resetTimer((byte) 5);
            Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§9Carpenter");
        }
        if (Hotkeys.bgTimer.isPressed()) {
            bgTimer = true;
            ManualTimer.resetTimer((byte) 6);
            Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§6Bonus Gold");
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (Utils.isModDisable()) return;
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());

        if (message.contains(">")) return;

        if (message.contains("Insta Kill") || message.contains("즉시 처치")) instaTimer = false;
        if (message.contains("Max Ammo") || message.contains("탄약 보급")) maxTimer = false;
        if (message.contains("Shopping Spree") || message.contains("지름신 강림")) ssTimer = false;
        if (message.contains("Double Gold") || message.contains("지름신 강림")) dgTimer = false;
        if (message.contains("Carpenter") || message.contains("목수")) carpenterTimer = false;
        if (message.contains("Bonus Gold") || message.contains("보너스 골드")) bgTimer = false;
    }
}