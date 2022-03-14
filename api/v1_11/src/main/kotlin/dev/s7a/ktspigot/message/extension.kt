@file:JvmName("Extension11")

package dev.s7a.ktspigot.message

import dev.s7a.ktspigot.util.color
import org.bukkit.entity.Player

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
fun Player.sendTitleMessage(
    title: String? = null,
    subtitle: String? = null,
    fadeIn: Int = 10,
    stay: Int = 70,
    fadeOut: Int = 20,
    altColorChar: Char? = '&',
) {
    sendTitle(title?.color(altColorChar), subtitle?.color(altColorChar), fadeIn, stay, fadeOut)
}
