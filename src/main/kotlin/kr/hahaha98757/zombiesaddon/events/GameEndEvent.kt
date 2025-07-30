package kr.hahaha98757.zombiesaddon.events

import kr.hahaha98757.zombiesaddon.Game
import net.minecraftforge.fml.common.eventhandler.Event

class GameEndEvent(val game: Game, val isWin: Boolean): Event()