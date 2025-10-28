# 업데이트 로그

## 4.5.3
2025-10-28
- 버그 수정: 키 입력이 무시됨

## 4.5.2
2025-10-12
- 코드 수정:
  - LastClientTickEvent 발생 클래스 변경
  - 성능 향상: 컨피그 카테고리 객체의 컬렉션 변경
  - 모드 활성화 옵션 관련 로그 제거
  - 키 입력 감지 함수 호출 방식 변경
- 버그 수정:
  - 오타 수정: Zombies Utils 번역 중 오타 수정

## 4.5.1
2025-10-05
- 추가
  - Korean Patcher: Zombies Utils 한국어 번역
- 수정
  - 클라이언트 전용 모드: 모드가 서버에서 로드되지 않도록 수정
- 코드 수정: 번역 키 수정

## 4.5.0
2025-09-30
- 변경
  - 업데이트 체크
    - 번역 문자열 수정
    - 용어 변경
      - 추천 버전 -> 최신 버전
      - 최신 버전 -> 개발 버전
    - 버전 URL 변경
    - 버전 비교 방식 변경
    - 출력 메시지 변경
- 수정
  - mcmod.info: 설명, 크레딧 수정

## 4.5.0-rc2
- Auto Rejoin 수정: 단축키 기본값 변경. 성능 향상.
- 수정: Auto Rejoin 활성화 중 Not Last 비활성화.
- 업데이트 체크 수정: 로그 수정. 주석 추가.

## 4.5.0-rc1
- Auto Splits 수정: /autosplits 명령어에 옵션 추가. 메시지 수정.

## 4.5.0-pre2
- 수정: 더 정확한 타이머 및 Auto Splits. 컨피그 기본값. Auto SLA가 게임 종료 시 SLA를 끔.
- 버그 수정: ZSV의 단축키가 반대로 작동함.
- 코드 수정: 모듈을 상속한 클래스들을 싱글톤 객체로 변경.

## 4.5.0-pre1
- 버그 수정: 잘못된 접두사.
- 로그 추가.

## 4.5.0-beta7
- 버그 수정: Powerup Patterns의 자동 타이머가 작동하지 않음. 게임 시작 시 그동안 입력된 단축키가 감지됨.
- Powerup Patterns 수정: 파워업의 감지 범위 수정.

## 4.5.0-beta6
- 버그 수정: Last Weapons 수정.
- Powerup Patterns 수정: 자동 타이머 키 추가.

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
- 업데이트 체크 수정: 추천 버전과 필수 업데이트를 GUI로 알림, 자동 업데이트 추가.(윈도우 전용)
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