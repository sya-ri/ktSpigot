package dev.s7a.spigot.command

/**
 * タブ補完を行う処理
 *
 * @see KtCommandTabCompleterTree.dynamic
 * @since 1.0.0
 */
fun interface KtCommandTabCompleteAction {
    /**
     * 補完処理
     *
     * @param parameter パラメータ
     * @return 補完候補
     * @since 1.0.0
     */
    fun complete(parameter: KtCommandTabCompleteParameter): Collection<String>?
}
