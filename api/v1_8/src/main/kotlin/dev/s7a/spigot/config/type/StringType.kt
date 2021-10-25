package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.setUnsafe

/**
 * [String] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.stringValue
 * @since 1.0.0
 */
object StringType : KtConfigValueType<String> {
    override fun get(config: KtConfig, path: String): KtConfigResult<String> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfig, path: String, value: String?) {
        config.setUnsafe(path, value)
    }
}
