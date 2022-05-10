@file:Suppress("DEPRECATION", "unused")

package dev.s7a.ktspigot.command

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * よく使う補完候補
 *
 * @see Dynamics
 * @since 1.0.0
 */
@Deprecated("Dynamics を使ってください")
object Dynamic {
    /**
     * オンラインプレイヤー
     *
     * @see Dynamics.OnlinePlayers
     * @since 1.0.0
     */
    @Deprecated("Dynamics::OnlinePlayers を使ってください", ReplaceWith("Dynamics.OnlinePlayers"))
    val OnlinePlayers = KtCommandTabCompleteAction<CommandSender> { Bukkit.getOnlinePlayers().map(Player::getName) }

    /**
     * オフラインプレイヤー
     *
     * @see Dynamics.OfflinePlayers
     * @since 1.0.0
     */
    @Deprecated("Dynamics::OfflinePlayers を使ってください", ReplaceWith("Dynamics.OfflinePlayers"))
    val OfflinePlayers = KtCommandTabCompleteAction<CommandSender> { Bukkit.getOfflinePlayers().mapNotNull(OfflinePlayer::getName) }
}

/**
 * オンラインプレイヤー
 *
 * @see Bukkit.getOnlinePlayers
 * @since 1.0.0
 */
inline val Dynamics.OnlinePlayers
    get() = Dynamic.OnlinePlayers

/**
 * オフラインプレイヤー
 *
 * @see Bukkit.getOfflinePlayers
 * @since 1.0.0
 */
inline val Dynamics.OfflinePlayers
    get() = Dynamic.OfflinePlayers
