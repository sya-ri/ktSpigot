package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.type.intValue
import randomString
import java.io.File
import kotlin.io.path.createTempFile
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ConfigDelegateTest {
    @Test
    fun `base can be get`() {
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value")
        }
        val expected = Random.nextInt()
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `default can be get`() {
        val expected = Random.nextInt()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").default(expected)
        }
        testConfig.writeText("")
        assertEquals(expected, testConfig.value)
        testConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `default function can be get`() {
        val expected = Random.nextInt()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").default { expected }
        }
        testConfig.writeText("")
        assertEquals(expected, testConfig.value)
        testConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `default force can be get`() {
        val expected = Random.nextInt()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value: Int by intValue("value").default(expected).force()
        }
        testConfig.writeText("")
        assertEquals(expected, testConfig.value)
        testConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `null can be get`() {
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").nullable()
        }
        testConfig.writeText("")
        assertNull(testConfig.value)
    }

    @Test
    fun `nullable can be get`() {
        val expected = Random.nextInt()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").nullable()
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `list can be get`() {
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
    fun `map can be get`() {
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
    fun `value can be changed`() {
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            var value by intValue("value")
        }
        testConfig.writeText("")
        testConfig.assertContent("") // 値が消えている
        val expected = Random.nextInt()
        testConfig.value = expected
        assertEquals(testConfig.value, expected) // 値が変更されている
        testConfig.assertContent( // 値が保存されている
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `value can be changed (disable autoSave)`() {
        val testConfig = object : KtConfig(createTempFile().toFile(), false) {
            var value by intValue("value")
        }
        testConfig.writeText("")
        testConfig.assertContent("") // 値が消えている
        val expected = Random.nextInt()
        testConfig.value = expected
        assertEquals(testConfig.value, expected) // 値が変更されている
        testConfig.assertContent("") // 値が保存されていない
        testConfig.save()
        testConfig.assertContent( // 値が保存されている
            buildString {
                appendLine("value: $expected")
            }
        )
    }
}
