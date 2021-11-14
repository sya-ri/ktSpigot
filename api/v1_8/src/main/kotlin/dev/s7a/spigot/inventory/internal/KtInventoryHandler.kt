package dev.s7a.spigot.inventory.internal

import dev.s7a.spigot.inventory.KtInventory
import dev.s7a.spigot.util.VirtualPlayer
import dev.s7a.spigot.util.VirtualPlayer.Companion.toVirtual
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.plugin.Plugin

/**
 * イベントハンドラー
 *
 * @param plugin イベントハンドリングに使うプラグイン
 * @since 1.0.0
 */
internal class KtInventoryHandler(plugin: Plugin) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    /**
     * インベントリを開かせる
     *
     * @param player 開くプレイヤー
     * @param inventory 開くインベントリ
     * @since 1.0.0
     */
    internal fun open(player: Player, inventory: KtInventory) {
        player.openInventory(inventory)
    }

    @EventHandler
    fun on(event: InventoryClickEvent) {
        val inventory = event.inventory as? KtInventory ?: return
        if (inventory.isCancel) {
            event.isCancelled = true
        }
        inventory.actions[event.slot]?.invoke(event)
        inventory.onClick?.invoke(event)
    }

    @EventHandler
    fun on(event: InventoryCloseEvent) {
        val inventory = event.inventory as? KtInventory ?: return
        inventory.onClose?.invoke(event)
    }

    companion object {
        private val handlers = mutableMapOf<Plugin, KtInventoryHandler>()

        /**
         * イベントハンドラーを取得する
         *
         * @param plugin イベントハンドリングに使うプラグイン
         * @return
         */
        fun get(plugin: Plugin) = handlers.getOrPut(plugin) { KtInventoryHandler(plugin) }
    }
}
