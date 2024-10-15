package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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

            GlStateManager.pushAttrib();

            for (int i = 0; i < 9; i++) {
                ItemStack weapon = weapons[i];

                if (weapon != null) {
                    int level = Utils.getLevel(Utils.getTextWithoutColors(weapon.getDisplayName()));

                    renderItem.renderItemAndEffectIntoGUI(weapon, x + 20*i, y);

                    if (ZombiesAddonConfig.isDisplayWeaponsLevel() && level != 0) {
                        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("zombiesaddon", "textures/items/level" + level + ".png"));
                        GlStateManager.disableDepth();
                        Gui.drawModalRectWithCustomSizedTexture(x + 20*i, y, 0, 0, 16, 16, 16, 16);
                        GlStateManager.enableDepth();
                    }
                    renderItem.renderItemOverlayIntoGUI(fr, weapon, x + 20*i, y, null);
                }
            }

            GlStateManager.popAttrib();

            if (ZombiesAddonConfig.isDisplayArmors()) {
                x = (int) (Utils.getX() / 2 + 12);
                y = (int) (Utils.getY() - 60);

                for (int i = 0; i < 4; i++) {
                    renderItem.renderItemAndEffectIntoGUI(armors[3-i], x + 20*i, y);
                    renderItem.renderItemOverlayIntoGUI(fr, armors[3-i], x + 20*i, y, null);
                }
            }
        }

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (player.inventory.getStackInSlot(1) == null) return;

        for (int i = 0; i < 9; i++) weapons[i] = player.inventory.getStackInSlot(i);

        System.arraycopy(player.inventory.armorInventory, 0, armors, 0, 4);
    }

    @SubscribeEvent
    public void onTitle(TitleEvent event) {
        if (Utils.isModDisable()) return;
        if (event.getTitle().equals("You Win!") || event.getTitle().equals("승리했습니다!")) win = true;
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;
        if (event.getSoundName().equals("mob.wither.spawn")) win = false;
    }
}