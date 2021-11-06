package dev.s7a.spigot.util

import org.bukkit.ChatColor

/**
 * 文字列を色付き文字列に変換する
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列
 * @since 1.0.0
 */
fun String.toColor(altColorChar: Char = '&'): String = ChatColor.translateAlternateColorCodes(altColorChar, this)

/**
 * 色付き文字列を文字列に変換する
 *
 * @return 文字列
 * @since 1.0.0
 */
fun String.toUncolor(): String = ChatColor.stripColor(this)
