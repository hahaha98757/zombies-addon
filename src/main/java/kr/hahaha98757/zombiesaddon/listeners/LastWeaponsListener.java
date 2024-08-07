package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LastWeaponsListener {
    public static String[] weapons = new String[] {"air", "air", "air", "air", "air", "air", "air", "air", "air"};

    public static String[] armors = new String[] {"air", "air", "air", "air"};

    public static boolean gameOver;

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event) {
        if (!ZombiesAddonConfig.enableMod) return;

        if (!ZombiesAddonConfig.toggleLastWeapons) return;

        if (!Utils.isZombies()) {
            gameOver = false;
            return;
        }

        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

        if (gameOver) {
            FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
            StringBuilder armorStrBuilder = new StringBuilder();
            armorStrBuilder.append("\u00A7eLast Armors: \u00A7r\u00A7f");
            for (int i = 0; i < 4; i++) {
                armorStrBuilder.append(armors[i]);
                if (i != 3) armorStrBuilder.append("\u00A7e/\u00A7r\u00A7f");
            }
            String armorStr = armorStrBuilder.toString();
            fr.drawStringWithShadow(armorStr, Utils.getX() / 2 - (float) fr.getStringWidth(armorStr) / 2, Utils.getY() / 1.6F, 0);

            StringBuilder weaponStrBuilder = new StringBuilder();
            weaponStrBuilder.append("\u00A7eLast Weapons: \u00A7r\u00A7f");
            for (int i = 0; i < 9; i++) {
                weaponStrBuilder.append(weapons[i]);
                if (i != 8) weaponStrBuilder.append("\u00A7e/\u00A7r\u00A7f");
            }
            String weaponStr = weaponStrBuilder.toString();
            fr.drawStringWithShadow(weaponStr, Utils.getX() / 2 - (float) fr.getStringWidth(weaponStr) / 2, Utils.getY() / 1.6F + fr.FONT_HEIGHT, 0);
        }

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (player.inventory.getStackInSlot(1) == null) return;

        for (int i = 0; i < 9; i++) {
            ItemStack itemStack = player.inventory.getStackInSlot(i);
            try {
                weapons[i] = itemStack.getDisplayName();
            } catch (Exception e) {
                weapons[i] = "air";
            }
        }

        for (int i = 0; i < 4; i++) {
            ItemStack armorItem = player.inventory.armorInventory[i];
            try {
                armors[i] = armorItem.getDisplayName();
            } catch (Exception e) {
                armors[i] = "air";
            }
        }
    }
}