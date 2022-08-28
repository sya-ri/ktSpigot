package dev.s7a.ktspigot.config

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * リソースをデフォルトコンフィグとして利用する
 *
 * @since 1.0.0
 */
interface KtConfigDefaultResource : KtConfigDefault {
    /**
     * デフォルトコンフィグに使うリソースのプラグイン
     *
     * @since 1.0.0
     */
    val resourcePlugin: JavaPlugin

    /**
     * デフォルトコンフィグに使うリソースのファイル名
     *
     * @since 1.0.0
     */
    val resourceName: String

    override fun saveDefault(file: File) {
        resourcePlugin.getResource(resourceName).copyTo(file.outputStream())
    }
}
