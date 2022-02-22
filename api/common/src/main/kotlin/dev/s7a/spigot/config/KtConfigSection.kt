package dev.s7a.spigot.config

/**
 * コンフィグセクション
 *
 * @since 1.0.0
 */
interface KtConfigSection {
    /**
     * コンフィグ
     *
     * @since 1.0.0
     */
    val config: KtConfigBase

    /**
     * コンフィグパス
     *
     * @since 1.0.0
     */
    val path: String

    /**
     * フルパスを取得する
     *
     * @param path コンフィグパス
     * @return フルパス
     */
    fun fullPath(path: String): String = config.fullPath(this.path) + "." + path
}
