package entity

import dev.s7a.spigot.KtSpigotTest
import dev.s7a.spigot.entity.spawnEntity
import dev.s7a.spigot.item.itemStack
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Zombie
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
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
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `entity can be spawned`() {
        val world = KtSpigotTest.addWorld("test")
        val location = world.spawnLocation
        val entity = world.spawnEntity<Zombie>(location)
        assertContains(world.entities, entity)
        assertIs<Zombie>(Bukkit.getEntity(entity.uniqueId))
    }

    @Test
    fun `entity can be spawned with armor`() {
        val world = KtSpigotTest.addWorld("test")
        val location = world.spawnLocation
        val helmet = itemStack(Material.LEATHER_HELMET)
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
