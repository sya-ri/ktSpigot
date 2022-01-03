package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.mapValues
import dev.s7a.spigot.config.uuidValue
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * [Entity] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.entityValue
 * @since 1.0.0
 */
object EntityType : KtConfigValueType.Listable<Entity> {
    /**
     * [UUID] から [Entity] が見つからなかった時のエラー
     *
     * @since 1.0.0
     */
    class NotFoundEntityError(config: KtConfig, path: String, value: UUID) : KtConfigError(config) {
        override val message = "$path の値($value)に対応するエンティティが見つかりませんでした"
    }

    /**
     * [UUID] を [KtConfigResult]<[Entity]> に変換する
     *
     * @since 1.0.0
     */
    private fun uuidToResult(config: KtConfig, path: String, value: UUID) = Bukkit.getEntity(value)?.let {
        KtConfigResult.Success(it)
    } ?: KtConfigResult.Failure(NotFoundEntityError(config, path, value))

    /**
     * [Entity] を [UUID] に変換する
     *
     * @since 1.0.0
     */
    private fun valueToUuid(value: Entity) = value.uniqueId

    override fun get(config: KtConfig, path: String): KtConfigResult<Entity> {
        return config.uuidValue(path).get().map { uuidToResult(config, path, it) }
    }

    override fun set(config: KtConfig, path: String, value: Entity?) {
        config.uuidValue(path).set(value?.let(::valueToUuid))
    }

    override val list = object : KtConfigValueType<List<Entity>> {
        override fun get(config: KtConfig, path: String): KtConfigResult<List<Entity>> {
            return config.uuidValue(path).list().get().mapValues(config, path) { index, value ->
                uuidToResult(config, "$path#$index", value)
            }
        }

        override fun set(config: KtConfig, path: String, value: List<Entity>?) {
            config.uuidValue(path).list().set(value?.map(::valueToUuid))
        }
    }
}
