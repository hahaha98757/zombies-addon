package kr.hahaha98757.zombiesaddon.data

class Wave(val ticks: Short, val prefixes: Array<Prefix>) {
    @Suppress("unused")
    constructor(ticks: Short) : this(ticks, emptyArray())
}