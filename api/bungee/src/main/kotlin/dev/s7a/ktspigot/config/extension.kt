@file:JvmName("ExtensionBungee")

package dev.s7a.ktspigot.config

import dev.s7a.ktspigot.util.sendChatMessage
import net.md_5.bungee.api.CommandSender

/**
 * コンフィグエラーを出力する
 *
 * @param sender 出力先
 * @see KtConfigBase.checkValues
 * @since 1.0.0
 */
fun List<KtConfigError>.printErrors(sender: CommandSender) {
    getErrors().forEach {
        sender.sendChatMessage("&e$it")
    }
}
