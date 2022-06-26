@file:JvmName("Extension12")

package dev.s7a.ktspigot.component

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.KeybindComponent
import net.md_5.bungee.api.chat.ScoreComponent
import net.md_5.bungee.api.chat.SelectorComponent

/**
 * [KeybindComponent] を末尾に追加する
 *
 * @param keybind キーバイド
 * @param color 文字色 / null
 * @param bold 太字 / false
 * @param italic 斜体 / false
 * @param underlined 下線 / false
 * @param strikethrough 取り消し線 / false
 * @param obfuscated 難読化 / false
 * @param hoverEvent カーソルを合わせたときのイベント
 * @param clickEvent クリックしたときのイベント
 * @see net.md_5.bungee.api.chat.Keybinds
 * @since 1.0.0
 */
fun KtComponentBuilder.appendKeybind(
    keybind: String,
    color: ChatColor? = null,
    bold: Boolean? = null,
    italic: Boolean? = null,
    underlined: Boolean? = null,
    strikethrough: Boolean? = null,
    obfuscated: Boolean? = null,
    hoverEvent: HoverEvent? = null,
    clickEvent: ClickEvent? = null
) {
    appendWith(KeybindComponent(keybind), color, bold, italic, underlined, strikethrough, obfuscated, hoverEvent, clickEvent)
}

/**
 * [ScoreComponent] を末尾に追加する
 *
 * @param name エンティティ名 もしくは セレクター
 * @param objective 表示するスコアのオブジェクティブ
 * @param color 文字色 / null
 * @param bold 太字 / false
 * @param italic 斜体 / false
 * @param underlined 下線 / false
 * @param strikethrough 取り消し線 / false
 * @param obfuscated 難読化 / false
 * @param hoverEvent カーソルを合わせたときのイベント
 * @param clickEvent クリックしたときのイベント
 * @since 1.0.0
 */
fun KtComponentBuilder.appendScore(
    name: String,
    objective: String,
    color: ChatColor? = null,
    bold: Boolean? = null,
    italic: Boolean? = null,
    underlined: Boolean? = null,
    strikethrough: Boolean? = null,
    obfuscated: Boolean? = null,
    hoverEvent: HoverEvent? = null,
    clickEvent: ClickEvent? = null
) {
    appendWith(ScoreComponent(name, objective, null), color, bold, italic, underlined, strikethrough, obfuscated, hoverEvent, clickEvent)
}

/**
 * [SelectorComponent] を末尾に追加する
 *
 * @param selector セレクター
 * @param color 文字色 / null
 * @param bold 太字 / false
 * @param italic 斜体 / false
 * @param underlined 下線 / false
 * @param strikethrough 取り消し線 / false
 * @param obfuscated 難読化 / false
 * @param hoverEvent カーソルを合わせたときのイベント
 * @param clickEvent クリックしたときのイベント
 * @since 1.0.0
 */
fun KtComponentBuilder.appendSelector(
    selector: String,
    color: ChatColor? = null,
    bold: Boolean? = null,
    italic: Boolean? = null,
    underlined: Boolean? = null,
    strikethrough: Boolean? = null,
    obfuscated: Boolean? = null,
    hoverEvent: HoverEvent? = null,
    clickEvent: ClickEvent? = null
) {
    appendWith(SelectorComponent(selector), color, bold, italic, underlined, strikethrough, obfuscated, hoverEvent, clickEvent)
}
