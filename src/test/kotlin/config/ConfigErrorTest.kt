package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.KtConfigError
import dev.s7a.ktspigot.config.KtConfigErrorHandler
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.checkValues
import dev.s7a.ktspigot.config.getErrors
import dev.s7a.ktspigot.config.type.intValue
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * コンフィグエラーが投げられるかのテスト
 */
class ConfigErrorTest {
    @Test
    fun `type mismatch error is thrown`() {
        TestConfig.writeText(
            """
                value: Hello
            """.trimIndent()
        )
        var errors: List<KtConfigError>? = null
        val tempConfig = object : KtConfig(File("build/tmp/test/config_test.yml")) {
            val int = intValue("value")

            override fun load() {
                super.load()
                errors = checkValues()
            }
        }
        tempConfig.load()
        errors.let {
            assertNotNull(it)
            assertEquals(1, it.size)
            assertIs<KtConfigError.ClassCastException>(it.first())
            assertEquals(listOf("build/tmp/test/config_test.yml のエラー [1]", "- value の値を Number として取得できませんでした"), it.getErrors())
        }
        tempConfig.int.get().let {
            assertIs<KtConfigResult.Failure<Int>>(it)
            val error = it.error
            assertIs<KtConfigError.ClassCastException>(error)
            assertEquals("value の値を Number として取得できませんでした", error.message)
        }
        val executed = AtomicBoolean(false)
        val handler = KtConfigErrorHandler {
            executed.set(true)
            assertIs<KtConfigError.ClassCastException>(it)
            assertEquals("value の値を Number として取得できませんでした", it.message)
        }
        assertNull(tempConfig.int.getValue(handler))
        assertTrue(executed.get())
    }
}
