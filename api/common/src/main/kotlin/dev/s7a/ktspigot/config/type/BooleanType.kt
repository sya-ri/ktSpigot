package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.getListUnsafe
import dev.s7a.ktspigot.config.getUnsafe
import dev.s7a.ktspigot.config.setUnsafe

/**
 * [Boolean] のコンフィグデータ型
 *
 * @see booleanValue
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
