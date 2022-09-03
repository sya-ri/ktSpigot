package dev.s7a.ktspigot.entity

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * 仮想エンティティ
 *
 * @property uniqueId [UUID]
 * @since 1.0.0
 */
data class VirtualEntity(val uniqueId: UUID) {
    /**
     * エンティティとして取得する
     *
     * @since 1.0.0
     */
    fun get(): Entity? = Bukkit.getEntity(uniqueId)

    override fun toString(): String {
        return uniqueId.toString()
    }
}
