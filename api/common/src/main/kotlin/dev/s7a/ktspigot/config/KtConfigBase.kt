package dev.s7a.ktspigot.config

import java.io.File
import java.io.FileNotFoundException

/**
 * コンフィグ
 *
 * @property file ファイル
 * @since 1.0.0
 */
abstract class KtConfigBase(val file: File) : KtConfigSection {
    override val config
        get() = this

    override val path = ""

    override fun fullPath(path: String) = path

    /**
     * コンフィグファイルを作成したときの処理
     *
     * @since 1.0.0
     */
    protected open fun onCreateNewFile() {}

    /**
     * コンフィグを読み込むときの処理
     *
     * @since 1.0.0
     */
    protected fun onLoadFile() {
        when {
            file.exists().not() -> {
                file.parentFile?.mkdirs()
                file.createNewFile()
                onCreateNewFile()
            }
            file.isDirectory -> {
                throw FileNotFoundException("${file.path} (Is a directory)")
            }
        }
    }

    /**
     * コンフィグの値を読み込む。既に読み込んでいる場合はリロードする
     *
     * @since 1.0.0
     */
    abstract fun load()

    /**
     * コンフィグに指定したパスが存在するか
     *
     * @param path コンフィグパス
     * @return 存在すれば true
     * @since 1.0.0
     */
    abstract fun contains(path: String): Boolean

    /**
     * コンフィグへの変更を保存する
     *
     * @since 1.0.0
     */
    abstract fun save()

    /**
     * ファイルを削除する
     *
     * @return ファイルの削除に成功すれば true
     * @since 1.0.0
     */
    fun delete(): Boolean {
        return file.delete()
    }

    /**
     * コンフィグに指定したパスの値がリストであるか
     *
     * @param path コンフィグパス
     * @return リストならば true
     * @since 1.0.0
     */
    abstract fun isList(path: String): Boolean

    /**
     * コンフィグから値を取得する
     *
     * @param path コンフィグパス
     * @return 値
     * @since 1.0.0
     */
    abstract fun get(path: String): Any?

    /**
     * コンフィグからセクションキーを取得する
     *
     * @param path コンフィグパス
     * @return セクションキー
     * @since 1.0.0
     */
    abstract fun getSectionKeys(path: String): Collection<String>?

    /**
     * コンフィグからマップリストを取得する
     *
     * @param path コンフィグパス
     * @return マップリスト
     * @since 1.0.0
     */
    abstract fun getMapList(path: String): List<Map<*, *>>

    /**
     * コンフィグに値を設定する
     *
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.0.0
     */
    abstract fun set(path: String, value: Any?)
}
