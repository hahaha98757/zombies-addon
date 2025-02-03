//Code in Zombies Cornering Mod by syeyoung

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.KeyBindings;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.opengl.GL11;

public class BlockAlarm {
    public static boolean blockAlarm;

    public BlockAlarm() {
        blockAlarm = ZombiesAddonConfig.getModDefaultValues()[1];
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (Utils.isNotZombies()) return;
        if (!blockAlarm) return;

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        byte[] rdq = Utils.getRevDeadQuit();

        if (!((rdq[0] + rdq[1] + rdq[2] == 3) && rdq[0] >= 1)) return;

        GL11.glPushMatrix();
        GL11.glScalef(7.0F, 7.0F, 7.0F);
        fr.drawStringWithShadow("BLOCK", (Utils.getX() + fr.getStringWidth("BLOCK")) / 14.0F, (Utils.getY() / 2.0F - 8.0F) / 7.0F, 0xaa0000);
        GL11.glPopMatrix();
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;
        if (!KeyBindings.toggleBlockAlarm.isPressed()) return;
        blockAlarm = !blockAlarm;
        Utils.addTranslationChat("zombiesaddon.features.general.toggled", "§eBlock Alarm", (blockAlarm ? "§aon" : "§coff"));
    }
}