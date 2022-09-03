package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.entity.SpecificVirtualEntity
import org.bukkit.entity.Entity

/**
 * [Entity] のコンフィグデータ型
 *
 * @see entityValue
 * @since 1.0.0
 */
class SpecificEntityType<T : Entity>(private val clazz: Class<T>) : KtConfigValueType.Listable<SpecificVirtualEntity<T>> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<SpecificVirtualEntity<T>> {
        return config.uuidValue(path).get().map { KtConfigResult.Success(SpecificVirtualEntity(clazz, it)) }
    }

    override fun set(config: KtConfigBase, path: String, value: SpecificVirtualEntity<T>?) {
        config.uuidValue(path).set(value?.uniqueId)
    }

    override fun list(force: Boolean): KtConfigValueType<List<SpecificVirtualEntity<T>>> {
        return object : KtConfigValueType<List<SpecificVirtualEntity<T>>> {
            override fun get(config: KtConfigBase, path: String): KtConfigResult<List<SpecificVirtualEntity<T>>> {
                return config.uuidValue(path).list(force).get().map { list ->
                    KtConfigResult.Success(list.map { SpecificVirtualEntity(clazz, it) })
                }
            }

            override fun set(config: KtConfigBase, path: String, value: List<SpecificVirtualEntity<T>>?) {
                config.uuidValue(path).list(force).set(value?.map { it.uniqueId })
            }
        }
    }
}
