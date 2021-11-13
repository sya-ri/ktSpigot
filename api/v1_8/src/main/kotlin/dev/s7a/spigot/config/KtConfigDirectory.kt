package dev.s7a.spigot.config

import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * ディレクトリ内のファイルをコンフィグとして読み込む
 *
 * @param T [KtConfig]
 * @property directory ディレクトリ
 * @since 1.0.0
 */
abstract class KtConfigDirectory<out T : KtConfig>(val directory: File) {
    /**
     * @param plugin プラグイン
     * @param directoryName ディレクトリ名
     * @since 1.0.0
     */
    constructor(plugin: JavaPlugin, directoryName: String) : this(plugin.dataFolder.resolve(directoryName))

    /**
     * 読み込んだファイル一覧
     *
     * @since 1.0.0
     */
    private val files = ConcurrentHashMap<File, T>()

    /**
     * コンフィグを読み込む。ファイルが存在しなければ新しく作成する
     *
     * @param file ファイル
     * @return 読み込んだコンフィグ
     * @since 1.0.0
     */
    operator fun get(file: File): T = files.getOrPut(file) { new(file) }

    /**
     * コンフィグを読み込む。ファイルが存在しなければ新しく作成する
     *
     * @param fileName ファイル名
     * @return 読み込んだコンフィグ
     * @since 1.0.0
     */
    operator fun get(fileName: String): T = get(directory.resolve(fileName))

    /**
     * コンフィグを読み込む。ファイルが存在しなければ何もしない
     *
     * @param file ファイル
     * @return 読み込んだコンフィグ。ファイルが存在しなければ null
     * @since 1.0.0
     */
    fun getOrNull(file: File): T? = files[file] ?: run {
        if (file.exists()) {
            files.putIfAbsent(file, new(file))
        } else {
            null
        }
    }

    /**
     * コンフィグを読み込む
     *
     * @param fileName ファイル名
     * @return 読み込んだコンフィグ。ファイルが存在しなければ null
     * @since 1.0.0
     */
    fun getOrNull(fileName: String): T? = getOrNull(directory.resolve(fileName))

    /**
     * ディレクトリ内のコンフィグを全て読み込む
     *
     * @return 読み込んだコンフィグ
     * @since 1.0.0
     */
    open fun loadAll(): List<T> {
        directory.mkdirs()
        return directory.listFiles()?.filter(File::isFile)?.mapNotNull(::get).orEmpty()
    }

    /**
     * 既に読み込み済みのコンフィグ
     *
     * @since 1.0.0
     */
    val loadedConfigList
        get() = files.values.toList()

    /**
     * コンフィグを削除する
     *
     * @param file ファイル
     * @since 1.0.0
     */
    fun delete(file: File) = files.remove(file)?.apply(KtConfig::delete)

    /**
     * コンフィグを削除する
     *
     * @param fileName ファイル名
     * @since 1.0.0
     */
    fun delete(fileName: String) = delete(directory.resolve(fileName))

    /**
     * コンフィグを削除する
     *
     * @param config コンフィグ
     * @since 1.0.0
     */
    fun delete(config: KtConfig) = delete(config.file)

    /**
     * ファイルをコンフィグに変換する。内部で使用されるので、[get], [getOrNull] を代わりに使用してください
     *
     * @param file ファイル
     * @return コンフィグ
     * @see get
     * @see getOrNull
     * @since 1.0.0
     */
    protected abstract fun new(file: File): T

    /**
     * ディレクトリ内のファイルを再帰的に取得し、コンフィグとして読み込む
     *
     * @param T [KtConfig]
     * @property directory ディレクトリ
     * @since 1.0.0
     */
    abstract class Recursive<out T : KtConfig>(directory: File) : KtConfigDirectory<T>(directory) {
        /**
         * @param plugin プラグイン
         * @param directoryName ディレクトリ名
         * @since 1.0.0
         */
        constructor(plugin: JavaPlugin, directoryName: String) : this(plugin.dataFolder.resolve(directoryName))

        override fun loadAll(): List<T> {
            fun loadAll(_directory: File): List<T> {
                return _directory.listFiles()?.flatMap {
                    if (it.isDirectory) {
                        loadAll(it)
                    } else {
                        listOf(get(it))
                    }
                }.orEmpty()
            }

            directory.mkdirs()
            return loadAll(directory)
        }
    }
}
