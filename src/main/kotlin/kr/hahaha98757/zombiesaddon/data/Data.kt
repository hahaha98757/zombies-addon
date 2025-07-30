package kr.hahaha98757.zombiesaddon.data

import kr.hahaha98757.zombiesaddon.enums.Prefix

class Round(val waves: Array<Wave>)

class Wave(val ticks: Int, val prefixes: Array<Prefix>) {
    @Suppress("unused")
    constructor(ticks: Int) : this(ticks, emptyArray())
}

@JvmInline
value class ServerNumber(private val value: String) {
    override fun toString() = value
}