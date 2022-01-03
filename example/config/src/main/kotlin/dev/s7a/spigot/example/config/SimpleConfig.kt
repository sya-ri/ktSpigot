package dev.s7a.spigot.example.config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.checkValues
import dev.s7a.spigot.config.printErrors
import dev.s7a.spigot.config.type.intValue
import dev.s7a.spigot.example.config.Main.Companion.plugin
import kotlin.random.Random

/**
 * [KtConfig] の例
 */
object SimpleConfig : KtConfig(plugin, "simple.yml") {
    /**
     * 整数値をコンフィグから取得する。存在しなければエラーを出す
     */
    private val int1 = intValue("int1")

    /**
     * 整数値をコンフィグから取得する。存在しなくてもエラーを出さない
     */
    private val int2OrNull = intValue("int2").nullable()

    /**
     * 整数値をコンフィグから取得する。存在しなければデフォルト値にする。使用したデフォルト値は保存される
     */
    private val int3OrDefault = intValue("int3").default(0)

    /**
     * 整数値をコンフィグから取得する。存在しなければデフォルト値にする。使用したデフォルト値は保存される
     */
    private val int4OrDefault = intValue("int4").default(Random.Default::nextInt)

    /**
     * 整数値をコンフィグから取得する。値が取得できなければデフォルト値にする。使用したデフォルト値は保存される
     */
    private val int5OrDefault = intValue("int5").default(0).force()

    override fun load() {
        super.load()
        checkValues().printErrors(plugin.logger)
        println("int1: ${int1.get()} / ${int1.getValue()}")
        println("int2 (orNull): ${int2OrNull.get()} / ${int2OrNull.getValue()}")
        println("int3 (orDefault): ${int3OrDefault.get()} / ${int3OrDefault.getValue()}")
        println("int4 (orDefault): ${int4OrDefault.get()} / ${int4OrDefault.getValue()}")
        println("int5 (orDefault): ${int5OrDefault.get()} / ${int5OrDefault.getValue()}")
    }
}
