package dev.s7a.spigot.config

import java.util.logging.Logger

/**
 * コンフィグエラーを出力する
 *
 * @param logger 出力先
 * @see KtConfig.checkValues
 * @since 1.0.0
 */
fun List<KtConfigError>.printError(logger: Logger) {
    groupBy(KtConfigError::config).forEach { (config, errorList) ->
        logger.warning("${config.file.path} のエラー [${errorList.size}]")
        errorList.forEach {
            logger.warning("- ${it.message}")
        }
    }
}

/**
 * コンフィグから値を取得する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @return 取得した値
 * @since 1.0.0
 */
inline fun <reified T> KtConfig.getUnsafe(path: String): KtConfigResult<T> {
    return if (bukkitConfig.contains(path)) {
        val value = bukkitConfig.get(path)
        try {
            KtConfigResult.Success(value as T)
        } catch (ex: ClassCastException) {
            KtConfigResult.Failure(KtConfigError.ClassCastException(this, path, T::class.java.simpleName))
        }
    } else {
        KtConfigResult.Failure(KtConfigError.NotFound(this, path))
    }
}

/**
 * コンフィグに値を設定する
 *
 * @param path コンフィグパス
 * @param value 設定する値
 * @since 1.0.0
 */
fun KtConfig.setUnsafe(path: String, value: Any?) {
    bukkitConfig.set(path, value)
}

/**
 * 値のエラーを確認する
 *
 * @return エラー一覧
 * @see printError
 * @since 1.0.0
 */
@OptIn(ExperimentalStdlibApi::class)
fun KtConfig.checkValues(): List<KtConfigError> {
    val values = this::class.java.declaredFields
    return buildList {
        values.forEach { field ->
            try {
                field.isAccessible = true
                val value = field.get(this)
                if (value is KtConfigValue<*>) {
                    value.getValue(::add)
                }
            } catch (ex: SecurityException) {
                add(KtConfigError.Reflection.ThrowSecurityException(this@checkValues, field))
            } catch (ex: IllegalAccessException) {
                add(KtConfigError.Reflection.ThrowIllegalAccessException(this@checkValues, field))
            }
        }
    }
}
