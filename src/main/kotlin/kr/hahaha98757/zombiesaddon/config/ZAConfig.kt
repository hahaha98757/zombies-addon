package kr.hahaha98757.zombiesaddon.config

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.enums.HighlightStyle
import kr.hahaha98757.zombiesaddon.enums.Language
import kr.hahaha98757.zombiesaddon.enums.TextStyle
import kr.hahaha98757.zombiesaddon.utils.HudUtils
import kr.hahaha98757.zombiesaddon.utils.logger
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
    var language = Language.AUTO

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
    var autoSplitsHost = "127.0.0.1"
    var autoSplitsPort = 16384

    var waveDelaysToggle = true
    var waveDelaysPlaySounds = intArrayOf(-60, -40, -20)
    var waveDelaysCustomPlaySound = true
    var waveDelaysPrefix = true
    var waveDelaysBossColor = true
    var waveDelaysTextStyle = TextStyle.NEW_COLON
    var waveDelaysHighlightStyle = HighlightStyle.ZOMBIES_ADDON
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
        logger.info("컨피그 로딩 시작...")

        // 일반
        val enableModKey = "zombiesaddon.config.enableMod"
        enableMod = addOption(categoryGeneral.list, enableModKey, config.get(
            categoryGeneral.name,
            "enableMod",
            true,
            "$enableModKey.description"
        )).boolean

        val languageKey = "zombiesaddon.config.language"
        language = Language.fromText(addOption(categoryGeneral.list, languageKey, config.get(
            categoryGeneral.name,
            "language",
            Language.AUTO.toString(),
            "$languageKey.description",
            Language.arrays
        )).string)


        // 모듈
        val toggleNotLastKey = "zombiesaddon.config.toggleNotLast"
        toggleNotLast = addOption(categoryModules.list, toggleNotLastKey, config.get(
            categoryModules.name,
            "toggleNotLast",
            false,
            "$toggleNotLastKey.description"
        )).boolean

        val toggleInternalTimerKey = "zombiesaddon.config.toggleInternalTimer"
        toggleInternalTimer = addOption(categoryModules.list, toggleInternalTimerKey, config.get(
            categoryModules.name,
            "toggleInternalTimer",
            true,
            "$toggleInternalTimerKey.description"
        )).boolean

        val togglePowerupPatternsKey = "zombiesaddon.config.togglePowerupPatterns"
        togglePowerupPatterns = addOption(categoryModules.list, togglePowerupPatternsKey, config.get(
            categoryModules.name,
            "togglePowerupPatterns",
            true,
            "$togglePowerupPatternsKey.description"
        )).boolean

        val textMacroKey = "zombiesaddon.config.textMacro"
        textMacro = addOption(categoryModules.list, textMacroKey, config.get(
            categoryModules.name,
            "textMacro",
            "T",
            "$textMacroKey.description"
        )).string


        // Player Visibility
        val pvDefaultKey = "zombiesaddon.config.pvDefault"
        pvDefault = addOption(categoryPv.list, pvDefaultKey, config.get(
            categoryPv.name,
            "pvDefault",
            true,
            "$pvDefaultKey.description"
        )).boolean

        val pvTextKey = "zombiesaddon.config.pvText"
        pvText = addOption(categoryPv.list, pvTextKey, config.get(
            categoryPv.name,
            "pvText",
            true,
            "$pvTextKey.description"
        )).boolean

        val pvRangeKey = "zombiesaddon.config.pvRange"
        pvRange = addOption(categoryPv.list, pvRangeKey, config.get(
            categoryPv.name,
            "pvRange",
            2.5,
            "$pvRangeKey.description",
            0.0, 10.0
        )).double

        val pvToggleSemiPvKey = "zombiesaddon.config.pvToggleSemiPv"
        pvToggleSemiPv = addOption(categoryPv.list, pvToggleSemiPvKey, config.get(
            categoryPv.name,
            "pvToggleSemiPv",
            true,
            "$pvToggleSemiPvKey.description"
        )).boolean

        val pvSemiPvRangeKey = "zombiesaddon.config.pvSemiPvRange"
        pvSemiPvRange = addOption(categoryPv.list, pvSemiPvRangeKey, config.get(
            categoryPv.name,
            "pvSemiPvRange",
            5.0,
            "$pvSemiPvRangeKey.description",
            0.0, 5.0
        )).double


        // Block Alarm
        val blockAlarmDefaultKey = "zombiesaddon.config.defaultBlockAlarm"
        blockAlarmDefault = addOption(categoryBlockAlarm.list, blockAlarmDefaultKey, config.get(
            categoryBlockAlarm.name,
            "blockAlarmDefault",
            false,
            "$blockAlarmDefaultKey.description"
        )).boolean

        val blockAlarmTextKey = "zombiesaddon.config.blockAlarmText"
        blockAlarmText = addOption(categoryBlockAlarm.list, blockAlarmTextKey, config.get(
            categoryBlockAlarm.name,
            "blockAlarmText",
            false,
            "$blockAlarmTextKey.description"
        )).boolean


        // Auto Splits
        val autoSplitsToggleKey = "zombiesaddon.config.autoSplitsToggle"
        autoSplitsToggle = addOption(categoryAutoSplits.list, autoSplitsToggleKey, config.get(
            categoryAutoSplits.name,
            "autoSplitsToggle",
            true,
            "$autoSplitsToggleKey.description"
        )).boolean

        val autoSplitsHostKey = "zombiesaddon.config.autoSplitsHost"
        autoSplitsHost = addOption(categoryAutoSplits.list, autoSplitsHostKey, config.get(
            categoryAutoSplits.name,
            "autoSplitsHost",
            "127.0.0.1",
            "$autoSplitsHostKey.description"
        )).string

        val autoSplitsPortKey = "zombiesaddon.config.autoSplitsPort"
        autoSplitsPort = addOption(categoryAutoSplits.list, autoSplitsPortKey, config.get(
            categoryAutoSplits.name,
            "autoSplitsPort",
            16834,
            "$autoSplitsPortKey.description",
            0, 65535
        )).int


        // Wave Delays
        val waveDelaysToggleKey = "zombiesaddon.config.waveDelaysToggle"
        waveDelaysToggle = addOption(categoryWaveDelays.list, waveDelaysToggleKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysToggle",
            true,
            "$waveDelaysToggleKey.description"
        )).boolean

        val waveDelaysPlaySoundsKey = "zombiesaddon.config.waveDelaysPlaySounds"
        waveDelaysPlaySounds = addOption(categoryWaveDelays.list, waveDelaysPlaySoundsKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysPlaySounds",
            intArrayOf(-60, -40, -20),
            "$waveDelaysPlaySoundsKey.description",
            -200, 200, false, 5
        )).intList

        val waveDelaysCustomPlaySoundKey = "zombiesaddon.config.waveDelaysCustomPlaySound"
        waveDelaysCustomPlaySound = addOption(categoryWaveDelays.list, waveDelaysCustomPlaySoundKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysCustomPlaySound",
            true,
            "$waveDelaysCustomPlaySoundKey.description"
        )).boolean

        val waveDelaysPrefixKey = "zombiesaddon.config.waveDelaysPrefix"
        waveDelaysPrefix = addOption(categoryWaveDelays.list, waveDelaysPrefixKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysPrefix",
            true,
            "$waveDelaysPrefixKey.description"
        )).boolean

        val waveDelaysBossColorKey = "zombiesaddon.config.waveDelaysBossColor"
        waveDelaysBossColor = addOption(categoryWaveDelays.list, waveDelaysBossColorKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysBossColor",
            true,
            "$waveDelaysBossColorKey.description"
        )).boolean

        val waveDelaysTextStyleKey = "zombiesaddon.config.waveDelaysTextStyle"
        waveDelaysTextStyle = TextStyle.fromText(addOption(categoryWaveDelays.list, waveDelaysTextStyleKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysTextStyle",
            TextStyle.NEW_COLON.toString(),
            "$waveDelaysTextStyleKey.description",
            TextStyle.arrays
        )).string)

        val waveDelaysHighlightStyleKey = "zombiesaddon.config.waveDelaysHighlightStyle"
        waveDelaysHighlightStyle = HighlightStyle.fromText(addOption(categoryWaveDelays.list, waveDelaysHighlightStyleKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysHighlightStyle",
            HighlightStyle.ZOMBIES_ADDON.toString(),
            "$waveDelaysHighlightStyleKey.description",
            HighlightStyle.arrays
        )).string)

        val waveDelaysHidePassedWaveKey = "zombiesaddon.config.waveDelaysHidePassedWave"
        waveDelaysHidePassedWave = addOption(categoryWaveDelays.list, waveDelaysHidePassedWaveKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysHidePassedWave",
            false,
            "$waveDelaysHidePassedWaveKey.description"
        )).boolean

        val waveDelaysRlModeOffsetKey = "zombiesaddon.config.waveDelaysRlModeOffset"
        waveDelaysRlModeOffset = addOption(categoryWaveDelays.list, waveDelaysRlModeOffsetKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysRlModeOffset",
            -28,
            "$waveDelaysRlModeOffsetKey.description",
            -1000, 1000
        )).int


        // SLA
        val slaAutoSlaKey = "zombiesaddon.config.slaAutoSla"
        slaAutoSla = addOption(categorySla.list, slaAutoSlaKey, config.get(
            categorySla.name,
            "slaAutoSla",
            true,
            "$slaAutoSlaKey.description"
        )).boolean

        val slaTextColorKey = "zombiesaddon.config.slaTextColor"
        slaTextColor = addOption(categorySla.list, slaTextColorKey, config.get(
            categorySla.name,
            "slaTextColor",
            true,
            "$slaTextColorKey.description"
        )).boolean

        val slaActivatedWindowsKey = "zombiesaddon.config.slaActivatedWindows"
        slaActivatedWindows = addOption(categorySla.list, slaActivatedWindowsKey, config.get(
            categorySla.name,
            "slaActivatedWindows",
            true,
            "$slaActivatedWindowsKey.description"
        )).boolean

        val slaUnactivatedWindowsKey = "zombiesaddon.config.slaUnactivatedWindows"
        slaUnactivatedWindows = addOption(categorySla.list, slaUnactivatedWindowsKey, config.get(
            categorySla.name,
            "slaUnactivatedWindows",
            false,
            "$slaUnactivatedWindowsKey.description"
        )).boolean


        // Auto Rejoin
        val autoRejoinDefaultKey = "zombiesaddon.config.defaultAutoRejoin"
        autoRejoinDefault = addOption(categoryAutoRejoin.list, autoRejoinDefaultKey, config.get(
            categoryAutoRejoin.name,
            "autoRejoinDefault",
            false,
            "$autoRejoinDefaultKey.description"
        )).boolean

        val autoRejoinTextKey = "zombiesaddon.config.autoRejoinText"
        autoRejoinText = addOption(categoryAutoRejoin.list, autoRejoinTextKey, config.get(
            categoryAutoRejoin.name,
            "autoRejoinText",
            false,
            "$autoRejoinTextKey.description"
        )).boolean


        // Last Weapons
        val lwToggleKey = "zombiesaddon.config.lwToggle"
        lwToggle = addOption(categoryLastWeapons.list, lwToggleKey, config.get(
            categoryLastWeapons.name,
            "lwToggle",
            true,
            "$lwToggleKey.description"
        )).boolean

        val lwDisplayArmorsKey = "zombiesaddon.config.lwDisplayArmors"
        lwDisplayArmors = addOption(categoryLastWeapons.list, lwDisplayArmorsKey, config.get(
            categoryLastWeapons.name,
            "lwDisplayArmors",
            true,
            "$lwDisplayArmorsKey.description"
        )).boolean

        val lwDisplayWeaponsLevelKey = "zombiesaddon.config.lwDisplayWeaponsLevel"
        lwDisplayWeaponsLevel = addOption(categoryLastWeapons.list, lwDisplayWeaponsLevelKey, config.get(
            categoryLastWeapons.name,
            "lwDisplayWeaponsLevel",
            true,
            "$lwDisplayWeaponsLevelKey.description"
        )).boolean

        val lwDisplayCooledDownSkillKey = "zombiesaddon.config.lwDisplayCooledDownSkill"
        lwDisplayCooledDownSkill = addOption(categoryLastWeapons.list, lwDisplayCooledDownSkillKey, config.get(
            categoryLastWeapons.name,
            "lwDisplayCooledDownSkill",
            true,
            "$lwDisplayCooledDownSkillKey.description"
        )).boolean


        // 한글 패치
        val koreanPatchersIngameKey = "zombiesaddon.config.koreanPatchersIngame"
        koreanPatchersIngame = addOption(categoryKoreanPatchers.list, koreanPatchersIngameKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersIngame",
            false,
            "$koreanPatchersIngameKey.description"
        )).boolean

        val koreanPatchersZombiesOverlayKey = "zombiesaddon.config.koreanPatchersZombiesOverlay"
        koreanPatchersZombiesOverlay = addOption(categoryKoreanPatchers.list, koreanPatchersZombiesOverlayKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersZombiesOverlay",
            false,
            "$koreanPatchersZombiesOverlayKey.description"
        )).boolean

        val koreanPatchersSstKey = "zombiesaddon.config.koreanPatchersSst"
        koreanPatchersSst = addOption(categoryKoreanPatchers.list, koreanPatchersSstKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersSst",
            false,
            "$koreanPatchersSstKey.description"
        )).boolean

