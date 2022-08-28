package dev.s7a.ktspigot.config

import java.io.File

/**
 * デフォルトコンフィグ
 *
 * @since 1.0.0
 */
interface KtConfigDefault {
    /**
     * デフォルトを読み込む
     *
     * @param file 保存先のファイル
     * @since 1.0.0
     */
    fun saveDefault(file: File)
}
