package dev.s7a.spigot.command

/**
 * ktCommand のタブ補完の方式
 *
 * @since 1.0.0
 */
enum class KtCommandTabCompleterType {
    /**
     * 単一モード
     *
     * @since 1.0.0
     */
    Single,

    /**
     * 複数モード(重複あり)
     *
     * @since 1.0.0
     */
    Multiple,

    /**
     * 複数モード(重複なし)
     *
     * @since 1.0.0
     */
    NoDuplication,
}
