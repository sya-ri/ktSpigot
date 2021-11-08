package command

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.MockPlugin
import dev.s7a.spigot.command.ktCommand
import randomString
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.io.path.createTempFile
import kotlin.io.path.writeText
import kotlin.test.Test
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
}
