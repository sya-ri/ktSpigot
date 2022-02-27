package dev.s7a.ktspigot.config.formatter

import dev.s7a.ktspigot.config.KtConfigFormatter
import dev.s7a.ktspigot.util.uuidOrNull
import java.util.UUID

/**
 * デフォルトとして使用する [UUID] のコンフィグフォーマッタ
 *
 * @see dev.s7a.ktspigot.config.uuidValue
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
