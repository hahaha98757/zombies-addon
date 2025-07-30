package kr.hahaha98757.zombiesaddon.enums

enum class ZombiesMap {
    DEAD_END, BAD_BLOOD, ALIEN_ARCADIUM, PRISON;

    fun getNormalGameMode() = when (this) {
        DEAD_END -> GameMode.DEAD_END_NORMAL
        BAD_BLOOD -> GameMode.BAD_BLOOD_NORMAL
        ALIEN_ARCADIUM -> GameMode.ALIEN_ARCADIUM
        PRISON -> GameMode.PRISON_NORMAL
    }

    override fun toString(): String {
        return when (this) {
            DEAD_END -> "Dead End"
            BAD_BLOOD -> "Bad Blood"
            ALIEN_ARCADIUM -> "Alien Arcadium"
            PRISON -> "Prison"
        }
    }
}

enum class Difficulty {
    NORMAL, HARD, RIP
}

enum class Status {
    SURVIVE, REVIVE, DEAD, QUIT
}