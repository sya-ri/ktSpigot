package util

import dev.s7a.spigot.util.uuid
import dev.s7a.spigot.util.uuidOrNull
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNull

/**
 * UUID に関するテスト
 *
 * @see dev.s7a.spigot.util.uuid
 * @see dev.s7a.spigot.util.uuidOrNull
 */
class UUIDTest {
    @Test
    fun `uuid can be get`() {
        val expected = UUID.randomUUID()
        assertEquals(expected, uuid("$expected"))
    }

    @Test
    fun `exception for illegal uuid`() {
        assertFails {
            uuid("")
        }
    }

    @Test
    fun `illegal uuid is null`() {
        assertNull(uuidOrNull(""))
    }
}
