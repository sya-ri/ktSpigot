package dev.s7a.ktspigot.config

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.collections.List as KotlinList
import kotlin.collections.Map as KotlinMap

/**
 * コンフィグの値
 *
 * @param T データ型
 * @property config コンフィグ
 * @property path コンフィグパス
 * @property type コンフィグデータ型
 * @since 1.0.0
 */
open class KtConfigValue<T>(val config: KtConfigBase, val path: String, open val type: KtConfigValueType<T>) : ReadWriteProperty<Any?, T?> {
    /**
     * 値を取得する
     *
     * @return [KtConfigResult]<[T]>
     * @since 1.0.0
     */
    open fun get(): KtConfigResult<T> {
        return type.get(config, path)
    }

    /**
     * 値を取得する
     *
     * @return [T]?
     * @since 1.0.0
     */
    open fun getValue(): T? {
        return get().orNull
    }

    /**
     * 値を設定する
     *
     * @param value 設定後の値
     * @since 1.0.0
     */
    fun set(value: T?) {
        type.set(config, path, value)
        if (config.autoSave) {
            config.save()
        }
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return getValue()
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        set(value)
    }

    /**
     * 値が取得できなけばエラーを出す
     *
     * @since 1.0.0
     */
    open class Base<T>(config: KtConfigBase, path: String, type: KtConfigValueType<T>) : KtConfigValue<T>(config, path, type) {
        /**
         * 値が設定されていなくてもエラーを出さない
         *
         * @since 1.0.0
         */
        fun nullable() = Nullable(this)

        /**
         * 値が設定されていなければデフォルト値を設定する
         *
         * @param value デフォルト値
         * @since 1.0.0
         */
        fun default(value: T) = default { value }

        /**
         * 値が設定されていなければデフォルト値を設定する
         *
         * @param value デフォルト値を生成する処理
         * @since 1.0.0
         */
        fun default(value: () -> T) = Default(this, value)

        /**
         * マップとしてのコンフィグデータ型
         *
         * @since 1.0.0
         */
        private val mapType
            get() = object : KtConfigValueType<KotlinMap<String, T>> {
                override fun get(config: KtConfigBase, path: String): KtConfigResult<KotlinMap<String, T>> {
                    return config.getSectionKeys(path)?.run {
                        associateWith {
                            type.get(config, "$path.$it")
                        }.flatten(config, path)
                    } ?: KtConfigResult.Failure(KtConfigError.NotFound(config, path))
                }

                override fun set(config: KtConfigBase, path: String, value: KotlinMap<String, T>?) {
                    config.setUnsafe(path, null)
                    value?.forEach { (section, value) ->
                        type.set(config, "$path.$section", value)
                    }
                }
            }

        /**
         * キーを持ったリストでの値
         *
         * @param force true: 不正な値を無視し、それ以外の値を返す。
         *              false: 不正な値があれば、エラーにする。
         * @since 1.0.0
         */
        fun map(force: Boolean = false) = Map(config, path, mapType, force)

        /**
         * [KtConfigValueType.Listable] である値を扱う
         *
         * @since 1.0.0
         */
        class Listable<T>(config: KtConfigBase, path: String, override val type: KtConfigValueType.Listable<T>) : Base<T>(config, path, type) {
            /**
             * リストでの値の入力を受け付ける
             *
             * @param force true: 不正な値を無視し、それ以外の値を返す。
             *              false: 不正な値があれば、エラーにする。
             * @since 1.0.0
             */
            fun list(force: Boolean = false) = List(config, path, type.list(force), force)
        }

        /**
         * キーを持ったリストでの値
         *
         * @since 1.0.0
         */
        class Map<T>(config: KtConfigBase, path: String, type: KtConfigValueType<KotlinMap<String, T>>, val force: Boolean) : Base<KotlinMap<String, T>>(config, path, type) {
            override fun get(): KtConfigResult<KotlinMap<String, T>> {
                return super.get().fold(
                    onSuccess = { KtConfigResult.Success(it) },
                    onFailure = {
                        if (force && it is KtConfigError.MapConfigError<*>) {
                            @Suppress("UNCHECKED_CAST")
                            KtConfigResult.Success(it.data as KotlinMap<String, T>)
                        } else {
                            KtConfigResult.Failure(it)
                        }
                    }
                )
            }

            /**
             * 値が設定されていなければデフォルト値を設定する
             *
             * @param defaultValue デフォルト値
             * @since 1.0.0
             */
            fun default(vararg defaultValue: Pair<String, T>) = default(defaultValue.toMap())

            /**
             * デフォルト値として空リストを使う
             *
             * @since 1.0.0
             */
            fun orEmpty() = default(emptyMap())
        }

        /**
         * リストでの値の入力を受け付ける
         *
         * @since 1.0.0
         */
        class List<T>(config: KtConfigBase, path: String, type: KtConfigValueType<KotlinList<T>>, val force: Boolean) : Base<KotlinList<T>>(config, path, type) {
            override fun get(): KtConfigResult<KotlinList<T>> {
                return super.get().fold(
                    onSuccess = { KtConfigResult.Success(it) },
                    onFailure = {
                        if (force && it is KtConfigError.ListConfigError<*>) {
                            @Suppress("UNCHECKED_CAST")
                            KtConfigResult.Success(it.data as KotlinList<T>)
                        } else {
                            KtConfigResult.Failure(it)
                        }
                    }
                )
            }

            /**
             * 値が設定されていなければデフォルト値を設定する
             *
             * @param defaultValue デフォルト値
             * @since 1.0.0
             */
            fun default(vararg defaultValue: T) = default(defaultValue.toList())

            /**
             * デフォルト値として空リストを使う
             *
             * @since 1.0.0
             */
            fun orEmpty() = default(emptyList())
        }
    }

    /**
     * 値が設定されていなくてもエラーを出さない
     *
     * @see Base.nullable
     * @since 1.0.0
     */
    @Suppress("UNCHECKED_CAST")
    class Nullable<T>(configValue: KtConfigValue<T>) : KtConfigValue<T?>(configValue.config, configValue.path, configValue.type as KtConfigValueType<T?>) {
        override fun getValue(): T? {
            return get().orNull
        }
    }

    /**
     * 値が設定されていなければデフォルト値を設定する
     *
     * @see Base.default
     * @since 1.0.0
     */
    open class Default<T>(configValue: KtConfigValue<T>, val defaultValue: () -> T) : KtConfigValue<T>(configValue.config, configValue.path, configValue.type) {
        /**
         * 値が取得できない時はデフォルト値を強制で使用する
         *
         * @since 1.0.0
         */
        fun force() = Force(this)

        override fun getValue(): T? {
            return get().fold(
                onSuccess = { it },
                onFailure = {
                    if (it is KtConfigError.NotFound) {
                        defaultValue().apply(this::set)
                    } else {
                        null
                    }
                }
            )
        }

        /**
         * 値が取得できない時はデフォルト値を強制で使用する
         *
         * @since 1.0.0
         */
        class Force<T>(private val configValue: Default<T>) : Default<T>(configValue, configValue.defaultValue) {
            override fun getValue(): T {
                return get().fold(
                    onSuccess = { it },
                    onFailure = {
                        configValue.defaultValue().apply(this::set)
                    }
                )
            }

            override fun getValue(thisRef: Any?, property: KProperty<*>): T {
                return getValue()
            }
        }
    }
}
