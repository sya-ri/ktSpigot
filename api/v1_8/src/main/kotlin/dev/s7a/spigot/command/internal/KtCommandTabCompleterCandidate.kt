package dev.s7a.spigot.command.internal

import dev.s7a.spigot.command.KtCommandTabCompleteParameter
import dev.s7a.spigot.command.KtCommandTabCompleterType

/**
 * タブ補完のデータ形式
 *
 * @since 1.0.0
 */
internal sealed interface KtCommandTabCompleterCandidate {
    val type: KtCommandTabCompleterType

    /**
     * 不変な値であり、常に固定
     *
     * @see [dev.s7a.spigot.command.KtCommandTabCompleterTree.literal]
     * @since 1.0.0
     */
    class Literal(list: Collection<String>, override val type: KtCommandTabCompleterType) : KtCommandTabCompleterCandidate {
        val list = list.associateBy(String::lowercase)
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @see [dev.s7a.spigot.command.KtCommandTabCompleterTree.dynamic]
     * @since 1.0.0
     */
    class Dynamic(val action: (KtCommandTabCompleteParameter) -> Collection<String>?, override val type: KtCommandTabCompleterType) : KtCommandTabCompleterCandidate
}
