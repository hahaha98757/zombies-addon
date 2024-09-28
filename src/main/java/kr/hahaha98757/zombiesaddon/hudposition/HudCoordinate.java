//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.hudposition;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class HudCoordinate {
    public double x;
    public double y;
    public int absoluteX;
    public int absoluteY;
    private final int width;
    private final int height;
    public boolean isDragging;
    private int dragOffsetX;
    private int dragOffsetY;
    private final int screenWidth;
    private final int screenHeight;
    private final int contents;
    private final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
    private boolean resizable;
    private int resizeCorner;

    public HudCoordinate(int contents, double x, double y, int width, int height, int screenWidth, int screenHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isDragging = false;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.contents = contents;
        this.resizable = true;
        this.resizeCorner = 0;
    }

    public int getContents() {
        return contents;
    }

    public void draw(GuiScreen gui) {
        absoluteX = (int) (x * screenWidth);
        absoluteY = (int) (y * screenHeight);
        int color = isDragging ? 0X80FFFFFF : 0;
        if (contents == 0) {
            Gui.drawRect(absoluteX, absoluteY, absoluteX + width, absoluteY + height, color);
            int widthDirector = fr.getStringWidth("➤ ");
            fr.drawStringWithShadow("§5➤ ", (float) absoluteX, (float) (absoluteY), 0);
            fr.drawStringWithShadow("§a" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY), 0);
            fr.drawStringWithShadow("§e" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT), 0);
            fr.drawStringWithShadow("§8" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT * 2), 0);
            fr.drawStringWithShadow("§8" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT * 3), 0);
            fr.drawStringWithShadow("§8" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT * 4), 0);
            fr.drawStringWithShadow("§8" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT * 5), 0);
            fr.drawStringWithShadow("§8" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT * 6), 0);
            fr.drawStringWithShadow("§8" + ZombiesAddonConfig.getTextStyle(), (float) (absoluteX + widthDirector), (float) (absoluteY + fr.FONT_HEIGHT * 7), 0);
        } else if (contents == 1) {
            Gui.drawRect(absoluteX, absoluteY, absoluteX + width + fr.getStringWidth(" "), absoluteY + height, color);
            fr.drawStringWithShadow("§cInsta Kill: §fRound 24", (float) (absoluteX + (absoluteX >= Utils.getX() / 2 ? fr.getStringWidth("Shopping Spree: Round 105") - fr.getStringWidth("Insta Kill: Round 24") : 0)), (float)absoluteY, 0);
            fr.drawStringWithShadow("§9Max Ammo: §fRound 102", (float) (absoluteX + (absoluteX >= Utils.getX() / 2 ? fr.getStringWidth("Shopping Spree: Round 105") - fr.getStringWidth("Max Ammo: Round 102") : 0)), (float) (absoluteY + fr.FONT_HEIGHT), 0);
            fr.drawStringWithShadow("§5Shopping Spree: §fRound 105", (float) absoluteX, (float) (absoluteY + fr.FONT_HEIGHT * 2), 0);
            fr.drawStringWithShadow("§6Double Gold: §f60s", (float) (absoluteX + (absoluteX >= Utils.getX() / 2 ? fr.getStringWidth("Shopping Spree: Round 105") - fr.getStringWidth("Double Gold: 60s") : 0)), (float) (absoluteY + fr.FONT_HEIGHT * 3), 0);
            fr.drawStringWithShadow("§9Carpenter: §f60s", (float) (absoluteX + (absoluteX >= Utils.getX() / 2 ? fr.getStringWidth("Shopping Spree: Round 105") - fr.getStringWidth("Carpenter: 60s") : 0)), (float) (absoluteY + fr.FONT_HEIGHT * 4), 0);
            fr.drawStringWithShadow("§6Bonus Gold: §f60s", (float) (absoluteX + (absoluteX >= Utils.getX() / 2 ? fr.getStringWidth("Shopping Spree: Round 105") - fr.getStringWidth("Bonus Gold: 60s") : 0)), (float) (absoluteY + fr.FONT_HEIGHT * 5), 0);
        } else if (contents == 2) {
            Gui.drawRect(absoluteX, absoluteY, absoluteX + width, absoluteY + height, color);
            fr.drawStringWithShadow("0:00.0", (float) absoluteX, (float) absoluteY, 0xFFFFFF);
        }

    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= absoluteX && mouseX <= absoluteX + width && mouseY >= absoluteY && mouseY <= absoluteY + height;
    }

    public void onMousePressed(int mouseX, int mouseY) {
        if (isMouseOver(mouseX, mouseY)) {
            isDragging = true;
            dragOffsetX = absoluteX - mouseX;
            dragOffsetY = absoluteY - mouseY;
        }

    }

    public void onMouseReleased() {
        isDragging = false;
        resizable = false;
        resizeCorner = 0;
    }

    public void onMouseDragged(int mouseX, int mouseY) {
        if (isDragging) {
            absoluteX = mouseX + dragOffsetX;
            absoluteY = mouseY + dragOffsetY;
            x = (double) absoluteX / (double) screenWidth;
            y = (double) absoluteY / (double) screenHeight;
            if (x < 0.0) {
                absoluteX = 0;
                x = 0.0;
            } else if (absoluteX + width > screenWidth) {
                absoluteX = screenWidth - width;
                x = (double) absoluteX / (double) screenWidth;
            }

            if (y < 0.0) {
                absoluteY = 0;
                y = 0.0;
            } else if (absoluteY + height > screenHeight) {
                absoluteY = screenHeight - height;
                y = (double) absoluteY / (double) screenHeight;
            }
        }

    }
}