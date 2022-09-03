package dev.s7a.ktspigot.entity

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * タイプが指定された仮想エンティティ
 *
 * @param uniqueId [UUID]
 * @since 1.0.0
 */
inline fun <reified T : Entity> SpecificVirtualEntity(uniqueId: UUID) = SpecificVirtualEntity(T::class.java, uniqueId)

/**
 * タイプが指定された仮想エンティティ
 *
 * @property clazz タイプクラス
 * @property uniqueId [UUID]
 * @since 1.0.0
 */
data class SpecificVirtualEntity<T : Entity>(val clazz: Class<T>, val uniqueId: UUID) {
    /**
     * エンティティとして取得する
     *
     * @since 1.0.0
     */
    fun get(): T? {
        val entity = Bukkit.getEntity(uniqueId)
        return if (clazz.isInstance(entity)) {
            clazz.cast(entity)
        } else {
            null
        }
    }

    override fun toString(): String {
        return uniqueId.toString()
    }
}
