package kr.hahaha98757.zombiesaddon.update

import com.google.gson.JsonParser
import kr.hahaha98757.zombiesaddon.VERSION
import kr.hahaha98757.zombiesaddon.gui.GuiDownloadWaiting
import kr.hahaha98757.zombiesaddon.gui.GuiUpdateScreen
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraft.event.ClickEvent
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatComponentTranslation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.commons.io.IOUtils
import java.io.File
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.security.KeyStore
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

object UpdateChecker {
    private const val LATEST_URL = "https://raw.githubusercontent.com/hahaha98757/zombies-addon/main/update.json"
    private const val DEV_URL = "https://raw.githubusercontent.com/hahaha98757/zombies-addon/dev/update.json"
    private val current: Version
    private var latest = Version.ZERO
    private var dev = Version.ZERO
    private var isCorrect = false
    init {
        current = Version.runCatching {
            fromString(VERSION).also { isCorrect = true }
        }.getOrElse {
            logger.error(it)
            isCorrect = false
            Version.ZERO
        }
    }

    private val downloadUrl
        get() = "https://github.com/hahaha98757/zombies-addon/releases/download/$latest/ZombiesAddon1.8.9-$latest.jar"

    private var ctx: SSLContext?
    init {
        try {
            val myKeyStore = KeyStore.getInstance("JKS")
            myKeyStore.load(UpdateChecker::class.java.getResourceAsStream("/mykeystore.jks"), "changeit".toCharArray())
            val kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            kmf.init(myKeyStore, null)
            tmf.init(myKeyStore)
            ctx = SSLContext.getInstance("TLS")
            ctx!!.init(kmf.keyManagers, tmf.trustManagers, null)
        } catch (e: Throwable) {
            logger.error("SSLContext 초기화 실패. 일부 JVM에서 업데이트 확인이 불가능합니다.", e)
            ctx = null
        }
    }

    fun initAndCheck() = Thread {
        if (!isCorrect) return@Thread
        latest = runCatching { getVersion(LATEST_URL) }.getOrElse {
            logger.warn("최신 버전 가져오는데 실패했습니다.", it)
            Version.ZERO
        }
        dev = runCatching { getVersion(DEV_URL) }.getOrElse {
            logger.warn("개발 버전 가져오는데 실패했습니다.", it)
            latest
        }
        logger.info("현재 버전: $current, 최신 버전: $latest, 개발 버전: $dev")
        mc.addScheduledTask { check() }
    }.start()

    fun check(displayGui: Boolean = true) {
        if (!isCorrect) return
        if (dev == Version.ZERO) {
            addTranslatedChat("zombiesaddon.update.failed")
            return
        }

        // 이 이후로 dev, latest 모두 zero가 아님이 보장됨
        if (current > dev) { // 미공개 버전 사용 중
            if (current.versionType == VersionType.ALPHA) {
                addLine()
                addTranslatedChat("zombiesaddon.update.usingDev")
                addTranslatedChat(getDevWarningKey(current.versionType))
                addLine()
            }
            return
        }

        if (current.x < latest.x) {
            mc.displayGuiScreen(GuiUpdateScreen.getInstance(true)) // 필수 업데이트
            return
        }

        if (latest == dev) {
            if (current != latest && displayGui) mc.displayGuiScreen(GuiUpdateScreen.getInstance(false)) // 새로운 최신 버전
        } else {
            if (current == dev) usingDev() // 개발 버전 사용 중
            else newDev() // 새로운 개발 버전
        }
    }

    private fun usingDev() {
        if (current.versionType == VersionType.RELEASE) return
        addLine()
        addTranslatedChat("zombiesaddon.update.usingDev")
        addTranslatedChat(getDevWarningKey(current.versionType))
        addLine()
    }

    private fun newDev() {
        if (dev.versionType == VersionType.RELEASE) return
        val url = ChatComponentText(getTranslatedString("zombiesaddon.update.downloadText")).apply {
            chatStyle.chatClickEvent =
                ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/zombies-addon/releases")
            chatStyle.chatHoverEvent =
                HoverEvent(HoverEvent.Action.SHOW_TEXT, ChatComponentTranslation("zombiesaddon.update.downloadHover"))
        }
        addLine()
        ChatComponentText("").run {
            appendText(getTranslatedString("zombiesaddon.update.newDev"))
            appendText(" ")
            appendSibling(url)
            appendText("\n")
            appendText(getTranslatedString("zombiesaddon.update.currentVer", true, VERSION))
            appendText("\n")
            appendText(getTranslatedString("zombiesaddon.update.devVer", true, dev))
            appendText("\n")
            appendText(getTranslatedString(getDevWarningKey(dev.versionType)))
            addChat(this)
        }
        addLine()
    }

