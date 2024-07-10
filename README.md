# Zombies Addon
A mod that helps playing hypixel zombies.

[한국어 내용](https://blog.naver.com/hahaha98757/223012598464)

## Features
### Cornering
Toggles the visibility of players nearby.
Use /cornering <number> command to set the range of cornering.
Used the config.

### Block Alarm
If you survived alone, show "BLOCK" on display.
It works only if set the language of Hypixel in English or Korean.

### Not Last
Shows who is kill the last in every round.
It works only if set the language of Hypixel in English or Korean.

### Auto Splits
Run timer at start of round.

### Wave Delays
Displays the wave delays. It can be used without Auto Splits.
You can set the style of the text to Zombies Addon (e.g. W1 0:10.0) or SST (e.g. W1 00:10).
You can set the style of highlight to Zombies Addon or SST.
If color of wave delay is yellow: Start wave soon.
If it is green: Started wave.
Displays the current wave with a purple arrow.
Used the config.

Displays the wave which the boss spawns.
| Color | Boss name |
|----|----|
| Gold | Bombie |
| Red | Inferno |
| Dark Purple | The Broodmother, Wither, or Herobrine |
| Red and Apua | Lily and Ellie |
| Green | King Slime or Mega Blob |
| Dark Apua | (Rainbow) Giant |
| Dark Red | The Old One or Mega Magma |
| Dark Apua and Dark Red | (Rainbow) Giant and The Old One |
| Black | World Ender |

### Zombies Strat Viewer
Displays the text in [https://pastebin.com/](https://pastebin.com/) on the in-game screen.
Use /ZSV <URL|off> command to load or close text.
URL: It must start with "https://pastebin.com/raw/".
off: Close text.
Use /ZSVLines <number> command to set lines of view.

### Spawn Limit Action
Display number and name of windows where zombies can spawn.
Use /SLA <de|bb|aa|pr|off> to sets the map or off SLA.
Used the config.

### Auto Rejoin
Automatically rejoin the Zombies game.
Used the config.
It works only if set the language of Hypixel in English or Korean.

### Powerup Alarm
Show power-up patterns.
Use /powerupAlarm <insta|max|ss> [number|on|off] command to reset or set the pattern.
It works only if set the language of Hypixel in English or Korean.

### Zombies Overlay in Korean
[Zombies Overlay](https://github.com/TheExploration/zombies-overlay) works even if language of Hypixel in Korean.

### Other
Fixed a bug of forge that commands are runned without "/".
You can turn off Wave Delay of SST in the ".minecraft\config\zombiesaddonSSTSetting.txt".
You can turn off timer of Zombies Utils in the ".minecraft\config\zombiesaddonZombiesUtilsSetting.txt".

****

## Update Log

# 4.0.1
- Fixed bugs.
- Fixed SLA.
- Fixed Powerup Alarm.
- Fixed Wave Delays.

# 4.0.0
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
- Fixed /ZSV, /SLA command.
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
- Added Auto Rejoin mod.
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
- Block Alarm fixed.
- Added /blockAlarm command.

### 1.4.1
- Fixed mod's message.

### 1.4.0
- Added ZSV and SLA mod.


### 1.3.0
- Added Auto Splits mod.


### 1.2.1
- Added /updatelog command.
- /info command fixed.

### 1.2.0
- Added NOTLAST


### 1.1.0
- Removed ZHF


### 1.0.0
- Added ZHF, Cornering, Block Alarm, and DPS Counter.
- Fixed Block Alarm
