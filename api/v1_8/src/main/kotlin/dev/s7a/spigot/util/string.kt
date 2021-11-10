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
 * 文字列を色付き文字列に変換する。[altColorChar] が null ならばそのままの文字列を返す
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列 もしくは そのまま
 * @since 1.0.0
 */
fun String.toColor(altColorChar: Char?): String = altColorChar?.let(::toColor) ?: this

/**
 * 文字列を色付き文字列に変換する
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列
 * @since 1.0.0
 */
fun List<String>.toColor(altColorChar: Char = '&'): List<String> = map { it.toColor(altColorChar) }

/**
 * 文字列を色付き文字列に変換する。[altColorChar] が null ならばそのままの文字列を返す
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列
 * @since 1.0.0
 */
fun List<String>.toColor(altColorChar: Char?): List<String> = altColorChar?.let(::toColor) ?: this

/**
 * 色付き文字列を文字列に変換する
 *
 * @return 文字列
 * @since 1.0.0
 */
fun String.toUncolor(): String = ChatColor.stripColor(this)

/**
 * 色付き文字列を文字列に変換する
 *
 * @return 文字列
 * @since 1.0.0
 */
fun List<String>.toUncolor(): List<String> = map(String::toUncolor)
