package dev.s7a.ktspigot.persistence

import org.bukkit.persistence.PersistentDataContainer

/**
 * [PersistentData] で定義された値を変更する
 *
 * @param data データ定義
 * @param value 変更する値
 * @since 1.0.0
 */
fun <T, Z : Any> PersistentDataContainer.set(data: PersistentData<T, Z>, value: Z) {
    set(data.key, data.type, value)
}

/**
 * [PersistentData] で定義された値が [PersistentDataContainer] 内にあるか
 *
 * @param data データ定義
 * @return [Boolean]
 * @since 1.0.0
 */
fun <T, Z : Any> PersistentDataContainer.has(data: PersistentData<T, Z>): Boolean {
    return has(data.key, data.type)
}

/**
 * [PersistentData] で定義された値を取得する
 *
 * @param data データ定義
 * @return [Z]?
 * @since 1.0.0
 */
fun <T, Z : Any> PersistentDataContainer.get(data: PersistentData<T, Z>): Z? {
    return get(data.key, data.type)
}

/**
 * [PersistentData] で定義された値を取得し、存在しない場合は [defaultValue] を返す
 *
 * @param data データ定義
 * @param defaultValue デフォルト値
 * @return [Z]
 * @since 1.0.0
 */
fun <T, Z : Any> PersistentDataContainer.getOrDefault(data: PersistentData<T, Z>, defaultValue: Z): Z {
    return getOrDefault(data.key, data.type, defaultValue)
}

/**
 * [PersistentData] で定義された値を削除する
 *
 * @param data データ定義
 * @since 1.0.0
 */
fun <T, Z : Any> PersistentDataContainer.remove(data: PersistentData<T, Z>) {
    remove(data.key)
}
