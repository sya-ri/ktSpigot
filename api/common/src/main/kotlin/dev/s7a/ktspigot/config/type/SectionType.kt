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
        val section = clazz.declaredConstructors.firstNotNullOfOrNull {
            if (it.parameterCount != 2) return@firstNotNullOfOrNull null
            val (type0, type1) = it.parameterTypes
            when {
                KtConfigBase::class.java.isAssignableFrom(type0) && type1.isAssignableFrom(String::class.java) -> {
                    it.isAccessible = true
                    it.newInstance(config, path)
                }
                type0.isAssignableFrom(String::class.java) && KtConfigBase::class.java.isAssignableFrom(type1) -> {
                    it.isAccessible = true
                    it.newInstance(path, config)
                }
                else -> {
                    null
                }
            }
        } ?: return KtConfigResult.Failure(KtConfigError.Reflection.ThrowNoSuchMethodException(config, "constructor(KtConfigBase, String) を定義してください"))
        @Suppress("UNCHECKED_CAST") return KtConfigResult.Success(section as T)
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
