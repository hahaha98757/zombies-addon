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

enum class TextStyle(val text: String) {
    NEW_COLON("W1: 0:10.0"), NEW("W1 0:10.0"), OLD_COLON("W1: 10:00"), OLD("W1 10:00");

    override fun toString() = text

    companion object {
        fun fromText(text: String) = entries.firstOrNull { it.text == text } ?: NEW_COLON
        val arrays get() = entries.map { it.text }.toTypedArray()
    }
}

enum class HighlightStyle(val text: String) {
    ZOMBIES_ADDON("Zombies Addon"), ZOMBIES_UTILS("Zombies Utils");

    override fun toString() = text

    companion object {
        fun fromText(text: String) = entries.firstOrNull { it.text == text } ?: ZOMBIES_ADDON
        val arrays get() = entries.map { it.text }.toTypedArray()
    }
}

enum class Language(val text: String) {
    AUTO("Auto"),
    KO_KR("한국어 (한국)"),
    EN_US("English (US)");

    override fun toString() = text

    companion object {
        fun fromText(text: String) = entries.firstOrNull { it.text == text } ?: AUTO
        val arrays get() = entries.map { it.text }.toTypedArray()
    }
}