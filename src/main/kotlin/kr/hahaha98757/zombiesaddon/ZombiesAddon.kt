package kr.hahaha98757.zombiesaddon

import com.google.gson.GsonBuilder
import com.seosean.showspawntime.ShowSpawnTime
import kr.hahaha98757.zombiesaddon.commands.Commands
import kr.hahaha98757.zombiesaddon.config.ZAConfig
import kr.hahaha98757.zombiesaddon.data.CustomPlaySound
import kr.hahaha98757.zombiesaddon.data.CustomPlaySoundLoader
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.game.GameManager
import kr.hahaha98757.zombiesaddon.gui.GuiDetectedUnlegitMods
import kr.hahaha98757.zombiesaddon.modules.*
import kr.hahaha98757.zombiesaddon.update.UpdateChecker
import kr.hahaha98757.zombiesaddon.update.UpdateCheckerHandler
import kr.hahaha98757.zombiesaddon.utils.mc
import kr.hahaha98757.zombiesaddon.utils.unlegitMods
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.FileWriter

const val MODID = "zombiesaddon"
const val NAME = "Zombies Addon"
const val VERSION = "4.5.3"

@Mod(modid = MODID, name = NAME, version = VERSION, guiFactory = "kr.hahaha98757.zombiesaddon.config.ZAGuiFactory", clientSideOnly = true)
class ZombiesAddon {
    companion object {
        @JvmStatic
        lateinit var instance: ZombiesAddon
            private set
    }
    init {
        instance = this
    }
    lateinit var config: ZAConfig
        private set
    lateinit var logger: Logger
        private set
    val keyBindings = KeyBindings()
    val gameManager = GameManager()

    private var hasUnlegitMod = false
    var hasSST = false
    var hasZombiesUtils = false
    var debug = false
        internal set

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        logger.info("preInit 시작.")
        File(mc.mcDataDir, "mods/deleter_zombiesaddon.bat").delete()
        val directory = File(event.modConfigurationDirectory, MODID)
        if (!directory.exists()) directory.mkdir()

        config = ZAConfig(Configuration(File(directory, "$MODID.cfg")))
        writeFile(directory)
        CustomPlaySoundLoader.loadFile()
    }

    @Suppress("unused")
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        logger.info("init 시작.")
        registerAll()
    }

    @Suppress("unused")
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        logger.info("postInit 시작.")
        if (config.blockUnlegitMods) for (mod in unlegitMods) if (Loader.isModLoaded(mod)) {
            logger.info("언레짓 모드 ${mod}가 감지되었습니다.")
            hasUnlegitMod = true
        }
        if (Loader.isModLoaded("showspawntime")) {
            hasSST = true
            runCatching { ShowSpawnTime.getMainConfiguration().ConfigLoad() }
            logger.info("ShowSpawnTime 모드가 감지되었습니다. 2.1.1 버전이 아닐 경우, 문제가 발생할 수 있습니다.")
        }
        if (Loader.isModLoaded("zombiesutils")) {
            hasZombiesUtils = true
            logger.info("Zombies Utils 모드가 감지되었습니다. 1.3.7 버전이 아닐 경우, 문제가 발생할 수 있습니다.")
        }

        logger.info("$NAME v${VERSION}이 로드되었습니다.")
    }

    @Suppress("unused")
    @SubscribeEvent
    fun startGame(event: GuiScreenEvent.DrawScreenEvent.Post) {
        logger.info("게임 시작.")
        MinecraftForge.EVENT_BUS.unregister(this)

        if (hasUnlegitMod) mc.displayGuiScreen(GuiDetectedUnlegitMods())
        else UpdateChecker.initAndCheck()
    }

    private fun writeFile(file: File) {
        javaClass.classLoader.getResourceAsStream("assets/$MODID/data/text/Custom Play Sound Guide.txt")!!.use { input ->
            File(file, "Custom Play Sound Guide.txt").outputStream().use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) output.write(buffer, 0, bytesRead)
            }
        }

        javaClass.classLoader.getResourceAsStream("assets/$MODID/data/text/커스텀 소리 재생 가이드.txt")!!.use { input ->
            File(file, "커스텀 소리 재생 가이드.txt").outputStream().use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) output.write(buffer, 0, bytesRead)
            }
        }

        val json = File(file, "CustomPlaySound.json")
        if (json.exists()) {
            logger.info("CustomPlaySound.json 파일이 존재합니다. 발생 가능한 문제 해결을 위해, 하위 버전의 문법을 현재 문법으로 변경합니다. playWave -> playType")
            json.writeText(json.readText().replace("playWave", "playType"))
            return
        }

        val cps = arrayOf(
            CustomPlaySound("note.pling", 2.0f, 0, 1),
            CustomPlaySound("note.pling", 1.5f, -60, 2),
            CustomPlaySound("note.pling", 1.5f, -40, 2),
            CustomPlaySound("note.pling", 1.5f, -20, 2),
            CustomPlaySound("random.orb", 0.5f, 0, 2)
        )

        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(cps)

        FileWriter(json).use { it.write(jsonString) }
    }

    private fun registerAll() {
        keyBindings.registerAll()

        Commands.registerCommands()

        MinecraftForge.EVENT_BUS.register(this)
        MinecraftForge.EVENT_BUS.register(config)
        MinecraftForge.EVENT_BUS.register(UpdateCheckerHandler())
        MinecraftForge.EVENT_BUS.register(LastClientTickEvent.EventBridge())
        MinecraftForge.EVENT_BUS.register(ModuleListener())
        MinecraftForge.EVENT_BUS.register(ThePlayerJoinHandler())

        ModuleListener.registerModule(General)
        ModuleListener.registerModule(PlayerVisibility)
        ModuleListener.registerModule(BlockAlarm)
        ModuleListener.registerModule(NotLast)
        ModuleListener.registerModule(InternalTimer)
        ModuleListener.registerModule(WaveDelays)
        ModuleListener.registerModule(ZombiesStratViewer)
        ModuleListener.registerModule(SlaHandler)
        ModuleListener.registerModule(AutoRejoin)
        ModuleListener.registerModule(PowerupPatterns)
        ModuleListener.registerModule(LastWeapons)
        ModuleListener.registerModule(TextMacro)
        ModuleListener.registerModule(KoreanPatchers)
    }
}