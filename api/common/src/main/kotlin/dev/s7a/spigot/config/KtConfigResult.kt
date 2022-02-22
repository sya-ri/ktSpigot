package dev.s7a.spigot.config

/**
 * コンフィグから値を取得した結果
 *
 * @param T データ型
 * @since 1.0.0
 */
sealed interface KtConfigResult<out T> {
    /**
     * 取得できれば値を返し、できなければ null を返す
     *
     * @since 1.0.0
     */
    val orNull: T?

    /**
     * 取得できれば値の変換を行う
     *
     * @param R 変換後のデータ型
     * @param action 変換処理
     * @return 変換後の結果
     * @since 1.0.0
     */
    fun <R> map(action: (T) -> KtConfigResult<R>): KtConfigResult<R>

    /**
     * 値の取得に成功した
     *
     * @property value 値
     * @since 1.0.0
     */
    data class Success<out T>(val value: T) : KtConfigResult<T> {
        override val orNull = value
        override fun <R> map(action: (T) -> KtConfigResult<R>) = action(value)
    }

    /**
     * 値の取得に失敗した
     *
     * @property error エラー
     * @since 1.0.0
     */
    data class Failure<out T>(val error: KtConfigError) : KtConfigResult<T> {
        override val orNull: T? = null
        override fun <R> map(action: (T) -> KtConfigResult<R>) = Failure<R>(error)
    }
}
