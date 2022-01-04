package command

import dev.s7a.spigot.KtSpigotTest
import dev.s7a.spigot.command.KtCommandCancelException
import dev.s7a.spigot.command.KtCommandTabCompleterType
import dev.s7a.spigot.command.ktCommand
import randomString
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * コマンドのテスト
 *
 * @see dev.s7a.spigot.command
 */
class CommandTest {
    @Test
    fun `command can be created`() {
        val commandName = randomString()
        val plugin = KtSpigotTest.plugin
        plugin.addCommand(commandName)
        val executed = AtomicBoolean(false)
        plugin.ktCommand(commandName) {
            execute {
                executed.set(true)
            }
        }
        val player = KtSpigotTest.addPlayer()
        assertTrue(player.performCommand(commandName))
        assertTrue(executed.get())
    }

    @Test
    fun `command can be completed`() {
        val commandName = randomString()
        val plugin = KtSpigotTest.plugin
        plugin.addCommand(commandName)
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
        val player = KtSpigotTest.addPlayer()
        tabCompleteAssert(player, command, commandName) {
            assert(listOf("a", "abc", "b", "bb", "d", "g", "j", "o"), arrayOf(""))
            assert(listOf("a", "abc"), arrayOf("a"))
            assert(listOf("abc"), arrayOf("ab"))
            assert(listOf("abc"), arrayOf("abc"))
            assert(listOf(), arrayOf("abcd"))
            assert(listOf(), arrayOf("a", ""))
            assert(listOf("c", "cc"), arrayOf("b", ""))
            assert(listOf("c", "cc"), arrayOf("b", "c"))
            assert(listOf("e", "f"), arrayOf("d", ""))
            assert(listOf("f"), arrayOf("d", "e", ""))
            assert(listOf("e"), arrayOf("d", "f", ""))
            assert(listOf(), arrayOf("d", "e", "f", ""))
            assert(listOf(), arrayOf("d", "f", "e", ""))
            assert(listOf("h", "i"), arrayOf("g", ""))
            assert(listOf("h", "i"), arrayOf("g", "h", ""))
            assert(listOf("h", "i"), arrayOf("g", "i", ""))
            assert(listOf("h", "i"), arrayOf("g", "h", "h", ""))
            assert(listOf("k", "l"), arrayOf("j", ""))
            assert(listOf("k"), arrayOf("j", "k"))
            assert(listOf("l"), arrayOf("j", "l"))
            assert(listOf("m", "n"), arrayOf("j", "k", ""))
            assert(listOf("m", "n"), arrayOf("j", "l", ""))
            assert(listOf("m"), arrayOf("j", "k", "m"))
            assert(listOf(), arrayOf("j", "k", "m", ""))
            assert(listOf("abc", "ABC", "あいうえお"), arrayOf("o", ""))
            assert(listOf("abc", "ABC"), arrayOf("o", "a"))
            assert(listOf("abc", "ABC"), arrayOf("o", "A"))
            assert(listOf("あいうえお"), arrayOf("o", "あ"))
            assert(listOf("あいうえお"), arrayOf("o", "いう"))
            assert(listOf("p"), arrayOf("null", ""))
            assert(listOf("p"), arrayOf("q", ""))
        }
    }

    @Test
    fun `command setting can be overwrote`() {
        val commandName = randomString()
        val plugin = KtSpigotTest.plugin
        plugin.addCommand(commandName)
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
        val player = KtSpigotTest.addPlayer()
        val command = plugin.getCommand(commandName)
        assertNotNull(command)
        tabCompleteAssert(player, command, commandName) {
            assert(listOf("before"), arrayOf(""))
        }
        assertTrue(player.performCommand(commandName))
        assertEquals(1, executeCount1.get())
        assertEquals(0, executeCount2.get())
        tabCompleteAssert(player, command, commandName) {
            assert(listOf("after"), arrayOf(""))
        }
        assertTrue(player.performCommand(commandName))
        assertEquals(1, executeCount1.get())
        assertEquals(1, executeCount2.get())
    }

    @Test
    fun `command can be cancelled`() {
        val commandName = randomString()
        val plugin = KtSpigotTest.plugin
        plugin.addCommand(commandName)
        plugin.ktCommand(commandName) {
            execute {
                throw KtCommandCancelException()
            }
        }
        val player = KtSpigotTest.addPlayer()
        val command = plugin.getCommand(commandName)
        assertNotNull(command)
        assertFalse(player.performCommand("/$commandName"))
    }
}
