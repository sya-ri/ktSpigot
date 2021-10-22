package dev.s7a.spigot.listener

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/**
 * イベントリスナーをプラグインに登録する
 *
 * @param listener イベントリスナー
 * @since 1.0.0
 */
fun Plugin.registerListener(listener: Listener) {
    Bukkit.getPluginManager().registerEvents(listener, this)
}
