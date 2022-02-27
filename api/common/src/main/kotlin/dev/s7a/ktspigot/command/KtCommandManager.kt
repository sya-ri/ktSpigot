package dev.s7a.ktspigot.command

import dev.s7a.ktspigot.command.internal.KtCommandTabCompleter

/**
 * コマンド
 *
 * @since 1.0.0
 */
class KtCommandManager<T> {
    private var tabCompleter: KtCommandTabCompleter<T>? = null
    private var executeAction: KtCommandExecuteAction<T> = {}

    /**
     * タブ補完を設定する
     *
     * @param action タブ補完処理
     * @since 1.0.0
     */
    fun tabComplete(action: KtCommandTabCompleteBuilder<T>) {
        tabCompleter = KtCommandTabCompleter<T>().apply(action)
    }

    /**
     * 実行処理を設定する
     *
     * @param action 実行処理
     * @since 1.0.0
     */
    fun execute(action: KtCommandExecuteAction<T>) {
        executeAction = action
    }

    /**
     * コマンドを実行する
     *
     * @param sender 実行者
     * @param label コマンド名
     * @param args 実行引数
     * @since 1.0.0
     */
    fun execute(sender: T, label: String, args: Array<out String>): Boolean {
        return try {
            executeAction(KtCommandExecuteParameter(sender, label, args.toList()))
            true
        } catch (ex: KtCommandCancelException) {
            false
        }
    }

    /**
     * コマンドを補完する
     *
     * @param sender 実行者
     * @param args 実行引数
     * @since 1.0.0
     */
    fun onTabComplete(sender: T, args: Array<out String>): List<String> {
        return tabCompleter?.complete(sender, args).orEmpty()
    }
}
