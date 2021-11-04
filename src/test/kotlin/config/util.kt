package config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.booleanValue
import dev.s7a.spigot.config.getMapListUnsafe
import dev.s7a.spigot.config.intValue
import dev.s7a.spigot.config.mapValues
import dev.s7a.spigot.config.setUnsafe
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

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
class TestSection(override val config: KtConfig, override val path: String) : KtConfigSection {
    val int = intValue("int")
    val boolean = booleanValue("boolean")
}

/**
 * テストで使うデータクラス
 */
data class TestDataClass(val int: Int, val boolean: Boolean) {
    companion object : KtConfigValueType.Listable<TestDataClass> {
        override fun get(config: KtConfig, path: String): KtConfigResult<TestDataClass> {
            return config.intValue("$path.int").get().map { int ->
                config.booleanValue("$path.boolean").get().map { boolean ->
                    KtConfigResult.Success(TestDataClass(int, boolean))
                }
            }
        }

        override fun set(config: KtConfig, path: String, value: TestDataClass?) {
            if (value != null) {
                config.intValue("$path.int").set(value.int)
                config.booleanValue("$path.boolean").set(value.boolean)
            } else {
                config.setUnsafe(path, null)
            }
        }

        override val list: KtConfigValueType<List<TestDataClass>>
            get() = object : KtConfigValueType<List<TestDataClass>> {
                override fun get(config: KtConfig, path: String): KtConfigResult<List<TestDataClass>> {
                    return config.getMapListUnsafe(path).mapValues(config, path) { index, value ->
                        try {
                            val int = value["int"] as Int
                            val boolean = value["boolean"] as Boolean
                            KtConfigResult.Success(TestDataClass(int, boolean))
                        } catch (ex: ClassCastException) {
                            KtConfigResult.Failure(KtConfigError.ClassCastException(config, "$path#$index", TestDataClass::class.java.simpleName))
                        }
                    }
                }

                override fun set(config: KtConfig, path: String, value: List<TestDataClass>?) {
                    config.setUnsafe(
                        path,
                        value?.map {
                            mapOf(
                                "int" to it.int,
                                "boolean" to it.boolean,
                            )
                        }
                    )
                }
            }
    }
}

/**
 * コンフィグに文字列を書き込む
 */
fun KtConfig.writeText(text: String) {
    file.writeText(text)
    reload()
}

/**
 * .yml に保存される形式にフォーマットする
 */
fun Date.toVerboseString(): String {
    val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}
