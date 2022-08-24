package dev.s7a.ktspigot.config

import java.util.logging.Logger

/**
 * コンフィグから値を取得する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @return 取得した値
 * @since 1.0.0
 */
inline fun <reified T> KtConfigBase.getUnsafe(path: String): KtConfigResult<T> {
    return if (contains(path)) {
        val value = get(path)
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
inline fun <reified T> KtConfigBase.getListUnsafe(path: String): KtConfigResult<List<T>> {
    return if (contains(path)) {
        if (isList(path)) {
            getUnsafe<List<*>>(path).mapValues(this, path) { index, value ->
                try {
                    KtConfigResult.Success(value as T)
                } catch (ex: ClassCastException) {
                    KtConfigResult.Failure(
                        KtConfigError.ClassCastException(
                            this,
                            "$path#$index",
                            T::class.java.simpleName
                        )
                    )
                }
            }
        } else {
            getUnsafe<T>(path).map { KtConfigResult.Success(listOf(it)) }
        }
    } else {
        KtConfigResult.Failure(KtConfigError.NotFound(this, path))
    }
}

/**
 * コンフィグからマップリストを取得する
 *
 * @param path コンフィグパス
 * @return 取得した値
 * @since 1.0.0
 */
@Suppress("unchecked_cast")
fun KtConfigBase.getMapListUnsafe(path: String): KtConfigResult<List<Map<String, Any>>> {
    return if (contains(path)) {
        try {
            KtConfigResult.Success(getMapList(path) as List<Map<String, Any>>)
        } catch (ex: ClassCastException) {
            KtConfigResult.Failure(KtConfigError.ClassCastException(this, path, "List<Map<String, Any>>"))
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
fun KtConfigBase.setUnsafe(path: String, value: Any?) {
    set(path, value)
}

/**
 * 値のエラーを確認する
 *
 * @return エラー一覧
 * @see printErrors
 * @since 1.0.0
 */
fun KtConfigBase.checkValues(): List<KtConfigError> {
    val fields = this::class.java.declaredFields
    return buildList {
        fields.forEach { field ->
            try {
                field.isAccessible = true
                val value = field.get(this@checkValues)
                if (value is KtConfigValue<*>) {
                    val result = value.get()
                    if (result is KtConfigResult.Failure<*>) {
                        add(result.error)
                    }
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
 * コンフィグエラーを取得する
 *
 * @return メッセージ一覧
 * @since 1.0.0
 */
fun List<KtConfigError>.getErrors(): List<String> {
    return buildList {
        this@getErrors.groupBy(KtConfigError::config).forEach { (config, errorList) ->
            add("${config.file.path} のエラー [${errorList.size}]")
            errorList.forEach { error ->
                add("- ${error.message}")
                if (error is KtConfigError.ErrorContainer<*>) {
                    error.errors.forEach {
                        add("  - ${it.error.message}")
                    }
                }
            }
        }
    }
}

/**
 * コンフィグエラーを出力する
 *
 * @param logger 出力先
 * @see KtConfigBase.checkValues
 * @since 1.0.0
 */
fun List<KtConfigError>.printErrors(logger: Logger) {
    getErrors().forEach {
        logger.warning(it)
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
fun <T, R> KtConfigResult<List<T>>.mapValues(config: KtConfigBase, path: String, action: (Int, T) -> KtConfigResult<R>): KtConfigResult<List<R>> {
    return map { it.mapIndexed(action).flatten(config, path) }
}

/**
 * [KtConfigResult] のリストを展開する
 *
 * @param config コンフィグ
 * @param path コンフィグパス
 * @return リスト内に一つでもエラーがあれば [KtConfigError.ListConfigError] で失敗したことにする
 * @since 1.0.0
 */
fun <T> List<KtConfigResult<T>>.flatten(config: KtConfigBase, path: String): KtConfigResult<List<T>> {
    val data = filterIsInstance<KtConfigResult.Success<T>>().map(KtConfigResult.Success<T>::value)
    val errors = filterIsInstance<KtConfigResult.Failure<T>>()
    return if (errors.isEmpty()) {
        KtConfigResult.Success(data)
    } else {
        KtConfigResult.Failure(KtConfigError.ListConfigError(config, path, data, errors))
    }
}

/**
 * [KtConfigResult] のリストを展開する
 *
 * @param config コンフィグ
 * @param path コンフィグパス
 * @return リスト内に一つでもエラーがあれば [KtConfigError.ListConfigError] で失敗したことにする
 * @since 1.0.0
 */
fun <T> Map<String, KtConfigResult<T>>.flatten(config: KtConfigBase, path: String): KtConfigResult<Map<String, T>> {
    val data = mutableMapOf<String, T>()
    val errors = mutableListOf<KtConfigResult.Failure<T>>()
    entries.forEach { (key, value) ->
        when (value) {
            is KtConfigResult.Success<T> -> {
                data[key] = value.value
            }
            is KtConfigResult.Failure<T> -> {
                errors.add(value)
            }
        }
    }
    return if (errors.isEmpty()) {
        KtConfigResult.Success(data)
    } else {
        KtConfigResult.Failure(KtConfigError.MapConfigError(config, path, data, errors))
    }
}

/**
 * セクション内の指定したパスの値を取得する
 *
 * @param T セクション
 * @param path コンフィグパス
 * @return セクション内にパスが存在すれば値を返し、しなければ [KtConfigResult.Failure] を返す
 * @since 1.0.0
 */
inline fun <reified T : KtConfigBase> KtConfigValue<Map<String, T>>.get(path: String): KtConfigResult<T> {
    val fullPath = this.path + "." + path
    return if (config.contains(fullPath)) {
        val constructor = T::class.java.getDeclaredConstructor(KtConfigBase::class.java, String::class.java)
        KtConfigResult.Success(constructor.newInstance(config, fullPath))
    } else {
        KtConfigResult.Failure(KtConfigError.NotFound(config, fullPath))
    }
}
