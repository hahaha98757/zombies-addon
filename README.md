![Zombies Addon Logo](src/main/resources/logo.png)
# Zombies Addon
지원 언어: [한국어 (대한민국)](README.md), [English (US)](README-en_US.md)
- 여러 좀비즈 모드를 통합하고 자잘한 버그 수정, 새로운 기능을 가진 모드입니다.


# 모듈
## Player Visibility
- 단축키(기본값 Z)로 토글합니다.
- 주변 플레이어를 시야에서 숨깁니다.
- 플레이어를 반투명하게 표시하는 Semi PV가 있습니다.
- 이 모드를 적용하면 SST의 Player Invisible의 플레이어 반투명이 작동하지 않습니다.
### 컨피그
- 토글 기본값: Player Visibility의 토글 기본값입니다.
- 텍스트 표시: Player Visibility 상태를 표시하는 텍스트입니다.
- 범위: Player Visibility의 범위(블록 단위)입니다.
- 토글 Semi PV: Semi Player Visibility를 활성화 또는 비활성화합니다.
- Semi PV 범위: Semi Player Visibility의 범위(블록 단위)입니다.


## Block Alarm
- 단축키(기본값 P)로 토글합니다.
- 혼자 살아있고 살릴 수 있는 플레이어가 있다면, 화면에 "BLOCK"이 표시됩니다.
### 컨피그
- 토글 기본값: Block Alarm의 토글 기본값입니다.
- 텍스트 표시: Block Alarm 상태를 표시하는 텍스트입니다.


## Not Last
- 마지막 적을 죽인 플레이어를 표시합니다.
- 다음 라운드가 시작하는 순간 킬 수가 올라간 플레이어를 찾는 방식으로 작동합니다. 때문에 플레이어를 못 찾거나, 여러 플레이어가 감지될 수 있습니다.


