package kr.hahaha98757.zombiesaddon.modules.recorder.files

import kr.hahaha98757.zombiesaddon.enums.GameMode
import kr.hahaha98757.zombiesaddon.modules.recorder.Indexable
import kr.hahaha98757.zombiesaddon.modules.recorder.readOrCreate
import kr.hahaha98757.zombiesaddon.modules.recorder.writeData
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.logger
import java.io.File

class CategoryFile(category: File, val gameMode: GameMode): File(category, "${gameMode.toStringForRecorder()}.times") {
    private val data = readOrCreate()

    val bestSegments = object: Indexable<Int> {
        override operator fun get(round: Int) = data.bestSegments[round - 1].toInt()
        override operator fun set(round: Int, ticks: Int) {
            data.bestSegments[round - 1] = ticks.toShort()

            runCatching { writeData(data) }.onFailure {
                logger.error("구간 최고 기록을 저장하는데 실패했습니다.", it)
                addTranslatedChat("zombiesaddon.modules.recorder.failed.save.bestSegment")
            }
        }
    }

    val personalBests = object: Indexable<Int> {
        override operator fun get(round: Int) = data.personalBests[round - 1]
        override operator fun set(round: Int, ticks: Int) {
            data.personalBests[round - 1] = ticks

            runCatching { writeData(data) }.onFailure {
                logger.error("개인 최고 기록을 저장하는데 실패했습니다.", it)
                addTranslatedChat("zombiesaddon.modules.recorder.failed.save.pb")
            }
        }
    }
}