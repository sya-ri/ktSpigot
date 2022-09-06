package dev.s7a.ktspigot.command

import dev.s7a.ktspigot.command.internal.KtCommandTabCompleterCandidate

/**
 * タブ補完の木
 *
 * @since 1.0.0
 */
open class KtCommandTabCompleterTree<T> internal constructor() {
    internal val list = mutableListOf<Pair<KtCommandTabCompleterCandidate, KtCommandTabCompleterTree<T>?>>()

    private fun add(candidate: KtCommandTabCompleterCandidate, child: KtCommandTabCompleterTree<T>?) {
        list.lastOrNull()?.let { (lastCandidate) ->
            if (lastCandidate is KtCommandTabCompleterCandidate.Default) {
                throw IllegalStateException("既に default {} が設定されています")
            }
        }
        list.add(candidate to child)
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param child 子要素
     * @since 1.0.0
     */
    fun literal(vararg option: String, child: KtCommandTabCompleteBuilder<T>? = null) {
        literal(option.toList(), child)
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param child 子要素
     * @since 1.0.0
     */
    fun literal(option: Collection<String>, child: KtCommandTabCompleteBuilder<T>? = null) {
        add(KtCommandTabCompleterCandidate.Literal(option.toList()), child?.run { KtCommandTabCompleterTree<T>().apply(this) })
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @param action 値を生成する処理
     * @param child 子要素
     * @since 1.0.0
     */
    fun dynamic(action: KtCommandTabCompleteAction<T>, child: KtCommandTabCompleteBuilder<T>? = null) {
        add(KtCommandTabCompleterCandidate.Dynamic(action), child?.run { KtCommandTabCompleterTree<T>().apply(this) })
    }

    /**
     * [literal], [dynamic] に一致するものがなければ、子要素を追加する
     *
     * @param child 子要素
     * @since 1.0.0
     */
    fun default(child: KtCommandTabCompleteBuilder<T>) {
        add(KtCommandTabCompleterCandidate.Default, KtCommandTabCompleterTree<T>().apply(child))
    }
}
