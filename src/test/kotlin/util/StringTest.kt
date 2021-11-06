package util

import dev.s7a.spigot.util.toColor
import dev.s7a.spigot.util.toUncolor
import org.bukkit.ChatColor
import randomString
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

/**
 * 文字列に関するテスト
 *
 * @see dev.s7a.spigot.util.toColor
 * @see dev.s7a.spigot.util.toUncolor
 */
class StringTest {
    @Test
    fun `color code can be replaced`() {
        val color = ChatColor.values().random()
        val string = randomString()
        "&${color.char}$string".toColor().run {
            assertContains(this, ChatColor.COLOR_CHAR)
            assertEquals("$color$string", this)
        }
    }

    @Test
    fun `altColorChar can be changed`() {
        val color = ChatColor.values().random()
        val string = randomString()
        "#${color.char}$string".toColor('#').run {
            assertContains(this, ChatColor.COLOR_CHAR)
            assertEquals("$color$string", this)
        }
    }

    @Test
    fun `color code can be removed`() {
        val color = ChatColor.values().random()
        val string = randomString()
        "$color$string".run {
            assertContains(this, ChatColor.COLOR_CHAR)
            assertEquals(string, toUncolor())
        }
    }
}
