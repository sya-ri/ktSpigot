package dev.s7a.ktspigot.config

import net.md_5.bungee.api.plugin.Plugin
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
    val resourcePlugin: Plugin

    /**
     * デフォルトコンフィグに使うリソースのファイル名
     *
     * @since 1.0.0
     */
    val resourceName: String

    override fun saveDefault(file: File) {
        resourcePlugin.getResourceAsStream(resourceName).copyTo(file.outputStream())
    }
}
