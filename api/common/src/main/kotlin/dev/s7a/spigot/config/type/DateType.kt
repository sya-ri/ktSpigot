package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigBase
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getListUnsafe
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.setUnsafe
import java.util.Date

/**
 * [Date] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.dateValue
 * @since 1.0.0
 */
object DateType : KtConfigValueType.Listable<Date> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<Date> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfigBase, path: String, value: Date?) {
        config.setUnsafe(path, value)
    }

    override val list = object : KtConfigValueType<List<Date>> {
        override fun get(config: KtConfigBase, path: String): KtConfigResult<List<Date>> {
            return config.getListUnsafe(path)
        }

        override fun set(config: KtConfigBase, path: String, value: List<Date>?) {
            config.setUnsafe(path, value)
        }
    }
}
