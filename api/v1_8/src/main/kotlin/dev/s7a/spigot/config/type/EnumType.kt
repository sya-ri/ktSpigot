package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult

/**
 * [Enum] のコンフィグデータ型
 *
 * @since 1.0.0
 */
object EnumType {
    /**
     * [Enum.name] として扱うコンフィグデータ型
     *
     * @see dev.s7a.spigot.config.enumNameValue
     * @since 1.0.0
     */
    open class Name<T : Enum<T>>(private val clazz: Class<T>, private val ignoreCase: Boolean) : StringType.Base<T>() {
        /**
         * 名前から [T] に変換する処理
         */
        protected open val nameToEnum = { name: String ->
            clazz.enumConstants.firstOrNull { name.equals(it.name, ignoreCase) }
        }

        override val stringToResult = { config: KtConfig, path: String, name: String ->
            nameToEnum(name)?.let {
                KtConfigResult.Success(it)
            } ?: KtConfigResult.Failure(
                KtConfigError.NotFoundEnumConstant(config, path, "name: $name", clazz.simpleName)
            )
        }

        override val valueToString = { value: T -> value.name }
    }

    /**
     * [Enum.ordinal] として扱うコンフィグデータ型
     *
     * @see dev.s7a.spigot.config.enumOrdinalValue
     * @since 1.0.0
     */
    class Ordinal<T : Enum<T>>(private val clazz: Class<T>) : IntType.Base<T>() {
        override val intToResult = { config: KtConfig, path: String, ordinal: Int ->
            clazz.enumConstants.getOrNull(ordinal)?.let {
                KtConfigResult.Success(it)
            } ?: KtConfigResult.Failure(
                KtConfigError.NotFoundEnumConstant(config, path, "ordinal: $ordinal", clazz.simpleName)
            )
        }

        override val valueToInt = { value: T -> value.ordinal }
    }
}
