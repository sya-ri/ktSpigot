package dev.s7a.spigot.config

import java.lang.reflect.Field

/**
 * コンフィグから値を取得したときのエラー
 *
 * @property config コンフィグ
 * @since 1.0.0
 */
abstract class KtConfigError(val config: KtConfig) {
    /**
     * エラーメッセージ
     *
     * @since 1.0.0
     */
    abstract val message: String

    /**
     * ファイルに対応する値が設定されてない
     *
     * @property path コンフィグパス
     * @since 1.0.0
     */
    class NotFound(config: KtConfig, val path: String) : KtConfigError(config) {
        override val message = "$path が見つかりませんでした"
    }

    /**
     * クラスキャストに失敗した
     *
     * @property path コンフィグパス
     * @property className クラス名
     * @since 1.0.0
     */
    class ClassCastException(config: KtConfig, val path: String, val className: String) : KtConfigError(config) {
        override val message = "$path の値を $className として取得できませんでした"
    }

    /**
     * 値に一致する列挙型が存在しなかった
     *
     * @property path コンフィグパス
     * @param value 値に関する情報
     * @property className クラス名
     * @since 1.0.0
     */
    class NotFoundEnumConstant(config: KtConfig, val path: String, val value: String, val className: String) : KtConfigError(config) {
        override val message = "$path の値($value)に一致する $className の値が見つかりませんでした"
    }

    /**
     * リフレクションに関するエラー
     *
     * @since 1.0.0
     */
    abstract class Reflection(config: KtConfig) : KtConfigError(config) {
        /**
         * [SecurityException] が投げられたとき
         *
         * @property field フィールド
         * @since 1.0.0
         */
        class ThrowSecurityException(config: KtConfig, val field: Field) : Reflection(config) {
            override val message = "${field.name} へのアクセスで SecurityException が投げられました"
        }

        /**
         * [ThrowIllegalAccessException] が投げられたとき
         *
         * @property field フィールド
         * @since 1.0.0
         */
        class ThrowIllegalAccessException(config: KtConfig, val field: Field) : Reflection(config) {
            override val message = "${field.name} へのアクセスで IllegalAccessException が投げられました"
        }
    }
}
