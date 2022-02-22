package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigBase
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getValues
import dev.s7a.spigot.config.setUnsafe

/**
 * KtConfigSection のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.section
 * @since 1.0.0
 */
class SectionType<T : KtConfigSection>(private val clazz: Class<T>) : KtConfigValueType<T> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<T> {
        val constructor = try {
            clazz.getDeclaredConstructor(KtConfigBase::class.java, String::class.java)
        } catch (ex: NoSuchMethodException) {
            return KtConfigResult.Failure(KtConfigError.Reflection.ThrowNoSuchMethodException(config, "constructor(KtConfig, String) を定義してください"))
        }
        constructor.isAccessible = true
        return KtConfigResult.Success(constructor.newInstance(config, path))
    }

    override fun set(config: KtConfigBase, path: String, value: T?) {
        value?.let { section ->
            section.getValues().forEach {
                it.setUnsafe(it.getValue())
            }
        } ?: run {
            config.setUnsafe(path, null)
        }
    }
}
