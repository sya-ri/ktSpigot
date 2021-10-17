package dev.s7a.spigot.command

import dev.s7a.spigot.command.internal.KtCommandTabCompleterCandidate

/**
 * タブ補完の木
 *
 * @since 1.0.0
 */
open class KtCommandTabCompleterTree internal constructor() {
    internal val literals = mutableMapOf<KtCommandTabCompleterCandidate.Literal, KtCommandTabCompleterTree?>()

    internal var dynamics = mutableMapOf<KtCommandTabCompleterCandidate.Dynamic, KtCommandTabCompleterTree?>()

    private fun addLiteral(option: Collection<String>, type: KtCommandTabCompleterType, child: (KtCommandTabCompleterTree.() -> Unit)?) {
        val candidate = KtCommandTabCompleterCandidate.Literal(option.toList(), type)
        literals[candidate] = child?.run {
            KtCommandTabCompleterTree().apply(this)
        }
    }

    private fun addDynamic(candidate: KtCommandTabCompleterCandidate.Dynamic, child: (KtCommandTabCompleterTree.() -> Unit)?) {
        dynamics[candidate] = child?.run {
            KtCommandTabCompleterTree().apply(this)
        }
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
    fun literal(vararg option: String, child: KtCommandTabCompleterTree.() -> Unit) {
        literal(option.toList(), child)
    }

    /**
     * 不変な値であり、常に固定
     *
     * @param option 候補
     * @param child 子要素
     * @since 1.0.0
     */
    fun literal(option: Collection<String>, child: KtCommandTabCompleterTree.() -> Unit) {
        addLiteral(option, KtCommandTabCompleterType.Single, child)
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @param action 値を生成する処理
     * @param type 補完方式
     * @since 1.0.0
     */
    fun dynamic(action: (KtCommandTabCompleteParameter) -> Collection<String>?, type: KtCommandTabCompleterType = KtCommandTabCompleterType.Single) {
        addDynamic(KtCommandTabCompleterCandidate.Dynamic(action, type), null)
    }

    /**
     * 実行する度に値の生成を行う
     *
     * @param action 値を生成する処理
     * @param child 子要素
     * @since 1.0.0
     */
    fun dynamic(action: (KtCommandTabCompleteParameter) -> Collection<String>?, child: KtCommandTabCompleterTree.() -> Unit) {
        addDynamic(KtCommandTabCompleterCandidate.Dynamic(action, KtCommandTabCompleterType.Single), child)
    }
}
