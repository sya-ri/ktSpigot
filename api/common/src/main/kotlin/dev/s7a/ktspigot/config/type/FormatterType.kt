package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigError
import dev.s7a.ktspigot.config.KtConfigFormatter
import dev.s7a.ktspigot.config.KtConfigResult

/**
 * [KtConfigFormatter] を使ったコンフィグデータ型
 *
 * @param T 値の型
 * @property formatter フォーマッタ
 * @see dev.s7a.ktspigot.config.formatterValue
 * @since 1.0.0
 */
class FormatterType<T>(private val formatter: KtConfigFormatter<T>) : StringType.Base<T>() {
    override val stringToResult = { config: KtConfigBase, path: String, string: String ->
        formatter.value(string)?.let {
            KtConfigResult.Success<T>(it)
        } ?: KtConfigResult.Failure(
            KtConfigError.IllegalFormat(config, path, string, formatter)
        )
    }

    override val valueToString = { value: T -> formatter.string(value) }
}
