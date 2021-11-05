package config

import dev.s7a.spigot.config.intValue
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * コンフィグの一般的なテスト
 *
 * @see dev.s7a.spigot.config
 */
class ConfigGeneralTest {
    @BeforeTest
    fun setup() {
        TestConfig.file.delete()
        TestConfig.reload()
    }

    @Test
    fun `exists can get`() {
        TestConfig.intValue("value").run {
            assertFalse(exists()) // 値を設定していないと false
            set(0)
            assertTrue(exists()) // 値を設定すると true
            TestConfig.reload()
            assertFalse(exists()) // 値を設定しても保存しなければ、リロードすると false
            setAndSave(0)
            assertTrue(exists()) // 値を設定すると true
            TestConfig.reload()
            assertTrue(exists()) // 値を設定して保存すれば、リロードすると true
        }
    }
}
