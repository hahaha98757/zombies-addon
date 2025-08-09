package kr.hahaha98757.zombiesaddon

import com.google.gson.GsonBuilder
import com.seosean.showspawntime.ShowSpawnTime
import kr.hahaha98757.zombiesaddon.commands.Commands
import kr.hahaha98757.zombiesaddon.config.ZAConfig
import kr.hahaha98757.zombiesaddon.data.CustomPlaySound
import kr.hahaha98757.zombiesaddon.data.CustomPlaySoundLoader
import kr.hahaha98757.zombiesaddon.events.LastClientTickEventListener
import kr.hahaha98757.zombiesaddon.game.GameManager
import kr.hahaha98757.zombiesaddon.gui.GuiDetectedUnlegitMods
import kr.hahaha98757.zombiesaddon.modules.*
import kr.hahaha98757.zombiesaddon.update.UpdateChecker
import kr.hahaha98757.zombiesaddon.update.UpdateCheckerListener
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
import java.io.File
import java.io.FileWriter

const val MODID = "zombiesaddon"
const val NAME = "Zombies Addon"
const val VERSION = "4.5.0-beta2"

@Mod(modid = MODID, name = NAME, version = VERSION, guiFactory = "kr.hahaha98757.zombiesaddon.config.ZAGuiFactory")
class ZombiesAddon {
    companion object {
        private lateinit var varInstance: ZombiesAddon
        @JvmStatic
        val instance get() = varInstance
    }
    init {
        varInstance = this
    }
    private lateinit var varConfig: ZAConfig
    val config get() = varConfig
    val keyBindings = KeyBindings()
    val gameManager = GameManager()

    private var hasUnlegitMod = false
    var debug = false
        internal set

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        File(mc.mcDataDir, "mods/deleter_zombiesaddon.bat").delete()
        val directory = File(event.modConfigurationDirectory, MODID)
        if (!directory.exists()) directory.mkdir()

        varConfig = ZAConfig(Configuration(File(directory, "$MODID.cfg")))
        writeFile(directory)
        CustomPlaySoundLoader.loadFile()
    }

    @Suppress("unused")
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        registerAll()
    }

    @Suppress("unused")
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        if (config.blockUnlegitMods) for (mod in unlegitMods) if (Loader.isModLoaded(mod)) {
            hasUnlegitMod = true
            break
        }
        if (Loader.isModLoaded("showspawntime")) runCatching { ShowSpawnTime.getMainConfiguration().ConfigLoad() }

        UpdateChecker.setVersion()
        println("$NAME v$VERSION is loaded.")
    }

    @Suppress("unused")
    @SubscribeEvent
    fun startGame(event: GuiScreenEvent.DrawScreenEvent.Post) {
        MinecraftForge.EVENT_BUS.unregister(this)

        if (hasUnlegitMod) mc.displayGuiScreen(GuiDetectedUnlegitMods())
        else UpdateChecker.checkUpdate()
    }

    private fun writeFile(file: File) {
        javaClass.classLoader.getResourceAsStream("assets/$MODID/data/text/Custom Play Sound Guide.txt")?.use { input ->
            File(file, "Custom Play Sound Guide.txt").outputStream().use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) output.write(buffer, 0, bytesRead)
            }
        }

        javaClass.classLoader.getResourceAsStream("assets/$MODID/data/text/커스텀 소리 재생 가이드.txt")?.use { input ->
            File(file, "커스텀 소리 재생 가이드.txt").outputStream().use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) output.write(buffer, 0, bytesRead)
            }
        }

        val json = File(file, "CustomPlaySound.json")
        if (json.exists()) {
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
        MinecraftForge.EVENT_BUS.register(UpdateCheckerListener())
        MinecraftForge.EVENT_BUS.register(LastClientTickEventListener())
        MinecraftForge.EVENT_BUS.register(ModuleListener())
        MinecraftForge.EVENT_BUS.register(ThePlayerJoinListener())

        ModuleListener.registerModule(General.instance)
        ModuleListener.registerModule(PlayerVisibility.instance)
        ModuleListener.registerModule(BlockAlarm.instance)
        ModuleListener.registerModule(NotLast.instance)
        ModuleListener.registerModule(AutoSplits.instance)
        ModuleListener.registerModule(InternalTimer.instance)
        ModuleListener.registerModule(WaveDelays.instance)
        ModuleListener.registerModule(ZombiesStratViewer.instance)
        ModuleListener.registerModule(SlaListener.instance)
        ModuleListener.registerModule(AutoRejoin.instance)
        ModuleListener.registerModule(PowerupPatterns.instance)
        ModuleListener.registerModule(LastWeapons.instance)
        ModuleListener.registerModule(TextMacro.instance)
        ModuleListener.registerModule(KoreanPatchers.instance)
    }
}