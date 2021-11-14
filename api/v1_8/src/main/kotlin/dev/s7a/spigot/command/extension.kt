package dev.s7a.spigot.command

import org.bukkit.plugin.java.JavaPlugin

/**
 * ktSpigot を使ってコマンドを登録する
 *
 * @param name コマンド名
 * @param buildAction コマンドの定義処理
 * @since 1.0.0
 */
fun JavaPlugin.ktCommand(name: String, buildAction: KtCommand.() -> Unit) {
    val command = getCommand(name) ?: throw IllegalStateException("コマンド /$name が ${this.name} の plugin.yml に登録されていません")
    KtCommand().apply(buildAction).run {
        command.executor = this
        command.tabCompleter = this
    }
}
