package config

import dev.s7a.spigot.config.KtConfig
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import kotlin.test.assertEquals

/**
 * テストで使用する列挙型
 */
enum class TestEnum {
    One,
    Two,
    Three,
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
 * .yml に保存される形式にフォーマットする
 */
fun Date.toVerboseString(): String {
    val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}
