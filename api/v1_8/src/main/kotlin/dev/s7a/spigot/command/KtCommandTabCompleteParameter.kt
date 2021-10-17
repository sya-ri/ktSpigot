package dev.s7a.spigot.command

import org.bukkit.command.CommandSender

/**
 * コマンドのタブ補完パラメータ
 *
 * @property sender 実行者
 * @property args 入力済み引数
 * @since 1.0.0
 */
data class KtCommandTabCompleteParameter(val sender: CommandSender, val args: List<String>)
