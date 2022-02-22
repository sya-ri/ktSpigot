@file:JvmName("MessageBungee")

package dev.s7a.spigot.util

import dev.s7a.spigot.component.KtComponentBuildAction
import dev.s7a.spigot.component.buildComponent
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.BaseComponent

/**
 * チャットに色付き文字列メッセージを送信する
 *
 * @param message メッセージ
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @since 1.0.0
 */
@Suppress("Deprecation")
fun CommandSender.sendChatMessage(message: String, altColorChar: Char? = '&') {
    sendMessage(message.color(altColorChar))
}

/**
 * チャットにコンポーネントメッセージを送信する
 *
 * @param buildAction メッセージの生成処理
 * @since 1.0.0
 */
inline fun CommandSender.sendChatMessage(buildAction: KtComponentBuildAction) {
    sendChatMessage(*buildComponent(buildAction))
}

/**
 * チャットにコンポーネントメッセージを送信する
 *
 * @param message メッセージ
 * @since 1.0.0
 */
fun CommandSender.sendChatMessage(vararg message: BaseComponent) {
    sendMessage(*message)
}
