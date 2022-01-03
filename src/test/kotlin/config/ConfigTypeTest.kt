package config

import be.seeseemelk.mockbukkit.MockBukkit
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.booleanValue
import dev.s7a.spigot.config.dataClassValue
import dev.s7a.spigot.config.dateValue
import dev.s7a.spigot.config.doubleValue
import dev.s7a.spigot.config.entityValue
import dev.s7a.spigot.config.enumNameValue
import dev.s7a.spigot.config.enumOrdinalValue
import dev.s7a.spigot.config.floatValue
import dev.s7a.spigot.config.intValue
import dev.s7a.spigot.config.locationValue
import dev.s7a.spigot.config.longValue
import dev.s7a.spigot.config.materialValue
import dev.s7a.spigot.config.section
import dev.s7a.spigot.config.stringValue
import dev.s7a.spigot.config.uuidValue
import dev.s7a.spigot.config.vectorValue
import dev.s7a.spigot.entity.spawnEntity
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Zombie
import org.bukkit.util.Vector
import randomLocation
import randomString
import randomVector
import java.util.Date
import java.util.UUID
import kotlin.random.Random
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

/**
 * コンフィグデータ型に関するテスト
 *
 * @see dev.s7a.spigot.config
 */
class ConfigTypeTest {
    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @Test
    fun `boolean can be get`() {
        val expected = Random.nextBoolean()
        TestConfig.writeText("value: $expected")
        TestConfig.booleanValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Boolean>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `boolean list can be get`() {
        val expected = List(5) { Random.nextBoolean() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.booleanValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Boolean>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `date can be get`() {
        val expected = Date()
        TestConfig.writeText("value: ${expected.toVerboseString()}")
        TestConfig.dateValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Date>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `date list can be get`() {
        val expected = List(5) { Date() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.toVerboseString()}")
                }
            }
        )
        TestConfig.dateValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Date>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `double can be get`() {
        val expected = Random.nextDouble()
        TestConfig.writeText("value: $expected")
        TestConfig.doubleValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Double>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `double list can be get`() {
        val expected = List(5) { Random.nextDouble() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.doubleValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Double>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Ignore // NotImplemented by MockBukkit
    @Test
    fun `entity can be get`() {
        val world = server.addSimpleWorld(randomString())
        val expected = world.spawnEntity<Zombie>(Location(world, 0.0, 0.0, 0.0))
        TestConfig.writeText("value: ${expected.uniqueId}")
        TestConfig.entityValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Entity>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `enum can be get from name`() {
        val expected = TestEnum.values().random()
        TestConfig.writeText("value: ${expected.name}")
        TestConfig.enumNameValue<TestEnum>("value", false).run {
            get().run {
                assertIs<KtConfigResult.Success<TestEnum>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `enum list can be get from name`() {
        val expected = List(5) { TestEnum.values().random() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.name}")
                }
            }
        )
        TestConfig.enumNameValue<TestEnum>("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<TestEnum>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `enum can be get from ignore case name`() {
        val expected = TestEnum.values().random()
        TestConfig.writeText("value: ${expected.name.uppercase()}")
        TestConfig.enumNameValue<TestEnum>("value", true).run {
            get().run {
                assertIs<KtConfigResult.Success<TestEnum>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `enum can be get from ordinal`() {
        val expected = TestEnum.values().random()
        TestConfig.writeText("value: ${expected.ordinal}")
        TestConfig.enumOrdinalValue<TestEnum>("value").run {
            get().run {
                assertIs<KtConfigResult.Success<TestEnum>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `enum list can be get from ordinal`() {
        val expected = List(5) { TestEnum.values().random() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.ordinal}")
                }
            }
        )
        TestConfig.enumOrdinalValue<TestEnum>("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<TestEnum>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `float can be get`() {
        val expected = Random.nextFloat()
        TestConfig.writeText("value: $expected")
        TestConfig.floatValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Float>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `float list can be get`() {
        val expected = List(5) { Random.nextFloat() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.floatValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Float>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `int can be get`() {
        val expected = Random.nextInt()
        TestConfig.writeText("value: $expected")
        TestConfig.intValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Int>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `int list can be get`() {
        val expected = List(5) { Random.nextInt() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.intValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Int>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `int map can be get`() {
        val expected = List(5) { randomString() to Random.nextInt() }.toMap()
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    appendLine("  $key: $value")
                }
            }
        )
        TestConfig.intValue("value").map().run {
            get().run {
                assertIs<KtConfigResult.Success<Map<String, Int>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `location can be get`() {
        val worldName = randomString()
        val world = server.addSimpleWorld(worldName)
        val expected = randomLocation(world)
        TestConfig.writeText("value: ${expected.world?.name}, ${expected.x}, ${expected.y}, ${expected.z}")
        TestConfig.locationValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Location>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `location unless world can be get`() {
        val expected = randomLocation(null)
        TestConfig.writeText("value: ${expected.world?.name}, ${expected.x}, ${expected.y}, ${expected.z}")
        TestConfig.locationValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Location>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `location list can be get`() {
        val world = server.addSimpleWorld(randomString())
        val expected = List(5) { randomLocation(world) }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.world?.name}, ${it.x}, ${it.y}, ${it.z}")
                }
            }
        )
        TestConfig.locationValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Location>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `long can be get`() {
        val expected = Random.nextLong()
        TestConfig.writeText("value: $expected")
        TestConfig.longValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Long>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `long list can be get`() {
        val expected = List(5) { Random.nextLong() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.longValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Long>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `material can be get`() {
        val expected = Material.values().random()
        TestConfig.writeText("value: $expected")
        TestConfig.materialValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Material>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `material list can be get`() {
        val expected = List(5) { Material.values().random() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.materialValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Material>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `mapList can be get`() {
        val expected = List(5) {
            randomString() to (Random.nextInt() to Random.nextBoolean())
        }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach { (key, value) ->
                    val (int, boolean) = value
                    appendLine("  $key:")
                    appendLine("    int: $int")
                    appendLine("    boolean: $boolean")
                }
            }
        )
        TestConfig.section<TestSection>("value").run {
            val value = getValue()
            assertNotNull(value)
            assertEquals(expected, value.map { it.key to (it.value.int.getValue() to it.value.boolean.getValue()) })
        }
    }

    @Test
    fun `string can be get`() {
        val expected = randomString()
        TestConfig.writeText("value: $expected")
        TestConfig.stringValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<String>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `string list can be get`() {
        val expected = List(5) { randomString() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.stringValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<String>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `uuid can be get`() {
        val expected = UUID.randomUUID()
        TestConfig.writeText("value: $expected")
        TestConfig.uuidValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<UUID>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `uuid list can be get`() {
        val expected = List(5) { UUID.randomUUID() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - $it")
                }
            }
        )
        TestConfig.uuidValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<UUID>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `vector can be get`() {
        val expected = randomVector()
        TestConfig.writeText("value: ${expected.x}, ${expected.y}, ${expected.z}")
        TestConfig.vectorValue("value").run {
            get().run {
                assertIs<KtConfigResult.Success<Vector>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `vector list can be get`() {
        val expected = List(5) { randomVector() }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - ${it.x}, ${it.y}, ${it.z}")
                }
            }
        )
        TestConfig.vectorValue("value").list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<Vector>>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `data class can get`() {
        val expected = TestDataClass(Random.nextInt(), Random.nextBoolean())
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                appendLine("  int: ${expected.int}")
                appendLine("  boolean: ${expected.boolean}")
            }
        )
        TestConfig.dataClassValue("value", TestDataClass.Converter).run {
            get().run {
                assertIs<KtConfigResult.Success<TestDataClass>>(this)
                assertEquals(expected, value)
            }
        }
    }

    @Test
    fun `data class list can get`() {
        val expected = List(5) { TestDataClass(Random.nextInt(), Random.nextBoolean()) }
        TestConfig.writeText(
            buildString {
                appendLine("value:")
                expected.forEach {
                    appendLine(" - int: ${it.int}")
                    appendLine("   boolean: ${it.boolean}")
                }
            }
        )
        TestConfig.dataClassValue("value", TestDataClass.Converter).list().run {
            get().run {
                assertIs<KtConfigResult.Success<List<TestDataClass>>>(this)
                assertEquals(expected, value)
            }
        }
    }
}
