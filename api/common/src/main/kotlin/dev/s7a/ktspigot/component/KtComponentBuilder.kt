package dev.s7a.ktspigot.component

import dev.s7a.ktspigot.util.color
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

/**
 * [Array]<[BaseComponent]> を生成するビルダークラス
 *
 * @since 1.0.0
 */
class KtComponentBuilder {
    /**
     * コンポーネント一覧
     *
     * @since 1.0.0
     */
    private val components = mutableListOf<BaseComponent>()

    /**
     * スタイルを変更して、コンポーネントを末尾に追加する
     *
     * @param component コンポーネント
     * @param color 文字色
     * @param bold 太字
     * @param italic 斜体
     * @param underlined 下線
     * @param strikethrough 取り消し線
     * @param obfuscated 難読化
     * @param hoverEvent カーソルを合わせたときのイベント
     * @param clickEvent クリックしたときのイベント
     * @since 1.0.0
     */
    fun appendWith(
        component: BaseComponent,
        color: ChatColor? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        underlined: Boolean? = null,
        strikethrough: Boolean? = null,
        obfuscated: Boolean? = null,
        hoverEvent: HoverEvent? = null,
        clickEvent: ClickEvent? = null
    ) {
        component.apply {
            this.color = color
            this.setBold(bold)
            this.setItalic(italic)
            this.setUnderlined(underlined)
            this.setStrikethrough(strikethrough)
            this.setObfuscated(obfuscated)
            this.hoverEvent = hoverEvent
            this.clickEvent = clickEvent
        }.let {
            append(it)
        }
    }

    /**
     * [ChatColor] を使ってテキストを末尾に追加する
     *
     * @param text 文字列
     * @param color 文字色
     * @param bold 太字 / false
     * @param italic 斜体 / false
     * @param underlined 下線 / false
     * @param strikethrough 取り消し線 / false
     * @param obfuscated 難読化 / false
     * @param hoverEvent カーソルを合わせたときのイベント
     * @param clickEvent クリックしたときのイベント
     * @since 1.0.0
     */
    fun append(
        text: String,
        color: ChatColor?,
        bold: Boolean? = null,
        italic: Boolean? = null,
        underlined: Boolean? = null,
        strikethrough: Boolean? = null,
        obfuscated: Boolean? = null,
        hoverEvent: HoverEvent? = null,
        clickEvent: ClickEvent? = null
    ) {
        appendWith(TextComponent(text), color, bold, italic, underlined, strikethrough, obfuscated, hoverEvent, clickEvent)
    }

    /**
     * [color] を使ってテキストを末尾に追加する
     *
     * @param text 文字列
     * @param hoverEvent カーソルを合わせたときのイベント
     * @param clickEvent クリックしたときのイベント
     * @param altColorChar [color] に使う文字 / '&'
     * @since 1.0.0
     */
    fun append(
        text: String,
        hoverEvent: HoverEvent? = null,
        clickEvent: ClickEvent? = null,
        altColorChar: Char? = '&'
    ) {
        textComponents(text.color(altColorChar)).forEach {
            it.hoverEvent = hoverEvent
            it.clickEvent = clickEvent
            append(it)
        }
    }

    /**
     * コンポーネントを末尾に追加する
     *
     * @param component コンポーネント
     * @since 1.0.0
     */
    fun append(component: BaseComponent) {
        components.add(component)
    }

    /**
     * [Array]<[BaseComponent]> を生成する
     *
     * @return [Array]<[BaseComponent]>
     * @since 1.0.0
     */
    fun build(): Array<BaseComponent> {
        return arrayOf(TextComponent(*components.toTypedArray()))
    }
}
