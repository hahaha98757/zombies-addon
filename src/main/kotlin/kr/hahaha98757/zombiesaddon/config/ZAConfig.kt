package kr.hahaha98757.zombiesaddon.config

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.utils.HudUtils
import net.minecraftforge.common.config.ConfigElement
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.common.config.Property
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement
import net.minecraftforge.fml.client.config.IConfigElement
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ZAConfig(val config: Configuration) {
    private val categoryGeneral = Category("General")
    private val categoryModules = Category("Modules")
    private val categoryPv = Category("Player Visibility")
    private val categoryBlockAlarm = Category("Block Alarm")
    private val categoryAutoSplits = Category("Auto Splits")
    private val categoryWaveDelays = Category("Wave Delays")
    private val categorySla = Category("SLA")
    private val categoryAutoRejoin = Category("Auto Rejoin")
    private val categoryLastWeapons = Category("Last Weapons")
    private val categoryKoreanPatchers = Category("Korean Patchers")
    private val categoryOtherMods = Category("Other Mods")
    private val categoryHidden = Category("Hidden")

    var enableMod = true
    var language = "Auto"

    var toggleNotLast = false
    var toggleInternalTimer = true
    var togglePowerupPatterns = true
    var textMacro = "T"

    var pvDefault = true
    var pvText = true
    var pvRange = 2.5
    var pvToggleSemiPv = true
    var pvSemiPvRange = 5.0

    var blockAlarmDefault = false
    var blockAlarmText = false

    var autoSplitsToggle = true
    var autoSplitsHost = "localhost"
    var autoSplitsPort = 16384

    var waveDelaysToggle = true
    var waveDelaysPlaySounds = intArrayOf(-60, -40, -20)
    var waveDelaysCustomPlaySound = true
    var waveDelaysPrefix = true
    var waveDelaysBossColor = true
    var waveDelaysTextStyle = "W1: 0:10.0"
        get() = if (field != "W1 0:10.0" && field != "W1: 00:10" && field != "W1 00:10") "W1: 0:10.0" else field
    var waveDelaysHighlightStyle = "Zombies Addon"
        get() = if (field != "Zombies Utils") "Zombies Addon" else field
    var waveDelaysHidePassedWave = false
    var waveDelaysRlModeOffset = -28
    
    var slaAutoSla = true
    var slaTextColor = true
    var slaActivatedWindows = true
    var slaUnactivatedWindows = false

    var autoRejoinDefault = false
    var autoRejoinText = false

    var lwToggle = true
    var lwDisplayArmors = true
    var lwDisplayWeaponsLevel = true
    var lwDisplayCooledDownSkill = true

    var koreanPatchersIngame = false
    var koreanPatchersZombiesOverlay = false
    var koreanPatchersSst = false
//    var koreanPatchersZombiesUtils = false

    var disableSpawnTimeOfSst = true
    var disableTimerOfZombiesUtils = true

    var blockUnlegitMods = true
    var blockUnlegitSst = true

    init {
        loadConfig()
    }

    private fun loadConfig() {
        config.load()

        enableMod = addOption(categoryGeneral.list, "zombiesaddon.config.enableMod", config.get(categoryGeneral.name, "enableMod", true, "zombiesaddon.config.enableMod.description")).boolean
        language = addOption(categoryGeneral.list, "zombiesaddon.config.language", config.get(categoryGeneral.name, "language", "Auto", "zombiesaddon.config.language.description", arrayOf("Auto", "English (US)", "한국어 (한국)"))).string

        toggleNotLast = addOption(categoryModules.list, "zombiesaddon.config.toggleNotLast", config.get(categoryModules.name, "toggleNotLast", false, "zombiesaddon.config.toggleNotLast.description")).boolean
        toggleInternalTimer = addOption(categoryModules.list, "zombiesaddon.config.toggleInternalTimer", config.get(categoryModules.name, "toggleInternalTimer", true, "zombiesaddon.config.toggleInternalTimer.description")).boolean
        togglePowerupPatterns = addOption(categoryModules.list, "zombiesaddon.config.togglePowerupPatterns", config.get(categoryModules.name, "togglePowerupPatterns", true, "zombiesaddon.config.togglePowerupPatterns.description")).boolean
        textMacro = addOption(categoryModules.list, "zombiesaddon.config.textMacro", config.get(categoryModules.name, "textMacro", "T", "zombiesaddon.config.textMacro.description")).string

        pvDefault = addOption(categoryPv.list, "zombiesaddon.config.pvDefault", config.get(categoryPv.name, "pvDefault", true, "zombiesaddon.config.pvDefault.description")).boolean
        pvText = addOption(categoryPv.list, "zombiesaddon.config.pvText", config.get(categoryPv.name, "pvText", true, "zombiesaddon.config.pvText.description")).boolean
        pvRange = addOption(categoryPv.list, "zombiesaddon.config.pvRange", config.get(categoryPv.name, "pvRange", 2.5, "zombiesaddon.config.pvRange.description", 0.0, 10.0)).double
        pvToggleSemiPv = addOption(categoryPv.list, "zombiesaddon.config.pvToggleSemiPv", config.get(categoryPv.name, "pvToggleSemiPv", true, "zombiesaddon.config.pvToggleSemiPv.description")).boolean
        pvSemiPvRange = addOption(categoryPv.list, "zombiesaddon.config.pvSemiPvRange", config.get(categoryPv.name, "pvSemiPvRange", 5.0, "zombiesaddon.config.pvSemiPvRange.description", 0.0, 5.0)).double

        blockAlarmDefault = addOption(categoryBlockAlarm.list, "zombiesaddon.config.defaultBlockAlarm", config.get(categoryBlockAlarm.name, "blockAlarmDefault", false, "zombiesaddon.config.defaultBlockAlarm.description")).boolean
        blockAlarmText = addOption(categoryBlockAlarm.list, "zombiesaddon.config.blockAlarmText", config.get(categoryBlockAlarm.name, "blockAlarmText", false, "zombiesaddon.config.blockAlarmText.description")).boolean

        autoSplitsToggle = addOption(categoryAutoSplits.list, "zombiesaddon.config.autoSplitsToggle", config.get(categoryAutoSplits.name, "autoSplitsToggle", true, "zombiesaddon.config.autoSplitsToggle.description")).boolean
        autoSplitsHost = addOption(categoryAutoSplits.list, "zombiesaddon.config.autoSplitsHost", config.get(categoryAutoSplits.name, "autoSplitsHost", "127.0.0.1", "zombiesaddon.config.autoSplitsHost.description")).string
        autoSplitsPort = addOption(categoryAutoSplits.list, "zombiesaddon.config.autoSplitsPort", config.get(categoryAutoSplits.name, "autoSplitsPort", 16834, "zombiesaddon.config.autoSplitsPort.description", 0, 65535)).int

        waveDelaysToggle = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysToggle", config.get(categoryWaveDelays.name, "waveDelaysToggle", true, "zombiesaddon.config.waveDelaysToggle.description")).boolean
        waveDelaysPlaySounds = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysPlaySounds", config.get(categoryWaveDelays.name, "waveDelaysPlaySounds", intArrayOf(-60, -40, -20), "zombiesaddon.config.waveDelaysPlaySounds.description", -200, 200, false, 5)).intList
        waveDelaysCustomPlaySound = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysCustomPlaySound", config.get(categoryWaveDelays.name, "waveDelaysCustomPlaySound", true, "zombiesaddon.config.waveDelaysCustomPlaySound.description")).boolean
        waveDelaysPrefix = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysPrefix", config.get(categoryWaveDelays.name, "waveDelaysPrefix", true, "zombiesaddon.config.waveDelaysPrefix.description")).boolean
        waveDelaysBossColor = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysBossColor", config.get(categoryWaveDelays.name, "waveDelaysBossColor", true, "zombiesaddon.config.waveDelaysBossColor.description")).boolean
        waveDelaysTextStyle = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysTextStyle", config.get(categoryWaveDelays.name, "waveDelaysTextStyle", "W1: 0:10.0", "zombiesaddon.config.waveDelaysTextStyle.description", arrayOf("W1: 0:10.0", "W1 0:10.0", "W1: 00:10", "W1 00:10"))).string
        waveDelaysHighlightStyle = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysHighlightStyle", config.get(categoryWaveDelays.name, "waveDelaysHighlightStyle", "Zombies Addon", "zombiesaddon.config.waveDelaysHighlightStyle.description", arrayOf("Zombies Addon", "Zombies Utils"))).string
        waveDelaysHidePassedWave = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysHidePassedWave", config.get(categoryWaveDelays.name, "waveDelaysHidePassedWave", false, "zombiesaddon.config.waveDelaysHidePassedWave.description")).boolean
        waveDelaysRlModeOffset = addOption(categoryWaveDelays.list, "zombiesaddon.config.waveDelaysRlModeOffset", config.get(categoryWaveDelays.name, "waveDelaysRlModeOffset", -28, "zombiesaddon.config.waveDelaysRlModeOffset.description", -1000, 1000)).int

        slaAutoSla = addOption(categorySla.list, "zombiesaddon.config.slaAutoSla", config.get(categorySla.name, "slaAutoSla", true, "zombiesaddon.config.slaAutoSla.description")).boolean
        slaTextColor = addOption(categorySla.list, "zombiesaddon.config.slaTextColor", config.get(categorySla.name, "slaTextColor", true, "zombiesaddon.config.slaTextColor.description")).boolean
        slaActivatedWindows = addOption(categorySla.list, "zombiesaddon.config.slaActivatedWindows", config.get(categorySla.name, "slaActivatedWindows", true, "zombiesaddon.config.slaActivatedWindows.description")).boolean
        slaUnactivatedWindows = addOption(categorySla.list, "zombiesaddon.config.slaUnactivatedWindows", config.get(categorySla.name, "slaUnactivatedWindows", false, "zombiesaddon.config.slaUnactivatedWindows.description")).boolean

        autoRejoinDefault = addOption(categoryAutoRejoin.list, "zombiesaddon.config.defaultAutoRejoin", config.get(categoryAutoRejoin.name, "autoRejoinDefault", false, "zombiesaddon.config.defaultAutoRejoin.description")).boolean
        autoRejoinText = addOption(categoryAutoRejoin.list, "zombiesaddon.config.autoRejoinText", config.get(categoryAutoRejoin.name, "autoRejoinText", false, "zombiesaddon.config.autoRejoinText.description")).boolean

        lwToggle = addOption(categoryLastWeapons.list, "zombiesaddon.config.lwToggle", config.get(categoryLastWeapons.name, "lwToggle", true, "zombiesaddon.config.lwToggle.description")).boolean
        lwDisplayArmors = addOption(categoryLastWeapons.list, "zombiesaddon.config.lwDisplayArmors", config.get(categoryLastWeapons.name, "lwDisplayArmors", true, "zombiesaddon.config.lwDisplayArmors.description")).boolean
        lwDisplayWeaponsLevel = addOption(categoryLastWeapons.list, "zombiesaddon.config.lwDisplayWeaponsLevel", config.get(categoryLastWeapons.name, "lwDisplayWeaponsLevel", true, "zombiesaddon.config.lwDisplayWeaponsLevel.description")).boolean
        lwDisplayCooledDownSkill = addOption(categoryLastWeapons.list, "zombiesaddon.config.lwDisplayCooledDownSkill", config.get(categoryLastWeapons.name, "lwDisplayCooledDownSkill", true, "zombiesaddon.config.lwDisplayCooledDownSkill.description")).boolean

        koreanPatchersIngame = addOption(categoryKoreanPatchers.list, "zombiesaddon.config.koreanPatchersIngame", config.get(categoryKoreanPatchers.name, "koreanPatchersIngame", false, "zombiesaddon.config.koreanPatchersIngame.description")).boolean
        koreanPatchersZombiesOverlay = addOption(categoryKoreanPatchers.list, "zombiesaddon.config.koreanPatchersZombiesOverlay", config.get(categoryKoreanPatchers.name, "koreanPatchersZombiesOverlay", false, "zombiesaddon.config.koreanPatchersZombiesOverlay.description")).boolean
        koreanPatchersSst = addOption(categoryKoreanPatchers.list, "zombiesaddon.config.koreanPatchersSst", config.get(categoryKoreanPatchers.name, "koreanPatchersSst", false, "zombiesaddon.config.koreanPatchersSst.description")).boolean
//        koreanPatchersZombiesUtils = addOption(categoryKoreanPatchers.list, "zombiesaddon.config.koreanPatchersZombiesUtils", config.get(categoryKoreanPatchers.name, "koreanPatchersZombiesUtils", false, "zombiesaddon.config.koreanPatchersZombiesUtils.description")).boolean

        disableSpawnTimeOfSst = addOption(categoryOtherMods.list, "zombiesaddon.config.disableSpawnTimeOfSst", config.get(categoryOtherMods.name, "disableSpawnTimeOfSst", true, "zombiesaddon.config.disableSpawnTimeOfSst.description")).boolean
        disableTimerOfZombiesUtils = addOption(categoryOtherMods.list, "zombiesaddon.config.disableTimerOfZombiesUtils", config.get(categoryOtherMods.name, "disableTimerOfZombiesUtils", true, "zombiesaddon.config.disableTimerOfZombiesUtils.description")).boolean

        blockUnlegitMods = addOption(categoryHidden.list, "zombiesaddon.config.blockUnlegitMods", config.get(categoryHidden.name, "blockUnlegitMods", true, "언레짓 모드 차단하는거")).boolean
        blockUnlegitSst = addOption(categoryHidden.list, "zombiesaddon.config.blockUnlegitSst", config.get(categoryHidden.name, "blockUnlegitSst", true, "Sst의 언레짓 기능 차단하는거")).boolean

        HudUtils.autoSplitsX = config.get("HUD", "autoSplitsX", -1.0).double
        HudUtils.autoSplitsY = config.get("HUD", "autoSplitsY", -1.0).double
        HudUtils.waveDelaysX = config.get("HUD", "waveDelaysX", -1.0).double
        HudUtils.waveDelaysY = config.get("HUD", "waveDelaysY", -1.0).double
        HudUtils.powerupPatternsX = config.get("HUD", "powerupPatternsX", -1.0).double
        HudUtils.powerupPatternsY = config.get("HUD", "powerupPatternsY", -1.0).double
        HudUtils.modNameX = config.get("HUD", "modNameX", -1.0).double
        HudUtils.modNameY = config.get("HUD", "modNameY", -1.0).double
        HudUtils.toggleTextX = config.get("HUD", "toggleTextX", -1.0).double
        HudUtils.toggleTextY = config.get("HUD", "toggleTextY", -1.0).double
    }

    private fun addOption(category: MutableList<IConfigElement>, langKey: String, prop: Property): Property {
        prop.languageKey = langKey
        category.removeIf { it.name == prop.name }
        category += ConfigElement(prop)
        return prop
    }

    fun getElements() = listOf(
        DummyCategoryElement(categoryGeneral.name, "zombiesaddon.config.category.general", categoryGeneral.list),
        DummyCategoryElement(categoryModules.name, "zombiesaddon.config.category.modules", categoryModules.list),
        DummyCategoryElement(categoryPv.name, "zombiesaddon.config.category.pv", categoryPv.list),
        DummyCategoryElement(categoryBlockAlarm.name, "zombiesaddon.config.category.blockAlarm", categoryBlockAlarm.list),
        DummyCategoryElement(categoryAutoSplits.name, "zombiesaddon.config.category.autoSplits", categoryAutoSplits.list),
        DummyCategoryElement(categoryWaveDelays.name, "zombiesaddon.config.category.waveDelays", categoryWaveDelays.list),
        DummyCategoryElement(categoryAutoRejoin.name, "zombiesaddon.config.category.autoRejoin", categoryAutoRejoin.list),
        DummyCategoryElement(categorySla.name, "zombiesaddon.config.category.sla", categorySla.list),
        DummyCategoryElement(categoryLastWeapons.name, "zombiesaddon.config.category.lw", categoryLastWeapons.list),
        DummyCategoryElement(categoryKoreanPatchers.name, "zombiesaddon.config.category.koreanPatchers", categoryKoreanPatchers.list),
        DummyCategoryElement(categoryOtherMods.name, "zombiesaddon.config.category.otherMods", categoryOtherMods.list)
    )

    private fun save() {
        config.save()
        loadConfig()
        if (enableMod) println("Enable Mod is true")
        else println("Enable Mod is false")
    }

    @SubscribeEvent
    fun onConfigChanged(event: OnConfigChangedEvent) {
        if (event.modID == MODID) save()
    }
}

private data class Category(val name: String) {
    val list = mutableListOf<IConfigElement>()
}