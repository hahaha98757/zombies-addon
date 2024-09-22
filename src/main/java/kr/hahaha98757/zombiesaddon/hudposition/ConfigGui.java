package kr.hahaha98757.zombiesaddon.hudposition;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiScreen {
    private final List<HudCoordinate> boxes = new ArrayList<>();

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int mouseX = Mouse.getEventX() * width / mc.displayWidth;
        int mouseY = height - Mouse.getEventY() * height / mc.displayHeight - 1;
        int button = Mouse.getEventButton();
        boolean mouseButtonDown = Mouse.getEventButtonState();

        for (HudCoordinate box : boxes) {
            if (box.isMouseOver(mouseX, mouseY) && button == 0) if (mouseButtonDown) box.onMousePressed(mouseX, mouseY);
            else box.onMouseReleased();

            if (box.isDragging) box.onMouseDragged(mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.InitGuiEvent.Pre(this, buttonList));
        super.initGui();
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.InitGuiEvent.Post(this, buttonList));
        int widthAutoSplits = fontRendererObj.getStringWidth("0:00.0");
        int widthWaveDelays = fontRendererObj.getStringWidth("➤ " + ZombiesAddonConfig.getTextStyle());
        int widthPowerupPatterns = fontRendererObj.getStringWidth("Shopping Spree: Round 105");
        ScaledResolution sr = new ScaledResolution(mc);
        buttonList.add(new GuiButton(1, sr.getScaledWidth() / 2 - 82, sr.getScaledHeight() - 25, 80, 20, "Done"));
        buttonList.add(new GuiButton(2, sr.getScaledWidth() / 2 - 82 + 85, sr.getScaledHeight() - 25, 80, 20, "Reset"));
        boxes.clear();
        HudCoordinate boxAutoSplits = new HudCoordinate(2, HUDUtils.getAutoSplitsX(), HUDUtils.getAutoSplitsY(), widthAutoSplits, fontRendererObj.FONT_HEIGHT, width, height);
        boxes.add(boxAutoSplits);
        HudCoordinate boxWaveDelays = new HudCoordinate(0, HUDUtils.getWaveDelaysX(), HUDUtils.getWaveDelaysY(), widthWaveDelays, fontRendererObj.FONT_HEIGHT * 8, width, height);
        boxes.add(boxWaveDelays);
        HudCoordinate boxPowerupPatterns = new HudCoordinate(1, HUDUtils.getPowerupPatternsX(), HUDUtils.getPowerupPatternsY(), widthPowerupPatterns, fontRendererObj.FONT_HEIGHT * 6, width, height);
        boxes.add(boxPowerupPatterns);

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        drawDefaultBackground();

        for (HudCoordinate box : boxes) box.draw(this);

        fontRendererObj.drawStringWithShadow("Zombies Addon", (float)sr.getScaledWidth() / 2.0F - (float)fontRendererObj.getStringWidth("Zombies Addon") / 2.0F, 10.0F, Color.WHITE.getRGB());
        fontRendererObj.drawStringWithShadow("Click \"Done\" to save your current HUD position settings.", (float)sr.getScaledWidth() / 2.0F - (float)fontRendererObj.getStringWidth("Click \"Done\" to save your current HUD position settings.") / 2.0F, (float)(height / 2), Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                for (HudCoordinate box : boxes) {
                    if (box.getContents() == 0) {
                        if (box.x != 0.9385416666666667) {
                            HUDUtils.waveDelaysX = box.x;
                            ZombiesAddonConfig.config.get("HUD", "waveDelaysX", -1).set(box.x);
                        }
                        if (box.y != 0.8404669314622879) {
                            HUDUtils.waveDelaysY = box.y;
                            ZombiesAddonConfig.config.get("HUD", "waveDelaysY", -1).set(box.y);
                        }
                    } else if (box.getContents() == 1) {
                        if (box.x != 0.8572916686534882) {
                            HUDUtils.powerupPatternsX = box.x;
                            ZombiesAddonConfig.config.get("HUD", "powerupPatternsX", -1).set(box.x);
                        }
                        if (box.y != 0.7354085743427277) {
                            HUDUtils.powerupPatternsY = box.y;
                            ZombiesAddonConfig.config.get("HUD", "powerupPatternsY", -1).set(box.y);
                        }
                    } else if (box.getContents() == 2) {
                        if (box.x != 0.9697916666666667) {
                            HUDUtils.autoSplitsX = box.x;
                            ZombiesAddonConfig.config.get("HUD", "autoSplitsX", -1).set(box.x);
                        }
                        if (box.y != 0.9805447477847338) {
                            HUDUtils.autoSplitsY = box.y;
                            ZombiesAddonConfig.config.get("HUD", "autoSplitsY", -1).set(box.y);
                        }
                    }
                }
                ZombiesAddonConfig.config.save();
                mc.displayGuiScreen(null);
                break;
            case 2:
                for (HudCoordinate box : boxes) {
                    if (box.getContents() == 0) {
                        ZombiesAddonConfig.config.get("HUD", "waveDelaysX", -1).set(-1);
                        ZombiesAddonConfig.config.get("HUD", "waveDelaysY", -1).set(-1);
                        HUDUtils.waveDelaysX = -1.0;
                        HUDUtils.waveDelaysY = -1.0;
                        new DelayedTask(() -> {
                            box.x = HUDUtils.getWaveDelaysX();
                            box.absoluteX = (int)(HUDUtils.getWaveDelaysX() * (double)width);
                            box.y = HUDUtils.getWaveDelaysY();
                            box.absoluteY = (int)(HUDUtils.getWaveDelaysY() * (double)height);
                        }, 2);
                    } else if (box.getContents() == 1) {
                        ZombiesAddonConfig.config.get("HUD", "powerupPatternsX", -1).set(-1);
                        ZombiesAddonConfig.config.get("HUD", "powerupPatternsY", -1).set(-1);
                        HUDUtils.powerupPatternsX = -1.0;
                        HUDUtils.powerupPatternsY = -1.0;
                        new DelayedTask(() -> {
                            box.x = HUDUtils.getPowerupPatternsX();
                            box.absoluteX = (int)(HUDUtils.getPowerupPatternsX() * (double)width);
                            box.y = HUDUtils.getPowerupPatternsY();
                            box.absoluteY = (int)(HUDUtils.getPowerupPatternsY() * (double)height);
                        }, 2);
                    } else if (box.getContents() == 2) {
                        ZombiesAddonConfig.config.get("HUD", "autoSplitsX", -1).set(-1);
                        ZombiesAddonConfig.config.get("HUD", "autoSplitsY", -1).set(-1);
                        HUDUtils.autoSplitsX = -1.0;
                        HUDUtils.autoSplitsY = -1.0;
                        new DelayedTask(() -> {
                            box.x =  HUDUtils.getAutoSplitsX();
                            box.absoluteX = (int)(HUDUtils.getAutoSplitsX() * (double)width);
                            box.y = HUDUtils.getAutoSplitsY();
                            box.absoluteY = (int)(HUDUtils.getAutoSplitsY() * (double)height);
                        }, 2);
                    }
                }
                ZombiesAddonConfig.config.save();
                break;
            default:
                ZombiesAddonConfig.config.save();
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}