## Auto Splits
- 라운드 시작 시 LiveSplit에 startorsplit 신호를 전달합니다.
### 컨피그
- 토글 Auto Splits: Auto Splits를 활성화 또는 비활성화합니다.
- 호스트: LiveSplit 서버의 호스트입니다.
- 포트: LiveSplit 서버의 포트입니다.
### 명령어
- autosplits <신호>: LiveSplit에 원하는 신호를 전달합니다.
### 사용법
1. [여기](https://livesplit.org/downloads/)에서 LiveSplit을 다운로드합니다.
2. LiveSplit을 실행해 우클릭 → Settings에서 Server Port를 0~65535 중 원하는 숫자(기본값 16834)로 입력하고 OK 버튼을 눌러 창을 닫는다.
3. 마인크래프트 실행 → 모드 → Zombies Addon → Config → Auto Splits에서 포트 값을 2단계에서 입력한 숫자(기본값 16834)를 그대로 입력한다.
4. LiveSplit 우클릭 → Control → Start TCP Server로 서버를 시작한다.
5. 인게임에서 Auto Splits를 키면, 라운드 시작 시 신호가 전달된다.


## Internal Timer
- 라운드 시작 시 타이머를 실행합니다.
### 명령어
- internaltimer <run|stop>: 명령어로 타이머를 시작하거나 정지합니다.


## Wave Delays
- 웨이브 딜레이를 표시합니다.
- 접두사는 웨이브의 특징을 표시합니다. 보스의 경우 웨이브 색으로도 구분할 수 있습니다.
- 텍스트 스타일은 기본값인 "W1: 0.10.0"과 "W1 0.10.0", "W1: 00:10", "W1 00:10"이 있습니다.
- 웨이브 딜레이의 하이라이트 스타일은 다음과 같습니다:
  <br>기본값인 "Zombies Addon"은 3초 후 시작할 웨이브는 노란색, 시작한 웨이브는 녹색, 시작되지 않은 웨이브는 진한 회색으로 표시되며 현재 웨이브는 보라색 화살표로 표시됩니다.
  <br>"Zombies Utils"는 Zombies Utils의 웨이브 딜레이와 동일합니다.
- 단축키(기본값 Up)으로 RL 모드를 토글합니다. 웨이브 딜레이가 설정된 틱 만큼 추가되어 표시됩니다.
### 컨피그
- 토글 Wave Delays: Wave Delays를 활성화 또는 비활성화합니다.
- 소리 재생: 웨이브 시작 n 틱 후 소리를 재생합니다.
- 커스텀 소리 재생: Json 파일에서 설정한 소리 이름, 피치, 플레이 타입으로 소리를 재생합니다.
- 접두사: 접두사를 활성화 또는 비활성화합니다.
- 보스 색상: 보스가 등장하는 웨이브의 색상을 활성화 또는 비활성화합니다.
- 텍스트 스타일: 화면에 표시되는 텍스트의 스타일입니다.
- 하이라이트 스타일: 현재 웨이브, 지난 웨이브, 다음 웨이브에 대한 하이라이트 방식입니다.
- 지난 웨이브 숨기기: 지난 웨이브를 숨길지 여부를 설정합니다.
- RL 모드 오프셋: RL 모드의 오프셋입니다.
### 명령어
- wavedelays: 난이도, 오프셋을 설정합니다.


## Zombies Strat Viewer
- https://pastebin.com/ 에 있는 글을 화면에 표시합니다. 
- 단축키(기본값 왼쪽 대괄호, 오른쪽 대괄호)로 표시되는 줄을 올리거나 내릴 수 있습니다.
- 유니코드(한글 포함)를 지원합니다.
### 명령어
- zsv <URL|off>: 글을 표시하거나 끕니다. URL은 "https://pastebin.com/raw/" 로 시작해야 합니다.
- zsvlines <숫자>: 한번에 표시할 줄을 설정합니다.


## Spawn Limit Action
- 적이 스폰될 수 있는 창문의 수를 표시합니다.
### 컨피그
- 자동 SLA: 게임 시작 시 SLA를 자동으로 켭니다.
- 텍스트 색상: SLA 텍스트의 색상을 활성화 또는 비활성화합니다.
- 활성화된 창문: SLA에서 활성화된 창문을 표시합니다.
- 비활성화된 창문: SLA에서 비활성화된 창문을 표시합니다.
### 명령어
- sla <de|bb|aa|off>: 맵을 설정하거나 끕니다.
- sla custom <offset|rotate|mirror>: 하우징과 같은 자작 맵에서 사용하기 위해 SLA의 위치와 방향을 조정합니다.
- sla quick <mogi_a|ghxula|ghxula-garden>: 각 유저의 하우징에 맞도록 SLA를 조정합니다.


## Auto Rejoin
- 단축키(기본값 없음)로 토글합니다.
- 라운드가 시작할 때 자동으로 재접속합니다.
### 컨피그
- 토글 기본값: 게임 시작 시 Auto Rejoin의 토글 기본값입니다.
- 텍스트 표시: Auto Rejoin 상태를 표시하는 텍스트입니다.


## Powerup Patterns
- 파워업이 스폰하는 패턴을 표시합니다.
- 변경, 감지된 패턴은 다음 라운드에서 반영됩니다.
- 예를 들어 라운드 2에 즉시 처치가 스폰한 경우, 라운드 3이 시작되야 화면에 즉시 처치의 패턴이 표시됩니다.
### 명령어
- pow: poweruppatterns의 단축 명령어입니다.
- poweruppatterns <insta|max|ss> <reset|숫자>: 패턴을 리셋하거나, 직접 설정합니다.
- poweruppatterns <insta|max|ss|dg|carpenter|bg>: 타이머를 작동시킵니다.


## Last Weapons
- 게임에서 승리했을 때 가지고 있던 무기와 갑옷을 보여줍니다.
### 컨피그
- 토글 Last Weapons: Last Weapons를 활성화 또는 비활성화합니다.
- 갑옷 표시: Last Weapons에서 갑옷을 표시합니다.
- 무기 레벨 표시: Last Weapons에서 무기 레벨을 표시합니다.
- 쿨다운된 스킬 표시: Last Weapons에서 쿨다운된 스킬을 표시합니다.


## Text Macro
- 단축키(기본값 Q)를 눌러 채팅에 텍스트를 보냅니다.


## 한글 패치
- 일부 문구를 한국어로 번역합니다.
### 컨피그
- 인게임: 인게임에 대한 한글 패치를 활성화 또는 비활성화합니다.
- Zombies Overlay: [Zombies Overlay](https://github.com/TheExploration/zombies-overlay)에 대한 한글 패치를 활성화 또는 비활성화합니다.
- SST: SST에 대한 한글 패치를 활성화 또는 비활성화합니다.


# 명령어
- zombiesaddon hud: Internal Timer, Wave Delays, Powerup Patterns의 위치를 옮길 수 있는 명령어입니다.
- /de, /bb, /aa, /pr: 각 맵에 접속할 수 있는 명령어입니다.

# 컨피그
## 일반
- 모드 활성화: Zombies Addon 모드를 활성화 또는 비활성화합니다.
- 언어: 모드에 사용할 언어입니다.

## 모듈
- 나머지 모듈의 설정입니다.

## 다른 모드
- SST의 스폰 시간 비활성화: SST의 스폰 시간을 비활성화합니다.
- Zombies Utils의 타이머 비활성화: Zombies Utils의 타이머를 비활성화합니다.

## 히든(컨피그 파일에서만 보임)
- blockUnlegitMods: 언레짓 모드를 차단합니다.
- blockUnlegitSST: SST의 언레짓 기능을 차단합니다.


# 기타
- Zombies Utils의 /sla 명령어가 /sla_zombiesutils로 바뀝니다.
- 추천 버전에 한에 윈도우 전용 자동 업데이트를 지원합니다.


# 버전 체계
- 버전은 x.y.z(추천 버전) 또는 x.y.z-타입w(최신 버전)으로 구성됩니다.
- x는 필수 업데이트를 의미합니다. 예를 들어 2.0.0 버전이 최신 버전이라면 그 아래의 버전은 사용할 수 없습니다.
- 버전 타입은 낮은 버전 부터 차례대로 Alpha, Beta, Pre-Release(pre), Release Candidate(rc)가 있습니다.
- Alpha는 비공개 테스트 버전으로, 개발 중인 기능이 포함되어 있습니다.
- Beta는 공개 테스트 버전으로, 심각한 버그가 발생할 수 있습니다.
- Pre-Release는 기능 추가이 완료된 버전으로, 버그가 발생할 수 있습니다.
- Release Candidate는 대부분의 버그가 수정된 버전으로, 더 이상 버그가 발생하지 않는다면 추천 버전으로 변경됩니다.


# 라이선스
이 프로젝트는 [LICENSE](LICENSE) 파일의 전문에 따라 MIT 허가서가 적용됩니다.<br>
라이선스 및 저작권 고지 하에 개인적 이용, 수정, 배포, 상업적 이용이 가능하며 보증 및 책임을 지지 않습니다.

# 크레딧
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

# 업데이트 로그

## 4.5.0-beta5
- 버그 수정: 버전이 변경되지 않음.

## 4.5.0-beta4
- 버그 수정: 잘못된 게임 제거 타이밍.

## 4.5.0-beta3
- 버그 수정: Prison에서 게임 종료가 되지 않음. 게임 중 모드가 활성화 될 경우 라운드 1부터 시작함.

## 4.5.0-beta2
- 버그 수정: 게임이 종료되지 않음.
- 변경: 게임 종료 시 타이머 정지.

## 4.5.0-beta1
- Internal Timer 수정: 더 정확한 타이머.
- 버그 수정: null 체크 추가. 번역 수정. 로그 메시지를 번역하지 않음.
- Powerup Patterns 수정: 패턴을 게임마다 따로 기록함.
- 추가: /za_debug 명령어와 디버그 모드. Zombies Utils에 한국어 옵션 추가.
- Player Visibility 수정: SST의 Player Invisible과 충돌하지 않도록 수정.
- 변경: 게임 처리 방식을 Zombies Utils에 맞춤.


## 4.4.4
- 버그 수정: 저장된 위치 정보가 불러와지지 않음.

## 4.4.3
- 버그 수정: 모듈의 on/off가 적용되지 않음.
- 코드 수정: 경고 억제.

## 4.4.2
- 버그 수정: 잘못된 Mixin으로 컨피그가 열리지 않음.

## 4.4.1
- 버그 수정: Wave Delays에서 잘못된 보스 색상.
- Gui 수정: Gui의 게임 종료 이름을 기존 번역 문자열로 변경.

## 4.4.0
- 버그 수정: Last Weapons에서 기술의 빈 슬롯이 표시되지 않음.

## 4.4.0-rc1
- ZSV 수정: 글을 불러올 때 게임이 정지하지 않도록 수정.
- 버그 수정: 버전을 불러오는데 실패했을 때, 잘못된 버전으로 대체하는 버그 수정.

## 4.4.0-pre4
- 버그 수정: 언어 변경 시 일부 문자열이 충돌을 발생시킴.
- 코드 수정: 일부 try 문을 runCatching 함수로 수정.

## 4.4.0-pre3
- 버그 수정: Wave Delays에서 보스 색상이 누락됨.
- 게임 시작 시 "CustomPlaySound.json"에서 예전 사용법 이었던 "playWave"를 "playType"으로 교체.

## 4.4.0-pre2
- Wave Delays 수정: 잘못된 순서의 접두사 수정. 

## 4.4.0-pre1
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

## 4.3.2
- 과도한 할당을 줄임.

## 4.3.1
- 버그 수정.

## 4.3.0
- 수정사항 없음.

## 4.3.0-pre3
- 버그 수정.
- /de, /bb, /aa, /pr 명령어 추가.
- Last Weapons 수정.

## 4.3.0-pre2
- 버그 수정.
- Wave Delays 수정.
- Powerup Patterns 수정.

## 4.3.0-pre1
- 버그 수정.
- 명령어 수정.
- 컨피그에 Language 옵션 추가.

## 4.2.3
- 버그 수정.

## 4.2.2
- 버그 수정.
- /zombiesaddon 명령어 추가.
- /info 명령어 제거.

## 4.2.1
- 버그 수정.

## 4.2.0
- 버그 수정.

## 4.2.0-pre6
- 버그 수정.
- Last Weapons 수정.

## 4.2.0-pre5
- 버그 수정.

## 4.2.0-pre4
- 버그 수정.
- /waveDelays, /autoSplits 명령어 추가.
- SST의 언레짓 기능을 차단함.
  <br>SST의 언레짓 기능 차단의 경우 컨피그 파일에서 끌 수 있습니다.

## 4.2.0-pre3
- 버그 수정.
- 여러 언어를 지원하지 않음(4.1.3으로 롤백)
  <br>나중에 추가하는 걸로.

## 4.2.0-pre2
- 버그 수정.

## 4.2.0-pre1
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


## 4.1.3
- 버그 수정.


## 4.1.2
- 버그 수정.


## 4.1.1
- 버그 수정.
- Auto Rejoin 수정.


## 4.1.0
- Last Weapons 수정.

## 4.1.0-pre6
- 버그 수정.
- Powerup Patterns 수정.

## 4.1.0-pre5
- Last Weapons 수정.
- 버그 수정.

## 4.1.0-pre4
- 버그 수정.

## 4.1.0-pre3
- Text Macro 추가.
- 코드 최적화.
- Last Weapons 수정.

## 4.1.0-pre2
- 버그 수정.

## 4.1.0-pre1
- 버그 수정.
- Powerup Alarm 수정.
- SST Patch 추가.
- Korean Patch 추가.
- Last Weapons 추가.
- Detect Unlegit Mods 추가.
  <br>Detect Unlegit Mods의 경우 컨피그 파일에서 끌 수 있습니다.


## 4.0.1
- 버그 수정.
- SLA 수정.
- Powerup Alarm 수정.
- Wave Delays 수정.


## 4.0.0
- DPS Counter 제거.
- Player Tracker 제거.
- Boss Tracker 제거.
- Gstep Guide 제거.
- Powerup Alarm 제거.
  <br>언레짓 모드가 새로 정의됨에 따라 언레짓 모드를 제거했습니다. Powerup Alarm은 패턴을 표시하는 모드로 변견되었습니다.


## 3.1.0-pre8
- 버그 수정.
- Wave Delays 수정.
- Boss Tracker 수정.
- Player Tracker 수정.
- Grow ESP 제거.
- Grow Guide 제거.
- Boss Spawn Tracker 제거.

## 3.1.0-pre7
- 버그 수정.
- Wave Delays 수정.

## 3.1.0-pre6
- 버그 수정.
- Wave Delays 수정.
- SLA 수정.

## 3.1.0-pre5
- 버그 수정.

## 3.1.0-pre4
- 버그 수정.

## 3.1.0-pre3
- 버그 수정.
- SLA 수정.

## 3.1.0-pre2
- 버그 수정.

## 3.1.0-pre1
- Wave Delays에 Prison맵 추가.
- Prison에서 Boss Wave Alarm, SLA, Boss Spawn Tracker, Boss Tracker가 작동되지 않음.
  <br>Prison에 대한 정확한 정보가 부족하고, 버그를 발생시킬 수 있기에 일부 모드를 Prison에서 작동하지 않도록 했습니다.


## 3.0.0
- 버그 수정.
- Boss Tracker가 보스가 아닌 몹을 표시하는 버그 수정.


## 2.0.0
- 버그 수정.
- Boss Alarm의 이름 수정.
- 닉네임에 "_"가 포함된 경우 일부 기능이 작동하지 않는 버그 수정.


## 1.17.2-pre1
- 버그 수정.
- Player Tracker 추가.
- Boss Tracker 추가.

## 1.17.1
- 버그 수정.

## 1.17.0
- 버그 수정.

## 1.17.0-pre4
- 버그 수정.
- 컨피그 옵션 추가.
- /updatelog 명령어 제거.

## 1.17.0-pre3
- 버그 수정.

## 1.17.0-pre2
- 버그 수정.

## 1.17.0-pre1
- 버그 수정.
- Auto Splits 수정.
- NOTLAST 이름 변경.
- 컨피그 수정.
- Wave Delays 수정.
- Not Last 수정.
  <br>LiveSplit은 잘 사용되지 않기에 Auto Splits에서 LiveSplit을 사용하는 옵션을 제거했습니다.
이제 Wave Delays와 Grow Guide가 Auto Splits 없이 사용할 수 있습니다.
게임 오버 시 Auto Splits의 타이머가 리셋이 아닌 정지되며, Wave Delays가 꺼지지 않고 유지됩니다.


## 1.16.0
- 버그 수정.
- Extra Setting 제거.

## 1.16.0-pre2
- 버그 수정.

## 1.16.0-pre1
- 버그 수정.
- Lrod Order 제거.
- Auto Splits 용 RL 모드 추가.
- 업데이트 채크 수정.
- Extra Setting 추가.
- 웨이브 딜레이 수정.
- Powerup Alarm 용 컨피그 제서.


## 1.15.1
- 버그 수정.
- Countdown 제거.

## 1.15.0
- 버그 수정.
- Auto Splits용 Play Sound 추가.


## 1.14.0
- 버그 수정.
- Zombies Utils와 사용할 경우, Zombies Utils의 타이머를 끔.
- Block Alarm의 Health Indicator 제거.

## 1.14.0-pre4
- 버그 수정.

## 1.14.0-pre3
- 버그 수정.

## 1.14.0-pre2
- 버그 수정.
- Gstep Guide 수정.

## 1.14.0-pre1
- Gstep Guide 추가.
- DPS Counter, NOTLAST, Auto Splits, Powerup Alarm 수정.
- 버그 수정.
  <br>DPS Counter, NOTLAST, Auto Splits, Powerup Alarm의 경우 게임 중 on/off를 잘 전환하지 않기에 컨피그로 옮겼습니다.


## 1.13.0
- 버그 수정.
- Grow ESP 추가.
- SST와 사용할 경우, SST의 웨이브 딜레이를 끔.
  <br>SST의 웨이브 딜레이와 Zombies Addon의 웨이브 딜레이가 겹치기에 SST의 웨이브 딜레이를 강제로 끄도록 했습니다. 현재로는 SST의 웨이브 딜레이를 사용할 수 없습니다.


## 1.12.3
- 버그 수정.
- 업데이트 체크 수정.

## 1.12.2
- 업데이트 체크 수정.
- /info 명령어 수정.

## 1.12.1
- Hologram Remover 제거.
- 업데이트 체크 추가.
  <br>싱글/멀티 플레이에 처음 접속하면 채팅창에 업데이트 알림이 뜹니다.

## 1.12.0
- Auto Splits 전용 Grow Guide 추가.
- 버그 수정.
- 컨피그 수정.
- 화면에 모드 버전 추가.
- 모드 업데이트 시 컨피그 리셋.
- 컨피그에서 모드 기본값을 설정하는 옵션 추가.
  <br>이제 컨피그가 변경되는 업데이트가 있을 경우 컨피그가 리셋되어 더 이상 쓰이지 않은 옵션들이 제거됩니다.


## 1.11.0
- Block Alarm 전용 Health Indicator 추가.
- Hologram Bug Generator의 이름을 Hologram Remover로 변경.
- Block Alarm의 rev, dead 제거.


## 1.10.3
- 버그 수정.

## 1.10.2
- Hologram Bug Generator 추가.

## 1.10.1
- 버그 수정.

## 1.10.0
- 패키지 변경.
- 코드 재구성.
- Lrod Order 추가.
- 고급 SLA 추가.
- 명령어 수정.


## 1.9.2
- 버그 수정.

## 1.9.1
- 버그 수정.

## 1.9.0
- 버그 수정.
- Boss Alarm 추가.
- forge 명령어 버그 수정.
  <br>모드의 명령어가 / 없이 실행되는 버그를 수정했습니다.


## 1.8.9
- 버그 수정.

## 1.8.8
- 버그 수정.
- Auto Splits 전용 Boss Wave Alarm 추가.
  <br>Boss Wave Alarm은 컨피그에서 활성화 가능하며 보스가 스폰하는 웨이브를 표시해줍니다.

## 1.8.7
- 버그 수정.

## 1.8.6
- 버그 수정.
- /zombies 명령어 제거.
- 컨피그에 Zombies Overlay 관련 설정 추가.
- Powerup Alarm 수정.
  <br>명령어보다 컨피그로 사용 여부를 설정하고 자동으로 사용되도록 하는 것이 더 나을 것입니다.

## 1.8.5
- 버그 수정.
- /info 명령어 수정.
- /zombies 명령어 추가.
  <br>/zombies 명령어는 Zombies Overlay를 한국어 환경에서 쓸 수 있도록 하는 명령어입니다.

## 1.8.4
- 버그 수정.
- Auto Splits 수정.

## 1.8.3
- 버그 수정.

## 1.8.2
- 버그 수정.

## 1.8.1
- Powerup Alarm 수정.
- 버그 수정.
- 컨피그에 SLA 관련 설정 추가.
  <br>게임이 시작할 때 자동으로 SLA를 켜주는 설정을 추가했습니다.

## 1.8.0
- Powerup Alarm 추가.
- 버그 수정.
- 컨피그에 Zombies Addon 관련 설정 추가.
- Auto Splits 수정.
  <br>Auto Splits의 기본값이 on으로 변경되었습니다. 오른쪽 위에 표시되는 모드 on/off 여부를 끌 수 있습니다.


## 1.7.6
- 버그 수정.

## 1.7.5
- 버그 수정.

## 1.7.4
- 버그 수정.

## 1.7.3
- 버그 수정.

## 1.7.2
- 버그 수정.

## 1.7.1
- 버그 수정.

## 1.7.0
- Auto Splits 수정.
- /ZSV, /SLA 명령어 수정.
- Auto Rejoin 수정.
- 컨피그에 Auto Splits 관련 설정 ㅊ가. 
<br>웨이브 딜레이를 추가했습니다.


## 1.6.5
- 버그 수정.

## 1.6.4
- 버그 수정.

## 1.6.3
- 버그 수정.

## 1.6.2
- NOTLAST, Auto Rejoin 수정.
- 버그 수정.
  <br>NOTLAST, Auto Rejoin을 한국어 환경에서 사용할 수 있습니다.

## 1.6.1
- Block Alarm 수정.
  <br>Block Alarm을 한국어 환경에서 사용할 수 있습니다.

## 1.6.0
- /setMap, /setstrat, /setlines 명령어의 이름을 /SLA, /ZSV, /ZSVLines로 변경.
- /blockAlarm 명령어 제거.
- /cornering 명령어 추가.
- 컨피그에 Cornering, Block Alarm 관련 설정 추가.


## 1.5.2
- 버그 수정.

## 1.5.1
- 버그 수정.

## 1.5.0
- Auto Rejoin 수정.
- 버그 수정.
- Auto Splits 수정.


## 1.4.5
- Block Alarm 수정.
  <br>Block Alarm이 v1.4.2로 롤백됩니다.

## 1.4.4
- Block Alarm 수정.

## 1.4.3
- 버그 수정.
- Block Alarm을 하이픽셀 언어가 한국어일 때 사용 가능.

## 1.4.2
- 버그 수정.
- Block Alarm 수정.
- /blockAlarm 명령어 추가.

## 1.4.1
- 모드의 메시지 수정.

## 1.4.0
- ZSV, SLA 추가.


## 1.3.0
- Auto Splits 추가.


## 1.2.1
- /updatelog 명령어 추가.
- /info 명령어 수정.

## 1.2.0
- NOTLAST 추가.
- /info 명령어 수정.


## 1.1.0
- ZHF 제거.
  <br>ZHF가 사용 금지됨.


## 1.0.0
- ZHF, Cornering, Block Alarm, DPS Counter 추가.
- Block Alarm 수정.
  <br>Block Alarm의 작동 조건을 3명이 다운 될 경우로 수정했습니다.