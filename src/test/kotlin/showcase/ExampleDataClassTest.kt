package showcase

import config.TestConfig
import config.writeText
import dev.s7a.ktspigot.KtSpigotTest
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.formatter.DefaultLocationFormatter
import dev.s7a.ktspigot.config.type.dataClassValue
import randomLocation
import randomString
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

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
        TestConfig.writeText(
            """
                value:
                  int: ${expected.int}
                  location: ${formatter.string(expected.location)}
            """.trimIndent()
        )
        TestConfig.dataClassValue("value", ExampleDataClass.Converter(formatter)).run {
            get().run {
                assertIs<KtConfigResult.Success<ExampleDataClass>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `example data class list can be get`() {
        val world = KtSpigotTest.addWorld(randomString())
        val expected = List(5) { ExampleDataClass(Random.nextInt(), randomLocation(world)) }
        val formatter = DefaultLocationFormatter
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - int: ${it.int}")
                    appendLine("   location: ${formatter.string(it.location)}")
                }
            }
        )
        TestConfig.dataClassValue("value", ExampleDataClass.Converter(formatter)).list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<ExampleDataClass>>>(this)
                assertEquals(expected, value)
            }
        }
    }
}
