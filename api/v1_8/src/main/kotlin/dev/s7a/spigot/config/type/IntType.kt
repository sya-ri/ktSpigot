package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.intValue

/**
 * [Int] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.intValue
 * @since 1.0.0
 */
object IntType : NumberType.Base<Int>() {
    override val numberToValue = Number::toInt

    /**
     * [Number] をベースとした [KtConfigValueType]
     *
     * @since 1.0.0
     */
    abstract class Base<T> : KtConfigValueType<T> {
        /**
         * [Int] から [T] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val intToResult: (KtConfig, String, Int) -> KtConfigResult<T>

        /**
         * [T] から [Int] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val valueToInt: (T) -> Int

        override fun get(config: KtConfig, path: String): KtConfigResult<T> {
            return config.intValue(path).get().map { intToResult(config, path, it) }
        }

        override fun set(config: KtConfig, path: String, value: T?) {
            config.intValue(path).set(value?.let(valueToInt))
        }
    }
}
