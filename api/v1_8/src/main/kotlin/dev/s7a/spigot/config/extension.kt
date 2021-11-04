package dev.s7a.spigot.config

import java.util.logging.Logger

/**
 * コンフィグから値を取得する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @return 取得した値
 * @since 1.0.0
 */
inline fun <reified T> KtConfig.getUnsafe(path: String): KtConfigResult<T> {
    return if (contains(path)) {
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
    return if (contains(path)) {
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
 * コンフィグからマップリストを取得する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @return 取得した値
 * @since 1.0.0
 */
@Suppress("unchecked_cast")
fun KtConfig.getMapListUnsafe(path: String): KtConfigResult<List<Map<String, Any>>> {
    return if (contains(path)) {
        try {
            KtConfigResult.Success(bukkitConfig.getMapList(path) as List<Map<String, Any>>)
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
fun KtConfig.setUnsafe(path: String, value: Any?) {
    bukkitConfig.set(path, value)
}

/**
 * セクション内の値を全て取得する
 *
 * @return 値一覧
 */
@OptIn(ExperimentalStdlibApi::class)
fun KtConfigSection.getValues(): List<KtConfigValue<*>> {
    val values = this::class.java.declaredFields
    return buildList {
        values.forEach { field ->
            try {
                field.isAccessible = true
                val value = field.get(this@getValues)
                if (value is KtConfigValue<*>) {
                    add(value)
                }
            } catch (_: SecurityException) {
            } catch (_: IllegalAccessException) {
            }
        }
    }
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
            if (error is KtConfigError.ErrorContainer<*>) {
                error.errors.forEach {
                    logger.warning("  - ${it.error.message}")
                }
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
 * [KtConfigResult] のリストを展開する
 *
 * @param config コンフィグ
 * @param path コンフィグパス
 * @return リスト内に一つでもエラーがあれば [KtConfigError.ListConfigError] で失敗したことにする
 * @since 1.0.0
 */
fun <T> Map<String, KtConfigResult<T>>.toResult(config: KtConfig, path: String): KtConfigResult<Map<String, T>> {
    val data = entries.filterIsInstance<Map.Entry<String, KtConfigResult.Success<T>>>().associate { it.key to it.value.value }
    val errors = values.filterIsInstance<KtConfigResult.Failure<T>>()
    return if (errors.isEmpty()) {
        KtConfigResult.Success(data)
    } else {
        KtConfigResult.Failure(KtConfigError.MapConfigError(config, path, data, errors))
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

/**
 * セクション内の指定したパスの値を取得する
 *
 * @param T セクション
 * @param path コンフィグパス
 * @return セクション内にパスが存在すれば値を返し、しなければ [KtConfigResult.Failure] を返す
 * @since 1.0.0
 */
inline fun <reified T : KtConfigSection> KtConfigValue<Map<String, T>>.get(path: String): KtConfigResult<T> {
    val fullPath = this.path + "." + path
    return if (config.contains(fullPath)) {
        val constructor = T::class.java.getDeclaredConstructor(KtConfig::class.java, String::class.java)
        KtConfigResult.Success(constructor.newInstance(config, fullPath))
    } else {
        KtConfigResult.Failure(KtConfigError.NotFound(config, fullPath))
    }
}

/**
 * セクションの値を変更する
 *
 * @param block 変更処理
 * @since 1.0.0
 */
inline fun <reified T : KtConfigSection> KtConfigValue<Map<String, T>>.edit(block: KtConfigSectionEditor<T>.() -> Unit) {
    val editor = KtConfigSectionEditor(config, path, T::class.java, getValue().orEmpty().toMutableMap())
    set(editor.apply(block).toMap())
}

/**
 * セクションの値を変更し、保存する
 *
 * @param block 変更処理
 * @since 1.0.0
 */
inline fun <reified T : KtConfigSection> KtConfigValue<Map<String, T>>.editAndSave(block: KtConfigSectionEditor<T>.() -> Unit) {
    edit(block)
    config.save()
}
