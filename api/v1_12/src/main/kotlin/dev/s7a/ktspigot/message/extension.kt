@file:JvmName("Extension12")

package dev.s7a.ktspigot.message

import dev.s7a.ktspigot.component.KtComponentBuildAction
import dev.s7a.ktspigot.component.buildComponent
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.command.CommandSender

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
    spigot().sendMessage(*message)
}
