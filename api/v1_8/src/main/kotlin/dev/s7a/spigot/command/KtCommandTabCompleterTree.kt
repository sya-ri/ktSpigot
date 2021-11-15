package dev.s7a.spigot.command

import dev.s7a.spigot.command.internal.KtCommandTabCompleterCandidate

/**
 * タブ補完の木
 *
 * @since 1.0.0
 */
open class KtCommandTabCompleterTree internal constructor() {
    internal val list = mutableListOf<Pair<KtCommandTabCompleterCandidate, KtCommandTabCompleterTree?>>()

    private fun add(candidate: KtCommandTabCompleterCandidate, child: KtCommandTabCompleterTree?) {
        list.lastOrNull()?.let { (lastCandidate) ->
            if (lastCandidate is KtCommandTabCompleterCandidate.Default) {
                throw IllegalStateException("既に default {} が設定されています")
            }
        }
        list.add(candidate to child)
    }

    private fun addLiteral(option: Collection<String>, type: KtCommandTabCompleterType, child: KtCommandTabCompleteBuilder?) {
        val candidate = KtCommandTabCompleterCandidate.Literal(option.toList(), type)
        add(candidate, child?.run { KtCommandTabCompleterTree().apply(this) })
    }

    private fun addDynamic(candidate: KtCommandTabCompleterCandidate.Dynamic, child: KtCommandTabCompleteBuilder?) {
        add(candidate, child?.run { KtCommandTabCompleterTree().apply(this) })
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param type 補完方式
     * @since 1.0.0
     */
    fun literal(vararg option: String, type: KtCommandTabCompleterType = KtCommandTabCompleterType.Single) {
        literal(option.toList(), type)
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param type 補完方式
     * @since 1.0.0
     */
    fun literal(option: Collection<String>, type: KtCommandTabCompleterType = KtCommandTabCompleterType.Single) {
        addLiteral(option, type, null)
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param child 子要素
     * @since 1.0.0
     */
    fun literal(vararg option: String, child: KtCommandTabCompleteBuilder) {
        literal(option.toList(), child)
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param child 子要素
     * @since 1.0.0
     */
    fun literal(option: Collection<String>, child: KtCommandTabCompleteBuilder) {
        addLiteral(option, KtCommandTabCompleterType.Single, child)
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @param action 値を生成する処理
     * @param type 補完方式
     * @since 1.0.0
     */
    fun dynamic(action: KtCommandTabCompleteAction, type: KtCommandTabCompleterType = KtCommandTabCompleterType.Single) {
        addDynamic(KtCommandTabCompleterCandidate.Dynamic(action, type), null)
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @param action 値を生成する処理
     * @param child 子要素
     * @since 1.0.0
     */
    fun dynamic(action: KtCommandTabCompleteAction, child: KtCommandTabCompleteBuilder) {
        addDynamic(KtCommandTabCompleterCandidate.Dynamic(action, KtCommandTabCompleterType.Single), child)
    }

    /**
     * [literal], [dynamic] に一致するものがなければ、子要素を追加する
     *
     * @param child 子要素
     * @since 1.0.0
     */
    fun default(child: KtCommandTabCompleteBuilder) {
        add(KtCommandTabCompleterCandidate.Default, KtCommandTabCompleterTree().apply(child))
    }
}
