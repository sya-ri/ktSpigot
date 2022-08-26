package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.type.intValue
import java.io.FileNotFoundException
import kotlin.io.path.createTempDirectory
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
    fun `lazy config load`() {
        val directory = createTempDirectory()
        val file = directory.resolve("file").toFile()
        val config = object : KtConfig(file) {}
        assertFalse(file.exists())
        config.load()
        assertTrue(file.exists())
    }

    @Test
    fun `config is directory`() {
        val directory = createTempDirectory()
        val config = object : KtConfig(directory.toFile()) {
            var int by intValue("value").nullable()
        }
        assertFailsWith<FileNotFoundException> { config.load() }
        assertFailsWith<FileNotFoundException> { config.int = 5 }
    }
}
