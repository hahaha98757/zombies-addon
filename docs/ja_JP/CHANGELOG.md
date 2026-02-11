# 更新ログ

## 4.6.3-pr2
2026-02-11
- 追加: Better Zombies Left: DEH モブスポーン情報を追加。

## 4.6.3-pre1
2026-02-09
- 追加：Better Zombies Left。
- コード修正：Mixinクラスのリファクタリング。

## 4.6.2
2026-01-29
- 追加：Last Weaponsがゲームオーバー時にも動作するオプション。
- 修正：Last Weaponsがクールタイム中の技術のアイテム数を表示する。

## 4.6.1
2025-12-23
- 追加：自動更新でダウンロードしたモードの整合性チェック。
- コード修正：GUIクラスをオブジェクトに変更。

## 4.6.0
2025-12-07
- 修正
    - Not Last：言語別のカンマを適用します。2人でカンマを削除
- 追加
    - デバッグモード：ラウンド通過時にNot Last使用可能

## 4.6.0-rc1
2025-12-04
- 追加：日本語翻訳
- 修正：英語、韓国語翻訳

## 4.6.0-pre1
2025-11-14
- 修正：Powerup Patternsの自動タイマー範囲の増加。

## 4.6.0-beta2
2025-11-10
- 変更
    - /sla quick コマンド：ZMPに記載されているハウジングマップを適用します。
        - 削除：mogi_a(Bad Blood)
        - 追加：loliepop5(Alien Arcadium)
    - Semi Player Visibility
        - 透明度の最小値、最大値の設定を追加。
        - 透明度変化速度設定を追加。
            - Linear：一定速度で変化（従来方式）
            - Fixed：最小値に固定
            - Smooth：スムーズに変化（二次関数曲線）
            - Sharp：急速に変化（無理関数曲線）

## 4.6.0-beta1
2025-10-28
- 追加
    - Recorder
    - 例外メッセージ：ゲームとラウンドの開始、レコーダーでの記録と保存に例外メッセージを追加する
- 変更
    - 難易度検出
        - スコアボードで検出（ハイピクセルアップデート）
        - ゲーム途中で生成されたゲームオブジェクトの難易度検知動作を追加
    - デバッグモード：翻訳、テキスト生き残り、出力フレーズにディテールを追加する
- 削除：/zombiesaddon コマンドの難易度変更機能
- 修正
    - Not Last：プレイヤーの名前の色をランクの色と一致させる
    - 言語ファイルのロードを最初の1回だけ実行するように変更
- コード修正：Tools.kt 개편


## 4.5.3
2025-10-28
- バグ修正：キー入力が無視される

## 4.5.2
2025-10-12
- コード修正
    - LastClientTickEvent発生クラスの変更
    - パフォーマンスの向上：コンフィグカテゴリオブジェクトのコレクションの変更
    - モード有効化オプション関連ログの削除
    - キー入力検出関数の呼び出し方法の変更
- バグ修正
    - 誤字修正：Zombies Utils翻訳中の誤字修正

## 4.5.1
2025-10-05
- 追加
    - Korean Patcher：Zombies Utils韓国語翻訳
- 修正
    - クライアント専用モード：モードがサーバーからロードされないように修正
- コード修正：翻訳キーの変更

## 4.5.0
2025-09-30
- 変更
    - アップデートチェック
        - 翻訳文字列の変更
        - 用語の変更
            - 推奨バージョン -> 最新バージョン
            - 最新バージョン -> 開発バージョン
        - バージョンURLの変更
        - バージョン比較方式の変更
        - 出力メッセージの変更
- 修正
    - mcmod.info：설명, 크레딧 수정

## 4.5.0-rc2
- Auto Rejoinを修正：ショートカットのデフォルト値を変更します。パフォーマンスの向上。
- 修正：Auto Rejoinを有効にしている間にNot Lastを無効にします。
- アップデートチェックを修正：ログの修正。コメントを追加。

## 4.5.0-rc1
- Auto Splitsを修正：/autosplits コマンドにオプションを追加します。メッセージの修正。

## 4.5.0-pre2
- 修正：より正確なタイマーとAuto Splits。コンフィグのデフォルト値。Auto SLAがゲーム終了時にSLAをオフ。
- バグ修正：ZSVのショートカットが逆に機能する。
- コード修正：モジュールを継承したクラスをシングルトンオブジェクトに変更します。

## 4.5.0-pre1
- バグ修正：誤った接頭辞。
- ログを追加します。

