package dev.s7a.ktspigot.config

import dev.s7a.ktspigot.util.LazyMutable
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * コンフィグ
 *
 * @property file ファイル
 * @since 1.0.0
 */
abstract class KtConfig(file: File) : KtConfigBase(file) {
    /**
     * @param plugin プラグイン
     * @param fileName ファイル名
     * @since 1.0.0
     */
    constructor(plugin: JavaPlugin, fileName: String) : this(plugin.dataFolder.resolve(fileName))

    /**
     * コンフィグの実際のデータ
     *
     * @since 1.0.0
     */
    var bukkitConfig by LazyMutable { loadFromFile() }
        private set

    /**
     * ファイルからコンフィグを読み込む
     *
     * @since 1.0.0
     */
    private fun loadFromFile(): YamlConfiguration {
        onLoadFile()
        return YamlConfiguration.loadConfiguration(file)
    }

    override fun load() {
        bukkitConfig = loadFromFile()
    }

    final override fun contains(path: String): Boolean {
        return bukkitConfig.contains(path)
    }

    final override fun save() {
        bukkitConfig.save(file)
    }

    final override fun isList(path: String): Boolean {
        return bukkitConfig.isList(path)
    }

    final override fun get(path: String): Any? {
        return bukkitConfig.get(path)
    }

    final override fun getSectionKeys(path: String): Set<String>? {
        return bukkitConfig.getConfigurationSection(path)?.getKeys(false)
    }

    final override fun getMapList(path: String): List<Map<*, *>> {
        return bukkitConfig.getMapList(path)
    }

    final override fun set(path: String, value: Any?) {
        bukkitConfig.set(path, value)
    }
}
