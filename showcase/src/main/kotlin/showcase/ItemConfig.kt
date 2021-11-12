package showcase

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.checkValues
import dev.s7a.spigot.config.intValue
import dev.s7a.spigot.config.materialValue
import dev.s7a.spigot.config.printErrors
import dev.s7a.spigot.config.stringValue
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

// CODE-SNIPPET BEGIN
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
        super.load()
        // 不正な値があったらログを流す
        checkValues().printErrors(plugin.logger)
    }
}
// CODE-SNIPPET END
