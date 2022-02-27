package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.type.booleanValue
import dev.s7a.ktspigot.config.type.intValue
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * コンフィグの一般的なテスト
 *
 * @see dev.s7a.ktspigot.config
 */
class ConfigGeneralTest {
    @Test
    fun `config can be loaded`() {
        val executed = AtomicBoolean(false)
        val config = object : KtConfig(File("build/tmp/test/config_temp.yml")) {
            val value = booleanValue("value").default(true).force()

            override fun load() {
                super.load()
                executed.set(true)
            }
        }
        config.load()
        assertTrue(executed.get())
        assertTrue(config.value.getValue())
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

    @Test
    fun `config can be accessed without loading`() {
        val executed = AtomicBoolean(false)
        val config = object : KtConfig(File("build/tmp/test/config_temp.yml")) {
            val value = booleanValue("value").default(true).force()

            override fun load() {
                super.load()
                executed.set(true)
            }
        }
        assertFalse(executed.get())
        assertTrue(config.value.getValue())
    }

    @Test
    fun `lazy config load`() {
        val file = File("build/tmp/test/config_lazy.yml")
        file.delete()
        val config = object : KtConfig(file) {}
        assertFalse(file.exists())
        config.load()
        assertTrue(file.exists())
    }

    @Test
    fun `config is directory`() {
        val file = File("build/tmp/test/test_directory")
        file.delete()
        file.mkdir()
        val config = object : KtConfig(file) {
            val int = intValue("value").nullable()
        }
        assertFailsWith<FileNotFoundException> { config.load() }
        assertFailsWith<FileNotFoundException> { config.int.setAndSave(5) }
    }
}