## 4.5.0-beta7
- バグ修正：Powerup Patternsの自動タイマーが機能しない。ゲーム開始時にこれまで入力されたショートカットが検出される。
- Powerup Patternsを修正：パワーアップの検出範囲の修正。

## 4.5.0-beta6
- バグ修正：Last Weaponsを修正。
- Powerup Patternsを修正：自動タイマーキーを追加。

## 4.5.0-beta5
- バグ修正：バージョンは変更されません。

## 4.5.0-beta4
- バグ修正：間違ったゲームの削除タイミング。

## 4.5.0-beta3
- バグ修正：Prisonでゲーム終了にならない。ゲーム中にモードがアクティブになった場合、ラウンド1から始まります。

## 4.5.0-beta2
- バグ修正：ゲームが終了しない。
- 変更：ゲーム終了時のタイマー停止。

## 4.5.0-beta1
- Internal Timerを修正：より正確なタイマー。
- バグ修正：nullチェックを追加。翻訳修正。ログメッセージを翻訳しない。
- Powerup Patternsを修正：パターンをゲームごとに別々に記録する。
- 追加：/za_debug命令とデバッグモード。Zombies Utilsに韓国語オプションを追加。
- Player Visibilityを修正：SSTのPlayer Invisibleと競合しないように修正。
- 変更：ゲーム処理方法をZombies Utilsに合わせます。


## 4.4.4
- バグ修正：保存された位置情報が読み込まれない。

## 4.4.3
- バグ修正：モジュールのオン/オフが適用されません。

## 4.4.2
- バグ修正：誤ったmixinでコンフィグが開かない。

## 4.4.1
- バグ修正：Wave Delaysの間違ったボスカラー。
- Gui修正：Guiのゲーム終了名を既存の翻訳文字列に変更します。

## 4.4.0
- バグ修正：Last Weaponsに技術の空きスロットが表示されない

## 4.4.0-rc1
- ZSVを修正：文を読み込むときにゲームが停止しないように修正。
- バグ修正：バージョンの読み込みに失敗した場合、不正なバージョンに置き換えるバグ修正。

## 4.4.0-pre4
- バグ修正：言語を変更すると、一部の文字列がクラッシュする可能性があります。
- コード修正：一部のtryステートメントをrunCatching関数で修正。

## 4.4.0-pre3
- バグ修正：Wave Delaysにボスカラーがありません。
- ゲーム開始時に「CustomPlaySound.json」で以前使用法だった「playWave」を「playType」に置き換え。

## 4.4.0-pre2
- Wave Delaysを修正：誤った順序のプレフィックス修正。

## 4.4.0-pre1
- JavaをKotlinに変更。
- バグ修正。
- コードを再構成。
- コンフィグを修正：オプションの名前と説明を翻訳。
- Detect Unlegit Modsを修正：ゲーム開始時にGUIで通知、自動モード削除機能を追加。（Windowsのみ）
- アップデートチェックを修正：推奨バージョンと必須アップデートをGUIで通知し、自動アップデートを追加する。（Windowsのみ）
- バージョン方式の変更。
- Player Visibilityを修正：半透明の範囲調整可能、ゲーム内でのみ動作するように変更。
- Block Alarmを修正：生きていない状態で動作しない、ショートカットのデフォルト値をPに変更。
- Auto Splitsを修正：LiveSplits専用に変更。
- Internal Timerを追加：既存のAuto Splitsの機能と同じ。
- Wave Delaysを修正：GiantとThe Old Oneの接頭辞を修正。
- ZSVを修正：ハングルサポート。
- SLAを修正：windowの名前とテキストの色を削除するオプションを追加。

## 4.3.2
- 過剰な割り当てを減らす。

## 4.3.1
- バグ修正。

## 4.3.0
- 修正なし。

## 4.3.0-pre3
- バグ修正。
- /de、/bb、/aa、/pr コマンドを追加。
- Last Weaponsを修正。

## 4.3.0-pre2
- バグ修正。
- Wave Delaysを修正。
- Powerup Patternsを修正。

## 4.3.0-pre1
- バグ修正。
- コマンドを修正。
- コンフィグに言語オプションを追加。

## 4.2.3
- バグ修正。

## 4.2.2
- バグ修正。
- /zombiesaddon コマンドを追加。
- /info コマンドを削除。

## 4.2.1
- バグ修正。

## 4.2.0
- バグ修正。

## 4.2.0-pre6
- バグ修正。
- Last Weaponsを修正。

## 4.2.0-pre5
- バグ修正。

