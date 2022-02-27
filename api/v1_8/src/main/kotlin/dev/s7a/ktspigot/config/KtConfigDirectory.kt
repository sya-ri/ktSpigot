package dev.s7a.ktspigot.config

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * ディレクトリ内のファイルをコンフィグとして読み込む
 *
 * @param T [KtConfig]
 * @property directory ディレクトリ
 * @since 1.0.0
 */
abstract class KtConfigDirectory<T : KtConfig>(directory: File) : KtConfigDirectoryBase<T>(directory) {
    /**
     * @param plugin プラグイン
     * @param directoryName ディレクトリ名
     * @since 1.0.0
     */
    constructor(plugin: JavaPlugin, directoryName: String) : this(plugin.dataFolder.resolve(directoryName))

    /**
     * ディレクトリ内のファイルを再帰的に取得し、コンフィグとして読み込む
     *
     * @param T [KtConfig]
     * @property directory ディレクトリ
     * @since 1.0.0
     */
    abstract class Recursive<T : KtConfig>(directory: File) : KtConfigDirectoryBase.Recursive<T>(directory) {
        /**
         * @param plugin プラグイン
         * @param directoryName ディレクトリ名
         * @since 1.0.0
         */
        constructor(plugin: JavaPlugin, directoryName: String) : this(plugin.dataFolder.resolve(directoryName))
    }
}
