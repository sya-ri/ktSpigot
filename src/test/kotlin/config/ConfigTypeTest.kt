package config

import be.seeseemelk.mockbukkit.MockBukkit
import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.booleanValue
import dev.s7a.spigot.config.dateValue
import dev.s7a.spigot.config.doubleValue
import dev.s7a.spigot.config.enumNameValue
import dev.s7a.spigot.config.enumOrdinalValue
import dev.s7a.spigot.config.floatValue
import dev.s7a.spigot.config.formatter.DefaultLocationFormatter
import dev.s7a.spigot.config.formatter.DefaultVectorFormatter
import dev.s7a.spigot.config.intValue
import dev.s7a.spigot.config.locationValue
import dev.s7a.spigot.config.longValue
import dev.s7a.spigot.config.materialValue
import dev.s7a.spigot.config.stringValue
import dev.s7a.spigot.config.uuidValue
import dev.s7a.spigot.config.vectorValue
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.util.Vector
import randomLocation
import randomString
import randomVector
import java.io.File
import java.util.Date
import java.util.UUID
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

/**
 * コンフィグに関するテスト
 *
 * @see dev.s7a.spigot.config
 */
class ConfigTypeTest {
    /**
     * テストで使うコンフィグ
     */
    object TestConfig : KtConfig(File("build/tmp/test/config_test.yml"))

    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @BeforeTest
    fun setup() {
        TestConfig.file.delete()
        TestConfig.reload()
    }

    @Test
    fun `boolean can be get true`() {
        TestConfig.booleanValue("value").run {
            setAndSave(true)
            get().run {
                assertIs<KtConfigResult.Success<Boolean>>(this)
                assertTrue(value)
            }
        }
        assertConfigContent("value" to "true", TestConfig)
    }

    @Test
    fun `boolean can be get false`() {
        TestConfig.booleanValue("value").run {
            setAndSave(false)
            get().run {
                assertIs<KtConfigResult.Success<Boolean>>(this)
                assertFalse(value)
            }
        }
        assertConfigContent("value" to "false", TestConfig)
    }

    @Test
    fun `boolean list can be get`() {
        val expected = List(5) { Random.nextBoolean() }
        TestConfig.booleanValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Boolean>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Boolean::toString), TestConfig)
    }

    @Test
    fun `date can be get`() {
        val expected = Date()
        TestConfig.dateValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Date>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.toVerboseString(), TestConfig)
    }

    @Test
    fun `date list can be get`() {
        val expected = List(5) { Date() }
        TestConfig.dateValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Date>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Date::toVerboseString), TestConfig)
    }

    @Test
    fun `double can be get 0`() {
        val expected = 0.0
        TestConfig.doubleValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Double>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `double can be get`() {
        val expected = Random.nextDouble()
        TestConfig.doubleValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Double>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `double list can be get`() {
        val expected = List(5) { Random.nextDouble() }
        TestConfig.doubleValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Double>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Double::toString), TestConfig)
    }

    @Test
    fun `enum can be get from name`() {
        val expected = TestEnum.values().random()
        TestConfig.enumNameValue<TestEnum>("value", false).run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<TestEnum>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.name, TestConfig)
    }

    @Test
    fun `enum list can be get from name`() {
        val expected = List(5) { TestEnum.values().random() }
        TestConfig.enumNameValue<TestEnum>("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<TestEnum>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(TestEnum::name), TestConfig)
    }

    @Test
    fun `enum can be get from ignore case name`() {
        val expected = TestEnum.values().random()
        TestConfig.stringValue("value").setAndSave(expected.name.lowercase())
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
        TestConfig.enumOrdinalValue<TestEnum>("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<TestEnum>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "${expected.ordinal}", TestConfig)
    }

    @Test
    fun `enum list can be get from ordinal`() {
        val expected = List(5) { TestEnum.values().random() }
        TestConfig.enumOrdinalValue<TestEnum>("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<TestEnum>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(TestEnum::ordinal).map(Int::toString), TestConfig)
    }

    @Test
    fun `float can be get`() {
        val expected = Random.nextFloat()
        TestConfig.floatValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Float>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `float list can be get`() {
        val expected = List(5) { Random.nextFloat() }
        TestConfig.floatValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Float>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Float::toString), TestConfig)
    }

    @Test
    fun `int can be get`() {
        val expected = Random.nextInt()
        TestConfig.intValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Int>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `int list can be get`() {
        val expected = List(5) { Random.nextInt() }
        TestConfig.intValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Int>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Int::toString), TestConfig)
    }

    @Test
    fun `location can be get`() {
        val worldName = randomString()
        val world = server.addSimpleWorld(worldName)
        val expected = randomLocation(world)
        TestConfig.locationValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Location>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to DefaultLocationFormatter.string(expected), TestConfig)
    }

    @Test
    fun `location unless world can be get`() {
        val expected = randomLocation(null)
        TestConfig.locationValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Location>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to DefaultLocationFormatter.string(expected), TestConfig)
    }

    @Test
    fun `location list can be get`() {
        val world = server.addSimpleWorld(randomString())
        val expected = List(5) { randomLocation(world) }
        TestConfig.locationValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Location>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(DefaultLocationFormatter::string), TestConfig)
    }

    @Test
    fun `long can be get`() {
        val expected = Random.nextLong()
        TestConfig.longValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Long>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `long list can be get`() {
        val expected = List(5) { Random.nextLong() }
        TestConfig.longValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Long>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Long::toString), TestConfig)
    }

    @Test
    fun `material can be get`() {
        val expected = Material.values().random()
        TestConfig.materialValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Material>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `material list can be get`() {
        val expected = List(5) { Material.values().random() }
        TestConfig.materialValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Material>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(Material::toString), TestConfig)
    }

    @Test
    fun `string can be get`() {
        val expected = randomString()
        TestConfig.stringValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<String>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected, TestConfig)
    }

    @Test
    fun `string list can be get`() {
        val expected = List(5) { randomString() }
        TestConfig.stringValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<String>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected, TestConfig)
    }

    @Test
    fun `uuid can be get`() {
        val expected = UUID.randomUUID()
        TestConfig.uuidValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<UUID>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to "$expected", TestConfig)
    }

    @Test
    fun `uuid list can be get`() {
        val expected = List(5) { UUID.randomUUID() }
        TestConfig.uuidValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<UUID>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(UUID::toString), TestConfig)
    }

    @Test
    fun `vector can be get`() {
        val expected = randomVector()
        TestConfig.vectorValue("value").run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<Vector>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to DefaultVectorFormatter.string(expected), TestConfig)
    }

    @Test
    fun `vector list can be get`() {
        val expected = List(5) { randomVector() }
        TestConfig.vectorValue("value").list().run {
            setAndSave(expected)
            get().run {
                assertIs<KtConfigResult.Success<List<Vector>>>(this)
                assertEquals(expected, value)
            }
        }
        assertConfigContent("value" to expected.map(DefaultVectorFormatter::string), TestConfig)
    }
}
