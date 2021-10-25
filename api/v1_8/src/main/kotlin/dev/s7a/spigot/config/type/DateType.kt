package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.setUnsafe
import java.util.Date

/**
 * [Date] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.dateValue
 * @since 1.0.0
 */
object DateType : KtConfigValueType<Date> {
    override fun get(config: KtConfig, path: String): KtConfigResult<Date> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfig, path: String, value: Date?) {
        config.setUnsafe(path, value)
    }
}
