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
     * クリック時の処理。アイテム毎のクリックアクションが処理される前に実行する
     *
     * デフォルトは イベントキャンセル
     *
     * @since 1.0.0
     */
    internal var onClick: KtInventoryClickEventHandler? = {
        it.isCancelled = true
    }

    /**
     * クリック時の処理。アイテム毎のクリックアクションが処理された後に実行する
     *
     * @since 1.0.0
     */
    internal var onClickResult: KtInventoryClickEventResultHandler? = null

    /**
     * 閉じる時の処理
     *
     * @since 1.0.0
     */
    internal var onClose: KtInventoryCloseEventHandler? = null

    /**
     * クリックアクション一覧
     *
     * @since 1.0.0
     */
    internal val actions = mutableMapOf<Int, KtInventoryClickEventHandler>()

    /**
     * クリック時の処理を変更する。アイテム毎のクリックアクションが処理される前に実行する
     *
     * デフォルトは イベントキャンセル
     *
     * @param action 処理
     * @since 1.0.0
     */
    fun onClick(action: KtInventoryClickEventHandler?) {
        onClick = action
    }

    /**
     * クリック時の処理を変更する。アイテム毎のクリックアクションが処理された後に実行する
     *
     * @param action 処理
     * @since 1.0.0
     */
    fun onClickResult(action: KtInventoryClickEventResultHandler?) {
        onClickResult = action
    }

    /**
     * 閉じる時の処理を変更する
     *
     * @param action 処理
     * @since 1.0.0
     */
    fun onClose(action: KtInventoryCloseEventHandler?) {
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
