package dev.s7a.spigot.command

import dev.s7a.spigot.command.internal.KtCommand
import dev.s7a.spigot.command.internal.KtCommandTabCompleter

/**
 * コマンドビルダー
 *
 * @since 1.0.0
 */
class KtCommandBuilder internal constructor() {
    private var completeAction: KtCommandTabCompleterTree.() -> Unit = {}
    private var executeAction: (KtCommandExecuteParameter) -> Unit = {}

    /**
     * タブ補完を定義する
     *
     * @param action タブ補完処理
     * @since 1.0.0
     */
    fun tabComplete(action: KtCommandTabCompleterTree.() -> Unit) {
        completeAction = action
    }

    /**
     * 実行処理を定義する
     *
     * @param action 実行処理
     * @since 1.0.0
     */
    fun execute(action: (KtCommandExecuteParameter) -> Unit) {
        executeAction = action
    }

    /**
     * [KtCommand] を生成する
     *
     * @return [KtCommand]
     * @since 1.0.0
     */
    internal fun build() = KtCommand(executeAction, KtCommandTabCompleter().apply(completeAction))
}
