package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigBase
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.mapValues

/**
 * [Int] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.intValue
 * @since 1.0.0
 */
object IntType : NumberType.Base<Int>() {
    override val numberToValue = Number::toInt

    /**
     * [Int] をベースとした [KtConfigValueType]
     *
     * @since 1.0.0
     */
    abstract class Base<T> : KtConfigValueType.Listable<T> {
        /**
         * [Int] から [T] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val intToResult: (KtConfigBase, String, Int) -> KtConfigResult<T>

        /**
         * [T] から [Int] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val valueToInt: (T) -> Int

        override fun get(config: KtConfigBase, path: String): KtConfigResult<T> {
            return config.intValue(path).get().map { intToResult(config, path, it) }
        }

        override fun set(config: KtConfigBase, path: String, value: T?) {
            config.intValue(path).set(value?.let(valueToInt))
        }

        override val list = object : KtConfigValueType<List<T>> {
            override fun get(config: KtConfigBase, path: String): KtConfigResult<List<T>> {
                return config.intValue(path).list().get().mapValues(config, path) { index, value ->
                    intToResult(config, "$path#$index", value)
                }
            }

            override fun set(config: KtConfigBase, path: String, value: List<T>?) {
                config.intValue(path).list().set(value?.map(valueToInt))
            }
        }
    }
}
