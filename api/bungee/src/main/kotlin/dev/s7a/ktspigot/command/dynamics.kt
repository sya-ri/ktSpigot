@file:Suppress("unused")

package dev.s7a.ktspigot.command

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * プレイヤーのタブ補完
 *
 * @see ProxyServer.getPlayers
 * @since 1.0.0
 */
fun KtCommandTabCompleterTree<CommandSender>.players(child: KtCommandTabCompleteBuilder<CommandSender>? = null) {
    dynamic({ ProxyServer.getInstance().players.map(ProxiedPlayer::getName) }, child)
}

/**
 * サーバーのタブ補完
 *
 * @see ProxyServer.getServers
 * @since 1.0.0
 */
fun KtCommandTabCompleterTree<CommandSender>.servers(child: KtCommandTabCompleteBuilder<CommandSender>? = null) {
    dynamic({ ProxyServer.getInstance().servers.keys }, child)
}
