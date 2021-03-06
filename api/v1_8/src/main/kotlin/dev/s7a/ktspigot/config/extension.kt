@file:JvmName("Extension8")

package dev.s7a.ktspigot.config

import dev.s7a.ktspigot.message.sendChatMessage
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

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

/**
 * リソースからコンフィグをコピーする
 *
 * @param plugin プラグイン
 * @param resourceName リソース名
 * @see KtConfigBase.onCreateNewFile
 * @since 1.0.0
 */
fun KtConfig.copyFromResource(plugin: JavaPlugin, resourceName: String) {
    plugin.getResource(resourceName).copyTo(file.outputStream())
}
