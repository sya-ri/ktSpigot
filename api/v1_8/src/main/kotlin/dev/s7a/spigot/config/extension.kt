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
        errorList.forEach { error ->
            logger.warning("- ${error.message}")
            if (error is KtConfigError.ListConfigError<*>) {
                error.errors.forEach {
                    logger.warning("  - ${it.error.message}")
                }
            }
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
 * コンフィグからリストを取得する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @return 取得した値
 * @since 1.0.0
 */
@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> KtConfig.getListUnsafe(path: String): KtConfigResult<List<T>> {
    return if (bukkitConfig.contains(path)) {
        if (bukkitConfig.isList(path)) {
            getUnsafe<List<*>>(path).mapValues(this, path) { index, value ->
                try {
                    KtConfigResult.Success(value as T)
                } catch (ex: ClassCastException) {
                    KtConfigResult.Failure(KtConfigError.ClassCastException(this, "$path#$index", T::class.java.simpleName))
                }
            }
        } else {
            when (val result = getUnsafe<T>(path)) {
                is KtConfigResult.Success -> KtConfigResult.Success(listOf(result.value))
                is KtConfigResult.Failure -> KtConfigResult.Failure(result.error)
            }
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

/**
 * [KtConfigResult] でラッピングされた値を全て変換する
 *
 * @param T 変換前のデータ型
 * @param R 変換後のデータ型
 * @param config コンフィグ
 * @param path コンフィグパス
 * @param action 変換処理
 * @return 変換後のリスト
 * @since 1.0.0
 */
fun <T, R> KtConfigResult<List<T>>.mapValues(config: KtConfig, path: String, action: (Int, T) -> KtConfigResult<R>): KtConfigResult<List<R>> {
    return map { it.mapIndexed(action).toResult(config, path) }
}

/**
 * [KtConfigResult] のリストを展開する
 *
 * @param config コンフィグ
 * @param path コンフィグパス
 * @return リスト内に一つでもエラーがあれば [KtConfigError.ListConfigError] で失敗したことにする
 * @since 1.0.0
 */
fun <T> List<KtConfigResult<T>>.toResult(config: KtConfig, path: String): KtConfigResult<List<T>> {
    val data = filterIsInstance<KtConfigResult.Success<T>>().map(KtConfigResult.Success<T>::value)
    val errors = filterIsInstance<KtConfigResult.Failure<T>>()
    return if (errors.isEmpty()) {
        KtConfigResult.Success(data)
    } else {
        KtConfigResult.Failure(KtConfigError.ListConfigError(config, path, data, errors))
    }
}

/**
 * リストを取得する。エラーがある項目は無視する。
 *
 * @since 1.0.0
 */
@Suppress("UNCHECKED_CAST")
fun <T> KtConfigValue<List<T>>.forceGetValue(): List<T> {
    return when (val result = get()) {
        is KtConfigResult.Success -> result.value
        is KtConfigResult.Failure -> {
            when (result.error) {
                is KtConfigError.ListConfigError<*> -> result.error.data as List<T>
                else -> listOf()
            }
        }
    }
}
