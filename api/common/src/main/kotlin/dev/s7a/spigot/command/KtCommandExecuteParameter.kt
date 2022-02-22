package dev.s7a.spigot.command

/**
 * コマンドの実行パラメータ
 *
 * @property sender 実行者
 * @property label コマンド名
 * @property args 実行引数
 * @since 1.0.0
 */
data class KtCommandExecuteParameter<T>(val sender: T, val label: String, val args: List<String>)
