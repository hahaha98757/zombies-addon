![Zombies Addon Logo](src/main/resources/logo.png)
# Zombies Addon
The mod for Hypixel Zombies.

Supported language: [English (US)](README.md), [한국어 (대한민국)](README-ko_KR.md)

## Modules
\* means that Hypixel language must be set to English or Korean for working.

### Player Visibility
Hides nearby players from your view.
<br>There is Semi PV that makes the players appear semi-transparent from your view.
<br>Toggle with the Z key.
<br>You can set the range in the config.
<br>A semi-transparent of Player Invisible in SST isn't worked.
<br>You can set to hide toggle text in the config.

### Block Alarm*
Displays "BLOCK" on the screen when you survived alone and there is a player you can revive.
<br>Toggle with the P key.
<br>You can set to hide toggle text in the config.

### Not Last
Displays the player who killed the last one.

### Auto Splits
Sends a startorsplit signal to LiveSplits at start of the round.
<br>You can set the IP and port of LiveSplits in the config.

### Internal Timer
Runs a timer at start of the round.
<br>You can set the position of timer with /zombiesaddon hud command.

### Wave Delays*
Displays the wave delays.
<br>Displays characteristics of the wave through prefixes.
<br>You can set the many options in the config.
<br>You can set the position of wave delays with /zombiesaddon hud command.
<br>You can set the difficult and offset with /wavedelays command.
<br>You can set the Custom Play Sound. See ".minecraft\config\zombiesaddon\Custom Play Sound Guide.txt" file for details.

Displays the wave which the boss spawns.
| Color | Boss name |
|----|----|
| Gold | Bombie or Warden |
| Red | Inferno, Angry Prisoner, or Corrupted Pigman |
| Dark Purple | The Broodmother, Wither, or Herobrine |
| Red and Aqua | Lily and Ellie |
| Green | King Slime or Mega Blob |
| Dark Aqua | (Rainbow) Giant |
| Dark Red | The Old One or Mega Magma |
| Dark Aqua and Dark Red | (Rainbow) Giant and The Old One |
| Black | World Ender |

### Zombies Strat Viewer
Displays the text in https://pastebin.com/ on the screen.
<br>Use /zsv <URL|off> command to load or close the text. URL must start with "https://pastebin.com/raw/".
<br>Use /zsvlines <number> command to set lines of view.

### Spawn Limit Action
Displays the number of windows where zombies can spawn.
<br>Use /sla <de|bb|aa|off> command to set the map or off SLA.
<br>You can set the SLA for custom map(e.g. Housing) with /sla custom <offset|rotate|mirror> command.
<br>You can set the many options in the config.

### Auto Rejoin
Automatically rejoin Zombies when start of the round.
<br>You can set to hide toggle text in the config.

### Powerup Patterns*
Displays the patterns of power-ups.
<br>Use /pow <insta|max|ss> [reset|number|on|off] command to reset or set the pattern.
<br>The displayed power-up pattern will be updated in the next round.
<br>For example, if Insta Kill spawned in round 2, Insta Kill pattern will be displayed on the screen when round 3 starts.
<br>Use hotkeys or /pow <insta|max|ss|dg|carpenter|bg> command to run timer.

### Last Weapons
Displays your weapons and armors when you win.
<br>Displays level of your weapons when you win.
<br>You can set the many options in the config.

### Text Macro
Sends the text on chat when press the Q key.
<br>You can set the text in the config.

