//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.utils;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.enums.Difficulty;
import kr.hahaha98757.zombiesaddon.enums.GameMode;
import kr.hahaha98757.zombiesaddon.enums.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.HashMap;

public class Utils {
    public static final String LINE = "§e-----------------------------------------------------";
    private static Minecraft mc;
    private static FontRenderer fr;

    public static void set() {
        mc = Minecraft.getMinecraft();
        fr = mc.fontRendererObj;
    }

    public static boolean isModDisable() {
        return !ZombiesAddonConfig.isEnableMod();
    }

    public static void addChat(String text) {
        mc.thePlayer.addChatComponentMessage(new ChatComponentText(text));
    }

    public static void addChatWithLine(String text) {
        mc.thePlayer.addChatComponentMessage(new ChatComponentText(LINE + "\n" + text + "\n" + LINE));
    }

    public static void addTranslationChat(String key, Object... objects) {
        mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation(LanguageUtils.getTranslateKey(key), objects));
    }

    public static void addChatWithURL(String beforeText, String urlText, String url, String urlHoverText, String afterText) {
        ChatComponentText urlText_ = new ChatComponentText(urlText);

        urlText_.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        urlText_.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(urlHoverText)));

        ChatComponentText text = new ChatComponentText("");

        text.appendText(beforeText);
        text.appendSibling(urlText_);
        text.appendText(afterText);

        mc.thePlayer.addChatComponentMessage(text);
    }

    public static void sendChat(String text) {
        mc.thePlayer.sendChatMessage(text);
    }

    public static String getTranslatedString(String key, boolean isFormatted, Object... objects) {
        ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation(LanguageUtils.getTranslateKey(key), objects);
        return isFormatted ? chatComponentTranslation.getFormattedText() : chatComponentTranslation.getUnformattedText();
    }

    public static float getX() {
        return new ScaledResolution(mc).getScaledWidth();
    }

    public static float getX(String text) {
        return getX() - fr.getStringWidth(text) - 1.0F;
    }

    public static float getY() {
        return new ScaledResolution(mc).getScaledHeight();
    }

    public static float getYFont() {
        return getY() - fr.FONT_HEIGHT - 1.0F;
    }

    private static HashMap<Integer, String> getScoreboard() {
        if (mc.theWorld == null || mc.thePlayer == null) return null;

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(1);

        if (scoreObjective == null || !EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equals("ZOMBIES")) return null;

        HashMap<Integer, String> scoreboards = new HashMap<>();
        for (Score score : scoreboard.getSortedScores(scoreObjective))
            scoreboards.put(score.getScorePoints(), EnumChatFormatting.getTextWithoutFormattingCodes(ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName())).replaceAll("[^A-Za-z0-9가-힣:_.,/\\s]", "").trim());
        return scoreboards;
    }

    private static boolean isHypixel() {
        HashMap<Integer, String> scoreboard = getScoreboard();
        if (scoreboard == null) return false;
        String serverIP = scoreboard.get(1);
        return serverIP != null && serverIP.equals("www.hypixel.net");
    }

    public static boolean isNotZombies() {
        return getScoreboard() == null;
    }

    public static boolean isNotPlayZombies() {
        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return true;

        if (isHypixel()) {
            String str = scoreboard.get(10);
            return str == null || !str.contains(mc.thePlayer.getName());
        }

        for (int i = 7; i <= 10; i++) {
            String str = scoreboard.get(i);
            if (str == null) continue;

            if (str.contains(mc.thePlayer.getName())) return false;
        }
        return true;
    }

    public static byte getRound() {
        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return 0;

        if (isHypixel()) {
            String str = scoreboard.get(13);
            try {
                return Byte.parseByte(str.replaceAll("[^0-9]", ""));
            } catch (Exception e) {
                return 0;
            }
        }

        for (int i = 10; i <= 13; i++) {
            String str = scoreboard.get(i);
            if (str == null) continue;

            if (!str.contains(":") && (str.contains("Round") || str.contains("라운드"))) return Byte.parseByte(str.replaceAll("[^0-9]", ""));
        }
        return 0;
    }

    public static Map getMap() {
        World world = mc.theWorld;

        if (isNotZombies()) return null;

        BlockPos pos = new BlockPos(44, 71, 0);

        if (!world.isBlockLoaded(pos)) return null;

        switch (world.getBlockState(pos).getBlock().getUnlocalizedName()) {
            case "tile.air":
                return Map.DEAD_END;
            case "tile.cloth":
                return Map.BAD_BLOOD;
            case "tile.stoneSlab":
                return Map.ALIEN_ARCADIUM;
            case "tile.woodSlab":
                return Map.PRISON;
            default:
                return null;
        }
    }

    public static GameMode getGameMode(Difficulty difficulty) {
        Map map = getMap();

        if (map == null) return null;

        switch (map) {
            case DEAD_END:
                switch (difficulty) {
                    case NORMAL:
                        return GameMode.DEAD_END_NORMAL;
                    case HARD:
                        return GameMode.DEAD_END_HARD;
                    case RIP:
                        return GameMode.DEAD_END_RIP;
                }
            case BAD_BLOOD:
                switch (difficulty) {
                    case NORMAL:
                        return GameMode.BAD_BLOOD_NORMAL;
                    case HARD:
                        return GameMode.BAD_BLOOD_HARD;
                    case RIP:
                        return GameMode.BAD_BLOOD_RIP;
                }
            case ALIEN_ARCADIUM:
                return GameMode.ALIEN_ARCADIUM;
            case PRISON:
                switch (difficulty) {
                    case NORMAL:
                        return GameMode.PRISON_NORMAL;
                    case HARD:
                        return GameMode.PRISON_HARD;
                    case RIP:
                        return GameMode.PRISON_RIP;
                }
        }
        return null;
    }

    /**
     * Get the language of Hypixel.
     * <p>
     * 0: English
     * 1: Korean
     *
     * @return If it can't check the language, returns 0.
     */
    public static byte getLang() {
        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return 0;

        if (isHypixel()) {
            String str = scoreboard.get(13);
            if (str == null) return 0;

            if (str.contains("라운드")) return 1;
            else return 0;
        }

        for (int i = 10; i <= 13; i++) {
            String str = scoreboard.get(i);
            if (str == null) continue;
            if (str.contains(":")) continue;

            if (str.contains("라운드")) return 1;
        }
        return 0;
    }

    public static boolean isRoundText(String text) {
        return text.contains("Round") || text.contains("라운드");
    }

    public static byte[] getRevDeadQuit() {
        byte[] revDeadQuit = new byte[] {0, 0, 0};

        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return revDeadQuit;

        for (int i = 7; i <= 10; i++) {
            String str = scoreboard.get(i);

            if (str == null) continue;

            try {
                str = str.split(":")[1].trim();
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            switch (str) {
                case "REVIVE":
                case "부활":
                    revDeadQuit[0]++;
                    break;
                case "DEAD":
                case "사망":
                    revDeadQuit[1]++;
                    break;
                case "QUIT":
                case "떠남":
                    revDeadQuit[2]++;
                    break;
            }
        }
        return revDeadQuit;
    }

    public static int getLevel(String itemName) {
        if (itemName.contains("Ultimate")) try {
            itemName = itemName.split("Ultimate")[1].trim();
        } catch (Exception e) {
            return 0;
        }
        if (itemName.contains("레벨")) itemName = itemName.split("레벨")[0].trim();

        if (itemName.equals("II") || itemName.equals("Extra Health II") || itemName.equals("추가 체력 II") || itemName.contains("2") || itemName.equals("Extra Health 2"))
            return 2;
        else if (itemName.equals("III") || itemName.equals("Extra Health III") || itemName.equals("추가 체력 III") || itemName.contains("3") || itemName.equals("Extra Health 3"))
            return 3;
        else if (itemName.equals("IV") || itemName.equals("Extra Health IV") || itemName.equals("추가 체력 IV") || itemName.contains("4") || itemName.equals("Extra Health 4"))
            return 4;
        else if (itemName.equals("V") || itemName.equals("Extra Health V") || itemName.equals("추가 체력 V") || itemName.contains("5") || itemName.equals("Extra Health 5"))
            return 5;
        else if (itemName.equals("Extra Health VI") || itemName.equals("추가 체력 VI") || itemName.equals("Extra Health 6"))
            return 6;
        else if (itemName.equals("Extra Health VII") || itemName.equals("추가 체력 VII") || itemName.equals("Extra Health 7"))
            return 7;
        else if (itemName.equals("Extra Health VIII") || itemName.equals("추가 체력 VIII") || itemName.equals("Extra Health 8"))
            return 8;
        else if (itemName.equals("Extra Health IX") || itemName.equals("추가 체력 IX") || itemName.equals("Extra Health 9"))
            return 9;
        else if (itemName.equals("Extra Health X") || itemName.equals("추가 체력 X") || itemName.equals("Extra Health 10"))
            return 10;
        else return 0;
    }

    /**
     * Get a color of the boss wave.
     *
     *
     * @param map A map.
     * @param difficulty A difficulty.
     * @param round A round.
     * @param wave A wave.
     * @return A color code with "§"
     */
    public static String getBossColor1(Map map, Difficulty difficulty, byte round, byte wave) {
        if (map == null) return "";

        switch (map) {
            case DEAD_END:
                switch (round) {
                    case 5:
                        if (difficulty == Difficulty.RIP && wave == 3) return "§6";
                        break;
                    case 10:
                        if (wave == 3) return "§6";
                        if (difficulty == Difficulty.RIP && wave == 4) return "§6";
                        break;
                    case 15:
                        if (difficulty != Difficulty.NORMAL && wave == 2) return "§6";
                        if (difficulty == Difficulty.RIP && wave == 3) return "§c";
                        break;
                    case 20:
                        if (wave == 3) return "§c";
                        if (difficulty == Difficulty.RIP && wave == 4) return "§c";
                        break;
                    case 25:
                        if (difficulty == Difficulty.HARD && wave == 3) return "§6";
                        if (difficulty == Difficulty.RIP && (wave == 1 || wave == 2)) return "§6";
                        if (difficulty == Difficulty.RIP && wave == 3) return "§5";
                        break;
                    case 30:
                        if (wave == 3) return "§5";
                        if (difficulty != Difficulty.NORMAL && wave == 2) return "§5";
                        if (difficulty == Difficulty.RIP) return "§5";
                        break;
                    default:
                        return "";
                }
            case BAD_BLOOD:
                switch (round) {
                    case 10:
                        if (wave == 3) return "§a";
                        break;
                    case 15:
                        if (difficulty == Difficulty.RIP) return "§a";
                        break;
                    case 20:
                    case 30:
                        if (difficulty != Difficulty.RIP && wave == 2) return "§5";
                        if (difficulty == Difficulty.RIP && (wave == 1 || wave == 3)) return "§5";
                        break;
                    case 25:
                        if (difficulty == Difficulty.RIP && wave == 3) return "§a";
                        break;
                    default:
                        return "";
                }
            case ALIEN_ARCADIUM:
                switch (round) {
                    case 15:
                        if (wave == 6) return "§3";
                        break;
                    case 20:
                        if (wave == 3 || wave == 5) return "§3";
                        break;
                    case 22:
                        if (wave == 4 || wave == 6) return "§3";
                        break;
                    case 24:
                    case 43:
                        if (wave == 2 || wave == 4 || wave == 6) return "§3";
                        break;
                    case 25:
                        return "§a";
                    case 30:
                    case 42:
                    case 44:
                        return "§3";
                    case 35:
                    case 59:
                    case 102:
                    case 103:
                    case 104:
                    case 105:
                        return "§4";
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 41:
                        if (wave == 2 || wave == 3) return "§3";
                        break;
                    case 40:
                        if (wave == 2 || wave == 3) return "§3";
                        if (wave == 5) return "§4";
                        break;
                    case 45:
                        if (wave == 2) return "§3";
                        if (wave == 3 || wave == 4) return "§4";
                        break;
                    case 46:
                    case 48:
                        if (wave == 4) return "§4";
                        break;
                    case 47:
                        if (wave == 3) return "§3";
                        break;
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                        if (wave == 2 || wave == 4) return "§3";
                        break;
                    case 54:
                    case 58:
                        if (wave == 2 || wave == 4) return "§3";
                        if (wave == 5) return "§4";
                        break;
                    case 55:
                        if (wave == 1 || wave == 2 || wave == 3 || wave == 4 || wave == 5) return "§3";
                        if (wave == 6) return "§4";
                        break;
                    case 56:
                        if (wave == 1) return "§a";
                        break;
                    case 57:
                        if (wave == 1) return "§4";
                        break;
                    case 60:
                        if (wave == 3 || wave == 4) return "§4";
                        break;
                    case 64:
                    case 68:
                    case 78:
                    case 88:
                    case 98:
                    case 69:
                    case 79:
                    case 89:
                    case 99:
                        if (wave == 5 || wave == 6) return "§4";
                        break;
                    case 74:
                    case 84:
                    case 94:
                        if (wave == 4 || wave == 5 || wave == 6) return "§4";
                        break;
                    case 65:
                    case 75:
                    case 85:
                    case 95:
                        if (wave == 4 || wave == 5 || wave == 6) return "§3";
                        break;
                    case 67:
                    case 77:
                    case 87:
                    case 97:
                        if (wave == 6) return "§4";
                        break;
                    case 70:
                    case 80:
                    case 90:
                    case 100:
                        if (wave == 4 || wave == 5 || wave == 6) return "§3";
                        if (wave == 2 || wave == 3) return "§4";
                        break;
                    case 101:
                        return "§0";
                    default:
                        return "";
                }
            case PRISON:
                switch (round) {
                    case 10:
                    case 20:
                        if (wave == 3) return "§c";
                        break;
                    case 30:
                        return "§6";
                    case 31:
                        if (wave == 5) return "§6";
                        break;
                    default:
                        return "";
                }
            default:
                return "";
        }
    }

    /**
     * Get a color of the boss wave in Alien Arcadium.
     *
     *
     * @param round A round.
     * @param wave A wave.
     * @return A color code with "§"
     */
    public static String getBossColor2(byte round, byte wave) {
        switch (round) {
            case 54:
            case 58:
                if (wave == 2) return "§4";
                break;
            case 55:
                if (wave == 5) return "§4";
                break;
            case 70:
            case 80:
            case 90:
            case 100:
                if (wave == 4 || wave == 5 || wave == 6) return "§4";
                break;
            default:
                return "";
        }
        return "";
    }
}