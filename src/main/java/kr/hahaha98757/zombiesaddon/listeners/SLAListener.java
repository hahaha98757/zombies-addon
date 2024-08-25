// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.Room;
import kr.hahaha98757.zombiesaddon.data.Window;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Optional;

public class SLAListener {
    private static Room[] rooms;
    private static short tick;
    private static boolean auto;

    public static void setSLA(String map) {
        switch (map) {
            case "de":
                rooms = Room.getDE();
                break;
            case "bb":
                rooms = Room.getBB();
                break;
            case "aa":
                rooms = Room.getAA();
                break;
            case "pr":
                rooms = Room.getPR();
                break;
            case "off":
                rooms = null;
                break;
            default: Utils.addChat("§eSLA: §cWrong map.");
        }
    }

    public void refreshActives() {
        final EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        final double[] playerCoords = { (player.posX), player.posY, player.posZ };
        for (Room room : rooms) {
            room.resetActiveWindowCount();
            for (Window window : room.getWindows()) {
                double distanceDoubledThenSquared = 0;
                for (int i = 0; i < 3; i++) {
                    distanceDoubledThenSquared += ((playerCoords[i] * 2 - window.getXYZ()[i])
                            * (playerCoords[i] * 2 - window.getXYZ()[i]));
                }

                if (Utils.getMap() == 4) {
                    if (Utils.getArea().equals(room.getName().replaceAll("[^A-Za-z]", ""))) {
                        window.setActive(true);
                        room.increaseActiveWindowCount();
                    } else {
                        window.setActive(false);
                    }
                } else {
                    if (distanceDoubledThenSquared < 10000) {
                        window.setActive(true);
                        room.increaseActiveWindowCount();
                    } else {
                        window.setActive(false);
                    }
                }
            }
        }
    }

    private static Optional<SLAListener> getInstance() {
        return Optional.of(new SLAListener());
    }

    private Room[] getRooms() {
        return rooms;
    }


    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

        if (rooms == null) return;

        SLAListener.getInstance().ifPresent(sla -> {
            sla.refreshActives();
            renderSla(sla.getRooms());
        });
    }

    private void renderSla(Room[] rooms) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        int y = 0;
        for (Room room : rooms) {
            if (!ZombiesAddonConfig.showInactiveWindows && room.getActiveWindowCount() == 0) continue;
            fr.drawStringWithShadow(room.getSlaString(), 1, 1 + y * fr.FONT_HEIGHT, 0xFFFFFF);
            y++;
        }
    }

    @SubscribeEvent
    public void autoSLA(TitleEvent event) {
        if (!ZombiesAddonConfig.autoSLA) return;

        String str = EnumChatFormatting.getTextWithoutFormattingCodes(event.getTitle());

        if (!(str.equals("Round 1") || str.equals("1라운드"))) return;

        auto = true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!auto) return;

        if (event.phase != TickEvent.Phase.END) return;

        tick++;

        if (tick == 200) {
            tick = 0;
            auto = false;
            Utils.addChat("§eAuto SLA: §cWrong map.");
            return;
        }

        String map;

        switch (Utils.getMap()) {
            case 1:
                map = "de";
                break;
            case 2:
                map = "bb";
                break;
            case 3:
                map = "aa";
                break;
            case 4:
                map = "pr";
                break;
            default:
                return;
        }

        Utils.addChat("§eAuto SLA: Set SLA to §a" + map);
        SLAListener.setSLA(map);
    }
}