## 4.2.0-pre4
- バグ修正。
- /waveDelays、/autoSplits コマンドを追加。
- SSTのunlegit機能をブロックしました。

## 4.2.0-pre3
- バグ修正。
- 複数の言語をサポートしていない（4.1.3にロールバック）

## 4.2.0-pre2
- バグ修正。

## 4.2.0-pre1
- コードの最適化。
- バグ修正。
- Corneringの名前をPlay Visibilityに変更。
- PVを修正。
- Powerup Patternsの手動タイマーを追加。
- SLAを修正。
- /ZAHUD コマンドを追加。
- Wave Delaysを修正。
- SST Setting、Zombies Utils Settingを修正。
- 複数の言語をサポートしています。


## 4.1.3
- バグ修正。


## 4.1.2
- バグ修正。


## 4.1.1
- バグ修正。
- Auto Rejoinを修正。


## 4.1.0
- Last Weaponsを修正。

## 4.1.0-pre6
- バグ修正。
- Powerup Patternsを修正。

## 4.1.0-pre5
- Last Weaponsを修正。
- バグ修正。

## 4.1.0-pre4
- バグ修正。

## 4.1.0-pre3
- Text Macroを追加。
- コードの最適化。
- Last Weaponsを修正。

## 4.1.0-pre2
- バグ修正。

## 4.1.0-pre1
- バグ修正。
- Powerup Alarmを修正。
- SST Patchを追加。
- Korean Patchを追加。
- Last Weaponsを追加。
- Detect Unlegit Modsを追加。


## 4.0.1
- バグ修正。
- SLAを修正。
- Powerup Alarmを修正。
- Wave Delaysを修正。


## 4.0.0
- DPS Counterを削除。
- Player Trackerを削除。
- Boss Trackerを削除。
- Gstep Guideを削除。
- Powerup Alarmを削除。


## 3.1.0-pre8
- バグ修正。
- Wave Delaysを修正。
- Boss Trackerを修正。
- Player Trackerを修正。
- Grow ESPを削除。
- Grow Guideを削除。
- Boss Spawn Trackerを削除。

## 3.1.0-pre7
- バグ修正。
- Wave Delaysを修正。

## 3.1.0-pre6
- バグ修正。
- Wave Delaysを修正。
- SLAを修正。

## 3.1.0-pre5
- バグ修正。

## 3.1.0-pre4
- バグ修正。

## 3.1.0-pre3
- バグ修正。
- SLAを修正。

## 3.1.0-pre2
- バグ修正。

## 3.1.0-pre1
- Wave DelaysにPrisonマップを追加。
- Prisonでは、Boss Wave Alarm、SLA、Boss Spawn Tracker、Boss Trackerが機能しません。


## 3.0.0
- バグ修正。
- Boss Trackerがボスではなくモブを表示するバグを修正しました。


## 2.0.0
- バグ修正。
- Boss Alarmの名前を変更。
- ニックネームに「_」が含まれていると一部の機能が機能しないバグを修正。


## 1.17.2-pre1
- バグ修正。
- Player Trackerを追加。
- Boss Trackerを追加。

## 1.17.1
- バグ修正。

## 1.17.0
- バグ修正。

## 1.17.0-pre4
- バグ修正。
- コンフィグのオプションを追加。
- /updatelog コマンドを削除。

## 1.17.0-pre3
- バグ修正。

## 1.17.0-pre2
- バグ修正。

## 1.17.0-pre1
- バグ修正。
- Auto Splitsを修正。
- NOTLASTの名前を変更。
- コンフィグを修正。
- Wave Delaysを修正。
- Not Lastを修正。


## 1.16.0
- バグ修正。
- Extra Settingを削除。

## 1.16.0-pre2
- バグ修正。

## 1.16.0-pre1
- バグ修正。
- Lrod Orderを削除。
- Auto Splits専用のRLモードを追加。
- アップデートチェックを修正。
- Extra Settingを追加。
- ウェーブディレイを修正。
- Powerup Alarm専用のコンフィグを削除。


## 1.15.1
- バグ修正。
- Countdownを削除。

## 1.15.0
- バグ修正。
- Auto Splits専用のPlay Soundを追加。


## 1.14.0
- バグ修正。
- Zombies Utilsと使用する場合、Zombies Utilsのタイマーをオフ。
- Block AlarmのHealth Indicatorを削除。

## 1.14.0-pre4
- バグ修正。

## 1.14.0-pre3
- バグ修正。

