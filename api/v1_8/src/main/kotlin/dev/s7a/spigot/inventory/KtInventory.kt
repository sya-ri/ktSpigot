package dev.s7a.spigot.inventory

import dev.s7a.spigot.inventory.internal.KtInventoryHandler
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * インベントリ
 *
 * @property handler イベントハンドラー
 * @property bukkitInventory インベントリ
 * @since 1.0.0
 */
class KtInventory internal constructor(private val handler: KtInventoryHandler, val bukkitInventory: Inventory) {
    /**
     * クリック時の処理
     *
     * @since 1.0.0
     */
    internal var onClick: ((InventoryClickEvent) -> Unit)? = null

    /**
     * 閉じる時の処理
     *
     * @since 1.0.0
     */
    internal var onClose: ((InventoryCloseEvent) -> Unit)? = null

    /**
     * クリックアクション一覧
     *
     * @since 1.0.0
     */
    internal val actions = mutableMapOf<Int, (InventoryClickEvent) -> Unit>()

    /**
     * デフォルトでクリックイベントをキャンセルする
     *
     * @since 1.0.0
     */
    var isCancel = true

    /**
     * クリック時の処理を変更する
     *
     * @param action 処理
     * @since 1.0.0
     */
    fun onClick(action: (InventoryClickEvent) -> Unit) {
        onClick = action
    }

    /**
     * 閉じる時の処理を変更する
     *
     * @param action 処理
     * @since 1.0.0
     */
    fun onClose(action: (InventoryCloseEvent) -> Unit) {
        onClose = action
    }

    /**
     * プレイヤーにインベントリを開かせる
     *
     * @param player プレイヤー
     * @since 1.0.0
     */
    fun open(player: Player) {
        handler.open(player, this)
    }
}
