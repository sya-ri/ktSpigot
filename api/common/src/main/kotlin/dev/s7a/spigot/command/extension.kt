package dev.s7a.spigot.command

/**
 * タブ補完を定義する処理
 *
 * @since 1.0.0
 */
typealias KtCommandTabCompleteBuilder<T> = KtCommandTabCompleterTree<T>.() -> Unit

/**
 * コマンド実行の処理
 *
 * @since 1.0.0
 */
typealias KtCommandExecuteAction<T> = (KtCommandExecuteParameter<T>) -> Unit