//        val koreanPatchersZombiesUtilsKey = "zombiesaddon.config.koreanPatchersZombiesUtils"
//        koreanPatchersZombiesUtils = addOption(categoryKoreanPatchers.list, koreanPatchersZombiesUtilsKey, config.get(
//            categoryKoreanPatchers.name,
//            "koreanPatchersZombiesUtils",
//            false,
//            "$koreanPatchersZombiesUtilsKey.description"
//        )).boolean


        // 다른 모드
        val disableSpawnTimeOfSstKey = "zombiesaddon.config.disableSpawnTimeOfSst"
        disableSpawnTimeOfSst = addOption(categoryOtherMods.list, disableSpawnTimeOfSstKey, config.get(
            categoryOtherMods.name,
            "disableSpawnTimeOfSst",
            true,
            "$disableSpawnTimeOfSstKey.description"
        )).boolean

        val disableTimerOfZombiesUtilsKey = "zombiesaddon.config.disableTimerOfZombiesUtils"
        disableTimerOfZombiesUtils = addOption(categoryOtherMods.list, disableTimerOfZombiesUtilsKey, config.get(
            categoryOtherMods.name,
            "disableTimerOfZombiesUtils",
            true,
            "$disableTimerOfZombiesUtilsKey.description"
        )).boolean


        // 숨겨짐
        blockUnlegitMods = addOption(categoryHidden.list, "zombiesaddon.config.blockUnlegitMods", config.get(
            categoryHidden.name,
            "blockUnlegitMods",
            true,
            "언레짓 모드를 차단합니다."
        )).boolean

        blockUnlegitSst = addOption(categoryHidden.list, "zombiesaddon.config.blockUnlegitSst", config.get(
            categoryHidden.name,
            "blockUnlegitSst",
            true,
            "SST의 언레짓 기능을 차단합니다."
        )).boolean

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

        logger.info("컨피그 로딩 완료.")
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
        if (enableMod) logger.info("Enable Mod가 true로 설정되었습니다.")
        else logger.info("Enable Mod가 false로 설정되었습니다.")
    }

    @SubscribeEvent
    fun onConfigChanged(event: OnConfigChangedEvent) {
        if (event.modID == MODID) save()
    }
}

private data class Category(val name: String) {
    val list = mutableListOf<IConfigElement>()
}