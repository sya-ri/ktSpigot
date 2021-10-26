package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigFormatter
import java.util.UUID

/**
 * [UUID] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.uuidValue
 * @since 1.0.0
 */
class UUIDType(formatter: KtConfigFormatter<UUID>) : FormatterType<UUID>(formatter)
