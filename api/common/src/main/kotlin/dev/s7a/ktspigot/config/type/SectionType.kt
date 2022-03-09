package dev.s7a.ktspigot.config.type

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigError
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigSection
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.getValues
import dev.s7a.ktspigot.config.setUnsafe

/**
 * KtConfigSection のコンフィグデータ型
 *
 * @see section
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
