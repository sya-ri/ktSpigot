package dev.s7a.spigot.command

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

/**
 * よく使う補完候補
 *
 * @see KtCommandTabCompleterTree.dynamic
 * @since 1.0.0
 */
object Dynamics {
    /**
     * オンラインプレイヤー
     *
     * @see Bukkit.getOnlinePlayers
     * @since 1.0.0
     */
    val OnlinePlayers: (KtCommandTabCompleteParameter) -> List<String> = { Bukkit.getOnlinePlayers().map(Player::getName) }

    /**
     * オフラインプレイヤー
     *
     * @see Bukkit.getOfflinePlayers
     * @since 1.0.0
     */
    val OfflinePlayers: (KtCommandTabCompleteParameter) -> List<String> = { Bukkit.getOfflinePlayers().mapNotNull(OfflinePlayer::getName) }
}
