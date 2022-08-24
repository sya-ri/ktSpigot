package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigDataClassConverter
import dev.s7a.ktspigot.config.KtConfigFormatter
import dev.s7a.ktspigot.config.KtConfigValue
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.formatter.DefaultUUIDFormatter
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
fun <T> KtConfigBase.value(path: String, type: KtConfigValueType<T>) = KtConfigValue.Base(this, path, type)

/**
 * コンフィグの値を登録する
 *
 * @param T 値の型
 * @param path コンフィグパス
 * @param type 値の種類
 * @see dev.s7a.ktspigot.config.type.booleanValue
 * @see dev.s7a.ktspigot.config.type.dataClassValue
 * @see dev.s7a.ktspigot.config.type.dateValue
 * @see dev.s7a.ktspigot.config.type.doubleValue
 * @see dev.s7a.ktspigot.config.type.enumNameValue
 * @see dev.s7a.ktspigot.config.type.enumOrdinalValue
 * @see dev.s7a.ktspigot.config.type.floatValue
 * @see dev.s7a.ktspigot.config.type.formatterValue
 * @see dev.s7a.ktspigot.config.type.intValue
 * @see dev.s7a.ktspigot.config.type.locationValue
 * @see dev.s7a.ktspigot.config.type.longValue
 * @see dev.s7a.ktspigot.config.type.materialValue
 * @see dev.s7a.ktspigot.config.type.numberValue
 * @see dev.s7a.ktspigot.config.type.section
 * @see dev.s7a.ktspigot.config.type.stringValue
 * @see dev.s7a.ktspigot.config.type.uuidValue
 * @see dev.s7a.ktspigot.config.type.vectorValue
 * @since 1.0.0
 */
fun <T> KtConfigBase.value(path: String, type: KtConfigValueType.Listable<T>) = KtConfigValue.Base.Listable(this, path, type)

/**
 * [Boolean] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.booleanValue(path: String) = value(path, BooleanType)

/**
 * データクラスを登録する
 *
 * @param T セクション型
 * @param path コンフィグパス
 * @param converter データクラスとの変換を行うクラス
 * @since 1.0.0
 */
fun <T> KtConfigBase.dataClassValue(path: String, converter: KtConfigDataClassConverter<T>) = value(
    path,
    DataClassType.Default(converter)
)

/**
 * データクラスを登録する
 *
 * @param T セクション型
 * @param path コンフィグパス
 * @param converter データクラスとの変換を行うクラス
 * @since 1.0.0
 */
fun <T> KtConfigBase.dataClassValue(path: String, converter: KtConfigDataClassConverter.Listable<T>) = value(
    path,
    DataClassType.Listable(converter)
)

/**
 * [java.util.Date] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.dateValue(path: String) = value(path, DateType)

/**
 * [Double] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.doubleValue(path: String) = value(path, DoubleType)

/**
 * [Enum.name] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / false
 * @since 1.0.0
 */
fun <T : Enum<T>> KtConfigBase.enumNameValue(path: String, clazz: Class<T>, ignoreCase: Boolean = false) = value(
    path,
    EnumType.Name(clazz, ignoreCase)
)

/**
 * [Enum.name] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param ignoreCase 大文字小文字を無視するか / false
 * @since 1.0.0
 */
inline fun <reified T : Enum<T>> KtConfigBase.enumNameValue(path: String, ignoreCase: Boolean = false) = enumNameValue(path, T::class.java, ignoreCase)

/**
 * [Enum.ordinal] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun <T : Enum<T>> KtConfigBase.enumOrdinalValue(path: String, clazz: Class<T>) = value(path, EnumType.Ordinal(clazz))

/**
 * [Enum.ordinal] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
inline fun <reified T : Enum<T>> KtConfigBase.enumOrdinalValue(path: String) = enumOrdinalValue(path, T::class.java)

/**
 * [Float] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.floatValue(path: String) = value(path, FloatType)

/**
 * [KtConfigFormatter] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun <T> KtConfigBase.formatterValue(path: String, formatter: KtConfigFormatter<T>) = value(path, FormatterType(formatter))

/**
 * [Int] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.intValue(path: String) = value(path, IntType)

/**
 * [Long] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.longValue(path: String) = value(path, LongType)

/**
 * [Number] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.numberValue(path: String) = value(path, NumberType)

/**
 * [String] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @since 1.0.0
 */
fun KtConfigBase.stringValue(path: String) = value(path, StringType)

/**
 * [UUID] のコンフィグデータ型として値を登録する
 *
 * @param path コンフィグパス
 * @param formatter フォーマッタ
 * @since 1.0.0
 */
fun KtConfigBase.uuidValue(path: String, formatter: KtConfigFormatter<UUID> = DefaultUUIDFormatter) = formatterValue(path, formatter)
