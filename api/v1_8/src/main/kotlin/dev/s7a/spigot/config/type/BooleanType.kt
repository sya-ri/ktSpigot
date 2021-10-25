package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.setUnsafe

/**
 * [Boolean] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.booleanValue
 * @since 1.0.0
 */
object BooleanType : KtConfigValueType<Boolean> {
    override fun get(config: KtConfig, path: String): KtConfigResult<Boolean> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfig, path: String, value: Boolean?) {
        config.setUnsafe(path, value)
    }
}
