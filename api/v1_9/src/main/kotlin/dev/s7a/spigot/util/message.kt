@file:JvmName("Message9")

package dev.s7a.spigot.util

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

/**
 * アクションバーに色付き文字列メッセージを表示する
 *
 * @param message メッセージ
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @since 1.0.0
 */
fun Player.sendActionBarMessage(message: String, altColorChar: Char? = '&') {
    spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message.color(altColorChar)))
}
