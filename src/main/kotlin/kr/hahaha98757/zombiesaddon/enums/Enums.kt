package kr.hahaha98757.zombiesaddon.enums

enum class Map {
    DEAD_END, BAD_BLOOD, ALIEN_ARCADIUM, PRISON;

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