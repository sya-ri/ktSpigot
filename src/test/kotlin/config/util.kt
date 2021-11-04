package config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.locationValue
import dev.s7a.spigot.config.mapList
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
class TestSection(override val config: KtConfig, override val path: String) : KtConfigSection {
    val location = locationValue("location")
    val nest = mapList<TestSection>("nest")
}

/**
 * コンフィグのファイル内容をアサートする
 */
fun assertConfigContent(expected: Pair<String, String>, actual: KtConfig) {
    assertEquals("${expected.first}: ${expected.second}\n", actual.file.readText())
}

/**
 * コンフィグのファイル内容をアサートする
 */
@JvmName("assertConfigContentList")
fun assertConfigContent(expected: Pair<String, List<String>>, actual: KtConfig) {
    assertEquals("${expected.first}:\n${expected.second.joinToString("\n") { "- $it" }}\n", actual.file.readText())
}

/**
 * コンフィグのファイル内容をアサートする
 */
@JvmName("assertConfigContentMap")
fun assertConfigContent(expected: Pair<String, Map<String, String>>, actual: KtConfig) {
    assertEquals("${expected.first}:\n${expected.second.entries.joinToString("\n") { "  ${it.key}:${it.value}" }}\n", actual.file.readText())
}

/**
 * .yml に保存される形式にフォーマットする
 */
fun Date.toVerboseString(): String {
    val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}
