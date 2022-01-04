package util

import dev.s7a.spigot.KtSpigotTest
import dev.s7a.spigot.player.VirtualPlayer.Companion.toVirtual
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * VirtualPlayerのテスト
 *
 * @see dev.s7a.spigot.util.VirtualPlayer
 */
class VirtualPlayerTest {
    @Test
    fun `player can be get`() {
        val player = KtSpigotTest.addPlayer()
        val virtualPlayer = player.toVirtual()
        assertEquals(player, virtualPlayer.player)
        assertEquals(player, virtualPlayer.offlinePlayer)
    }

    @Test
    fun `VirtualPlayer#toString is UUID#toString`() {
        val player = KtSpigotTest.addPlayer()
        val virtualPlayer = player.toVirtual()
        assertEquals("${player.uniqueId}", virtualPlayer.toString())
    }
}
