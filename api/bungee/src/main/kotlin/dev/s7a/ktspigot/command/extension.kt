@file:JvmName("Extension8")

package dev.s7a.ktspigot.command

import net.md_5.bungee.api.plugin.Plugin

/**
 * ktSpigot を使ってコマンドを登録する
 *
 * @param name コマンド名
 * @param permission 実行権限
 * @param aliases コマンドの別名
 * @param buildAction コマンドの定義処理
 * @since 1.0.0
 */
fun Plugin.ktCommand(name: String, permission: String? = null, vararg aliases: String, buildAction: KtCommand.() -> Unit) {
    proxy.pluginManager.registerCommand(this, KtCommand(name, permission, *aliases).apply(buildAction))
}
