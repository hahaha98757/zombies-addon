package kr.hahaha98757.zombiesaddon.mixins;

import com.seosean.showspawntime.config.MainConfiguration;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.utils.Tools;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.seosean.showspawntime.config.MainConfiguration.*;

@Mixin(value = MainConfiguration.class, remap = false)
public class MixinMainConfiguration {
    /**
     * @author hahaha98757
     * @reason 언레짓 기능 차단.
     */
    @Overwrite()
    public void ConfigLoad() {
        config.load();
        logger.info("Started loading config. ");

        final Property propertyXSpawnTime = config.get(Configuration.CATEGORY_CLIENT, "XSpawnTime", -1.0, "X");
        final Property propertyYSpawnTime = config.get(Configuration.CATEGORY_CLIENT, "YSpawnTime", -1.0, "Y");
        XSpawnTime = propertyXSpawnTime.getDouble();
        YSpawnTime = propertyYSpawnTime.getDouble();
        guiRelated.put("XSpawnTime", new ConfigElement(propertyXSpawnTime));
        guiRelated.put("YSpawnTime", new ConfigElement(propertyYSpawnTime));

        final Property propertyXPowerup = config.get(Configuration.CATEGORY_CLIENT, "XPowerup", -1.0, "X");
        final Property propertyYPowerup = config.get(Configuration.CATEGORY_CLIENT, "YPowerup", -1.0, "Y");
        XPowerup = propertyXPowerup.getDouble();
        YPowerup = propertyYPowerup.getDouble();
        guiRelated.put("XPowerup", new ConfigElement(propertyXPowerup));
        guiRelated.put("YPowerup", new ConfigElement(propertyYPowerup));

        final Property propertyXDPSCounter = config.get(Configuration.CATEGORY_CLIENT, "XDPSCounter", -1.0, "X");
        final Property propertyYDPSCounter = config.get(Configuration.CATEGORY_CLIENT, "YDPSCounter", -1.0, "Y");
        XDPSCounter = propertyXDPSCounter.getDouble();
        YDPSCounter = propertyYDPSCounter.getDouble();
        guiRelated.put("XDPSCounter", new ConfigElement(propertyXDPSCounter));
        guiRelated.put("YDPSCounter", new ConfigElement(propertyYDPSCounter));

        /*
           SST Related Elements
         */

        final String commentPlaySound = "Turn on/off the sound of wave spawning in AA.";
        final Property propertyPlaySound =  config.get(Configuration.CATEGORY_GENERAL, "Toggle AA Sound", true, commentPlaySound);
        PlayAASound = propertyPlaySound.getBoolean();
        sstRelated.put(propertyPlaySound.getName(), new ConfigElement(propertyPlaySound));

        final String commentDEBBPlaySound = "Turn on/off the sound of wave spawning in DE and BB.";
        final Property propertyPlayDEBBSound = config.get(Configuration.CATEGORY_GENERAL, "Toggle DE/BB Sound", true, commentDEBBPlaySound);
        PlayDEBBSound = propertyPlayDEBBSound.getBoolean();
        sstRelated.put(propertyPlayDEBBSound.getName(), new ConfigElement(propertyPlayDEBBSound));

        final String commentPlaySoundPrecededWave = "The sound will be played when a wave spawns except the last wave. \nYou can search the sounds you want at https://minecraft.fandom.com/wiki/Sounds.json/Java_Edition_values_before_1.9 \nChinese wiki: https://minecraft.fandom.com/zh/wiki/Sounds.json/Java%E7%89%881.9%E5%89%8D";
        final Property propertyPrecededWave = config.get(Configuration.CATEGORY_GENERAL, "Preceded Wave Sound", "note.pling", commentPlaySoundPrecededWave);
        PrecededWaveSound = propertyPrecededWave.getString();
        sstRelated.put(propertyPrecededWave.getName(), new ConfigElement(propertyPrecededWave));

        final String commentPlaySoundPrecededWavePitch = "The pitch setting of PrecededWave.";
        final Property propertyPrecededWavePitch = config.get(Configuration.CATEGORY_GENERAL, "Preceded Wave Pitch", 2.0, commentPlaySoundPrecededWavePitch, 0, 2);
        PrecededWavePitch = propertyPrecededWavePitch.getDouble();
        sstRelated.put(propertyPrecededWavePitch.getName(), new ConfigElement(propertyPrecededWavePitch));

        final String commentPlaySoundLastWave = "The sound will be played when the last wave spawns.";
        final Property propertyTheLastWave = config.get(Configuration.CATEGORY_GENERAL, "Final Wave Sound", "random.orb", commentPlaySoundLastWave);
        TheLastWaveSound = propertyTheLastWave.getString();
        sstRelated.put(propertyTheLastWave.getName(), new ConfigElement(propertyTheLastWave));

        final String commentPlaySoundLastWavePitch = "The pitch setting of TheLastWave.";
        final Property propertyTheLastWavePitch = config.get(Configuration.CATEGORY_GENERAL, "Final Wave Pitch", 0.5, commentPlaySoundLastWavePitch, 0, 2);
        TheLastWavePitch = propertyTheLastWavePitch.getDouble();
        sstRelated.put(propertyTheLastWavePitch.getName(), new ConfigElement(propertyTheLastWavePitch));

        final String commentDEBBCountDown = "Turn on/off the count-down sound of seconds before final wave spawns in DE and BB.";
        final Property propertyDEBBCountDown = config.get(Configuration.CATEGORY_GENERAL, "Toggle DE/BB W3 Count Down Sound", false, commentDEBBCountDown);
        DEBBCountDown = propertyDEBBCountDown.getBoolean();
        sstRelated.put(propertyDEBBCountDown.getName(), new ConfigElement(propertyDEBBCountDown));

        final String commentDEBBCountDownSound = "The sound of W3 Count Down";
        final Property propertyCountDownSound = config.get(Configuration.CATEGORY_GENERAL, "Count Down Sound", "note.pling", commentDEBBCountDownSound);
        CountDownSound = propertyCountDownSound.getString();
        sstRelated.put(propertyCountDownSound.getName(), new ConfigElement(propertyCountDownSound));

        final String commentDEBBCountDownPitch = "The sound pitch of W3 Count Down";
        final Property propertyCountDownPitch = config.get(Configuration.CATEGORY_GENERAL, "Count Down Pitch", 1.5, commentDEBBCountDownPitch, 0, 2);
        CountDownPitch = propertyCountDownPitch.getDouble();
        sstRelated.put(propertyCountDownPitch.getName(), new ConfigElement(propertyCountDownPitch));

        final String commentDangerAlert = "Turn on/off the color alert to The Old One and Giants. \nOnly works in AA.";
        final Property propertyColorAlert = config.get(Configuration.CATEGORY_GENERAL, "AA Boss Color Alert", true, commentDangerAlert);
        ColorAlert = propertyColorAlert.getBoolean();
        sstRelated.put(propertyColorAlert.getName(), new ConfigElement(propertyColorAlert));

        /*
           Record Related Elements
         */
        final String commentAAAllRoundsRecord = "Turn on/off the round timing similar to round 10/20/105 in AA.";
        final Property propertyAARoundsRecordToggle = config.get(Configuration.CATEGORY_GENERAL, "AA Rounds Record Timing", "ALL", commentAAAllRoundsRecord, AARoundsRecord);
        AARoundsRecordToggle = propertyAARoundsRecordToggle.getString();
        recordRelated.put(propertyAARoundsRecordToggle.getName(), new ConfigElement(propertyAARoundsRecordToggle));

        final String commentDEBBAllRoundsRecord = "Turn on/off the round timing similar to round 10/20/30 in DE/BB.";
        final Property propertyDEBBRoundsRecordToggle = config.get(Configuration.CATEGORY_GENERAL, "DE/BB Rounds Record Timing", "ALL", commentDEBBAllRoundsRecord, DEBBRoundsRecord);
        DEBBRoundsRecordToggle = propertyDEBBRoundsRecordToggle.getString();
        recordRelated.put(propertyDEBBRoundsRecordToggle.getName(), new ConfigElement(propertyDEBBRoundsRecordToggle));

        final String commentCleanUpTimeTook = "Turn on/off the tip of time took to clean up.";
        final Property propertyCleanUpTimeToggle = config.get(Configuration.CATEGORY_GENERAL, "Clean Up Time Tips", true, commentCleanUpTimeTook);
        CleanUpTimeToggle = propertyCleanUpTimeToggle.getBoolean();
        recordRelated.put(propertyCleanUpTimeToggle.getName(), new ConfigElement(propertyCleanUpTimeToggle));

        /*
           Powerup Related Elements
         */

        final String commentPowerupAlert = "Remind you when this is a powerup-round. Start counting down when a powerup spawns";
        final Property propertyPowerupAlertToggle = config.get(Configuration.CATEGORY_GENERAL, "Powerup Alert", true, commentPowerupAlert);
        try {
            if (ZombiesAddon.getInstance().getConfig().getBlockUnlegitSst()) propertyPowerupAlertToggle.set(false);
        } catch (Throwable ignored) {}
        PowerupAlertToggle = propertyPowerupAlertToggle.getBoolean();
        powerupRelated.put(propertyPowerupAlertToggle.getName(), new ConfigElement(propertyPowerupAlertToggle));

        final String commentPowerupPredict = "Notice you when the next powerup is at the beginning of round";
        final Property propertyPowerupPredictToggle = config.get(Configuration.CATEGORY_GENERAL, "Powerup Predict", true, commentPowerupPredict);
        PowerupPredictToggle = propertyPowerupPredictToggle.getBoolean();
        powerupRelated.put(propertyPowerupPredictToggle.getName(), new ConfigElement(propertyPowerupPredictToggle));

        final String commentPowerupCountDown = "Show the remaining time of powerups on expiration.";
        final Property propertyPowerupCountDown = config.get(Configuration.CATEGORY_GENERAL, "Powerup Count Down", true, commentPowerupCountDown);
        try {
            if (ZombiesAddon.getInstance().getConfig().getBlockUnlegitSst()) propertyPowerupCountDown.set(false);
        } catch (Throwable ignored) {}
        PowerupCountDown = propertyPowerupCountDown.getBoolean();
        powerupRelated.put(propertyPowerupCountDown.getName(), new ConfigElement(propertyPowerupCountDown));

        final String commentPowerupNameTagShadow = "Render shadow for nametags of powerups.";
        final Property propertyPowerupNameTagShadow = config.get(Configuration.CATEGORY_GENERAL, "Powerup NameTag Shadow", false, commentPowerupNameTagShadow);
        PowerupNameTagShadow = propertyPowerupNameTagShadow.getBoolean();
        powerupRelated.put(propertyPowerupNameTagShadow.getName(), new ConfigElement(propertyPowerupNameTagShadow));

        /*
           QoL Related Elements
         */
        final String commentLightningRodHelper = "Turn on/off the helper of lightning rod queue in AA.";
        final Property propertyLightningRodQueue = config.get(Configuration.CATEGORY_GENERAL, "LR Queue Helper", true, commentLightningRodHelper);
        LightningRodQueue = propertyLightningRodQueue.getBoolean();
        qolRelated.put(propertyLightningRodQueue.getName(), new ConfigElement(propertyLightningRodQueue));

        final String commentWave3LeftNotice = "Enhance the Sidebar which shows you the amount of zombies in wave 3rd in DE/BB.";
        final Property propertyWave3LeftNotice = config.get(Configuration.CATEGORY_GENERAL, "Wave 3rd Left Notice", true, commentWave3LeftNotice);
        Wave3LeftNotice = propertyWave3LeftNotice.getBoolean();
        qolRelated.put(propertyWave3LeftNotice.getName(), new ConfigElement(propertyWave3LeftNotice));

        final String commentPlayerHealthNotice = "Enhance the Sidebar which shows the health of players.";
        final Property propertyPlayerHealthNotice = config.get(Configuration.CATEGORY_GENERAL, "Player Health Notice", true, commentPlayerHealthNotice);
        try {
            if (ZombiesAddon.getInstance().getConfig().getBlockUnlegitSst()) propertyPlayerHealthNotice.set(false);
        } catch (Throwable ignored) {}
        PlayerHealthNotice = propertyPlayerHealthNotice.getBoolean();
        qolRelated.put(propertyPlayerHealthNotice.getName(), new ConfigElement(propertyPlayerHealthNotice));

        final String commentFastReviveCoolDown = "Enhance the Sidebar which shows the cool down of fast revive for each player.";
        final Property propertyFastReviveCoolDown = config.get(Configuration.CATEGORY_GENERAL, "Fast Revive Cool Down", "BEHIND", commentFastReviveCoolDown, FastReviveCoolDownRenderType);
        try {
            if (ZombiesAddon.getInstance().getConfig().getBlockUnlegitSst()) propertyFastReviveCoolDown.set("OFF");
        } catch (Throwable ignored) {}
        FastReviveCoolDown = com.seosean.showspawntime.features.frcooldown.FastReviveCoolDown.RenderType.valueOf(propertyFastReviveCoolDown.getString());
        qolRelated.put(propertyFastReviveCoolDown.getName(), new ConfigElement(propertyFastReviveCoolDown));

        final String commentDPSCounterToggle = "Display your own damage per second on your screen.";
        final Property propertyDPSCounterToggle = config.get(Configuration.CATEGORY_GENERAL, "Individual DPS Counter", true, commentDPSCounterToggle);
        DPSCounterToggle = propertyDPSCounterToggle.getBoolean();
        qolRelated.put(propertyDPSCounterToggle.getName(), new ConfigElement(propertyDPSCounterToggle));

        final String commentCriticalTextFix = "Fix a bug which caused texts after full angle bracket do not render.";
        final Property propertyCriticalTextFix = config.get(Configuration.CATEGORY_GENERAL, "Critical Hit Text Fix" + EnumChatFormatting.WHITE + "(" + EnumChatFormatting.RED + "Reload Resources" + EnumChatFormatting.WHITE + ")", true, commentCriticalTextFix);
        CriticalTextFix = propertyCriticalTextFix.getBoolean();
        qolRelated.put(propertyCriticalTextFix.getName(), new ConfigElement(propertyCriticalTextFix));

        config.save();
        logger.info("Finished loading config. ");
        logger.info("The unlegit features of SST have been blocked by Zombies Addon.");
        try {
            Tools.addTranslationChat("zombiesaddon.messages.blockUnlegitSST");
        } catch (Throwable ignored) {}
    }
}