## 1.14.0-pre2
- バグ修正。
- Gstep Guideを修正。

## 1.14.0-pre1
- Gstep Guideを追加。
- DPS Counter、NOTLAST、Auto Splits、Powerup Alarmを修正。
- バグ修正。


## 1.13.0
- バグ修正。
- Grow ESPを追加。
- SSTで使用する場合、SSTのウェーブディレイをオフ。


## 1.12.3
- バグ修正。
- アップデートチェックを修正。

## 1.12.2
- アップデートチェックを修正。
- /info コマンドを修正。

## 1.12.1
- Hologram Removerを削除。
- アップデートチェックを追加。

## 1.12.0
- Auto Splits専用のGrow Guideを追加。
- バグ修正。
- コンフィグを修正。
- 画面にmodのバージョンを追加。
- mod更新時のコンフィグリセット。
- コンフィグでmodのデフォルト値を設定するオプションを追加。


## 1.11.0
- Block Alarm専用のHealth Indicatorを追加。
- Hologram Bug Generatorの名前をHologram Removerに変更。
- Block Alarmのrev、deadを削除。


## 1.10.3
- バグ修正。

## 1.10.2
- Hologram Bug Generatorを追加。

## 1.10.1
- バグ修正。

## 1.10.0
- パッケージを変更。
- コードを再構成。
- Lrod Orderを追加。
- 高度SLAを追加。
- コマンドを修正。


## 1.9.2
- バグ修正。

## 1.9.1
- バグ修正。

## 1.9.0
- バグ修正。
- Boss Alarmを追加。
- Forge コマンドのバグ修正。


## 1.8.9
- バグ修正。

## 1.8.8
- バグ修正。
- Auto Splits専用のBoss Wave Alarmを追加。

## 1.8.7
- バグ修正。

## 1.8.6
- バグ修正。
- /zombies コマンドを削除。
- コンフィグにZombies Overlay関連設定を追加。
- Powerup Alarmを修正。

## 1.8.5
- バグ修正。
- /info コマンドを修正。
- /zombies コマンドを追加。

## 1.8.4
- バグ修正。
- Auto Splitsを修正。

## 1.8.3
- バグ修正。

## 1.8.2
- バグ修正。

## 1.8.1
- Powerup Alarmを修正。
- バグ修正。
- コンフィグにSLA関連設定を追加。

## 1.8.0
- Powerup Alarmを追加。
- バグ修正。
- コンフィグにZombies Addon関連設定を追加。
- Auto Splitsを修正。


## 1.7.6
- バグ修正。

## 1.7.5
- バグ修正。

## 1.7.4
- バグ修正。

## 1.7.3
- バグ修正。

## 1.7.2
- バグ修正。

## 1.7.1
- バグ修正。

## 1.7.0
- Auto Splitsを修正。
- /ZSV、/SLA コマンドを修正。
- Auto Rejoinを修正。
- コンフィグにAuto Splits関連設定を追加。


## 1.6.5
- バグ修正。

## 1.6.4
- バグ修正。

## 1.6.3
- バグ修正。

## 1.6.2
- NOTLAST、Auto Rejoinを修正。
- バグ修正。

## 1.6.1
- Block Alarmを修正。

## 1.6.0
- /setMap、/setstrat、/setlines コマンドの名前を/SLA、/ZSV、/ZSVLinesに変更。
- /blockAlarm コマンドを削除。
- /cornering コマンドを削除。
- コンフィグにCornering、Block Alarm関連設定を追加。


## 1.5.2
- バグ修正。

## 1.5.1
- バグ修正。

## 1.5.0
- Auto Rejoinを修正。
- バグ修正。
- Auto Splitsを修正。


## 1.4.5
- Block Alarmを修正。

## 1.4.4
- Block Alarmを修正。

## 1.4.3
- バグ修正。
- Block AlarmをHypixel言語が韓国語のときに使用可能。

## 1.4.2
- バグ修正。
- Block Alarmを修正。
- /blockAlarm コマンドを追加。

## 1.4.1
- Modのメッセージを修正。

## 1.4.0
- ZSV, SLAを追加。


## 1.3.0
- Auto Splitsを追加。


## 1.2.1
- /updatelog コマンドを追加。
- /info コマンドを修正。

## 1.2.0
- NOTLASTを追加。
- /info コマンドを修正。


## 1.1.0
- ZHFを削除。


## 1.0.0
- ZHF、Cornering、Block Alarm、DPS Counterを追加。
- Block Alarmを修正。