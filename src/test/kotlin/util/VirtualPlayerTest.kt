package util

import be.seeseemelk.mockbukkit.MockBukkit
import dev.s7a.spigot.player.VirtualPlayer.Companion.toVirtual
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * VirtualPlayerのテスト
 *
 * @see dev.s7a.spigot.util.VirtualPlayer
 */
class VirtualPlayerTest {
    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @Test
    fun `player can be get`() {
        val player = server.addPlayer()
        val virtualPlayer = player.toVirtual()
        assertEquals(player, virtualPlayer.player)
        assertEquals(player, virtualPlayer.offlinePlayer)
    }

    @Test
    fun `VirtualPlayer#toString is UUID#toString`() {
        val player = server.addPlayer()
        val virtualPlayer = player.toVirtual()
        assertEquals("${player.uniqueId}", virtualPlayer.toString())
    }
}
