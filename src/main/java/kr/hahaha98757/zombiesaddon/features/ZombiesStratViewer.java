//Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.KeyBindings;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class ZombiesStratViewer {
    public static final List<String> START_LINES = new ArrayList<>();
    private static final List<String> ACTUAL_LINES = new ArrayList<>();
    public static int currentLine = 0;
    public static int linesOfView = 1;
    private static int width = 0;
    private static int height = 0;

    public static boolean zombiesStratViewer;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;
        if (KeyBindings.zsvScrollDown.isPressed()) {
            if (currentLine + 1 < START_LINES.size()) ++currentLine;
            recalcActualLines();
        } else if (KeyBindings.zsvScrollUp.isPressed()) {
            if (currentLine > 0) --currentLine;
            recalcActualLines();
        }
    }

    public static void recalcActualLines() {
        height = 0;
        width = 20;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        ACTUAL_LINES.clear();

        for (int i = currentLine; i < currentLine + linesOfView; ++i) {
            if (!(i < START_LINES.size())) continue;

            String line = START_LINES.get(i);
            if (line.isEmpty()) {
                ACTUAL_LINES.add("$CONTROL EMPTY$");
                height += 10;
            } else {
                line = WordUtils.wrap(line, 55);
                String[] strArray = line.split("\n");

                for (String s : strArray) {
                    String str = s.replace("\r", "");
                    ACTUAL_LINES.add(str);
                    int width = fr.getStringWidth(str);
                    if (width > ZombiesStratViewer.width) ZombiesStratViewer.width = width;
                    height += 10;
                }

                ACTUAL_LINES.add("$HEY CONTROL$");
                height += 5;
            }
        }
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (!zombiesStratViewer) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;


        drawRect(5 + width, 5 + height);
        int y = -5;

        for (String str : ACTUAL_LINES)
            if (str.equalsIgnoreCase("$HEY CONTROL$")) y += 5;
            else if (str.equalsIgnoreCase("$CONTROL EMPTY$")) y += 10;
            else {
                y += 10;
                FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
                fr.drawStringWithShadow(str, 5.0F, (float) y, 0xffffff);
            }
    }

    private static void drawRect(int right, int bottom) {
        int left = 3;
        int top = 3;
        if (left < right) {
            int temp = left;
            left = right;
            right = temp;
        }

        if (top < bottom) {
            int temp = top;
            top = bottom;
            bottom = temp;
        }

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer buffer = tessellator.getWorldRenderer();

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(0.3F, 0.3F, 0.3F, 0.3F);

        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos(left, bottom, 0.0).endVertex();
        buffer.pos(right, bottom, 0.0).endVertex();
        buffer.pos(right, top, 0.0).endVertex();
        buffer.pos(left, top, 0.0).endVertex();
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}