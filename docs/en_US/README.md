![Zombies Addon Logo](../../src/main/resources/logo.png)
# Zombies Addon
Download: [Latest version](https://github.com/hahaha98757/zombies-addon/releases/latest), [All versions](https://github.com/hahaha98757/zombies-addon/releases)

## Supported languages
- English (US)
- 한국어 (대한민국): [이 파일](../ko_KR/README.md)을 참고하세요.

## Translation info
Translated by Google Translate and GitHub Copilot.


## Introduction
A mod that integrates several Zombies mods and provides them as modules.

## Modules
### Player Visibility
- Hides nearby players from view.
- Toggle with hotkey (default Z).
- Semi PV displays players as semi-transparent.
- Semi PV has a higher priority than SST's Player Invisible

#### Config
- Default Toggle State: Toggle state when the game starts.
- Displays Text: Displays texts with state of Player Visibility.
- Range: The range for Player Visibility in blocks.
- Toggle Semi PV: Enable or disable Semi Player Visibility.
- Semi PV Range: The range for Semi Player Visibility in blocks.

----
### Block Alarm
- Toggle with hotkey (default P).
- If you are the only one alive and there is a player who can be revived, "BLOCK" will be displayed on the screen.
#### Config
- Default Toggle State: Toggle state when the game starts.
- Displays Text: Displays texts with state of Block Alarm.

----
### Not Last
- Displays the player who killed the last enemy.

----
### Auto Splits
- Sends the startorsplit command to LiveSplit at the start of a round or when you win the game.
- Sends the pause command to LiveSplit when the game is over.

#### Config
- Toggle Auto Splits: Enable or disable Auto Splits.
- Host: The host of LiveSplit server.
- Port: The port of LiveSplit server.

#### Commands
- autosplits <command>: Sends a command to LiveSplit.

#### Usage
1. Download LiveSplit from [here](https://livesplit.org/downloads/).
2. Run LiveSplit → Right-click → Settings and enter a number (default 16834) between 0 and 65535 for Server Port.
3. Run Minecraft → Mods → Zombies Addon → Config → Auto Splits and enter the port value
 you entered in step 2 (default 16834) as it is.
4. Right-click LiveSplit → Control → Start TCP Server to start the LiveSplit server.
5. In-game, you can turn on Auto Splits or use the command to send commands to LiveSplit.

----
### Internal Timer
- Displays the elapsed time since the game started.

----
### Wave Delays
- Displays wave delays.
- Prefixes indicate the characteristics of the wave. Bosses can also be distinguished by wave color.
- Text styles include the default "W1: 0.10.0", "W1 0.10.0", "W1: 00:10", and "W1 00:10".
- Highlight styles of wave delay include:
  - The default "Zombies Addon" will show waves that will start in 3 seconds in yellow,
   waves that have started in green, waves that haven't started in dark gray, and the current wave with a purple arrow.
  - "Zombies Utils" is the same as the wave delay of Zombies Utils.
- Toggle RL mode with the hotkey (default Up). Wave delay will be displayed with the specified number of ticks added.

#### Config
- Toggle Wave Delays: Enable or disable Wave Delays.
- Play Sounds: Plays a sound after n ticks of wave start.
- Custom Play Sound: Plays sounds with a sound name, pitch, and playType that you set in a json file.
- Prefix: Enable or disable the prefix.
- Boss Color: Enable or disable colors of waves where a boss spawns.
- Text Style: The style of texts that are displayed on the screen.
- Highlight Style: The Highlight method for a current wave, passed waves, and next waves.
- Hide Passed Wave: Enable or disable hiding passed waves.
- RL Mode Offset: The offset for RL Mode.

----
### Zombies Strat Viewer
- Displays text from https://pastebin.com/ on the screen.
- You can raise or lower the displayed lines with the hotkeys (default left bracket, right bracket).
- Supports Unicode.

#### Commands
- zsv <URL|off>: Show or hide the text. The URL must begin with "https://pastebin.com/raw/".
- zsvlines <number>: Set the lines to display at a time.

----
### Spawn Limit Action
- Displays the number of windows in which zombies can spawn.

#### Config
- Auto SLA: Turns on SLA when the game starts.
- Text Color: Enable or disable colors for SLA texts.
- Activated Windows: Displays activated windows in SLA.
- Unactivated Windows: Displays unactivated windows in SLA.

#### Commands
- sla <de|bb|aa|off>: Turn the map on or off.
- sla custom <offset|rotate|mirror>: Adjust the position and orientation of the SLA for use in custom maps such as housing.
- sla quick <mogi_a|ghxula|ghxula-garden>: Adjust SLA to fit each user's housing.

----
### Auto Rejoin
- Toggle with hotkey (default O).
- You will be automatically reconnected when the round starts.
- Reconnect attempts will last up to 5 seconds.

#### Config
- Default Toggle State: Toggle state when the game starts.
- Displays Text: Displays texts with state of Auto Rejoin.

----
### Powerup Patterns
- Displays the pattern that power-ups spawn.
- Changes and detected patterns will be reflected in the next round.
- For example, if an instant kill spawns in Round 2,
 the instant kill pattern will not appear on screen until Round 3 begins.
- Use the hotkeys (default is the numpad numbers) to start the timer for each power-up.
- Use the hotkey (default is F) to start the timer for any power-up within 30 blocks of the target.

#### Commands
- pow: This is a shortcut command for poweruppatterns.
- poweruppatterns <insta|max|ss> <reset|number>: Reset the pattern or set it yourself.
- poweruppatterns <insta|max|ss|dg|carpenter|bg>: Start the timer.

----
### Last Weapons
- Shows the weapons and armor you had when you won the game.

#### Config
- Toggle Last Weapons: Enable or disable Last Weapons.
- Display Armors: Displays armors in Last Weapons.
- Display Weapons Level: Displays weapons level in Last Weapons.
- Display Cooled Down Skills: Displays a cooled down skill in Last Weapons.

----
### Recorder
- Saves and compares PB (personal best) and best segment for each round.
- Records are saved by category, which can be specified by config or command.
- When you pass a round, compares it to your previous best record.
- PB and best segment are saved as UTF-16 encoded .times files in the .minecraft/zombies/splits/(category) folder.
  If not in Hypixel or in debug mode, they are saved in the practice-splits folder.
- For each game, segments are saved in the format of YYYY-MM-DD_HH-MM-SS_(Server Number).seg
 as UTF-16 encoded files in the .minecraft/zombies/runs folder.
- There may be a 1 tick difference from the records of Zombies Utils, and the records of Zombies Utils are disabled.
- Saved records can be used in Zombies Utils, and vice versa.

#### Config
- Toggle Recorder: Enable or disable Recorder.
- Default Category: Category to use when not specified using the command. Must not contain '/' or '\'.
- PB Notice: Notifies when a new personal best or best segment is achieved.

#### Commands
- recorder category [Category]: Check or set the current category. Must not contain '/' or '\'.

----
### Text Macro
- Press the hotkey (default Q) to send text to chat.

----
### Korean Patcher
- Translate some phrases into Korean.

----
## Commands
- zombiesaddon difficulty <normal|hard|rip>: Sets the difficulty of the current game.
- zombiesaddon hud: Move the positions of Internal Timer, Wave Delays, and Powerup Patterns.
- /de, /bb, /aa, /pr: Join each map.

## Config
### General
- Enable Mod: Enable or disable Zombies Addon mod.
- Language: The language for the mod.

### Modules
- Other modules options.

### Other Mods
- Disable Spawn Time of SST: Disable the spawn time of SST.
- Disable Timer of Zombies Utils: Disable the timer of Zombies Utils.

### Hidden(Visible only in config file)
- blockUnlegitMods: Blocks unlegit Mods.
- blockUnlegitSst: Blocks the unlegit feature of SST.


## Debug mode
You can test the mod without connecting to an actual game. The commands below must be started with the /za_debug command.
- /za_debug: Toggles debug mode.
- isNotZombies [false|true]: Checks or sets a flag to determine if you are not in a lobby or in-game.
- serverNumber: Checks or sets the current server number. null means none.
- new: Starts a new game in Dead End Normal game mode.
- map [de|bb|aa|pr]: Checks or sets the map for the current game.
- pass <round>: Pass <round> in the current game.
- helicopter: Starts a helicopter escape from the Prison map.
- end <win|lose>: Ends the current game with a win or loss.
- remove: Removes an already completed game.


## Others
- The /sla command in Zombies Utils is renamed to /sla_zombiesutils.
- Supports automatic updates for Windows only, limited to the latest version.


## Version system
- Versions are formatted as x.y.z (latest version) or x.y.z-(type)w (development version).
- x indicates a mandatory update. For example, if version 2.0.0 is the latest version, then versions below it cannot be used.
- Version types are, from lowest to highest, Alpha, Beta, Pre-Release(pre), and Release Candidate(rc).
- The alpha version is private. Do not share.
- The beta version is test version. There may be serious problem.
- The pre-release version is tested version. There may be minor bugs.
- The release candidate version is final test version.


## License
This project is licensed under the MIT License as specified in the [LICENSE](../../LICENSE) file.<br>
It allows for personal use, modification, distribution, and commercial use under the license
 and copyright notice, and it comes with no warranty or liability.


## Credits
Please refer to [this file](CREDITS.md).


## Update Log
Please refer to [this file](CHANGELOG.md).
