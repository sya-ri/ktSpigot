package dev.s7a.ktspigot.inventory

import dev.s7a.ktspigot.inventory.internal.KtInventoryHandler
import dev.s7a.ktspigot.util.color
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

/**
 * [InventoryClickEvent] のハンドリング処理
 *
 * @see KtInventory.onClickResult
 * @since 1.0.0
 */
typealias KtInventoryClickEventHandler = (InventoryClickEvent) -> Unit

/**
 * [KtInventory.onClickResult] で使われるパラメータ
 *
 * @property event クリックイベント
 * @property isInvoked アイテム毎のクリックイベントが実行されたか
 * @since 1.0.0
 */
data class KtInventoryClickEventResult(val event: InventoryClickEvent, val isInvoked: Boolean)

/**
 * [InventoryClickEvent] のハンドリング処理
 *
 * @see KtInventory.onClickResult
 * @since 1.0.0
 */
typealias KtInventoryClickEventResultHandler = (KtInventoryClickEventResult) -> Unit

/**
 * [InventoryCloseEvent] のハンドリング処理
 *
 * @see KtInventory.onClose
 * @since 1.0.0
 */
typealias KtInventoryCloseEventHandler = (InventoryCloseEvent) -> Unit

/**
 * ktSpigot を使ってインベントリを操作する
 *
 * @param bukkitInventory インベントリ
 * @param action インベントリの操作処理
 * @return [KtInventory]
 * @since 1.0.0
 */
fun Plugin.ktInventory(bukkitInventory: Inventory, action: KtInventory.() -> Unit): KtInventory {
    val handler = KtInventoryHandler.get(this)
    return KtInventory(handler, bukkitInventory).apply(action)
}

/**
 * ktSpigot を使ってインベントリを操作する
 *
 * @param title タイトル
 * @param line インベントリ行数
 * @param altColorChar [title].[color] で使う文字 / '&'
 * @param action インベントリの操作処理
 * @return [KtInventory]
 * @since 1.0.0
 */
fun Plugin.ktInventory(title: String, line: Int = 3, altColorChar: Char? = '&', action: KtInventory.() -> Unit): KtInventory {
    require(line in 1..6)
    return ktInventory(Bukkit.createInventory(null, line * 9, title.color(altColorChar)), action)
}
