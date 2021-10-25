package dev.s7a.spigot.config

import dev.s7a.spigot.config.type.BooleanType
import dev.s7a.spigot.config.type.DateType
import dev.s7a.spigot.config.type.DoubleType
import dev.s7a.spigot.config.type.FloatType
import dev.s7a.spigot.config.type.IntType
import dev.s7a.spigot.config.type.LongType
import dev.s7a.spigot.config.type.NumberType
import dev.s7a.spigot.config.type.StringType

/**
 * コンフィグの値を登録する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @param type 値の種類
 * @see booleanValue
 * @see dateValue
 * @see doubleValue
 * @see floatValue
 * @see intValue
 * @see longValue
 * @see numberValue
 * @see stringValue
 * @since 1.0.0
 */
fun <T> KtConfig.value(path: String, type: KtConfigValueType<T>) = KtConfigValue.Normal(this, path, type)

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
