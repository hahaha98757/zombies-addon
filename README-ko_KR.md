![Zombies Addon Logo](src/main/resources/logo.png)
# Zombies Addon
하이픽셀의 좀비즈를 위한 모드입니다.

지원 언어: [English (US)](README.md), [한국어 (대한민국)](README-ko_KR.md)

## 모듈
*은 하이픽셀 언어를 영어나 한국어로 설정해야 작동하는 것을 의미합니다.

### Player Visibility
주변 플레이어를 시야에서 숨깁니다.
<br>플레이어를 반투명하게 표시하는 Semi PV가 있습니다.
<br>단축키 Z로 토글합니다.
<br>컨피그에서 범위를 설정할 수 있습니다.
<br>SST의 Player Invisible의 반투명 기능이 작동하지 않습니다.
<br>컨피그에서 화면에 표시되는 토글 여부 텍스트를 숨길 수 있습니다.

### Block Alarm*
혼자 살아있고 살릴 수 있는 플레이어가 있다면, 화면에 "BLOCK"이 표시됩니다.
<br>단축키 P로 토글합니다.
<br>컨피그에서 화면에 표시되는 토글 여부 텍스트를 숨길 수 있습니다.

### Not Last
마지막 적을 죽인 플레이어를 표시합니다.

### Auto Splits
라운드 시작 시 LiveSplits에 startorsplit 신호를 전달합니다.
<br>컨피그에서 LiveSplits의 IP와 포트를 수정할 수 있습니다.

### Internal Timer
라운드 시작 시 타이머를 실행합니다.
<br>/zombiesaddon hud 명령어로 타이머의 위치를 수정할 수 있습니다.

### Wave Delays*
웨이브 딜레이를 표시합니다.
<br>컨피그에서 텍스트의 스타일을 변경할 수 있습니다. 기본값인 "W1: 0.10.0"과 "W1 0.10.0", "W1: 00:10", "W1 00:10"이 있습니다.
<br>하이라이트 스타일을 변경할 수 있습니다. 기본값인 "Zombies Addon"과 "Zombies Utils"가 있습니다.
<br>컨피그에서 지나간 웨이브를 숨기도록 설정할 수 있습니다
<br>컨피그에서 소리를 재생하도록 설정할 수 있습니다.
<br>소리는 웨이브를 기준으로 설정된 틱 만큼 추가된 시점에서 재생됩니다.
<br>컨피그에서 소리 재생을 더 자세히 설정할 수 있습니다. 자세한건 ".minecraft\config\zombiesaddon\커스텀 소리 재생 가이드.txt"파일을 확인하세요.
<br>단축키 Up(위쪽 화살표)로 RL 모드를 토글합니다.
<br>RL 모드에선 웨이브 딜레이가 설정된 틱 만큼 추가되어 표시됩니다.
<br>/zombiesaddon hud 명령어로 웨이브 딜레이의 위치를 설정할 수 있습니다.
<br>/wavedelays 명령어로 난이도, 오프셋을 설정할 수 있습니다.
<br>접두사로 웨이브의 특징이 표시됩니다.

보스나 보스급 적이 스폰하는 웨이브를 알 수 있습니다.
| 색 | 보스 이름 |
|----|----|
| 금색 | 봄비 또는 Warden |
| 빨간색 | 인페르노, Angry Prisoner, or Corrupted Pigman |
| 어두운 보라색 | 브루드마더, 위더, 또는 히로빈 |
| 빨간색과 아쿠아색 | 릴리와 엘리 |
| 녹색 | 킹 슬라임 또는 메가 블롭 |
| 어두운 아쿠아색 | (무지개) 자이언트 |
| 어두운 빨간색 | 오래된 자 또는 메가 마그마 |
| 어두운 아쿠아색과 어두운 빨간색 | (무지개) 자이언트와 오래된 자 |
| 검은색 | 세계 엔더 |

### Zombies Strat Viewer
https://pastebin.com/ 에 있는 글을 화면에 표시합니다.
<br>/zsv <URL|off> 명령어로 글을 표시하거나 끕니다. URL은 "https://pastebin.com/raw/" 로 시작해야 합니다.
<br>/zsvlines <숫자> 명령어로 표시할 줄을 설정합니다.

