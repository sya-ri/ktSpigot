package showcase

import config.writeText
import dev.s7a.spigot.KtSpigotTest
import org.bukkit.Material
import randomString
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * [ItemConfig] に関するテスト
 *
 * @see showcase.ItemConfig
 */
class ItemConfigTest {
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `itemStack can be get`() {
        val plugin = KtSpigotTest.getPlugin()
        val itemConfig = ItemConfig(plugin)
        val expectedMaterial = Material.STONE
        val expectedAmount = (1..64).random()
        val expectedDisplay = randomString()
        val expectedLore = List(5) { randomString() }
        itemConfig.writeText(
            buildString {
                appendLine("material: $expectedMaterial")
                appendLine("amount: $expectedAmount")
                appendLine("display: $expectedDisplay")
                appendLine("lore:")
                expectedLore.forEach {
                    appendLine(" - $it")
                }
            }
        )
        itemConfig.itemStack.run {
            assertNotNull(this)
            assertEquals(expectedMaterial, type)
            assertEquals(expectedAmount, amount)
            val meta = itemMeta
            assertNotNull(meta)
            assertEquals(expectedDisplay, meta.displayName)
            assertEquals(expectedLore, meta.lore)
        }
    }
}
