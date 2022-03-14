package config

import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigSection
import dev.s7a.ktspigot.config.type.intValue
import dev.s7a.ktspigot.config.type.section
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ConfigSectionTest {
    @Test
    fun `section can be get`() {
        class KtConfigBaseString(override val config: KtConfigBase, override val path: String) : KtConfigSection {
            val int = intValue("int")
        }
        class StringKtConfigBase(override val path: String, override val config: KtConfigBase) : KtConfigSection {
            val int = intValue("int")
        }
        class KtConfigString(override val config: KtConfig, override val path: String) : KtConfigSection {
            val int = intValue("int")
        }
        class StringKtConfig(override val path: String, override val config: KtConfig) : KtConfigSection {
            val int = intValue("int")
        }

        val expected = Random.nextInt()
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                appendLine("  int: $expected")
            }
        )

        TestConfig.section<KtConfigBaseString>("value").run {
            val value = getValue()
            assertNotNull(value)
            assertEquals(expected, value.int.getValue())
        }
        TestConfig.section<StringKtConfigBase>("value").run {
            val value = getValue()
            assertNotNull(value)
            assertEquals(expected, value.int.getValue())
        }
        TestConfig.section<KtConfigString>("value").run {
            val value = getValue()
            assertNotNull(value)
            assertEquals(expected, value.int.getValue())
        }
        TestConfig.section<StringKtConfig>("value").run {
            val value = getValue()
            assertNotNull(value)
            assertEquals(expected, value.int.getValue())
        }
    }
}
