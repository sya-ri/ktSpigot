package dev.s7a.spigot.command

/**
 * コマンドの処理をキャンセルする
 *
 * この例外を投げることで [org.bukkit.command.CommandExecutor.onCommand] の戻り値が false になる
 *
 * @since 1.0.0
 */
class KtCommandCancelException : Exception()
