package config

import dev.s7a.ktspigot.KtSpigotTest
import dev.s7a.ktspigot.config.KtConfig
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.type.SpecificEntityType
import dev.s7a.ktspigot.config.type.booleanValue
import dev.s7a.ktspigot.config.type.dataClassValue
import dev.s7a.ktspigot.config.type.dateValue
import dev.s7a.ktspigot.config.type.doubleValue
import dev.s7a.ktspigot.config.type.entityValue
import dev.s7a.ktspigot.config.type.enumNameValue
import dev.s7a.ktspigot.config.type.enumOrdinalValue
import dev.s7a.ktspigot.config.type.floatValue
import dev.s7a.ktspigot.config.type.intValue
import dev.s7a.ktspigot.config.type.locationValue
import dev.s7a.ktspigot.config.type.longValue
import dev.s7a.ktspigot.config.type.materialValue
import dev.s7a.ktspigot.config.type.stringValue
import dev.s7a.ktspigot.config.type.uuidValue
import dev.s7a.ktspigot.config.type.vectorValue
import dev.s7a.ktspigot.entity.spawnEntity
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Skeleton
import org.bukkit.entity.Zombie
import randomLocation
import randomString
import randomVector
import java.util.Date
import java.util.UUID
import kotlin.io.path.createTempFile
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

/**
 * コンフィグデータ型に関するテスト
 *
 * @see dev.s7a.ktspigot.config
 */
class ConfigTypeTest {
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `boolean can be get`() {
        val expected = Random.nextBoolean()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by booleanValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `boolean list can be get`() {
        val expected = List(5) { Random.nextBoolean() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by booleanValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `date can be get`() {
        val expected = Date()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dateValue("value")
        }
        testConfig.writeText("value: ${expected.toVerboseString()}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `date list can be get`() {
        val expected = List(5) { Date() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dateValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.toVerboseString()}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `double can be get`() {
        val expected = Random.nextDouble()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by doubleValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `double list can be get`() {
        val expected = List(5) { Random.nextDouble() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by doubleValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `entity can be get`() {
        val world = KtSpigotTest.addWorld(randomString())
        val expected = world.spawnEntity<Zombie>(Location(world, 0.0, 0.0, 0.0))
        val testConfig = object : KtConfig(createTempFile().toFile()) {}
        testConfig.writeText("value: ${expected.uniqueId}")
        testConfig.entityValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Entity>>(this)
                assertEquals(expected, value)
            }
        }
        testConfig.entityValue<Zombie>("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Entity>>(this)
                assertEquals(expected, value)
            }
        }
        testConfig.entityValue<Skeleton>("value").run {
            get().run {
                assertIs<KtConfigResult.Failure<Entity>>(this)
                assertIs<SpecificEntityType.MismatchEntityTypeError>(error)
            }
        }
    }

    @Test
    fun `enum can be get from name`() {
        val expected = TestEnum.values().random()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by enumNameValue<TestEnum>("value", false)
        }
        testConfig.writeText("value: ${expected.name}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `enum list can be get from name`() {
        val expected = List(5) { TestEnum.values().random() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by enumNameValue<TestEnum>("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.name}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `enum can be get from ignore case name`() {
        val expected = TestEnum.values().random()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by enumNameValue<TestEnum>("value", true)
        }
        testConfig.writeText("value: ${expected.name.uppercase()}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `enum can be get from ordinal`() {
        val expected = TestEnum.values().random()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by enumOrdinalValue<TestEnum>("value")
        }
        testConfig.writeText("value: ${expected.ordinal}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `enum list can be get from ordinal`() {
        val expected = List(5) { TestEnum.values().random() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by enumOrdinalValue<TestEnum>("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.ordinal}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `float can be get`() {
        val expected = Random.nextFloat()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by floatValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `float list can be get`() {
        val expected = List(5) { Random.nextFloat() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by floatValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `int can be get`() {
        val expected = Random.nextInt()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `int list can be get`() {
        val expected = List(5) { Random.nextInt() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `int map can be get`() {
        val expected = List(5) { randomString() to Random.nextInt() }.toMap()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by intValue("value").map()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `location can be get`() {
        val worldName = randomString()
        val world = KtSpigotTest.addWorld(worldName)
        val expected = randomLocation(world)
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by locationValue("value")
        }
        testConfig.writeText("value: $worldName, ${expected.x}, ${expected.y}, ${expected.z}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `location unless world can be get`() {
        val expected = randomLocation(null)
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by locationValue("value")
        }
        testConfig.writeText("value: null, ${expected.x}, ${expected.y}, ${expected.z}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `location list can be get`() {
        val world = KtSpigotTest.addWorld(randomString())
        val expected = List(5) { randomLocation(world) }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by locationValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.world?.name}, ${it.x}, ${it.y}, ${it.z}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `long can be get`() {
        val expected = Random.nextLong()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by longValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `long list can be get`() {
        val expected = List(5) { Random.nextLong() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by longValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `material can be get`() {
        val expected = Material.values().random()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by materialValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `material list can be get`() {
        val expected = List(5) { Material.values().random() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by materialValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `string can be get`() {
        val expected = randomString()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by stringValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `string list can be get`() {
        val expected = List(5) { randomString() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by stringValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `uuid can be get`() {
        val expected = UUID.randomUUID()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by uuidValue("value")
        }
        testConfig.writeText("value: $expected")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `uuid list can be get`() {
        val expected = List(5) { UUID.randomUUID() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by uuidValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `vector can be get`() {
        val expected = randomVector()
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by vectorValue("value")
        }
        testConfig.writeText("value: ${expected.x}, ${expected.y}, ${expected.z}")
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `vector list can be get`() {
        val expected = List(5) { randomVector() }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by vectorValue("value").list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.x}, ${it.y}, ${it.z}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `data class can get`() {
        val expected = TestDataClass(Random.nextInt(), Random.nextBoolean())
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dataClassValue("value", TestDataClass.Converter)
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                appendLine("  int: ${expected.int}")
                appendLine("  boolean: ${expected.boolean}")
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `data class list can get`() {
        val expected = List(5) { TestDataClass(Random.nextInt(), Random.nextBoolean()) }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dataClassValue("value", TestDataClass.Converter).list()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - int: ${it.int}")
                    appendLine("   boolean: ${it.boolean}")
                }
            }
        )
        assertEquals(expected, testConfig.value)
    }

    @Test
    fun `data class map can get`() {
        val expected = List(5) { TestDataClass(Random.nextInt(), Random.nextBoolean()) }
        val testConfig = object : KtConfig(createTempFile().toFile()) {
            val value by dataClassValue("value", TestDataClass.Converter).map()
        }
        testConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEachIndexed { index, it ->
                    appendLine("  v$index:")
                    appendLine("    int: ${it.int}")
                    appendLine("    boolean: ${it.boolean}")
                }
            }
        )
        assertEquals(expected.mapIndexed { index, it -> "v$index" to it }.toMap(), testConfig.value)
    }
}
