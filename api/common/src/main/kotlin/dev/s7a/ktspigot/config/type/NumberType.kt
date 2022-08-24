package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.getListUnsafe
import dev.s7a.ktspigot.config.getUnsafe
import dev.s7a.ktspigot.config.setUnsafe

/**
 * [Number] のコンフィグデータ型
 *
 * @see numberValue
 * @since 1.0.0
 */
object NumberType : KtConfigValueType.Listable<Number> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<Number> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfigBase, path: String, value: Number?) {
        config.setUnsafe(path, value)
    }

    override fun list(force: Boolean): KtConfigValueType<List<Number>> {
        return object : KtConfigValueType<List<Number>> {
            override fun get(config: KtConfigBase, path: String): KtConfigResult<List<Number>> {
                return config.getListUnsafe(path)
            }

            override fun set(config: KtConfigBase, path: String, value: List<Number>?) {
                config.setUnsafe(path, value)
            }
        }
    }

    /**
     * [Number] をベースとした [KtConfigValueType]
     *
     * @since 1.0.0
     */
    abstract class Base<T : Number> : KtConfigValueType.Listable<T> {
        /**
         * [Number] から [T] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val numberToValue: (Number) -> T

        override fun get(config: KtConfigBase, path: String): KtConfigResult<T> {
            return config.numberValue(path).get().map { KtConfigResult.Success(it.let(numberToValue)) }
        }

        override fun set(config: KtConfigBase, path: String, value: T?) {
            config.numberValue(path).set(value)
        }

        override fun list(force: Boolean): KtConfigValueType<List<T>> {
            return object : KtConfigValueType<List<T>> {
                override fun get(config: KtConfigBase, path: String): KtConfigResult<List<T>> {
                    return config.numberValue(path).list(force).get().map { KtConfigResult.Success(it.map(numberToValue)) }
                }

                override fun set(config: KtConfigBase, path: String, value: List<T>?) {
                    config.numberValue(path).list(force).set(value)
                }
            }
        }
    }
}
