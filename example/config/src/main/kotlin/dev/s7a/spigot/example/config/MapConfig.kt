package dev.s7a.spigot.example.config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.checkValues
import dev.s7a.spigot.config.intValue
import dev.s7a.spigot.config.printErrors
import dev.s7a.spigot.config.section
import dev.s7a.spigot.example.config.Main.Companion.plugin

/**
 * [KtConfig] の例
 */
object MapConfig : KtConfig(plugin, "map.yml") {
    /**
     * [section] に使うクラス
     */
    private class Value(override val config: KtConfig, override val path: String) : KtConfigSection {
        /**
         * コンフィグから取得する値
         */
        val int = intValue("int")
    }

    /**
     * セクションマップ
     *
     * ```yml
     * values:
     *   one:
     *     int: 1
     *   two:
     *     int: 2
     *   three:
     *     int: 3
     * ```
     */
    private val values = section<Value>("values")

    override fun load() {
        checkValues().printErrors(plugin.logger)
        println("values: ${values.get()}")
        values.getValue()?.forEach { (_, value) ->
            println("${value.int.path}: ${value.int.getValue()}")
        }
    }
}
