package dev.s7a.spigot.inventory

import dev.s7a.spigot.inventory.internal.KtInventoryHandler
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

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
    internal var onClick: InventoryClickEventHandler? = null

    /**
     * 閉じる時の処理
     *
     * @since 1.0.0
     */
    internal var onClose: InventoryCloseEventHandler? = null

    /**
     * クリックアクション一覧
     *
     * @since 1.0.0
     */
    internal val actions = mutableMapOf<Int, InventoryClickEventHandler>()

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
    fun onClick(action: InventoryClickEventHandler) {
        onClick = action
    }

    /**
     * 閉じる時の処理を変更する
     *
     * @param action 処理
     * @since 1.0.0
     */
    fun onClose(action: InventoryCloseEventHandler) {
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
