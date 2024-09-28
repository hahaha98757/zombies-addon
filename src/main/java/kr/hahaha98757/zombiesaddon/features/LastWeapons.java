package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LastWeapons {
    public static final ItemStack[] weapons = new ItemStack[9];

    public static final ItemStack[] armors = new ItemStack[4];

    public static boolean win;

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (Utils.isModDisable()) return;
        if (!ZombiesAddonConfig.isToggleLastWeapons()) return;

        if (Utils.isNotPlayZombies()) {
            win = false;
            return;
        }

        if (win) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
            int x = (int) (Utils.getX() / 2 - 88);
            int y = (int) (Utils.getY() - 19);

            for (int i = 0; i < 9; i++) {
                renderItem.renderItemAndEffectIntoGUI(weapons[i], x + 20*i, y);
                renderItem.renderItemOverlayIntoGUI(fr, weapons[i], x + 20*i, y, null);
            }

            x = (int) (Utils.getX() / 2 + 12);
            y = (int) (Utils.getY() - 60);
            for (int i = 0; i < 4; i++) {
                renderItem.renderItemAndEffectIntoGUI(armors[3-i], x + 20*i, y);
                renderItem.renderItemOverlayIntoGUI(fr, armors[3-i], x + 20*i, y, null);
            }
        }

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (player.inventory.getStackInSlot(1) == null) return;

        for (int i = 0; i < 9; i++) weapons[i] = player.inventory.getStackInSlot(i);

        System.arraycopy(player.inventory.armorInventory, 0, armors, 0, 4);
    }

    @SubscribeEvent
    public void onTitle(TitleEvent event) {
        if (event.getTitle().equals("You Win!") || event.getTitle().equals("승리했습니다!"))
            win = true;
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (event.getSoundName().equals("mob.wither.spawn")) win = false;
    }
}