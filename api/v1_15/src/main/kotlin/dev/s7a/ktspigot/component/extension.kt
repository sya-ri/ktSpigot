@file:JvmName("Extension15")

package dev.s7a.ktspigot.component

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent

/**
 * クリックしたら文字列をコピーする
 *
 * @param text 文字列
 * @return [HoverEvent]
 * @since 1.0.0
 */
fun clickCopyToClipboard(text: String): ClickEvent {
    return ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text)
}
