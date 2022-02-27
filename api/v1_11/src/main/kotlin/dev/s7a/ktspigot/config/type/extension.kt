@file:JvmName("Extension11")

package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigSection
import org.bukkit.entity.Entity

/**
 * [org.bukkit.entity.Entity] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.entityValue(path: String) = value(path, EntityType)

/**
 * [org.bukkit.entity.Entity] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
@JvmName("specificEntityValue")
fun <T : Entity> KtConfigSection.entityValue(path: String, clazz: Class<T>) = value(path, SpecificEntityType(clazz))

/**
 * [org.bukkit.entity.Entity] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
@JvmName("specificEntityValue")
inline fun <reified T : Entity> KtConfigSection.entityValue(path: String) = entityValue(path, T::class.java)
