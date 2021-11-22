@file:JvmName("Extension11")

package dev.s7a.spigot.entity

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.util.Consumer

/**
 * エンティティをスポーンさせる
 *
 * @param T エンティティタイプ
 * @param location スポーンさせる座標
 * @param block エンティティの初期化処理
 * @return エンティティ
 * @since 1.0.0
 */
inline fun <reified T : Entity> World.spawnEntity(location: Location, noinline block: T.() -> Unit): T {
    return spawn(location, T::class.java, Consumer(block))
}
