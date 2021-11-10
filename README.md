# ktSpigot
[![Kotlin](https://img.shields.io/badge/kotlin-1.5.31-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![ktSpigot](https://img.shields.io/badge/ktSpigot-%E2%AD%90-16E.svg)](https://github.com/sya-ri/ktSpigot)

A Library that Simplifies Spigot with Kotlin.

- [Wiki](https://github.com/sya-ri/ktSpigot/wiki)
- [CONTRIBUTING.md](CONTRIBUTING.md)
- [LICENSE](LICENSE)

## What's this?

[EasySpigotAPI](https://github.com/sya-ri/EasySpigotAPI) の反省点を元に生まれた全く新しいライブラリです。

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
    private val type = materialValue("material").default { Material.values().random() }

    /**
     * コンフィグから整数値を取得する。
     * 設定されていなければ 1 を使う。
     * 数字以外が設定されていてもデフォルト値を使う。
     */
    private val amount = intValue("amount").default(1).force()

    /**
     * コンフィグから文字列を取得する。
     * 設定されていなければ null を使う。
     */
    private val displayName = stringValue("display").nullable()

    /**
     * コンフィグから文字列リストを取得する。
     * 設定されていなければ空になる。
     */
    private val lore = stringValue("lore").list().orEmpty().force()

    /**
     * アイテムとして取得する。
     * [type] にマテリアル以外の値が設定されていると null になる。
     */
    val itemStack: ItemStack?
        get() = type.getValue()?.let { material ->
            ItemStack(material, amount.getValue()).apply {
                itemMeta = itemMeta?.also { meta ->
                    meta.setDisplayName(displayName.getValue())
                    meta.lore = lore.getValue()
                }
            }
        }

    override fun load() {
        // 不正な値があったらログを流す
        checkValues().printErrors(plugin.logger)
    }
}
```
<!-- CODE-SNIPPET END ItemConfig -->

</details>

## Badge

[![ktSpigot](https://img.shields.io/badge/ktSpigot-%E2%AD%90-16E.svg)](https://github.com/sya-ri/ktSpigot)

```
[![ktSpigot](https://img.shields.io/badge/ktSpigot-%E2%AD%90-16E.svg)](https://github.com/sya-ri/ktSpigot)
```
