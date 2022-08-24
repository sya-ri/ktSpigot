package location

import dev.s7a.ktspigot.KtSpigotTest
import dev.s7a.ktspigot.location.BlockLocation
import dev.s7a.ktspigot.location.BlockLocation.Companion.toBlockLocation
import randomLocation
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BlockLocationTest {
    @BeforeTest
    fun before() {
        KtSpigotTest.mock()
    }

    @AfterTest
    fun after() {
        KtSpigotTest.unmock()
    }

    @Test
    fun `blockLocation can be get from location`() {
        val world = KtSpigotTest.addWorld("test")
        val location = randomLocation(world)
        val blockLocation = location.toBlockLocation()
        assertEquals(location.world, blockLocation.world)
        assertEquals(location.blockX, blockLocation.x)
        assertEquals(location.blockY, blockLocation.y)
        assertEquals(location.blockZ, blockLocation.z)
    }

    @Test
    fun `blockLocation can be add`() {
        val location = BlockLocation(null, 1, 2, 3)
        location.add(2, 4, 6).run {
            assertEquals(3, x)
            assertEquals(6, y)
            assertEquals(9, z)
        }
        assertEquals(3, location.x)
        assertEquals(6, location.y)
        assertEquals(9, location.z)
    }

    @Test
    fun `blockLocation can be add location`() {
        val location = BlockLocation(null, 1, 2, 3)
        location.add(BlockLocation(null, 2, 4, 6)).run {
            assertEquals(3, x)
            assertEquals(6, y)
            assertEquals(9, z)
        }
        assertEquals(3, location.x)
        assertEquals(6, location.y)
        assertEquals(9, location.z)
    }

    @Test
    fun `blockLocation can be subtract`() {
        val location = BlockLocation(null, 10, 20, 30)
        location.subtract(2, 4, 6).run {
            assertEquals(8, x)
            assertEquals(16, y)
            assertEquals(24, z)
        }
        assertEquals(8, location.x)
        assertEquals(16, location.y)
        assertEquals(24, location.z)
    }

    @Test
    fun `blockLocation can be subtract location`() {
        val location = BlockLocation(null, 10, 20, 30)
        location.subtract(BlockLocation(null, 2, 4, 6)).run {
            assertEquals(8, x)
            assertEquals(16, y)
            assertEquals(24, z)
        }
        assertEquals(8, location.x)
        assertEquals(16, location.y)
        assertEquals(24, location.z)
    }

    @Test
    fun `blockLocation can be multiply`() {
        val location = BlockLocation(null, 1, 2, 3)
        location.multiply(3).run {
            assertEquals(3, x)
            assertEquals(6, y)
            assertEquals(9, z)
        }
        assertEquals(3, location.x)
        assertEquals(6, location.y)
        assertEquals(9, location.z)
    }

    @Test
    fun `blockLocation can be zero`() {
        val location = BlockLocation(null, 1, 2, 3)
        location.zero().run {
            assertEquals(0, x)
            assertEquals(0, y)
            assertEquals(0, z)
        }
        assertEquals(0, location.x)
        assertEquals(0, location.y)
        assertEquals(0, location.z)
    }

    @Test
    fun `blockLocation can be get length`() {
        val blockLocation = BlockLocation(null, 1, 2, 3)
        val location = blockLocation.toLocation()
        assertEquals(location.length(), blockLocation.length())
        assertEquals(location.lengthSquared(), blockLocation.lengthSquared())
    }

    @Test
    fun `blockLocation can be get distance`() {
        val world = KtSpigotTest.addWorld("test")
        val blockLocation1 = BlockLocation(world, 1, 2, 3)
        val location1 = blockLocation1.toLocation()
        val blockLocation2 = BlockLocation(world, 5, 6, 7)
        val location2 = blockLocation2.toLocation()
        assertEquals(location1.distance(location2), blockLocation1.distance(blockLocation2))
        assertEquals(location1.distanceSquared(location2), blockLocation1.distanceSquared(blockLocation2))
    }

    @Test
    fun `blockLocation can be get block`() {
        val world = KtSpigotTest.addWorld("test")
        val location = BlockLocation(world, 1, 2, 3)
        assertNotNull(location.block)
    }
}
