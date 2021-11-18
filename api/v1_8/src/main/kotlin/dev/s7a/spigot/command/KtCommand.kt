package dev.s7a.spigot.command

import dev.s7a.spigot.command.internal.KtCommandTabCompleter
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

/**
 * コマンド
 *
 * @since 1.0.0
 */
class KtCommand internal constructor() : CommandExecutor, TabCompleter {
    private var tabCompleter: KtCommandTabCompleter? = null
    private var executeAction: KtCommandExecuteAction = {}

    /**
     * タブ補完を設定する
     *
     * @param action タブ補完処理
     * @since 1.0.0
     */
    fun tabComplete(action: KtCommandTabCompleteBuilder) {
        tabCompleter = KtCommandTabCompleter().apply(action)
    }

    /**
     * 実行処理を設定する
     *
     * @param action 実行処理
     * @since 1.0.0
     */
    fun execute(action: KtCommandExecuteAction) {
        executeAction = action
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return try {
            executeAction(KtCommandExecuteParameter(sender, label, args.toList()))
            true
        } catch (ex: KtCommandCancelException) {
            false
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        return tabCompleter?.complete(sender, args).orEmpty()
    }
}
