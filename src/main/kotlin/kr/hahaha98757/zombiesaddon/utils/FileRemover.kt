package kr.hahaha98757.zombiesaddon.utils

import net.minecraft.client.Minecraft
import java.io.File
import java.io.RandomAccessFile

object FileRemover {
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) return
        Thread.sleep(1000)

        val targets = args[0].split("|").map { File(it) }

        for (file in targets) {
            if (!file.exists()) continue
            if (file.isDirectory) continue
            waitUntilUnlocked(file)
            deleteWithRetry(file)
        }
    }

    private fun waitUntilUnlocked(file: File, timeout: Long = 10000) {
        var time = timeout
        while (isFileLocked(file)) {
            Thread.sleep(1000)
            time -= 1000
            if (time <= 0) return
        }
    }

    private fun isFileLocked(file: File) = if (!file.exists()) false
    else try {
        RandomAccessFile(file, "rw").use { raf ->
            val lock = raf.channel.tryLock()
            if (lock != null) {
                lock.release()
                false
            } else true
        }
    } catch (_: Exception) {
        true
    }

    private fun deleteWithRetry(file: File) = repeat(10) {
        if (!file.exists() || file.delete()) return
        Thread.sleep(500)
    }
}

object FileRemoverLauncher {
    /** Minecraft API 필요 */
    internal fun runAndQuit(targets: Array<File>, jar: File? = null) {
        runCatching { Minecraft.getMinecraft() }.getOrNull() ?: throw Error("Minecraft not found")
        logger.info("FileRemover 실행 및 게임 종료를 시작합니다.")
        val jarFile = jar ?: modFile
        val joinedPaths = targets.joinToString(separator = "|") { it.absolutePath }
        val jvm = jvmPath

        val command = if (isWindows) {
            listOf(
                "cmd", "/c", "start", "",
                jvm,
                "-cp", jarFile.absolutePath,
                "kr.hahaha98757.zombiesaddon.utils.FileRemover",
                joinedPaths
            )
        } else {
            listOf(
                "sh", "-c",
                "nohup \"$jvm\" -cp \"${jarFile.absolutePath}\" kr.hahaha98757.zombiesaddon.utils.FileRemover \"$joinedPaths\" >/dev/null 2>&1 &"
            )
        }
        logger.info("실행할 명령: ${command.joinToString(" ")}")

        ProcessBuilder(command).start()
        Thread.sleep(1000)
        mc.shutdown()
    }

    private val jvmPath: String get() {
        val exec = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw"
        return if (isWindows) "$exec.exe" else exec
    }

    private val isWindows = "win" in System.getProperty("os.name").lowercase()
}