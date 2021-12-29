package dev.s7a.spigot.config

class KtConfigSectionEditor<T>(
    private val config: KtConfig,
    private val path: String,
    private val clazz: Class<T>,
    map: MutableMap<String, T>
) : MutableMap<String, T> by map {
    fun put(key: String, block: T.() -> Unit): T {
        val constructor = try {
            clazz.getDeclaredConstructor(KtConfig::class.java, String::class.java)
        } catch (ex: NoSuchMethodException) {
            throw ex
        }
        constructor.isAccessible = true
        return constructor.newInstance(config, "$path.$key").apply(block).apply {
            put(key, this)
        }
    }
}
