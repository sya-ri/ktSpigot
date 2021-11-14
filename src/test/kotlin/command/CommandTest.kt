package command

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.MockPlugin
import dev.s7a.spigot.command.KtCommandTabCompleterType
import dev.s7a.spigot.command.ktCommand
import randomString
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.createTempFile
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * コマンドのテスト
 *
 * @see dev.s7a.spigot.command
 */
class CommandTest {
    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @Test
    fun `command can be created`() {
        val commandName = randomString()
        val descriptionFile = createTempFile()
        descriptionFile.writeText(
            """
                name: MockPlugin
                version: 1.0.0
                main: ${MockPlugin::class.java.name}
                commands:
                  $commandName: {}
            """.trimIndent()
        )
        val plugin = MockBukkit.loadWith(MockPlugin::class.java, descriptionFile.toFile())
        val executed = AtomicBoolean(false)
        plugin.ktCommand(commandName) {
            execute {
                executed.set(true)
            }
        }
        val player = server.addPlayer()
        assertTrue(player.performCommand(commandName))
        assertTrue(executed.get())
    }

    @Test
    fun `command can be completed`() {
        val commandName = randomString()
        val descriptionFile = createTempFile()
        descriptionFile.writeText(
            """
                name: MockPlugin
                version: 1.0.0
                main: ${MockPlugin::class.java.name}
                commands:
                  $commandName: {}
            """.trimIndent()
        )
        val plugin = MockBukkit.loadWith(MockPlugin::class.java, descriptionFile.toFile())
        plugin.ktCommand(commandName) {
            tabComplete {
                literal("a", "abc")
                literal("b", "bb") {
                    literal("c", "cc")
                }
                literal("d") {
                    literal("e", "f", type = KtCommandTabCompleterType.NoDuplication)
                }
                literal("g") {
                    literal("h", "i", type = KtCommandTabCompleterType.Multiple)
                }
                literal("j") {
                    dynamic({ listOf("k", "l") }) {
                        dynamic({ listOf("m", "n") })
                    }
                }
                literal("o") {
                    literal("abc", "ABC", "あいうえお")
                }
                default {
                    literal("p")
                }
                assertFailsWith<IllegalStateException> {
                    literal("q") {
                        literal("r")
                    }
                }
            }
        }
        val command = plugin.getCommand(commandName)
        assertNotNull(command)
        val player = server.addPlayer()
        assertEquals(listOf("a", "abc", "b", "bb", "d", "g", "j", "o"), command.tabComplete(player, commandName, arrayOf("")))
        assertEquals(listOf("a", "abc"), command.tabComplete(player, commandName, arrayOf("a")))
        assertEquals(listOf("abc"), command.tabComplete(player, commandName, arrayOf("ab")))
        assertEquals(listOf("abc"), command.tabComplete(player, commandName, arrayOf("abc")))
        assertEquals(listOf(), command.tabComplete(player, commandName, arrayOf("abcd")))
        assertEquals(listOf(), command.tabComplete(player, commandName, arrayOf("a", "")))
        assertEquals(listOf("c", "cc"), command.tabComplete(player, commandName, arrayOf("b", "")))
        assertEquals(listOf("c", "cc"), command.tabComplete(player, commandName, arrayOf("b", "c")))
        assertEquals(listOf("e", "f"), command.tabComplete(player, commandName, arrayOf("d", "")))
        assertEquals(listOf("f"), command.tabComplete(player, commandName, arrayOf("d", "e", "")))
        assertEquals(listOf("e"), command.tabComplete(player, commandName, arrayOf("d", "f", "")))
        assertEquals(listOf(), command.tabComplete(player, commandName, arrayOf("d", "e", "f", "")))
        assertEquals(listOf(), command.tabComplete(player, commandName, arrayOf("d", "f", "e", "")))
        assertEquals(listOf("h", "i"), command.tabComplete(player, commandName, arrayOf("g", "")))
        assertEquals(listOf("h", "i"), command.tabComplete(player, commandName, arrayOf("g", "h", "")))
        assertEquals(listOf("h", "i"), command.tabComplete(player, commandName, arrayOf("g", "i", "")))
        assertEquals(listOf("h", "i"), command.tabComplete(player, commandName, arrayOf("g", "h", "h", "")))
        assertEquals(listOf("k", "l"), command.tabComplete(player, commandName, arrayOf("j", "")))
        assertEquals(listOf("k"), command.tabComplete(player, commandName, arrayOf("j", "k")))
        assertEquals(listOf("l"), command.tabComplete(player, commandName, arrayOf("j", "l")))
        assertEquals(listOf("m", "n"), command.tabComplete(player, commandName, arrayOf("j", "k", "")))
        assertEquals(listOf("m", "n"), command.tabComplete(player, commandName, arrayOf("j", "l", "")))
        assertEquals(listOf("m"), command.tabComplete(player, commandName, arrayOf("j", "k", "m")))
        assertEquals(listOf(), command.tabComplete(player, commandName, arrayOf("j", "k", "m", "")))
        assertEquals(listOf("abc", "ABC", "あいうえお"), command.tabComplete(player, commandName, arrayOf("o", "")))
        assertEquals(listOf("abc", "ABC"), command.tabComplete(player, commandName, arrayOf("o", "a")))
        assertEquals(listOf("abc", "ABC"), command.tabComplete(player, commandName, arrayOf("o", "A")))
        assertEquals(listOf("あいうえお"), command.tabComplete(player, commandName, arrayOf("o", "あ")))
        assertEquals(listOf("p"), command.tabComplete(player, commandName, arrayOf("null", "")))
        assertEquals(listOf("p"), command.tabComplete(player, commandName, arrayOf("q", "")))
    }

    @Test
    fun `command setting can be overwrote`() {
        val commandName = randomString()
        val descriptionFile = createTempFile()
        descriptionFile.writeText(
            """
                name: MockPlugin
                version: 1.0.0
                main: ${MockPlugin::class.java.name}
                commands:
                  $commandName: {}
            """.trimIndent()
        )
        val plugin = MockBukkit.loadWith(MockPlugin::class.java, descriptionFile.toFile())
        val executeCount1 = AtomicInteger(0)
        val executeCount2 = AtomicInteger(0)
        plugin.ktCommand(commandName) {
            execute {
                executeCount1.incrementAndGet()
                tabComplete {
                    literal("after")
                }
                execute {
                    executeCount2.incrementAndGet()
                }
            }
            tabComplete {
                literal("before")
            }
        }
        val player = server.addPlayer()
        val command = plugin.getCommand(commandName)
        assertNotNull(command)
        assertEquals(listOf("before"), command.tabComplete(player, commandName, arrayOf("")))
        assertTrue(player.performCommand(commandName))
        assertEquals(1, executeCount1.get())
        assertEquals(0, executeCount2.get())
        assertEquals(listOf("after"), command.tabComplete(player, commandName, arrayOf("")))
        assertTrue(player.performCommand(commandName))
        assertEquals(1, executeCount1.get())
        assertEquals(1, executeCount2.get())
    }
}
