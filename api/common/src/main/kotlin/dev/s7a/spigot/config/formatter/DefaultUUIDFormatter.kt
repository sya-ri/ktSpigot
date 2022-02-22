package dev.s7a.spigot.config.formatter

import dev.s7a.spigot.config.KtConfigFormatter
import dev.s7a.spigot.util.uuidOrNull
import java.util.UUID

/**
 * デフォルトとして使用する [UUID] のコンフィグフォーマッタ
 *
 * @see dev.s7a.spigot.config.uuidValue
 * @since 1.0.0
 */
object DefaultUUIDFormatter : KtConfigFormatter<UUID> {
    override val name: String = this::class.java.simpleName

    override fun string(value: UUID): String {
        return value.toString()
    }

    override fun value(string: String): UUID? {
        return uuidOrNull(string)
    }
}
