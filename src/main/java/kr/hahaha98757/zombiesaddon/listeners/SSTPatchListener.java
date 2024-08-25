package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class SSTPatchListener extends GuiNewChat {
    public SSTPatchListener(Minecraft minecraft) {
        super(minecraft);
    }

    @Override
    public void printChatMessage(IChatComponent chatComponent) {
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(chatComponent.getUnformattedText());

        if (isWork(message)) {
            if (message.contains(" seconds to clean up after the last wave.")) {
                String time = message.replaceAll("[^0-9.]", "");
                Utils.addChat("§e마지막 웨이브 이후 넘어가는데 §c" + time.substring(0, time.length() - 1) +"§e초가 걸렸습니다.");
                return;
            }

            if (message.contains("You completed Round ")) {
                Utils.addChat("                  §c라운드 " + message.split("in")[0].replaceAll("[^0-9]", "") + "§e을(를) §a" + message.split("in")[1].replaceAll("[^0-9:]", "") +"§e에 완료했습니다!");
                return;
            }
        }

        super.printChatMessage(chatComponent);
    }

    private boolean isWork(String str) {
        if (!ZombiesAddonConfig.enableMod) return false;

        if (!ZombiesAddonConfig.sstPatch || Utils.getLang() != 1) return false;

        if (!Utils.isZombies()) return false;

        return !str.contains("<");
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) throws IllegalAccessException {
        if (Minecraft.getMinecraft().ingameGUI.getChatGUI() instanceof SSTPatchListener) {
            return;
        }

        ReflectionHelper.findField(GuiIngame.class, new String[] { "persistantChatGUI", "field_73840_e" }).set(Minecraft.getMinecraft().ingameGUI, new SSTPatchListener(Minecraft.getMinecraft()));
    }
}