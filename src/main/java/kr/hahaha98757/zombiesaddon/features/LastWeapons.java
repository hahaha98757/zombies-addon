package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
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
                    if (i == 4) {
                        if (weapon.getItem() == Item.getItemById(351) && weapon.getItemDamage() == 8) {
                            String name = weapon.getDisplayName();
                            if (name.contains("Heal Skill") || name.contains("회복 기술"))
                                displayTexture("textures/items/heal_cool.png", x + 20*i, y);
                            else if (name.contains("Lightning Rod Skill") || name.contains("번개 막대 기술"))
                                displayTexture("textures/items/lrod_cool.png", x + 20*i, y);
                            else if (name.contains("Deployable Turret Skill"))
                                displayTexture("textures/items/deployable_turret_cool.png", x + 20*i, y);
                            continue;
                        }
                    }

                    int level = Utils.getLevel(EnumChatFormatting.getTextWithoutFormattingCodes(weapon.getDisplayName()));

                    renderItem.renderItemAndEffectIntoGUI(weapon, x + 20*i, y);

                    if (ZombiesAddonConfig.isDisplayWeaponsLevel() && level != 0)
                        displayTexture("textures/items/level" + level + ".png", x + 20 * i, y);
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

    private static void displayTexture(String path, int x, int y) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ZombiesAddon.MODID, path));
        GlStateManager.disableDepth();
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, 16, 16, 16, 16);
        GlStateManager.enableDepth();
    }
}