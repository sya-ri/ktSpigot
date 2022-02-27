package dev.s7a.ktspigot.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

/**
 * コマンド
 *
 * @since 1.0.0
 */
class KtCommand : KtCommandBase<CommandSender>, CommandExecutor, TabCompleter {
    private val manager = KtCommandManager<CommandSender>()

    override fun execute(action: KtCommandExecuteAction<CommandSender>) {
        manager.execute(action)
    }

    override fun tabComplete(action: KtCommandTabCompleteBuilder<CommandSender>) {
        manager.tabComplete(action)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return manager.execute(sender, label, args)
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        return manager.onTabComplete(sender, args)
    }
}
