package util

import dev.s7a.spigot.KtSpigotTest
import dev.s7a.spigot.util.sendActionBarMessage
import dev.s7a.spigot.util.sendChatMessage
import dev.s7a.spigot.util.sendTitleMessage
import org.bukkit.ChatColor
import randomString
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * メッセージに関するテスト
 *
 * @see dev.s7a.spigot.util.sendChatMessage
 * @see dev.s7a.spigot.util.sendActionBarMessage
 * @see dev.s7a.spigot.util.sendTitleMessage
 */
class MessageTest {
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `chat message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = ChatColor.values().random()
        val string = randomString()
        player.assertMessageIsEmpty()
        player.sendChatMessage("&${color.char}$string")
        player.assertMessage("$color$string")
        player.assertMessageIsEmpty()
    }

    @Test
    fun `action bar message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = ChatColor.values().random()
        val string = randomString()
        player.assertActionBarIsEmpty()
        player.sendActionBarMessage("&${color.char}$string")
        player.assertActionBar("$color$string")
    }

    @Test
    fun `title message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = ChatColor.values().random()
        val string = randomString()
        player.assertTitleIsEmpty()
        player.sendTitleMessage("&${color.char}$string",)
        player.assertTitle("$color$string")
    }

    @Test
    fun `component message using line can be received`() {
        val expected = """
            §a1
            §f2§f3
            §b4
            §c5§d6§e7
            
        """.trimIndent()
        val player = KtSpigotTest.addPlayer()
        player.sendChatMessage {
            line {
                append("&a1")
                lineBreak()
                line {
                    append("2")
                    append("3")
                }
                append("4", ChatColor.AQUA.asBungee())
            }
            line {
                append("&c5&d6&e7")
                lineBreak()
            }
        }
        expected.lines().forEach {
            player.assertMessage(it)
        }
        player.assertMessageIsEmpty()
    }
}
