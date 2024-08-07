//Code in Cornering by syeyoung

package kr.hahaha98757.zombiesaddon.utils;

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
import net.minecraft.util.EnumChatFormatting;

public class Utils {
    public static final String LINE = "\u00A7e-----------------------------------------------------";
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final FontRenderer fr = mc.fontRendererObj;

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
        byte[] rdq = new byte[] { 0, 0, 0 };
        if (mc.thePlayer == null) return rdq;

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));

        if (scoreObjective == null) return rdq;

        if (!EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equalsIgnoreCase("ZOMBIES")) return rdq;

        for (Score score : scoreboard.getSortedScores(scoreObjective)) {
            String str = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName());
            if (score.getScorePoints() <= 10 && score.getScorePoints() >= 7) {
                str = EnumChatFormatting.getTextWithoutFormattingCodes(str);

                try {
                    str = str.split(":")[1].replaceAll("[^A-Za-z0-9\uAC00-\uD7A3]", "");
                } catch (Exception e) {
                    continue;
                }

                if (str.equalsIgnoreCase("revive") || str.equalsIgnoreCase("\uBD80\uD65C")) rdq[0]++;
                else if (str.equalsIgnoreCase("dead") || str.equalsIgnoreCase("\uC0AC\uB9DD")) rdq[1]++;
                else if (str.equalsIgnoreCase("quit") || str.equalsIgnoreCase("\uB5A0\uB0A8")) rdq[2]++;
            }
        }
        return rdq;
    }

    /**
     * Checks if you play Zombies.
     *
     * @return Returns true if you play Zombies.
     */
    public static boolean isZombies() {
        if (mc.thePlayer == null) return false;

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));

        if (scoreObjective == null) return false;

        return EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equalsIgnoreCase("ZOMBIES");
    }

    /**
     * Get the map.
     * <p>
     * 1: Dead End
     * 2: Bad Blood
     * 3: Alien Arcadium
     * 4: Prison
     *
     * @return Returns 0 if can't check the map.
     */
    public static byte getMap() {
        if (mc.theWorld == null) return 0;

        if (!isZombies()) return 0;

        String blockName = mc.theWorld.getBlockState(new BlockPos(0, 72, 12)).getBlock().getUnlocalizedName();

        switch (blockName) {
            case "tile.cloth":// wool
                return 1;
            case "tile.stonebricksmooth":// stonebrick
                return 2;
            case "tile.woolCarpet":// carpet
                return 3;
            case "tile.clayHardenedStained":// stained_hardened_clay
                return 4;
        }
        return 0;
    }

    /**
     * Get the area.
     *
     * @return The name of the area. Returns empty if can't check the area.
     */
    public static String getArea() {
        if (mc.thePlayer == null) return "";

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));

        if (scoreObjective == null) return "";

        if (!EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equalsIgnoreCase("ZOMBIES")) return "";

        for (Score score : scoreboard.getSortedScores(scoreObjective)) {
            String str = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName());
            if (score.getScorePoints() == 3) {
                str = EnumChatFormatting.getTextWithoutFormattingCodes(str);

                try {
                    return str.split(":")[1].replaceAll("[^A-Za-z]", "");
                } catch (Exception e) {
                    return "";
                }
            }
        }
        return "";
    }

    /**
     * Get the round.
     *
     * @return Returns 0 if can't check the round.
     */
    public static byte getRound() {
        if (mc.thePlayer == null) return 0;

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));

        if (scoreObjective == null) return 0;

        if (!EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equalsIgnoreCase("ZOMBIES")) return 0;

        for (Score score : scoreboard.getSortedScores(scoreObjective)) {
            String str = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName());
            if (score.getScorePoints() == 13) {
                str = EnumChatFormatting.getTextWithoutFormattingCodes(str);

                try {
                    return Byte.parseByte(str.replaceAll("[^0-9]", ""));
                } catch (Exception e) {
                    return 0;
                }
            }
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
     * @return If can't check the difficult, returns 1.
     */
    public static byte getDifficult(byte map, byte round) {// Normal: 1, Hard: 2, RIP: 3
        if (mc.thePlayer == null) return 1;

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));

        if (scoreObjective == null) return 1;

        if (!EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equalsIgnoreCase("ZOMBIES")) return 1;

        for (Score score : scoreboard.getSortedScores(scoreObjective)) {
            String str = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName());
            if (score.getScorePoints() == 12) {
                str = str.replaceAll("\u00A7.", "");
                try {
                    str = str.replaceAll("[^0-9]", "");
                } catch (Exception var13) {
                    return 1;
                }

                if (map == 1) {
                    if (round == 4 && str.equals("26")) return 3;
                    if (round == 9 && str.equals("34")) return 3;
                    if (round == 14) {
                        if (str.equals("41")) return 2;
                        else if (str.equals("48")) return 3;
                    }
                    if (round == 19 && str.equals("68")) return 3;
                    if (round == 24) {
                        if (str.equals("70")) return 2;
                        else if (str.equals("78")) return 3;
                    }
                    if (round == 29) {
                        if (str.equals("101")) return 2;
                        else if (str.equals("111")) return 3;
                    }
                } else if (map == 2) {
                    if (round == 14 && str.equals("42")) return 3;
                    if (round == 19 && str.equals("44")) return 3;
                    if (round == 24 && str.equals("66")) return 3;
                    if (round == 29 && str.equals("83")) return 3;
                }
            }
        }
        return 1;
    }

    /**
     * Get the language of Hypixel.
     * <p>
     * 0: English
     * 1: Korean
     *
     * @return If can't check the language, returns 0.
     */
    public static byte getLang() {
        if (mc.thePlayer == null) return 0;

        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));

        if (scoreObjective == null) return 0;

        if (!EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.getDisplayName()).equalsIgnoreCase("ZOMBIES")) return 0;

        for (Score score : scoreboard.getSortedScores(scoreObjective)) {
            String str = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName());
            if (score.getScorePoints() == 13) {
                str = EnumChatFormatting.getTextWithoutFormattingCodes(str);

                if (str.contains("Round")) return 0;
                else if (str.contains("\uB77C\uC6B4\uB4DC")) return 1;
            }
        }
        return 0;
    }
}