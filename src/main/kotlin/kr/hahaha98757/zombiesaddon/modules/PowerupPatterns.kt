package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Map
import kr.hahaha98757.zombiesaddon.events.LastClientTickEvent
import kr.hahaha98757.zombiesaddon.events.SoundEvent
import kr.hahaha98757.zombiesaddon.events.TitleEvent
import kr.hahaha98757.zombiesaddon.utils.*
import net.minecraft.entity.item.EntityArmorStand
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent

class PowerupPatterns: Module("Powerup Patterns") {
    companion object {
        val instance = PowerupPatterns()
    }

    private val insPattern1 = intArrayOf(2, 5, 8, 11, 14, 17, 20, 23)
    private val insPattern2 = intArrayOf(3, 6, 9, 12, 15, 18, 21, 24)

    private val maxPattern1 = intArrayOf(2, 5, 8, 12, 16, 21, 26, 31, 36, 41, 46, 51, 61, 66, 71, 76, 81, 86, 91, 96)
    private val maxPattern2 = intArrayOf(3, 6, 9, 13, 17, 22, 27, 32, 37, 42, 47, 52, 62, 67, 72, 77, 82, 87, 92, 97, 102)

    private val ssPattern1 = intArrayOf(5, 15, 45, 55, 65, 75, 85, 95, 105)
    private val ssPattern2 = intArrayOf(6, 16, 26, 36, 46, 66, 76, 86, 96)
    private val ssPattern3 = intArrayOf(7, 17, 27, 37, 47, 67, 77, 87, 97)

    var rawInsPattern = 0
    var rawMaxPattern = 0
    var rawSSPattern = 0

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
    private var gameEnd = false
    private var insPattern = 0
    private var maxPattern = 0
    private var ssPattern = 0

    override fun onRender(event: RenderGameOverlayEvent.Text) {
        if (isNotZombies()) return
        drawTimers()
        drawPatterns()
    }

