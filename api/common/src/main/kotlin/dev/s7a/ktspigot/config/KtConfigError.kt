package dev.s7a.ktspigot.config

import java.lang.reflect.Field

/**
 * コンフィグから値を取得したときのエラー
 *
 * @property config コンフィグ
 * @since 1.0.0
 */
abstract class KtConfigError(val config: KtConfigBase) {
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
    class NotFound(config: KtConfigBase, val path: String) : KtConfigError(config) {
        override val message = "$path が見つかりませんでした"
    }

    /**
     * クラスキャストに失敗した
     *
     * @property path コンフィグパス
     * @property className クラス名
     * @since 1.0.0
     */
    class ClassCastException(config: KtConfigBase, val path: String, val className: String) : KtConfigError(config) {
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
    class NotFoundEnumConstant(config: KtConfigBase, val path: String, val value: String, val className: String) : KtConfigError(config) {
        override val message = "$path の値($value)に一致する $className の値が見つかりませんでした"
    }

    /**
     * 不正なフォーマット
     *
     * @property path コンフィグパス
     * @property string 元の文字列
     * @property formatter 使用したフォーマッタ
     * @since 1.0.0
     */
    class IllegalFormat<T>(config: KtConfigBase, val path: String, val string: String, val formatter: KtConfigFormatter<T>) : KtConfigError(config) {
        override val message = "$path の値($string)はフォーマット(${formatter.name})に一致していません"
    }

    /**
     * 複数のエラーを持つクラス
     *
     * @since 1.0.0
     */
    interface ErrorContainer<out T> {
        /**
         * エラーの一覧
         *
         * @since 1.0.0
         */
        val errors: List<KtConfigResult.Failure<T>>
    }

    /**
     * リスト内でコンフィグエラーが発生した
     *
     * @property path コンフィグパス
     * @property data 値の一覧
     * @property errors エラーの一覧
     * @since 1.0.0
     */
    class ListConfigError<out T>(config: KtConfigBase, val path: String, val data: List<T>, override val errors: List<KtConfigResult.Failure<T>>) :
        KtConfigError(config),
        ErrorContainer<T> {
        override val message = "$path のリストの内部でエラーが発生しました [${errors.size}]"
    }

    /**
     * リスト内でコンフィグエラーが発生した
     *
     * @property path コンフィグパス
     * @property data 値の一覧
     * @property errors エラーの一覧
     * @since 1.0.0
     */
    class MapConfigError<out T>(config: KtConfigBase, val path: String, val data: Map<String, T>, override val errors: List<KtConfigResult.Failure<T>>) :
        KtConfigError(config),
        ErrorContainer<T> {
        override val message = "$path のマップ内部でエラーが発生しました [${errors.size}]"
    }

    /**
     * リフレクションに関するエラー
     *
     * @since 1.0.0
     */
    abstract class Reflection(config: KtConfigBase) : KtConfigError(config) {
        /**
         * [SecurityException] が投げられたとき
         *
         * @property field フィールド
         * @since 1.0.0
         */
        class ThrowSecurityException(config: KtConfigBase, val field: Field) : Reflection(config) {
            override val message = "${field.name} へのアクセスで SecurityException が投げられました"
        }

        /**
         * [ThrowIllegalAccessException] が投げられたとき
         *
         * @property field フィールド
         * @since 1.0.0
         */
        class ThrowIllegalAccessException(config: KtConfigBase, val field: Field) : Reflection(config) {
            override val message = "${field.name} へのアクセスで IllegalAccessException が投げられました"
        }

        /**
         * [NoSuchMethodException] が投げられたとき
         *
         * @param description 説明
         * @since 1.0.0
         */
        class ThrowNoSuchMethodException(config: KtConfigBase, description: String) : Reflection(config) {
            override val message = "NoSuchMethodException が投げられました ($description)"
        }
    }
}
