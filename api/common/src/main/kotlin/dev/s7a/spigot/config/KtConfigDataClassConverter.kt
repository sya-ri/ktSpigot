package dev.s7a.spigot.config

/**
 * データクラスとの変換を行うクラス
 *
 * @since 1.0.0
 */
interface KtConfigDataClassConverter<T> {
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
     * 複数の値を受付られるようにする
     *
     * @since 1.0.0
     */
    interface Listable<T> : KtConfigDataClassConverter<T> {
        /**
         * マップから値を取得する
         *
         * @param config コンフィグ
         * @param path コンフィグパス
         * @param index リストの序数
         * @param map マップ
         * @return [KtConfigResult]
         * @since 1.0.0
         */
        fun toValue(config: KtConfigBase, path: String, index: Int, map: Map<String, Any>): KtConfigResult<T>

        /**
         * 値からマップに変換する
         *
         * @param value 元の値
         * @return マップ
         * @since 1.0.0
         */
        fun toMap(value: T): Map<String, Any>
    }
}
