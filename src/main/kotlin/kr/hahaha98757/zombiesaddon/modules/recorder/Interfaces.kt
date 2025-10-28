package kr.hahaha98757.zombiesaddon.modules.recorder

interface ISplitData {
    fun toJson(): String
}

interface Indexable<T> {
    operator fun get(round: Int): T
    operator fun set(round: Int, ticks: T)
}