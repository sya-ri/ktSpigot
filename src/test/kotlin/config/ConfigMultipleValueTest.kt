package config

import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.editAndSave
import dev.s7a.spigot.config.forceGetValue
import dev.s7a.spigot.config.intValue
import randomString
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

/**
 * list, map 等の複数の値を受け付けるコンフィグデータ型に関するテスト
 *
 * @see dev.s7a.spigot.config
 */
class ConfigMultipleValueTest {
    @Test
    fun `single value can be get as list`() {
        val expected = Random.nextInt()
        TestConfig.writeText(
            """
                value: $expected
            """.trimIndent()
        )
        TestConfig.intValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Int>>>(this)
                assertEquals(listOf(expected), value)
            }
        }
    }

    @Test
    fun `multiple value can be get as list`() {
        val expected = List(5) { Random.nextInt() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.intValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Int>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `multiple value can be edited as list`() {
        val expected1 = List(5) { Random.nextInt() }
        val expected2 = List(5) { Random.nextInt() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected1.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.intValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Int>>>(this)
                assertEquals(expected1, value)
            }
            editAndSave {
                addAll(expected2)
            }
            get().run {
                assertIs<KtConfigResult.Success<List<Int>>>(this)
                assertEquals(expected1 + expected2, value)
            }
        }
        TestConfig.assertContent(
            buildString {
                appendLine("value:")
                (expected1 + expected2).forEach {
                    appendLine("- $it")
                }
            }
        )
    }

    @Test
    fun `forceGet can be get as list, ignore error`() {
        val expected = List(2) { Random.nextInt() }
        TestConfig.writeText(
            """
                value:
                 - a
                 - ${expected[0]}
                 - b
                 - ${expected[1]}
                 - c
            """.trimIndent()
        )
        TestConfig.intValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Failure<List<Int>>>(this)
                assertIs<KtConfigError.ListConfigError<List<Int>>>(error)
                assertEquals(expected, forceGetValue())
            }
        }
    }

    @Test
    fun `multiple value can be get as map`() {
        val expected = List(5) { randomString() to Random.nextInt() }.toMap()
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        TestConfig.intValue("value").map().run {
            get().run {
                assertIs<KtConfigResult.Success<Map<String, Int>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `multiple value can be edited as map`() {
        val expected1 = List(5) { randomString() to Random.nextInt() }.toMap()
        val expected2 = List(5) { randomString() to Random.nextInt() }.toMap()
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected1.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        TestConfig.intValue("value").map().run {
            get().run {
                assertIs<KtConfigResult.Success<Map<String, Int>>>(this)
                assertEquals(expected1, value)
            }
            editAndSave {
                putAll(expected2)
            }
            get().run {
                assertIs<KtConfigResult.Success<Map<String, Int>>>(this)
                assertEquals(expected1 + expected2, value)
            }
        }
        TestConfig.assertContent(
            buildString {
                appendLine("value:")
                (expected1 + expected2).forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
    }

    @Test
    fun `forceGet can be get as map, ignore error`() {
        val expected = List(2) { Random.nextInt() }
        TestConfig.writeText(
            """
                value:
                  a: a
                  b: ${expected[0]}
                  c: c
                  d: ${expected[1]}
                  e: e
            """.trimIndent()
        )
        TestConfig.intValue("value").map().run {
            get().run {
                assertIs<KtConfigResult.Failure<Map<String, Int>>>(this)
                assertIs<KtConfigError.MapConfigError<Map<String, Int>>>(error)
                assertEquals(expected, forceGetValue().values.toList())
            }
        }
    }
}
