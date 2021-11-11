package util

import dev.s7a.spigot.util.color
import dev.s7a.spigot.util.uncolor
import org.bukkit.ChatColor
import randomString
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

/**
 * 文字列に関するテスト
 *
 * @see dev.s7a.spigot.util.color
 * @see dev.s7a.spigot.util.uncolor
 */
class StringTest {
    @Test
    fun `color code can be replaced`() {
        val color = ChatColor.values().random()
        val string = randomString()
        "&${color.char}$string".color().run {
            assertContains(this, ChatColor.COLOR_CHAR)
            assertEquals("$color$string", this)
        }
    }

    @Test
    fun `altColorChar can be changed`() {
        val color = ChatColor.values().random()
        val string = randomString()
        "#${color.char}$string".color('#').run {
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
            assertEquals(string, uncolor())
        }
    }

    @Test
    fun `do not convert if altColorChar is null`() {
        val color = ChatColor.values().random()
        val string = randomString()
        "&${color.char}$string".run {
            assertEquals(this, color(null))
        }
    }
}
