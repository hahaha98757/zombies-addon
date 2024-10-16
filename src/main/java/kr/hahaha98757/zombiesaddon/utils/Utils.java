//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.utils;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.GameMode;
import kr.hahaha98757.zombiesaddon.enums.Difficulty;
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
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Utils {
    public static final String LINE = "§e-----------------------------------------------------";
    private static final Pattern formattingCodePattern = Pattern.compile("(?i)" + "§" + "[0-9A-FK-OR]");
    private static Minecraft mc;
    private static FontRenderer fr;

    public static void set() {
        mc = Minecraft.getMinecraft();
        fr = mc.fontRendererObj;
    }

    public static boolean isModDisable() {
        return !ZombiesAddonConfig.isEnableMod();
    }

    /**
     * Add the text on chat that only you can see.
     *
     * @param text The text that will add on chat.
     */
    public static void addChat(String text) {
        mc.thePlayer.addChatComponentMessage(new ChatComponentText(text));
    }

    /**
     * Add the text with Lines on chat that only you can see.
     *
     * @param text The text that will add on chat.
     */
    public static void addChatLine(String text) {
        mc.thePlayer.addChatComponentMessage(new ChatComponentText(LINE + "\n" + text + "\n" + LINE));
    }

    public static void sendChat(String text) {
        mc.thePlayer.sendChatMessage(text);
    }

    /**
     * Add the text contained URL on chat that only you can see.
     *
     * @param text1 The text before URL.
     * @param urlText The text contained URL.
     * @param url URL.
     * @param showURLText The text that will be displayed when the mouse hovers on URL.
     * @param text2 The text after URL.
     */
    public static void addChatWithURL(String text1, String urlText, String url, String showURLText, String text2) {
        ChatComponentText url1 = new ChatComponentText(urlText);

        url1.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        url1.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(showURLText)));

        ChatComponentText text = new ChatComponentText("");

        text.appendSibling(new ChatComponentText(text1));
        text.appendSibling(url1);
        text.appendSibling(new ChatComponentText(text2));

        mc.thePlayer.addChatComponentMessage(text);
    }

    /**
     * Play Sound
     *
     * @param name A name of the sound.
     * @param pitch A pitch of the sound.
     */
    public static void playSound(String name, float pitch) {
        mc.thePlayer.playSound(name, 1, pitch);
    }

    /**
     * Get the width of the screen.
     *
     * @return The width of the screen.
     */
    public static float getX() {
        return new ScaledResolution(mc).getScaledWidth();
    }

    /**
     * Get the width of the text minus the width of the screen.
     *
     * @return The width of the screen - the width of the text - 1.0F.
     */
    public static float getX(String str) {
        return new ScaledResolution(mc).getScaledWidth() - fr.getStringWidth(str) - 1.0F;
    }

    /**
     * Get the height of the screen.
     * @return The height of the screen.
     */
    public static float getY() {
        return new ScaledResolution(mc).getScaledHeight();
    }

    /**
     * Get the height of the text minus the height of the screen.
     *
     * @return The height of the screen - the height of the text - 1.0F.
     */
    public static float getYFont() {
        return new ScaledResolution(mc).getScaledHeight() - fr.FONT_HEIGHT - 1.0F;
    }

    public static String getTextWithoutColors(String text) {
        return text == null ? null : formattingCodePattern.matcher(text).replaceAll("");
    }

    private static HashMap<Integer, String> getScoreboard() {
        HashMap<Integer, String> scoreboards = new HashMap<>();
        if (mc.theWorld == null || mc.thePlayer == null) return null;

        Scoreboard scoreboard = mc.theWorld.getScoreboard();
        ScoreObjective sidebar = scoreboard.getObjectiveInDisplaySlot(1);

        if (sidebar == null || !getTextWithoutColors(sidebar.getDisplayName()).equals("ZOMBIES")) return null;

        for (Score score : scoreboard.getSortedScores(sidebar))
            for (int i = 1; i <= 15; i++)
                if (score.getScorePoints() == i)
                    scoreboards.put(i, getTextWithoutColors(ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName())).replaceAll("[^A-Za-z0-9가-힣:\\s]", "").trim());
        return scoreboards;
    }

    private static boolean isHypixel() {
        String serverIP = getScoreboard().get(1);
        return serverIP != null && serverIP.equals("www.hypixel.net");
    }

    /**
     * Get the status of players in Zombies.
     * <p>
     * The first is the number of revive players.
     * The second is the number of dead players.
     * The third is the number of quit players.
     *
     * @return Byte array.
     */
    public static byte[] getRevDeadQuit() {
        byte[] revDeadQuit = new byte[] { 0, 0, 0 };

        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return revDeadQuit;

        for (int i = 7; i <= 10; i++) {
            String str = scoreboard.get(i);

            if (str == null) continue;

            try {
                str = str.split(":")[1].trim();
            } catch (Exception e) {
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

    /**
     * Checks if there are you in Zombies.
     *
     * @return Returns false if there are you in Zombies.
     */
    public static boolean isNotZombies() {
        return getScoreboard() == null;
    }

    /**
     * Checks if you play Zombies.
     *
     * @return Returns false if you play Zombies.
     */
    public static boolean isNotPlayZombies() {
        java.util.Map<Integer, String> scoreboard = getScoreboard();

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

    /**
     * Get the map.
     *
     * @return Returns null if it can't check the map.
     */
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

    /**
     * Get the GameMode.
     *
     *
     * @param difficulty difficulty
     * @return GameMode.
     */
    public static GameMode getGameMode(Difficulty difficulty) {
        Map map = getMap();

        if (map == null) return null;

        switch (map) {
            case DEAD_END:
                switch (difficulty) {
                    case NORMAL:
                        return GameMode.DEAD_END;
                    case HARD:
                        return GameMode.DEAD_END_HARD;
                    case RIP:
                        return GameMode.DEAD_END_RIP;
                }
            case BAD_BLOOD:
                switch (difficulty) {
                    case NORMAL:
                        return GameMode.BAD_BLOOD;
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
                        return GameMode.PRISON;
                    case HARD:
                        return GameMode.PRISON_HARD;
                    case RIP:
                        return GameMode.PRISON_RIP;
                }
        }
        return null;
    }

    /**
     * Get the round.
     *
     * @return Returns 0 if it can't check the round.
     */
    public static byte getRound() {
        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return 0;

        if (isHypixel()) {
            String str = scoreboard.get(13);
            return str == null ? 0 : Byte.parseByte(str.replaceAll("[^0-9]", ""));
        }

        for (int i = 10; i <= 13; i++) {
            String str = scoreboard.get(i);
            if (str == null) continue;

            if (!str.contains(":") && (str.contains("Round") || str.contains("라운드"))) return Byte.parseByte(str.replaceAll("[^0-9]", ""));
        }
        return 0;
    }

    /**
     * Get the difficult.
     * <p>
     * 1: Normal
     * 2: Hard
     * 3: RIP
     *
     * @param map The code of the map.
     * @param round The round.
     * @return If it can't check the difficult, returns 1.
     */
    @Deprecated
    public static byte getDifficult(byte map, byte round) {// Normal: 1, Hard: 2, RIP: 3
        short zombiesLeft = 0;
        HashMap<Integer, String> scoreboard = getScoreboard();

        if (scoreboard == null) return 1;

        for (int i = 9; i <= 12; i++) {
            String str = scoreboard.get(i);
            if (str == null) continue;

            if (str.contains("Zombies Left: ") || str.contains("남은 좀비: ")) {
                zombiesLeft = Short.parseShort(str.replaceAll("[^0-9]", ""));
                break;
            }
        }

        if (map == 1) {
            if (round == 4 && zombiesLeft == 26) return 3;
            if (round == 9 && zombiesLeft == 34) return 3;
            if (round == 14) {
                if (zombiesLeft == 41) return 2;
                else if (zombiesLeft == 48) return 3;
            }
            if (round == 19 && zombiesLeft == 68) return 3;
            if (round == 24) {
                if (zombiesLeft == 70) return 2;
                else if (zombiesLeft == 78) return 3;
            }
            if (round == 29) {
                if (zombiesLeft == 101) return 2;
                else if (zombiesLeft == 111) return 3;
            }
        } else if (map == 2) {
            if (round == 14 && zombiesLeft == 42) return 3;
            if (round == 19 && zombiesLeft == 44) return 3;
            if (round == 24 && zombiesLeft == 66) return 3;
            if (round == 29 && zombiesLeft == 83) return 3;
        }
        return 1;
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