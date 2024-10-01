package kr.hahaha98757.zombiesaddon.features;

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
        MinecraftForge.EVENT_BUS.register(new SSTPatcher(Minecraft.getMinecraft()));
    }

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
        if (!ZombiesAddonConfig.isShowMods()) return;

        byte y = (byte) fr.FONT_HEIGHT;

        String str = "Zombies Addon v" + ZombiesAddon.VERSION;
        fr.drawStringWithShadow(str, Utils.getX(str), 1, 0xffff55);

        str = "Player Visibility: " + (PlayerVisibility.playerVisibility ? "§aon" : "§coff");
        fr.drawStringWithShadow(str, Utils.getX(str), 1 + y, 0xffff55);

        str = "Block Alarm: " + (BlockAlarm.blockAlarm ? "§aon" : "§coff");
        fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*2, 0xffff55);

        if (ZombiesAddonConfig.isHideAutoRejoin()) return;
        str = "Auto Rejoin: " + (AutoRejoin.autoRejoin ? "§aon" : "§coff");
        fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*3, 0xffff55);
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
            Utils.addChatLine("You are using Show Spawn Time of old version.\nPlease update the mod.");

        if (ZombiesAddon.hasSST) Utils.addChatLine("Blocked the unlegit features of SST by Zombies Addon.");

        if (ZombiesAddon.haveUnlegitMods) {
            Utils.addChatLine("§cYou are using ZombiesSatellite, Zombies Explorer, or TeammatesOutline.\n§cThey are unlegit mods. Please remove them.\n§c§lThe game ends after 10 seconds.");
            ClientCrash.setUnlegit();
            MinecraftForge.EVENT_BUS.register(new ClientCrash());
        }

        join = true;
    }
}