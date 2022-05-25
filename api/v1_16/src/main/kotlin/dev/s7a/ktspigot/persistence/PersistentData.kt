package dev.s7a.ktspigot.persistence

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

/**
 * [NamespacedKey] と [PersistentDataType] の定義クラス
 *
 * @property key キー
 * @property type 値の種類
 * @since 1.0.0
 */
data class PersistentData<T, Z : Any>(
    val key: NamespacedKey,
    val type: PersistentDataType<T, Z>
)
