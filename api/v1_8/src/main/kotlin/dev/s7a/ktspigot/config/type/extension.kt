@file:JvmName("Extension8")

package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigFormatter
import dev.s7a.ktspigot.config.formatter.DefaultBlockLocationFormatter
import dev.s7a.ktspigot.config.formatter.DefaultLocationFormatter
import dev.s7a.ktspigot.config.formatter.DefaultVectorFormatter
import dev.s7a.ktspigot.location.BlockLocation
import org.bukkit.Location
import org.bukkit.util.Vector

/**
 * [BlockLocation] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigBase.blockLocationValue(path: String, formatter: KtConfigFormatter<BlockLocation> = DefaultBlockLocationFormatter) = formatterValue(path, formatter)

/**
 * [Location] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigBase.locationValue(path: String, formatter: KtConfigFormatter<Location> = DefaultLocationFormatter) = formatterValue(path, formatter)

/**
 * [org.bukkit.Material] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / true
 * @since 1.0.0
 */
fun KtConfigBase.materialValue(path: String, ignoreCase: Boolean = true) = value(path, MaterialType(ignoreCase))

/**
 * [Vector] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigBase.vectorValue(path: String, formatter: KtConfigFormatter<Vector> = DefaultVectorFormatter) = formatterValue(path, formatter)
