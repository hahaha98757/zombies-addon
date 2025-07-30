package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.events.GameEndEvent
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraft.entity.item.EntityArmorStand
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent

class PowerupPatterns: Module("Powerup Patterns", ZombiesAddon.instance.config.togglePowerupPatterns) {
    companion object {
        val instance = PowerupPatterns()
    }
    private val insPatternArr1 = intArrayOf(2, 5, 8, 11, 14, 17, 20, 23)
    private val insPatternArr2 = intArrayOf(3, 6, 9, 12, 15, 18, 21, 24)

    private val maxPatternArr1 = intArrayOf(2, 5, 8, 12, 16, 21, 26, 31, 36, 41, 46, 51, 61, 66, 71, 76, 81, 86, 91, 96)
    private val maxPatternArr2 = intArrayOf(3, 6, 9, 13, 17, 22, 27, 32, 37, 42, 47, 52, 62, 67, 72, 77, 82, 87, 92, 97, 102)

    private val ssPatternArr1 = intArrayOf(5, 15, 45, 55, 65, 75, 85, 95, 105)
    private val ssPatternArr2 = intArrayOf(6, 16, 26, 36, 46, 66, 76, 86, 96)
    private val ssPatternArr3 = intArrayOf(7, 17, 27, 37, 47, 67, 77, 87, 97)

    var queuedInsPattern = 0
    var queuedMaxPattern = 0
    var queuedSsPattern = 0

    var insTimer = false
        set(b) {
            field = b
            if (field) {
                ManualTimer.ins.runTimer()
                addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§cInsta Kill")
            }
        }
    var maxTimer = false
        set(b) {
            field = b
            if (field) {
                ManualTimer.max.runTimer()
                addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§9Max Ammo")
            }
        }
    var ssTimer = false
        set(b) {
            field = b
            if (field) {
                ManualTimer.ss.runTimer()
                addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§5Shopping Spree")
            }
        }
    var dgTimer = false
        set(b) {
            field = b
            if (field) {
                ManualTimer.dg.runTimer()
                addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§6Double Gold")
            }
        }
    var carTimer = false
        set(b) {
            field = b
            if (field) {
                ManualTimer.car.runTimer()
                addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§9Carpenter")
            }
        }
    var bgTimer = false
        set(b) {
            field = b
            if (field) {
                ManualTimer.bg.runTimer()
                addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§6Bonus Gold")
            }
        }

