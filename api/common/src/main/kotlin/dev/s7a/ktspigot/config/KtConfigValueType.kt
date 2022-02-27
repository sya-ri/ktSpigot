package dev.s7a.ktspigot.config

/**
 * コンフィグデータ型
 *
 * @param T データ型
 * @since 1.0.0
 */
interface KtConfigValueType<T> {
    /**
     * コンフィグから値を取得する
     *
     * @param config コンフィグ
     * @param path コンフィグパス
     * @return [KtConfigResult]
     * @since 1.0.0
     */
    fun get(config: KtConfigBase, path: String): KtConfigResult<T>

    /**
     * コンフィグに値を設定する
     *
     * @param config コンフィグ
     * @param path コンフィグパス
     * @param value 設定後の値
     * @since 1.0.0
     */
    fun set(config: KtConfigBase, path: String, value: T?)

    /**
     * 複数の値を受付られるコンフィグデータ型
     *
     * @since 1.0.0
     */
    interface Listable<T> : KtConfigValueType<T> {
        /**
         * リストとしてのコンフィグデータ型
         *
         * @since 1.0.0
         */
        val list: KtConfigValueType<List<T>>
    }
}
