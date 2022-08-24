package dev.s7a.ktspigot.config

import dev.s7a.ktspigot.util.LazyMutable
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File

/**
 * コンフィグ
 *
 * @property file ファイル
 * @since 1.0.0
 */
abstract class KtConfig(file: File, autoSave: Boolean = true) : KtConfigBase(file, autoSave) {
    companion object {
        private val provider: ConfigurationProvider = ConfigurationProvider.getProvider(YamlConfiguration::class.java)
    }

    /**
     * @param plugin プラグイン
     * @param fileName ファイル名
     * @since 1.0.0
     */
    constructor(plugin: Plugin, fileName: String) : this(plugin.dataFolder.resolve(fileName))

    /**
     * コンフィグの実際のデータ
     *
     * @since 1.0.0
     */
    var bungeeConfig by LazyMutable { loadFromFile() }
        private set

    /**
     * ファイルからコンフィグを読み込む
     *
     * @since 1.0.0
     */
    private fun loadFromFile(): Configuration {
        onLoadFile()
        return provider.load(file)
    }

    override fun load() {
        bungeeConfig = loadFromFile()
    }

    final override fun contains(path: String): Boolean {
        return bungeeConfig.contains(path)
    }

    final override fun save() {
        provider.save(bungeeConfig, file)
    }

    final override fun isList(path: String): Boolean {
        return get(path) is List<*>
    }

    final override fun get(path: String): Any? {
        return bungeeConfig.get(path)
    }

    final override fun getSectionKeys(path: String): Collection<String>? {
        return bungeeConfig.getSection(path)?.keys
    }

    final override fun getMapList(path: String): List<Map<*, *>> {
        return bungeeConfig.getList(path)?.filterIsInstance<Map<*, *>>().orEmpty()
    }

    final override fun set(path: String, value: Any?) {
        bungeeConfig.set(path, value)
    }
}
