package dev.s7a.ktspigot.config

import java.util.logging.Logger as JavaLogger

/**
 * [KtConfigError] のハンドリングを行うクラス
 *
 * @since 1.0.0
 */
fun interface KtConfigErrorHandler {
    /**
     * エラーのハンドリングを行う
     *
     * @param error エラー
     * @since 1.0.0
     */
    fun handle(error: KtConfigError)

    /**
     * エラーをロガーに送信する
     *
     * @property logger ロガー
     * @since 1.0.0
     */
    class Logger(private val logger: JavaLogger) : KtConfigErrorHandler {
        override fun handle(error: KtConfigError) {
            logger.warning("[${error.config.file.path}] ${error.message}")
        }
    }
}
