package location

import dev.s7a.ktspigot.location.component1
import dev.s7a.ktspigot.location.component2
import dev.s7a.ktspigot.location.component3
import dev.s7a.ktspigot.location.component4
import dev.s7a.ktspigot.location.component5
import dev.s7a.ktspigot.location.component6
import org.bukkit.Location
import randomLocation
import kotlin.test.Test
import kotlin.test.assertEquals

class DestructureTest {
    @Test
    fun `location destructure`() {
        val location = randomLocation(null)
        val (world, x, y, z, yaw, pitch) = location
        assertEquals(location, Location(world, x, y, z, yaw, pitch))
    }
}
