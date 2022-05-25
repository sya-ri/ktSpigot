package dev.s7a.ktspigot.persistence.type

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import java.nio.ByteBuffer
import java.util.UUID

/**
 * [UUID] の [PersistentDataType]
 *
 * @since 1.0.0
 */
object UUIDPersistentDataType : PersistentDataType<ByteArray, UUID> {
    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<UUID> {
        return UUID::class.java
    }

    override fun toPrimitive(complex: UUID, context: PersistentDataAdapterContext): ByteArray {
        return ByteBuffer.wrap(ByteArray(16)).apply {
            putLong(complex.mostSignificantBits)
            putLong(complex.leastSignificantBits)
        }.array()
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): UUID {
        return ByteBuffer.wrap(primitive).run {
            UUID(long, long)
        }
    }
}
