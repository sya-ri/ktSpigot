@file:JvmName("Extension9")

package dev.s7a.ktspigot.message

import dev.s7a.ktspigot.component.KtComponentBuildAction
import dev.s7a.ktspigot.component.buildComponent
import dev.s7a.ktspigot.component.textComponents
import dev.s7a.ktspigot.util.color
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.entity.Player

/**
 * アクションバーに色付き文字列メッセージを表示する
 *
 * @param message メッセージ
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @since 1.0.0
 */
fun Player.sendActionBarMessage(message: String, altColorChar: Char? = '&') {
    sendActionBarMessage(*textComponents(message.color(altColorChar)))
}

/**
 * アクションバーにコンポーネントメッセージを送信する
 *
 * @param buildAction メッセージの生成処理
 * @since 1.0.0
 */
inline fun Player.sendActionBarMessage(buildAction: KtComponentBuildAction) {
    sendActionBarMessage(*buildComponent(buildAction))
}

/**
 * アクションバーにコンポーネントメッセージを送信する
 *
 * @param message メッセージ
 * @since 1.0.0
 */
fun Player.sendActionBarMessage(vararg message: BaseComponent) {
    spigot().sendMessage(ChatMessageType.ACTION_BAR, *message)
}
