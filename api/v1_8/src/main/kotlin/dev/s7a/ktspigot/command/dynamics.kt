@file:Suppress("unused")

package dev.s7a.ktspigot.command

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * オンラインプレイヤーのタブ補完
 *
 * @see Bukkit.getOnlinePlayers
 * @since 1.0.0
 */
fun KtCommandTabCompleterTree<CommandSender>.onlinePlayers(child: KtCommandTabCompleteBuilder<CommandSender>? = null) {
    dynamic({ Bukkit.getOnlinePlayers().map(Player::getName) }, child)
}

/**
 * オフラインプレイヤーのタブ補完
 *
 * @see Bukkit.getOfflinePlayers
 * @since 1.0.0
 */
fun KtCommandTabCompleterTree<CommandSender>.offlinePlayers(child: KtCommandTabCompleteBuilder<CommandSender>? = null) {
    dynamic({ Bukkit.getOfflinePlayers().mapNotNull(OfflinePlayer::getName) }, child)
}
