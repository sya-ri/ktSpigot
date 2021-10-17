package dev.s7a.spigot.command.internal

import dev.s7a.spigot.command.KtCommandCancelException
import dev.s7a.spigot.command.KtCommandExecuteParameter
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

/**
 * コマンド
 *
 * @param executeAction 実行処理
 * @param tabCompleter タブ補完
 * @since 1.0.0
 */
internal class KtCommand(
    private val executeAction: (KtCommandExecuteParameter) -> Unit,
    private val tabCompleter: KtCommandTabCompleter,
) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return try {
            executeAction(KtCommandExecuteParameter(sender, label, args.toList()))
            true
        } catch (ex: KtCommandCancelException) {
            false
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        return tabCompleter.complete(sender, args)
    }
}
