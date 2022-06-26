package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigDataClassConverter
import dev.s7a.ktspigot.config.KtConfigError
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigSection
import dev.s7a.ktspigot.config.setUnsafe
import dev.s7a.ktspigot.config.type.booleanValue
import dev.s7a.ktspigot.config.type.intValue
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import kotlin.test.assertEquals

/**
 * テストで使うコンフィグ
 */
object TestConfig : KtConfig(File("build/tmp/test/config_test.yml"))

/**
 * テストで使用する列挙型
 */
enum class TestEnum {
    One,
    Two,
    Three,
}

/**
 * テストで使用するセクション
 */
class TestSection(override val config: KtConfigBase, override val path: String) : KtConfigSection {
    val int = intValue("int")
    val boolean = booleanValue("boolean")
}

/**
 * テストで使うデータクラス
 */
data class TestDataClass(val int: Int, val boolean: Boolean) {
    object Converter : KtConfigDataClassConverter.Listable<TestDataClass> {
        override fun get(config: KtConfigBase, path: String): KtConfigResult<TestDataClass> {
            return config.intValue("$path.int").get().map { int ->
                config.booleanValue("$path.boolean").get().map { boolean ->
                    KtConfigResult.Success(TestDataClass(int, boolean))
                }
            }
        }

        override fun set(config: KtConfigBase, path: String, value: TestDataClass?) {
            if (value != null) {
                config.intValue("$path.int").set(value.int)
                config.booleanValue("$path.boolean").set(value.boolean)
            } else {
                config.setUnsafe(path, null)
            }
        }

        override fun toValue(config: KtConfigBase, path: String, index: Int, map: Map<String, Any>): KtConfigResult<TestDataClass> {
            return try {
                val int = map["int"] as Int
                val boolean = map["boolean"] as Boolean
                KtConfigResult.Success(TestDataClass(int, boolean))
            } catch (ex: ClassCastException) {
                KtConfigResult.Failure(KtConfigError.ClassCastException(config, "$path#$index", TestDataClass::class.java.simpleName))
            }
        }

        override fun toMap(value: TestDataClass): Map<String, Any> {
            return mapOf(
                "int" to value.int,
                "boolean" to value.boolean
            )
        }
    }
}

/**
 * コンフィグに文字列を書き込む
 */
fun KtConfigBase.writeText(text: String) {
    file.writeText(text)
    load()
}

/**
 * コンフィグの内容をアサートする
 */
fun KtConfigBase.assertContent(expected: String) {
    assertEquals(expected, file.readText())
}

/**
 * .yml に保存される形式にフォーマットする
 */
fun Date.toVerboseString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}
