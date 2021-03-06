package dev.s7a.ktspigot.inventory.internal

import dev.s7a.ktspigot.inventory.KtInventory
import dev.s7a.ktspigot.inventory.KtInventoryClickEventResult
import dev.s7a.ktspigot.listener.registerListener
import dev.s7a.ktspigot.player.VirtualPlayer
import org.bukkit.entity.HumanEntity
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
    private val players = mutableMapOf<VirtualPlayer, KtInventory>()

    init {
        plugin.registerListener(this)
    }

    /**
     * インベントリを開かせる
     *
     * @param player 開くプレイヤー
     * @param inventory 開くインベントリ
     * @since 1.0.0
     */
    internal fun open(player: HumanEntity, inventory: KtInventory) {
        val virtualPlayer = VirtualPlayer(player.uniqueId)
        player.openInventory(inventory.bukkitInventory)
        players[virtualPlayer] = inventory
    }

    @EventHandler
    fun on(event: InventoryClickEvent) {
        val player = event.whoClicked
        val virtualPlayer = VirtualPlayer(player.uniqueId)
        val inventory = players[virtualPlayer] ?: return
        if (inventory.bukkitInventory !== event.inventory) {
            players.remove(virtualPlayer)
            return
        }
        inventory.onClick?.invoke(event)
        val isInvoked = inventory.actions[event.slot]?.invoke(event) != null
        inventory.onClickResult?.invoke(KtInventoryClickEventResult(event, isInvoked))
    }

    @EventHandler
    fun on(event: InventoryCloseEvent) {
        val player = event.player
        val virtualPlayer = VirtualPlayer(player.uniqueId)
        val inventory = players.remove(virtualPlayer) ?: return
        if (inventory.bukkitInventory === event.inventory) {
            inventory.onClose?.invoke(event)
        }
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
