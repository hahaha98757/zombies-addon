package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.data.Round
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.enums.Map

object RoundData {
    private val roundData: Array<Array<Round>>
    init {
        roundData = arrayOf(
            readFromFile("data/rounds/DEN_WAVES.json"),
            readFromFile("data/rounds/DEH_WAVES.json"),
            readFromFile("data/rounds/DER_WAVES.json"),
            readFromFile("data/rounds/BBN_WAVES.json"),
            readFromFile("data/rounds/BBH_WAVES.json"),
            readFromFile("data/rounds/BBR_WAVES.json"),
            readFromFile("data/rounds/AA_WAVES.json"),
            readFromFile("data/rounds/PRN_WAVES.json"),
            readFromFile("data/rounds/PRH_WAVES.json"),
            readFromFile("data/rounds/PRR_WAVES.json")
        )
    }

    fun getRoundData(map: Map, difficulty: Difficulty, round: Int) = try {
        when (map) {
            Map.DEAD_END -> when (difficulty) {
                Difficulty.NORMAL -> roundData[0][round - 1]
                Difficulty.HARD -> roundData[1][round - 1]
                Difficulty.RIP -> roundData[2][round - 1]
            }
            Map.BAD_BLOOD -> when (difficulty) {
                Difficulty.NORMAL -> roundData[3][round - 1]
                Difficulty.HARD -> roundData[4][round - 1]
                Difficulty.RIP -> roundData[5][round - 1]
            }
            Map.ALIEN_ARCADIUM -> roundData[6][round - 1]
            Map.PRISON -> when (difficulty) {
                Difficulty.NORMAL -> roundData[7][round - 1]
                Difficulty.HARD -> roundData[8][round - 1]
                Difficulty.RIP -> roundData[9][round - 1]
            }
        }
    } catch (_: Exception) {
        null
    }

    private fun readFromFile(resourcePath: String): Array<Round> =
        JsonLoader.loadJsonFromResource(resourcePath, Array<Round>::class.java)
}