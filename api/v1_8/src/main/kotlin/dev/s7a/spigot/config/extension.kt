@file:JvmName("Extension8")

package dev.s7a.spigot.config

import org.bukkit.command.CommandSender

/**
 * コンフィグエラーを出力する
 *
 * @param sender 出力先
 * @see KtConfigBase.checkValues
 * @since 1.0.0
 */
fun List<KtConfigError>.printErrors(sender: CommandSender) {
    getErrors().forEach {
        sender.sendMessage("§e$it")
    }
}
