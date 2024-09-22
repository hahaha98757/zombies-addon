package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.enums.Map;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import kr.hahaha98757.zombiesaddon.utils.LanguageSupport;
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
        if (Utils.isNotZombies()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (ZombiesAddonConfig.isNotTogglePowerupPatterns()) return;
        if (Utils.isNotZombies()) return;

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        if (instaTimer) {
            short timer = ManualTimer.getTimer((byte) 1);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = "§cInsta Kill: " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(1), 0);
            if (timer == 1) instaTimer = false;
        }
        if (maxTimer) {
            short timer = ManualTimer.getTimer((byte) 2);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = "§9Max Ammo: " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(2), 0);
            if (timer == 1) maxTimer = false;
        }
        if (ssTimer) {
            short timer = ManualTimer.getTimer((byte) 3);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = "§5Shopping Spree: " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(3), 0);
            if (timer == 1) ssTimer = false;
        }
        if (dgTimer) {
            short timer = ManualTimer.getTimer((byte) 4);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = "§6Double Gold: " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(4), 0);
            if (timer == 1) dgTimer = false;
        }
        if (carpenterTimer) {
            short timer = ManualTimer.getTimer((byte) 5);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = "§9Carpenter: " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(5), 0);
            if (timer == 1) carpenterTimer = false;
        }
        if (bgTimer) {
            short timer = ManualTimer.getTimer((byte) 6);
            byte second = (byte) (timer / 20);
            String timerText = String.format("%02d", second);

            String str = "§6Bonus Gold: " + "§f" + timerText + "s";
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(6), 0);
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

                if (patternRound != 0 && showInsta) {
                    printInsta(patternRound);
                }
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

                if (patternRound != 0 && showInsta) {
                    printInsta(patternRound);
                }
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

                if (patternRound != 0 && showMax) {
                    printMax(patternRound);
                }
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

                if (patternRound != 0 && showMax) {
                    printMax(patternRound);
                }
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

                if (patternRound != 0 && showSS) {
                    printSS(patternRound);
                }
            } else if (showingSSPattern == 6) {
                for (int i : SS_PATTERN2)
                    if (round <= i) {
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showSS) {
                    printSS(patternRound);
                }
            } else if (showingSSPattern == 7) {
                for (int i : SS_PATTERN3)
                    if (round <= i) {
                        patternRound = (byte) i;
                        break;
                    }

                if (patternRound != 0 && showSS) {
                    printSS(patternRound);
                }
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

        if (LanguageSupport.INSTA_KILL_ENTITY.contains(name)) {
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

        if (LanguageSupport.MAX_AMMO_ENTITY.contains(name)) {
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

        if (LanguageSupport.SHOPPING_SPREE_ENTITY.contains(name)) {
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
        String title = EnumChatFormatting.getTextWithoutFormattingCodes(event.getTitle());

        if (LanguageSupport.ROUND.contains(LanguageSupport.getRoundText(title)) && title.replaceAll("[^0-9]", "").equals("1")) {
            spawnedEntities.clear();
            instaPattern = 0;
            maxPattern = 0;
            ssPattern = 0;
        }

        if (LanguageSupport.ROUND.contains(LanguageSupport.getRoundText(title))) {
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

        String str = "§cInsta Kill: " + "§fRound " + pattern;
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(1), 0);
    }

    private static void printMax(byte pattern) {
        if (maxTimer) return;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        String str = "§9Max Ammo: " + "§fRound " + pattern;
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(2), 0);
    }

    private static void printSS(byte pattern) {
        if (ssTimer) return;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        String str = "§5Shopping Spree: " + "§fRound " + pattern;
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(3), 0);
    }



    @SubscribeEvent
    public void onSound(SoundEvent event) {
        String soundName = event.getSoundName();

        if (soundName.equals("mob.wither.spawn")) {
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
        if (Hotkeys.instaTimer.isPressed()) {
            instaTimer = true;
            ManualTimer.resetTimer((byte) 1);
            Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §cInsta Kill §ewith a hotkey");
        }
        if (Hotkeys.maxTimer.isPressed()) {
            maxTimer = true;
            ManualTimer.resetTimer((byte) 2);
            Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §9Max Ammo §ewith a hotkey");
        }
        if (Hotkeys.ssTimer.isPressed()) {
            ssTimer = true;
            ManualTimer.resetTimer((byte) 3);
            Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §5Shopping Spree §ewith a hotkey");
        }
        if (Hotkeys.dgTimer.isPressed()) {
            dgTimer = true;
            ManualTimer.resetTimer((byte) 4);
            Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §6Double Gold §ewith a hotkey");
        }
        if (Hotkeys.carpenterTimer.isPressed()) {
            carpenterTimer = true;
            ManualTimer.resetTimer((byte) 5);
            Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §9Carpenter §ewith a hotkey");
        }
        if (Hotkeys.bgTimer.isPressed()) {
            bgTimer = true;
            ManualTimer.resetTimer((byte) 6);
            Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §6Bonus Gold §ewith a hotkey");
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());

        if (message.contains(">")) return;

        for (String str : LanguageSupport.INSTA_KILL_CHAT)
            if (message.contains(str)) {
                instaTimer = false;
                break;
            }

        for (String str : LanguageSupport.MAX_AMMO_CHAT)
            if (message.contains(str)) {
                maxTimer = false;
                break;
            }

        for (String str : LanguageSupport.SHOPPING_SPREE_CHAT)
            if (message.contains(str)) {
                ssTimer = false;
                break;
            }

        for (String str : LanguageSupport.DOUBLE_GOLD_CHAT)
            if (message.contains(str)) {
                dgTimer = false;
                break;
            }

        for (String str : LanguageSupport.CARPENTER_CHAT)
            if (message.contains(str)) {
                carpenterTimer = false;
                break;
            }

        for (String str : LanguageSupport.BONUS_GOLD_CHAT)
            if (message.contains(str)) {
                bgTimer = false;
                break;
            }
    }
}