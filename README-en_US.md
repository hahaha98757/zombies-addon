![Zombies Addon Logo](src/main/resources/logo.png)
# Zombies Addon
Supported Languages: [한국어 (대한민국)](README.md), [English (US)](README-en_US.md)
<br>Translated by Google Translate.
- A mod that combines several Zombies mods, fixes minor bugs, and has new features.


# Modules
## Player Visibility
- Toggle with hotkey (default Z).
- Hides surrounding players from view.
- There is a Semi PV which makes the player appear semi-transparent.
- Applying this mod will cause SST's Player Invisible's player translucency to not work.
### Config
- Default Toggle State: Toggle state when the game starts.
- Displays Text: Displays texts with state of Player Visibility.
- Range: The range for Player Visibility in blocks.
- Toggle Semi PV: Enable or disable Semi Player Visibility.
- Semi PV Range: The range for Semi Player Visibility in blocks.


## Block Alarm
- Toggle with hotkey (default P).
- If there is a player who is alive and can be revived, "BLOCK" will appear on the screen.
### Config
- Default Toggle State: Toggle state when the game starts.
- Displays Text: Displays texts with state of Block Alarm.


## Not Last
- Displays the player who killed the last enemy.
- It works by finding the player with the highest kill count at the start of the next round. This can result in players not being found, or multiple players being detected.


