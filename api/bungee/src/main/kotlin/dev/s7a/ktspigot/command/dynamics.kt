@file:Suppress("DEPRECATION", "unused")

package dev.s7a.ktspigot.command

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * よく使う補完候補
 *
 * @see Dynamics
 * @since 1.0.0
 */
@Deprecated("Dynamics を使ってください")
object Dynamic {
    /**
     * プレイヤー
     *
     * @see Dynamics.Players
     * @since 1.0.0
     */
    @Deprecated("Dynamics::Players を使ってください", ReplaceWith("Dynamics.Players"))
    val Players = KtCommandTabCompleteAction<CommandSender> { ProxyServer.getInstance().players.map(ProxiedPlayer::getName) }

    /**
     * サーバー
     *
     * @see ProxyServer.getServers
     * @since 1.0.0
     */
    @Deprecated("Dynamics::Servers を使ってください", ReplaceWith("Dynamics.Servers"))
    val Servers = KtCommandTabCompleteAction<CommandSender> { ProxyServer.getInstance().servers.keys }
}

/**
 * プレイヤー
 *
 * @see ProxyServer.getPlayers
 * @since 1.0.0
 */
inline val Dynamics.Players
    get() = Dynamic.Players

/**
 * サーバー
 *
 * @see ProxyServer.getServers
 * @since 1.0.0
 */
inline val Dynamics.Servers
    get() = Dynamic.Servers
