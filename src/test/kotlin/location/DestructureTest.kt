package location

import dev.s7a.ktspigot.location.component1
import dev.s7a.ktspigot.location.component2
import dev.s7a.ktspigot.location.component3
import dev.s7a.ktspigot.location.component4
import randomLocation
import kotlin.test.Test
import kotlin.test.assertEquals

class DestructureTest {
    @Test
    fun `location destructure`() {
        val location = randomLocation(null)
        val (world, x, y, z) = location
        assertEquals(location.world, world)
        assertEquals(location.x, x)
        assertEquals(location.y, y)
        assertEquals(location.z, z)
    }
}
