package showcase

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.type.intValue
import dev.s7a.ktspigot.config.type.materialValue
import dev.s7a.ktspigot.config.type.stringValue
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
        itemConfig = ItemConfig(this).apply {
            load(logger)
        }
    }
}

/**
 * コンフィグ
 */
class ItemConfig(plugin: JavaPlugin) : KtConfig(plugin, "item.yml") {
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
}
// CODE-SNIPPET END
