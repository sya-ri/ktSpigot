@file:JvmName("Types11")

package dev.s7a.spigot.config

import dev.s7a.spigot.config.type.EntityType

/**
 * [org.bukkit.entity.Entity] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.entityValue(path: String) = value(path, EntityType)
