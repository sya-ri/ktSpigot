package dev.s7a.ktspigot.listener

import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.plugin.Plugin

/**
 * イベントリスナーをプラグインに登録する
 *
 * @param listener イベントリスナー
 * @since 1.0.0
 */
fun Plugin.registerListener(listener: Listener) {
    proxy.pluginManager.registerListener(this, listener)
}
