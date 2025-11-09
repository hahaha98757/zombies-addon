package kr.hahaha98757.zombiesaddon.config

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.enums.HighlightStyle
import kr.hahaha98757.zombiesaddon.enums.Language
import kr.hahaha98757.zombiesaddon.enums.SemiPvMode
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
    private val categoryRecorder = Category("Recorder")
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
    var pvSemiPvMinAlpha = 0.0
    var pvSemiPvMaxAlpha = 1.0
        get() = if (field < pvSemiPvMinAlpha) pvSemiPvMinAlpha else field
    var pvSemiPvMode = SemiPvMode.LINEAR

    var blockAlarmDefault = false
    var blockAlarmText = false

    var autoSplitsToggle = false
    var autoSplitsHost = "127.0.0.1"
    var autoSplitsPort = 16384

    var waveDelaysToggle = true
    var waveDelaysPlaySounds = intArrayOf(-40, -20, 0)
    var waveDelaysCustomPlaySound = false
    var waveDelaysPrefix = true
    var waveDelaysBossColor = true
    var waveDelaysTextStyle = TextStyle.NEW_COLON
    var waveDelaysHighlightStyle = HighlightStyle.ZOMBIES_ADDON
    var waveDelaysHidePassedWave = false
    var waveDelaysRlModeOffset = -28
    
    var slaAutoSla = false
    var slaTextColor = true
    var slaActivatedWindows = true
    var slaUnactivatedWindows = false

    var autoRejoinDefault = false
    var autoRejoinText = false

    var lwToggle = true
    var lwDisplayArmors = true
    var lwDisplayWeaponsLevel = true
    var lwDisplayCooledDownSkill = true

    var recorderToggle = true
    var recorderDefaultCategory = "general"
        get() = if ("/" in field || "\\" in field) "general" else field
    var recorderPbNotice = true

    var koreanPatchersIngame = false
    var koreanPatchersZombiesOverlay = false
    var koreanPatchersSst = false
    var koreanPatchersZombiesUtils = false

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
        enableMod = addOption(categoryGeneral.map, enableModKey, config.get(
            categoryGeneral.name,
            "enableMod",
            true,
            "$enableModKey.description"
        )).boolean

        val languageKey = "zombiesaddon.config.language"
        language = Language.fromText(addOption(categoryGeneral.map, languageKey, config.get(
            categoryGeneral.name,
            "language",
            Language.AUTO.toString(),
            "$languageKey.description",
            Language.arrays
        )).string)


        // 모듈
        val toggleNotLastKey = "zombiesaddon.config.toggleNotLast"
        toggleNotLast = addOption(categoryModules.map, toggleNotLastKey, config.get(
            categoryModules.name,
            "toggleNotLast",
            false,
            "$toggleNotLastKey.description"
        )).boolean

        val toggleInternalTimerKey = "zombiesaddon.config.toggleInternalTimer"
        toggleInternalTimer = addOption(categoryModules.map, toggleInternalTimerKey, config.get(
            categoryModules.name,
            "toggleInternalTimer",
            true,
            "$toggleInternalTimerKey.description"
        )).boolean

        val togglePowerupPatternsKey = "zombiesaddon.config.togglePowerupPatterns"
        togglePowerupPatterns = addOption(categoryModules.map, togglePowerupPatternsKey, config.get(
            categoryModules.name,
            "togglePowerupPatterns",
            true,
            "$togglePowerupPatternsKey.description"
        )).boolean

        val textMacroKey = "zombiesaddon.config.textMacro"
        textMacro = addOption(categoryModules.map, textMacroKey, config.get(
            categoryModules.name,
            "textMacro",
            "T",
            "$textMacroKey.description"
        )).string


        // Player Visibility
        val pvDefaultKey = "zombiesaddon.config.pvDefault"
        pvDefault = addOption(categoryPv.map, pvDefaultKey, config.get(
            categoryPv.name,
            "pvDefault",
            true,
            "$pvDefaultKey.description"
        )).boolean

        val pvTextKey = "zombiesaddon.config.pvText"
        pvText = addOption(categoryPv.map, pvTextKey, config.get(
            categoryPv.name,
            "pvText",
            true,
            "$pvTextKey.description"
        )).boolean

        val pvRangeKey = "zombiesaddon.config.pvRange"
        pvRange = addOption(categoryPv.map, pvRangeKey, config.get(
            categoryPv.name,
            "pvRange",
            2.5,
            "$pvRangeKey.description",
            0.0, 10.0
        )).double

        val pvToggleSemiPvKey = "zombiesaddon.config.pvToggleSemiPv"
        pvToggleSemiPv = addOption(categoryPv.map, pvToggleSemiPvKey, config.get(
            categoryPv.name,
            "pvToggleSemiPv",
            true,
            "$pvToggleSemiPvKey.description"
        )).boolean

        val pvSemiPvRangeKey = "zombiesaddon.config.pvSemiPvRange"
        pvSemiPvRange = addOption(categoryPv.map, pvSemiPvRangeKey, config.get(
            categoryPv.name,
            "pvSemiPvRange",
            5.0,
            "$pvSemiPvRangeKey.description",
            0.0, 10.0
        )).double

        val pvSemiPvMinAlphaKey = "zombiesaddon.config.pvSemiPvMinAlpha"
        pvSemiPvMinAlpha = addOption(categoryPv.map, pvSemiPvMinAlphaKey, config.get(
            categoryPv.name,
            "pvSemiPvMinAlpha",
            0.0,
            "$pvSemiPvMinAlphaKey.description",
            0.0, 1.0
        )).double

        val pvSemiPvMaxAlphaKey = "zombiesaddon.config.pvSemiPvMaxAlpha"
        pvSemiPvMaxAlpha = addOption(categoryPv.map, pvSemiPvMaxAlphaKey, config.get(
            categoryPv.name,
            "pvSemiPvMaxAlpha",
            1.0,
            "$pvSemiPvMaxAlphaKey.description",
            0.0, 1.0
        )).double

        val pvSemiPvModeKey = "zombiesaddon.config.pvSemiPvMode"
        pvSemiPvMode = SemiPvMode.fromText(addOption(categoryPv.map, pvSemiPvModeKey, config.get(
            categoryPv.name,
            "pvSemiPvMode",
            SemiPvMode.LINEAR.toString(),
            "$pvSemiPvModeKey.description",
            SemiPvMode.arrays
        )).string)


        // Block Alarm
        val blockAlarmDefaultKey = "zombiesaddon.config.defaultBlockAlarm"
        blockAlarmDefault = addOption(categoryBlockAlarm.map, blockAlarmDefaultKey, config.get(
            categoryBlockAlarm.name,
            "blockAlarmDefault",
            false,
            "$blockAlarmDefaultKey.description"
        )).boolean

        val blockAlarmTextKey = "zombiesaddon.config.blockAlarmText"
        blockAlarmText = addOption(categoryBlockAlarm.map, blockAlarmTextKey, config.get(
            categoryBlockAlarm.name,
            "blockAlarmText",
            false,
            "$blockAlarmTextKey.description"
        )).boolean


        // Auto Splits
        val autoSplitsToggleKey = "zombiesaddon.config.autoSplitsToggle"
        autoSplitsToggle = addOption(categoryAutoSplits.map, autoSplitsToggleKey, config.get(
            categoryAutoSplits.name,
            "autoSplitsToggle",
            false,
            "$autoSplitsToggleKey.description"
        )).boolean

        val autoSplitsHostKey = "zombiesaddon.config.autoSplitsHost"
        autoSplitsHost = addOption(categoryAutoSplits.map, autoSplitsHostKey, config.get(
            categoryAutoSplits.name,
            "autoSplitsHost",
            "127.0.0.1",
            "$autoSplitsHostKey.description"
        )).string

        val autoSplitsPortKey = "zombiesaddon.config.autoSplitsPort"
        autoSplitsPort = addOption(categoryAutoSplits.map, autoSplitsPortKey, config.get(
            categoryAutoSplits.name,
            "autoSplitsPort",
            16834,
            "$autoSplitsPortKey.description",
            0, 65535
        )).int


        // Wave Delays
        val waveDelaysToggleKey = "zombiesaddon.config.waveDelaysToggle"
        waveDelaysToggle = addOption(categoryWaveDelays.map, waveDelaysToggleKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysToggle",
            true,
            "$waveDelaysToggleKey.description"
        )).boolean

        val waveDelaysPlaySoundsKey = "zombiesaddon.config.waveDelaysPlaySounds"
        waveDelaysPlaySounds = addOption(categoryWaveDelays.map, waveDelaysPlaySoundsKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysPlaySounds",
            intArrayOf(-40, -20, 0),
            "$waveDelaysPlaySoundsKey.description",
            -200, 200, false, 5
        )).intList

        val waveDelaysCustomPlaySoundKey = "zombiesaddon.config.waveDelaysCustomPlaySound"
        waveDelaysCustomPlaySound = addOption(categoryWaveDelays.map, waveDelaysCustomPlaySoundKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysCustomPlaySound",
            false,
            "$waveDelaysCustomPlaySoundKey.description"
        )).boolean

        val waveDelaysPrefixKey = "zombiesaddon.config.waveDelaysPrefix"
        waveDelaysPrefix = addOption(categoryWaveDelays.map, waveDelaysPrefixKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysPrefix",
            true,
            "$waveDelaysPrefixKey.description"
        )).boolean

        val waveDelaysBossColorKey = "zombiesaddon.config.waveDelaysBossColor"
        waveDelaysBossColor = addOption(categoryWaveDelays.map, waveDelaysBossColorKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysBossColor",
            true,
            "$waveDelaysBossColorKey.description"
        )).boolean

        val waveDelaysTextStyleKey = "zombiesaddon.config.waveDelaysTextStyle"
        waveDelaysTextStyle = TextStyle.fromText(addOption(categoryWaveDelays.map, waveDelaysTextStyleKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysTextStyle",
            TextStyle.NEW_COLON.toString(),
            "$waveDelaysTextStyleKey.description",
            TextStyle.arrays
        )).string)

        val waveDelaysHighlightStyleKey = "zombiesaddon.config.waveDelaysHighlightStyle"
        waveDelaysHighlightStyle = HighlightStyle.fromText(addOption(categoryWaveDelays.map, waveDelaysHighlightStyleKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysHighlightStyle",
            HighlightStyle.ZOMBIES_ADDON.toString(),
            "$waveDelaysHighlightStyleKey.description",
            HighlightStyle.arrays
        )).string)

        val waveDelaysHidePassedWaveKey = "zombiesaddon.config.waveDelaysHidePassedWave"
        waveDelaysHidePassedWave = addOption(categoryWaveDelays.map, waveDelaysHidePassedWaveKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysHidePassedWave",
            false,
            "$waveDelaysHidePassedWaveKey.description"
        )).boolean

        val waveDelaysRlModeOffsetKey = "zombiesaddon.config.waveDelaysRlModeOffset"
        waveDelaysRlModeOffset = addOption(categoryWaveDelays.map, waveDelaysRlModeOffsetKey, config.get(
            categoryWaveDelays.name,
            "waveDelaysRlModeOffset",
            -28,
            "$waveDelaysRlModeOffsetKey.description",
            -1000, 1000
        )).int


        // SLA
        val slaAutoSlaKey = "zombiesaddon.config.slaAutoSla"
        slaAutoSla = addOption(categorySla.map, slaAutoSlaKey, config.get(
            categorySla.name,
            "slaAutoSla",
            false,
            "$slaAutoSlaKey.description"
        )).boolean

        val slaTextColorKey = "zombiesaddon.config.slaTextColor"
        slaTextColor = addOption(categorySla.map, slaTextColorKey, config.get(
            categorySla.name,
            "slaTextColor",
            true,
            "$slaTextColorKey.description"
        )).boolean

        val slaActivatedWindowsKey = "zombiesaddon.config.slaActivatedWindows"
        slaActivatedWindows = addOption(categorySla.map, slaActivatedWindowsKey, config.get(
            categorySla.name,
            "slaActivatedWindows",
            true,
            "$slaActivatedWindowsKey.description"
        )).boolean

        val slaUnactivatedWindowsKey = "zombiesaddon.config.slaUnactivatedWindows"
        slaUnactivatedWindows = addOption(categorySla.map, slaUnactivatedWindowsKey, config.get(
            categorySla.name,
            "slaUnactivatedWindows",
            false,
            "$slaUnactivatedWindowsKey.description"
        )).boolean


        // Auto Rejoin
        val autoRejoinDefaultKey = "zombiesaddon.config.defaultAutoRejoin"
        autoRejoinDefault = addOption(categoryAutoRejoin.map, autoRejoinDefaultKey, config.get(
            categoryAutoRejoin.name,
            "autoRejoinDefault",
            false,
            "$autoRejoinDefaultKey.description"
        )).boolean

        val autoRejoinTextKey = "zombiesaddon.config.autoRejoinText"
        autoRejoinText = addOption(categoryAutoRejoin.map, autoRejoinTextKey, config.get(
            categoryAutoRejoin.name,
            "autoRejoinText",
            false,
            "$autoRejoinTextKey.description"
        )).boolean


        // Last Weapons
        val lwToggleKey = "zombiesaddon.config.lwToggle"
        lwToggle = addOption(categoryLastWeapons.map, lwToggleKey, config.get(
            categoryLastWeapons.name,
            "lwToggle",
            true,
            "$lwToggleKey.description"
        )).boolean

        val lwDisplayArmorsKey = "zombiesaddon.config.lwDisplayArmors"
        lwDisplayArmors = addOption(categoryLastWeapons.map, lwDisplayArmorsKey, config.get(
            categoryLastWeapons.name,
            "lwDisplayArmors",
            true,
            "$lwDisplayArmorsKey.description"
        )).boolean

        val lwDisplayWeaponsLevelKey = "zombiesaddon.config.lwDisplayWeaponsLevel"
        lwDisplayWeaponsLevel = addOption(categoryLastWeapons.map, lwDisplayWeaponsLevelKey, config.get(
            categoryLastWeapons.name,
            "lwDisplayWeaponsLevel",
            true,
            "$lwDisplayWeaponsLevelKey.description"
        )).boolean

        val lwDisplayCooledDownSkillKey = "zombiesaddon.config.lwDisplayCooledDownSkill"
        lwDisplayCooledDownSkill = addOption(categoryLastWeapons.map, lwDisplayCooledDownSkillKey, config.get(
            categoryLastWeapons.name,
            "lwDisplayCooledDownSkill",
            true,
            "$lwDisplayCooledDownSkillKey.description"
        )).boolean


        // Recorder
        val recorderToggleKey = "zombiesaddon.config.recorderToggle"
        recorderToggle = addOption(categoryRecorder.map, recorderToggleKey, config.get(
            categoryRecorder.name,
            "recorderToggle",
            true,
            "$recorderToggleKey.description"
        )).boolean

        val recorderDefaultCategoryKey = "zombiesaddon.config.recorderDefaultCategory"
        recorderDefaultCategory = addOption(categoryRecorder.map, recorderDefaultCategoryKey, config.get(
            categoryRecorder.name,
            "recorderDefaultCategory",
            "general",
            "$recorderDefaultCategoryKey.description"
        )).string

        val recorderPbNoticeKey = "zombiesaddon.config.recorderPbNotice"
        recorderPbNotice = addOption(categoryRecorder.map, recorderPbNoticeKey, config.get(
            categoryRecorder.name,
            "recorderPbNotice",
            true,
            "$recorderPbNoticeKey.description"
        )).boolean


        // 한글 패치
        val koreanPatchersIngameKey = "zombiesaddon.config.koreanPatchersIngame"
        koreanPatchersIngame = addOption(categoryKoreanPatchers.map, koreanPatchersIngameKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersIngame",
            false,
            "$koreanPatchersIngameKey.description"
        )).boolean

        val koreanPatchersZombiesOverlayKey = "zombiesaddon.config.koreanPatchersZombiesOverlay"
        koreanPatchersZombiesOverlay = addOption(categoryKoreanPatchers.map, koreanPatchersZombiesOverlayKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersZombiesOverlay",
            false,
            "$koreanPatchersZombiesOverlayKey.description"
        )).boolean

        val koreanPatchersSstKey = "zombiesaddon.config.koreanPatchersSst"
        koreanPatchersSst = addOption(categoryKoreanPatchers.map, koreanPatchersSstKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersSst",
            false,
            "$koreanPatchersSstKey.description"
        )).boolean

        val koreanPatchersZombiesUtilsKey = "zombiesaddon.config.koreanPatchersZombiesUtils"
        koreanPatchersZombiesUtils = addOption(categoryKoreanPatchers.map, koreanPatchersZombiesUtilsKey, config.get(
            categoryKoreanPatchers.name,
            "koreanPatchersZombiesUtils",
            false,
            "$koreanPatchersZombiesUtilsKey.description"
        )).boolean


        // 다른 모드
        val disableSpawnTimeOfSstKey = "zombiesaddon.config.disableSpawnTimeOfSst"
        disableSpawnTimeOfSst = addOption(categoryOtherMods.map, disableSpawnTimeOfSstKey, config.get(
            categoryOtherMods.name,
            "disableSpawnTimeOfSst",
            true,
            "$disableSpawnTimeOfSstKey.description"
        )).boolean

        val disableTimerOfZombiesUtilsKey = "zombiesaddon.config.disableTimerOfZombiesUtils"
        disableTimerOfZombiesUtils = addOption(categoryOtherMods.map, disableTimerOfZombiesUtilsKey, config.get(
            categoryOtherMods.name,
            "disableTimerOfZombiesUtils",
            true,
            "$disableTimerOfZombiesUtilsKey.description"
        )).boolean


        // 숨겨짐
        blockUnlegitMods = addOption(categoryHidden.map, "zombiesaddon.config.blockUnlegitMods", config.get(
            categoryHidden.name,
            "blockUnlegitMods",
            true,
            "언레짓 모드를 차단합니다."
        )).boolean

        blockUnlegitSst = addOption(categoryHidden.map, "zombiesaddon.config.blockUnlegitSst", config.get(
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

    private fun addOption(category: MutableMap<String, IConfigElement>, langKey: String, prop: Property): Property {
        prop.languageKey = langKey
        category[prop.name] = ConfigElement(prop)
        return prop
    }

    fun getElements() = listOf(
        DummyCategoryElement(categoryGeneral.name, "zombiesaddon.config.category.general", categoryGeneral.map.values.toList()),
        DummyCategoryElement(categoryModules.name, "zombiesaddon.config.category.modules", categoryModules.map.values.toList()),
        DummyCategoryElement(categoryPv.name, "zombiesaddon.config.category.pv", categoryPv.map.values.toList()),
        DummyCategoryElement(categoryBlockAlarm.name, "zombiesaddon.config.category.blockAlarm", categoryBlockAlarm.map.values.toList()),
        DummyCategoryElement(categoryAutoSplits.name, "zombiesaddon.config.category.autoSplits", categoryAutoSplits.map.values.toList()),
        DummyCategoryElement(categoryWaveDelays.name, "zombiesaddon.config.category.waveDelays", categoryWaveDelays.map.values.toList()),
        DummyCategoryElement(categoryAutoRejoin.name, "zombiesaddon.config.category.autoRejoin", categoryAutoRejoin.map.values.toList()),
        DummyCategoryElement(categorySla.name, "zombiesaddon.config.category.sla", categorySla.map.values.toList()),
        DummyCategoryElement(categoryLastWeapons.name, "zombiesaddon.config.category.lw", categoryLastWeapons.map.values.toList()),
        DummyCategoryElement(categoryRecorder.name, "zombiesaddon.config.category.recorder", categoryRecorder.map.values.toList()),
        DummyCategoryElement(categoryKoreanPatchers.name, "zombiesaddon.config.category.koreanPatchers", categoryKoreanPatchers.map.values.toList()),
        DummyCategoryElement(categoryOtherMods.name, "zombiesaddon.config.category.otherMods", categoryOtherMods.map.values.toList())
    )

    private fun save() {
        config.save()
        loadConfig()
    }

    @SubscribeEvent
    fun onConfigChanged(event: OnConfigChangedEvent) {
        if (event.modID == MODID) save()
    }
}

private data class Category(val name: String) {
    val map = mutableMapOf<String, IConfigElement>()
}