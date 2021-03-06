package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigError
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.mapValues
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * [Entity] のコンフィグデータ型
 *
 * @see entityValue
 * @since 1.0.0
 */
object EntityType : KtConfigValueType.Listable<Entity> {
    /**
     * [UUID] から [Entity] が見つからなかった時のエラー
     *
     * @since 1.0.0
     */
    class NotFoundEntityError(config: KtConfigBase, path: String, value: UUID) : KtConfigError(config) {
        override val message = "$path の値($value)に対応するエンティティが見つかりませんでした"
    }

    /**
     * [UUID] を [KtConfigResult]<[Entity]> に変換する
     *
     * @since 1.0.0
     */
    private fun uuidToResult(config: KtConfigBase, path: String, value: UUID) = Bukkit.getEntity(value)?.let {
        KtConfigResult.Success(it)
    } ?: KtConfigResult.Failure(NotFoundEntityError(config, path, value))

    /**
     * [Entity] を [UUID] に変換する
     *
     * @since 1.0.0
     */
    private fun valueToUuid(value: Entity) = value.uniqueId

    override fun get(config: KtConfigBase, path: String): KtConfigResult<Entity> {
        return config.uuidValue(path).get().map { uuidToResult(config, path, it) }
    }

    override fun set(config: KtConfigBase, path: String, value: Entity?) {
        config.uuidValue(path).set(value?.let(::valueToUuid))
    }

    override val list = object : KtConfigValueType<List<Entity>> {
        override fun get(config: KtConfigBase, path: String): KtConfigResult<List<Entity>> {
            return config.uuidValue(path).list().get().mapValues(config, path) { index, value ->
                uuidToResult(config, "$path#$index", value)
            }
        }

        override fun set(config: KtConfigBase, path: String, value: List<Entity>?) {
            config.uuidValue(path).list().set(value?.map(::valueToUuid))
        }
    }
}
