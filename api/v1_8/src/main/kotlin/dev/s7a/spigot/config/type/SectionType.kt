package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getValues

/**
 * [Map]<[String], [KtConfigSection]> のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.section
 * @since 1.0.0
 */
class SectionType<T : KtConfigSection>(private val clazz: Class<T>) : KtConfigValueType<Map<String, T>> {
    override fun get(config: KtConfig, path: String): KtConfigResult<Map<String, T>> {
        val constructor = try {
            clazz.getDeclaredConstructor(KtConfig::class.java, String::class.java)
        } catch (ex: NoSuchMethodException) {
            return KtConfigResult.Failure(KtConfigError.Reflection.ThrowNoSuchMethodException(config, "constructor(KtConfig, String) を定義してください"))
        }
        return config.bukkitConfig.getConfigurationSection(path)?.run {
            getKeys(false).associateWith {
                constructor.isAccessible = true
                constructor.newInstance(config, "$path.$it")
            }.run {
                KtConfigResult.Success(this)
            }
        } ?: KtConfigResult.Failure(KtConfigError.NotFound(config, path))
    }

    override fun set(config: KtConfig, path: String, value: Map<String, T>?) {
        value?.values?.forEach { section ->
            section.getValues().forEach {
                it.set(it.getValue())
            }
        }
    }
}
