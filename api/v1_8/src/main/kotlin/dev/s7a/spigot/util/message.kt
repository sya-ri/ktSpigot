@file:JvmName("Message8")

package dev.s7a.spigot.util

import org.bukkit.command.CommandSender

/**
 * チャットに色付き文字列メッセージを送信する
 *
 * @param message メッセージ
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @since 1.0.0
 */
fun CommandSender.sendChatMessage(message: String, altColorChar: Char? = '&') {
    sendMessage(message.color(altColorChar))
}
