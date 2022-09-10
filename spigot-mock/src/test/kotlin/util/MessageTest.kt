package util

import dev.s7a.ktspigot.KtSpigotTest
import dev.s7a.ktspigot.message.sendActionBarMessage
import dev.s7a.ktspigot.message.sendChatMessage
import dev.s7a.ktspigot.message.sendTitleMessage
import org.bukkit.ChatColor
import randomChatColor
import randomFormatChatColor
import randomString
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * メッセージに関するテスト
 *
 * @see dev.s7a.ktspigot.message.sendChatMessage
 * @see dev.s7a.ktspigot.message.sendActionBarMessage
 * @see dev.s7a.ktspigot.message.sendTitleMessage
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
        val color = randomChatColor()
        val string = randomString()
        player.assertMessageIsEmpty()
        player.sendChatMessage("&${color.char}$string")
        player.assertMessage("$color$string")
        player.assertMessageIsEmpty()
    }

    @Test
    fun `formatted chat message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = randomChatColor()
        val format = randomFormatChatColor()
        val string = randomString()
        player.assertMessageIsEmpty()
        player.sendChatMessage("&${color.char}&${format.char}$string")
        player.assertMessage("$color$format$string")
        player.assertMessageIsEmpty()
    }

    @Test
    fun `empty chat message can be received`() {
        val player = KtSpigotTest.addPlayer()
        player.assertMessageIsEmpty()
        player.sendChatMessage("")
        player.assertMessage("")
        player.assertMessageIsEmpty()
    }

    @Test
    fun `colored empty chat message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = randomChatColor()
        player.assertMessageIsEmpty()
        player.sendChatMessage("&${color.char}")
        player.assertMessage("$color")
        player.assertMessageIsEmpty()
    }

    @Test
    fun `action bar message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = randomChatColor()
        val string = randomString()
        player.assertActionBarIsEmpty()
        player.sendActionBarMessage("&${color.char}$string")
        player.assertActionBar("$color$string")
    }

    @Test
    fun `formatted action bar message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = randomChatColor()
        val format = randomFormatChatColor()
        val string = randomString()
        player.assertActionBarIsEmpty()
        player.sendActionBarMessage("&${color.char}&${format.char}$string")
        player.assertActionBar("$color$format$string")
    }

    @Test
    fun `empty action bar message can be received`() {
        val player = KtSpigotTest.addPlayer()
        player.assertActionBarIsEmpty()
        player.sendActionBarMessage("")
        player.assertActionBar("")
    }

    @Test
    fun `colored empty action bar message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = randomChatColor()
        player.assertActionBarIsEmpty()
        player.sendActionBarMessage("&${color.char}")
        player.assertActionBar("$color")
    }

    @Test
    fun `title message can be received`() {
        val player = KtSpigotTest.addPlayer()
        val color = randomChatColor()
        val string = randomString()
        player.assertTitleIsEmpty()
        player.sendTitleMessage("&${color.char}$string")
        player.assertTitle("$color$string")
    }

    @Test
    fun `component message using line can be received`() {
        val expected = """
            §a1§f2§f§k3§b4
            §f5§d6§e7
        """.trimIndent()
        val player = KtSpigotTest.addPlayer()
        player.sendChatMessage {
            append("&a1")
            append("2")
            append("&k3")
            append("4\n", ChatColor.AQUA.asBungee())
            append("5&d6&e7")
        }
        expected.lines().forEach {
            player.assertMessage(it)
        }
        player.assertMessageIsEmpty()
    }
}
