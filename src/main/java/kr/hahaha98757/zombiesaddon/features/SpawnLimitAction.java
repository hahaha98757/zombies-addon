//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.spawnlimitaction.Room;
import kr.hahaha98757.zombiesaddon.data.spawnlimitaction.Window;
import kr.hahaha98757.zombiesaddon.enums.Map;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;

public class SpawnLimitAction {
    private static final int[] offset = new int[3];
    private static Room[] rooms;

    public static void setMap(Map map) {
        switch (map) {
            case DEAD_END:
                Utils.addChat("§eSLA: Set SLA to §aDead End");
                rooms = Room.getDE();
                break;
            case BAD_BLOOD:
                Utils.addChat("§eSLA: Set SLA to §aBad Blood");
                rooms = Room.getBB();
                break;
            case ALIEN_ARCADIUM:
                Utils.addChat("§eSLA: Set SLA to §aAlien Arcadium");
                rooms = Room.getAA();
                break;
            default:
                Utils.addChat("§eSLA: §cFailed to set map");
        }
    }

    public static void offMap() {
        Utils.addChat("§eSLA: Set SLA to §coff");
        rooms = null;
        resetOffset();
    }

    public static void rotate(int rotations) {
        Utils.addChat("§eSLA: Rotates all windows around the axis §a(0,y,0)");
        for (Room room : rooms) {
            for (Window window : room.getWindows()) {
                window.rotate(rotations);
            }
        }
    }

    public static void mirrorX() {
        Utils.addChat("§eSLA: Mirrors all windows along the plane §a(0,y,z)");
        for (Room room : rooms) {
            for (Window window : room.getWindows()) {
                window.mirrorX();
            }
        }
    }

    public static void mirrorZ() {
        Utils.addChat("§eSLA: Mirrors all windows along the plane §a(x,y,0)");
        for (Room room : rooms) {
            for (Window window : room.getWindows()) {
                window.mirrorZ();
            }
        }
    }

    private static void refreshActives() {
        final EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        final double[] playerCoords = {
                (player.posX - offset[0]),
                player.posY - offset[1],
                player.posZ - offset[2]
        };
        for (Room room : rooms) {
            room.resetActiveWindowCount();
            for (Window window : room.getWindows()
            ) {
                double distanceDoubledThenSquared = 0;
                for (int i = 0; i < 3; i++) {
                    distanceDoubledThenSquared += ((playerCoords[i] * 2 - window.getXYZ()[i]) * (playerCoords[i] * 2 - window.getXYZ()[i]));
                }

                // (2x)²+(2y)²+(2z)² = 4(x²+y²+z²) = 4d²
                final int slaRange = 50;
                if (distanceDoubledThenSquared < 4 * slaRange * slaRange) {
                    window.setActive(true);
                    room.increaseActiveWindowCount();
                } else window.setActive(false);
            }
        }
    }

    public static Room[] getRooms() {
        refreshActives();
        return rooms;
    }

    public static void resetOffset() {
        Arrays.fill(offset, 0);
    }

    public static void setOffset(int[] offset_) {
        Utils.addChat("§eSLA: Set offset to §a" + offset_[0] + " " + offset_[1] + " " + offset_[2]);
        System.arraycopy(offset_, 0, offset, 0, 3);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

        try {
            Room[] rooms = getRooms();

            FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
            int y = 0;
            for (Room room : rooms) {
                if (!ZombiesAddonConfig.isShowInactiveWindows() && room.getActiveWindowCount() == 0) continue;
                fr.drawStringWithShadow(room.getSLAString(), 1, 1 + y * fr.FONT_HEIGHT, 0xFFFFFF);
                y++;
            }
        } catch (Exception ignored) {
        }
    }

    private static boolean autoSLA;
    private static byte tick;

    @SubscribeEvent
    public void onTitle(TitleEvent event) {
        if (Utils.isModDisable()) return;
        if (!ZombiesAddonConfig.isAutoSLA()) return;

        String title = event.getTitle();

        if (!(Utils.isRoundText(title) && title.replaceAll("[^0-9]", "").equals("1"))) return;

        tick = 0;
        autoSLA = true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        if (!autoSLA) return;

        Map map = Utils.getMap();

        if (map == null) {
            tick++;
            return;
        }

        switch (map) {
            case DEAD_END:
                SpawnLimitAction.setMap(Map.DEAD_END);
                autoSLA = false;
                tick = 0;
                return;
            case BAD_BLOOD:
                SpawnLimitAction.setMap(Map.BAD_BLOOD);
                autoSLA = false;
                tick = 0;
                return;
            case ALIEN_ARCADIUM:
                SpawnLimitAction.setMap(Map.ALIEN_ARCADIUM);
                autoSLA = false;
                tick = 0;
                return;
        }

        if (tick == 100) {
            Utils.addChat("§eSLA: §cWrong map");
            autoSLA = false;
            tick = 0;
            return;
        }

        tick++;
    }
}