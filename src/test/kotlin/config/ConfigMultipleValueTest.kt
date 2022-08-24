package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.type.intValue
import randomString
import kotlin.io.path.createTempFile
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * list, map 等の複数の値を受け付けるコンフィグデータ型に関するテスト
 *
 * @see dev.s7a.ktspigot.config
 */
class ConfigMultipleValueTest {
    @Test
    fun `single value can be get as list`() {
        val expected = Random.nextInt()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").list()
        }
        testConfig.writeText(
            """
                value: $expected
            """.trimIndent()
        )
        assertEquals(listOf(expected), testConfig.value)
    }

    @Test
    fun `multiple value can be get as list`() {
        val expected = List(5) { Random.nextInt() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `multiple value can be edited as list`() {
        val expected1 = List(5) { Random.nextInt() }
        val expected2 = List(5) { Random.nextInt() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            var value by intValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected1.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected1, testConfig.value)
        testConfig.value = testConfig.value.orEmpty().toMutableList().apply {
            addAll(expected2)
        }
        assertEquals(expected1 + expected2, testConfig.value)
        testConfig.assertContent(
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
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").list(true)
        }
        testConfig.writeText(
            """
                value:
                 - a
                 - ${expected[0]}
                 - b
                 - ${expected[1]}
                 - c
            """.trimIndent()
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `forceGet can be get as list, returns null`() {
        val expected = List(2) { Random.nextInt() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").list(false)
        }
        testConfig.writeText(
            """
                value:
                 - a
                 - ${expected[0]}
                 - b
                 - ${expected[1]}
                 - c
            """.trimIndent()
        )
        assertNull(testConfig.value)
    }

    @Test
    fun `multiple value can be get as map`() {
        val expected = List(5) { randomString() to Random.nextInt() }.toMap()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").map()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `multiple value can be edited as map`() {
        val expected1 = List(5) { randomString() to Random.nextInt() }.toMap()
        val expected2 = List(5) { randomString() to Random.nextInt() }.toMap()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            var value by intValue("value").map()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected1.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        assertEquals(expected1, testConfig.value)
        testConfig.value = testConfig.value.orEmpty().toMutableMap().apply {
            putAll(expected2)
        }
        assertEquals(expected1 + expected2, testConfig.value)
        testConfig.assertContent(
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
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").map(true)
        }
        testConfig.writeText(
            """
                value:
                  a: a
                  b: ${expected[0]}
                  c: c
                  d: ${expected[1]}
                  e: e
            """.trimIndent()
        )
        assertContentEquals(expected, testConfig.value?.values)
    }

    @Test
    fun `forceGet can be get as map, returns null`() {
        val expected = List(2) { Random.nextInt() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").map(false)
        }
        testConfig.writeText(
            """
                value:
                  a: a
                  b: ${expected[0]}
                  c: c
                  d: ${expected[1]}
                  e: e
            """.trimIndent()
        )
        assertNull(testConfig.value)
    }
}
