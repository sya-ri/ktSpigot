package config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.booleanValue
import dev.s7a.spigot.config.intValue
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
