package dev.s7a.spigot.config

/**
 * フォーマットによってコンフィグにデータを格納する
 *
 * @since 1.0.0
 */
interface KtConfigFormatter<T> {
    /**
     * フォーマッタ名
     *
     * @since 1.0.0
     */
    val name: String

    /**
     * [String] から [T] に変換する
     *
     * @param string 文字列
     * @return 変換後の値
     * @since 1.0.0
     */
    fun value(string: String): T?

    /**
     * [T] から [String] に変換する
     *
     * @param value 値
     * @return 変換後の文字列
     * @since 1.0.0
     */
    fun string(value: T): String
}
