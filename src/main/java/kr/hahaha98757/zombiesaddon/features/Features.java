package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.ClientCrash;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Features {
    public static void registerAll() {
        MinecraftForge.EVENT_BUS.register(new Features());
        MinecraftForge.EVENT_BUS.register(new PlayerVisibility());
        MinecraftForge.EVENT_BUS.register(new BlockAlarm());
        MinecraftForge.EVENT_BUS.register(new NotLast());
        MinecraftForge.EVENT_BUS.register(new AutoSplits());
        MinecraftForge.EVENT_BUS.register(new WaveDelays());
        MinecraftForge.EVENT_BUS.register(new ZombiesStratViewer());
        MinecraftForge.EVENT_BUS.register(new SpawnLimitAction());
        MinecraftForge.EVENT_BUS.register(new AutoRejoin());
        MinecraftForge.EVENT_BUS.register(new PowerupPatterns());
        MinecraftForge.EVENT_BUS.register(new ManualTimer());
        MinecraftForge.EVENT_BUS.register(new LastWeapons());
        MinecraftForge.EVENT_BUS.register(new TextMacro());
        MinecraftForge.EVENT_BUS.register(new ZombiesOverlayPatcher());
        MinecraftForge.EVENT_BUS.register(new KoreanPatcher());
    }
    
    private static boolean gameEnd;

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        if (ClientCrash.update) {
            String str = "Zombies Addon update required!";
            fr.drawStringWithShadow(str, Utils.getX(str), 0, 0xff5555);
            return;
        }
        if (ClientCrash.unlegit) {
            String str = "Detected Unlegit Mods!";
            fr.drawStringWithShadow(str, Utils.getX(str), 0, 0xff5555);
            return;
        }

        if (Utils.isModDisable()) return;
        byte y = (byte) fr.FONT_HEIGHT;
        String str;

        if (ZombiesAddonConfig.isShowMods()) {
            str = "Zombies Addon v" + ZombiesAddon.VERSION;
            fr.drawStringWithShadow(str, Utils.getX(str), 1, 0xffff55);

            str = "Player Visibility: " + (PlayerVisibility.playerVisibility ? "§aon" : "§coff");
            fr.drawStringWithShadow(str, Utils.getX(str), 1 + y, 0xffff55);

            str = "Block Alarm: " + (BlockAlarm.blockAlarm ? "§aon" : "§coff");
            fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*2, 0xffff55);

            if (!ZombiesAddonConfig.isHideAutoRejoin()) {
                str = "Auto Rejoin: " + (AutoRejoin.autoRejoin ? "§aon" : "§coff");
                fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*3, 0xffff55);
            }
        }

        if (!gameEnd) return;

        if (!ZombiesAddonConfig.isDetectUnlegitMods()) {
            str = "Detect Unlegit Mods is disabled.";
            fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*4, 0xff5555);
        }

        if (!ZombiesAddonConfig.isBlockUnlegitSST()) {
            str = "Block Unlegit SST is disabled.";
            fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*5, 0xff5555);
        }
        
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(ZombiesAddon.MODID)) ZombiesAddonConfig.reloadConfig();
    }

    private static boolean join;

    @SubscribeEvent
    public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity != Minecraft.getMinecraft().thePlayer) return;

        if (join) return;

        if (ZombiesAddon.hasOldSST)
            Utils.addChatWithLine("You are using Show Spawn Time of old version.\nPlease update the mod.");

        if (ZombiesAddon.hasSST) Utils.addChatWithLine("Blocked the unlegit features of SST by Zombies Addon.");

        if (ZombiesAddon.haveUnlegitMods) {
            Utils.addChatWithLine("§cYou are using ZombiesSatellite, Zombies Explorer, TeammatesOutline, or ZombiesHelper.\n§cThey are unlegit mods. Please remove them.\n§c§lThe game ends after 10 seconds.");
            ClientCrash.setUnlegit();
            MinecraftForge.EVENT_BUS.register(new ClientCrash());
        }

        join = true;
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isNotPlayZombies()) {
            gameEnd = false;
            return;
        }
        if (!event.getSoundName().equals("mob.enderdragon.end")) return;
        gameEnd = true;

        System.out.println(Utils.LINE);
        System.out.println(ZombiesAddon.NAME + " v" + ZombiesAddon.VERSION + " is being used.");
        if (!ZombiesAddonConfig.isDetectUnlegitMods()) System.out.println("Detect Unlegit Mods is disabled.");
        if (!ZombiesAddonConfig.isBlockUnlegitSST()) System.out.println("Block Unlegit SST is disabled.");
        System.out.println(Utils.LINE);

    }
}