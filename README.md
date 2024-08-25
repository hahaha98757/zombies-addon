# Zombies Addon
The mod for Hypixel Zombies

[한국어 내용](https://blog.naver.com/hahaha98757/223012598464)

## Features
### Cornering
Hide nearby players from your view.
Toggle with the V key.
You can set the range in the config.

### Block Alarm
Display "BLOCK" on the screen when you survived alone and there is a player you can revive.
Toggle with the B key.
It only works when the Hypixel language is set to English or Korean.

### Not Last
Display the player who killed the last one.

### Auto Splits
Run a timer at start of the round.

### Wave Delays
Display the wave delays.
You can set the options in the config.
It only works when the Hypixel language is set to English or Korean.

Displays the wave which the boss spawns.
| Color | Boss name |
|----|----|
| Gold | Bombie or Warden |
| Red | Inferno, Angry Prisoner, or Corrupted Pigman |
| Dark Purple | The Broodmother, Wither, or Herobrine |
| Red and Apua | Lily and Ellie |
| Green | King Slime or Mega Blob |
| Dark Apua | (Rainbow) Giant |
| Dark Red | The Old One or Mega Magma |
| Dark Apua and Dark Red | (Rainbow) Giant and The Old One |
| Black | World Ender |

### Zombies Strat Viewer
Display the text in [https://pastebin.com/](https://pastebin.com/) on the screen.
Use /ZSV <URL|off> command to load or close the text. URL must start with "https://pastebin.com/raw/".
Use /ZSVLines <number> command to set lines of view.

### Spawn Limit Action
Display the number and names of windows where zombies can spawn.
Use /SLA <de|bb|aa|pr|off> command to set the map or off SLA.
You can set the options in the config.

### Auto Rejoin
Automatically rejoin Zombies when start of the round.
You can set to hide toggle text in the config.

### Powerup Patterns
Display the patterns of power-ups.
Use /pow <insta|max|ss> [number|on|off] command to reset or set the pattern.
The displayed power-up pattern will be updated in the next round.
For example, if Insta Kill spawned in round 2, Insta Kill pattern will be displayed on the screen when round 3 starts.
It only works when the Hypixel language is set to English or Korean.

### Last Weapons
Display your weapons when You Win.
It only works when the Hypixel language is set to English or Korean.

### Text Macro
Send the text on chat when press the Q key.
You can set the text in the config.

### Korean Patchers
일부 문구가 한국어로 번역됨.
SST의 일부 문구가 한국어로 번역됨.
[Zombies Overlay](https://github.com/TheExploration/zombies-overlay)를 하이픽셀 언어를 한국어로 설정해도 사용할 수 있음.

### Other
Fixed a bug of forge that commands are executed without "/".
You can turn off Wave Delay of SST in the ".minecraft\config\zombiesaddonSSTSetting.txt".
You can turn off timer of Zombies Utils in the ".minecraft\config\zombiesaddonZombiesUtilsSetting.txt".

****

## Update Log

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


### 1.1.0
- Removed ZHF.


### 1.0.0
- Added ZHF, Cornering, Block Alarm, and DPS Counter.
- Fixed Block Alarm.
