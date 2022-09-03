package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.entity.VirtualEntity
import org.bukkit.entity.Entity

/**
 * [Entity] のコンフィグデータ型
 *
 * @see entityValue
 * @since 1.0.0
 */
object EntityType : KtConfigValueType.Listable<VirtualEntity> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<VirtualEntity> {
        return config.uuidValue(path).get().map {
            KtConfigResult.Success(VirtualEntity(it))
        }
    }

    override fun set(config: KtConfigBase, path: String, value: VirtualEntity?) {
        config.uuidValue(path).set(value?.uniqueId)
    }

    override fun list(force: Boolean): KtConfigValueType<List<VirtualEntity>> {
        return object : KtConfigValueType<List<VirtualEntity>> {
            override fun get(config: KtConfigBase, path: String): KtConfigResult<List<VirtualEntity>> {
                return config.uuidValue(path).list(force).get().map {
                    KtConfigResult.Success(it.map(::VirtualEntity))
                }
            }

            override fun set(config: KtConfigBase, path: String, value: List<VirtualEntity>?) {
                config.uuidValue(path).list(force).set(value?.map(VirtualEntity::uniqueId))
            }
        }
    }
}
