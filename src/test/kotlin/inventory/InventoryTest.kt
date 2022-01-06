package inventory

import dev.s7a.spigot.KtSpigotTest
import dev.s7a.spigot.inventory.item
import dev.s7a.spigot.inventory.ktInventory
import org.bukkit.Material
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import randomString
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * インベントリに関するテスト
 *
 * @see dev.s7a.spigot.inventory
 */
class InventoryTest {
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `inventory can be opened`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val line = (1..6).random()
        plugin.ktInventory("", line) {
        }.open(player)
        val topInventory = player.openInventory.topInventory
        assertEquals(InventoryType.CHEST, topInventory.type)
        assertEquals(line * 9, topInventory.size)
    }

    @Test
    fun `ktInventory#item can be executed`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val executed = AtomicBoolean(false)
        plugin.ktInventory("", 1) {
            item(0, ItemStack(Material.STONE)) {
                executed.set(true)
            }
        }.open(player)
        player.assertInventoryView(InventoryType.CHEST)
        val inventoryView = player.openInventory
        inventoryView.topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.STONE, type)
        }
        val event = InventoryClickEvent(inventoryView, InventoryType.SlotType.CONTAINER, 0, ClickType.LEFT, InventoryAction.NOTHING)
        plugin.server.pluginManager.callEvent(event)
        assertTrue(executed.get())
    }

    @Test
    fun `item placement outside slots fails`() {
        val plugin = KtSpigotTest.getPlugin()
        plugin.ktInventory("", 1) {
            assertFails {
                item(-1, ItemStack(Material.STONE)) {
                }
            }
        }
    }

    @Test
    fun `ktInventory#onClick can be executed`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val executed = AtomicBoolean(false)
        plugin.ktInventory("", 1) {
            onClick {
                executed.set(true)
            }
        }.open(player)
        player.assertInventoryView(InventoryType.CHEST)
        val inventoryView = player.openInventory
        val event = InventoryClickEvent(inventoryView, InventoryType.SlotType.CONTAINER, 0, ClickType.LEFT, InventoryAction.NOTHING)
        plugin.server.pluginManager.callEvent(event)
        assertTrue(executed.get())
    }

    @Test
    fun `ktInventory#onClose can be executed`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val executed = AtomicBoolean(false)
        plugin.ktInventory("", 1) {
            onClose {
                executed.set(true)
            }
        }.open(player)
        player.assertInventoryView(InventoryType.CHEST)
        player.closeInventory()
        player.assertInventoryView(InventoryType.CRAFTING)
        assertTrue(executed.get())
    }

    @Test
    fun `click action can be overwrote`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val executeCount = AtomicInteger(0)
        val nestExecuteCount = AtomicInteger(0)
        plugin.ktInventory("", 1) {
            item(0, ItemStack(Material.STONE)) {
                executeCount.incrementAndGet()
                item(0, ItemStack(Material.GRASS_BLOCK)) {
                    nestExecuteCount.incrementAndGet()
                    item(0, ItemStack(Material.COBBLESTONE))
                }
            }
        }.open(player)
        player.assertInventoryView(InventoryType.CHEST)
        val inventoryView = player.openInventory
        val topInventory = inventoryView.topInventory
        // 初期状態は STONE
        topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.STONE, type)
        }
        val event = InventoryClickEvent(inventoryView, InventoryType.SlotType.CONTAINER, 0, ClickType.LEFT, InventoryAction.NOTHING)
        plugin.server.pluginManager.callEvent(event)
        // 1回クリックすると GRASS_BLOCK
        topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.GRASS_BLOCK, type)
        }
        // 外側のみカウントされるので 1, 0
        assertEquals(1, executeCount.get())
        assertEquals(0, nestExecuteCount.get())
        plugin.server.pluginManager.callEvent(event)
        // 2回クリックすると COBBLESTONE
        topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.COBBLESTONE, type)
        }
        // 内側のみカウントされるので 1, 1
        assertEquals(1, executeCount.get())
        assertEquals(1, nestExecuteCount.get())
        plugin.server.pluginManager.callEvent(event)
        // 3回以上クリックしても COBBLESTONE のまま
        topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.COBBLESTONE, type)
        }
        // 外側も内側も実行されないので 1, 1
        assertEquals(1, executeCount.get())
        assertEquals(1, nestExecuteCount.get())
    }

    @Test
    fun `item can be placed use itemStack8`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val amount = (1..64).random()
        val displayName = randomString()
        val lore = List(5) { randomString() }
        plugin.ktInventory("", 1) {
            item(0, Material.STONE, amount, displayName, lore)
        }.open(player)
        player.assertInventoryView(InventoryType.CHEST)
        val topInventory = player.openInventory.topInventory
        topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.STONE, type)
            assertEquals(amount, this.amount)
            val meta = itemMeta
            assertNotNull(meta)
            assertEquals(displayName, meta.displayName)
            assertEquals(lore, meta.lore)
        }
    }

    @Test
    fun `item can be placed use itemStack16`() {
        val plugin = KtSpigotTest.getPlugin()
        val player = KtSpigotTest.addPlayer()
        val amount = (1..64).random()
        val displayName = randomString()
        val lore = List(5) { randomString() }
        val customModelData = Random.nextUInt().toInt()
        plugin.ktInventory("", 1) {
            item(0, Material.STONE, amount, displayName, lore, customModelData)
        }.open(player)
        player.assertInventoryView(InventoryType.CHEST)
        val topInventory = player.openInventory.topInventory
        topInventory.getItem(0).run {
            assertNotNull(this)
            assertEquals(Material.STONE, type)
            assertEquals(amount, this.amount)
            val meta = itemMeta
            assertNotNull(meta)
            assertEquals(displayName, meta.displayName)
            assertEquals(lore, meta.lore)
            assertEquals(customModelData, meta.customModelData)
        }
    }
}
