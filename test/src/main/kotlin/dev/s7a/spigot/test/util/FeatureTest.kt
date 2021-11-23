package dev.s7a.spigot.test.util

import dev.s7a.spigot.command.KtCommandExecuteAction
import dev.s7a.spigot.command.KtCommandExecuteParameter
import dev.s7a.spigot.command.KtCommandTabCompleteBuilder
import dev.s7a.spigot.util.sendChatMessage

/**
 * 機能テスト
 */
fun featureTest(name: String, tabComplete: KtCommandTabCompleteBuilder = {}, execute: KtCommandExecuteAction) = FeatureTest(name, tabComplete, execute)

/**
 * 機能単位のテスト
 *
 * @property tabComplete タブ補完処理
 * @property execute 実行処理
 */
open class FeatureTest(name: String?, val tabComplete: KtCommandTabCompleteBuilder, val execute: KtCommandExecuteAction) {
    /**
     * 機能名
     */
    val name = name ?: this::class.java.simpleName.removeSuffix("Test").lowercase()

    /**
     * 機能テストを一括管理する
     */
    class Container(vararg features: FeatureTest) {
        /**
         * タブ補完
         */
        val tabComplete: KtCommandTabCompleteBuilder = {
            features.forEach {
                literal(it.name) {
                    it.tabComplete(this)
                }
            }
        }

        /**
         * 実行
         */
        val execute: KtCommandExecuteAction = { (sender, label, args) ->
            val arg0 = args.getOrNull(0)
            val feature = features.firstOrNull { it.name.equals(arg0, true) }
            if (feature != null) {
                feature.execute(KtCommandExecuteParameter(sender, label, args.drop(1)))
            } else {
                sender.sendChatMessage("&cFeature($arg0) Not Found. [${features.joinToString(transform = FeatureTest::name)}]")
            }
        }
    }

    /**
     * [Container] を使った機能テスト
     */
    open class UseContainer(features: Container, name: String? = null) : FeatureTest(name, features.tabComplete, features.execute) {
        constructor(vararg features: FeatureTest, name: String? = null) : this(Container(*features), name)
    }
}
