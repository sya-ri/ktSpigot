package command

import org.bukkit.command.Command
import org.bukkit.entity.Player
import kotlin.test.assertEquals

/**
 * タブ補完のアサート
 */
class TabCompleteAsserter(private val player: Player, private val command: Command, private val commandName: String) {
    private fun tabComplete(args: Array<out String>) = command.tabComplete(player, commandName, args)

    /**
     * 引数によって期待される候補が取得できるかのアサート
     */
    fun assert(expected: List<String>, args: Array<out String>) {
        assertEquals(expected, tabComplete(args))
    }
}

/**
 * タブ補完のアサート行う
 */
fun tabCompleteAssert(player: Player, command: Command, commandName: String, block: TabCompleteAsserter.() -> Unit) {
    TabCompleteAsserter(player, command, commandName).run(block)
}
