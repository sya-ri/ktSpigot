package showcase

import config.writeText
import dev.s7a.ktspigot.KtSpigotTest
import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.formatter.DefaultLocationFormatter
import dev.s7a.ktspigot.config.type.dataClassValue
import randomLocation
import randomString
import kotlin.io.path.createTempFile
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * [ExampleDataClass] に関するテスト
 *
 * @see showcase.ExampleDataClass
 */
class ExampleDataClassTest {
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `example data class can be get`() {
        val world = KtSpigotTest.addWorld(randomString())
        val expected = ExampleDataClass(Random.nextInt(), randomLocation(world))
        val formatter = DefaultLocationFormatter
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dataClassValue("value", ExampleDataClass.Converter(formatter))
        }
        testConfig.writeText(
            """
                value:
                  int: ${expected.int}
                  location: ${formatter.string(expected.location)}
            """.trimIndent()
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `example data class list can be get`() {
        val world = KtSpigotTest.addWorld(randomString())
        val expected = List(5) { ExampleDataClass(Random.nextInt(), randomLocation(world)) }
        val formatter = DefaultLocationFormatter
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dataClassValue("value", ExampleDataClass.Converter(formatter)).list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - int: ${it.int}")
                    appendLine("   location: ${formatter.string(it.location)}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }
}
