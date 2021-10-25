package dev.s7a.spigot.config

import kotlin.collections.List as KotlinList

/**
 * コンフィグの値
 *
 * @param T データ型
 * @property config コンフィグ
 * @property path コンフィグパス
 * @property type コンフィグデータ型
 * @since 1.0.0
 */
open class KtConfigValue<T>(
    val config: KtConfig,
    val path: String,
    open val type: KtConfigValueType<T>,
) {
    /**
     * 値を取得する
     *
     * @return [KtConfigResult]
     * @since 1.0.0
     */
    open fun get() = type.get(config, path)

    /**
     * 値を設定する
     *
     * @param value 設定後の値
     * @since 1.0.0
     */
    fun set(value: T?) {
        type.set(config, path, value)
    }

    /**
     * 値を設定し、変更を保存する
     *
     * @param value 設定後の値
     * @since 1.0.0
     */
    fun setAndSave(value: T?) {
        set(value)
        config.save()
    }

    /**
     * 値を取得し、取得できなけば null を返す
     *
     * @since 1.0.0
     */
    open fun getValue() = get().orNull

    /**
     * 値を取得し、取得できなけばエラーハンドリングを行い null を返す
     *
     * @param handler エラーハンドリングを行うクラス
     * @since 1.0.0
     */
    open fun getValue(handler: KtConfigErrorHandler): T? {
        return when (val result = get()) {
            is KtConfigResult.Success -> {
                result.value
            }
            is KtConfigResult.Failure -> {
                handler.handle(result.error)
                null
            }
        }
    }

    /**
     * 値が取得できなけばエラーを出す
     *
     * @since 1.0.0
     */
    open class Base<T>(config: KtConfig, path: String, type: KtConfigValueType<T>) : KtConfigValue<T>(config, path, type) {
        /**
         * 値が設定されていなくてもエラーを出さない
         *
         * @since 1.0.0
         */
        fun nullable() = Nullable(this)

        /**
         * 値が設定されていなければデフォルト値を設定する
         *
         * @param defaultValue デフォルト値
         * @since 1.0.0
         */
        fun default(defaultValue: T) = Default.Literal(this, defaultValue)

        /**
         * 値が設定されていなければデフォルト値を設定する
         *
         * @param defaultValue デフォルト値を生成する処理
         * @since 1.0.0
         */
        fun default(defaultValue: () -> T) = Default.Dynamic(this, defaultValue)

        /**
         * [KtConfigValueType.Listable] である値を扱う
         *
         * @since 1.0.0
         */
        class Listable<T>(config: KtConfig, path: String, override val type: KtConfigValueType.Listable<T>) : Base<T>(config, path, type) {
            /**
             * リストでの値の入力を受け付ける
             *
             * @since 1.0.0
             */
            fun list() = List(config, path, type.list)
        }

        /**
         * リストでの値の入力を受け付ける
         *
         * @since 1.0.0
         */
        class List<T>(config: KtConfig, path: String, type: KtConfigValueType<KotlinList<T>>) : Base<KotlinList<T>>(config, path, type) {
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
    class Nullable<T>(
        configValue: KtConfigValue<T>,
    ) : KtConfigValue<T?>(
        configValue.config,
        configValue.path,
        configValue.type as KtConfigValueType<T?>
    ) {
        override fun get(): KtConfigResult<T?> {
            val value = super.get()
            return if (value is KtConfigResult.Failure) {
                when (value.error) {
                    is KtConfigError.NotFound -> KtConfigResult.Success(null)
                    else -> value
                }
            } else {
                value
            }
        }
    }

    /**
     * 値が設定されていなければデフォルト値を設定する
     *
     * @see Base.default
     * @since 1.0.0
     */
    abstract class Default<T>(
        configValue: KtConfigValue<T>,
    ) : KtConfigValue<T>(
        configValue.config,
        configValue.path,
        configValue.type,
    ) {
        /**
         * 値が取得できない時はデフォルト値を強制で使用する
         *
         * @since 1.0.0
         */
        fun force() = Force(this)

        /**
         * デフォルト値を取得する
         *
         * @since 1.0.0
         */
        abstract fun getDefault(): T

        override fun get(): KtConfigResult<T> {
            val value = super.get()
            return if (value is KtConfigResult.Failure) {
                when (value.error) {
                    is KtConfigError.NotFound -> KtConfigResult.Success(getDefault().apply(::setAndSave))
                    else -> value
                }
            } else {
                value
            }
        }

        /**
         * デフォルト値として固定値を使う
         *
         * @since 1.0.0
         */
        class Literal<T>(
            configValue: KtConfigValue<T>,
            private val defaultValue: T,
        ) : Default<T>(configValue) {
            override fun getDefault() = defaultValue
        }

        /**
         * デフォルト値として変化する値を使う
         *
         * @since 1.0.0
         */
        class Dynamic<T>(
            configValue: KtConfigValue<T>,
            private val defaultValue: () -> T,
        ) : Default<T>(configValue) {
            override fun getDefault() = defaultValue()
        }

        /**
         * 値が取得できない時はデフォルト値を強制で使用する
         *
         * @since 1.0.0
         */
        class Force<T>(
            private val configValue: Default<T>
        ) : KtConfigValue<T>(
            configValue.config,
            configValue.path,
            configValue.type,
        ) {
            override fun get(): KtConfigResult<T> {
                val value = super.get()
                return if (value is KtConfigResult.Failure) {
                    KtConfigResult.Success(configValue.getDefault().apply(::setAndSave))
                } else {
                    value
                }
            }

            /**
             * 値を取得する
             *
             * @since 1.0.0
             */
            override fun getValue(): T = super.getValue()!!

            @Deprecated("値の取得に失敗することはありません", level = DeprecationLevel.ERROR, replaceWith = ReplaceWith("getValue()"))
            override fun getValue(handler: KtConfigErrorHandler): T = super.getValue(handler)!!
        }
    }
}
