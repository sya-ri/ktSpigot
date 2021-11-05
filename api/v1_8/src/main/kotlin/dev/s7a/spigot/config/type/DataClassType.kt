package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigDataClassConverter
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getMapListUnsafe
import dev.s7a.spigot.config.mapValues
import dev.s7a.spigot.config.setUnsafe

/**
 * データクラスのコンフィグデータ型
 *
 * @property converter コンバーター
 * @see dev.s7a.spigot.config.dataClassValue
 * @since 1.0.0
 */
open class DataClassType<T>(protected open val converter: KtConfigDataClassConverter<T>) : KtConfigValueType<T> {
    override fun get(config: KtConfig, path: String): KtConfigResult<T> {
        return converter.get(config, path)
    }

    override fun set(config: KtConfig, path: String, value: T?) {
        converter.set(config, path, value)
    }

    /**
     * 複数の値を受付られるコンフィグデータ型
     *
     * @see dev.s7a.spigot.config.dataClassValue
     * @since 1.0.0
     */
    open class Listable<T>(override val converter: KtConfigDataClassConverter.Listable<T>) : DataClassType<T>(converter), KtConfigValueType.Listable<T> {
        override val list: KtConfigValueType<List<T>>
            get() = object : KtConfigValueType<List<T>> {
                override fun get(config: KtConfig, path: String): KtConfigResult<List<T>> {
                    return config.getMapListUnsafe(path).mapValues(config, path) { index, map ->
                        converter.toValue(config, path, index, map)
                    }
                }

                override fun set(config: KtConfig, path: String, value: List<T>?) {
                    config.setUnsafe(path, value?.map(converter::toMap))
                }
            }
    }
}
