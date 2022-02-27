package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.getListUnsafe
import dev.s7a.ktspigot.config.getUnsafe
import dev.s7a.ktspigot.config.setUnsafe
import java.util.Date

/**
 * [Date] のコンフィグデータ型
 *
 * @see dev.s7a.ktspigot.config.dateValue
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
