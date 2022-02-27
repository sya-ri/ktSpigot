@file:JvmName("Extension8")

package dev.s7a.ktspigot.component

import dev.s7a.ktspigot.util.color
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.TranslatableComponent

/**
 * [KtComponentBuilder] を操作する処理
 *
 * @since 1.0.0
 */
typealias KtComponentBuildAction = KtComponentBuilder.() -> Unit

/**
 * ktSpigot を使って [Array]<[BaseComponent]> を生成する
 *
 * @param buildAction [KtComponentBuilder] を操作する処理
 * @return [Array]<[BaseComponent]>
 * @since 1.0.0
 */
inline fun buildComponent(buildAction: KtComponentBuildAction): Array<BaseComponent> {
    return KtComponentBuilder().apply(buildAction).build()
}

/**
 * [TranslatableComponent] を末尾に追加する
 *
 * - [lang/en_us.json](https://github.com/InventivetalentDev/minecraft-assets/blob/1.17.1/assets/minecraft/lang/en_us.json)
 *
 * @param key 翻訳キー
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
fun KtComponentBuilder.appendTranslatable(
    key: String,
    vararg args: String,
    color: ChatColor? = null,
    bold: Boolean? = null,
    italic: Boolean? = null,
    underlined: Boolean? = null,
    strikethrough: Boolean? = null,
    obfuscated: Boolean? = null,
    hoverEvent: HoverEvent? = null,
    clickEvent: ClickEvent? = null,
) {
    appendWith(TranslatableComponent(key, *args), color, bold, italic, underlined, strikethrough, obfuscated, hoverEvent, clickEvent)
}

/**
 * カーソルを合わせたら文字列を表示する
 *
 * @param text 文字列
 * @param altColorChar [color] に使う文字 / '&'
 * @return [HoverEvent]
 * @since 1.0.0
 */
fun hoverShowText(text: String, altColorChar: Char? = '&'): HoverEvent {
    return HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(text.color(altColorChar))))
}

/**
 * クリックしたらURLを開く
 *
 * @param text URL
 * @return [HoverEvent]
 * @since 1.0.0
 */
fun clickOpenUrl(text: String): ClickEvent {
    return ClickEvent(ClickEvent.Action.RUN_COMMAND, text)
}

/**
 * クリックしたらコマンドを実行する
 *
 * @param text コマンド
 * @return [HoverEvent]
 * @since 1.0.0
 */
fun clickRunCommand(text: String): ClickEvent {
    return ClickEvent(ClickEvent.Action.RUN_COMMAND, text)
}

/**
 * クリックしたらコマンドを入力する
 *
 * @param text コマンド
 * @return [HoverEvent]
 * @since 1.0.0
 */
fun clickSuggestCommand(text: String): ClickEvent {
    return ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, text)
}
