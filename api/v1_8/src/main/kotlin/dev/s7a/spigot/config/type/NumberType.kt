package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getListUnsafe
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.numberValue
import dev.s7a.spigot.config.setUnsafe

/**
 * [Number] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.numberValue
 * @since 1.0.0
 */
object NumberType : KtConfigValueType.Listable<Number> {
    override fun get(config: KtConfig, path: String): KtConfigResult<Number> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfig, path: String, value: Number?) {
        config.setUnsafe(path, value)
    }

    override val list = object : KtConfigValueType<List<Number>> {
        override fun get(config: KtConfig, path: String): KtConfigResult<List<Number>> {
            return config.getListUnsafe(path)
        }

        override fun set(config: KtConfig, path: String, value: List<Number>?) {
            config.setUnsafe(path, value)
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

        override fun get(config: KtConfig, path: String): KtConfigResult<T> {
            return config.numberValue(path).get().map { KtConfigResult.Success(it.let(numberToValue)) }
        }

        override fun set(config: KtConfig, path: String, value: T?) {
            config.numberValue(path).set(value)
        }

        override val list = object : KtConfigValueType<List<T>> {
            override fun get(config: KtConfig, path: String): KtConfigResult<List<T>> {
                return config.numberValue(path).list().get().map { KtConfigResult.Success(it.map(numberToValue)) }
            }

            override fun set(config: KtConfig, path: String, value: List<T>?) {
                config.numberValue(path).list().set(value)
            }
        }
    }
}
