@file:JvmName("ExtensionBungee")

package dev.s7a.ktspigot.message

import dev.s7a.ktspigot.component.KtComponentBuildAction
import dev.s7a.ktspigot.component.buildComponent
import dev.s7a.ktspigot.component.textComponents
import dev.s7a.ktspigot.util.color
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.connection.ProxiedPlayer

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

/**
 * アクションバーに色付き文字列メッセージを表示する
 *
 * @param message メッセージ
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @since 1.0.0
 */
fun ProxiedPlayer.sendActionBarMessage(message: String, altColorChar: Char? = '&') {
    sendActionBarMessage {
        append(message, altColorChar = altColorChar)
    }
}

/**
 * アクションバーにコンポーネントメッセージを送信する
 *
 * @param buildAction メッセージの生成処理
 * @since 1.0.0
 */
inline fun ProxiedPlayer.sendActionBarMessage(buildAction: KtComponentBuildAction) {
    sendActionBarMessage(*buildComponent(buildAction))
}

/**
 * アクションバーにコンポーネントメッセージを送信する
 *
 * @param message メッセージ
 * @since 1.0.0
 */
fun ProxiedPlayer.sendActionBarMessage(vararg message: BaseComponent) {
    sendMessage(ChatMessageType.ACTION_BAR, *message)
}

/**
 * タイトルに色付き文字列メッセージを表示する
 *
 * @param title タイトル / null
 * @param subtitle サブタイトル / null
 * @param fadeIn フェードイン / 10
 * @param stay 表示時間 / 70
 * @param fadeOut フェードアウト / 20
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @since 1.0.0
 */
fun ProxiedPlayer.sendTitleMessage(
    title: String? = null,
    subtitle: String? = null,
    fadeIn: Int = 10,
    stay: Int = 70,
    fadeOut: Int = 20,
    altColorChar: Char? = '&',
) {
    ProxyServer.getInstance().createTitle().apply {
        title(*textComponents(title?.color(altColorChar).orEmpty()))
        subTitle(*textComponents(subtitle?.color(altColorChar).orEmpty()))
        fadeIn(fadeIn)
        stay(stay)
        fadeOut(fadeOut)
    }.send(this)
}