    private fun drawTimers() {
        if (insTimer) {
            val timer = ManualTimer.ins.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.ins")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(1), 0xffffff)
            if (timer <= 0) insTimer = false
        }
        if (maxTimer) {
            val timer = ManualTimer.max.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.max")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(2), 0xffffff)
            if (timer <= 0) maxTimer = false
        }
        if (ssTimer) {
            val timer = ManualTimer.ss.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.ss")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(3), 0xffffff)
            if (timer <= 0) ssTimer = false
        }
        if (dgTimer) {
            val timer = ManualTimer.dg.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.dg")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(4), 0xffffff)
            if (timer <= 0) dgTimer = false
        }
        if (carTimer) {
            val timer = ManualTimer.car.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.car")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(5), 0xffffff)
            if (timer <= 0) carTimer = false
        }
        if (bgTimer) {
            val timer = ManualTimer.bg.timer
            val second = timer / 20
            val timerText = String.format("%02d", second)

            val str = "${getTranslatedString("zombiesaddon.game.bg")}: §f${timerText}s"
            fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(6), 0xffffff)
            if (timer <= 0) bgTimer = false
        }
    }

    private fun drawPatterns() {
        val round = getRound()

        if (insPattern != 0) {
            var patternRound = 0
            if (insPattern == 2) {
                for (i in insPattern1) if (round <= i) {
                    if (getMap() == Map.DEAD_END && i == 5) {
                        patternRound = 8
                        break
                    }
                    if (getMap() != Map.ALIEN_ARCADIUM && i == 20) {
                        patternRound = 23
                        break
                    }
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawIns(patternRound)
            } else if (insPattern == 3) {
                for (i in insPattern2) if (round <= i) {
                    if (getMap() != Map.ALIEN_ARCADIUM && i == 15) {
                        patternRound = 18
                        break
                    }
                    if (getMap() != Map.ALIEN_ARCADIUM || i != 24) patternRound = i
                    break
                }
                if (patternRound != 0) drawIns(patternRound)
            }
        }

        if (maxPattern != 0) {
            var patternRound = 0
            if (maxPattern == 2) {
                for (i in maxPattern1) if (round <= i) {
                    if (getMap() == Map.DEAD_END && i == 5) {
                        patternRound = 8
                        break
                    }
                    if (getMap() != Map.ALIEN_ARCADIUM && i > 30) break
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawMax(patternRound)
            } else if (maxPattern == 3) {
                for (i in maxPattern2) if (round <= i) {
                    if (getMap() != Map.ALIEN_ARCADIUM && i > 30) break
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawMax(patternRound)
            }
        }

        if (ssPattern != 0) {
            var patternRound = 0
            if (ssPattern == 5) {
                for (i in ssPattern1) if (round <= i) {
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawSS(patternRound)
            } else if (ssPattern == 6) {
                for (i in ssPattern2) if (round <= i) {
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawSS(patternRound)
            } else if (ssPattern == 7) {
                for (i in ssPattern3) if (round <= i) {
                    patternRound = i
                    break
                }
                if (patternRound != 0) drawSS(patternRound)
            }
        }
    }

    override fun onTitle(event: TitleEvent) {
        val title = event.title
        if (!isRoundText(title)) return

        if (title.replace(Regex("[^0-9]"), "").toInt() == 1) {
            gameEnd = false
            spawnedEntities.clear()
            rawInsPattern = 0
            rawMaxPattern = 0
            rawSSPattern = 0
        }

        insPattern = rawInsPattern
        maxPattern = rawMaxPattern
        ssPattern = rawSSPattern
    }

    override fun onLastTick(event: LastClientTickEvent) {
        if (isNotZombies()) return
        if (gameEnd) return

        for (entity in mc.theWorld.loadedEntityList) {
            if (entity !is EntityArmorStand) continue
            if (entity in spawnedEntities) continue

            val name = getText(entity.name)

            when (name) {
                "INSTA KILL", "즉시 처치" -> {
                    spawnedEntities += entity
                    val round = getRound()
                    for (i in insPattern1) if (i == round) {
                        rawInsPattern = 2
                        break
                    }
                    for (i in insPattern2) if (i == round) {
                        rawInsPattern = 3
                        break
                    }
                }
                "MAX AMMO", "탄약 충전" -> {
                    spawnedEntities += entity
                    val round = getRound()
                    for (i in maxPattern1) if (i == round) {
                        rawMaxPattern = 2
                        break
                    }
                    for (i in maxPattern2) if (i == round) {
                        rawMaxPattern = 3
                        break
                    }
                }
                "SHOPPING SPREE", "지름신 강림" -> {
                    spawnedEntities += entity
                    val round = getRound()
                    for (i in ssPattern1) if (i == round) {
                        rawSSPattern = 5
                        break
                    }
                    for (i in ssPattern2) if (i == round) {
                        rawSSPattern = 6
                        break
                    }
                    for (i in ssPattern3) if (i == round) {
                        rawSSPattern = 7
                        break
                    }
                }
            }
        }
    }

    private fun drawIns(pattern: Int) {
        if (insTimer) return
        val str = "${getTranslatedString("zombiesaddon.game.ins")}: ${getTranslatedString("zombiesaddon.game.round", true, pattern)}"
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(1), 0xffffff)
    }

    private fun drawMax(pattern: Int) {
        if (maxTimer) return
        val str = "${getTranslatedString("zombiesaddon.game.max")}: ${getTranslatedString("zombiesaddon.game.round", true, pattern)}"
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(2), 0xffffff)
    }

    private fun drawSS(pattern: Int) {
        if (ssTimer) return
        val str = "${getTranslatedString("zombiesaddon.game.ss")}: ${getTranslatedString("zombiesaddon.game.round", true, pattern)}"
        fr.drawStringWithShadow(str, HUDUtils.getPowerupPatternsStrX(str), HUDUtils.getPowerupPatternsStrY(3), 0xffffff)
    }

    override fun onSound(event: SoundEvent) {
        if (event.sound != "minecraft:mob.enderdragon.end") return
        gameEnd = true
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

    override fun isEnable() = ZombiesAddon.instance.config.togglePowerupPatterns
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

    @SubscribeEvent
    fun onTick(@Suppress("unused") event: LastClientTickEvent) {
        if (--timer <= 0) MinecraftForge.EVENT_BUS.register(this)
    }
}