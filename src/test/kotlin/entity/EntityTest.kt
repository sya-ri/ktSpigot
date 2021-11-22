package entity

import be.seeseemelk.mockbukkit.MockBukkit
import dev.s7a.spigot.entity.spawnEntity
import dev.s7a.spigot.util.itemStack
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Zombie
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertIs

/**
 * エンティティに関するテスト
 *
 * @see dev.s7a.spigot.entity
 */
class EntityTest {
    /**
     * モックサーバー
     */
    private val server = MockBukkit.getOrCreateMock()

    @Ignore // https://github.com/MockBukkit/MockBukkit/pull/269
    @Test
    fun `entity can be spawned`() {
        val world = server.addSimpleWorld("test")
        val location = world.spawnLocation
        val entity = world.spawnEntity<Zombie>(location)
        assertContains(world.entities, entity)
        assertIs<Zombie>(Bukkit.getEntity(entity.uniqueId))
    }

    @Ignore // https://github.com/MockBukkit/MockBukkit/pull/269
    @Test
    fun `entity can be spawned with armor`() {
        val world = server.addSimpleWorld("test")
        val location = world.spawnLocation
        val helmet = itemStack(Material.LEATHER_HELMET) // fail: https://github.com/MockBukkit/MockBukkit/pull/270
        val entity = world.spawnEntity<Zombie>(location) {
            equipment?.run {
                this.helmet = helmet
            }
        }
        assertContains(world.entities, entity)
        assertIs<Zombie>(Bukkit.getEntity(entity.uniqueId))
        assertEquals(helmet, entity.equipment?.helmet)
    }
}
