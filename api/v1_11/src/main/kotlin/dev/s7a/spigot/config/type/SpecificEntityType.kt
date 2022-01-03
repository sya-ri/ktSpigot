package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.entityValue
import dev.s7a.spigot.config.mapValues
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * [Entity] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.entityValue
 * @since 1.0.0
 */
class SpecificEntityType<T : Entity>(private val clazz: Class<T>) : KtConfigValueType.Listable<T> {
    /**
     * [Entity] の種類が一致しなかった時のエラー
     *
     * @since 1.0.0
     */
    class MismatchEntityTypeError(config: KtConfig, path: String, value: UUID, name: String) : KtConfigError(config) {
        override val message = "$path の値($value)に対応するエンティティは $name ではありません"
    }

    /**
     * [Entity] を [KtConfigResult]<[T]> に変換する
     *
     * @since 1.0.0
     */
    private fun entityToResult(config: KtConfig, path: String, value: Entity) = if (clazz.isInstance(value)) {
        KtConfigResult.Success(clazz.cast(value))
    } else {
        KtConfigResult.Failure(MismatchEntityTypeError(config, path, value.uniqueId, clazz.simpleName))
    }

    override fun get(config: KtConfig, path: String): KtConfigResult<T> {
        return config.entityValue(path).get().map { entityToResult(config, path, it) }
    }

    override fun set(config: KtConfig, path: String, value: T?) {
        config.entityValue(path).set(value)
    }

    override val list = object : KtConfigValueType<List<T>> {
        override fun get(config: KtConfig, path: String): KtConfigResult<List<T>> {
            return config.entityValue(path).list().get().mapValues(config, path) { index, value ->
                entityToResult(config, "$path#$index", value)
            }
        }

        override fun set(config: KtConfig, path: String, value: List<T>?) {
            config.entityValue(path).list().set(value)
        }
    }
}
