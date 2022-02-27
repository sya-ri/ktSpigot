@file:JvmName("Message8")

package dev.s7a.ktspigot.util

import dev.s7a.ktspigot.component.KtComponentBuildAction
import dev.s7a.ktspigot.component.buildComponent
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

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

/**
 * チャットにコンポーネントメッセージを送信する
 *
 * @param buildAction メッセージの生成処理
 * @since 1.0.0
 */
inline fun Player.sendChatMessage(buildAction: KtComponentBuildAction) {
    sendChatMessage(*buildComponent(buildAction))
}

/**
 * チャットにコンポーネントメッセージを送信する
 *
 * @param message メッセージ
 * @since 1.0.0
 */
fun Player.sendChatMessage(vararg message: BaseComponent) {
    spigot().sendMessage(*message)
}
