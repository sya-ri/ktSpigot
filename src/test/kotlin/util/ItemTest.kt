package util

import be.seeseemelk.mockbukkit.MockBukkit
import dev.s7a.spigot.item.customModelDataOrNull
import dev.s7a.spigot.item.displayNameOrNull
import dev.s7a.spigot.item.editItemMeta
import dev.s7a.spigot.item.itemStack
import dev.s7a.spigot.item.localizedNameOrNull
import dev.s7a.spigot.item.loreOrNull
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import randomString
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * アイテムに関するテスト
 *
 * @see dev.s7a.spigot.util.editItemMeta
 */
class ItemTest {
    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @Test
    fun `item meta can be changed`() {
        val expected = randomString()
        val itemStack = ItemStack(Material.STONE)
        assertFalse(itemStack.hasItemMeta())
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertFalse(meta.hasDisplayName())
        itemStack.editItemMeta {
            setDisplayName(expected)
        }.run(::assertTrue)
        val newMeta = itemStack.itemMeta
        assertNotNull(newMeta)
        assertNotEquals(meta, newMeta)
        assertEquals(expected, newMeta.displayName)
    }

    @Test
    fun `specific item meta can be changed`() {
        val defaultColor = Bukkit.getItemFactory().defaultLeatherColor
        val expected = Color.fromBGR((0..0xFFFFFF).random())
        val itemStack = ItemStack(Material.LEATHER_BOOTS)
        assertFalse(itemStack.hasItemMeta())
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertIs<LeatherArmorMeta>(meta)
        assertEquals(defaultColor, meta.color)
        itemStack.editItemMeta<LeatherArmorMeta> {
            setColor(expected)
        }.run(::assertTrue)
        val newMeta = itemStack.itemMeta
        assertNotNull(newMeta)
        assertIs<LeatherArmorMeta>(newMeta)
        assertNotEquals(meta, newMeta)
        assertEquals(expected, newMeta.color)
    }

    @Test
    fun `mismatch item meta is not changed`() {
        val itemStack = ItemStack(Material.STONE)
        assertFalse(itemStack.hasItemMeta())
        val executed = AtomicBoolean(false)
        itemStack.editItemMeta<SkullMeta> {
            executed.set(true)
        }.run(::assertFalse)
        assertFalse(itemStack.hasItemMeta())
        assertFalse(executed.get())
    }

    @Test
    fun `displayName can be get`() {
        val expected = randomString()
        val itemStack = ItemStack(Material.STONE)
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertNull(meta.displayNameOrNull)
        meta.setDisplayName(expected)
        assertEquals(expected, meta.displayNameOrNull)
    }

    @Test
    fun `lore can be get`() {
        val expected = List(5) { randomString() }
        val itemStack = ItemStack(Material.STONE)
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertNull(meta.loreOrNull)
        meta.lore = expected
        assertEquals(expected, meta.loreOrNull)
    }

    @Ignore // UnimplementedOperationException by MockBukkit
    @Test
    fun `localizedName can be get`() {
        val expected = randomString()
        val itemStack = ItemStack(Material.STONE)
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertNull(meta.localizedNameOrNull)
        meta.setLocalizedName(expected)
        assertEquals(expected, meta.localizedNameOrNull)
    }

    @Test
    fun `customModelData can be get`() {
        val expected = Random.nextUInt().toInt()
        val itemStack = ItemStack(Material.STONE)
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertNull(meta.customModelDataOrNull)
        meta.setCustomModelData(expected)
        assertEquals(expected, meta.customModelDataOrNull)
    }

    @Test
    fun `itemStack8 can be get`() {
        val displayNameColor = ChatColor.values().random()
        val displayName = randomString()
        val loreColor = ChatColor.values().random()
        val lore = List(5) { randomString() }
        val itemStack = itemStack(
            Material.STONE,
            displayName = "&${displayNameColor.char}$displayName",
            lore = lore.map { "&${loreColor.char}$it" },
        )
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertEquals("$displayNameColor$displayName", meta.displayName)
        assertEquals(lore.map { "$loreColor$it" }, meta.lore)
    }

    @Test
    fun `itemStack16 can be get`() {
        val displayNameColor = ChatColor.values().random()
        val displayName = randomString()
        val loreColor = ChatColor.values().random()
        val lore = List(5) { randomString() }
        val customModelData = Random.nextInt()
        val itemStack = itemStack(
            Material.STONE,
            displayName = "&${displayNameColor.char}$displayName",
            lore = lore.map { "&${loreColor.char}$it" },
            customModelData = customModelData
        )
        val meta = itemStack.itemMeta
        assertNotNull(meta)
        assertEquals("$displayNameColor$displayName", meta.displayName)
        assertEquals(lore.map { "$loreColor$it" }, meta.lore)
        assertEquals(customModelData, meta.customModelData)
    }
}
