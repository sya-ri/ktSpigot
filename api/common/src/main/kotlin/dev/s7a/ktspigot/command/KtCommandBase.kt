package dev.s7a.ktspigot.command

/**
 * コマンド
 *
 * @since 1.0.0
 */
interface KtCommandBase<T> {
    /**
     * タブ補完を設定する
     *
     * @param action タブ補完処理
     * @since 1.0.0
     */
    fun tabComplete(action: KtCommandTabCompleteBuilder<T>)

    /**
     * 実行処理を設定する
     *
     * @param action 実行処理
     * @since 1.0.0
     */
    fun execute(action: KtCommandExecuteAction<T>)
}
