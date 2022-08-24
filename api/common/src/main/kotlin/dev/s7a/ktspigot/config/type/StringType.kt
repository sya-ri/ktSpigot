package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.getListUnsafe
import dev.s7a.ktspigot.config.getUnsafe
import dev.s7a.ktspigot.config.mapValues
import dev.s7a.ktspigot.config.setUnsafe

/**
 * [String] のコンフィグデータ型
 *
 * @see stringValue
 * @since 1.0.0
 */
object StringType : KtConfigValueType.Listable<String> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<String> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfigBase, path: String, value: String?) {
        config.setUnsafe(path, value)
    }

    override fun list(force: Boolean): KtConfigValueType<List<String>> {
        return object : KtConfigValueType<List<String>> {
            override fun get(config: KtConfigBase, path: String): KtConfigResult<List<String>> {
                return config.getListUnsafe(path)
            }

            override fun set(config: KtConfigBase, path: String, value: List<String>?) {
                config.setUnsafe(path, value)
            }
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
        protected abstract val stringToResult: (KtConfigBase, String, String) -> KtConfigResult<T>

        /**
         * [T] から [String] に変換する処理
         *
         * @since 1.0.0
         */
        protected abstract val valueToString: (T) -> String

        override fun get(config: KtConfigBase, path: String): KtConfigResult<T> {
            return config.stringValue(path).get().map { stringToResult(config, path, it) }
        }

        override fun set(config: KtConfigBase, path: String, value: T?) {
            config.stringValue(path).set(value?.let(valueToString))
        }

        override fun list(force: Boolean): KtConfigValueType<List<T>> {
            return object : KtConfigValueType<List<T>> {
                override fun get(config: KtConfigBase, path: String): KtConfigResult<List<T>> {
                    return config.stringValue(path).list(force).get().mapValues(config, path) { index, value ->
                        stringToResult(config, "$path#$index", value)
                    }
                }

                override fun set(config: KtConfigBase, path: String, value: List<T>?) {
                    config.stringValue(path).list(force).set(value?.map(valueToString))
                }
            }
        }
    }
}
