package dev.s7a.ktspigot.util

import net.md_5.bungee.api.ChatColor

/**
 * 文字列を色付き文字列に変換する
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列
 * @since 1.0.0
 */
fun String.color(altColorChar: Char = '&'): String = ChatColor.translateAlternateColorCodes(altColorChar, this)

/**
 * 文字列を色付き文字列に変換する。[altColorChar] が null ならばそのままの文字列を返す
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列 もしくは そのまま
 * @since 1.0.0
 */
fun String.color(altColorChar: Char?): String = altColorChar?.let(::color) ?: this

/**
 * 文字列を色付き文字列に変換する
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列
 * @since 1.0.0
 */
fun List<String>.color(altColorChar: Char = '&'): List<String> = map { it.color(altColorChar) }

/**
 * 文字列を色付き文字列に変換する。[altColorChar] が null ならばそのままの文字列を返す
 *
 * @param altColorChar セクションの代わりに使う文字 / '&'
 * @return 色付き文字列
 * @since 1.0.0
 */
fun List<String>.color(altColorChar: Char?): List<String> = altColorChar?.let(::color) ?: this

/**
 * 色付き文字列を文字列に変換する
 *
 * @return 文字列
 * @since 1.0.0
 */
fun String.uncolor(): String = ChatColor.stripColor(this)

/**
 * 色付き文字列を文字列に変換する
 *
 * @return 文字列
 * @since 1.0.0
 */
fun List<String>.uncolor(): List<String> = map(String::uncolor)
