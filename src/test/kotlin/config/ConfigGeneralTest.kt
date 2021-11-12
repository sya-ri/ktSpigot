package config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.booleanValue
import dev.s7a.spigot.config.intValue
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * コンフィグの一般的なテスト
 *
 * @see dev.s7a.spigot.config
 */
class ConfigGeneralTest {
    @Test
    fun `config can be accessed without loading`() {
        val executed = AtomicBoolean(false)
        val tempConfig = object : KtConfig(File("build/tmp/test/config_temp.yml")) {
            val value = booleanValue("value").default(true).force()

            override fun load() {
                super.load()
                executed.set(true)
            }
        }
        assertFalse(executed.get())
        assertTrue(tempConfig.value.getValue())
    }

    @Test
    fun `config can be loaded`() {
        val executed = AtomicBoolean(false)
        val tempConfig = object : KtConfig(File("build/tmp/test/config_temp.yml")) {
            val value = booleanValue("value").default(true).force()

            override fun load() {
                super.load()
                executed.set(true)
            }
        }
        tempConfig.load()
        assertTrue(executed.get())
        assertTrue(tempConfig.value.getValue())
    }

    @Test
    fun `exists can get`() {
        TestConfig.file.delete()
        TestConfig.load()
        TestConfig.intValue("value").run {
            assertFalse(exists()) // 値を設定していないと false
            set(0)
            assertTrue(exists()) // 値を設定すると true
            TestConfig.load()
            assertFalse(exists()) // 値を設定しても保存しなければ、リロードすると false
            setAndSave(0)
            assertTrue(exists()) // 値を設定すると true
            TestConfig.load()
            assertTrue(exists()) // 値を設定して保存すれば、リロードすると true
        }
    }
}
