package config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigDirectory
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * [KtConfigDirectory] に関するテスト
 */
class ConfigDirectoryTest {
    private val parentFile = File("build/tmp/test/directory")

    private class Config(file: File) : KtConfig(file)

    @BeforeTest
    fun setup() {
        parentFile.run {
            if (exists()) deleteRecursively()
            mkdirs()
            resolve("1.yml").createNewFile()
            resolve("2.yml").createNewFile()
            resolve("3").run {
                mkdir()
                resolve("4.yml").createNewFile()
                resolve("5.yml").createNewFile()
            }
        }
    }

    @Test
    fun `non recursive config directory can be get`() {
        val configDirectory = object : KtConfigDirectory<Config>(parentFile) {
            override fun new(file: File) = Config(file)
        }
        assertEquals(0, configDirectory.loadedConfigList.size)
        assertEquals(2, configDirectory.loadAll().size)
        assertEquals(2, configDirectory.loadedConfigList.size)
        assertNull(configDirectory.getOrNull("0.yml"))
        assertNotNull(configDirectory.getOrNull("1.yml"))
        assertEquals(2, configDirectory.loadedConfigList.size)
        assertNotNull(configDirectory["0.yml"])
        assertNotNull(configDirectory["1.yml"])
        assertEquals(3, configDirectory.loadedConfigList.size)
    }

    @Test
    fun `recursive config directory can be get`() {
        val configDirectory = object : KtConfigDirectory.Recursive<Config>(parentFile) {
            override fun new(file: File) = Config(file)
        }
        assertEquals(0, configDirectory.loadedConfigList.size)
        assertEquals(4, configDirectory.loadAll().size)
        assertEquals(4, configDirectory.loadedConfigList.size)
        assertNull(configDirectory.getOrNull("3/0.yml"))
        assertNotNull(configDirectory.getOrNull("3/4.yml"))
        assertEquals(4, configDirectory.loadedConfigList.size)
        assertNotNull(configDirectory["3/0.yml"])
        assertNotNull(configDirectory["3/4.yml"])
        assertEquals(5, configDirectory.loadedConfigList.size)
    }
}
