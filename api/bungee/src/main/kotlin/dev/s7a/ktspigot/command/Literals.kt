package dev.s7a.ktspigot.command

/**
 * よく使う補完候補
 *
 * @see KtCommandTabCompleterTree.literal
 * @since 1.0.0
 */
@Suppress("FunctionName")
object Literals {
    /**
     * 列挙型の一覧
     *
     * @param transform 列挙型定数を文字列に変換する処理
     * @since 1.0.0
     */
    inline fun <reified E : Enum<E>> Enums(transform: (E) -> String) = enumValues<E>().map(transform)

    /**
     * 列挙型の一覧
     *
     * @param transform 列挙型定数を文字列に変換する処理
     * @param predicate フィルター処理
     * @since 1.0.0
     */
    inline fun <reified E : Enum<E>> Enums(transform: (E) -> String, predicate: (E) -> Boolean) = enumValues<E>().filter(predicate).map(transform)

    /**
     * 列挙型の名前一覧
     *
     * @since 1.0.0
     */
    inline fun <reified E : Enum<E>> EnumNames() = Enums<E> { it.name }

    /**
     * [predicate] でフィルターされた列挙型の名前一覧
     *
     * @param predicate フィルター処理
     * @since 1.0.0
     */
    inline fun <reified E : Enum<E>> EnumNames(predicate: (E) -> Boolean) = Enums({ it.name }, predicate)
}