    private fun getDevWarningKey(type: VersionType) = when (type) {
        VersionType.ALPHA -> "zombiesaddon.update.alpha"
        VersionType.BETA -> "zombiesaddon.update.beta"
        VersionType.PRE_RELEASE -> "zombiesaddon.update.pre"
        VersionType.RELEASE_CANDIDATE -> "zombiesaddon.update.rc"
        VersionType.RELEASE -> ""
    }

    fun autoUpdate() = Thread {
        val tempMod = File(modFile.parentFile, ".temp/ZombiesAddon1.8.9-$latest.jar.temp")
        val newMod = File(modFile.parentFile, "ZombiesAddon1.8.9-$latest.jar")
        try {
            tempMod.parentFile.mkdirs()
            val connection = URL(downloadUrl).openConnection() as HttpsURLConnection?
            if (connection != null && ctx != null) connection.sslSocketFactory = ctx!!.socketFactory

            connection!!.requestMethod = "GET"
            connection.connect()

            Files.copy(connection.inputStream, tempMod.toPath(), StandardCopyOption.REPLACE_EXISTING)

            if (!verifyModFile(tempMod)) throw Exception("다운로드한 파일의 무결성 검사에 실패했습니다.")

            Files.move(tempMod.toPath(), newMod.toPath(),
                StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)

            mc.addScheduledTask {
                logger.info("배치 파일 실행 및 게임 종료를 시작합니다.")
                runBatchFileAndQuit(File(mc.mcDataDir, "mods/deleter_zombiesaddon.bat"), """
                    @echo off
                    chcp 65001
                    cls
                    echo This is a mod deleter. It should continue after Minecraft quits.
                    echo 이것은 모드 제거 프로그램 입니다. 마인크래프트가 종료된 후 계속 진행되어야 합니다.
                    timeout /t 2 /nobreak
                    pause
                    echo Deleting old mod file...
                    echo 이전 모드 파일을 삭제하는 중...
                    del "${modFile.absolutePath}"
                    exit
                """.trimIndent())
            }
        } catch (e: Exception) {
            if (newMod.exists()) newMod.delete()
            logger.error("자동 업데이트 중 오류 발생.", e)
            GuiDownloadWaiting.failed = true
        } finally {
            if (tempMod.exists()) tempMod.delete()
            tempMod.parentFile.delete()
        }
    }.start()

    private fun verifyModFile(file: File): Boolean {
        val connection = URL("$downloadUrl.sha256").openConnection() as HttpsURLConnection?
        if (connection != null && ctx != null) connection.sslSocketFactory = ctx!!.socketFactory

        connection!!.requestMethod = "GET"
        connection.connect()

        val expectedHash = IOUtils.toString(connection.inputStream, StandardCharsets.UTF_8).trim()
        logger.info("예상되는 SHA-256: $expectedHash")
        val actualHash = calculateSHA256(file)
        return expectedHash.equals(actualHash, ignoreCase = true)
    }

    private fun calculateSHA256(file: File): String {
        val digest = java.security.MessageDigest.getInstance("SHA-256")
        file.inputStream().use { input ->
            val buffer = ByteArray(8192)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } > 0) digest.update(buffer, 0, bytesRead)
        }
        return digest.digest().joinToString("") { "%02x".format(it) }.also { logger.info("실제 SHA-256: $it") }
    }

    private fun getVersion(url: String): Version {
        val url = URL(url)
        val connection = url.openConnection() as HttpsURLConnection?
        if (connection != null && ctx != null) connection.sslSocketFactory = ctx!!.socketFactory

        connection!!.requestMethod = "GET"
        connection.connectTimeout = 60000
        connection.readTimeout = 60000

        connection.inputStream.use {
            val jsonResponse = IOUtils.toString(it, StandardCharsets.UTF_8)

            val json = JsonParser().parse(jsonResponse).asJsonObject
            return Version.fromString(json.get("version").asString)
        }
    }
}

class UpdateCheckerHandler {
    @SubscribeEvent
    fun onPlayerJoin(event: EntityJoinWorldEvent) {
        if (event.entity != mc.thePlayer) return
        UpdateChecker.check(false)
        MinecraftForge.EVENT_BUS.unregister(this)
    }
}