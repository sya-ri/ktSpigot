package vector

import dev.s7a.ktspigot.vector.component1
import dev.s7a.ktspigot.vector.component2
import dev.s7a.ktspigot.vector.component3
import randomVector
import kotlin.test.Test
import kotlin.test.assertEquals

class DestructureTest {
    @Test
    fun `vector destructure`() {
        val vector = randomVector()
        val (x, y, z) = vector
        assertEquals(vector.x, x)
        assertEquals(vector.y, y)
        assertEquals(vector.z, z)
    }
}
