package dev.s7a.spigot.config

import dev.s7a.spigot.config.formatter.DefaultLocationFormatter
import dev.s7a.spigot.config.formatter.DefaultUUIDFormatter
import dev.s7a.spigot.config.formatter.DefaultVectorFormatter
import dev.s7a.spigot.config.type.BooleanType
import dev.s7a.spigot.config.type.DataClassType
import dev.s7a.spigot.config.type.DateType
import dev.s7a.spigot.config.type.DoubleType
import dev.s7a.spigot.config.type.EnumType
import dev.s7a.spigot.config.type.FloatType
import dev.s7a.spigot.config.type.FormatterType
import dev.s7a.spigot.config.type.IntType
import dev.s7a.spigot.config.type.LocationType
import dev.s7a.spigot.config.type.LongType
import dev.s7a.spigot.config.type.MaterialType
import dev.s7a.spigot.config.type.NumberType
import dev.s7a.spigot.config.type.SectionType
import dev.s7a.spigot.config.type.StringType
import dev.s7a.spigot.config.type.UUIDType
import dev.s7a.spigot.config.type.VectorType
import org.bukkit.Location
import org.bukkit.util.Vector
import java.util.UUID

/**
 * コンフィグの値を登録する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @param type 値の種類
 * @see dataClassValue
 * @see section
 * @since 1.0.0
 */
fun <T> KtConfigSection.value(path: String, type: KtConfigValueType<T>) = KtConfigValue.Base(config, fullPath(path), type)

/**
 * コンフィグの値を登録する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @param type 値の種類
 * @see booleanValue
 * @see dataClassValue
 * @see dateValue
 * @see doubleValue
 * @see enumNameValue
 * @see enumOrdinalValue
 * @see floatValue
 * @see formatterValue
 * @see intValue
 * @see locationValue
 * @see longValue
 * @see materialValue
 * @see numberValue
 * @see section
 * @see stringValue
 * @see uuidValue
 * @see vectorValue
 * @since 1.0.0
 */
fun <T> KtConfigSection.value(path: String, type: KtConfigValueType.Listable<T>) = KtConfigValue.Base.Listable(config, fullPath(path), type)

/**
 * [Boolean] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.booleanValue(path: String) = value(path, BooleanType)

/**
 * データクラスを登録する
 *
 * @param T セクション型
 * @param path コンフィグパス
 * @param converter データクラスとの変換を行うクラス
 * @since 1.0.0
 */
fun <T> KtConfigSection.dataClassValue(path: String, converter: KtConfigDataClassConverter<T>) = value(path, DataClassType(converter))

/**
 * データクラスを登録する
 *
 * @param T セクション型
 * @param path コンフィグパス
 * @param converter データクラスとの変換を行うクラス
 * @since 1.0.0
 */
fun <T> KtConfigSection.dataClassValue(path: String, converter: KtConfigDataClassConverter.Listable<T>) = value(path, DataClassType.Listable(converter))

/**
 * [java.util.Date] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.dateValue(path: String) = value(path, DateType)

/**
 * [Double] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.doubleValue(path: String) = value(path, DoubleType)

/**
 * [Enum.name] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / false
 * @since 1.0.0
 */
inline fun <reified T : Enum<T>> KtConfigSection.enumNameValue(path: String, ignoreCase: Boolean = false) = value(path, EnumType.Name(T::class.java, ignoreCase))

/**
 * [Enum.ordinal] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
inline fun <reified T : Enum<T>> KtConfigSection.enumOrdinalValue(path: String) = value(path, EnumType.Ordinal(T::class.java))

/**
 * [Float] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.floatValue(path: String) = value(path, FloatType)

/**
 * [KtConfigFormatter] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun <T> KtConfigSection.formatterValue(path: String, formatter: KtConfigFormatter<T>) = value(path, FormatterType(formatter))

/**
 * [Int] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.intValue(path: String) = value(path, IntType)

/**
 * [Location] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigSection.locationValue(path: String, formatter: KtConfigFormatter<Location> = DefaultLocationFormatter) = value(path, LocationType(formatter))

/**
 * [Long] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.longValue(path: String) = value(path, LongType)

/**
 * [org.bukkit.Material] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / true
 * @since 1.0.0
 */
fun KtConfigSection.materialValue(path: String, ignoreCase: Boolean = true) = value(path, MaterialType(ignoreCase))

/**
 * [Number] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.numberValue(path: String) = value(path, NumberType)

/**
 * セクションマップを登録する
 *
 * @param T セクション型
 * @param path コンフィグパス
 * @since 1.0.0
 */
inline fun <reified T : KtConfigSection> KtConfigSection.section(path: String) = value(path, SectionType(T::class.java))

/**
 * [String] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigSection.stringValue(path: String) = value(path, StringType)

/**
 * [UUID] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigSection.uuidValue(path: String, formatter: KtConfigFormatter<UUID> = DefaultUUIDFormatter) = value(path, UUIDType(formatter))

/**
 * [Vector] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigSection.vectorValue(path: String, formatter: KtConfigFormatter<Vector> = DefaultVectorFormatter) = value(path, VectorType(formatter))
