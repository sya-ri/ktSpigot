@file:JvmName("Extension8")

package dev.s7a.spigot.command

import net.md_5.bungee.api.plugin.Plugin

/**
 * ktSpigot を使ってコマンドを登録する
 *
 * @param name コマンド名
 * @param buildAction コマンドの定義処理
 * @since 1.0.0
 */
fun Plugin.ktCommand(name: String, buildAction: KtCommand.() -> Unit) {
    proxy.pluginManager.registerCommand(this, KtCommand(name).apply(buildAction))
}
