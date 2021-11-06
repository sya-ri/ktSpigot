package dev.s7a.spigot.util

import java.util.UUID

/**
 * 文字列を [UUID] に変換する。変換できなければ例外を投げる
 *
 * @param name UUIDの文字列
 * @return [UUID]
 * @throws IllegalArgumentException 不正なUUIDだったとき
 * @since 1.0.0
 */
fun uuid(name: String): UUID = UUID.fromString(name)

/**
 * 文字列を [UUID] に変換する。変換できなければ null を返す
 *
 * @param name UUIDの文字列
 * @return [UUID]?
 * @since 1.0.0
 */
fun uuidOrNull(name: String) = try {
    uuid(name)
} catch (ex: IllegalArgumentException) {
    null
}
