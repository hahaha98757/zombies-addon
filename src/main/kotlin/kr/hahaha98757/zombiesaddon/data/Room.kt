package kr.hahaha98757.zombiesaddon.data

import kr.hahaha98757.zombiesaddon.ZombiesAddon

class Room(val name: String, private val alias: String, val windows: Array<Window>) {
    var activeWindows = 0

    override fun toString(): String {
        if (!ZombiesAddon.instance.config.slaActivatedWindows && !ZombiesAddon.instance.config.slaUnactivatedWindows) return "${color('6')}$name${color('e')}: ${color('d')}$activeWindows"
        val strBuilder = StringBuilder("${color('6')}$name${color('d')} $activeWindows${color('e')}:")
        for (window in windows)
            if (!window.active && ZombiesAddon.instance.config.slaUnactivatedWindows) strBuilder.append("${color('c')} $alias${window.id}")
            else if (window.active && ZombiesAddon.instance.config.slaActivatedWindows) strBuilder.append("${color('2')} $alias${window.id}")
        return strBuilder.toString()
    }

    private fun color(code: Char): String = if (ZombiesAddon.instance.config.slaTextColor) "§$code" else ""
}