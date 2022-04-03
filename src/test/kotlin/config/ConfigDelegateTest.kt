package config

import dev.s7a.ktspigot.config.type.intValue
import randomString
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ConfigDelegateTest {
    @Test
    fun `base can be get`() {
        val expected = Random.nextInt()
        TestConfig.writeText("value: $expected")
        val value by TestConfig.intValue("value")
        assertEquals(expected, value)
    }

    @Test
    fun `default can be get`() {
        TestConfig.writeText("")
        val expected = Random.nextInt()
        val value by TestConfig.intValue("value").default(expected)
        assertEquals(expected, value)
        TestConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `default function can be get`() {
        TestConfig.writeText("")
        val expected = Random.nextInt()
        val value by TestConfig.intValue("value").default { expected }
        assertEquals(expected, value)
        TestConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `default force can be get`() {
        TestConfig.writeText("")
        val expected = Random.nextInt()
        val value: Int by TestConfig.intValue("value").default(expected).force()
        assertEquals(expected, value)
        TestConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `null can be get`() {
        TestConfig.writeText("")
        val value by TestConfig.intValue("value").nullable()
        assertNull(value)
    }

    @Test
    fun `nullable can be get`() {
        val expected = Random.nextInt()
        TestConfig.writeText("value: $expected")
        val value by TestConfig.intValue("value").nullable()
        assertEquals(expected, value)
    }

    @Test
    fun `list can be get`() {
        val expected = List(5) { Random.nextInt() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        val value by TestConfig.intValue("value").list()
        assertEquals(expected, value)
    }

    @Test
    fun `map can be get`() {
        val expected = List(5) { randomString() to Random.nextInt() }.toMap()
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        val value by TestConfig.intValue("value").map()
        assertEquals(expected, value)
    }

    @Test
    fun `value can be changed`() {
        var value by TestConfig.intValue("value")
        TestConfig.writeText("")
        TestConfig.assertContent("") // 値が消えている
        val expected = Random.nextInt()
        value = expected
        assertEquals(value, expected) // 値が変更されている
        TestConfig.assertContent("") // 値が保存されていない
        TestConfig.save()
        TestConfig.assertContent( // 値が保存されている
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `base can be get (autoSave)`() {
        val expected = Random.nextInt()
        TestConfig.writeText("value: $expected")
        val value by TestConfig.intValue("value").autoSave()
        assertEquals(expected, value)
    }

    @Test
    fun `default can be get (autoSave)`() {
        TestConfig.writeText("")
        val expected = Random.nextInt()
        val value by TestConfig.intValue("value").default(expected).autoSave()
        assertEquals(expected, value)
        TestConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `default function can be get (autoSave)`() {
        TestConfig.writeText("")
        val expected = Random.nextInt()
        val value by TestConfig.intValue("value").default { expected }.autoSave()
        assertEquals(expected, value)
        TestConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `default force can be get (autoSave)`() {
        TestConfig.writeText("")
        val expected = Random.nextInt()
        val value: Int by TestConfig.intValue("value").default(expected).force().autoSave()
        assertEquals(expected, value)
        TestConfig.assertContent(
            buildString {
                appendLine("value: $expected")
            }
        )
    }

    @Test
    fun `null can be get (autoSave)`() {
        TestConfig.writeText("")
        val value by TestConfig.intValue("value").nullable().autoSave()
        assertNull(value)
    }

    @Test
    fun `nullable can be get (autoSave)`() {
        val expected = Random.nextInt()
        TestConfig.writeText("value: $expected")
        val value by TestConfig.intValue("value").nullable().autoSave()
        assertEquals(expected, value)
    }

    @Test
    fun `list can be get (autoSave)`() {
        val expected = List(5) { Random.nextInt() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        val value by TestConfig.intValue("value").list().autoSave()
        assertEquals(expected, value)
    }

    @Test
    fun `map can be get (autoSave)`() {
        val expected = List(5) { randomString() to Random.nextInt() }.toMap()
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        val value by TestConfig.intValue("value").map().autoSave()
        assertEquals(expected, value)
    }

    @Test
    fun `value can be changed (autoSave)`() {
        var value by TestConfig.intValue("value").autoSave()
        TestConfig.writeText("")
        TestConfig.assertContent("") // 値が消えている
        val expected = Random.nextInt()
        value = expected
        assertEquals(value, expected) // 値が変更されている
        TestConfig.assertContent( // 値が保存されている
            buildString {
                appendLine("value: $expected")
            }
        )
    }
}
