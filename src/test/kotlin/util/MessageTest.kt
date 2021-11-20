package util

import be.seeseemelk.mockbukkit.MockBukkit
import dev.s7a.spigot.util.sendActionBarMessage
import dev.s7a.spigot.util.sendChatMessage
import dev.s7a.spigot.util.sendTitleMessage
import org.bukkit.ChatColor
import randomString
import kotlin.test.Ignore
import kotlin.test.Test

/**
 * メッセージに関するテスト
 *
 * @see dev.s7a.spigot.util.sendChatMessage
 * @see dev.s7a.spigot.util.sendActionBarMessage
 * @see dev.s7a.spigot.util.sendTitleMessage
 */
class MessageTest {
    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @Test
    fun `chat message can be received`() {
        val player = server.addPlayer()
        val color = ChatColor.values().random()
        val string = randomString()
        player.assertNoMoreSaid()
        player.sendChatMessage("&${color.char}$string")
        player.assertSaid("$color$string")
        player.assertNoMoreSaid()
    }

    @Test
    fun `action bar message can be received`() {
        val player = server.addPlayer()
        val color = ChatColor.values().random()
        val string = randomString()
        player.assertNoMoreSaid()
        player.sendActionBarMessage("&${color.char}$string")
        player.assertSaid("$color$string")
        player.assertNoMoreSaid()
    }

    @Ignore // UnimplementedOperationException by MockBukkit
    @Test
    fun `title message can be received`() {
        val player = server.addPlayer()
        val color = ChatColor.values().random()
        val string = randomString()
        player.sendTitleMessage("&${color.char}$string")
    }

    @Test
    fun `component message using line can be received`() {
        val expected = """
            1
            23
            4
            5
            
        """.trimIndent()
        val player = server.addPlayer()
        player.sendChatMessage {
            line {
                append("1")
                lineBreak()
                line {
                    append("2")
                    append("3")
                }
                append("4")
            }
            line {
                append("5")
                lineBreak()
            }
        }
        expected.lines().forEach {
            player.assertSaid(it)
        }
        player.assertNoMoreSaid()
    }
}