### Korean Patchers*
Translated some phrases in Zombies.
<br>Translated some phrases in SST.
<br>You can use [Zombies Overlay](https://github.com/TheExploration/zombies-overlay) even if the Hypixel language is set to Korean.

### Other Mods
You can turn off spawn time of SST in the config.
<br>You can turn off timer of Zombies Utils in the config.

### Other
You can't start the game when you are using unlegit mods.(ZombiesSatellite, Zombies Explorer, TeammatesOutline, and ZombiesHelper)
<br>Blocks the unlegit features of SST.
<br>Changed /sla command of Zombies Utils to /sla_zombiesutils
<br>Use /de, /bb, /aa, or /pr commands to join each map.
<br>Supports automatic updates for Windows only, limited to the recommended version.


## License
This project is licensed under the MIT License as specified in the [LICENSE](LICENSE) file.<br>
It allows for personal use, modification, distribution, and commercial use under the license and copyright notice, and it comes with no warranty or liability.

## Credits
- [Architectury Pack200](https://github.com/architectury/architectury-pack200) by architectury ([GNU General Public License v2.0 with Classpath Exception](https://github.com/architectury/architectury-pack200/blob/master/LICENSE))
- [DevAuth](https://github.com/DJtheRedstoner/DevAuth) by DJtheRedstoner ([MIT License](https://github.com/DJtheRedstoner/DevAuth/blob/master/LICENSE))
- [Essential Loom](https://github.com/EssentialGG/architectury-loom) by EssentialGG ([MIT License](https://github.com/EssentialGG/architectury-loom/blob/dev/1.6/LICENSE))
- [Gson](https://github.com/google/gson) by google ([Apache License 2.0](https://github.com/google/gson/blob/main/LICENSE))
- [Kotlin](https://github.com/JetBrains/kotlin) by JetBrains ([Apache License 2.0](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt))
- [Mixin](https://github.com/SpongePowered/Mixin) by SpongePowered ([MIT License](https://github.com/SpongePowered/Mixin/blob/master/LICENSE.txt))
- [Show Spawn Time](https://github.com/Seosean/ShowSpawnTime/tree/1.15.0) by Seosean ([MIT License](licenses/Show%20Spawn%20Time-LICENSE))
- [Zombies AutoSplits](https://github.com/tahmid-23/ZombiesAutoSplits) by tahmid-23 ([MIT License](https://github.com/tahmid-23/ZombiesAutoSplits/blob/main/LICENSE))
- [Zombies Cornering Mod](https://github.com/cyoung06/ZombiesCorneringMod) by syeyoung ([MIT License](licenses/Zombies%20Cornering%20Mod-LICENSE))
- [Zombies Strat Viewer](https://github.com/cyoung06/ZombiesStratViewer) by syeyoung ([MIT License](licenses/Zombies%20Strat%20Viewer-LICENSE))
- [Zombies Utils](https://github.com/Stachelbeere1248/zombies-utils) by Stachelbeere1248 ([MIT License](https://github.com/Stachelbeere1248/zombies-utils/blob/master/LICENSE))

Zombies Hologrambug Fixer, Zombies Cornering Mod, and Zombies Strat Viewer was originally licensed under the GNU Lesser General Public License v2.1, but the author, syeyoung, granted permission to license it under the MIT license.<br>
Show Spawn Time has MIT license in the LICENSE.txt file inside ShowSpawnTime-1.15.0.jar.

****

## Update Log

### 4.4.0-pre1
- Changed Java to Kotlin.
- Fixed bugs.
- Code reorganization.
- Fixed config: Translated option names and descriptions.
- Fixed Detect Unlegit Mods: Apprise it with GUI., Added a feature that automatic deletion the mods.(Windows only)
- Fixed Update Checker: Apprise recommended version and required updates with GUI, Added auto update.(Windows only)
- Changed version system.
- Fixed Player Visibility: Can be set semi-transparent range, It works only ingame.
- Fixed Block Alarm: Does not work when not alive, Change the default key to P.
- Fixed Auto Splits: Changed to for LiveSplits only.
- Added Internal Timer: Same functionality as the existing Auto Splits.
- Fixed Wave Delays: Fixed prefixes for Giant and The Old One.
- Fixed ZSV: Korean language support.
- Fixed SLA: Added option to remove window name and text color.


### 4.3.2
- Reduces excessive allocations

### 4.3.1
- Fixed bugs.

### 4.3.0
- No changes.

### 4.3.0-pre3
- Fixed bugs.
- Added /de, /bb, /aa, and /pr commands.
- Fixed Last Weapons.

### 4.3.0-pre2
- Fixed bugs.
- Fixed Wave Delays.
- Fixed Powerup Patterns.

### 4.3.0-pre1
- Fixed bugs.
- Added commands.
- Added Language option in config.


### 4.2.3
- Fixed bugs.

### 4.2.2
- Fixed bugs.
- Added /zombiesaddon command.
- Removed /info command.

### 4.2.1
- Fixed bugs.

### 4.2.0
- Fixed bugs.

### 4.2.0-pre6
- Fixed bugs.
- Fixed Last Weapons.

### 4.2.0-pre5
- Fixed bugs.

### 4.2.0-pre4
- Fixed bugs.
- Added /waveDelays and /autoSplits commands.
- Blocks the unlegit feature of SST.

### 4.2.0-pre3
- Fixed bugs.
- Multiple languages are not supported.(Only English or Korean.)

### 4.2.0-pre2
- Fixed bugs.

### 4.2.0-pre1
- Code optimization.
- Fixed bugs.
- Renamed Cornering to PV.
- Fixed PV.
- Added Manual Timer for Powerup Patterns.
- Fixed SLA.
- Added /ZAHUD command.
- Fixed Wave Delays.
- Added SST Setting and Zombies Utils Setting.
- Supported multiple languages.


### 4.1.3
- Fixed bugs.


### 4.1.2
- Fixed bugs.


### 4.1.1
- Fixed bugs.
- Fixed Auto Rejoin.


### 4.1.0
- Fixed Last Weapons.

### 4.1.0-pre6
- Fixed bugs.
- Fixed Powerup Patterns.

### 4.1.0-pre5
- Fixed Last Weapons.
- Fixed bugs.

### 4.1.0-pre4
- Fixed a bug.

### 4.1.0-pre3
- Added Text Macro.
- Code optimization.
- Fixed Last Weapons.

### 4.1.0-pre2
- Fixed bugs.

### 4.1.0-pre1
- Fixed bugs.
- Fixed Powerup Alarm.
- Added SST Patch.
- Added Korean Patch.
- Added Last Weapons.
- Added Detect Unlegit Mods.


### 4.0.1
- Fixed bugs.
- Fixed SLA.
- Fixed Powerup Alarm.
- Fixed Wave Delays.


### 4.0.0
- Removed DPS Counter.
- Removed Player Tracker.
- Removed Boss Tracker.
- Removed Gstep Guide.
- Fixed Powerup Alarm.


### 3.1.0-pre8
- Fixed bugs.
- Fixed Wave Delays.
- Fixed Boss Tracker.
- Fixed Player Tracker.
- Removed Grow ESP.
- Removed Grow Guide.
- Removed Boss Spawn Tracker.

### 3.1.0-pre7
- Fixed bugs.
- Fixed Wave Delays.

### 3.1.0-pre6
- Fixed bugs.
- Fixed Wave Delays.
- Fixed SLA.

### 3.1.0-pre5
- Fixed bugs.

### 3.1.0-pre4
- Fixed bugs.

### 3.1.0-pre3
- Fixed bugs.
- Fixed SLA.

### 3.1.0-pre2
- Fixed bugs.

### 3.1.0-pre1
- Added Prison at Wave Delays.
- Boss Wave Alarm, SLA, Boss Spawn Tracker, Boss Tracker is not work in Prison.


### 3.0.0
- Fixed bug.
- Fixed a bug that Boss Tracker did display non-boss mobs.


### 2.0.0
- Fixed bug.
- Renamed Boss Alarm.
- Fixed a bug that some functions did not work when the nickname contained "_".


### 1.17.2-pre1
- Fixed bug.
- Added Player Tracker.
- Added Boss Tracker.

### 1.17.1
- Fixed bugs.

### 1.17.0
- Fixed bugs.

### 1.17.0-pre4
- Fixed bugs.
- Added config option.
- Removed /updatelog command.

### 1.17.0-pre3
- Fixed bugs.

### 1.17.0-pre2
- Fixed bugs.

### 1.17.0-pre1
- Fixed bugs.
- Fixed Auto Splits.
- Renamed NOTLAST.
- Fixed config.
- Fixed Wave Delays.
- Fixed Not Last.


### 1.16.0
- Fixed bugs.
- Renamed Extra Setting.

### 1.16.0-pre2
- Fixed bugs.

### 1.16.0-pre1
- Fixed bugs.
- Removed Lrod Order.
- Added RL-mode for Auto Splits.
- Fixed Update Checker.
- Added Extra Setting.
- Fixed Wave Delays.
- Removed config for Powerup Alarm.


### 1.15.1
- Fixed bugs.
- Removed Countdown.

### 1.15.0
- Fixed bugs.
- Added Play Sound for Auto Splits.


### 1.14.0
- Fixed bugs.
- Timer of Zombies Utils is off when use with Zombies Utils.
- Removed Health Indicator for Block Alarm.

### 1.14.0-pre4
- Fixed bugs.

### 1.14.0-pre3
- Fixed bugs.

### 1.14.0-pre2
- Fixed bugs.
- Fixed Gstep Guide.

### 1.14.0-pre1
- Added Gstep Guide.
- Fixed DPS Counter, NOTLAST, Auto Splits, and Powerup Alarm.
- Fixed bugs.


### 1.13.0
- Fixed bugs.
- Added Grow ESP.
- Wave delay of SST is off when use with SST.


### 1.12.3
- Fixed bugs.
- Fixed update checker

### 1.12.2
- Fixed update checker.
- Fixed /info command.

### 1.12.1
- Removed Hologram Remover.
- Added update checker.

### 1.12.0
- Added Grow Guide for Auto Splits.
- Fixed bugs.
- Fixed config.
- Added mod version on the display.
- Config reset when mod update.
- Added option which default value of mod be set.


### 1.11.0
- Added Health Indicator for Block Alarm.
- Rename Hologram Bug Generator to Hologram Remover.
- Removed rev and dead for Block Alarm.


### 1.10.3
- Fixed bugs.

### 1.10.2
- Added Hologram Bug Generator.

### 1.10.1
- Fixed bugs.

### 1.10.0
- Changed package.
- Code Reorganization.
- Added Lrod Order.
- Added Advanced SLA.
- Fixed commands.


### 1.9.2
- Fixed bugs.

### 1.9.1
- Fixed bugs.

### 1.9.0
- Fixed bugs.
- Added Boss Alarm.
- Fixed forge command bug.


### 1.8.9
- Fixed bugs.

### 1.8.8
- Fixed bugs.
- Added Boss Wave Alarm for Auto Splits.

### 1.8.7
- Fixed bugs.

### 1.8.6
- Fixed bugs.
- Removed /zombies command.
- Added config for Zombies Overlay.
- Fixed Powerup Alarm.

### 1.8.5
- Fixed bugs.
- Fixed CommandInfo.
- Added /zombies Command for Zombies Overlay.

### 1.8.4
- Fixed bugs.
- Fixed Auto Splits.

### 1.8.3
- Fixed bugs.

### 1.8.2
- Fixed bugs.

### 1.8.1
- Fixed Powerup Alarm.
- Fixed bugs.
- Added config for SLA.

### 1.8.0
- Added Powerup Alarm.
- Fixed bugs.
- Added config for Zombies Addon.
- Fixed Auto Splits.


### 1.7.6
- Fixed bug.

### 1.7.5
- Fixed bug.

### 1.7.4
- Fixed bug.

### 1.7.3
- Fixed bug.

### 1.7.2
- Fixed bug.

### 1.7.1
- Fixed bug.

### 1.7.0
- Fixed Auto Splits.
- Fixed /ZSV and /SLA command.
- Fixed Auto Rejoin.
- Added config for Auto Splits.

### 1.6.5
- Fixed bug.

### 1.6.4
- Fixed bug.

### 1.6.3
- Fixed bug.

### 1.6.2
- Fixed NOTLAST and Auto Rejoin.
- Fixed bug.

### 1.6.1
- Fixed Block Alarm.

### 1.6.0
- Remake /setMap, /setstrat, and /setlines commands name to /SLA, /ZSV, and /ZSVLines.
- Remove /blockAlarm command.
- Added /cornering command.
- Added config for Cornering and Block Alarm.


### 1.5.2
- Fixed bug.

### 1.5.1
- Fixed bug.

### 1.5.0
- Added Auto Rejoin.
- Fixed bug.
- Fixed Auto Splits.


### 1.4.5
- Fixed Block Alarm.

### 1.4.4
- Fixed Block Alarm.

### 1.4.3
- Fixed bug.
- You can use Block Alarm in Korean.

### 1.4.2
- Fixed bug.
- Fixed Block Alarm.
- Added /blockAlarm command.

### 1.4.1
- Fixed mod's message.

### 1.4.0
- Added ZSV and SLA.


### 1.3.0
- Added Auto Splits.


### 1.2.1
- Added /updatelog command.
- Fixed /info command.

### 1.2.0
- Added NOTLAST.
- Fixed /info command.


### 1.1.0
- Removed ZHF.


### 1.0.0
- Added ZHF, Cornering, Block Alarm, and DPS Counter.
- Fixed Block Alarm.
