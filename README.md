# ktSpigot
[![Kotlin](https://img.shields.io/badge/kotlin-1.7.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![ktSpigot](https://img.shields.io/badge/ktSpigot-%E2%AD%90-16E.svg)](https://github.com/sya-ri/ktSpigot)

A Library that Simplifies Spigot with Kotlin.

- [Wiki](https://ktspigot.s7a.dev)
- [API Document](https://gh.s7a.dev/ktSpigot)
- [CONTRIBUTING.md](CONTRIBUTING.md)
- [LICENSE](LICENSE)

## What's this?

[EasySpigotAPI](https://github.com/sya-ri/EasySpigotAPI) の反省点を元に生まれた全く新しいライブラリです。

### Support platform

- Spigot `1.8.x` 〜 `1.18.x`
- BungeeCord

## Example

### Config

<details>
<summary>コンフィグにアイテムの情報を格納しておく</summary>

<!-- CODE-SNIPPET BEGIN ItemConfig -->
```kotlin
/**
 * プラグインのメインクラス
 */
class Main : JavaPlugin() {
    companion object {
        lateinit var itemConfig: ItemConfig
    }

    override fun onEnable() {
        // プラグイン起動時にコンフィグを読み込む
        itemConfig = ItemConfig(this).apply(ItemConfig::load)
    }
}

/**
 * コンフィグ
 */
class ItemConfig(private val plugin: JavaPlugin) : KtConfig(plugin, "item.yml") {
    /**
     * コンフィグからマテリアルを取得する。
     * 設定されていなければランダムなマテリアルを使う。
     * デフォルト値は初回読み込み時にファイルへ書き込まれる。
     */
    private val type by materialValue("material").default { Material.values().random() }

    /**
     * コンフィグから整数値を取得する。
     * 設定されていなければ 1 を使う。
     * 数字以外が設定されていてもデフォルト値を使う。
     */
    private val amount by intValue("amount").default(1).force()

    /**
     * コンフィグから文字列を取得する。
     * 設定されていなければ null を使う。
     */
    private val displayName by stringValue("display").nullable()

    /**
     * コンフィグから文字列リストを取得する。
     * 設定されていなければ空になる。
     */
    private val lore by stringValue("lore").list().orEmpty().force()

    /**
     * アイテムとして取得する。
     * [type] にマテリアル以外の値が設定されていると null になる。
     */
    val itemStack: ItemStack?
        get() = type?.let { type ->
            ItemStack(type, amount).apply {
                itemMeta = itemMeta?.also { meta ->
                    meta.setDisplayName(displayName)
                    meta.lore = lore
                }
            }
        }

    override fun load() {
        super.load()
        // 不正な値があったらログを流す
        checkValues().printErrors(plugin.logger)
    }
}
```
<!-- CODE-SNIPPET END ItemConfig -->

</details>

## Installation

Minecraft のバージョン毎にライブラリがあります。

### RELEASE

基本的にこちらを使ってください。安定して使うことができます。

:warning: 現在、開発中です。`1.0.0` がリリースされるまで `1.0.0-SNAPSHOT` を使ってください。

### SNAPSHOT

開発途中の最新バージョンを試すことができます。リリースされるまでに変更される可能性があるので、注意してください。

<details>
<summary>build.gradle.kts</summary>

```kotlin
repositories {
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    // BungeeCord
    implementation("dev.s7a:ktSpigot-bungee:1.0.0-SNAPSHOT")

    // Spigot 1.8.x
    implementation("dev.s7a:ktSpigot-v1_8:1.0.0-SNAPSHOT")

    // Spigot 1.9.x
    implementation("dev.s7a:ktSpigot-v1_9:1.0.0-SNAPSHOT")

    // Spigot 1.10.x
    implementation("dev.s7a:ktSpigot-v1_10:1.0.0-SNAPSHOT")

    // Spigot 1.11.x
    implementation("dev.s7a:ktSpigot-v1_11:1.0.0-SNAPSHOT")

    // Spigot 1.12.x
    implementation("dev.s7a:ktSpigot-v1_12:1.0.0-SNAPSHOT")

    // Spigot 1.13.x
    implementation("dev.s7a:ktSpigot-v1_13:1.0.0-SNAPSHOT")

    // Spigot 1.14.x
    implementation("dev.s7a:ktSpigot-v1_14:1.0.0-SNAPSHOT")

    // Spigot 1.15.x
    implementation("dev.s7a:ktSpigot-v1_15:1.0.0-SNAPSHOT")

    // Spigot 1.16.x
    implementation("dev.s7a:ktSpigot-v1_16:1.0.0-SNAPSHOT")

    // Spigot 1.17.x
    implementation("dev.s7a:ktSpigot-v1_17:1.0.0-SNAPSHOT")

    // Spigot 1.18.x
    implementation("dev.s7a:ktSpigot-v1_18:1.0.0-SNAPSHOT")
}
```

</details>

## Badge

[![ktSpigot](https://img.shields.io/badge/ktSpigot-%E2%AD%90-16E.svg)](https://github.com/sya-ri/ktSpigot)

```
[![ktSpigot](https://img.shields.io/badge/ktSpigot-%E2%AD%90-16E.svg)](https://github.com/sya-ri/ktSpigot)
```