    private val spawnedEntities = mutableSetOf<EntityArmorStand>()
    private var insPattern = 0
    private var maxPattern = 0
    private var ssPattern = 0

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotPlayZombies()) return
        drawTimers()
        drawPatterns()
    }

    private fun drawTimers() {
        if (insTimer) {
            val timer = ManualTimer.ins.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.ins")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(1), 0xffffff)
            if (timer <= 0) insTimer = false
        }
        if (maxTimer) {
            val timer = ManualTimer.max.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.max")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(2), 0xffffff)
            if (timer <= 0) maxTimer = false
        }
        if (ssTimer) {
            val timer = ManualTimer.ss.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.ss")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(3), 0xffffff)
            if (timer <= 0) ssTimer = false
        }
        if (dgTimer) {
            val timer = ManualTimer.dg.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.dg")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(4), 0xffffff)
            if (timer <= 0) dgTimer = false
        }
        if (carTimer) {
            val timer = ManualTimer.car.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.car")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(5), 0xffffff)
            if (timer <= 0) carTimer = false
        }
        if (bgTimer) {
            val timer = ManualTimer.bg.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.bg")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(6), 0xffffff)
            if (timer <= 0) bgTimer = false
        }
    }

    private fun drawPatterns() {
        val game = ZombiesAddon.instance.gameManager.game ?: return
        val round = if (game.gameEnd) 0 else game.round
        val map = game.gameMode.map

        if (insPattern != 0) {
            var patternRound = 0
            if (insPattern == 2) {
                for (i in insPatternArr1) if (round <= i) {
                    if (map == ZombiesMap.DEAD_END && i == 5) {
                        patternRound = 8
                        break
                    }
                    if (map != ZombiesMap.ALIEN_ARCADIUM && i == 20) {
                        patternRound = 23
                        break
                    }
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawIns(patternRound)
            } else if (insPattern == 3) {
                for (i in insPatternArr2) if (round <= i) {
                    if (map != ZombiesMap.ALIEN_ARCADIUM && i == 15) {
                        patternRound = 18
                        break
                    }
                    if (map != ZombiesMap.ALIEN_ARCADIUM || i != 24) patternRound = i
                    break
                }
                if (patternRound != 0) drawIns(patternRound)
            }
        }

        if (maxPattern != 0) {
            var patternRound = 0
            if (maxPattern == 2) {
                for (i in maxPatternArr1) if (round <= i) {
                    if (map == ZombiesMap.DEAD_END && i == 5) {
                        patternRound = 8
                        break
                    }
                    if (map != ZombiesMap.ALIEN_ARCADIUM && i > 30) break
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawMax(patternRound)
            } else if (maxPattern == 3) {
                for (i in maxPatternArr2) if (round <= i) {
                    if (map != ZombiesMap.ALIEN_ARCADIUM && i > 30) break
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawMax(patternRound)
            }
        }

        if (ssPattern != 0) {
            var patternRound = 0
            if (ssPattern == 5) {
                for (i in ssPatternArr1) if (round <= i) {
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawSs(patternRound)
            } else if (ssPattern == 6) {
                for (i in ssPatternArr2) if (round <= i) {
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawSs(patternRound)
            } else if (ssPattern == 7) {
                for (i in ssPatternArr3) if (round <= i) {
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawSs(patternRound)
            }
        }
    }

    override fun onRoundStart(event: RoundStartEvent) {
        if (event.game.round == 1) {
            spawnedEntities.clear()
            queuedInsPattern = 0
            queuedMaxPattern = 0
            queuedSsPattern = 0
        }

        insPattern = queuedInsPattern
        maxPattern = queuedMaxPattern
        ssPattern = queuedSsPattern
    }

    override fun onLastTick(event: LastClientTickEvent) {
        if (isNotPlayZombies()) return
        val game = ZombiesAddon.instance.gameManager.game ?: return
        if (game.gameEnd) return
        val round = game.round

        for (entity in mc.theWorld.loadedEntityList) {
            if (entity !is EntityArmorStand) continue
            if (entity in spawnedEntities) continue

            val name = getText(entity.name)

            when (name) {
                "INSTA KILL", "즉시 처치" -> {
                    @Suppress("DuplicatedCode")
                    spawnedEntities += entity
                    for (i in insPatternArr1) if (i == round) {
                        queuedInsPattern = 2
                        break
                    }
                    for (i in insPatternArr2) if (i == round) {
                        queuedInsPattern = 3
                        break
                    }
                }
                "MAX AMMO", "탄약 충전" -> {
                    spawnedEntities += entity
                    for (i in maxPatternArr1) if (i == round) {
                        queuedMaxPattern = 2
                        break
                    }
                    for (i in maxPatternArr2) if (i == round) {
                        queuedMaxPattern = 3
                        break
                    }
                }
                "SHOPPING SPREE", "지름신 강림" -> {
                    spawnedEntities += entity
                    for (i in ssPatternArr1) if (i == round) {
                        queuedSsPattern = 5
                        break
                    }
                    for (i in ssPatternArr2) if (i == round) {
                        queuedSsPattern = 6
                        break
                    }
                    for (i in ssPatternArr3) if (i == round) {
                        queuedSsPattern = 7
                        break
                    }
                }
            }
        }
    }

    private fun drawIns(pattern: Int) {
        if (insTimer) return
        val str = "${getTranslatedString("zombiesaddon.game.ins")}: ${getTranslatedString("zombiesaddon.game.round", true, pattern)}"
        fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(1), 0xffffff)
    }

    private fun drawMax(pattern: Int) {
        if (maxTimer) return
        val str = "${getTranslatedString("zombiesaddon.game.max")}: ${getTranslatedString("zombiesaddon.game.round", true, pattern)}"
        fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(2), 0xffffff)
    }

    private fun drawSs(pattern: Int) {
        if (ssTimer) return
        val str = "${getTranslatedString("zombiesaddon.game.ss")}: ${getTranslatedString("zombiesaddon.game.round", true, pattern)}"
        fr.drawStringWithShadow(str, HudUtils.getPowerupPatternsStrX(str), HudUtils.getPowerupPatternsStrY(3), 0xffffff)
    }

    override fun onGameEnd(event: GameEndEvent) {
        insTimer = false
        maxTimer = false
        ssTimer = false
        dgTimer = false
        carTimer = false
        bgTimer = false
    }

    override fun onKeyInput(event: InputEvent.KeyInputEvent) {
        val keys = ZombiesAddon.instance.keyBindings
        if (keys.insTimer.isPressed) insTimer = true
        if (keys.maxTimer.isPressed) maxTimer = true
        if (keys.ssTimer.isPressed) ssTimer = true
        if (keys.dgTimer.isPressed) dgTimer = true
        if (keys.carTimer.isPressed) carTimer = true
        if (keys.bgTimer.isPressed) bgTimer = true
    }

    override fun onChat(event: ClientChatReceivedEvent) {
        val message = getText(event.message.unformattedText)
        if (">" in message) return

        if ("Insta Kill" in message || "즉시 처치" in message) insTimer = false
        if ("Max Ammo" in message || "탄약 보급" in message) maxTimer = false
        if ("Shopping Spree" in message || "지름신 강림" in message) ssTimer = false
        if ("Double Gold" in message || "더블 골드" in message) dgTimer = false
        if ("Carpenter" in message || "목수" in message) carTimer = false
        if ("Bonus Gold" in message || "보너스 골드" in message) bgTimer = false
    }
}

class ManualTimer {
    companion object {
        val ins = ManualTimer()
        val max = ManualTimer()
        val ss = ManualTimer()
        val dg = ManualTimer()
        val car = ManualTimer()
        val bg = ManualTimer()
    }

    var timer = 1200

    fun runTimer() {
        timer = 1200
        MinecraftForge.EVENT_BUS.register(this)
    }

    @Suppress("unused")
    @SubscribeEvent
    fun onTick(event: LastClientTickEvent) {
        if (--timer <= 0) MinecraftForge.EVENT_BUS.register(this)
    }
}