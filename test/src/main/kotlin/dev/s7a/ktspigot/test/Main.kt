package dev.s7a.ktspigot.test

import dev.s7a.ktspigot.command.ktCommand
import dev.s7a.ktspigot.test.feature.InventoryTest
import dev.s7a.ktspigot.test.feature.MessageTest
import dev.s7a.ktspigot.test.util.FeatureTest
import org.bukkit.plugin.java.JavaPlugin

/***
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    private val features = FeatureTest.Container(
        InventoryTest,
        MessageTest,
    )

    override fun onEnable() {
        ktCommand("test") {
            tabComplete(features.tabComplete)
            execute(features.execute)
        }
    }
}