### Spawn Limit Action
좀비가 스폰할 수 있는 창문의 수를 표시합니다.
<br>/sla <de|bb|aa|off> 명령어로 맵을 설정하거나 끕니다.
<br>SLA가 다른 맵(예: 하우징)에서 작동하도록 하려면 /sla custom <offset|rotate|mirror> 명령어를 사용합니다.
<br>컨피그에서 창문의 이름을 볼 수 있도록 설정할 수 있습니다.
<br>컨피그에서 텍스트의 색상을 제거할 수 있습니다.
<br>컨피그에서 SLA를 자동으로 키도록 설정할 수 있습니다.

### Auto Rejoin
라운드가 시작할 때 자동으로 재접속합니다.
<br>단축키로 토글합니다. 단축키의 기본값은 없습니다.
<br>컨피그에서 화면에 표시되는 토글 여부 텍스트를 숨길 수 있습니다.

### Powerup Patterns*
파워업의 패턴을 표시합니다.
<br>/pow <insta|max|ss> [reset|숫자|on|off] 명령어로 패턴을 리셋하거나, 직접 설정, 또는 끌 수 있습니다.
<br>변경, 감지된 패턴은 다음 라운드에서 반영됩니다.
<br>예를 들어 라운드 2에 즉시 처치가 스폰한 경우, 라운드 3이 시작되야 화면에 즉시 처치의 패턴이 표시됩니다.
<br>단축키나 /pow <insta|max|ss|dg|carpenter|bg> 명령어로 타이머를 작동시킬 수 있습니다.

### Last Weapons*
게임에서 승리했을 때 가지고 있던 무기와 갑옷을 보여줍니다.
<br>가지고 있던 무기나 퍼크의 레벨을 보여줍니다.

### Text Macro
단축키 Q를 눌러 채팅에 텍스트를 보냅니다.
<br>컨피그에서 텍스트를 설정할 수 있습니다.

