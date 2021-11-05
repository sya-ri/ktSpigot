package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getListUnsafe
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.mapValues
import dev.s7a.spigot.config.setUnsafe
import dev.s7a.spigot.config.stringValue

/**
 * [String] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.stringValue
 * @since 1.0.0
 */
object StringType : KtConfigValueType.Listable<String> {
    override fun get(config: KtConfig, path: String): KtConfigResult<String> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfig, path: String, value: String?) {
        config.setUnsafe(path, value)
    }

    override val list = object : KtConfigValueType<List<String>> {
        override fun get(config: KtConfig, path: String): KtConfigResult<List<String>> {
            return config.getListUnsafe(path)
        }

        override fun set(config: KtConfig, path: String, value: List<String>?) {
            config.setUnsafe(path, value)
        }
    }

    /**
     * [String] をベースとした [KtConfigValueType]
     *
     * @since 1.0.0
     */
    abstract class Base<T> : KtConfigValueType.Listable<T> {
        /**
         * [String] から [T] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val stringToResult: (KtConfig, String, String) -> KtConfigResult<T>

        /**
         * [T] から [String] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val valueToString: (T) -> String

        override fun get(config: KtConfig, path: String): KtConfigResult<T> {
            return config.stringValue(path).get().map { stringToResult(config, path, it) }
        }

        override fun set(config: KtConfig, path: String, value: T?) {
            config.stringValue(path).set(value?.let(valueToString))
        }

        override val list = object : KtConfigValueType<List<T>> {
            override fun get(config: KtConfig, path: String): KtConfigResult<List<T>> {
                return config.stringValue(path).list().get().mapValues(config, path) { index, value ->
                    stringToResult(config, "$path#$index", value)
                }
            }

            override fun set(config: KtConfig, path: String, value: List<T>?) {
                config.stringValue(path).list().set(value?.map(valueToString))
            }
        }
    }
}
