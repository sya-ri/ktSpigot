package dev.s7a.ktspigot.command.internal

import dev.s7a.ktspigot.command.KtCommandTabCompleteAction

/**
 * タブ補完のデータ形式
 *
 * @since 1.0.0
 */
internal sealed interface KtCommandTabCompleterCandidate {
    /**
     * 不変な値であり、常に固定
     *
     * @see [dev.s7a.ktspigot.command.KtCommandTabCompleterTree.literal]
     * @since 1.0.0
     */
    class Literal(list: Collection<String>) : KtCommandTabCompleterCandidate {
        val list = list.groupBy(String::lowercase)
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @see [dev.s7a.ktspigot.command.KtCommandTabCompleterTree.dynamic]
     * @since 1.0.0
     */
    class Dynamic<T>(val action: KtCommandTabCompleteAction<T>) : KtCommandTabCompleterCandidate

    /**
     * [Literal], [Dynamic] で一致するものがなければ子要素として使う
     *
     * @see [dev.s7a.ktspigot.command.KtCommandTabCompleterTree.default]
     * @since 1.0.0
     */
    object Default : KtCommandTabCompleterCandidate
}
