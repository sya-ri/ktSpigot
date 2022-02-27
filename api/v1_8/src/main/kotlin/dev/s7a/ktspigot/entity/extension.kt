@file:JvmName("Extension8")

package dev.s7a.ktspigot.entity

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity

/**
 * エンティティをスポーンさせる
 *
 * @param T エンティティタイプ
 * @param location スポーンさせる座標
 * @return エンティティ
 * @since 1.0.0
 */
inline fun <reified T : Entity> World.spawnEntity(location: Location): T {
    return spawn(location, T::class.java)
}
