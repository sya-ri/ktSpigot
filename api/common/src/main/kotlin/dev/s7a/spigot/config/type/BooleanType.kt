package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigBase
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getListUnsafe
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.setUnsafe

/**
 * [Boolean] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.booleanValue
 * @since 1.0.0
 */
object BooleanType : KtConfigValueType.Listable<Boolean> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<Boolean> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfigBase, path: String, value: Boolean?) {
        config.setUnsafe(path, value)
    }

    override val list = object : KtConfigValueType<List<Boolean>> {
        override fun get(config: KtConfigBase, path: String): KtConfigResult<List<Boolean>> {
            return config.getListUnsafe(path)
        }

        override fun set(config: KtConfigBase, path: String, value: List<Boolean>?) {
            config.setUnsafe(path, value)
        }
    }
}
