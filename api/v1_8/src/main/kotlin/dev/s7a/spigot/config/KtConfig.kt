package dev.s7a.spigot.config

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * コンフィグ
 *
 * @property file ファイル
 * @since 1.0.0
 */
abstract class KtConfig(val file: File) : KtConfigSection {
    /**
     * @param plugin プラグイン
     * @param fileName ファイル名
     * @since 1.0.0
     */
    constructor(plugin: JavaPlugin, fileName: String) : this(plugin.dataFolder.resolve(fileName))

    override val config
        get() = this

    override val path = ""

    override fun fullPath(path: String) = path

    /**
     * コンフィグの実際のデータ。読み込まれてなければ自動で読み込む
     *
     * @since 1.0.0
     */
    private var _bukkitConfig: YamlConfiguration? = null
        get() /* : YamlConfiguration */ {
            if (field == null) loadFromFile()
            return field
        }

    /**
     * コンフィグの実際のデータ
     *
     * @since 1.0.0
     */
    val bukkitConfig: YamlConfiguration
        get() = _bukkitConfig!!

    /**
     * ファイルからコンフィグを読み込む
     *
     * @since 1.0.0
     */
    protected fun loadFromFile() {
        if (file.exists().not()) {
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
        _bukkitConfig = YamlConfiguration.loadConfiguration(file)
    }

    /**
     * コンフィグの値を読み込む。既に読み込んでいる場合はリロードする
     *
     * @since 1.0.0
     */
    open fun load() {
        loadFromFile()
    }

    /**
     * コンフィグに指定したパスが存在するか
     *
     * @param path コンフィグパス
     * @return 存在すれば true
     * @since 1.0.0
     */
    fun contains(path: String): Boolean {
        return bukkitConfig.contains(path)
    }

    /**
     * コンフィグへの変更を保存する
     *
     * @since 1.0.0
     */
    fun save() {
        bukkitConfig.save(file)
    }

    /**
     * ファイルを削除する
     *
     * @return ファイルの削除に成功すれば true
     * @since 1.0.0
     */
    fun delete(): Boolean {
        return file.delete()
    }
}
