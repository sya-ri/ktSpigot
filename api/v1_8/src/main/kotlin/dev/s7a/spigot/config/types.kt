package dev.s7a.spigot.config

import dev.s7a.spigot.config.type.BooleanType
import dev.s7a.spigot.config.type.DateType
import dev.s7a.spigot.config.type.DoubleType
import dev.s7a.spigot.config.type.EnumType
import dev.s7a.spigot.config.type.FloatType
import dev.s7a.spigot.config.type.IntType
import dev.s7a.spigot.config.type.LongType
import dev.s7a.spigot.config.type.MaterialType
import dev.s7a.spigot.config.type.NumberType
import dev.s7a.spigot.config.type.StringType

/**
 * コンフィグの値を登録する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @param type 値の種類
 * @since 1.0.0
 */
fun <T> KtConfig.value(path: String, type: KtConfigValueType<T>) = KtConfigValue.Base(this, path, type)

/**
 * コンフィグの値を登録する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @param type 値の種類
 * @see booleanValue
 * @see dateValue
 * @see doubleValue
 * @see enumNameValue
 * @see enumOrdinalValue
 * @see floatValue
 * @see intValue
 * @see longValue
 * @see materialValue
 * @see numberValue
 * @see stringValue
 * @since 1.0.0
 */
fun <T> KtConfig.value(path: String, type: KtConfigValueType.Listable<T>) = KtConfigValue.Base.Listable(this, path, type)

/**
 * [Boolean] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.booleanValue(path: String) = value(path, BooleanType)

/**
 * [java.util.Date] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.dateValue(path: String) = value(path, DateType)

/**
 * [Double] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.doubleValue(path: String) = value(path, DoubleType)

/**
 * [Enum.name] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / false
 * @since 1.0.0
 */
inline fun <reified T : Enum<T>> KtConfig.enumNameValue(path: String, ignoreCase: Boolean = false) = value(path, EnumType.Name(T::class.java, ignoreCase))

/**
 * [Enum.ordinal] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
inline fun <reified T : Enum<T>> KtConfig.enumOrdinalValue(path: String) = value(path, EnumType.Ordinal(T::class.java))

/**
 * [Float] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.floatValue(path: String) = value(path, FloatType)

/**
 * [Int] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.intValue(path: String) = value(path, IntType)

/**
 * [Long] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.longValue(path: String) = value(path, LongType)

/**
 * [org.bukkit.Material] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / true
 * @since 1.0.0
 */
fun KtConfig.materialValue(path: String, ignoreCase: Boolean = true) = value(path, MaterialType(ignoreCase))

/**
 * [Number] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.numberValue(path: String) = value(path, NumberType)

/**
 * [String] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfig.stringValue(path: String) = value(path, StringType)