## Auto Splits
- At the start of a round, pass a startorsplit signal to LiveSplit.
### Config
- Toggle Auto Splits: Enable or disable Auto Splits.
- Host: The host of LiveSplit server.
- Port: The port of LiveSplit server.
### Commands
- autosplits <signal>: LiveSplit에 원하는 신호를 전달합니다.
### Usage
1. Download LiveSplit [here](https://livesplit.org/downloads/).
2. Run LiveSplit, right-click → Settings, enter the desired number for Server Port between 0 and 65535 (default 16834), and click OK to close the window.
3. Launch Minecraft → Mods → Zombies Addon → Config → Auto Splits and enter the port value as the number you entered in step 2 (default 16834).
4. Right-click LiveSplit → Control → Start TCP Server to start the server.
5. When you turn on Auto Splits in-game, a signal is sent at the start of the round.


## Internal Timer
- Starts a timer at the start of a round.
### Commands
- internaltimer <run|stop>: Start or stop the timer with the command.


## Wave Delays
- Displays wave delay.
- Prefixes indicate the characteristics of the wave. In the case of bosses, they can also be distinguished by wave color.
- Text styles include the default "W1: 0.10.0", "W1 0.10.0", "W1: 00:10", and "W1 00:10".
- The highlight styles of Wave Delay are:
  <br>The default "Zombies Addon" will display waves that will start in 3 seconds in yellow, waves that have started in green, waves that have not started in dark gray, and the current wave is indicated by a purple arrow.
  <br>"Zombies Utils" is the wave delay equivalent of Zombies Utils.
- Toggle RL mode with the hotkey (default Up). The wave delay will be displayed with the set number of ticks added.
### Config
- Toggle Wave Delays: Enable or disable Wave Delays.
- Play Sounds: Plays a sound after n ticks of wave start.
- Custom Play Sound: Plays sounds with a sound name, pitch, and playType that you set in a json file.
- Prefix: Enable or disable the prefix.
- Boss Color: Enable or disable colors of waves where a boss spawns.
- Text Style: The style of texts that are displayed on the screen.
- Highlight Style: The Highlight method for a current wave, passed waves, and next waves.
- Hide Passed Wave: Enable or disable hiding passed waves.
- RL Mode Offset: The offset for RL Mode.
### Commands
- wavedelays: Set difficulty and offset.


## Zombies Strat Viewer
- Displays text from https://pastebin.com/ on the screen.
- You can raise or lower the displayed lines with the hotkeys (default left bracket, right bracket).
- Supports Unicode.
### Commands
- zsv <URL|off>: Show or hide text. URL must start with "https://pastebin.com/raw/".
- zsvlines <number>: Set the lines to display at one time.


## Spawn Limit Action
- Displays the number of windows in which enemies can spawn.
### Config
- Auto SLA: Turns on SLA when the game starts.
- Text Color: Enable or disable colors for SLA texts.
- Activated Windows: Displays activated windows in SLA.
- Unactivated Windows: Displays unactivated windows in SLA.
### Commands
- sla <de|bb|aa|off>: Turn the map on or off.
- sla custom <offset|rotate|mirror>: Adjust the position and orientation of the SLA for use in custom maps such as housing.
- sla quick <mogi_a|ghxula|ghxula-garden>: Adjust SLA to fit each user's housing.


## Auto Rejoin
- Toggle with hotkey (no default).
- You will be automatically reconnected when the round starts.
### Config
- Default Toggle State: Toggle state when the game starts.
- Displays Text: Displays texts with state of Auto Rejoin.


## Powerup Patterns
- Displays the pattern that power-ups spawn.
- Changes and detected patterns are reflected in the next round.
- For example, if an instant kill spawned in round 2, it won't be until round 3 starts that the instant kill pattern will appear on screen.
### Commands
- pow: This is a shortcut command for poweruppatterns.
- poweruppatterns <insta|max|ss> <reset|number>: Reset the pattern or set it yourself.
- poweruppatterns <insta|max|ss|dg|carpenter|bg>: Start the timer.


## Last Weapons
- Shows the weapons and armor you had when you won the game.
### Config
- Toggle Last Weapons: Enable or disable Last Weapons.
- Display Armors: Displays armors in Last Weapons.
- Display Weapons Level: Displays weapons level in Last Weapons.
- Display Cooled Down Skills: Displays a cooled down skill in Last Weapons.


## Text Macro
- Press the hotkey (default Q) to send text to chat.


## Korean Patcher
- Translate some phrases into Korean.


# Commands
- zombiesaddon hud: This command allows you to move the positions of the Internal Timer, Wave Delays, and Powerup Patterns.
- /de, /bb, /aa, /pr: These are the commands that allow you to access each map.

# Config
## General
- Enable Mod: Enable or disable Zombies Addon mod.
- Language: The language for the mod.

## Modules
- Other modules options.

## Other Mods
- Disable Spawn Time of SST: Disable the spawn time of SST.
- Disable Timer of Zombies Utils: Disable the timer of Zombies Utils.

## Hidden(Visible only in config file)
- blockUnlegitMods: Blocks unlegit Mods.
- blockUnlegitSST: Blocks the unlegit feature of SST.


# Others
- The /sla command in Zombies Utils is renamed to /sla_zombiesutils.
- Supports automatic updates for Windows only, limited to the recommended version.


# Version system
- Version consists of x.y.z (recommended version) or x.y.z-typew (latest version).
- x means mandatory update. For example, if 2.0.0 is the latest version, then the versions below it cannot be used.
- Version types are Alpha, Beta, Pre-Release (pre), and Release Candidate (rc) in order from the lowest version.
- Alpha is a closed beta version that includes features under development.
- Beta is a public beta version that may have serious bugs.
- Pre-Release is a version that has completed adding features, and bugs may occur.
- Release Candidate is a version that has most bugs fixed, and if no more bugs occur, it will be changed to a recommended version.


# License
This project is licensed under the MIT License as specified in the [LICENSE](LICENSE) file.<br>
It allows for personal use, modification, distribution, and commercial use under the license and copyright notice, and it comes with no warranty or liability.

# Credits
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

# Update Log

## 4.4.3
- Bug fix: Module on/off not applied
- Code fix: Warning suppression

## 4.4.2
- Bug fix: Config wouldn't open with incorrect Mixin.

## 4.4.1
- Bugfix: Wrong boss color in Wave Delays.
- Gui fix: Changed the game exit name in the Gui to the original translated string.

## 4.4.0
- Bugfix: Empty slots in skills are not displayed in Last Weapons.

## 4.4.0-rc1
- ZSV Fix: Fixed the game not freezing when loading text.
- Bugfix: Fixed a bug where if loading a version fails, it would fall back to the wrong version.

## 4.4.0-pre4
- Bugfix: Some strings would crash when changing languages.
- Code fix: Replaced some try statements with runCatching functions.

## 4.4.0-pre3
- Fixed a bug: Missing boss colors in Wave Delays.
- Replaces "playWave", the old usage with "playType" in "CustomPlaySound.json" when starting the game.

## 4.4.0-pre2
- Fixed Wave Delays: Fixed incorrect order of prefixes.

## 4.4.0-pre1
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


## 4.3.2
- Reduces excessive allocations

## 4.3.1
- Fixed bugs.

## 4.3.0
- No changes.

## 4.3.0-pre3
- Fixed bugs.
- Added /de, /bb, /aa, and /pr commands.
- Fixed Last Weapons.

## 4.3.0-pre2
- Fixed bugs.
- Fixed Wave Delays.
- Fixed Powerup Patterns.

## 4.3.0-pre1
- Fixed bugs.
- Added commands.
- Added Language option in config.


## 4.2.3
- Fixed bugs.

## 4.2.2
- Fixed bugs.
- Added /zombiesaddon command.
- Removed /info command.

## 4.2.1
- Fixed bugs.

## 4.2.0
- Fixed bugs.

## 4.2.0-pre6
- Fixed bugs.
- Fixed Last Weapons.

## 4.2.0-pre5
- Fixed bugs.

## 4.2.0-pre4
- Fixed bugs.
- Added /waveDelays and /autoSplits commands.
- Blocks the unlegit feature of SST.

## 4.2.0-pre3
- Fixed bugs.
- Multiple languages are not supported.(Only English or Korean.)

## 4.2.0-pre2
- Fixed bugs.

## 4.2.0-pre1
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


## 4.1.3
- Fixed bugs.


## 4.1.2
- Fixed bugs.


## 4.1.1
- Fixed bugs.
- Fixed Auto Rejoin.


## 4.1.0
- Fixed Last Weapons.

## 4.1.0-pre6
- Fixed bugs.
- Fixed Powerup Patterns.

## 4.1.0-pre5
- Fixed Last Weapons.
- Fixed bugs.

## 4.1.0-pre4
- Fixed a bug.

## 4.1.0-pre3
- Added Text Macro.
- Code optimization.
- Fixed Last Weapons.

## 4.1.0-pre2
- Fixed bugs.

## 4.1.0-pre1
- Fixed bugs.
- Fixed Powerup Alarm.
- Added SST Patch.
- Added Korean Patch.
- Added Last Weapons.
- Added Detect Unlegit Mods.


## 4.0.1
- Fixed bugs.
- Fixed SLA.
- Fixed Powerup Alarm.
- Fixed Wave Delays.


## 4.0.0
- Removed DPS Counter.
- Removed Player Tracker.
- Removed Boss Tracker.
- Removed Gstep Guide.
- Fixed Powerup Alarm.


## 3.1.0-pre8
- Fixed bugs.
- Fixed Wave Delays.
- Fixed Boss Tracker.
- Fixed Player Tracker.
- Removed Grow ESP.
- Removed Grow Guide.
- Removed Boss Spawn Tracker.

## 3.1.0-pre7
- Fixed bugs.
- Fixed Wave Delays.

## 3.1.0-pre6
- Fixed bugs.
- Fixed Wave Delays.
- Fixed SLA.

## 3.1.0-pre5
- Fixed bugs.

## 3.1.0-pre4
- Fixed bugs.

## 3.1.0-pre3
- Fixed bugs.
- Fixed SLA.

## 3.1.0-pre2
- Fixed bugs.

## 3.1.0-pre1
- Added Prison at Wave Delays.
- Boss Wave Alarm, SLA, Boss Spawn Tracker, Boss Tracker is not work in Prison.


## 3.0.0
- Fixed bug.
- Fixed a bug that Boss Tracker did display non-boss mobs.


## 2.0.0
- Fixed bug.
- Renamed Boss Alarm.
- Fixed a bug that some functions did not work when the nickname contained "_".


## 1.17.2-pre1
- Fixed bug.
- Added Player Tracker.
- Added Boss Tracker.

## 1.17.1
- Fixed bugs.

## 1.17.0
- Fixed bugs.

## 1.17.0-pre4
- Fixed bugs.
- Added config option.
- Removed /updatelog command.

## 1.17.0-pre3
- Fixed bugs.

## 1.17.0-pre2
- Fixed bugs.

## 1.17.0-pre1
- Fixed bugs.
- Fixed Auto Splits.
- Renamed NOTLAST.
- Fixed config.
- Fixed Wave Delays.
- Fixed Not Last.


## 1.16.0
- Fixed bugs.
- Renamed Extra Setting.

## 1.16.0-pre2
- Fixed bugs.

## 1.16.0-pre1
- Fixed bugs.
- Removed Lrod Order.
- Added RL-mode for Auto Splits.
- Fixed Update Checker.
- Added Extra Setting.
- Fixed Wave Delays.
- Removed config for Powerup Alarm.


## 1.15.1
- Fixed bugs.
- Removed Countdown.

## 1.15.0
- Fixed bugs.
- Added Play Sound for Auto Splits.


## 1.14.0
- Fixed bugs.
- Timer of Zombies Utils is off when use with Zombies Utils.
- Removed Health Indicator for Block Alarm.

## 1.14.0-pre4
- Fixed bugs.

## 1.14.0-pre3
- Fixed bugs.

## 1.14.0-pre2
- Fixed bugs.
- Fixed Gstep Guide.

## 1.14.0-pre1
- Added Gstep Guide.
- Fixed DPS Counter, NOTLAST, Auto Splits, and Powerup Alarm.
- Fixed bugs.


## 1.13.0
- Fixed bugs.
- Added Grow ESP.
- Wave delay of SST is off when use with SST.


## 1.12.3
- Fixed bugs.
- Fixed update checker

## 1.12.2
- Fixed update checker.
- Fixed /info command.

## 1.12.1
- Removed Hologram Remover.
- Added update checker.

## 1.12.0
- Added Grow Guide for Auto Splits.
- Fixed bugs.
- Fixed config.
- Added mod version on the display.
- Config reset when mod update.
- Added option which default value of mod be set.


## 1.11.0
- Added Health Indicator for Block Alarm.
- Rename Hologram Bug Generator to Hologram Remover.
- Removed rev and dead for Block Alarm.


## 1.10.3
- Fixed bugs.

## 1.10.2
- Added Hologram Bug Generator.

## 1.10.1
- Fixed bugs.

## 1.10.0
- Changed package.
- Code Reorganization.
- Added Lrod Order.
- Added Advanced SLA.
- Fixed commands.


## 1.9.2
- Fixed bugs.

## 1.9.1
- Fixed bugs.

## 1.9.0
- Fixed bugs.
- Added Boss Alarm.
- Fixed forge command bug.


## 1.8.9
- Fixed bugs.

## 1.8.8
- Fixed bugs.
- Added Boss Wave Alarm for Auto Splits.

## 1.8.7
- Fixed bugs.

## 1.8.6
- Fixed bugs.
- Removed /zombies command.
- Added config for Zombies Overlay.
- Fixed Powerup Alarm.

## 1.8.5
- Fixed bugs.
- Fixed CommandInfo.
- Added /zombies Command for Zombies Overlay.

## 1.8.4
- Fixed bugs.
- Fixed Auto Splits.

## 1.8.3
- Fixed bugs.

## 1.8.2
- Fixed bugs.

## 1.8.1
- Fixed Powerup Alarm.
- Fixed bugs.
- Added config for SLA.

## 1.8.0
- Added Powerup Alarm.
- Fixed bugs.
- Added config for Zombies Addon.
- Fixed Auto Splits.


## 1.7.6
- Fixed bug.

## 1.7.5
- Fixed bug.

## 1.7.4
- Fixed bug.

## 1.7.3
- Fixed bug.

## 1.7.2
- Fixed bug.

## 1.7.1
- Fixed bug.

## 1.7.0
- Fixed Auto Splits.
- Fixed /ZSV and /SLA command.
- Fixed Auto Rejoin.
- Added config for Auto Splits.

## 1.6.5
- Fixed bug.

## 1.6.4
- Fixed bug.

## 1.6.3
- Fixed bug.

## 1.6.2
- Fixed NOTLAST and Auto Rejoin.
- Fixed bug.

## 1.6.1
- Fixed Block Alarm.

## 1.6.0
- Remake /setMap, /setstrat, and /setlines commands name to /SLA, /ZSV, and /ZSVLines.
- Remove /blockAlarm command.
- Added /cornering command.
- Added config for Cornering and Block Alarm.


## 1.5.2
- Fixed bug.

## 1.5.1
- Fixed bug.

## 1.5.0
- Added Auto Rejoin.
- Fixed bug.
- Fixed Auto Splits.


## 1.4.5
- Fixed Block Alarm.

## 1.4.4
- Fixed Block Alarm.

## 1.4.3
- Fixed bug.
- You can use Block Alarm in Korean.

## 1.4.2
- Fixed bug.
- Fixed Block Alarm.
- Added /blockAlarm command.

## 1.4.1
- Fixed mod's message.

## 1.4.0
- Added ZSV and SLA.


## 1.3.0
- Added Auto Splits.


## 1.2.1
- Added /updatelog command.
- Fixed /info command.

## 1.2.0
- Added NOTLAST.
- Fixed /info command.


## 1.1.0
- Removed ZHF.


## 1.0.0
- Added ZHF, Cornering, Block Alarm, and DPS Counter.
- Fixed Block Alarm.