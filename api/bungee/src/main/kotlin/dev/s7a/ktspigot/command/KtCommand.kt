package dev.s7a.ktspigot.command

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command
import net.md_5.bungee.api.plugin.TabExecutor

/**
 * コマンド
 *
 * @param name コマンド名
 * @param permission 実行権限
 * @param aliases コマンドの別名
 * @since 1.0.0
 */
class KtCommand(name: String, permission: String?, vararg aliases: String) : KtCommandBase<CommandSender>, Command(name, permission, *aliases), TabExecutor {
    private val manager = KtCommandManager<CommandSender>()

    override fun execute(action: KtCommandExecuteAction<CommandSender>) {
        manager.execute(action)
    }

    override fun tabComplete(action: KtCommandTabCompleteBuilder<CommandSender>) {
        manager.tabComplete(action)
    }

    override fun execute(sender: CommandSender, args: Array<out String>) {
        manager.execute(sender, name, args)
    }

    override fun onTabComplete(sender: CommandSender, args: Array<out String>): List<String> {
        return manager.onTabComplete(sender, args)
    }
}
