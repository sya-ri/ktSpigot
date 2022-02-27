package dev.s7a.ktspigot.command

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * よく使う補完候補
 *
 * @see KtCommandTabCompleterTree.dynamic
 * @since 1.0.0
 */
object Dynamics {
    /**
     * プレイヤー
     *
     * @see ProxyServer.getPlayers
     * @since 1.0.0
     */
    val Players = KtCommandTabCompleteAction<CommandSender> { ProxyServer.getInstance().players.map(ProxiedPlayer::getName) }

    /**
     * サーバー
     *
     * @see ProxyServer.getServers
     * @since 1.0.0
     */
    val Servers = KtCommandTabCompleteAction<CommandSender> { ProxyServer.getInstance().servers.keys }
}
