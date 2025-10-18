package kr.hahaha98757.zombiesaddon.modules.recorder.files

import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.modules.recorder.Indexable
import kr.hahaha98757.zombiesaddon.modules.recorder.createData
import kr.hahaha98757.zombiesaddon.modules.recorder.data.GameData
import kr.hahaha98757.zombiesaddon.modules.recorder.writeData
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.logger
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class GameFile(serverNumber: ServerNumber, map: ZombiesMap): File("zombies/runs", "${formattedTime}_$serverNumber.seg") {
    private val data = GameData(map)
    init {
        createData(data)
    }

    val segments = object: Indexable<Int> {
        override fun get(round: Int) = data.segments[round - 1].toInt()

        override operator fun set(round: Int, ticks: Int) {
            data.segments[round - 1] = ticks.toShort()

            runCatching { writeData(data) }.onFailure {
                logger.error("구간 기록을 저장하는데 실패했습니다.", it)
                addTranslatedChat("zombiesaddon.modules.recorder.failed.save.segment")
            }
        }
    }


}

private val formattedTime get() = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace(":", "-").replaceFirst("T", "_")