### Korean Patchers*
좀비즈의 일부 문구를 한국어로 번역합니다.
<br>SST의 일부 문구를 한국어로 번역합니다.
<br>하이픽셀 언어를 한국어로 설정해도 [Zombies Overlay](https://github.com/TheExploration/zombies-overlay)를 사용할수 있습니다.

### Other Mods
컨피그에서 SST의 웨이브 딜레이를 끌 수 있습니다.
<br>컨피그에서 Zombies Utils의 타이머를 끌 수 있습니다.

### 그 외
언레짓 모드(ZombiesSatellite, Zombies Explorer, TeammatesOutline, and ZombiesHelper)가 감지되면 게임을 시작할 수 없습니다. 컨피그 파일을 통해 끌 수 있습니다.
<br>SST의 언레짓 기능이 차단됩니다. 컨피그 파일을 통해 끌 수 있습니다.
<br>Zombies Utils의 /sla 명령어가 /sla_zombiesutils로 바뀝니다.
<br>/de, /bb, /aa, /pr 명령어로 각 맵에 접속할 수 있습니다.
<br>추천 버전에 한에 윈도우 전용 자동 업데이트를 지원합니다.


## 라이선스
이 프로젝트는 [LICENSE](LICENSE) 파일의 전문에 따라 MIT 허가서가 적용됩니다.<br>
라이선스 및 저작권 고지 하에 개인적 이용, 수정, 배포, 상업적 이용이 가능하며 보증 및 책임을 지지 않습니다.

## Credits
- architectury의 [Architectury Pack200](https://github.com/architectury/architectury-pack200) ([Classpath Exception을 포함한 GNU 일반 공중 사용 허가서 v2.0](https://github.com/architectury/architectury-pack200/blob/master/LICENSE))
- DJtheRedstoner의 [DevAuth](https://github.com/DJtheRedstoner/DevAuth) ([MIT 허가서](https://github.com/DJtheRedstoner/DevAuth/blob/master/LICENSE))
- EssentialGG의 [Essential Loom](https://github.com/EssentialGG/architectury-loom) ([MIT 허가서](https://github.com/EssentialGG/architectury-loom/blob/dev/1.6/LICENSE))
- google의 [Gson](https://github.com/google/gson) ([아파치 라이선스 2.0](https://github.com/google/gson/blob/main/LICENSE))
- JetBrains의 [Kotlin](https://github.com/JetBrains/kotlin) ([아파치 라이선스 2.0](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt))
- SpongePowered의 [Mixin](https://github.com/SpongePowered/Mixin) ([MIT 허가서](https://github.com/SpongePowered/Mixin/blob/master/LICENSE.txt))
- Seosean의 [Show Spawn Time](https://github.com/Seosean/ShowSpawnTime/tree/1.15.0) ([MIT 허가서](licenses/Show%20Spawn%20Time-LICENSE))
- tahmid-23의 [Zombies AutoSplits](https://github.com/tahmid-23/ZombiesAutoSplits) ([MIT 허가서](https://github.com/tahmid-23/ZombiesAutoSplits/blob/main/LICENSE))
- syeyoung의 [Zombies Cornering Mod](https://github.com/cyoung06/ZombiesCorneringMod) ([MIT 허가서](licenses/Zombies%20Cornering%20Mod-LICENSE))
- syeyoung의 [Zombies Strat Viewer](https://github.com/cyoung06/ZombiesStratViewer) ([MIT 허가서](licenses/Zombies%20Strat%20Viewer-LICENSE))
- Stachelbeere1248의 [Zombies Utils](https://github.com/Stachelbeere1248/zombies-utils) ([MIT 허가서](https://github.com/Stachelbeere1248/zombies-utils/blob/master/LICENSE))

Zombies Hologrambug Fixer, Zombies Cornering Mod, Zombies Strat Viewer는 본래 GNU 약소 일반 공중 사용 허가서 v2.1이 적용되지만, 저작자인 syeyoung이 MIT 허가서를 부여했습니다.<br>
Show Spawn Time은 ShowSpawnTime-1.15.0.jar내에 있는 LICENSE.txt 파일에 MIT 허가서가 명시되어 있습니다.

****

## 업데이트 로그

### 4.4.0-pre2
- Wave Delays 수정: 잘못된 순서의 접두사 수정. 

### 4.4.0-pre1
- 자바를 코틀린으로 변경.
- 버그 수정.
- 코드 개편.
- 컨피그 수정: 옵션의 이름과 설명을 번역.
- Detect Unlegit Mods 수정: 게임 시작 시 GUI로 알림, 자동 모드 삭제 기능 추가.(윈도우 전용)
- 업데이트 채크 수정: 추천 버전과 필수 업데이트를 GUI로 알림, 자동 업데이트 추가.(윈도우 전용)
- 버전 체계 변경.
- Player Visibility 수정: 반투명 범위 조절 가능, 인게임에서만 작동하도록 변경.
- Block Alarm 수정: 살아있지 않은 상태에서 작동하지 않음, 단축키의 기본값을 P로 변경.
- Auto Splits 수정: LiveSplits 전용으로 변경.
- Internal Timer 추가: 기존 Auto Splits의 기능과 동일.
- Wave Delays 수정: 자이언트와 오래된 자의 접두사 수정.
- ZSV 수정: 한글 지원.
- SLA 수정: 창문의 이름과 텍스트 색상 제거 옵션 추가.

### 4.3.2
- 과도한 할당을 줄임.

### 4.3.1
- 버그 수정.

### 4.3.0
- 수정사항 없음.

### 4.3.0-pre3
- 버그 수정.
- /de, /bb, /aa, /pr 명령어 추가.
- Last Weapons 수정.

### 4.3.0-pre2
- 버그 수정.
- Wave Delays 수정.
- Powerup Patterns 수정.

### 4.3.0-pre1
- 버그 수정.
- 명령어 수정.
- 컨피그에 Language 옵션 추가.

### 4.2.3
- 버그 수정.

### 4.2.2
- 버그 수정.
- /zombiesaddon 명령어 추가.
- /info 명령어 제거.

### 4.2.1
- 버그 수정.

### 4.2.0
- 버그 수정.

### 4.2.0-pre6
- 버그 수정.
- Last Weapons 수정.

### 4.2.0-pre5
- 버그 수정.

### 4.2.0-pre4
- 버그 수정.
- /waveDelays, /autoSplits 명령어 추가.
- SST의 언레짓 기능을 차단함.
  <br>SST의 언레짓 기능 차단의 경우 컨피그 파일에서 끌 수 있습니다.

### 4.2.0-pre3
- 버그 수정.
- 여러 언어를 지원하지 않음(4.1.3으로 롤백)
  <br>나중에 추가하는 걸로.

### 4.2.0-pre2
- 버그 수정.

### 4.2.0-pre1
- 코드 최적화.
- 버그 수정.
- Cornering을 Play Visibility로 이름 변경.
- PV 수정.
- Powerup Patterns의 수동 타이머 추가.
- SLA 수정.
- /ZAHUD 명령어 추가.
- Wave Delays 수정.
- SST Setting, Zombies Utils Setting 추가.
- 여러 언어를 지원함.
  <br>SST Setting와 Zombies Utils Setting은 SST의 웨이브 딜레이, Zombies Utils의 타이머를 끄는 컨피그 설정입니다. 기존과 달리 게임을 재시작할 필요가 없습니다.
/ZAHUD 명령어는 Auto Splits, Wave Delays, Powerup Patterns의 위치를 옮길 수 있는 명령어입니다.


### 4.1.3
- 버그 수정.


### 4.1.2
- 버그 수정.


### 4.1.1
- 버그 수정.
- Auto Rejoin 수정.


### 4.1.0
- Last Weapons 수정.

### 4.1.0-pre6
- 버그 수정.
- Powerup Patterns 수정.

### 4.1.0-pre5
- Last Weapons 수정.
- 버그 수정.

### 4.1.0-pre4
- 버그 수정.

### 4.1.0-pre3
- Text Macro 추가.
- 코드 최적화.
- Last Weapons 수정.

### 4.1.0-pre2
- 버그 수정.

### 4.1.0-pre1
- 버그 수정.
- Powerup Alarm 수정.
- SST Patch 추가.
- Korean Patch 추가.
- Last Weapons 추가.
- Detect Unlegit Mods 추가.
  <br>Detect Unlegit Mods의 경우 컨피그 파일에서 끌 수 있습니다.


### 4.0.1
- 버그 수정.
- SLA 수정.
- Powerup Alarm 수정.
- Wave Delays 수정.


### 4.0.0
- DPS Counter 제거.
- Player Tracker 제거.
- Boss Tracker 제거.
- Gstep Guide 제거.
- Powerup Alarm 제거.
  <br>언레짓 모드가 새로 정의됨에 따라 언레짓 모드를 제거했습니다. Powerup Alarm은 패턴을 표시하는 모드로 변견되었습니다.


### 3.1.0-pre8
- 버그 수정.
- Wave Delays 수정.
- Boss Tracker 수정.
- Player Tracker 수정.
- Grow ESP 제거.
- Grow Guide 제거.
- Boss Spawn Tracker 제거.

### 3.1.0-pre7
- 버그 수정.
- Wave Delays 수정.

### 3.1.0-pre6
- 버그 수정.
- Wave Delays 수정.
- SLA 수정.

### 3.1.0-pre5
- 버그 수정.

### 3.1.0-pre4
- 버그 수정.

### 3.1.0-pre3
- 버그 수정.
- SLA 수정.

### 3.1.0-pre2
- 버그 수정.

### 3.1.0-pre1
- Wave Delays에 Prison맵 추가.
- Prison에서 Boss Wave Alarm, SLA, Boss Spawn Tracker, Boss Tracker가 작동되지 않음.
  <br>Prison에 대한 정확한 정보가 부족하고, 버그를 발생시킬 수 있기에 일부 모드를 Prison에서 작동하지 않도록 했습니다.


### 3.0.0
- 버그 수정.
- Boss Tracker가 보스가 아닌 몹을 표시하는 버그 수정.


### 2.0.0
- 버그 수정.
- Boss Alarm의 이름 수정.
- 닉네임에 "_"가 포함된 경우 일부 기능이 작동하지 않는 버그 수정.


### 1.17.2-pre1
- 버그 수정.
- Player Tracker 추가.
- Boss Tracker 추가.

### 1.17.1
- 버그 수정.

### 1.17.0
- 버그 수정.

### 1.17.0-pre4
- 버그 수정.
- 컨피그 옵션 추가.
- /updatelog 명령어 제거.

### 1.17.0-pre3
- 버그 수정.

### 1.17.0-pre2
- 버그 수정.

### 1.17.0-pre1
- 버그 수정.
- Auto Splits 수정.
- NOTLAST 이름 변경.
- 컨피그 수정.
- Wave Delays 수정.
- Not Last 수정.
  <br>LiveSplit은 잘 사용되지 않기에 Auto Splits에서 LiveSplit을 사용하는 옵션을 제거했습니다.
이제 Wave Delays와 Grow Guide가 Auto Splits 없이 사용할 수 있습니다.
게임 오버 시 Auto Splits의 타이머가 리셋이 아닌 정지되며, Wave Delays가 꺼지지 않고 유지됩니다.


### 1.16.0
- 버그 수정.
- Extra Setting 제거.

### 1.16.0-pre2
- 버그 수정.

### 1.16.0-pre1
- 버그 수정.
- Lrod Order 제거.
- Auto Splits 용 RL 모드 추가.
- 업데이트 채크 수정.
- Extra Setting 추가.
- 웨이브 딜레이 수정.
- Powerup Alarm 용 컨피그 제서.


### 1.15.1
- 버그 수정.
- Countdown 제거.

### 1.15.0
- 버그 수정.
- Auto Splits용 Play Sound 추가.


### 1.14.0
- 버그 수정.
- Zombies Utils와 사용할 경우, Zombies Utils의 타이머를 끔.
- Block Alarm의 Health Indicator 제거.

### 1.14.0-pre4
- 버그 수정.

### 1.14.0-pre3
- 버그 수정.

### 1.14.0-pre2
- 버그 수정.
- Gstep Guide 수정.

### 1.14.0-pre1
- Gstep Guide 추가.
- DPS Counter, NOTLAST, Auto Splits, Powerup Alarm 수정.
- 버그 수정.
  <br>DPS Counter, NOTLAST, Auto Splits, Powerup Alarm의 경우 게임 중 on/off를 잘 전환하지 않기에 컨피그로 옮겼습니다.


### 1.13.0
- 버그 수정.
- Grow ESP 추가.
- SST와 사용할 경우, SST의 웨이브 딜레이를 끔.
  <br>SST의 웨이브 딜레이와 Zombies Addon의 웨이브 딜레이가 겹치기에 SST의 웨이브 딜레이를 강제로 끄도록 했습니다. 현재로는 SST의 웨이브 딜레이를 사용할 수 없습니다.


### 1.12.3
- 버그 수정.
- 업데이트 체크 수정.

### 1.12.2
- 업데이트 체크 수정.
- /info 명령어 수정.

### 1.12.1
- Hologram Remover 제거.
- 업데이트 체크 추가.
  <br>싱글/멀티 플레이에 처음 접속하면 채팅창에 업데이트 알림이 뜹니다.

### 1.12.0
- Auto Splits 전용 Grow Guide 추가.
- 버그 수정.
- 컨피그 수정.
- 화면에 모드 버전 추가.
- 모드 업데이트 시 컨피그 리셋.
- 컨피그에서 모드 기본값을 설정하는 옵션 추가.
  <br>이제 컨피그가 변경되는 업데이트가 있을 경우 컨피그가 리셋되어 더 이상 쓰이지 않은 옵션들이 제거됩니다.


### 1.11.0
- Block Alarm 전용 Health Indicator 추가.
- Hologram Bug Generator의 이름을 Hologram Remover로 변경.
- Block Alarm의 rev, dead 제거.


### 1.10.3
- 버그 수정.

### 1.10.2
- Hologram Bug Generator 추가.

### 1.10.1
- 버그 수정.

### 1.10.0
- 패키지 변경.
- 코드 재구성.
- Lrod Order 추가.
- 고급 SLA 추가.
- 명령어 수정.


### 1.9.2
- 버그 수정.

### 1.9.1
- 버그 수정.

### 1.9.0
- 버그 수정.
- Boss Alarm 추가.
- forge 명령어 버그 수정.
  <br>모드의 명령어가 / 없이 실행되는 버그를 수정했습니다.


### 1.8.9
- 버그 수정.

### 1.8.8
- 버그 수정.
- Auto Splits 전용 Boss Wave Alarm 추가.
  <br>Boss Wave Alarm은 컨피그에서 활성화 가능하며 보스가 스폰하는 웨이브를 표시해줍니다.

### 1.8.7
- 버그 수정.

### 1.8.6
- 버그 수정.
- /zombies 명령어 제거.
- 컨피그에 Zombies Overlay 관련 설정 추가.
- Powerup Alarm 수정.
  <br>명령어보다 컨피그로 사용 여부를 설정하고 자동으로 사용되도록 하는 것이 더 나을 것입니다.

### 1.8.5
- 버그 수정.
- /info 명령어 수정.
- /zombies 명령어 추가.
  <br>/zombies 명령어는 Zombies Overlay를 한국어 환경에서 쓸 수 있도록 하는 명령어입니다.

### 1.8.4
- 버그 수정.
- Auto Splits 수정.

### 1.8.3
- 버그 수정.

### 1.8.2
- 버그 수정.

### 1.8.1
- Powerup Alarm 수정.
- 버그 수정.
- 컨피그에 SLA 관련 설정 추가.
  <br>게임이 시작할 때 자동으로 SLA를 켜주는 설정을 추가했습니다.

### 1.8.0
- Powerup Alarm 추가.
- 버그 수정.
- 컨피그에 Zombies Addon 관련 설정 추가.
- Auto Splits 수정.
  <br>Auto Splits의 기본값이 on으로 변경되었습니다. 오른쪽 위에 표시되는 모드 on/off 여부를 끌 수 있습니다.


### 1.7.6
- 버그 수정.

### 1.7.5
- 버그 수정.

### 1.7.4
- 버그 수정.

### 1.7.3
- 버그 수정.

### 1.7.2
- 버그 수정.

### 1.7.1
- 버그 수정.

### 1.7.0
- Auto Splits 수정.
- /ZSV, /SLA 명령어 수정.
- Auto Rejoin 수정.
- 컨피그에 Auto Splits 관련 설정 ㅊ가. 
<br>웨이브 딜레이를 추가했습니다.


### 1.6.5
- 버그 수정.

### 1.6.4
- 버그 수정.

### 1.6.3
- 버그 수정.

### 1.6.2
- NOTLAST, Auto Rejoin 수정.
- 버그 수정.
  <br>NOTLAST, Auto Rejoin을 한국어 환경에서 사용할 수 있습니다.

### 1.6.1
- Block Alarm 수정.
  <br>Block Alarm을 한국어 환경에서 사용할 수 있습니다.

### 1.6.0
- /setMap, /setstrat, /setlines 명령어의 이름을 /SLA, /ZSV, /ZSVLines로 변경.
- /blockAlarm 명령어 제거.
- /cornering 명령어 추가.
- 컨피그에 Cornering, Block Alarm 관련 설정 추가.


### 1.5.2
- 버그 수정.

### 1.5.1
- 버그 수정.

### 1.5.0
- Auto Rejoin 수정.
- 버그 수정.
- Auto Splits 수정.


### 1.4.5
- Block Alarm 수정.
  <br>Block Alarm이 v1.4.2로 롤백됩니다.

### 1.4.4
- Block Alarm 수정.

### 1.4.3
- 버그 수정.
- Block Alarm을 하이픽셀 언어가 한국어일 때 사용 가능.

### 1.4.2
- 버그 수정.
- Block Alarm 수정.
- /blockAlarm 명령어 추가.

### 1.4.1
- 모드의 메시지 수정.

### 1.4.0
- ZSV, SLA 추가.


### 1.3.0
- Auto Splits 추가.


### 1.2.1
- /updatelog 명령어 추가.
- /info 명령어 수정.

### 1.2.0
- NOTLAST 추가.
- /info 명령어 수정.


### 1.1.0
- ZHF 제거.
  <br>ZHF가 사용 금지됨.


### 1.0.0
- ZHF, Cornering, Block Alarm, DPS Counter 추가.
- Block Alarm 수정.
  <br>Block Alarm의 작동 조건을 3명이 다운 될 경우로 수정했습니다.