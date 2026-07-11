package kr.hahaha98757.zombiesaddon.data

import kr.hahaha98757.zombiesaddon.config.ZAConfig

class Room(val name: String, private val alias: String, val windows: Array<Window>) {
    var activeWindows = 0

    override fun toString(): String {
        if (!ZAConfig.slaActivatedWindows && !ZAConfig.slaUnactivatedWindows) return "${color('6')}$name${color('e')}: ${color('d')}$activeWindows"
        val strBuilder = StringBuilder("${color('6')}$name${color('d')} $activeWindows${color('e')}:")
        for (window in windows)
            if (!window.active && ZAConfig.slaUnactivatedWindows) strBuilder.append("${color('c')} $alias${window.id}")
            else if (window.active && ZAConfig.slaActivatedWindows) strBuilder.append("${color('2')} $alias${window.id}")
        return strBuilder.toString()
    }

    private fun color(code: Char): String = if (ZAConfig.slaTextColor) "§$code" else ""
}